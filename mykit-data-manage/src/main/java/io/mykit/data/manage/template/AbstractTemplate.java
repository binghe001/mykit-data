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
package io.mykit.data.manage.template;

import io.mykit.data.manage.config.OperationConfig;
import io.mykit.data.manage.config.PreloadConfig;
import io.mykit.data.manage.config.QueryConfig;
import io.mykit.data.manage.enums.GroupStrategyEnum;

/**
 * @author binghe
 * @version 1.0.0
 * @description 抽象模板
 */
public abstract class AbstractTemplate {

    protected GroupStrategyEnum getDefaultStrategy(PreloadConfig config) {
        return getDefaultStrategy(config.getGroupStrategyEnum());
    }

    protected GroupStrategyEnum getDefaultStrategy(OperationConfig config) {
        return getDefaultStrategy(config.getGroupStrategyEnum());
    }

    protected GroupStrategyEnum getDefaultStrategy(QueryConfig query) {
        return getDefaultStrategy(query.getGroupStrategyEnum());
    }

    private GroupStrategyEnum getDefaultStrategy(GroupStrategyEnum strategy) {
        return null != strategy ? strategy : GroupStrategyEnum.DEFAULT;
    }
}
