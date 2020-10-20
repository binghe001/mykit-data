package io.mykit.data.parser.convert.handler;

import io.mykit.data.parser.convert.Handler;

/**
 * 前面追加
 */
public class PrependHandler implements Handler {

    @Override
    public Object handle(String args, Object value) {
        if (null == value) {
            return args;
        }
        return new StringBuilder().append(args).append(value).toString();
    }
}