package io.mykit.data.monitor.mysql.binlog.impl.variable.status;


import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public class QFlags2Code extends AbstractStatusVariable {
    public static final int TYPE = MySQLConstants.Q_FLAGS2_CODE;

    private final int flags;

    public QFlags2Code(int flags) {
        super(TYPE);
        this.flags = flags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("flags", flags).toString();
    }

    public int getFlags() {
        return flags;
    }

    public static QFlags2Code valueOf(XInputStream tis) throws IOException {
        return new QFlags2Code(tis.readInt(4));
    }
}
