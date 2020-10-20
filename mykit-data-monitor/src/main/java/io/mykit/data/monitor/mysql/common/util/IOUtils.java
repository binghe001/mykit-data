package io.mykit.data.monitor.mysql.common.util;


import io.mykit.data.monitor.mysql.io.XInputStream;
import io.mykit.data.monitor.mysql.io.XOutputStream;

import java.net.Socket;

public final class IOUtils {

    public static void closeQuietly(Socket socket) {
        try {
            socket.close();
        } catch (Exception e) {
            // NOP
        }
    }

    public static void closeQuietly(XInputStream is) {
        try {
            is.close();
        } catch (Exception e) {
            // NOP
        }
    }

    public static void closeQuietly(XOutputStream os) {
        try {
            os.close();
        } catch (Exception e) {
            // NOP
        }
    }
}
