package io.mykit.data.monitor.mysql.binlog;


import io.mykit.data.monitor.mysql.binlog.impl.event.TableMapEvent;

public interface BinlogRowEventFilter {

    boolean accepts(BinlogEventV4Header header, BinlogParserContext context, TableMapEvent event);
}
