package io.mykit.data.parser.convert.handler;

import io.mykit.data.common.utils.AESEncyptUtils;
import io.mykit.data.parser.convert.AbstractHandler;

/**
 * AES加密
 */
public class AesEncryptHandler extends AbstractHandler {

    @Override
    public Object convert(String args, Object value) throws Exception {
        return AESEncyptUtils.encrypt(String.valueOf(value), args);
    }
}