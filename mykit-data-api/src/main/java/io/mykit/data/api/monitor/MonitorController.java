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
package io.mykit.data.api.monitor;

import io.mykit.data.api.BaseController;
import io.mykit.data.business.service.MonitorService;
import io.mykit.data.business.vo.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监控API
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MonitorService monitorService;

    @RequestMapping("")
    public String index(HttpServletRequest request, ModelMap model) {
        Map<String, String> params = getParams(request);
        model.put("threadInfo", monitorService.getThreadInfo());
        model.put("metaId", monitorService.getDefaultMetaId(params));
        model.put("meta", monitorService.getMetaAll());
        model.put("data", monitorService.queryData(params));
        model.put("log", monitorService.queryLog(params));
        return "monitor/monitor.html";
    }

    @GetMapping("/queryData")
    @ResponseBody
    public RestResult queryData(HttpServletRequest request) {
        try {
            Map<String, String> params = getParams(request);
            return RestResult.restSuccess(monitorService.queryData(params));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e.getClass());
            return RestResult.restFail(e.getMessage());
        }
    }

    @GetMapping("/queryLog")
    @ResponseBody
    public RestResult queryLog(HttpServletRequest request) {
        try {
            Map<String, String> params = getParams(request);
            return RestResult.restSuccess(monitorService.queryLog(params));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e.getClass());
            return RestResult.restFail(e.getMessage());
        }
    }

    @PostMapping("/clearData")
    @ResponseBody
    public RestResult clearData(String id) {
        try {
            return RestResult.restSuccess(monitorService.clearData(id));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e.getClass());
            return RestResult.restFail(e.getMessage());
        }
    }

    @PostMapping("/clearLog")
    @ResponseBody
    public RestResult clearLog() {
        try {
            return RestResult.restSuccess(monitorService.clearLog());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e.getClass());
            return RestResult.restFail(e.getMessage());
        }
    }
}
