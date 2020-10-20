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
package io.mykit.data.manage.config;

import io.mykit.data.manage.enums.GroupStrategyEnum;
import io.mykit.data.manage.template.Handler;
import io.mykit.data.parser.model.ConfigModel;

/**
 * @author binghe
 * @version 1.0.0
 * @description 条件配置
 */
public class OperationConfig {

    private String id;

    private ConfigModel model;

    private GroupStrategyEnum groupStrategyEnum;

    private Handler handler;

    public OperationConfig(String id) {
        this.id = id;
    }

    public OperationConfig(String id, GroupStrategyEnum groupStrategyEnum) {
        this.id = id;
        this.groupStrategyEnum = groupStrategyEnum;
    }

    public OperationConfig(ConfigModel model, Handler handler) {
        this.model = model;
        this.handler = handler;
    }

    public OperationConfig(ConfigModel model, GroupStrategyEnum groupStrategyEnum, Handler handler) {
        this.model = model;
        this.groupStrategyEnum = groupStrategyEnum;
        this.handler = handler;
    }

    public String getId() {
        return id;
    }

    public ConfigModel getModel() {
        return model;
    }

    public GroupStrategyEnum getGroupStrategyEnum() {
        return groupStrategyEnum;
    }

    public Handler getHandler() {
        return handler;
    }
}
