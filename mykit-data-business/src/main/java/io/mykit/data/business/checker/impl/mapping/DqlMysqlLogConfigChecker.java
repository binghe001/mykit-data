package io.mykit.data.business.checker.impl.mapping;


import io.mykit.data.business.checker.MappingLogConfigChecker;
import io.mykit.data.monitor.config.ListenerConfig;
import io.mykit.data.parser.model.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 检查增量DqlMysql配置
 */
@Component
public class DqlMysqlLogConfigChecker implements MappingLogConfigChecker {

    @Override
    public void modify(Mapping mapping, Map<String, String> params) {
        ListenerConfig config = mapping.getListener();
        Assert.notNull(config, "ListenerConfig can not be null.");

        String label = params.get("incrementStrategyLogTableLabel");
        Assert.hasText(label, "MysqlLogConfigChecker check params incrementStrategyLogTableLabel is empty");
        config.setTableLabel(label);
        mapping.setListener(config);
    }

}