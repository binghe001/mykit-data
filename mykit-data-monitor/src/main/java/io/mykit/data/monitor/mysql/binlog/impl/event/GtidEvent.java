package io.mykit.data.monitor.mysql.binlog.impl.event;

import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

import java.util.Arrays;

public class GtidEvent extends AbstractBinlogEventV4 {
    private byte[] sourceId;
    private long transactionId;

    public GtidEvent() {
    }

    public GtidEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header", header)
                .append("transactionId", transactionId)
                .append("sourceId", Arrays.toString(sourceId)).toString();
    }

    public byte[] getSourceId() {
        return sourceId;
    }

    public void setSourceId(byte[] sourceId) {
        this.sourceId = sourceId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
