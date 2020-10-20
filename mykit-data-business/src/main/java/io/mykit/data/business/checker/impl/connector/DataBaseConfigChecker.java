package io.mykit.data.business.checker.impl.connector;


import io.mykit.data.business.checker.ConnectorConfigChecker;
import io.mykit.data.connector.config.DatabaseConfig;
import io.mykit.data.parser.model.Connector;
import org.springframework.util.Assert;

import java.util.Map;

public abstract class DataBaseConfigChecker implements ConnectorConfigChecker {

    @Override
    public void modify(Connector connector, Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        String url = params.get("url");
        String driverClassName = params.get("driverClassName");
        Assert.hasText(username, "Username is empty.");
        Assert.hasText(password, "Password is empty.");
        Assert.hasText(url, "Url is empty.");
        Assert.hasText(driverClassName, "DriverClassName is empty.");

        DatabaseConfig config = (DatabaseConfig) connector.getConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setUrl(url);
        config.setDriverClassName(driverClassName);
    }

    protected void modifyDql(Connector connector, Map<String, String> params) {
        String sql = params.get("sql");
        Assert.hasText(sql, "Sql is empty.");
        DatabaseConfig config = (DatabaseConfig) connector.getConfig();
        config.setSql(sql);
    }

}