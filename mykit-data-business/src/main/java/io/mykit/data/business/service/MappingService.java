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

import io.mykit.data.business.vo.MappingVo;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 映射服务
 */
public interface MappingService {

    /**
     * 新增驱动
     *
     * @param params
     */
    String add(Map<String, String> params);

    /**
     * 修改驱动
     *
     * @param params
     */
    String edit(Map<String, String> params);

    /**
     * 删除驱动
     *
     * @param id
     */
    String remove(String id);

    /**
     * 获取驱动
     *
     * @param id
     * @return
     */
    MappingVo getMapping(String id);

    /**
     * 获取所有驱动
     *
     * @return
     */
    List<MappingVo> getMappingAll();

    /**
     * 启动驱动
     *
     * @param id
     */
    String start(String id);

    /**
     * 停止驱动
     *
     * @param id
     */
    String stop(String id);
}
