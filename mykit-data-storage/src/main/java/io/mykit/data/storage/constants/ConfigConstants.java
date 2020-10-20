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
package io.mykit.data.storage.constants;

/**
 * @author binghe
 * @version 1.0.0
 * @description 常量类
 */
public class ConfigConstants {

    /**
     * 公共属性
     */
    public static final String CONFIG_MODEL_ID = "id";
    public static final String CONFIG_MODEL_NAME = "name";
    public static final String CONFIG_MODEL_TYPE = "type";
    public static final String CONFIG_MODEL_CREATE_TIME = "createTime";
    public static final String CONFIG_MODEL_UPDATE_TIME = "updateTime";
    public static final String CONFIG_MODEL_JSON = "json";

    /**
     * 配置类型
     */
    public static final String CONNECTOR = "connector";
    public static final String MAPPING = "mapping";
    public static final String TABLE_GROUP = "tableGroup";
    public static final String META = "meta";
    public static final String CONFIG = "config";

    /**
     * 数据
     */
    public static final String DATA_SUCCESS = "success";
    public static final String DATA_EVENT = "event";
    public static final String DATA_ERROR = "error";
}
