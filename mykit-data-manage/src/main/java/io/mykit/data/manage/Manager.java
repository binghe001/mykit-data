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
package io.mykit.data.manage;

import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.config.MetaInfo;
import io.mykit.data.connector.enums.ConnectorEnum;
import io.mykit.data.connector.enums.FilterEnum;
import io.mykit.data.connector.enums.OperationEnum;
import io.mykit.data.monitor.enums.QuartzFilterEnum;
import io.mykit.data.parser.enums.ConvertEnum;
import io.mykit.data.parser.model.*;
import io.mykit.data.plugins.config.Plugin;
import io.mykit.data.storage.query.Query;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public interface Manager extends Executor {

    boolean alive(ConnectorConfig config);

    List<String> getTable(ConnectorConfig config);

    MetaInfo getMetaInfo(String connectorId, String tableName);

    // Connector
    String addConnector(ConfigModel model);

    String editConnector(ConfigModel model);

    void removeConnector(String connectorId);

    Connector getConnector(String connectorId);

    List<Connector> getConnectorAll();

    // Mapping
    String addMapping(ConfigModel model);

    String editMapping(ConfigModel model);

    void removeMapping(String mappingId);

    Mapping getMapping(String mappingId);

    List<Mapping> getMappingAll();

    // TableGroup
    String addTableGroup(ConfigModel model);

    String editTableGroup(ConfigModel model);

    void removeTableGroup(String tableGroupId);

    TableGroup getTableGroup(String tableGroupId);

    List<TableGroup> getTableGroupAll(String mappingId);

    Map<String, String> getCommand(Mapping mapping, TableGroup tableGroup);

    long getCount(String connectorId, Map<String, String> command);

    // Meta
    String addMeta(ConfigModel model);

    String editMeta(ConfigModel model);

    Meta getMeta(String metaId);

    void removeMeta(String metaId);

    List<Meta> getMetaAll();

    // Config
    String addConfig(ConfigModel model);

    String editConfig(ConfigModel model);

    Config getConfig(String configId);

    void removeConfig(String configId);

    List<Config> getConfigAll();

    // Data
    List<Map> queryData(Query query, String collectionId);

    void clearData(String collectionId);

    // Log
    List<Map> queryLog(Query query);

    void clearLog();

    // ConnectorEnum
    List<ConnectorEnum> getConnectorEnumAll();

    // OperationEnum
    List<OperationEnum> getOperationEnumAll();

    // QuartzFilterEnum
    List<QuartzFilterEnum> getQuartzFilterEnumAll();

    // FilterEnum
    List<FilterEnum> getFilterEnumAll();

    // ConvertEnum
    List<ConvertEnum> getConvertEnumAll();

    // Plugin
    List<Plugin> getPluginAll();
}
