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
package io.mykit.data.business.vo;

import io.mykit.data.parser.model.Mapping;

/**
 * @author binghe
 * @version 1.0.0
 * @description 关系映射
 */
public class MappingVo extends Mapping {

    // 连接器
    private ConnectorVo sourceConnector;
    private ConnectorVo targetConnector;

    // 元信息
    private MetaVo meta;

    public MappingVo(ConnectorVo sourceConnector, ConnectorVo targetConnector, MetaVo meta) {
        this.sourceConnector = sourceConnector;
        this.targetConnector = targetConnector;
        this.meta = meta;
    }

    public ConnectorVo getSourceConnector() {
        return sourceConnector;
    }

    public ConnectorVo getTargetConnector() {
        return targetConnector;
    }

    public MetaVo getMeta() {
        return meta;
    }
}
