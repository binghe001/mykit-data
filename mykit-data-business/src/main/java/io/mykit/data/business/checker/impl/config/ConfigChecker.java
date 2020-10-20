package io.mykit.data.business.checker.impl.config;


import io.mykit.data.business.checker.AbstractChecker;
import io.mykit.data.manage.Manager;
import io.mykit.data.parser.logger.LogService;
import io.mykit.data.parser.logger.LogType;
import io.mykit.data.parser.model.Config;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.storage.constants.ConfigConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Component
public class ConfigChecker extends AbstractChecker {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Manager manager;

    @Autowired
    private LogService logService;

    @Override
    public ConfigModel checkAddConfigModel(Map<String, String> params) {
        Config config = new Config();
        config.setName("系统配置");
        config.setType(ConfigConstants.CONFIG);

        // 修改基本配置
        this.modifyConfigModel(config, params);

        manager.addConfig(config);
        return config;
    }

    @Override
    public ConfigModel checkEditConfigModel(Map<String, String> params) {
        logger.info("params:{}", params);
        Assert.notEmpty(params, "Config check params is null.");

        String id = params.get(ConfigConstants.CONFIG_MODEL_ID);
        Assert.hasText(id, "Config id is empty.");
        Config config = manager.getConfig(id);
        Assert.notNull(config, "配置文件为空.");

        logService.log(LogType.SystemLog.INFO, "修改系统配置");

        // 刷新监控间隔（秒）
        String refreshInterval = params.get("refreshInterval");
        if (StringUtils.isNotBlank(refreshInterval)) {
            int time = NumberUtils.toInt(refreshInterval, 10);
            config.setRefreshInterval(time);
        }

        // 修改基本配置
        this.modifyConfigModel(config, params);
        return config;
    }

}