package io.mykit.data.monitor.mysql.net;

public interface Transport {

    boolean isConnected();

    void disconnect() throws Exception;

    void connect(String host, int port) throws Exception;

    TransportContext getContext();

    TransportInputStream getInputStream();

    TransportOutputStream getOutputStream();

    interface Authenticator {

        void login(Transport transport) throws Exception;
    }
}
