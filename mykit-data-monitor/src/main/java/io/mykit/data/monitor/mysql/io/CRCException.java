package io.mykit.data.monitor.mysql.io;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;

import java.io.IOException;


public class CRCException extends IOException {
    private static final long serialVersionUID = -3079479140853693743L;

    public CRCException(BinlogEventV4Header header) {
        super("CRC Exception processing " + header);
    }

}
