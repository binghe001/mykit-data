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
package io.mykit.data.storage.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author binghe
 * @version 1.0.0
 * @description 查询参数
 */
public class Query {

    private List<Param> params;

    private int pageNum = 1;

    private int pageSize = 20;

    public Query() {
        this.params = new ArrayList<>();
    }

    public Query(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.params = new ArrayList<>();
    }

    public void put(String key, String value) {
        params.add(new Param(key, value));
    }

    public void put(String key, String value, boolean highlighter) {
        params.add(new Param(key, value, highlighter));
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }
}
