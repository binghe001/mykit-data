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
package io.mykit.data.monitor;

import io.mykit.data.common.event.Event;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.connector.config.ConnectorConfig;
import io.mykit.data.monitor.config.ListenerConfig;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author binghe
 * @version 1.0.0
 * @description 抽象任务提取器
 */
public abstract class AbstractExtractor implements Extractor{
    protected ConnectorConfig connectorConfig;
    protected ListenerConfig listenerConfig;
    protected Map<String, String> map;
    private List<Event> watcher;

    @Override
    public void addListener(Event event) {
        if (null != event) {
            if (null == watcher) {
                watcher = new CopyOnWriteArrayList<>();
            }
            watcher.add(event);
        }
    }

    @Override
    public void clearAllListener() {
        if (null != watcher) {
            watcher.clear();
            watcher = null;
        }
    }

    @Override
    public void changedQuartzEvent(int tableGroupIndex, String event, Map<String, Object> before, Map<String, Object> after) {
        if (!CollectionUtils.isEmpty(watcher)) {
            watcher.forEach(w -> w.changedQuartzEvent(tableGroupIndex, event, before, after));
        }
    }

    @Override
    public void changedLogEvent(String tableName, String event, List<Object> before, List<Object> after) {
        if (!CollectionUtils.isEmpty(watcher)) {
            watcher.forEach(w -> w.changedLogEvent(tableName, event, before, after));
        }
    }

    @Override
    public void flushEvent() {
        if (!CollectionUtils.isEmpty(watcher)) {
            watcher.forEach(w -> w.flushEvent(map));
        }
    }

    @Override
    public void errorEvent(Exception e) {
        if (!CollectionUtils.isEmpty(watcher)) {
            watcher.forEach(w -> w.errorEvent(e));
        }
    }

    public void setConnectorConfig(ConnectorConfig connectorConfig) {
        this.connectorConfig = connectorConfig;
    }

    public void setListenerConfig(ListenerConfig listenerConfig) {
        this.listenerConfig = listenerConfig;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
