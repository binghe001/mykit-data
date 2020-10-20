package io.mykit.data.monitor.mysql.binlog.impl.parser;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.binlog.BinlogParserContext;
import io.mykit.data.monitor.mysql.binlog.impl.event.IntvarEvent;
import io.mykit.data.monitor.mysql.common.glossary.UnsignedLong;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public class IntvarEventParser extends AbstractBinlogEventParser {

    public IntvarEventParser() {
        super(IntvarEvent.EVENT_TYPE);
    }

    public void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context)
            throws IOException {
        final IntvarEvent event = new IntvarEvent(header);
        event.setBinlogFilename(context.getBinlogFileName());
        event.setType(is.readInt(1));
        event.setValue(UnsignedLong.valueOf(is.readLong(8)));
        context.getEventListener().onEvents(event);
    }
}
