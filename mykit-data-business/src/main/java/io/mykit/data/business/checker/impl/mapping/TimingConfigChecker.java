package io.mykit.data.business.checker.impl.mapping;


import io.mykit.data.business.checker.MappingConfigChecker;
import io.mykit.data.common.utils.StringUtils;
import io.mykit.data.monitor.config.ListenerConfig;
import io.mykit.data.monitor.enums.ListenerTypeEnum;
import io.mykit.data.parser.model.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 定时配置
 */
@Component
public class TimingConfigChecker implements MappingConfigChecker {

    @Override
    public void modify(Mapping mapping, Map<String, String> params) {
        String cron = params.get("incrementStrategyTimingCronExpression");
        String eventFieldName = params.get("incrementStrategyTimingEventFieldName");
        String insert = params.get("incrementStrategyTimingInsert");
        String update = params.get("incrementStrategyTimingUpdate");
        String delete = params.get("incrementStrategyTimingDelete");

        ListenerConfig config = mapping.getListener();
        Assert.notNull(config, "ListenerConfig can not be null.");

        if (StringUtils.isNotBlank(cron)) {
            config.setCronExpression(cron);
        }
        if (StringUtils.isNotBlank(eventFieldName)) {
            config.setEventFieldName(eventFieldName);
        }
        if (StringUtils.isNotBlank(insert)) {
            config.setInsert(insert);
        }
        if (StringUtils.isNotBlank(update)) {
            config.setUpdate(update);
        }
        if (StringUtils.isNotBlank(delete)) {
            config.setDelete(delete);
        }

        config.setListenerType(ListenerTypeEnum.TIMING.getType());
        mapping.setListener(config);
    }

}