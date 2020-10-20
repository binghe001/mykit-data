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
package io.mykit.data.api.index;

import io.mykit.data.business.service.MonitorService;
import io.mykit.data.business.vo.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author binghe
 * @version 1.0.0
 * @description 元数据
 */
@Controller
@RequestMapping("/meta")
public class MetaController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/getAll")
    @ResponseBody
    public RestResult getAll() {
        try {
            return RestResult.restSuccess(monitorService.getMetaAll());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e.getClass());
            return RestResult.restFail(e.getMessage());
        }
    }

}
