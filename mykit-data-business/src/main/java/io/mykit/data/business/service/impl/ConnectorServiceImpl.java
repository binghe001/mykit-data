/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.data.business.service.impl;

import io.mykit.data.business.checker.Checker;
import io.mykit.data.business.service.ConnectorService;
import io.mykit.data.manage.Manager;
import io.mykit.data.parser.logger.LogType;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.model.Connector;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
@Service
public class ConnectorServiceImpl extends BaseServiceImpl implements ConnectorService {

    @Autowired
    private Manager manager;

    @Autowired
    private Checker connectorChecker;

    @Override
    public String add(Map<String, String> params) {
        ConfigModel model = connectorChecker.checkAddConfigModel(params);
        log(LogType.ConnectorLog.INSERT, model);

        return manager.addConnector(model);
    }

    @Override
    public String edit(Map<String, String> params) {
        ConfigModel model = connectorChecker.checkEditConfigModel(params);
        log(LogType.ConnectorLog.UPDATE, model);

        return manager.editConnector(model);
    }

    @Override
    public String remove(String id) {
        Connector connector = manager.getConnector(id);
        log(LogType.ConnectorLog.DELETE, connector);

        manager.removeConnector(id);
        return "删除连接器成功!";
    }

    @Override
    public Connector getConnector(String id) {
        return StringUtils.isNotBlank(id) ? manager.getConnector(id) : null;
    }

    @Override
    public List<Connector> getConnectorAll() {
        List<Connector> list = manager.getConnectorAll()
                .stream()
                .sorted(Comparator.comparing(Connector::getUpdateTime).reversed())
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> getConnectorTypeAll() {
        List<String> list = new ArrayList<>();
        manager.getConnectorEnumAll().forEach(c -> list.add(c.getType()));
        return list;
    }
}
