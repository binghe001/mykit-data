package io.mykit.data.monitor.mysql.binlog;

public interface BinlogEventListener {

    void onEvents(BinlogEventV4 event);
}
