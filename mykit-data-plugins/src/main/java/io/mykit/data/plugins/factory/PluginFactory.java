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
package io.mykit.data.plugins.factory;

import io.mykit.data.plugins.config.Plugin;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 插件工厂类
 */
@Component
public class PluginFactory {

    public List<Plugin> getPluginAll() {
        List<Plugin> list = new ArrayList<>();
        return list;
    }

    public void convert(Plugin plugin, List<Map<String, Object>> source, List<Map<String, Object>> target) {
        if (null != plugin) {
            // TODO 插件转换
        }
    }

    public void convert(Plugin plugin, String event, Map<String, Object> source, Map<String, Object> target) {
        if (null != plugin) {
            //TODO 插件转换
        }
    }
}
