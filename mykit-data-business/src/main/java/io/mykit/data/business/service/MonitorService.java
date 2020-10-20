/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.data.business.service;

import io.mykit.data.business.vo.DataVo;
import io.mykit.data.business.vo.LogVo;
import io.mykit.data.business.vo.MetaVo;

import java.util.List;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监控服务
 */
public interface MonitorService {

    /**
     * 获取线程信息
     *
     * @return
     */
    Map getThreadInfo();

    /**
     * 获取驱动元信息列表
     *
     * @return
     */
    List<MetaVo> getMetaAll();

    /**
     * 获取驱动默认元信息id
     *
     * @param params
     * @return
     */
    String getDefaultMetaId(Map<String, String> params);

    /**
     * 查询驱动同步数据
     *
     * @param params
     * @return
     */
    List<DataVo> queryData(Map<String, String> params);

    /**
     * 清空驱动同步数据
     *
     * @param id
     * @return
     */
    String clearData(String id);

    /**
     * 查询操作日志
     *
     * @param params
     * @return
     */
    List<LogVo> queryLog(Map<String, String> params);

    /**
     * 清空操作日志
     *
     * @return
     */
    String clearLog();
}
