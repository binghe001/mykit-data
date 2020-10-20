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
package io.mykit.data.api;

import io.mykit.data.business.service.ConditionService;
import io.mykit.data.business.service.ConvertService;
import io.mykit.data.business.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description 基础控制类
 */
@Controller
@RequestMapping("/")
public abstract class BaseController {

    @Autowired
    private ConditionService filterService;

    @Autowired
    private ConvertService convertService;

    @Autowired
    private PluginService pluginService;

    /**
     * 获取请求参数
     *
     * @param request
     * @return
     */
    protected Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> res = new HashMap<>();
        map.forEach((k, v) -> res.put(k, v[0]));
        return res;
    }

    /**
     * 初始化: 条件/转换/插件
     *
     * @param model
     */
    protected void initConfig(ModelMap model) {
        model.put("condition", filterService.getCondition());
        model.put("convert", convertService.getConvertEnumAll());
        model.put("plugin", pluginService.getPluginAll());
    }

}
