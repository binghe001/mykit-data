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
package io.mykit.data.monitor.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author binghe
 * @version 1.0.0
 * @description 调度任务服务实现类
 */
@Component
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 定时任务线程池
     */
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private Map<String, ScheduledFuture> map = new ConcurrentHashMap<>();

    @Override
    public void start(String key, String cron, ScheduledTaskJob job) {
        //校验任务key是否已经启动
        final ScheduledFuture scheduledFuture = map.get(key);
        if (null != scheduledFuture && !scheduledFuture.isCancelled()) {
            logger.warn(">>>>>> 当前任务已经启动，无需重复启动！");
            return;
        }
        //获取需要定时调度的接口
        map.putIfAbsent(key, taskScheduler.schedule(job, (trigger) -> new CronTrigger(cron).nextExecutionTime(trigger)));
    }

    @Override
    public void stop(String key) {
        ScheduledFuture job = map.get(key);
        if (null != job) {
            logger.info(">>>>>> 进入停止任务 {}  >>>>>>", key);
            job.cancel(true);
        }
    }
}
