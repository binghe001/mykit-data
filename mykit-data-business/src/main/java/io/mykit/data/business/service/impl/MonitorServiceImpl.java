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

import io.mykit.data.business.service.MonitorService;
import io.mykit.data.business.vo.DataVo;
import io.mykit.data.business.vo.LogVo;
import io.mykit.data.business.vo.MetaVo;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.common.utils.JsonUtils;
import io.mykit.data.manage.Manager;
import io.mykit.data.monitor.Monitor;
import io.mykit.data.parser.enums.ModelEnum;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.Meta;
import io.mykit.data.storage.constants.ConfigConstants;
import io.mykit.data.storage.query.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监控服务
 */
@Service
public class MonitorServiceImpl  implements MonitorService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Monitor monitor;

    @Autowired
    private Manager manager;

    @Override
    public Map getThreadInfo() {
        return monitor.getThreadInfo();
    }

    @Override
    public List<MetaVo> getMetaAll() {
        List<MetaVo> list = manager.getMetaAll()
                .stream()
                .map(m -> convertMeta2Vo(m))
                .sorted(Comparator.comparing(MetaVo::getUpdateTime).reversed())
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public String getDefaultMetaId(Map<String, String> params) {
        String id = params.get(ConfigConstants.CONFIG_MODEL_ID);
        if (StringUtils.isNotBlank(id)) {
            return id;
        }
        return getDefaultMetaId();
    }

    @Override
    public List<DataVo> queryData(Map<String, String> params) {
        String id = params.get(ConfigConstants.CONFIG_MODEL_ID);
        // 获取默认驱动元信息
        if (StringUtils.isBlank(id)) {
            id = getDefaultMetaId();
        }

        // 没有驱动
        if (StringUtils.isBlank(id)) {
            return Collections.EMPTY_LIST;
        }

        int pageNum = NumberUtils.toInt(params.get("pageNum"), 1);
        int pageSize = NumberUtils.toInt(params.get("pageSize"), 10);
        Query query = new Query(pageNum, pageSize);
        // 查询异常信息
        String error = params.get(ConfigConstants.DATA_ERROR);
        if (StringUtils.isNotBlank(error)) {
            query.put(ConfigConstants.DATA_ERROR, error, true);
        }

        List<DataVo> list = manager.queryData(query, id).stream()
                .map(m -> convert2Vo(m, DataVo.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public String clearData(String id) {
        Assert.hasText(id, "驱动不存在.");
        manager.clearData(id);
        return "清空同步数据成功";
    }

    @Override
    public List<LogVo> queryLog(Map<String, String> params) {
        int pageNum = NumberUtils.toInt(params.get("pageNum"), 1);
        int pageSize = NumberUtils.toInt(params.get("pageSize"), 10);
        Query query = new Query(pageNum, pageSize);
        // 查询日志内容
        String json = params.get(ConfigConstants.CONFIG_MODEL_JSON);
        if (StringUtils.isNotBlank(json)) {
            query.put(ConfigConstants.CONFIG_MODEL_JSON, json, true);
        }
        List<LogVo> list = manager.queryLog(query).stream()
                .map(m -> convert2Vo(m, LogVo.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public String clearLog() {
        manager.clearLog();
        return "清空日志成功";
    }

    private MetaVo convertMeta2Vo(Meta meta) {
        Mapping mapping = manager.getMapping(meta.getMappingId());
        Assert.notNull(mapping, "驱动不存在.");
        ModelEnum modelEnum = ModelEnum.getModelEnum(mapping.getModel());
        MetaVo metaVo = new MetaVo(modelEnum.getName(), mapping.getName());
        metaVo.setMappingName(mapping.getName());
        BeanUtils.copyProperties(meta, metaVo);
        return metaVo;
    }

    private <T> T convert2Vo(Map map, Class<T> clazz) {
        String json = JsonUtils.objToJson(map);
        return (T) JsonUtils.jsonToObj(json, clazz);
    }

    private String getDefaultMetaId() {
        List<MetaVo> list = getMetaAll();
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0).getId();
        }
        return "";
    }
}
