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
package io.mykit.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author binghe
 * @version 1.0.0
 * @description 项目启动类
 */
@EnableAsync
@EnableScheduling
@EnableCaching
@SpringBootApplication(scanBasePackages = "io.mykit.data", exclude = DataSourceAutoConfiguration.class)
public class MykitDataStarter {

    public static void main(String[] args) {
        try{
            SpringApplication.run(MykitDataStarter.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
