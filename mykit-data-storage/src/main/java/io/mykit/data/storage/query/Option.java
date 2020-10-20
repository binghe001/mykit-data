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
package io.mykit.data.storage.query;

import io.mykit.data.common.utils.CollectionUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author binghe
 * @version 1.0.0
 * @description 条件信息
 */
public class Option {

    private Query query;
    private Set<String> highLightKeys;
    private boolean enableHighLightSearch;
    private Highlighter highlighter = null;

    public Option() {
    }

    public Option(Query query) {
        this.query = query;
    }

    public Option(Query query, List<Param> params) {
        this.query = query;
        if (!CollectionUtils.isEmpty(params)) {
            this.highLightKeys = params.stream()
                    .filter(p -> p.isHighlighter())
                    .map(p -> p.getKey())
                    .collect(Collectors.toSet());
        }
        if (!CollectionUtils.isEmpty(highLightKeys)) {
            enableHighLightSearch = true;
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
            highlighter = new Highlighter(formatter, new QueryScorer(query));
        }
    }

    public Query getQuery() {
        return query;
    }

    public Set<String> getHighLightKeys() {
        return highLightKeys;
    }

    public boolean isEnableHighLightSearch() {
        return enableHighLightSearch;
    }

    public Highlighter getHighlighter() {
        return highlighter;
    }
}
