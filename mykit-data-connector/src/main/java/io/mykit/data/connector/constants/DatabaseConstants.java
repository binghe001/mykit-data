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
 * @description 数据库常量类
 */
public class DatabaseConstants {

    //*********************************** Mysql **************************************//
    /**
     * Mysql分页语句
     */
    public static final String MYSQL_PAGE_SQL = " LIMIT ?,?";

    /**
     * Mysql驱动
     */
    public static final String MYSQL_DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";

    //*********************************** Oracle **************************************//
    /**
     * Oracle分页语句开始
     */
    public static final String ORACLE_PAGE_SQL_START = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (";

    /**
     * Oracle分页语句结束
     */
    public static final String ORACLE_PAGE_SQL_END = ")A WHERE ROWNUM <= ?) WHERE RN > ?";

    /**
     * Oracle驱动
     */
    public static final String ORACLE_DRIVER_CLASSNAME = "oracle.jdbc.OracleDriver";
}
