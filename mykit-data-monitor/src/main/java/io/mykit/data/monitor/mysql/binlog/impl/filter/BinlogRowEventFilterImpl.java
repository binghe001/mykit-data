package io.mykit.data.monitor.mysql.binlog.impl.filter;


import io.mykit.data.monitor.mysql.binlog.BinlogEventV4Header;
import io.mykit.data.monitor.mysql.binlog.BinlogParserContext;
import io.mykit.data.monitor.mysql.binlog.BinlogRowEventFilter;
import io.mykit.data.monitor.mysql.binlog.impl.event.TableMapEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h3>默认拦截器</h3>
 * <ol type="1">
 * <li><dt>自定义拦截</dt></li>
 * </ol>
 */
public class BinlogRowEventFilterImpl implements BinlogRowEventFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinlogRowEventFilterImpl.class);

    private boolean verbose = true;

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean accepts(BinlogEventV4Header header, BinlogParserContext context, TableMapEvent event) {
        if (event == null) {
            if (isVerbose() && LOGGER.isWarnEnabled()) {
                LOGGER.warn("failed to find TableMapEvent, header: {}", header);
            }
            return false;
        }
        return true;
    }
}
