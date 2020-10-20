package io.mykit.data.monitor.mysql.net.impl.packet;

import io.mykit.data.monitor.mysql.common.glossary.UnsignedLong;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;
import io.mykit.data.monitor.mysql.io.util.XDeserializer;
import io.mykit.data.monitor.mysql.io.util.XSerializer;
import io.mykit.data.monitor.mysql.net.Packet;

import java.io.IOException;

public class ResultSetHeaderPacket extends AbstractPacket {
    private static final long serialVersionUID = -5491186291875548645L;

    private UnsignedLong fieldCount;
    private UnsignedLong extra;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fieldCount", fieldCount)
                .append("extra", extra).toString();
    }

    public byte[] getPacketBody() {
        final XSerializer s = new XSerializer(32);
        s.writeUnsignedLong(this.fieldCount);
        if (this.extra != null) s.writeUnsignedLong(this.extra);
        return s.toByteArray();
    }

    public UnsignedLong getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(UnsignedLong fieldCount) {
        this.fieldCount = fieldCount;
    }

    public UnsignedLong getExtra() {
        return extra;
    }

    public void setExtra(UnsignedLong extra) {
        this.extra = extra;
    }

    public static ResultSetHeaderPacket valueOf(Packet packet) throws IOException {
        final XDeserializer d = new XDeserializer(packet.getPacketBody());
        final ResultSetHeaderPacket r = new ResultSetHeaderPacket();
        r.length = packet.getLength();
        r.sequence = packet.getSequence();
        r.fieldCount = d.readUnsignedLong();
        if (d.available() > 0) r.extra = d.readUnsignedLong();
        return r;
    }
}
