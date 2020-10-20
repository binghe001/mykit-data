package io.mykit.data.monitor.mysql.binlog.impl.event;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

/**
 * Written when mysqld stops.
 */
public final class StopEvent extends AbstractBinlogEventV4 {
    public static final int EVENT_TYPE = MySQLConstants.STOP_EVENT;

    public StopEvent() {
    }

    public StopEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header", header).toString();
    }
}
