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
package io.mykit.data.connector.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author binghe
 * @version 1.0.0
 * @description JDBC工具类
 */
public class JDBCUtils {

    private final static Logger logger = LoggerFactory.getLogger(JDBCUtils.class);

    public static Connection getConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        // com.mysql.jdbc.JDBC4Connection
        // 不需要显式调用 Class.forName(driver), DriverManager.getConnection会自动加载合适的驱动
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e.getClass() + " >> " + e.getLocalizedMessage());
            }
        }
    }

    public static void close(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error(e.getClass() + " >> " + e.getLocalizedMessage());
            }
        }
    }

    public static void close(Statement statement, Connection conn) {
        close(statement);
        close(conn);
    }
}
