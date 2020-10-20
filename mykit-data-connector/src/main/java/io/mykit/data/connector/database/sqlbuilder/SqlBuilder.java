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
 * @description sql生成器
 */
public interface SqlBuilder {
    /**
     * 生成SQL
     * @param tableName 数据表
     * @param pk 主键
     * @param filedNames 字段集合
     * @param queryFilter 查询过滤条件
     * @param quotation 条件引用
     * @param database 数据库连接信息
     * @return 生成的SQL语句
     */
    String buildSql(String tableName, String pk, List<String> filedNames, String queryFilter, String quotation, Database database);
}
