package io.mykit.data.monitor.mysql.binlog;

public interface BinlogEventFilter {

    boolean accepts(BinlogEventV4Header header, BinlogParserContext context);
}
