package io.mykit.data.monitor.mysql.binlog.impl.event;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.common.glossary.column.StringColumn;
import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

/**
 * Written when mysqld switches to a new binary log file. This occurs when someone
 * issues a FLUSH LOGS statement or the current binary log file becomes too large.
 * The maximum size is determined by max_binlog_size.
 */
public final class RotateEvent extends AbstractBinlogEventV4 {
    public static final int EVENT_TYPE = MySQLConstants.ROTATE_EVENT;

    private long binlogPosition;
    private StringColumn binlogFileName;

    public RotateEvent() {
    }

    public RotateEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header", header)
                .append("binlogPosition", binlogPosition)
                .append("binlogFileName", binlogFileName).toString();
    }

    public long getBinlogPosition() {
        return binlogPosition;
    }

    public void setBinlogPosition(long binlogPosition) {
        this.binlogPosition = binlogPosition;
    }

    public StringColumn getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(StringColumn binlogFileName) {
        this.binlogFileName = binlogFileName;
    }
}
