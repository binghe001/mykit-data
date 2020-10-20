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
package io.mykit.data.manage.puller.impl;

import io.mykit.data.common.event.FullRefreshEvent;
import io.mykit.data.common.model.Task;
import io.mykit.data.manage.Manager;
import io.mykit.data.manage.puller.AbstractPuller;
import io.mykit.data.parser.Parser;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.Meta;
import io.mykit.data.parser.model.TableGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author binghe
 * @version 1.0.0
 * @description 全量同步
 */
@Component
public class FullPuller extends AbstractPuller implements ApplicationListener<FullRefreshEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Parser parser;

    @Autowired
    private Manager manager;

    private Map<String, Task> map = new ConcurrentHashMap<>();

    @Override
    public void asyncStart(Mapping mapping) {
        final String mappingId = mapping.getId();
        final String metaId = mapping.getMetaId();
        map.putIfAbsent(metaId, new Task(metaId));

        try {
            List<TableGroup> list = manager.getTableGroupAll(mappingId);
            Assert.notEmpty(list, "映射关系不能为空");

            // 执行任务
            logger.info("启动任务:{}", metaId);
            Task task = map.get(metaId);
            doTask(task, mapping, list);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            map.remove(metaId);
            publishClosedEvent(metaId);
            logger.info("结束任务:{}", metaId);
        }
    }

    @Override
    public void close(String metaId) {
        Task task = map.get(metaId);
        if (null != task) {
            task.stop();
        }
    }

    @Override
    public void onApplicationEvent(FullRefreshEvent event) {
        // 异步监听任务刷新事件
        flush(event.getTask());
    }

    private void doTask(Task task, Mapping mapping, List<TableGroup> list) {
        // 记录开始时间
        long now = Instant.now().toEpochMilli();
        task.setBeginTime(now);
        task.setEndTime(now);
        flush(task);

        for (TableGroup t : list) {
            if (!task.isRunning()) {
                break;
            }
            parser.execute(task, mapping, t);
        }

        // 记录结束时间
        task.setEndTime(Instant.now().toEpochMilli());
        flush(task);
    }

    private void flush(Task task) {
        Meta meta = manager.getMeta(task.getId());
        Assert.notNull(meta, "检查meta为空.");

        meta.setBeginTime(task.getBeginTime());
        meta.setEndTime(task.getEndTime());
        manager.editMeta(meta);
    }
}
