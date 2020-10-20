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
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author binghe
 * @version 1.0.0
 * @description 封装读取的结果数据
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 5936488937825603334L;
    // 读取数据
    private List<Map<String, Object>> data;

    // 错误数据
    private Queue<Map<String, Object>> failData;

    // 错误数
    private AtomicLong fail;

    // 错误日志
    private StringBuffer error;

    public Result() {
        init();
    }

    public Result(List<Map<String, Object>> data) {
        init();
        this.data = data;
    }

    private void init() {
        this.failData = new ConcurrentLinkedQueue<>();
        this.fail = new AtomicLong(0);
        this.error = new StringBuffer();
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public Queue<Map<String, Object>> getFailData() {
        return failData;
    }

    public AtomicLong getFail() {
        return fail;
    }

    public StringBuffer getError() {
        return error;
    }
}
