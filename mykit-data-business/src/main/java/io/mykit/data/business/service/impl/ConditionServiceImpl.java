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

import io.mykit.data.business.service.ConditionService;
import io.mykit.data.business.vo.ConditionVo;
import io.mykit.data.connector.enums.FilterEnum;
import io.mykit.data.connector.enums.OperationEnum;
import io.mykit.data.manage.Manager;
import io.mykit.data.monitor.enums.QuartzFilterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
@Component
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private Manager manager;

    @Override
    public ConditionVo getCondition() {
        List<OperationEnum> operationEnumAll = manager.getOperationEnumAll();
        List<QuartzFilterEnum> quartzFilterEnumAll = manager.getQuartzFilterEnumAll();
        List<FilterEnum> filterEnumAll = manager.getFilterEnumAll();
        return new ConditionVo(operationEnumAll, quartzFilterEnumAll, filterEnumAll);
    }
}
