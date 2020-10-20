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
package io.mykit.data.business.checker;

import io.mykit.data.business.exception.BizException;
import io.mykit.data.business.service.PluginService;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.common.utils.JsonUtils;
import io.mykit.data.connector.config.Filter;
import io.mykit.data.parser.model.AbstractConfigModel;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.model.Convert;
import io.mykit.data.plugins.config.Plugin;
import io.mykit.data.storage.SnowflakeIdWorker;
import io.mykit.data.storage.constants.ConfigConstants;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public abstract class AbstractChecker implements Checker {

    @Autowired
    private PluginService pluginService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 修改基本配置
     * <p>id,type,name,createTime,updateTime</p>
     *
     * @param model
     * @param params
     */
    protected void modifyConfigModel(ConfigModel model, Map<String, String> params) {
        Assert.notNull(model, "ConfigModel can not be null.");
        Assert.hasText(model.getType(), "ConfigModel type can not be empty.");
        Assert.hasText(model.getName(), "ConfigModel name can not be empty.");
        // 名称
        String name = params.get(ConfigConstants.CONFIG_MODEL_NAME);
        if (StringUtils.isNotBlank(name)) {
            model.setName(name);
        }
        model.setId(StringUtils.isEmpty(model.getId()) ? String.valueOf(snowflakeIdWorker.nextId()) : model.getId());
        long now = Instant.now().toEpochMilli();
        model.setCreateTime(null == model.getCreateTime() ? now : model.getCreateTime());
        model.setUpdateTime(now);
    }

    /**
     * 修改高级配置：过滤条件/转换配置/插件配置
     *
     * @param model
     * @param params
     */
    protected void modifySuperConfigModel(AbstractConfigModel model, Map<String, String> params) {
        // 过滤条件
        String filterJson = params.get("filter");
        if (StringUtils.isNotBlank(filterJson)) {
            List<Filter> list = jsonToList(filterJson, Filter.class);
            model.setFilter(list);
        }

        // 转换配置
        String convertJson = params.get("convert");
        if (StringUtils.isNotBlank(convertJson)) {
            List<Convert> convert = jsonToList(convertJson, Convert.class);
            model.setConvert(convert);
        }

        // 插件配置
        String pluginClassName = params.get("pluginClassName");
        Plugin plugin = null;
        if (StringUtils.isNotBlank(pluginClassName)) {
            List<Plugin> plugins = pluginService.getPluginAll();
            if (!CollectionUtils.isEmpty(plugins)) {
                for (Plugin p : plugins) {
                    if (StringUtils.equals(p.getClassName(), pluginClassName)) {
                        plugin = p;
                        break;
                    }
                }
            }
        }
        model.setPlugin(plugin);
    }

    private <T> List<T> jsonToList(String json, Class<T> valueType) {
        try {
            JSONArray array = new JSONArray(json);
            if (null != array) {
                List<T> list = new ArrayList<>();
                int length = array.length();
                for (int i = 0; i < length; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    T t = JsonUtils.jsonToObj(obj.toString(), valueType);
                    list.add(t);
                }
                return list;
            }
        } catch (JSONException e) {
            throw new BizException(String.format("解析高级配置参数异常:%s", json));
        }
        return Collections.EMPTY_LIST;
    }
}
