/**
 * DBSyncer Copyright 2019-2024 All Rights Reserved.
 */
package io.mykit.data.monitor.oracle.dcn;

/**
 * 行变更监听器
 */
public interface RowEventListener {

    void onEvents(RowChangeEvent event);

}