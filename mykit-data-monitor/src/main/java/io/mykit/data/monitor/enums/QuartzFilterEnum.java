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

import io.mykit.data.common.utils.DateFormatUtils;
import io.mykit.data.monitor.QuartzFilter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description 定时过滤器枚举
 */
public enum QuartzFilterEnum {

    /**
     * 时间戳(开始)
     */
    TIME_STAMP_BEGIN("$timestamp_begin$", "系统时间戳(开始)", new QuartzFilter() {
        @Override
        public Object getObject() {
            return new Timestamp(Instant.now().toEpochMilli());
        }

        @Override
        public Object getObject(String s) {
            return new Timestamp(Long.parseLong(s));
        }

        @Override
        public String toString(Object value) {
            Timestamp ts = (Timestamp) value;
            return String.valueOf(ts.getTime());
        }
    }),
    /**
     * 时间戳(结束)
     */
    TIME_STAMP_END("$timestamp_end$", "系统时间戳(结束)", new QuartzFilter() {
        @Override
        public Object getObject() {
            return new Timestamp(Instant.now().toEpochMilli());
        }

        @Override
        public Object getObject(String s) {
            return new Timestamp(Long.parseLong(s));
        }

        @Override
        public String toString(Object value) {
            Timestamp ts = (Timestamp) value;
            return String.valueOf(ts.getTime());
        }

        @Override
        public boolean begin() {
            return false;
        }
    }),
    /**
     * 日期
     */
    DATE_BEGIN("$date$", "系统日期", new QuartzFilter() {
        @Override
        public Object getObject() {
            return new Date();
        }

        @Override
        public Object getObject(String s) {
            return DateFormatUtils.stringToDate(s);
        }

        @Override
        public String toString(Object value) {
            return DateFormatUtils.dateToString((Date) value);
        }
    });

    private String type;
    private String message;
    private QuartzFilter quartzFilter;

    QuartzFilterEnum(String type, String message, QuartzFilter quartzFilter) {
        this.type = type;
        this.message = message;
        this.quartzFilter = quartzFilter;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public QuartzFilter getQuartzFilter() {
        return quartzFilter;
    }
}
