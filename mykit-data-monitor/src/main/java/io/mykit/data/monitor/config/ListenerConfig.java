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
package io.mykit.data.monitor.config;

import java.io.Serializable;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监听配置
 */
public class ListenerConfig implements Serializable {
    private static final long serialVersionUID = 8355393745980631196L;

    /**
     * 监听器类型
     */
    private String listenerType;

    /**
     * 每次读取数
     */
    private int readNum = 200;

    // 定时表达式, 格式: [秒] [分] [小时] [日] [月] [周]
    private String cronExpression = "*/30 * * * * ?";

    // 事件字段
    private String eventFieldName = "";

    // 修改事件, 例如当eventFieldName值等于U 或 update时，判定该条数据为修改操作
    private String update = "U";

    // 插入事件
    private String insert = "I";

    // 删除事件
    private String delete = "D";

    // 表别名
    private String tableLabel = "T1";

    public ListenerConfig() {
    }

    public ListenerConfig(String listenerType) {
        this.listenerType = listenerType;
    }

    public String getListenerType() {
        return listenerType;
    }

    public void setListenerType(String listenerType) {
        this.listenerType = listenerType;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getEventFieldName() {
        return eventFieldName;
    }

    public void setEventFieldName(String eventFieldName) {
        this.eventFieldName = eventFieldName;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getTableLabel() {
        return tableLabel;
    }

    public void setTableLabel(String tableLabel) {
        this.tableLabel = tableLabel;
    }
}
