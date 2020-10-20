package io.mykit.data.monitor.mysql.binlog.impl.parser;


import io.mykit.data.monitor.mysql.binlog.BinlogEventParser;
import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.binlog.BinlogParserContext;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public final class NopEventParser implements BinlogEventParser {

    public int getEventType() {
        throw new UnsupportedOperationException();
    }

    public void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
            throws IOException {
        final int available = is.available();
        is.skip(available);
    }
}
