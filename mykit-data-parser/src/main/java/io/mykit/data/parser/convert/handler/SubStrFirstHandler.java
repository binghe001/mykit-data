package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.AbstractHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.Assert;

/**
 * 从前面截取N个字符
 */
public class SubStrFirstHandler extends AbstractHandler {

    @Override
    protected Object convert(String args, Object value) {
        Assert.isTrue(NumberUtils.isNumber(args), "参数必须为正整数.");
        String s = String.valueOf(value);
        int size = NumberUtils.toInt(args);
        return StringUtils.substring(s, 0, size);
    }
}