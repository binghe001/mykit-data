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
package io.mykit.data.connector.database;

import io.mykit.data.connector.Connector;
import io.mykit.data.connector.config.DatabaseConfig;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public interface Database extends Connector {

    /**
     * 获取JdbcTemplate句柄
     */
    JdbcTemplate getJdbcTemplate(DatabaseConfig config);

    /**
     * 关闭jdbc连接
     */
    void close(JdbcTemplate jdbcTemplate);

    /**
     * 获取分页SQL
     */
    String getPageSql(String tableName, String pk, String querySQL);

    /**
     * 获取分页参数
     */
    Object[] getPageArgs(int pageIndex, int pageSize);

}
