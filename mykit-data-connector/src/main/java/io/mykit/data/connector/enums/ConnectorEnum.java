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
package io.mykit.data.connector.enums;

import io.mykit.data.connector.Connector;
import io.mykit.data.connector.exception.ConnectorException;
import io.mykit.data.connector.mysql.MysqlConnector;
import io.mykit.data.connector.oracle.OracleConnector;
import io.mykit.data.connector.sql.DQLMysqlConnector;
import io.mykit.data.connector.sql.DQLOracleConnector;
import io.mykit.data.connector.config.DatabaseConfig;
import org.apache.commons.lang.StringUtils;

/**
 * @author binghe
 * @version 1.0.0
 * @description 连接器类型
 */
public enum ConnectorEnum {

    /**
     * Mysql 连接器
     */
    MYSQL("Mysql", new MysqlConnector(), DatabaseConfig.class),
    /**
     * Oracle 连接器
     */
    ORACLE("Oracle", new OracleConnector(), DatabaseConfig.class),
    /**
     * DqlMysql 连接器
     */
    DQL_MYSQL("DqlMysql", new DQLMysqlConnector(), DatabaseConfig.class),
    /**
     * DqlOracle 连接器
     */
    DQL_ORACLE("DqlOracle", new DQLOracleConnector(), DatabaseConfig.class);


    // 连接器名称
    private String type;

    // 连接器
    private Connector connector;

    // 配置
    private Class<?> configClass;

    ConnectorEnum(String type, Connector connector, Class<?> configClass) {
        this.type = type;
        this.connector = connector;
        this.configClass = configClass;
    }

    /**
     * 获取连接器
     *
     * @param type
     * @return
     * @throws ConnectorException
     */
    public static Connector getConnector(String type) throws ConnectorException {
        for (ConnectorEnum e : ConnectorEnum.values()) {
            if (StringUtils.equals(type, e.getType())) {
                return e.getConnector();
            }
        }
        throw new ConnectorException(String.format("Connector type \"%s\" does not exist.", type));
    }

    /**
     * 获取连接配置
     *
     * @param type
     * @return
     * @throws ConnectorException
     */
    public static Class<?> getConfigClass(String type) throws ConnectorException {
        for (ConnectorEnum e : ConnectorEnum.values()) {
            if (StringUtils.equals(type, e.getType())) {
                return e.getConfigClass();
            }
        }
        throw new ConnectorException(String.format("Connector type \"%s\" does not exist.", type));
    }

    public static boolean isOracle(String connectorType) {
        return StringUtils.equals(ORACLE.getType(), connectorType) || StringUtils.equals(DQL_ORACLE.getType(), connectorType);
    }

    public String getType() {
        return type;
    }

    public Connector getConnector() {
        return connector;
    }

    public Class<?> getConfigClass() {
        return configClass;
    }
}
