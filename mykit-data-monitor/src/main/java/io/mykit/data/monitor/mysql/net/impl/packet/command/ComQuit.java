package io.mykit.data.monitor.mysql.net.impl.packet.command;

import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

import java.io.IOException;

public class ComQuit extends AbstractCommandPacket {
    private static final long serialVersionUID = 4569517082177697955L;

    public ComQuit() {
        super(MySQLConstants.COM_QUIT);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

    public byte[] getPacketBody() throws IOException {
        return new byte[0];
    }
}
