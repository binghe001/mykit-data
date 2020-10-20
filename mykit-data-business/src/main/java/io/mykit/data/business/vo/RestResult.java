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

import java.io.Serializable;

/**
 * @author binghe
 * @version 1.0.0
 * @description Rest请求响应对象
 */
public class RestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 请求成功后返回的结果数据
     */
    private Object resultValue;

    /**
     * 状态码
     */
    private int status;

    /**
     * 请求失败返回提示信息
     *
     * @param resultValue
     * @return RestResult
     */
    public static RestResult restFail(Object resultValue) {
        return new RestResult(false, resultValue);
    }

    /**
     * 请求失败返回提示信息
     *
     * @param resultValue
     * @param status      状态码
     * @return RestResult
     */
    public static RestResult restFail(Object resultValue, int status) {
        return new RestResult(false, resultValue, status);
    }

    /**
     * 请求成功返回结果数据
     *
     * @param resultValue
     * @return RestResult
     */
    public static RestResult restSuccess(Object resultValue) {
        return new RestResult(true, resultValue, 200);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResultValue() {
        return resultValue;
    }

    public void setResultValue(Object resultValue) {
        this.resultValue = resultValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RestResult(boolean success, Object resultValue) {
        super();
        this.success = success;
        this.resultValue = resultValue;
    }

    public RestResult(boolean success, Object resultValue, int status) {
        super();
        this.success = success;
        this.resultValue = resultValue;
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("RestResult [success=").append(success).append(", resultValue=")
                .append(resultValue).append(", status=").append(status).append("]").toString();
    }
}
