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
import io.mykit.data.parser.Parser;
import io.mykit.data.parser.model.Config;
import io.mykit.data.parser.model.Mapping;
import io.mykit.data.parser.model.Meta;
import io.mykit.data.parser.model.TableGroup;

/**
 * @author binghe
 * @version 1.0.0
 * @description PreloadCallBack
 */
public class PreloadCallBack implements Callback {

    private Parser parser;

    private String json;

    public PreloadCallBack(Parser parser, String json) {
        this.parser = parser;
        this.json = json;
    }

    public Object parseConnector() {
        return parser.parseConnector(json);
    }

    public Object parseMapping() {
        return parser.parseObject(json, Mapping.class);
    }

    public Object parseTableGroup() {
        return parser.parseObject(json, TableGroup.class);
    }

    public Object parseMeta() {
        return parser.parseObject(json, Meta.class);
    }

    public Object parseConfig() {
        return parser.parseObject(json, Config.class);
    }

}
