package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.AbstractHandler;
import org.apache.commons.lang.StringUtils;

/**
 * 去掉尾字符
 */
public class RemStrLastHandler extends AbstractHandler {

    @Override
    protected Object convert(String args, Object value) {
        String s = String.valueOf(value);
        return StringUtils.substring(s, 0, s.length() - 1);
    }
}