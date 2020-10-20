package io.mykit.data.monitor.mysql.binlog.impl.variable.status;

import io.mykit.data.monitor.mysql.binlog.StatusVariable;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

public abstract class AbstractStatusVariable implements StatusVariable {
    protected final int type;

    public AbstractStatusVariable(int type) {
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
