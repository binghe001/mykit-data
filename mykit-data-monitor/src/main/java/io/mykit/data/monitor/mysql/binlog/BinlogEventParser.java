package io.mykit.data.monitor.mysql.binlog;


import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public interface BinlogEventParser {

    int getEventType();

    void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException;
}
