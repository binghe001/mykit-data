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
package io.mykit.data.connector.factory;

import io.mykit.data.common.model.Result;
import io.mykit.data.connector.Connector;
import io.mykit.data.connector.config.CommandConfig;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.config.Field;
import io.mykit.data.connector.config.MetaInfo;
import io.mykit.data.connector.enums.ConnectorEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 连接器工厂类
 */
@Component
public class ConnectorFactory {
    /**
     * 检查连接配置是否可用
     */
    public boolean isAlive(ConnectorConfig config) {
        Assert.notNull(config, "ConnectorConfig can not be null.");
        String type = config.getConnectorType();
        return getConnector(type).isAlive(config);
    }

    /**
     * 获取配置表
     */
    public List<String> getTable(ConnectorConfig config) {
        Assert.notNull(config, "ConnectorConfig can not be null.");
        String type = config.getConnectorType();
        return getConnector(type).getTable(config);
    }

    /**
     * 获取配置表元信息
     */
    public MetaInfo getMetaInfo(ConnectorConfig config, String tableName) {
        Assert.notNull(config, "ConnectorConfig can not be null.");
        Assert.hasText(tableName, "tableName can not be empty.");
        String type = config.getConnectorType();
        return getConnector(type).getMetaInfo(config, tableName);
    }

    /**
     * 获取连接器同步参数
     */
    public Map<String, String> getCommand(CommandConfig sourceCommandConfig, CommandConfig targetCommandConfig) {
        String sType = sourceCommandConfig.getType();
        String tType = targetCommandConfig.getType();
        Map<String, String> map = new HashMap<>();
        Map<String, String> sCmd = getConnector(sType).getSourceCommand(sourceCommandConfig);
        Map<String, String> tCmd = getConnector(tType).getTargetCommand(targetCommandConfig);
        map.putAll(sCmd);
        map.putAll(tCmd);
        return map;
    }

    /**
     * 获取总数
     */
    public long getCount(ConnectorConfig config, Map<String, String> command) {
        Connector connector = getConnector(config.getConnectorType());
        return connector.getCount(config, command);
    }

    public Result reader(ConnectorConfig config, Map<String, String> command, List<Object> args, int pageIndex, int pageSize) {
        Connector connector = getConnector(config.getConnectorType());
        Result result = connector.reader(config, command, args, pageIndex, pageSize);
        Assert.notNull(result, "Connector reader result can not null");
        return result;
    }

    public Result writer(ConnectorConfig config, Map<String, String> command, List<Field> fields, List<Map<String, Object>> data) {
        Connector connector = getConnector(config.getConnectorType());
        Result result = connector.writer(config, command, fields, data);
        Assert.notNull(result, "Connector writer result can not null");
        return result;
    }

    public Result writer(ConnectorConfig config, List<Field> fields, Map<String, String> command, String event, Map<String, Object> data) {
        Connector connector = getConnector(config.getConnectorType());
        Result result = connector.writer(config, fields, command, event, data);
        Assert.notNull(result, "Connector writer result can not null");
        return result;
    }

    /**
     * 获取连接器
     */
    private Connector getConnector(String connectorType) {
        // 获取连接器类型
        Assert.hasText(connectorType, "ConnectorType can not be empty.");
        return ConnectorEnum.getConnector(connectorType);
    }
}
