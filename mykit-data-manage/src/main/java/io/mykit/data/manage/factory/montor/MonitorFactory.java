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
package io.mykit.data.manage.factory.montor;

import io.mykit.data.manage.Manager;
import io.mykit.data.monitor.Monitor;
import io.mykit.data.parser.model.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监控工厂类
 */
@Component
public class MonitorFactory implements Monitor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Manager manager;

    @Autowired
    private Executor taskExecutor;

    @Override
    @Cacheable(value = "connector", keyGenerator = "cacheKeyGenerator")
    public boolean alive(String id) {
        Connector connector = manager.getConnector(id);
        return null != connector ? manager.alive(connector.getConfig()) : false;
    }

    @Override
    public Map getThreadInfo() {
        Map map = new HashMap();
        if (taskExecutor instanceof ThreadPoolTaskExecutor) {
            ThreadPoolTaskExecutor threadTask = (ThreadPoolTaskExecutor) taskExecutor;
            ThreadPoolExecutor threadPoolExecutor = threadTask.getThreadPoolExecutor();

            map.put("已提交", threadPoolExecutor.getTaskCount());
            map.put("已完成", threadPoolExecutor.getCompletedTaskCount());
            map.put("处理中", threadPoolExecutor.getActiveCount());
            map.put("排队中", threadPoolExecutor.getQueue().size());
            map.put("队列长度", threadPoolExecutor.getQueue().remainingCapacity());
        }
        return map;
    }
}
