package io.mykit.data.parser.convert;

public interface Handler {

    /**
     * 值转换
     *
     * @param args  参数
     * @param value 值
     * @return
     */
    Object handle(String args, Object value);
}