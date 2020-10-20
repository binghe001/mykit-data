package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.Handler;
import org.apache.commons.lang.StringUtils;

/**
 * 默认值
 */
public class DefaultHandler implements Handler {

    @Override
    public Object handle(String args, Object value) {
        return null == value || StringUtils.isBlank(String.valueOf(value)) ? args : value;
    }
}