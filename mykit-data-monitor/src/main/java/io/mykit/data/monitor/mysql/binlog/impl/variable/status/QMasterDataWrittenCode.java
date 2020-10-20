package io.mykit.data.monitor.mysql.binlog.impl.variable.status;

import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public class QMasterDataWrittenCode extends AbstractStatusVariable {
    public static final int TYPE = MySQLConstants.Q_MASTER_DATA_WRITTEN_CODE;

    private final int value;

    public QMasterDataWrittenCode(int value) {
        super(TYPE);
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value).toString();
    }

    public int getValue() {
        return value;
    }

    public static QMasterDataWrittenCode valueOf(XInputStream tis) throws IOException {
        return new QMasterDataWrittenCode(tis.readInt(4));
    }
}
