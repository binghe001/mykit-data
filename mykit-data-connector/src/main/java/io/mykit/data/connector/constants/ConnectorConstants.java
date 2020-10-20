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
package io.mykit.data.connector.constants;

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据连接常量类
 */
public class ConnectorConstants {

    /**
     * 新增
     */
    public static final String OPERTION_INSERT = "INSERT";

    /**
     * 更新
     */
    public static final String OPERTION_UPDATE = "UPDATE";

    /**
     * 删除
     */
    public static final String OPERTION_DELETE = "DELETE";

    /**
     * 查询
     */
    public static final String OPERTION_QUERY = "QUERY";

    /**
     * 查询总数
     */
    public static final String OPERTION_QUERY_COUNT = "QUERY_COUNT";

    /**
     * 查询最近记录点
     * <p>例如：SELECT MAX(MY_TEST.LAST_TIME) FROM MY_TEST</p>
     */
    public static final String OPERTION_QUERY_MAX = "QUERY_MAX";

    /**
     * 查询表达式and
     */
    public static final String OPERTION_QUERY_AND = "and";

    /**
     * 查询表达式or
     */
    public static final String OPERTION_QUERY_OR = "or";
}
