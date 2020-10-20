package io.mykit.data.parser.convert.handler;

import io.mykit.data.common.utils.SHA1Utils;
import io.mykit.data.parser.convert.AbstractHandler;

/**
 * SHA1加密
 */
public class Sha1Handler extends AbstractHandler {

    @Override
    protected Object convert(String args, Object value) {
        return SHA1Utils.b64_sha1(String.valueOf(value));
    }
}