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
 * @description 元数据信息（包括连接器的配置、元信息、总条数）
 */
public class MetaInfo implements Serializable {
    private static final long serialVersionUID = -2179099599623687370L;

    /**
     * 属性字段
     * 格式：[{"name":"ID","typeName":"INT","type":"4"},{"name":"NAME","typeName":"VARCHAR","type":"12"}]
     */
    private List<Field> column;

    /**
     * 总条数
     */
    private long count;

    public MetaInfo() {
    }

    public MetaInfo(List<Field> column, long count) {
        super();
        this.column = column;
        this.count = count;
    }

    public List<Field> getColumn() {
        return column;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("MetaInfo{").append("column=").append(column).append(", count=").append(count).append('}').toString();
    }
}
