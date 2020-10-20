package io.mykit.data.parser.model;

import io.mykit.data.common.utils.JsonUtils;
import io.mykit.data.connector.constants.ConnectorConstants;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public final class DataEvent {

    private String event;
    private Map<String, Object> before;
    private Map<String, Object> after;

    public DataEvent(String event, Map<String, Object> before, Map<String, Object> after) {
        this.event = event;
        this.before = before;
        this.after = after;
    }

    public Map<String, Object> getData() {
        return StringUtils.equals(ConnectorConstants.OPERTION_DELETE, event) ? before : after;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Map<String, Object> getBefore() {
        return before;
    }

    public void setBefore(Map<String, Object> before) {
        this.before = before;
    }

    public Map<String, Object> getAfter() {
        return after;
    }

    public void setAfter(Map<String, Object> after) {
        this.after = after;
    }

    @Override
    public String toString() {
        return JsonUtils.objToJson(this);
    }

}