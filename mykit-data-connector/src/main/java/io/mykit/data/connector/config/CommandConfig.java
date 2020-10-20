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
package io.mykit.data.connector.config;

import java.io.Serializable;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 查询同步参数模板的配置
 */
public class CommandConfig implements Serializable {
    private String type;

    private Table table;

    private List<Filter> filter;

    public CommandConfig(String type, Table table) {
        this.type = type;
        this.table = table;
    }

    public CommandConfig(String type, Table table, List<Filter> filter) {
        this.type = type;
        this.table = table;
        this.filter = filter;
    }

    public String getType() {
        return type;
    }

    public Table getTable() {
        return table;
    }

    public List<Filter> getFilter() {
        return filter;
    }
}


