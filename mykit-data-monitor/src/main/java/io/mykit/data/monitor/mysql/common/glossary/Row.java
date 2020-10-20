package io.mykit.data.monitor.mysql.common.glossary;

import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

import java.util.List;

public class Row {
    private List<Column> columns;

    public Row() {
    }

    public Row(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("columns", columns).toString();
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
