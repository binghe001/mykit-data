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

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据表字段属性
 */
public class Field implements Serializable {
    private static final long serialVersionUID = 8017677742107438340L;

    /**
     * 字段名，ID
     */
    private String name;

    /**
     * 类型名，INT
     */
    private String typeName;

    /**
     * 类型编码，4
     */
    private int type;

    /**
     * 主键
     */
    private boolean pk;

    public Field() {
    }

    public Field(String name, String typeName, int type) {
        this.name = name;
        this.typeName = typeName;
        this.type = type;
    }

    public Field(String name, String typeName, int type, boolean pk) {
        this.name = name;
        this.typeName = typeName;
        this.type = type;
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getType() {
        return type;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Field{").append("name='").append(name).append('\'')
                .append(", typeName='").append(typeName).append('\'')
                .append(", type=").append(type)
                .append(", pk=").append(pk).append('}').toString();
    }
}
