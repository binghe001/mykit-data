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

/**
 * @author binghe
 * @version 1.0.0
 * @description Redis配置
 */
public class RedisConfig extends ConnectorConfig{

    private String url;

    // key
    private String key;

    // 密码
    private String password;

    public String getUrl() {
        return url;
    }

    public RedisConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getKey() {
        return key;
    }

    public RedisConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RedisConfig setPassword(String password) {
        this.password = password;
        return this;
    }
}
