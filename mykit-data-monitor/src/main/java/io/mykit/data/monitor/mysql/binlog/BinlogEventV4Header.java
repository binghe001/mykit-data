package io.mykit.data.monitor.mysql.binlog;

public interface BinlogEventV4Header {

    int getHeaderLength();

    long getPosition();

    long getTimestamp();

    int getEventType();

    long getServerId();

    long getEventLength();

    long getNextPosition();

    int getFlags();

    long getTimestampOfReceipt();
}
