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
package io.mykit.data.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author binghe
 * @version 1.0.0
 * @description SHA1算法工具类
 */
public class SHA1Utils {

    private static MessageDigest instance;

    static {
        try {
            instance = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("restriction")
    public static String b64_sha1(String s) {
        if (null == s || "" == s.trim()) {
            return null;
        }
        if (null == instance) {
            return null;
        }
        byte[] sha1 = instance.digest(s.getBytes());
        if (null == sha1) {
            return null;
        }
        // base64加密
        return java.util.Base64.getEncoder().encodeToString(sha1);
    }
}
