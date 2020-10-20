package io.mykit.data.monitor.mysql.binlog.impl.parser;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.binlog.BinlogParserContext;
import io.mykit.data.monitor.mysql.binlog.impl.event.StopEvent;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public class StopEventParser extends AbstractBinlogEventParser {

    public StopEventParser() {
        super(StopEvent.EVENT_TYPE);
    }

    public void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
            throws IOException {
        final StopEvent event = new StopEvent(header);
        event.setBinlogFilename(context.getBinlogFileName());
        context.getEventListener().onEvents(event);
    }
}
