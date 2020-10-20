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
import io.mykit.data.business.service.TableGroupService;
import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.connector.config.Field;
import io.mykit.data.parser.logger.LogType;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.TableGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author binghe
 * @version 1.0.0
 * @description 数据表分组服务
 */
@Service
public class TableGroupServiceImpl extends BaseServiceImpl implements TableGroupService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Checker tableGroupChecker;

    @Override
    public String add(Map<String, String> params) {
        TableGroup model = (TableGroup) tableGroupChecker.checkAddConfigModel(params);
        assertRunning(model);
        log(LogType.TableGroupLog.INSERT, model);

        String id = manager.addTableGroup(model);

        // 合并驱动公共字段
        mergeMappingColumn(model.getMappingId());

        return id;
    }

    @Override
    public String edit(Map<String, String> params) {
        TableGroup model = (TableGroup) tableGroupChecker.checkEditConfigModel(params);
        assertRunning(model);
        log(LogType.TableGroupLog.UPDATE, model);

        return manager.editTableGroup(model);
    }

    @Override
    public boolean remove(String id) {
        TableGroup tableGroup = manager.getTableGroup(id);
        Assert.notNull(tableGroup, "tableGroup can not be null.");
        assertRunning(tableGroup);
        log(LogType.TableGroupLog.DELETE, tableGroup);

        manager.removeTableGroup(id);
        // 合并驱动公共字段
        mergeMappingColumn(tableGroup.getMappingId());

        return true;
    }

    @Override
    public TableGroup getTableGroup(String id) {
        TableGroup tableGroup = manager.getTableGroup(id);
        Assert.notNull(tableGroup, "TableGroup can not be null");
        return tableGroup;
    }

    @Override
    public List<TableGroup> getTableGroupAll(String mappingId) {
        return manager.getTableGroupAll(mappingId);
    }

    private void mergeMappingColumn(String mappingId) {
        List<TableGroup> groups = manager.getTableGroupAll(mappingId);

        Mapping mapping = manager.getMapping(mappingId);
        Assert.notNull(mapping, "mapping not exist.");

        List<Field> sourceColumn = null;
        List<Field> targetColumn = null;
        for (TableGroup g : groups) {
            sourceColumn = pickCommonFields(sourceColumn, g.getSourceTable().getColumn());
            targetColumn = pickCommonFields(targetColumn, g.getTargetTable().getColumn());
        }

        mapping.setSourceColumn(sourceColumn);
        mapping.setTargetColumn(targetColumn);
        manager.editMapping(mapping);
    }

    private List<Field> pickCommonFields(List<Field> column, List<Field> target) {
        if (CollectionUtils.isEmpty(column) || CollectionUtils.isEmpty(target)) {
            return target;
        }
        List<Field> list = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        column.forEach(f -> keys.add(f.getName()));
        target.forEach(f -> {
            if (keys.contains(f.getName())) {
                list.add(f);
            }
        });
        return list;
    }
}
