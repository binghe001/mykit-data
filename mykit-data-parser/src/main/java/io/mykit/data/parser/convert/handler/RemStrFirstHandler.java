package io.mykit.data.parser.convert.handler;


import io.mykit.data.parser.convert.AbstractHandler;
import org.apache.commons.lang.StringUtils;

/**
 * 去掉首字符
 */
public class RemStrFirstHandler extends AbstractHandler {

    @Override
    protected Object convert(String args, Object value) {
        return StringUtils.substring(String.valueOf(value), 1);
    }
}