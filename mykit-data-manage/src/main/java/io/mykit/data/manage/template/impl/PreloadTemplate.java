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
package io.mykit.data.manage.template.impl;

import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.manage.Manager;
import io.mykit.data.manage.config.PreloadCallBack;
import io.mykit.data.manage.config.PreloadConfig;
import io.mykit.data.manage.config.QueryConfig;
import io.mykit.data.manage.enums.GroupStrategyEnum;
import io.mykit.data.manage.enums.HandlerEnum;
import io.mykit.data.manage.template.AbstractTemplate;
import io.mykit.data.manage.template.Handler;
import io.mykit.data.parser.Parser;
import io.mykit.data.parser.enums.MetaEnum;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.Meta;
import io.mykit.data.storage.StorageService;
import io.mykit.data.storage.constants.ConfigConstants;
import io.mykit.data.storage.enums.StorageEnum;
import io.mykit.data.storage.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 预加载模板
 */
@Component
public class PreloadTemplate extends AbstractTemplate implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Parser parser;

    @Autowired
    private Manager manager;

    @Autowired
    private StorageService storageService;

    @Autowired
    private OperationTemplate operationTemplate;

    public void execute(PreloadConfig config) {
        Query query = new Query();
        String filterType = config.getFilterType();
        query.put(ConfigConstants.CONFIG_MODEL_TYPE, filterType);
        List<Map> list = storageService.query(StorageEnum.CONFIG, query);
        boolean empty = CollectionUtils.isEmpty(list);
        logger.info("PreLoad {}:{}", filterType, empty ? 0 : list.size());
        if (!empty) {
            Handler handler = config.getHandlerEnum().getHandler();
            GroupStrategyEnum strategy = getDefaultStrategy(config);
            list.forEach(map -> {
                String json = (String) map.get(ConfigConstants.CONFIG_MODEL_JSON);
                ConfigModel model = (ConfigModel) handler.execute(new PreloadCallBack(parser, json));
                if (null != model) {
                    operationTemplate.cache(model, strategy);
                }
            });
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Load connectors
        execute(new PreloadConfig(ConfigConstants.CONNECTOR, HandlerEnum.PRELOAD_CONNECTOR));
        // Load mappings
        execute(new PreloadConfig(ConfigConstants.MAPPING, HandlerEnum.PRELOAD_MAPPING));
        // Load tableGroups
        execute(new PreloadConfig(ConfigConstants.TABLE_GROUP, GroupStrategyEnum.TABLE, HandlerEnum.PRELOAD_TABLE_GROUP));
        // Load metas
        execute(new PreloadConfig(ConfigConstants.META, HandlerEnum.PRELOAD_META));
        // Load configs
        execute(new PreloadConfig(ConfigConstants.CONFIG, HandlerEnum.PRELOAD_CONFIG));

        // 启动驱动
        Meta meta = new Meta();
        meta.setType(ConfigConstants.META);
        QueryConfig<Meta> queryConfig = new QueryConfig<>(meta);
        List<Meta> metas = operationTemplate.queryAll(queryConfig);
        if (!CollectionUtils.isEmpty(metas)) {
            metas.forEach(m -> {
                // 恢复驱动状态
                if (MetaEnum.RUNNING.getCode() == m.getState()) {
                    Mapping mapping = manager.getMapping(m.getMappingId());
                    manager.start(mapping);
                } else if (MetaEnum.STOPPING.getCode() == m.getState()) {
                    manager.changeMetaState(m.getId(), MetaEnum.READY);
                }
            });
        }
    }
}
