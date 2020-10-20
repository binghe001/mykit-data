package io.mykit.data.monitor.mysql.binlog.impl.parser;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.binlog.BinlogParserContext;
import io.mykit.data.monitor.mysql.binlog.impl.event.IncidentEvent;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public class IncidentEventParser extends AbstractBinlogEventParser {

    public IncidentEventParser() {
        super(IncidentEvent.EVENT_TYPE);
    }

    public void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
            throws IOException {
        final IncidentEvent event = new IncidentEvent(header);
        event.setBinlogFilename(context.getBinlogFileName());
        event.setIncidentNumber(is.readInt(1));
        event.setMessageLength(is.readInt(1));
        if (event.getMessageLength() > 0) event.setMessage(is.readFixedLengthString(event.getMessageLength()));
        context.getEventListener().onEvents(event);
    }
}
