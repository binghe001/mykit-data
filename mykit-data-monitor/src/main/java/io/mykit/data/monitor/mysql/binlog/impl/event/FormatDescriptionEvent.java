package io.mykit.data.monitor.mysql.binlog.impl.event;

import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.common.glossary.column.StringColumn;
import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

import java.util.Arrays;

public final class FormatDescriptionEvent extends AbstractBinlogEventV4 {
    //
    public static final int EVENT_TYPE = MySQLConstants.FORMAT_DESCRIPTION_EVENT;

    //
    private int binlogVersion;
    private StringColumn serverVersion;
    private long createTimestamp;
    private int headerLength;
    private byte[] eventTypes;

    /**
     *
     */
    public FormatDescriptionEvent() {
    }

    public FormatDescriptionEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header", header)
                .append("binlogVersion", binlogVersion)
                .append("serverVersion", serverVersion)
                .append("createTimestamp", createTimestamp)
                .append("headerLength", headerLength)
                .append("eventTypes", Arrays.toString(eventTypes)).toString();
    }

    /**
     *
     */
    public int getBinlogVersion() {
        return binlogVersion;
    }

    public void setBinlogVersion(int binlogVersion) {
        this.binlogVersion = binlogVersion;
    }

    public StringColumn getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(StringColumn serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public byte[] getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(byte[] eventTypes) {
        this.eventTypes = eventTypes;
    }

    public boolean checksumEnabled() {
        if (checksumPossible()) {
            return this.eventTypes[this.eventTypes.length - 1] == 1;
        } else {
            return false;
        }

    }

    public boolean checksumPossible() {
        Integer[] version = splitServerVersion();
        if ((version[0] >= 5 && version[1] >= 6 && version[2] >= 1) || (version[0] >= 8)) {
            return true;
        } else {
            return false;
        }
    }

    private Integer[] splitServerVersion() {
        String version = this.serverVersion.toString();
        String[] versionSplit = version.split("\\.");
        Integer[] ret = new Integer[versionSplit.length];

        for (int i = 0; i < versionSplit.length; i++) {
            ret[i] = Integer.parseInt(versionSplit[i].replaceAll("[^0-9]", ""));
        }
        return ret;
    }
}
