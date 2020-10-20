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
package io.mykit.data.connector.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author binghe
 * @version 1.0.0
 * @description ping网络工具类
 */
public class PingUtils {

    public static boolean ping(String ip, int port) {
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(ip, port));
            s.close();
            return true;
        } catch (IOException e) {
        }
        return false;
    }
}
