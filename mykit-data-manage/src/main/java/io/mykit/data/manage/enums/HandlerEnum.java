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
package io.mykit.data.manage.enums;

import io.mykit.data.manage.config.OperationCallBack;
import io.mykit.data.manage.config.PreloadCallBack;
import io.mykit.data.manage.handler.AbstractOperationHandler;
import io.mykit.data.manage.handler.AbstractPreloadHandler;
import io.mykit.data.manage.template.Handler;

/**
 * @author binghe
 * @version 1.0.0
 * @description 操作类型
 */
public enum HandlerEnum {

    /**
     * 添加
     */
    OPR_ADD(new AbstractOperationHandler() {
        @Override
        protected void handle(OperationCallBack operationCallBack) {
            operationCallBack.add();
        }
    }),
    /**
     * 修改
     */
    OPR_EDIT(new AbstractOperationHandler() {
        @Override
        protected void handle(OperationCallBack operationCallBack) {
            operationCallBack.edit();
        }
    }),
    /**
     * 预加载Connector
     */
    PRELOAD_CONNECTOR(new AbstractPreloadHandler() {
        @Override
        protected Object preload(PreloadCallBack preloadCallBack) {
            return preloadCallBack.parseConnector();
        }
    }),
    /**
     * 预加载Mapping
     */
    PRELOAD_MAPPING(new AbstractPreloadHandler() {
        @Override
        protected Object preload(PreloadCallBack preloadCallBack) {
            return preloadCallBack.parseMapping();
        }
    }),
    /**
     * 预加载TableGroup
     */
    PRELOAD_TABLE_GROUP(new AbstractPreloadHandler() {
        @Override
        protected Object preload(PreloadCallBack preloadCallBack) {
            return preloadCallBack.parseTableGroup();
        }
    }),
    /**
     * 预加载Meta
     */
    PRELOAD_META(new AbstractPreloadHandler() {
        @Override
        protected Object preload(PreloadCallBack preloadCallBack) {
            return preloadCallBack.parseMeta();
        }
    }),
    /**
     * 预加载Config
     */
    PRELOAD_CONFIG(new AbstractPreloadHandler() {
        @Override
        protected Object preload(PreloadCallBack preloadCallBack) {
            return preloadCallBack.parseConfig();
        }
    });

    private Handler handler;

    HandlerEnum(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }
}
