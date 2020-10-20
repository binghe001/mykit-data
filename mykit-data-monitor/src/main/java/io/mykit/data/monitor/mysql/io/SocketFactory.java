package io.mykit.data.monitor.mysql.io;

import java.net.Socket;

public interface SocketFactory {

    Socket create(String host, int port) throws Exception;
}
