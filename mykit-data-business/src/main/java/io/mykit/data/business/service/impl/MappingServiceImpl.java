/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.data.business.service.impl;

import io.mykit.data.business.checker.Checker;
import io.mykit.data.business.exception.BizException;
import io.mykit.data.business.service.MappingService;
import io.mykit.data.business.vo.ConnectorVo;
import io.mykit.data.business.vo.MappingVo;
import io.mykit.data.business.vo.MetaVo;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.monitor.Monitor;
import io.mykit.data.parser.enums.ModelEnum;
import io.mykit.data.parser.logger.LogType;
import io.mykit.data.parser.model.*;
import io.mykit.data.storage.constants.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
@Service
public class MappingServiceImpl  extends BaseServiceImpl implements MappingService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Monitor monitor;

    @Autowired
    private Checker mappingChecker;

    @Override
    public String add(Map<String, String> params) {
        ConfigModel model = mappingChecker.checkAddConfigModel(params);
        log(LogType.MappingLog.INSERT, (Mapping) model);

        return manager.addMapping(model);
    }

    @Override
    public String edit(Map<String, String> params) {
        String id = params.get(ConfigConstants.CONFIG_MODEL_ID);
        Mapping mapping = assertMappingExist(id);
        synchronized (LOCK) {
            assertRunning(mapping.getMetaId());
            ConfigModel model = mappingChecker.checkEditConfigModel(params);
            log(LogType.MappingLog.UPDATE, (Mapping) model);

            return manager.editMapping(model);
        }
    }

    @Override
    public String remove(String id) {
        Mapping mapping = assertMappingExist(id);
        String metaId = mapping.getMetaId();
        Meta meta = manager.getMeta(metaId);
        synchronized (LOCK) {
            assertRunning(metaId);

            // 删除数据
            manager.clearData(metaId);
            log(LogType.MetaLog.CLEAR, meta);

            // 删除meta
            manager.removeMeta(metaId);
            log(LogType.MetaLog.DELETE, meta);

            // 删除tableGroup
            List<TableGroup> groupList = manager.getTableGroupAll(id);
            if (!CollectionUtils.isEmpty(groupList)) {
                groupList.forEach(t -> manager.removeTableGroup(t.getId()));
            }

            // 删除驱动
            manager.removeMapping(id);
            log(LogType.MappingLog.DELETE, mapping);
        }
        return "驱动删除成功";
    }

    @Override
    public MappingVo getMapping(String id) {
        Mapping mapping = manager.getMapping(id);
        return convertMapping2Vo(mapping);
    }

    @Override
    public List<MappingVo> getMappingAll() {
        List<MappingVo> list = manager.getMappingAll()
                .stream()
                .map(m -> convertMapping2Vo(m))
                .sorted(Comparator.comparing(MappingVo::getUpdateTime).reversed())
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public String start(String id) {
        Mapping mapping = assertMappingExist(id);
        final String metaId = mapping.getMetaId();
        synchronized (LOCK) {
            assertRunning(metaId);

            // 清空同步记录
            Meta meta = manager.getMeta(metaId);
            meta.getFail().set(0);
            meta.getSuccess().set(0);
            manager.editMeta(meta);

            // 启动
            manager.start(mapping);

            log(LogType.MappingLog.RUNNING, mapping);
        }
        return "驱动启动成功";
    }

    @Override
    public String stop(String id) {
        Mapping mapping = assertMappingExist(id);
        synchronized (LOCK) {
            if (!isRunning(mapping.getMetaId())) {
                throw new BizException("驱动已停止.");
            }
            manager.close(mapping);

            log(LogType.MappingLog.STOP, mapping);
        }
        return "驱动停止成功";
    }

    private MappingVo convertMapping2Vo(Mapping mapping) {
        String model = mapping.getModel();
        Assert.notNull(mapping, "Mapping can not be null.");
        Connector s = manager.getConnector(mapping.getSourceConnectorId());
        Connector t = manager.getConnector(mapping.getTargetConnectorId());
        ConnectorVo sConn = new ConnectorVo(alive(s.getId()));
        BeanUtils.copyProperties(s, sConn);
        ConnectorVo tConn = new ConnectorVo(alive(t.getId()));
        BeanUtils.copyProperties(t, tConn);

        // 元信息
        Meta meta = manager.getMeta(mapping.getMetaId());
        Assert.notNull(meta, "Meta can not be null.");
        MetaVo metaVo = new MetaVo(ModelEnum.getModelEnum(model).getName(), mapping.getName());
        BeanUtils.copyProperties(meta, metaVo);

        MappingVo vo = new MappingVo(sConn, tConn, metaVo);
        BeanUtils.copyProperties(mapping, vo);
        return vo;
    }

    /**
     * 检查是否存在驱动
     *
     * @param mappingId
     * @return
     */
    private Mapping assertMappingExist(String mappingId) {
        Mapping mapping = manager.getMapping(mappingId);
        Assert.notNull(mapping, "驱动不存在.");
        return mapping;
    }

    @Cacheable(value = "connector", keyGenerator = "cacheKeyGenerator")
    public boolean alive(String id) {
        Connector connector = manager.getConnector(id);
        return null != connector ? manager.alive(connector.getConfig()) : false;
    }
}
