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
package io.mykit.data.spi.loader;

import io.mykit.data.spi.annotation.ExtNamed;
import io.mykit.data.spi.annotation.SPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author binghe
 * @version 1.0.0
 * @description SPI 类加载
 */
public class ExtensionLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionLoader.class);

    private static volatile Map<Class<?>, Object> extensionMap = new ConcurrentHashMap<>();

    private static volatile Map<Class<?>, List<?>> extensionListMap = new ConcurrentHashMap<>();

    private ExtensionLoader() {
    }

    public static <T> T getExtension(Class<T> clazz, String spiType) {
        T extension = (T) extensionMap.get(clazz);
        if (extension == null) {
            extension = newExtension(clazz, spiType);
            if (extension != null) {
                extensionMap.put(clazz, extension);
            }
        }
        return extension;
    }

    public static <T> List<T> getExtensionList(Class<T> clazz) {
        List<T> extensions = (List<T>) extensionListMap.get(clazz);
        if (extensions == null) {
            extensions = newExtensionList(clazz);
            if (!extensions.isEmpty()) {
                extensionListMap.put(clazz, extensions);
            }
        }
        return extensions;
    }

    public static <T> T newExtension(Class<T> clazz, String spiType) {
        if (StringUtils.isEmpty(spiType)){
            spiType = getDefaultSPI(clazz);
        }
        if (StringUtils.isEmpty(spiType)) {
            throw new RuntimeException(String.format("请配置 %s SPI默认实现", clazz.getName()));
        }
        LOGGER.debug("默认的SPI为===>>>" + spiType);
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        for (T service : serviceLoader) {
            if (service.getClass().isAnnotationPresent(ExtNamed.class)
                    && spiType.equalsIgnoreCase(getExNamed(service.getClass()))) {
                return service;
            }
        }
        return null;
    }

    private static <T> String getDefaultSPI(Class<T> clazz) {
        String spi = System.getProperty(clazz.getName());
        if (!StringUtils.isEmpty(spi)) {
            return spi;
        }
        if (clazz.isAnnotationPresent(SPI.class)) {
            SPI annotation = clazz.getAnnotation(SPI.class);
            return annotation.value();
        }
        return null;
    }

    private static <T> String getExNamed(Class<T> clazz) {
        if (clazz.isAnnotationPresent(ExtNamed.class)) {
            ExtNamed annotation = clazz.getAnnotation(ExtNamed.class);
            return annotation.value();
        }
        return null;
    }

    public static <T> List<T> newExtensionList(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        List<T>          extensions    = new ArrayList<>();
        for (T service : serviceLoader) {
            extensions.add(service);
        }
        return extensions;
    }
}
