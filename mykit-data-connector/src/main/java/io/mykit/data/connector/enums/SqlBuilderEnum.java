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

import io.mykit.data.connector.constants.ConnectorConstants;
import io.mykit.data.connector.database.sqlbuilder.*;
import io.mykit.data.connector.exception.ConnectorException;
import org.apache.commons.lang.StringUtils;

/**
 * @author binghe
 * @version 1.0.0
 * @description 构建sql类型
 */
public enum SqlBuilderEnum {
    /**
     * 插入SQL生成器
     */
    INSERT(ConnectorConstants.OPERTION_INSERT, new SqlBuilderInsert()),
    /**
     * 修改SQL生成器
     */
    UPDATE(ConnectorConstants.OPERTION_UPDATE, new SqlBuilderUpdate()),
    /**
     * 删除SQL生成器
     */
    DELETE(ConnectorConstants.OPERTION_DELETE, new SqlBuilderDelete()),
    /**
     * 查询SQL生成器
     */
    QUERY(ConnectorConstants.OPERTION_QUERY, new SqlBuilderQuery());

    // SQL构造器名称
    private String name;

    // SQL构造器
    private SqlBuilder sqlBuilder;

    SqlBuilderEnum(String name, SqlBuilder sqlBuilder) {
        this.name = name;
        this.sqlBuilder = sqlBuilder;
    }

    public static SqlBuilder getSqlBuilder(String name) throws ConnectorException {
        for (SqlBuilderEnum e : SqlBuilderEnum.values()) {
            if (StringUtils.equals(name, e.getName())) {
                return e.getSqlBuilder();
            }
        }
        throw new ConnectorException(String.format("SqlBuilder name \"%s\" does not exist.", name));
    }

    public String getName() {
        return name;
    }

    public SqlBuilder getSqlBuilder() {
        return sqlBuilder;
    }
}
