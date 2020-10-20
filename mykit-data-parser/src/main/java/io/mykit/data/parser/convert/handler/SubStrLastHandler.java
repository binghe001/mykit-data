package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.AbstractHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.Assert;

/**
 * 从后面截取N个字符
 */
public class SubStrLastHandler extends AbstractHandler {

    @Override
    protected Object convert(String args, Object value) {
        Assert.isTrue(NumberUtils.isNumber(args), "参数必须为正整数.");
        String s = String.valueOf(value);
        int size = NumberUtils.toInt(args);
        int length = s.length();
        return StringUtils.substring(s, length - size, length);
    }
}