package io.mykit.data.monitor.mysql.binlog.impl.event;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.common.glossary.Row;
import io.mykit.data.monitor.mysql.common.glossary.UnsignedLong;
import io.mykit.data.monitor.mysql.common.glossary.column.BitColumn;
import io.mykit.data.monitor.mysql.common.util.MySQLConstants;
import io.mykit.data.monitor.mysql.common.util.ToStringBuilder;

import java.util.List;

/**
 * Used for row-based binary logging. This event logs inserts of rows in a single table.
 */
public final class WriteRowsEvent extends AbstractRowEvent {
    public static final int EVENT_TYPE = MySQLConstants.WRITE_ROWS_EVENT;

    private UnsignedLong columnCount;
    private BitColumn usedColumns;
    private List<Row> rows;

    public WriteRowsEvent() {
    }

    public WriteRowsEvent(BinlogEventV4Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("header", header)
                .append("tableId", tableId)
                .append("reserved", reserved)
                .append("columnCount", columnCount)
                .append("usedColumns", usedColumns)
                .append("rows", rows).toString();
    }

    public UnsignedLong getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(UnsignedLong columnCount) {
        this.columnCount = columnCount;
    }

    public BitColumn getUsedColumns() {
        return usedColumns;
    }

    public void setUsedColumns(BitColumn usedColumns) {
        this.usedColumns = usedColumns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
