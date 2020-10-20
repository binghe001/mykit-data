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
package io.mykit.data.business.vo;

import io.mykit.data.connector.enums.FilterEnum;
import io.mykit.data.connector.enums.OperationEnum;
import io.mykit.data.monitor.enums.QuartzFilterEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 条件
 */
public class ConditionVo implements Serializable {
    /**
     * 条件
     */
    private List<OperationEnum> operation;

    /**
     * 系统参数(定时配置)
     */
    private List<QuartzFilterEnum> quartzFilter;

    /**
     * 运算符
     */
    private List<FilterEnum> filter;

    public ConditionVo(List<OperationEnum> operation, List<QuartzFilterEnum> quartzFilter, List<FilterEnum> filter) {
        this.operation = operation;
        this.quartzFilter = quartzFilter;
        this.filter = filter;
    }

    public List<OperationEnum> getOperation() {
        return operation;
    }

    public void setOperation(List<OperationEnum> operation) {
        this.operation = operation;
    }

    public List<QuartzFilterEnum> getQuartzFilter() {
        return quartzFilter;
    }

    public void setQuartzFilter(List<QuartzFilterEnum> quartzFilter) {
        this.quartzFilter = quartzFilter;
    }

    public List<FilterEnum> getFilter() {
        return filter;
    }

    public void setFilter(List<FilterEnum> filter) {
        this.filter = filter;
    }
}
