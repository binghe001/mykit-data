package io.mykit.data.parser.flush;


import io.mykit.data.common.utils.JsonUtils;
import io.mykit.data.storage.SnowflakeIdWorker;
import io.mykit.data.storage.StorageService;
import io.mykit.data.storage.constants.ConfigConstants;
import io.mykit.data.storage.enums.StorageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 持久化
 * <p>全量或增量数据</p>
 * <p>系统日志</p>
 */
@Component
public class FlushServiceImpl implements FlushService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void asyncWrite(String type, String error) {
        Map<String, Object> params = new HashMap();
        params.put(ConfigConstants.CONFIG_MODEL_ID, String.valueOf(snowflakeIdWorker.nextId()));
        params.put(ConfigConstants.CONFIG_MODEL_TYPE, type);
        params.put(ConfigConstants.CONFIG_MODEL_JSON, error);
        params.put(ConfigConstants.CONFIG_MODEL_CREATE_TIME, Instant.now().toEpochMilli());
        storageService.addLog(StorageEnum.LOG, params);
    }

    @Override
    public void asyncWrite(String metaId, String event, boolean success, List<Map<String, Object>> data, String error) {
        long now = Instant.now().toEpochMilli();
        List<Map> list = data.parallelStream().map(r -> {
            Map<String, Object> params = new HashMap();
            params.put(ConfigConstants.CONFIG_MODEL_ID, String.valueOf(snowflakeIdWorker.nextId()));
            params.put(ConfigConstants.DATA_SUCCESS, success);
            params.put(ConfigConstants.DATA_EVENT, event);
            params.put(ConfigConstants.DATA_ERROR, error);
            params.put(ConfigConstants.CONFIG_MODEL_JSON, JsonUtils.objToJson(r));
            params.put(ConfigConstants.CONFIG_MODEL_CREATE_TIME, now);
            return params;
        }).collect(Collectors.toList());
        storageService.addData(StorageEnum.DATA, metaId, list);
    }
}