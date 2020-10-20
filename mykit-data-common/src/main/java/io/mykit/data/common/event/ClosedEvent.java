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
package io.mykit.data.common.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author binghe
 * @version 1.0.0
 * @description 任务关闭事件
 */
public class ClosedEvent extends ApplicationContextEvent {

    private String id;

    /**
     * Create a new ContextStartedEvent.
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public ClosedEvent(ApplicationContext source, String id) {
        super(source);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
