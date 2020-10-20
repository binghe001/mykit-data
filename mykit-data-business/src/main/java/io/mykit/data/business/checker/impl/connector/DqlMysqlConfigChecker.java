package io.mykit.data.business.checker.impl.connector;


import io.mykit.data.parser.model.Connector;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DqlMysqlConfigChecker extends DataBaseConfigChecker {

    @Override
    public void modify(Connector connector, Map<String, String> params) {
        super.modify(connector, params);
        super.modifyDql(connector, params);
    }
}