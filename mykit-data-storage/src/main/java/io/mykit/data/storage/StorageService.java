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
package io.mykit.data.storage;

import io.mykit.data.storage.enums.StorageEnum;
import io.mykit.data.storage.query.Query;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 存储的基础服务接口
 */
public interface StorageService {

    List<Map> query(StorageEnum type, Query query);

    List<Map> query(StorageEnum type, Query query, String collectionId);

    void add(StorageEnum type, Map params);

    void add(StorageEnum type, Map params, String collectionId);

    void edit(StorageEnum type, Map params);

    void edit(StorageEnum type, Map params, String collectionId);

    void remove(StorageEnum type, String id);

    void remove(StorageEnum type, String id, String collectionId);

    /**
     * 记录日志
     *
     * @param log
     * @param params
     */
    void addLog(StorageEnum log, Map<String, Object> params);

    /**
     * 记录数据
     *
     * @param data
     * @param collectionId
     * @param list
     */
    void addData(StorageEnum data, String collectionId, List<Map> list);

    /**
     * 清空数据/日志
     *
     * @param type
     * @param collectionId
     */
    void clear(StorageEnum type, String collectionId);
}
