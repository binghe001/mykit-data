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
package io.mykit.data.cache.service.impl;

import io.mykit.data.cache.service.CacheService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author binghe
 * @version 1.0.0
 * @description 缓存的实现类
 */
@Component
public class CacheServiceImpl implements CacheService {

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    @Override
    public Object put(String key, Object value) {
        return cache.put(key, value);
    }

    @Override
    public Object putIfAbsent(String key, Object value) {
        return cache.putIfAbsent(key, value);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> valueType) {
        return (T) cache.get(key);
    }

}
