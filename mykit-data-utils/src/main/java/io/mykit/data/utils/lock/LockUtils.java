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
package io.mykit.data.utils.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe
 * @version 1.0.0
 * @description 加锁工具类
 */
public class LockUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockUtils.class);

    private static final Lock LOCK = new ReentrantLock();

    /**
     * 尝试加锁，默认等待3秒
     */
    public static boolean tryLock() {
        try {
            return LOCK.tryLock(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("加锁异常:{}", e);
            return false;
        }
    }

    /**
     * 取消加锁
     */
    public static void unlock() {
        LOCK.unlock();
    }
}
