package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.Handler;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;

public class DefaultTimestampHandler implements Handler {

    @Override
    public Object handle(String args, Object value) {
        return null == value || StringUtils.isBlank(String.valueOf(value)) ? new Timestamp(Instant.now().toEpochMilli()) : value;
    }
}