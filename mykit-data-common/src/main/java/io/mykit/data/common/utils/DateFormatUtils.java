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

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description 时间格式化类
 */
public class DateFormatUtils {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static ZoneId zoneId = ZoneId.systemDefault();

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    public static String dateToString(Date date) {
        String format = date.toInstant().atZone(zoneId).toLocalDate().format(dateFormatter);
        return format;
    }

    public static Date stringToDate(String s) {
        LocalDate localDate = LocalDate.parse(s, dateFormatter);
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
        return Date.from(instant);
    }
}
