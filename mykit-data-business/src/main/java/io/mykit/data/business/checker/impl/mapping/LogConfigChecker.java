package io.mykit.data.business.checker.impl.mapping;


import io.mykit.data.business.checker.MappingConfigChecker;
import io.mykit.data.business.checker.MappingLogConfigChecker;
import io.mykit.data.common.utils.StringUtils;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.manage.Manager;
import io.mykit.data.monitor.config.ListenerConfig;
import io.mykit.data.monitor.enums.ListenerTypeEnum;
import io.mykit.data.parser.model.Connector;
import io.mykit.data.parser.model.Mapping;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 日志配置
 *
 */
@Component
public class LogConfigChecker implements MappingConfigChecker, ApplicationContextAware {
    @Autowired
    private Manager manager;

    private Map<String, MappingLogConfigChecker> map;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(MappingLogConfigChecker.class);
    }

    @Override
    public void modify(Mapping mapping, Map<String, String> params) {
        String connectorId = mapping.getSourceConnectorId();
        Connector connector = manager.getConnector(connectorId);
        ConnectorConfig config = connector.getConfig();
        String type = StringUtils.toLowerCaseFirstOne(config.getConnectorType()).concat("LogConfigChecker");
        MappingLogConfigChecker checker = map.get(type);
        if (null != checker) {
            checker.modify(mapping, params);
        }
        ListenerConfig listener = mapping.getListener();
        Assert.notNull(listener, "ListenerConfig can not be null.");

        listener.setListenerType(ListenerTypeEnum.LOG.getType());
    }

}