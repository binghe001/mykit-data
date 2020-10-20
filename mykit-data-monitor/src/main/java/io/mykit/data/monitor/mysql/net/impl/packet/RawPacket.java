package io.mykit.data.monitor.mysql.net.impl.packet;

import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

public class RawPacket extends AbstractPacket {
    private static final long serialVersionUID = 4109090905397000303L;

    private byte packetBody[];

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("length", length)
                .append("sequence", sequence).toString();
    }

    public byte[] getPacketBody() {
        return packetBody;
    }

    public void setPacketBody(byte[] packetBody) {
        this.packetBody = packetBody;
    }
}
