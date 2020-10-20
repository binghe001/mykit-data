package io.mykit.data.monitor.mysql.binlog.impl.variable.user;

import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

public class UserVariableReal extends AbstractUserVariable {
    public static final int TYPE = MySQLConstants.REAL_RESULT;

    private final double value;

    public UserVariableReal(double value) {
        super(TYPE);
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value).toString();
    }

    public Double getValue() {
        return this.value;
    }
}
