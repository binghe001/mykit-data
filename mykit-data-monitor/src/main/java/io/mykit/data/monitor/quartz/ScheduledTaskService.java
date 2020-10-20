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

/**
 * @author binghe
 * @version 1.0.0
 * @description 调度任务服务
 */
public interface ScheduledTaskService {

    /**
     * 第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日期天/日，取值1-31
     * 第五位，日期月份，取值1-12
     * 第六位，星期，取值1-7
     * [秒 分 时 日 月 星期]
     *
     * @param key  任务唯一key
     * @param cron 任务表达式
     * @param job  任务实现
     */
    void start(String key, String cron, ScheduledTaskJob job);

    /**
     * 根据指定的Key停止任务
     * @param key 任务的Key
     */
    void stop(String key);
}
