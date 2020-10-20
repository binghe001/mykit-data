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
package io.mykit.data.storage.enums;

/**
 * @author binghe
 * @version 1.0.0
 * @description 存储类型
 */
public enum StorageEnum {

    /**
     * 配置：连接器、驱动、映射关系、同步信息、系统配置
     */
    CONFIG("config"),
    /**
     * 日志：连接器、驱动、映射关系、同步信息、系统日志
     */
    LOG("log"),
    /**
     * 数据：全量或增量数据
     */
    DATA("data");

    private String type;

    StorageEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
