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
package io.mykit.data.cache.service;

/**
 * @author binghe
 * @version 1.0.0
 * @description 缓存接口
 */
public interface CacheService {

    /**
     * 存放K-V
     * @param key 缓存的key
     * @param value 缓存的value
     * @return 返回缓存的value
     */
    Object put(String key, Object value);

    /**
     * 存放K-V，不存在k则写入
     * @param key 缓存的key
     * @param value 缓存的value
     * @return 返回缓存的value
     */
    Object putIfAbsent(String key, Object value);

    /**
     * 根据Key删除
     * @param key 缓存的key
     */
    void remove(String key);

    /**
     * 根据Key获取值
     * @param key 缓存的key
     * @return 返回缓存的value
     */
    Object get(String key);

    /**
     * 根据Key获取值
     * @param key 缓存的key
     * @param valueType value的类型
     * @return 返回valueType类型的value
     */
    <T> T get(String key, Class<T> valueType);
}
