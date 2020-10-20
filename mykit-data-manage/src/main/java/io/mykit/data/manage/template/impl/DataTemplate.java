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
package io.mykit.data.manage.template.impl;

import io.mykit.data.storage.StorageService;
import io.mykit.data.storage.enums.StorageEnum;
import io.mykit.data.storage.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 同步数据和日志模板
 */
@Component
public final class DataTemplate {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    public List<Map> query(StorageEnum type, Query query, String collectionId) {
        return storageService.query(type, query, collectionId);
    }

    public void clear(StorageEnum type, String collectionId) {
        storageService.clear(type, collectionId);
    }

}
