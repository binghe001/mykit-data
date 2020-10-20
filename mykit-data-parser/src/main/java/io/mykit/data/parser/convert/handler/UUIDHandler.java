package io.mykit.data.parser.convert.handler;

import io.mykit.data.common.utils.UUIDUtils;
import io.mykit.data.parser.convert.Handler;


/**
 * UUID
 */
public class UUIDHandler implements Handler {

    @Override
    public Object handle(String args, Object value) {
        return UUIDUtils.getUUID();
    }
}