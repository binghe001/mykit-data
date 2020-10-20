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
import io.mykit.data.manage.enums.HandlerEnum;

/**
 * @author binghe
 * @version 1.0.0
 * @description PreloadConfig
 */
public class PreloadConfig {

    private String filterType;

    private GroupStrategyEnum groupStrategyEnum;

    private HandlerEnum handlerEnum;

    public PreloadConfig(String filterType, HandlerEnum handlerEnum) {
        this.filterType = filterType;
        this.handlerEnum = handlerEnum;
    }

    public PreloadConfig(String filterType, GroupStrategyEnum groupStrategyEnum, HandlerEnum handlerEnum) {
        this.filterType = filterType;
        this.groupStrategyEnum = groupStrategyEnum;
        this.handlerEnum = handlerEnum;
    }

    public String getFilterType() {
        return filterType;
    }

    public GroupStrategyEnum getGroupStrategyEnum() {
        return groupStrategyEnum;
    }

    public HandlerEnum getHandlerEnum() {
        return handlerEnum;
    }
}
