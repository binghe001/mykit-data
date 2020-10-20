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

import io.mykit.data.manage.config.PreloadCallBack;
import io.mykit.data.manage.template.Callback;
import io.mykit.data.manage.template.Handler;

/**
 * @author binghe
 * @version 1.0.0
 * @description AbstractPreloadHandler
 */
public abstract class AbstractPreloadHandler implements Handler {

    /**
     * 交给实现handler处理
     */
    protected abstract Object preload(PreloadCallBack preloadCallBack);

    @Override
    public Object execute(Callback call) {
        if (null != call && call instanceof PreloadCallBack) {
            PreloadCallBack preloadCallBack = (PreloadCallBack) call;
            return preload(preloadCallBack);
        }
        return null;
    }

}
