package io.mykit.data.parser;


import io.mykit.data.common.model.Task;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.config.MetaInfo;
import io.mykit.data.connector.enums.ConnectorEnum;
import io.mykit.data.connector.enums.FilterEnum;
import io.mykit.data.connector.enums.OperationEnum;
import io.mykit.data.monitor.enums.QuartzFilterEnum;
import io.mykit.data.parser.enums.ConvertEnum;
import io.mykit.data.parser.model.Connector;
import io.mykit.data.parser.model.DataEvent;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.TableGroup;

import java.util.List;
import java.util.Map;

public interface Parser {

    /**
     * 解析连接器配置是否可用
     *
     * @param config
     * @return
     */
    boolean alive(ConnectorConfig config);

    /**
     * 获取连接器表
     *
     * @param config
     * @return
     */
    List<String> getTable(ConnectorConfig config);

    /**
     * 获取表元信息
     *
     * @param connectorId
     * @param tableName
     * @return
     */
    MetaInfo getMetaInfo(String connectorId, String tableName);

    /**
     * 获取映射关系执行命令
     *
     * @param mapping
     * @param tableGroup
     * @return
     */
    Map<String, String> getCommand(Mapping mapping, TableGroup tableGroup);

    /**
     * 获取总数
     *
     * @param connectorId
     * @param command
     * @return
     */
    long getCount(String connectorId, Map<String, String> command);

    /**
     * 解析连接器配置为Connector
     *
     * @param json
     * @return
     */
    Connector parseConnector(String json);

    /**
     * 解析配置
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T parseObject(String json, Class<T> clazz);

    /**
     * 获取所有连接器类型
     *
     * @return
     */
    List<ConnectorEnum> getConnectorEnumAll();

    /**
     * 获取所有条件类型
     *
     * @return
     */
    List<OperationEnum> getOperationEnumAll();

    /**
     * 获取过滤条件系统参数
     *
     * @return
     */
    List<QuartzFilterEnum> getQuartzFilterEnumAll();

    /**
     * 获取所有运算符类型
     *
     * @return
     */
    List<FilterEnum> getFilterEnumAll();

    /**
     * 获取所有转换类型
     *
     * @return
     */
    List<ConvertEnum> getConvertEnumAll();

    /**
     * 全量同步
     *
     * @param task
     * @param mapping
     * @param tableGroup
     */
    void execute(Task task, Mapping mapping, TableGroup tableGroup);

    /**
     * 增量同步
     *
     * @param mapping
     * @param tableGroup
     * @param dataEvent
     */
    void execute(Mapping mapping, TableGroup tableGroup, DataEvent dataEvent);
}