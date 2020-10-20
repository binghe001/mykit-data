package io.mykit.data.parser.convert.handler;


import io.mykit.data.common.utils.AESEncyptUtils;
import io.mykit.data.parser.convert.AbstractHandler;

/**
 * AES解密
 */
public class AesDecryptHandler extends AbstractHandler {

    @Override
    public Object convert(String args, Object value) throws Exception {
        return AESEncyptUtils.decrypt(String.valueOf(value), args);
    }

}