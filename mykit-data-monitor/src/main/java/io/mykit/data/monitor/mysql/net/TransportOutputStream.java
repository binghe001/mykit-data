package io.mykit.data.monitor.mysql.net;

import io.mykit.data.monitor.mysql.io.XOutputStream;

import java.io.IOException;

public interface TransportOutputStream extends XOutputStream {

    void writePacket(Packet packet) throws IOException;

}
