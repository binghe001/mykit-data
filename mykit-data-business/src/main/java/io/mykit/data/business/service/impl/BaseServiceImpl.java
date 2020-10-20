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

import io.mykit.data.manage.Manager;
import io.mykit.data.parser.enums.MetaEnum;
import io.mykit.data.parser.enums.ModelEnum;
import io.mykit.data.parser.logger.LogService;
import io.mykit.data.parser.logger.LogType;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.Meta;
import io.mykit.data.parser.model.TableGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author binghe
 * @version 1.0.0
 * @description 基础服务实现类
 */
public class BaseServiceImpl {

    @Autowired
    protected Manager manager;

    @Autowired
    private LogService logService;

    /**
     * 驱动启停锁
     */
    protected final static Object LOCK = new Object();

    protected boolean isRunning(String metaId) {
        Meta meta = manager.getMeta(metaId);
        if (null != meta) {
            int state = meta.getState();
            return MetaEnum.isRunning(state);
        }
        return false;
    }

    protected void assertRunning(String metaId) {
        Assert.isTrue(!isRunning(metaId), "驱动正在运行, 请先停止.");
    }

    protected void assertRunning(TableGroup model) {
        synchronized (LOCK) {
            Mapping mapping = manager.getMapping(model.getMappingId());
            assertRunning(mapping.getMetaId());
        }
    }

    protected void log(LogType log, ConfigModel model) {
        if (null != model) {
            // 新增连接器:知识库
            logService.log(log, "%s%s:%s", log.getMessage(), log.getName(), model.getName());
        }
    }

    protected void log(LogType log, Mapping mapping) {
        if (null != mapping) {
            // 新增驱动:知识库(全量)
            String model = ModelEnum.getModelEnum(mapping.getModel()).getName();
            logService.log(log, "%s%s:%s(%s)", log.getMessage(), log.getName(), mapping.getName(), model);
        }
    }

    protected void log(LogType log, TableGroup tableGroup) {
        if (null != tableGroup) {
            Mapping mapping = manager.getMapping(tableGroup.getMappingId());
            if (null != mapping) {
                // 新增驱动知识库(全量)映射关系:[My_User] >> [My_User_Target]
                String name = mapping.getName();
                String model = ModelEnum.getModelEnum(mapping.getModel()).getName();
                String s = tableGroup.getSourceTable().getName();
                String t = tableGroup.getTargetTable().getName();
                logService.log(log, "%s驱动%s(%s)%s:[%s] >> [%s]", log.getMessage(), name, model, log.getName(), s, t);
            }
        }
    }
}
