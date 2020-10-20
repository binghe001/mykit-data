package io.mykit.data.monitor.mysql.common.glossary.column;

import io.mykit.data.monitor.mysql.common.glossary.Column;

public final class FloatColumn implements Column {
    private static final long serialVersionUID = -890414733626452618L;

    private final float value;

    private FloatColumn(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public Float getValue() {
        return this.value;
    }

    public static final FloatColumn valueOf(float value) {
        return new FloatColumn(value);
    }
}
