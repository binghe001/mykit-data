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
package io.mykit.data.connector.enums;

import io.mykit.data.connector.CompareFilter;
import io.mykit.data.connector.exception.ConnectorException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author binghe
 * @version 1.0.0
 * @description 运算表达式类型
 */
public enum  FilterEnum {

    /**
     * 等于
     */
    EQUAL("=", (value, filterValue) -> StringUtils.equals(value, filterValue)),
    /**
     * 不等于
     */
    NOT_EQUAL("!=", (value, filterValue) -> !StringUtils.equals(value, filterValue)),
    /**
     * 大于
     */
    GT(">", (value, filterValue) -> NumberUtils.toInt(value) > NumberUtils.toInt(filterValue)),
    /**
     * 小于
     */
    LT("<", (value, filterValue) -> NumberUtils.toInt(value) < NumberUtils.toInt(filterValue)),
    /**
     * 大于等于
     */
    GT_AND_EQUAL(">=", (value, filterValue) -> NumberUtils.toInt(value) >= NumberUtils.toInt(filterValue)),
    /**
     * 小于等于
     */
    LT_AND_EQUAL("<=", (value, filterValue) -> NumberUtils.toInt(value) <= NumberUtils.toInt(filterValue));

    // 运算符名称
    private String name;
    // 比较器
    private CompareFilter compareFilter;

    FilterEnum(String name, CompareFilter compareFilter) {
        this.name = name;
        this.compareFilter = compareFilter;
    }

    /**
     * 获取比较器
     *
     * @param filterName
     * @return
     * @throws ConnectorException
     */
    public static CompareFilter getCompareFilter(String filterName) throws ConnectorException {
        for (FilterEnum e : FilterEnum.values()) {
            if (StringUtils.equals(filterName, e.getName())) {
                return e.getCompareFilter();
            }
        }
        throw new ConnectorException(String.format("FilterEnum name \"%s\" does not exist.", filterName));
    }

    public String getName() {
        return name;
    }

    public CompareFilter getCompareFilter() {
        return compareFilter;
    }
}
