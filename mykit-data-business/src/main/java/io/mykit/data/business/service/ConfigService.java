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

import io.mykit.data.business.vo.ConfigVo;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 配置服务
 */
public interface ConfigService {

    /**
     * 修改系统配置
     *
     * @param params
     */
    String edit(Map<String, String> params);

    /**
     * 获取系统配置
     *
     * @return
     */
    ConfigVo getConfig();

    /**
     * 获取所有配置
     *
     * @return
     */
    List<ConfigVo> queryConfig();
}
