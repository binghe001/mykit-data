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
package io.mykit.data.connector.exception;

/**
 * @author binghe
 * @version 1.0.0
 * @description 连接器异常类
 */
public class ConnectorException extends RuntimeException{
    private static final long serialVersionUID = -6937770738525831946L;
    public ConnectorException(String message) {
        super(message);
    }

    public ConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectorException(Throwable cause) {
        super(cause);
    }

    protected ConnectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
