package io.mykit.data.monitor.mysql.binlog.impl.variable.user;

import io.mykit.data.monitor.mysql.binlog.UserVariable;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

public abstract class AbstractUserVariable implements UserVariable {
    protected final int type;

    public AbstractUserVariable(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

    public int getType() {
        return type;
    }
}
