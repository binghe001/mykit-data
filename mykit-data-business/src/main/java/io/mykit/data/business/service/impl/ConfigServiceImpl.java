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
package io.mykit.data.business.service.impl;

import io.mykit.data.business.checker.impl.config.ConfigChecker;
import io.mykit.data.business.service.ConfigService;
import io.mykit.data.business.vo.ConfigVo;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.manage.Manager;
import io.mykit.data.parser.model.Config;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.storage.constants.ConfigConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private Manager manager;

    @Autowired
    private ConfigChecker configChecker;

    @Override
    public String edit(Map<String, String> params) {
        synchronized (this) {
            Config config = manager.getConfig(ConfigConstants.CONFIG);
            if (null == config) {
                configChecker.checkAddConfigModel(params);
            }
            ConfigModel model = configChecker.checkEditConfigModel(params);
            manager.editConfig(model);
        }
        return "修改成功.";
    }

    @Override
    public ConfigVo getConfig() {
        List<Config> all = manager.getConfigAll();
        Config config = CollectionUtils.isEmpty(all) ? (Config) configChecker.checkAddConfigModel(new HashMap<>()) : all.get(0);
        return convertConfig2Vo(config);
    }


    @Override
    public List<ConfigVo> queryConfig() {
        List<ConfigVo> list = manager.getConfigAll().stream()
                .map(config -> convertConfig2Vo(config))
                .collect(Collectors.toList());
        return list;
    }

    private ConfigVo convertConfig2Vo(Config config) {
        ConfigVo configVo = new ConfigVo();
        BeanUtils.copyProperties(config, configVo);
        return configVo;
    }
}
