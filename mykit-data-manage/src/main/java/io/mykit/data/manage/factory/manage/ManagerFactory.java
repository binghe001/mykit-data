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
package io.mykit.data.manage.factory.manage;

import io.mykit.data.common.event.ClosedEvent;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.config.MetaInfo;
import io.mykit.data.connector.enums.ConnectorEnum;
import io.mykit.data.connector.enums.FilterEnum;
import io.mykit.data.connector.enums.OperationEnum;
import io.mykit.data.manage.Manager;
import io.mykit.data.manage.config.OperationConfig;
import io.mykit.data.manage.config.QueryConfig;
import io.mykit.data.manage.enums.GroupStrategyEnum;
import io.mykit.data.manage.enums.HandlerEnum;
import io.mykit.data.manage.puller.Puller;
import io.mykit.data.manage.template.impl.DataTemplate;
import io.mykit.data.manage.template.impl.OperationTemplate;
import io.mykit.data.monitor.enums.QuartzFilterEnum;
import io.mykit.data.parser.Parser;
import io.mykit.data.parser.enums.ConvertEnum;
import io.mykit.data.parser.enums.MetaEnum;
import io.mykit.data.parser.model.*;
import io.mykit.data.plugins.config.Plugin;
import io.mykit.data.plugins.factory.PluginFactory;
import io.mykit.data.storage.constants.ConfigConstants;
import io.mykit.data.storage.enums.StorageEnum;
import io.mykit.data.storage.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 工厂类
 */
@Component
public class ManagerFactory  implements Manager, ApplicationContextAware, ApplicationListener<ClosedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Parser parser;

    @Autowired
    private PluginFactory pluginFactory;

    @Autowired
    private OperationTemplate operationTemplate;

    @Autowired
    private DataTemplate dataTemplate;

    private Map<String, Puller> map;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(Puller.class);
    }

    @Override
    public boolean alive(ConnectorConfig config) {
        return parser.alive(config);
    }

    @Override
    public List<String> getTable(ConnectorConfig config) {
        return parser.getTable(config);
    }

    @Override
    public MetaInfo getMetaInfo(String connectorId, String tableName) {
        return parser.getMetaInfo(connectorId, tableName);
    }

    @Override
    public String addConnector(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_ADD.getHandler()));
    }

    @Override
    public String editConnector(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_EDIT.getHandler()));
    }

    @Override
    public void removeConnector(String connectorId) {
        operationTemplate.remove(new OperationConfig(connectorId));
    }

    @Override
    public Connector getConnector(String connectorId) {
        return operationTemplate.queryObject(Connector.class, connectorId);
    }

    @Override
    public List<Connector> getConnectorAll() {
        Connector connector = new Connector();
        connector.setType(ConfigConstants.CONNECTOR);
        QueryConfig<Connector> queryConfig = new QueryConfig<>(connector);
        List<Connector> connectors = operationTemplate.queryAll(queryConfig);
//        System.err.println("长度："+connectors.size());
//        System.err.println("connectors为："+connectors.get(0).getType());
//        System.err.println("connectors为："+connectors.get(0).getConfig());
//        System.err.println("connectors为："+connectors.get(0).getTable());
        return connectors;
    }

    @Override
    public String addMapping(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_ADD.getHandler()));
    }

    @Override
    public String editMapping(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_EDIT.getHandler()));
    }

    @Override
    public void removeMapping(String mappingId) {
        operationTemplate.remove(new OperationConfig(mappingId));
    }

    @Override
    public Mapping getMapping(String mappingId) {
        return operationTemplate.queryObject(Mapping.class, mappingId);
    }

    @Override
    public List<Mapping> getMappingAll() {
        Mapping mapping = new Mapping();
        mapping.setType(ConfigConstants.MAPPING);
        QueryConfig<Mapping> queryConfig = new QueryConfig<>(mapping);
        List<Mapping> mappings = operationTemplate.queryAll(queryConfig);
        return mappings;
    }

    @Override
    public String addTableGroup(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, GroupStrategyEnum.TABLE, HandlerEnum.OPR_ADD.getHandler()));
    }

    @Override
    public String editTableGroup(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, GroupStrategyEnum.TABLE, HandlerEnum.OPR_EDIT.getHandler()));
    }

    @Override
    public void removeTableGroup(String tableGroupId) {
        operationTemplate.remove(new OperationConfig(tableGroupId, GroupStrategyEnum.TABLE));
    }

    @Override
    public TableGroup getTableGroup(String tableGroupId) {
        return operationTemplate.queryObject(TableGroup.class, tableGroupId);
    }

    @Override
    public List<TableGroup> getTableGroupAll(String mappingId) {
        TableGroup tableGroup = new TableGroup();
        tableGroup.setType(ConfigConstants.TABLE_GROUP);
        tableGroup.setMappingId(mappingId);
        QueryConfig<TableGroup> queryConfig = new QueryConfig<>(tableGroup, GroupStrategyEnum.TABLE);
        List<TableGroup> tableGroups = operationTemplate.queryAll(queryConfig);
        return tableGroups;
    }

    @Override
    public Map<String, String> getCommand(Mapping mapping, TableGroup tableGroup) {
        return parser.getCommand(mapping, tableGroup);
    }

    @Override
    public long getCount(String connectorId, Map<String, String> command) {
        return parser.getCount(connectorId, command);
    }

    @Override
    public String addMeta(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_ADD.getHandler()));
    }

    @Override
    public String editMeta(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_EDIT.getHandler()));
    }

    @Override
    public Meta getMeta(String metaId) {
        return operationTemplate.queryObject(Meta.class, metaId);
    }

    @Override
    public void removeMeta(String metaId) {
        operationTemplate.remove(new OperationConfig(metaId));
    }

    @Override
    public List<Meta> getMetaAll() {
        Meta meta = new Meta();
        meta.setType(ConfigConstants.META);
        QueryConfig<Meta> queryConfig = new QueryConfig<>(meta);
        return operationTemplate.queryAll(queryConfig);
    }

    @Override
    public String addConfig(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_ADD.getHandler()));
    }

    @Override
    public String editConfig(ConfigModel model) {
        return operationTemplate.execute(new OperationConfig(model, HandlerEnum.OPR_EDIT.getHandler()));
    }

    @Override
    public Config getConfig(String configId) {
        return operationTemplate.queryObject(Config.class, configId);
    }

    @Override
    public void removeConfig(String configId) {
        operationTemplate.remove(new OperationConfig(configId));
    }

    @Override
    public List<Config> getConfigAll() {
        Config config = new Config();
        config.setType(ConfigConstants.CONFIG);
        QueryConfig<Config> queryConfig = new QueryConfig<>(config);
        return operationTemplate.queryAll(queryConfig);
    }

    @Override
    public List<Map> queryData(Query query, String collectionId) {
        return dataTemplate.query(StorageEnum.DATA, query, collectionId);
    }

    @Override
    public void clearData(String collectionId) {
        dataTemplate.clear(StorageEnum.DATA, collectionId);
    }

    @Override
    public List<Map> queryLog(Query query) {
        return dataTemplate.query(StorageEnum.LOG, query, null);
    }

    @Override
    public void clearLog() {
        dataTemplate.clear(StorageEnum.LOG, null);
    }

    @Override
    public List<ConnectorEnum> getConnectorEnumAll() {
        return parser.getConnectorEnumAll();
    }

    @Override
    public List<OperationEnum> getOperationEnumAll() {
        return parser.getOperationEnumAll();
    }

    @Override
    public List<QuartzFilterEnum> getQuartzFilterEnumAll() {
        return parser.getQuartzFilterEnumAll();
    }

    @Override
    public List<FilterEnum> getFilterEnumAll() {
        return parser.getFilterEnumAll();
    }

    @Override
    public List<ConvertEnum> getConvertEnumAll() {
        return parser.getConvertEnumAll();
    }

    @Override
    public List<Plugin> getPluginAll() {
        return pluginFactory.getPluginAll();
    }

    @Override
    public void start(Mapping mapping) {
        Puller puller = getPuller(mapping);

        // 标记运行中
        changeMetaState(mapping.getMetaId(), MetaEnum.RUNNING);

        puller.asyncStart(mapping);
    }

    @Override
    public void close(Mapping mapping) {
        Puller puller = getPuller(mapping);

        // 标记停止中
        String metaId = mapping.getMetaId();
        changeMetaState(metaId, MetaEnum.STOPPING);

        puller.close(metaId);
    }

    @Override
    public void changeMetaState(String metaId, MetaEnum metaEnum) {
        Meta meta = getMeta(metaId);
        int code = metaEnum.getCode();
        if (null != meta && meta.getState() != code) {
            meta.setState(code);
            meta.setUpdateTime(Instant.now().toEpochMilli());
            editMeta(meta);
        }
    }

    @Override
    public void onApplicationEvent(ClosedEvent event) {
        // 异步监听任务关闭事件
        changeMetaState(event.getId(), MetaEnum.READY);
    }

    private Puller getPuller(Mapping mapping) {
        Assert.notNull(mapping, "驱动不能为空");
        String model = mapping.getModel();
        String metaId = mapping.getMetaId();
        Assert.hasText(model, "同步方式不能为空");
        Assert.hasText(metaId, "任务ID不能为空");

        Puller puller = map.get(model.concat("Puller"));
        Assert.notNull(puller, String.format("未知的同步方式: %s", model));
        return puller;
    }
}
