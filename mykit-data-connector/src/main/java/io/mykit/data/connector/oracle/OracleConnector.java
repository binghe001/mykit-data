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
package io.mykit.data.connector.oracle;

import io.mykit.data.connector.config.DatabaseConfig;
import io.mykit.data.connector.constants.DatabaseConstants;
import io.mykit.data.connector.database.AbstractDatabaseConnector;

/**
 * @author binghe
 * @version 1.0.0
 * @description Oracle连接器
 */
public class OracleConnector extends AbstractDatabaseConnector {

    @Override
    protected String getQueryTablesSql(DatabaseConfig config) {
        // "SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='AE86'"
        return String.format("SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER='%s'", config.getUsername()).toUpperCase();
    }

    @Override
    public String getPageSql(String tableName, String pk, String querySQL) {
        // Oracle 分页查询
        return new StringBuilder().append(DatabaseConstants.ORACLE_PAGE_SQL_START).append(querySQL).append(DatabaseConstants.ORACLE_PAGE_SQL_END).toString();
        //return DatabaseConstant.ORACLE_PAGE_SQL_START + querySQL + DatabaseConstant.ORACLE_PAGE_SQL_END;
    }

    @Override
    public Object[] getPageArgs(int pageIndex, int pageSize) {
        return new Object[]{pageIndex * pageSize, (pageIndex - 1) * pageSize};
    }

    @Override
    protected String buildSqlWithQuotation() {
        return "\"";
    }
}
