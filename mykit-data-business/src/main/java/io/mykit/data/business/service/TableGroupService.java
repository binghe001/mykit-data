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
package io.mykit.data.business.service;

import io.mykit.data.parser.model.TableGroup;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据表分组服务
 */
public interface TableGroupService {

    /**
     * 新增表关系
     *
     * @param params
     */
    String add(Map<String, String> params);

    /**
     * 修改表关系
     *
     * @param params
     */
    String edit(Map<String, String> params);

    /**
     * 删除表关系
     *
     * @param id
     */
    boolean remove(String id);

    /**
     * 获取表关系
     *
     * @param id
     * @return
     */
    TableGroup getTableGroup(String id);

    /**
     * 获取所有表关系
     *
     * @param mappingId
     * @return
     */
    List<TableGroup> getTableGroupAll(String mappingId);
}
