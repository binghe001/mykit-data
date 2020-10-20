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
package io.mykit.data.manage.config;

import io.mykit.data.manage.template.Callback;
import io.mykit.data.storage.StorageService;
import io.mykit.data.storage.enums.StorageEnum;

import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 操作回调
 */
public class OperationCallBack implements Callback {

    private StorageService storageService;

    private StorageEnum type;

    private Map params;

    public OperationCallBack(StorageService storageService, StorageEnum type, Map params) {
        this.storageService = storageService;
        this.type = type;
        this.params = params;
    }

    public void add() {
        storageService.add(type, params);
    }

    public void edit() {
        storageService.edit(type, params);
    }
}
