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

import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 生成sql语句
 */
public class SqlBuilderInsert implements SqlBuilder {
    @Override
    public String buildSql(String tableName, String pk, List<String> filedNames, String queryFilter, String quotation, Database database) {
        StringBuilder sql = new StringBuilder();
        StringBuilder fs = new StringBuilder();
        StringBuilder vs = new StringBuilder();
        int size = filedNames.size();
        int end = size - 1;
        for (int i = 0; i < size; i++) {
            // "USERNAME"
            fs.append(quotation).append(filedNames.get(i)).append(quotation);
            vs.append("?");
            //如果不是最后一个字段
            if (i < end) {
                fs.append(", ");
                vs.append(", ");
            }
        }
        // INSERT INTO "USER"("USERNAME","AGE") VALUES (?,?)
        sql.insert(0, "INSERT INTO ").append(quotation).append(tableName).append(quotation).append("(").append(fs).append(") VALUES (").append(vs).append(")");
        return sql.toString();
    }
}
