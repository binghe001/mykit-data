package io.mykit.data.parser.utils;

import io.mykit.data.common.utils.JsonUtils;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.storage.constants.ConfigConstants;

import java.util.HashMap;
import java.util.Map;

public abstract class ConfigModelUtils {

    private ConfigModelUtils() {
    }

    public static Map<String, Object> convertModelToMap(ConfigModel model) {
        Map<String, Object> params = new HashMap();
        params.put(ConfigConstants.CONFIG_MODEL_ID, model.getId());
        params.put(ConfigConstants.CONFIG_MODEL_TYPE, model.getType());
        params.put(ConfigConstants.CONFIG_MODEL_NAME, model.getName());
        params.put(ConfigConstants.CONFIG_MODEL_CREATE_TIME, model.getCreateTime());
        params.put(ConfigConstants.CONFIG_MODEL_UPDATE_TIME, model.getUpdateTime());
        params.put(ConfigConstants.CONFIG_MODEL_JSON, JsonUtils.objToJson(model));
        return params;
    }

}