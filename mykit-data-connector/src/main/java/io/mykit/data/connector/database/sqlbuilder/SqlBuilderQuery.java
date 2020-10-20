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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 生成查询语句
 */
public class SqlBuilderQuery implements SqlBuilder {
    private final Logger logger = LoggerFactory.getLogger(SqlBuilderQuery.class);

    @Override
    public String buildSql(String tableName, String pk, List<String> filedNames, String queryFilter, String quotation, Database database) {
        StringBuilder sql = new StringBuilder();
        int size = filedNames.size();
        int end = size - 1;
        for (int i = 0; i < size; i++) {
            // "USERNAME"
            quotation = quotation.replace("\"", "");
            sql.append(quotation).append(filedNames.get(i)).append(quotation);
            //如果不是最后一个字段
            if (i < end) {
                sql.append(", ");
            }
        }
        // SELECT "ID","NAME" FROM "USER"
        sql.insert(0, "SELECT ").append(" FROM ").append(quotation).append(tableName).append(quotation);
        // 解析查询条件
        if (StringUtils.isNotBlank(queryFilter)) {
            sql.append(queryFilter);
        }
        logger.debug("拼接的查询语句===>>> " + sql.toString());
        // 分页语句
        String pageSql = database.getPageSql(tableName, pk, sql.toString());
        logger.debug("拼接的分页查询语句===>>> " + pageSql);
        return pageSql;
    }
}
