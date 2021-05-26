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
package io.mykit.data.monitor.oracle.dcn;

import oracle.jdbc.dcn.RowChangeDescription.RowOperation;

/**
 * @author binghe
 * @version 1.0.0
 * @description 封装监听到的Oracle事件
 */
public final class DCNEvent {

    /**
     * 数据表名称
     */
    private String tableName;
    /**
     * 数据表rowId
     */
    private String rowId;
    /**
     * 行操作事件
     */
    private RowOperation event;


    public DCNEvent() {
        super();
    }


    public DCNEvent(String tableName, String rowId, RowOperation event) {
        super();
        this.tableName = tableName;
        this.rowId = rowId;
        this.event = event;
    }


    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getRowId() {
        return rowId;
    }


    public void setRowId(String rowId) {
        this.rowId = rowId;
    }


    public RowOperation getEvent() {
        return event;
    }


    public void setEvent(RowOperation event) {
        this.event = event;
    }
}
