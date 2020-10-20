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
package io.mykit.data.connector.database.sqlbuilder;

import io.mykit.data.connector.database.Database;
import io.mykit.data.connector.exception.ConnectorException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 生成update语句
 */
public class SqlBuilderUpdate implements SqlBuilder{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String buildSql(String tableName, String pk, List<String> filedNames, String queryFilter, String quotation, Database database) {
        if (StringUtils.isBlank(pk)) {
            logger.error("Table primary key can not be empty.");
            throw new ConnectorException("Table primary key can not be empty.");
        }
        StringBuilder sql = new StringBuilder();
        int size = filedNames.size();
        int end = size - 1;
        sql.append("UPDATE ").append(quotation).append(tableName).append(quotation).append(" SET ");
        for (int i = 0; i < size; i++) {
            // "USERNAME"=?
            sql.append(quotation).append(filedNames.get(i)).append(quotation).append("=?");
            //如果不是最后一个字段
            if (i < end) {
                sql.append(",");
            }
        }
        // UPDATE "USER" SET "USERNAME"=?,"AGE"=? WHERE "ID"=?
        sql.append(" WHERE ").append(quotation).append(pk).append(quotation).append("=?");
        return sql.toString();
    }
}
