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
package io.mykit.data.common.event;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 事件接口
 */
public interface Event {


    /**
     * 日志数据变更事件
     *
     * @param tableName 表名
     * @param event     事件
     * @param before    变化前
     * @param after     变化后
     */
    void changedLogEvent(String tableName, String event, List<Object> before, List<Object> after);

    /**
     * 定时数据变更事件
     */
    void changedQuartzEvent(int tableGroupIndex, String event, Map<String, Object> before, Map<String, Object> after);

    /**
     * 写入增量点事件
     */
    void flushEvent(Map<String, String> map);

    /**
     * 异常事件
     */
    void errorEvent(Exception e);
}
