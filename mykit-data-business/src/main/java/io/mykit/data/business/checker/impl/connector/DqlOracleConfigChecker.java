package io.mykit.data.business.checker.impl.connector;


import io.mykit.data.parser.model.Connector;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DqlOracleConfigChecker extends DataBaseConfigChecker {

    @Override
    public void modify(Connector connector, Map<String, String> params) {
        super.modifyDql(connector, params);
    }
}