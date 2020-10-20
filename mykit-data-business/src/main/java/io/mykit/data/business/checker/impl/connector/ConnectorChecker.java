package io.mykit.data.business.checker.impl.connector;


import io.mykit.data.business.checker.AbstractChecker;
import io.mykit.data.business.checker.ConnectorConfigChecker;
import io.mykit.data.business.exception.BizException;
import io.mykit.data.common.utils.StringUtils;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.connector.enums.ConnectorEnum;
import io.mykit.data.manage.Manager;
import io.mykit.data.parser.logger.LogService;
import io.mykit.data.parser.logger.LogType;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.model.Connector;
import io.mykit.data.storage.constants.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;


@Component
public class ConnectorChecker extends AbstractChecker implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Manager manager;

    @Autowired
    private LogService logService;

    private Map<String, ConnectorConfigChecker> map;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(ConnectorConfigChecker.class);
    }

    @Override
    public ConfigModel checkAddConfigModel(Map<String, String> params) {
        logger.info("params:{}", params);
        String name = params.get(ConfigConstants.CONFIG_MODEL_NAME);
        String connectorType = params.get("connectorType");
        Assert.hasText(name, "connector name is empty.");
        Assert.hasText(connectorType, "connector connectorType is empty.");

        Connector connector = new Connector();
        connector.setName(name);
        connector.setType(ConfigConstants.CONNECTOR);
        setConfig(connector, connectorType);

        // 配置连接器配置
        String type = StringUtils.toLowerCaseFirstOne(connectorType).concat("ConfigChecker");
        ConnectorConfigChecker checker = map.get(type);
        Assert.notNull(checker, "Checker can not be null.");
        checker.modify(connector, params);

        // 获取表
        setTable(connector);

        // 修改基本配置
        this.modifyConfigModel(connector, params);

        return connector;
    }

    @Override
    public ConfigModel checkEditConfigModel(Map<String, String> params) {
        logger.info("params:{}", params);
        Assert.notEmpty(params, "ConnectorChecker check params is null.");
        String id = params.get(ConfigConstants.CONFIG_MODEL_ID);
        Connector connector = manager.getConnector(id);
        Assert.notNull(connector, "Can not find connector.");

        // 修改基本配置
        this.modifyConfigModel(connector, params);

        // 配置连接器配置
        ConnectorConfig config = connector.getConfig();
        String type = StringUtils.toLowerCaseFirstOne(config.getConnectorType()).concat("ConfigChecker");
        ConnectorConfigChecker checker = map.get(type);
        Assert.notNull(checker, "Checker can not be null.");
        checker.modify(connector, params);

        // 获取表
        setTable(connector);

        return connector;
    }

    private void setConfig(Connector connector, String connectorType) {
        Class<ConnectorConfig> configClass = (Class<ConnectorConfig>) ConnectorEnum.getConfigClass(connectorType);
        Assert.notNull(configClass, String.format("不支持该连接器类型:%s", connectorType));
        try {
            ConnectorConfig config = configClass.newInstance();
            config.setConnectorType(connectorType);
            connector.setConfig(config);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BizException("获取连接器配置异常.");
        }
    }

    private void setTable(Connector connector) {
        // 获取表信息
        boolean alive = manager.alive(connector.getConfig());
        if (!alive) {
            logService.log(LogType.ConnectorLog.FAILED);
        }
        Assert.isTrue(alive, "无法连接.");
        List<String> table = manager.getTable(connector.getConfig());
        connector.setTable(table);
    }

}