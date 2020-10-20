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
package io.mykit.data.common.model;

import java.io.Serializable;

/**
 * @author binghe
 * @version 1.0.0
 * @description 封装的执行任务
 */
public class Task implements Serializable {
    private static final long serialVersionUID = -1947541232118328359L;

    private String id;

    private StateEnum state;

    private long beginTime;

    private long endTime;

    public Task() {
    }

    public Task(String id) {
        this.id = id;
        this.state = StateEnum.RUNNING;
    }

    public void stop() {
        this.state = StateEnum.STOP;
    }

    public boolean isRunning() {
        return StateEnum.RUNNING == state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public enum StateEnum {
        /**
         * 运行
         */
        RUNNING,
        /**
         * 停止
         */
        STOP;
    }
}
