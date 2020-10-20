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
package io.mykit.data.manage.handler;

import io.mykit.data.manage.config.OperationCallBack;
import io.mykit.data.manage.template.Callback;
import io.mykit.data.manage.template.Handler;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public abstract class AbstractOperationHandler implements Handler {
    /**
     * 交给实现handler处理
     *
     * @param operationCallBack
     * @return
     */
    protected abstract void handle(OperationCallBack operationCallBack);

    @Override
    public Object execute(Callback call) {
        if (null != call && call instanceof OperationCallBack) {
            handle((OperationCallBack) call);
        }
        return null;
    }
}
