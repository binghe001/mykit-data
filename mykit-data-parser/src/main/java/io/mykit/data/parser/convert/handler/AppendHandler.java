package io.mykit.data.parser.convert.handler;


import io.mykit.data.parser.convert.Handler;

/**
 * 后面追加
 */
public class AppendHandler implements Handler {

    @Override
    public Object handle(String args, Object value) {
        if (null == value) {
            return args;
        }
        return new StringBuilder().append(value).append(args).toString();
    }
}