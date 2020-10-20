package io.mykit.data.monitor.mysql.binlog.impl.variable.status;

import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;
import io.mykit.data.monitor.mysql.io.XInputStream;

import java.io.IOException;

public class QSQLModeCode extends AbstractStatusVariable {
    public static final int TYPE = MySQLConstants.Q_SQL_MODE_CODE;

    private final long sqlMode;

    public QSQLModeCode(long sqlMode) {
        super(TYPE);
        this.sqlMode = sqlMode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sqlMode", sqlMode).toString();
    }

    public long getSqlMode() {
        return sqlMode;
    }

    public static QSQLModeCode valueOf(XInputStream tis) throws IOException {
        return new QSQLModeCode(tis.readLong(8));
    }
}
