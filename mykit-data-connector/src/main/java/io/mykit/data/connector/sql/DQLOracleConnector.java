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
package io.mykit.data.connector.sql;

import io.mykit.data.connector.config.CommandConfig;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.config.DatabaseConfig;
import io.mykit.data.connector.config.MetaInfo;
import io.mykit.data.connector.constants.DatabaseConstants;
import io.mykit.data.connector.database.AbstractDatabaseConnector;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description DQLOracleConnector
 */
public class DQLOracleConnector extends AbstractDatabaseConnector {

    @Override
    protected String getQueryTablesSql(DatabaseConfig config) {
        return String.format("SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='%s'", config.getUsername()).toUpperCase();
    }

    @Override
    public String getPageSql(String tableName, String pk, String querySQL) {
        // Oracle 分页查询
        return DatabaseConstants.ORACLE_PAGE_SQL_START + querySQL + DatabaseConstants.ORACLE_PAGE_SQL_END;
    }

    @Override
    public Object[] getPageArgs(int pageIndex, int pageSize) {
        return new Object[]{pageIndex * pageSize, (pageIndex - 1) * pageSize};
    }

    @Override
    public List<String> getTable(ConnectorConfig config) {
        return super.getDqlTable(config);
    }

    @Override
    public MetaInfo getMetaInfo(ConnectorConfig config, String tableName) {
        return super.getDqlMetaInfo(config);
    }

    @Override
    public Map<String, String> getSourceCommand(CommandConfig commandConfig) {
        return super.getDqlSourceCommand(commandConfig, "");
    }

    @Override
    protected String buildSqlWithQuotation() {
        return "\"";
    }
}
