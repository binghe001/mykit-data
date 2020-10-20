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
package io.mykit.data.manage.enums;

import io.mykit.data.manage.strategy.DefaultGroupStrategy;
import io.mykit.data.manage.strategy.TableGroupStrategy;
import io.mykit.data.manage.template.GroupStrategy;

/**
 * @author binghe
 * @version 1.0.0
 * @description 分组策略类型
 */
public enum GroupStrategyEnum {

    /**
     * 默认
     */
    DEFAULT(new DefaultGroupStrategy()),
    /**
     * 表映射关系
     */
    TABLE(new TableGroupStrategy());

    private GroupStrategy groupStrategy;

    GroupStrategyEnum(GroupStrategy groupStrategy) {
        this.groupStrategy = groupStrategy;
    }

    public GroupStrategy getGroupStrategy() {
        return groupStrategy;
    }
}
