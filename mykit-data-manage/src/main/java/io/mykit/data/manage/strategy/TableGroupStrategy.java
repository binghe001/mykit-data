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
package io.mykit.data.manage.strategy;

import io.mykit.data.manage.exception.ManagerException;
import io.mykit.data.manage.template.GroupStrategy;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.model.TableGroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据表分组策略
 */
public class TableGroupStrategy implements GroupStrategy {

    @Override
    public String getGroupId(ConfigModel model) {
        if (model instanceof TableGroup) {
            TableGroup t = (TableGroup) model;
            String type = t.getType();
            String mappingId = t.getMappingId();
            // 格式：${type} + "_" + ${mappingId}
            return new StringBuilder(type).append("_").append(mappingId).toString();
        }
        throw new ManagerException(String.format("Not support config model \"%s\".", model));
    }

}
