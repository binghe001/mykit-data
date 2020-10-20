package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.AbstractHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 替换
 */
public class ReplaceHandler extends AbstractHandler {

    @Override
    protected Object convert(String args, Object value) {
        Assert.hasText(args, "缺少替换参数.");
        String[] split = StringUtils.split(args, ",");
        String a = split[0];
        String b = split.length == 2 ? split[1] : "";
        return StringUtils.replace(String.valueOf(value), a, b);
    }
}