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

import io.mykit.data.parser.model.Connector;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 连接服务
 */
public interface ConnectorService {
    /**
     * 新增连接器
     *
     * @param params
     */
    String add(Map<String, String> params);

    /**
     * 修改连接器
     *
     * @param params
     */
    String edit(Map<String, String> params);

    /**
     * 删除连接器
     *
     * @param id
     */
    String remove(String id);

    /**
     * 获取连接器
     *
     * @param id
     * @return
     */
    Connector getConnector(String id);

    /**
     * 获取所有连接器
     *
     * @return
     */
    List<Connector> getConnectorAll();

    /**
     * 获取所有支持的连接器类型
     *
     * @return
     */
    List<String> getConnectorTypeAll();
}
