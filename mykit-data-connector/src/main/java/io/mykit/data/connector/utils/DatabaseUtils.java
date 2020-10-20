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

import com.sun.rowset.CachedRowSetImpl;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.connector.config.DatabaseConfig;
import io.mykit.data.connector.config.Field;
import io.mykit.data.connector.config.MetaInfo;
import io.mykit.data.connector.exception.ConnectorException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据库工具类
 */
public class DatabaseUtils {

    public static JdbcTemplate getJdbcTemplate(DatabaseConfig config) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(config.getDriverClassName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        // 是否自动回收超时连接
        dataSource.setRemoveAbandoned(true);
        // 超时时间(以秒数为单位)
        dataSource.setRemoveAbandonedTimeout(60);
        return new JdbcTemplate(dataSource);
    }

    public static void close(JdbcTemplate jdbcTemplate) throws SQLException {
        if (null != jdbcTemplate) {
            DataSource dataSource = jdbcTemplate.getDataSource();
            BasicDataSource ds = (BasicDataSource) dataSource;
            ds.close();
        }
    }

    public static void close(Connection connection) throws SQLException {
        if (null != connection) {
            connection.close();
        }
    }

    public static void close(ResultSet rs) throws SQLException {
        if (null != rs) {
            rs.close();
        }
    }

    /**
     * 获取数据库表元数据信息
     *
     * @param jdbcTemplate
     * @param metaSql      查询元数据
     * @param tableName    表名
     * @return
     */
    public static MetaInfo getMetaInfo(JdbcTemplate jdbcTemplate, String metaSql, String tableName) throws SQLException {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(metaSql);
        ResultSetWrappingSqlRowSet rowSet = (ResultSetWrappingSqlRowSet) sqlRowSet;
        CachedRowSetImpl resultSet = (CachedRowSetImpl) rowSet.getResultSet();
        SqlRowSetMetaData metaData = rowSet.getMetaData();

        // 查询表字段信息
        int columnCount = metaData.getColumnCount();
        if (1 > columnCount) {
            throw new ConnectorException("查询表字段不能为空.");
        }
        Connection connection = null;
        List<Field> fields = new ArrayList<>(columnCount);
        // <表名,[主键, ...]>
        Map<String, List<String>> tables = new HashMap<>();
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            DatabaseMetaData md = connection.getMetaData();
            String name = null;
            String label = null;
            String typeName = null;
            String table = null;
            int columnType;
            boolean pk;
            for (int i = 1; i <= columnCount; i++) {
                table = StringUtils.isNotBlank(tableName) ? tableName : metaData.getTableName(i);
                if (null == tables.get(table)) {
                    tables.putIfAbsent(table, findTablePrimaryKeys(md, table));
                }
                name = metaData.getColumnName(i);
                label = metaData.getColumnLabel(i);
                typeName = metaData.getColumnTypeName(i);
                columnType = metaData.getColumnType(i);
                pk = isPk(tables, table, name);
                fields.add(new Field(label, typeName, columnType, pk));
            }
        } finally {
            tables.clear();
            close(connection);
        }
        return new MetaInfo(fields, resultSet.size());
    }

    private static boolean isPk(Map<String, List<String>> tables, String tableName, String name) {
        List<String> pk = tables.get(tableName);
        return !CollectionUtils.isEmpty(pk) && pk.contains(name);
    }

    private static List<String> findTablePrimaryKeys(DatabaseMetaData md, String tableName) throws SQLException {
        //根据表名获得主键结果集
        ResultSet rs = null;
        List<String> primaryKeys = new ArrayList<>();
        try {
            rs = md.getPrimaryKeys(null, null, tableName);
            while (rs.next()) {
                primaryKeys.add(rs.getString("COLUMN_NAME"));
            }
        } finally {
            close(rs);
        }
        return primaryKeys;
    }
}
