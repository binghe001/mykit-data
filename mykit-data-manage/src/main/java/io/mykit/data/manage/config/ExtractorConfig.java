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
package io.mykit.data.manage.config;

import io.mykit.data.common.event.Event;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.monitor.config.ListenerConfig;

import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据抽取配置
 */
public class ExtractorConfig {

    private ConnectorConfig connectorConfig;
    private ListenerConfig listenerConfig;
    private Map<String, String> map;
    private Event event;

    /**
     * 抽取器配置
     *
     * @param connectorConfig 连接器配置
     * @param listenerConfig  监听配置
     * @param map             增量元信息
     * @param event           监听器
     */
    public ExtractorConfig(ConnectorConfig connectorConfig, ListenerConfig listenerConfig, Map<String, String> map, Event event) {
        this.connectorConfig = connectorConfig;
        this.listenerConfig = listenerConfig;
        this.map = map;
        this.event = event;
    }

    public ConnectorConfig getConnectorConfig() {
        return connectorConfig;
    }

    public ListenerConfig getListenerConfig() {
        return listenerConfig;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public Event getEvent() {
        return event;
    }
}
