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
package io.mykit.data.manage.template.impl;

import io.mykit.data.cache.service.CacheService;
import io.mykit.data.manage.config.OperationCallBack;
import io.mykit.data.manage.config.OperationConfig;
import io.mykit.data.manage.config.QueryConfig;
import io.mykit.data.manage.enums.GroupStrategyEnum;
import io.mykit.data.manage.exception.ManagerException;
import io.mykit.data.manage.template.AbstractTemplate;
import io.mykit.data.manage.template.GroupStrategy;
import io.mykit.data.manage.template.Handler;
import io.mykit.data.parser.model.ConfigModel;
import io.mykit.data.parser.utils.ConfigModelUtils;
import io.mykit.data.storage.StorageService;
import io.mykit.data.storage.enums.StorageEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author binghe
 * @version 1.0.0
 * @description 操作配置模板
 */
@Component
public class OperationTemplate extends AbstractTemplate {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    @Autowired
    private CacheService cacheService;

    public <T> List<T> queryAll(QueryConfig<T> query) {
        ConfigModel model = query.getConfigModel();
        String groupId = getGroupId(model, getDefaultStrategy(query));
        Group group = cacheService.get(groupId, Group.class);
        if (null != group) {
            List<String> index = group.getAll();
            if (!CollectionUtils.isEmpty(index)) {
                List<T> list = new ArrayList<>();
                Class<? extends ConfigModel> clazz = model.getClass();
                index.forEach(e -> {
                    Object v = cacheService.get(e);
                    if (null != v) {
                        list.add((T) beanCopy(clazz, v));
                    }
                });
                return list;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public <T> T queryObject(Class<T> clazz, String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        Object o = cacheService.get(id, clazz);
        return beanCopy(clazz, o);
    }

    public String execute(OperationConfig config) {
        // 1、解析配置
        ConfigModel model = config.getModel();
        Assert.notNull(model, "ConfigModel can not be null.");

        // 2、持久化
        Map<String, Object> params = ConfigModelUtils.convertModelToMap(model);
        logger.debug("params:{}", params);
        Handler handler = config.getHandler();
        Assert.notNull(handler, "Handler can not be null.");
        handler.execute(new OperationCallBack(storageService, StorageEnum.CONFIG, params));

        // 3、缓存
        GroupStrategyEnum strategy = getDefaultStrategy(config);
        cache(model, strategy);
        return model.getId();
    }

    public void cache(ConfigModel model, GroupStrategyEnum strategy) {
        // 1、缓存
        Assert.notNull(model, "ConfigModel can not be null.");
        String id = model.getId();
        cacheService.put(id, model);

        // 2、分组
        String groupId = getGroupId(model, strategy);
        cacheService.putIfAbsent(groupId, new Group());
        Group group = cacheService.get(groupId, Group.class);
        group.addIfAbsent(id);
        logger.debug("Put the model [{}] for {} group into cache.", id, groupId);
    }

    public void remove(OperationConfig config) {
        String id = config.getId();
        Assert.hasText(id, "ID can not be empty.");
        // 删除分组
        ConfigModel model = cacheService.get(id, ConfigModel.class);
        String groupId = getGroupId(model, getDefaultStrategy(config));
        Group group = cacheService.get(groupId, Group.class);
        if (null != group) {
            group.remove(id);
            if (0 >= group.size()) {
                cacheService.remove(groupId);
            }
        }
        cacheService.remove(id);
        storageService.remove(StorageEnum.CONFIG, id);
    }

    private String getGroupId(ConfigModel model, GroupStrategyEnum strategy) {
        Assert.notNull(model, "ConfigModel can not be null.");
        Assert.notNull(strategy, "GroupStrategyEnum can not be null.");
        GroupStrategy groupStrategy = strategy.getGroupStrategy();
        Assert.notNull(groupStrategy, "GroupStrategy can not be null.");

        String groupId = groupStrategy.getGroupId(model);
        Assert.hasText(groupId, "GroupId can not be empty.");
        return groupId;
    }

    private <T> T beanCopy(Class<T> clazz, Object o) {
        if (null == o || null == clazz) {
            return null;
        }
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(o, t);
            return t;
        } catch (InstantiationException e) {
            throw new ManagerException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new ManagerException(e.getMessage());
        }
    }

    class Group {

        private List<String> index;

        public Group() {
            this.index = new LinkedList<>();
        }

        public synchronized void addIfAbsent(String e) {
            if (!index.contains(e)) {
                index.add(e);
            }
        }

        public synchronized void remove(String e) {
            index.remove(e);
        }

        public List<String> subList(int fromIndex, int toIndex) {
            return index.subList(fromIndex, toIndex);
        }

        public int size() {
            return index.size();
        }

        public List<String> getAll() {
            return Collections.unmodifiableList(index);
        }

    }
}
