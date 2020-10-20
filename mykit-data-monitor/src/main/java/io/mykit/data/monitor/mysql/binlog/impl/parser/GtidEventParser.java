package io.mykit.data.monitor.mysql.binlog.impl.parser;

import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.binlog.BinlogParserContext;
import io.mykit.data.monitor.mysql.binlog.impl.event.GtidEvent;
import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

/**
 * <h3>GTID Event</h3>
 * <ol type="1">
 * <li><dt>Event format:</dt></li>
 * <pre>
 *    +-------------------+
 *    | 1B commit flag    |
 *    +-------------------+
 *    | 16B Source ID     |
 *    +-------------------+
 *    | 8B Txn ID         |
 *    +-------------------+
 *    | ...               |
 *    +-------------------+
 *  </pre>
 * </ol>
 */
public class GtidEventParser extends AbstractBinlogEventParser {

    public GtidEventParser() {
        super(MySQLConstants.GTID_LOG_EVENT);
    }

    public void parse(XInputStream is, BinlogEventV4Header header, BinlogParserContext context) throws IOException {
        GtidEvent event = new GtidEvent(header);
        is.readBytes(1); // commit flag, always true
        event.setSourceId(is.readBytes(16));
        event.setTransactionId(is.readLong(8, true));
        //event.setTransactionId(ByteBuffer.wrap(is.readBytes(8)).order(ByteOrder.LITTLE_ENDIAN).getLong());
        is.skip(is.available()); // position at next event
        context.getEventListener().onEvents(event);
    }
}
