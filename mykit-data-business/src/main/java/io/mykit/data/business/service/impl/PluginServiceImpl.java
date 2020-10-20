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

import io.mykit.data.business.service.PluginService;
import io.mykit.data.manage.Manager;
import io.mykit.data.plugins.config.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 插件
 */
@Service
public class PluginServiceImpl implements PluginService {

    @Autowired
    private Manager manager;

    @Override
    public List<Plugin> getPluginAll() {
        return manager.getPluginAll();
    }
}
