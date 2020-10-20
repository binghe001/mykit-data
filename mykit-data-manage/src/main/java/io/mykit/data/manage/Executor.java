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
package io.mykit.data.manage;

import io.mykit.data.parser.enums.MetaEnum;
import io.mykit.data.parser.model.Mapping;

/**
 * @author binghe
 * @version 1.0.0
 * @description 同步任务执行器
 */
public interface Executor {

    /**
     * 启动同步任务
     *
     * @param mapping
     */
    void start(Mapping mapping);

    /**
     * 关闭同步任务
     *
     * @param mapping
     */
    void close(Mapping mapping);

    /**
     * 切换meta状态
     *
     * @param metaId
     * @param metaEnum
     */
    void changeMetaState(String metaId, MetaEnum metaEnum);
}
