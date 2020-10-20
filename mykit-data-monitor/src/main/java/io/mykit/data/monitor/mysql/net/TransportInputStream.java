package io.mykit.data.monitor.mysql.net;


import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public interface TransportInputStream extends XInputStream {
    Packet readPacket() throws IOException;

    public int currentPacketLength();

    public int currentPacketSequence();
}
