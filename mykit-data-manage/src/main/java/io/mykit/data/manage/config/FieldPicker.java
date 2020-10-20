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
package io.mykit.data.manage.config;

import io.mykit.data.common.utils.CollectionUtils;
import io.mykit.data.connector.CompareFilter;
import io.mykit.data.connector.config.Field;
import io.mykit.data.connector.config.Filter;
import io.mykit.data.connector.enums.FilterEnum;
import io.mykit.data.connector.enums.OperationEnum;
import io.mykit.data.parser.model.DataEvent;
import io.mykit.data.parser.model.FieldMapping;
import io.mykit.data.parser.model.TableGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author binghe
 * @version 1.0.0
 * @description 字段选择器
 */
public class FieldPicker {

    private TableGroup tableGroup;
    private List<Node> index;
    private int indexSize;
    private boolean filterSwitch;
    private List<Filter> add;
    private List<Filter> or;

    public FieldPicker(TableGroup tableGroup) {
        this.tableGroup = tableGroup;
    }

    public FieldPicker(TableGroup tableGroup, List<Filter> filter, List<Field> column, List<FieldMapping> fieldMapping) {
        this.tableGroup = tableGroup;
        init(filter, column, fieldMapping);
    }

    public Map<String, Object> getColumns(List<Object> list) {
        if (!CollectionUtils.isEmpty(list)) {
            Map<String, Object> data = new HashMap<>(indexSize);
            final int size = list.size() - 1;
            index.parallelStream().forEach(node -> {
                if (node.i <= size) {
                    data.put(node.name, list.get(node.i));
                }
            });
            return data;
        }
        return Collections.EMPTY_MAP;
    }

    public TableGroup getTableGroup() {
        return tableGroup;
    }

    /**
     * 根据过滤条件过滤
     * @param data
     * @return
     */
    public boolean filter(DataEvent data) {
        if (!filterSwitch) {
            return true;
        }
        final Map<String, Object> row = data.getData();
        // where (id > 1 and id < 100) or (id = 100 or id =101)
        // 或 关系(成立任意条件)
        CompareFilter filter = null;
        Object value = null;
        for (Filter f : or) {
            value = row.get(f.getName());
            if (null == value) {
                continue;
            }
            filter = FilterEnum.getCompareFilter(f.getFilter());
            if (filter.compare(String.valueOf(value), f.getValue())) {
                return true;
            }
        }

        boolean pass = false;
        // 并 关系(成立所有条件)
        for (Filter f : add) {
            value = row.get(f.getName());
            if (null == value) {
                continue;
            }
            filter = FilterEnum.getCompareFilter(f.getFilter());
            if (!filter.compare(String.valueOf(value), f.getValue())) {
                return false;
            }
            pass = true;
        }

        return pass;
    }

    private void init(List<Filter> filter, List<Field> column, List<FieldMapping> fieldMapping) {
        // 解析过滤条件
        if ((filterSwitch = !CollectionUtils.isEmpty(filter))) {
            add = filter.stream().filter(f -> StringUtils.equals(f.getOperation(), OperationEnum.AND.getName())).collect(
                    Collectors.toList());
            or = filter.stream().filter(f -> StringUtils.equals(f.getOperation(), OperationEnum.OR.getName())).collect(Collectors.toList());
        }

        // column  => [1, 86, 0, 中文, 2020-05-15T12:17:22.000+0800, 备注信息]
        Assert.notEmpty(column, "读取字段不能为空.");
        Assert.notEmpty(fieldMapping, "映射关系不能为空.");

        // 找到同步字段 => [{source.name}]
        Set<String> key = fieldMapping.stream().filter(m -> null != m.getSource()).map(m -> m.getSource().getName()).collect(Collectors.toSet());

        // 记录字段索引 [{"ID":0},{"NAME":1}]
        index = new LinkedList<>();
        int size = column.size();
        String k = null;
        for (int i = 0; i < size; i++) {
            k = column.get(i).getName();
            if (key.contains(k)) {
                index.add(new Node(k, i));
            }
        }
        Assert.notEmpty(index, "同步映射关系不能为空.");
        this.indexSize = index.size();
    }

    final class Node {
        // 属性
        String name;
        // 索引
        int i;

        public Node(String name, int i) {
            this.name = name;
            this.i = i;
        }
    }
}
