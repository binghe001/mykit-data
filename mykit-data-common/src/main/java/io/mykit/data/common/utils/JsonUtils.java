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
package io.mykit.data.common.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author binghe
 * @version 1.0.0
 * @description Json工具类
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String objToJson(Object obj) {
        try {
            // 将对象转换成json
            return mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public static <T> T jsonToObj(String json, Class<T> valueType) {
        try {
            return (T) mapper.readValue(json, valueType);
        } catch (Exception e) {
        }
        return null;
    }
}
