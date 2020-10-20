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
package io.mykit.data.storage.utils;

import io.mykit.data.storage.constants.ConfigConstants;
import org.apache.lucene.document.*;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 * <b>索引维护工具类</b>
 * <p/>1、使用方法：
 * <p/> new IntPoint(name, value); 存放int类型
 * <p/> new StoredField(name, value); 要存储值，必须添加一个同名的StoredField
 * <p/> new NumericDocValuesField(name, value); 要排序，必须添加一个同名的SortedNumericDocValuesField
 * <p/> 其他FloatPoint、LongPoint、DoublePoint同上
 * <p/> id使用字符串，防止更新失败
 * <p>
 * <p/>2、Field：
 * <p/>IntPoint
 * <p/>FloatPoint
 * <p/>LongPoint
 * <p/>DoublePoint
 * <p/>BinaryPoint
 * <p/>StringField 索引不分词，所有的字符串会作为一个整体进行索引，例如通常用于country或id等
 * <p/>TextField 索引并分词，不包括term vectors，例如通常用于一个body Field
 * <p/>StoredField 存储Field的值，可以用 IndexSearcher.doc和IndexReader.document来获取存储的Field和存储的值
 * <p/>SortedDocValuesField 存储String、Text类型排序
 * <p/>NumericDocValuesField 存储Int、Long类型索引并排序，用于评分、排序和值检索
 * <p/>FloatDocValuesField 存储Float类型索引并排序
 * <p/>DoubleDocValuesField 存储Double类型索引并排序
 * <p/>BinaryDocValuesField 只存储不共享，例如标题类字段，如果需要共享并排序，推荐使用SortedDocValuesField
 * <p>
 * <p/>3、Lucene 6.0版本后：
 * <p>IntField 替换为 IntPoint</p>
 * <p>FloatField 替换为 FloatPoint</p>
 * <p>LongField 替换为 LongPoint</p>
 * <p>DoubleField 替换为 DoublePoint</p>
 */
public class ParamsUtils {
    public static Document convertParams2Doc(Map params) {
        Assert.notNull(params, "Params can not be null.");
        Document doc = new Document();
        String id = (String) params.get(ConfigConstants.CONFIG_MODEL_ID);
        String type = (String) params.get(ConfigConstants.CONFIG_MODEL_TYPE);
        String name = (String) params.get(ConfigConstants.CONFIG_MODEL_NAME);
        String json = (String) params.get(ConfigConstants.CONFIG_MODEL_JSON);
        Long createTime = (Long) params.get(ConfigConstants.CONFIG_MODEL_CREATE_TIME);
        Long updateTime = (Long) params.get(ConfigConstants.CONFIG_MODEL_UPDATE_TIME);

        doc.add(new StringField(ConfigConstants.CONFIG_MODEL_ID, id, Field.Store.YES));
        doc.add(new StringField(ConfigConstants.CONFIG_MODEL_TYPE, type, Field.Store.YES));
        doc.add(new TextField(ConfigConstants.CONFIG_MODEL_NAME, name, Field.Store.YES));
        doc.add(new StoredField(ConfigConstants.CONFIG_MODEL_JSON, json));
        // 创建时间(不需要存储)
        doc.add(new LongPoint(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        doc.add(new NumericDocValuesField(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        // 修改时间(不需要存储)
        doc.add(new LongPoint(ConfigConstants.CONFIG_MODEL_UPDATE_TIME, updateTime));
        doc.add(new NumericDocValuesField(ConfigConstants.CONFIG_MODEL_UPDATE_TIME, updateTime));
        return doc;
    }

    public static Document convertLog2Doc(Map params) {
        Assert.notNull(params, "Params can not be null.");
        Document doc = new Document();
        String id = (String) params.get(ConfigConstants.CONFIG_MODEL_ID);
        String type = (String) params.get(ConfigConstants.CONFIG_MODEL_TYPE);
        String json = (String) params.get(ConfigConstants.CONFIG_MODEL_JSON);
        Long createTime = (Long) params.get(ConfigConstants.CONFIG_MODEL_CREATE_TIME);

        doc.add(new StringField(ConfigConstants.CONFIG_MODEL_ID, id, Field.Store.YES));
        doc.add(new StringField(ConfigConstants.CONFIG_MODEL_TYPE, type, Field.Store.YES));
        // 日志信息
        doc.add(new TextField(ConfigConstants.CONFIG_MODEL_JSON, json, Field.Store.YES));
        // 创建时间
        doc.add(new LongPoint(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        doc.add(new StoredField(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        doc.add(new NumericDocValuesField(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        return doc;
    }

    public static Document convertData2Doc(Map params) {
        Assert.notNull(params, "Params can not be null.");
        Document doc = new Document();
        String id = (String) params.get(ConfigConstants.CONFIG_MODEL_ID);
        Boolean success = (Boolean) params.get(ConfigConstants.DATA_SUCCESS);
        String event = (String) params.get(ConfigConstants.DATA_EVENT);
        String error = (String) params.get(ConfigConstants.DATA_ERROR);
        String json = (String) params.get(ConfigConstants.CONFIG_MODEL_JSON);
        Long createTime = (Long) params.get(ConfigConstants.CONFIG_MODEL_CREATE_TIME);

        doc.add(new StringField(ConfigConstants.CONFIG_MODEL_ID, id, Field.Store.YES));
        doc.add(new StringField(ConfigConstants.DATA_SUCCESS, String.valueOf(success), Field.Store.YES));
        doc.add(new StringField(ConfigConstants.DATA_EVENT, event, Field.Store.YES));
        doc.add(new TextField(ConfigConstants.DATA_ERROR, error, Field.Store.YES));
        doc.add(new StoredField(ConfigConstants.CONFIG_MODEL_JSON, json));
        // 创建时间
        doc.add(new LongPoint(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        doc.add(new StoredField(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        doc.add(new NumericDocValuesField(ConfigConstants.CONFIG_MODEL_CREATE_TIME, createTime));
        return doc;
    }

}
