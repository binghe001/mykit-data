package io.mykit.data.monitor.mysql.binlog.impl.parser;


import io.mykit.data.monitor.mysql.binlog.BinlogEventParser;

public abstract class AbstractBinlogEventParser implements BinlogEventParser {
    protected final int eventType;

    public AbstractBinlogEventParser(int eventType) {
        this.eventType = eventType;
    }

    public final int getEventType() {
        return eventType;
    }
}
