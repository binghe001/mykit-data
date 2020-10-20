package io.mykit.data.monitor.oracle;


import io.mykit.data.connector.config.DatabaseConfig;
import io.mykit.data.connector.constants.ConnectorConstants;
import io.mykit.data.monitor.AbstractExtractor;
import io.mykit.data.monitor.exception.ListenerException;
import io.mykit.data.monitor.oracle.dcn.DBChangeNotification;
import io.mykit.data.monitor.oracle.dcn.RowChangeEvent;
import oracle.jdbc.dcn.TableChangeDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class OracleExtractor extends AbstractExtractor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DBChangeNotification client;

    @Override
    public void start() {
        try {
            final DatabaseConfig config = (DatabaseConfig) connectorConfig;
            String username = config.getUsername();
            String password = config.getPassword();
            String url = config.getUrl();
            client = new DBChangeNotification(username, password, url);
            client.addRowEventListener((e) -> onEvent(e));
            client.start();
        } catch (Exception e) {
            logger.error("启动失败:{}", e.getMessage());
            throw new ListenerException(e);
        }
    }

    @Override
    public void close() {
        if (null != client) {
            client.close();
        }
    }

    private void onEvent(RowChangeEvent event) {
        if (event.getEvent() == TableChangeDescription.TableOperation.UPDATE.getCode()) {
            changedLogEvent(event.getTableName(), ConnectorConstants.OPERTION_UPDATE, Collections.EMPTY_LIST, event.getData());
            return;
        }

        if (event.getEvent() == TableChangeDescription.TableOperation.INSERT.getCode()) {
            changedLogEvent(event.getTableName(), ConnectorConstants.OPERTION_INSERT, Collections.EMPTY_LIST, event.getData());
            return;
        }

        if (event.getEvent() == TableChangeDescription.TableOperation.DELETE.getCode()) {
            changedLogEvent(event.getTableName(), ConnectorConstants.OPERTION_DELETE, event.getData(), Collections.EMPTY_LIST);
            return;
        }
    }

}