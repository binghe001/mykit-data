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
package io.mykit.data.connector;

import io.mykit.data.common.model.Result;
import io.mykit.data.connector.config.CommandConfig;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.config.Field;
import io.mykit.data.connector.config.MetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 连接器基础功能
 */
public interface Connector {
    /**
     * 检查连接器是否连接正常
     *
     * @param config 连接器配置
     * @return 连接是否活跃，true:是; false：否
     */
    boolean isAlive(ConnectorConfig config);

    /**
     * 获取所有表名
     * @param config 连接的配置信息
     * @return 数据表集合
     */
    List<String> getTable(ConnectorConfig config);

    /**
     * 获取表元信息
     * @param config 连接配置
     * @param tableName 数据表名称
     * @return 元数据信息
     */
    MetaInfo getMetaInfo(ConnectorConfig config, String tableName);

    /**
     * 获取数据源同步参数
     * @param commandConfig 查询同步参数模板的配置
     */
    Map<String, String> getSourceCommand(CommandConfig commandConfig);

    /**
     * 获取目标源同步参数
     */
    Map<String, String> getTargetCommand(CommandConfig commandConfig);

    /**
     * 获取总数
     */
    long getCount(ConnectorConfig config, Map<String, String> command);

    /**
     * 分页获取数据源数据
     * @param config    连接器配置
     * @param command   执行命令
     * @param args      命令参数
     * @param pageIndex 页数
     * @param pageSize  页大小
     */
    Result reader(ConnectorConfig config, Map<String, String> command, List<Object> args, int pageIndex, int pageSize);

    /**
     * 批量写入目标源数据
     *
     * @param config  连接器配置
     * @param command 执行命令
     * @param fields  字段信息
     * @param data    数据
     */
    Result writer(ConnectorConfig config, Map<String, String> command, List<Field> fields, List<Map<String, Object>> data);

    /**
     * 写入目标源数据
     *
     * @param config  连接器配置
     * @param fields  字段信息
     * @param command 执行命令
     * @param event   事件
     * @param data    数据
     */
    Result writer(ConnectorConfig config, List<Field> fields, Map<String, String> command, String event, Map<String, Object> data);
}
