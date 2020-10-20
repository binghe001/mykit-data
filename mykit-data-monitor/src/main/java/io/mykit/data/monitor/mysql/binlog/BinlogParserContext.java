package io.mykit.data.monitor.mysql.binlog;


import io.mykit.data.monitor.mysql.binlog.impl.event.TableMapEvent;

public interface BinlogParserContext {
    boolean getChecksumEnabled();

    void setChecksumEnabled(boolean flag);


    String getBinlogFileName();

    BinlogEventListener getEventListener();

    TableMapEvent getTableMapEvent(long tableId);
}
