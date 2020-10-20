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
package io.mykit.data.monitor.enums;

import io.mykit.data.connector.enums.ConnectorEnum;
import io.mykit.data.monitor.exception.ListenerException;
import io.mykit.data.monitor.mysql.MysqlExtractor;
import io.mykit.data.monitor.oracle.OracleExtractor;
import io.mykit.data.monitor.quartz.QuartzExtractor;
import org.apache.commons.lang.StringUtils;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监听枚举
 */
public enum ListenerEnum {
    /**
     * 定时
     */
    DEFAULT(ListenerTypeEnum.TIMING.getType(), QuartzExtractor.class),
    /**
     * Mysql
     */
    MYSQL(ConnectorEnum.MYSQL.getType(), MysqlExtractor.class),
    /**
     * Oracle
     */
    ORACLE(ConnectorEnum.ORACLE.getType(), OracleExtractor.class);

    private String type;
    private Class<?> clazz;

    ListenerEnum(String type, Class<?> clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    /**
     * 获取抽取器
     *
     * @param type
     * @return
     * @throws ListenerException
     */
    public static Class<?> getExtractor(String type) throws ListenerException {
        for (ListenerEnum e : ListenerEnum.values()) {
            if (StringUtils.equals(type, e.getType())) {
                return e.getClazz();
            }
        }
        throw new ListenerException(String.format("Extractor type \"%s\" does not exist.", type));
    }

    public String getType() {
        return type;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
