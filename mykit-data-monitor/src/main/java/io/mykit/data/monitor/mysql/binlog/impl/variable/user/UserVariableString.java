package io.mykit.data.monitor.mysql.binlog.impl.variable.user;


import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

public class UserVariableString extends AbstractUserVariable {
    public static final int TYPE = MySQLConstants.STRING_RESULT;

    private final byte[] value;
    private final int collation;

    public UserVariableString(byte[] value, int collation) {
        super(TYPE);
        this.value = value;
        this.collation = collation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("collation", collation).toString();
    }

    public byte[] getValue() {
        return this.value;
    }

    public int getCollation() {
        return collation;
    }
}
