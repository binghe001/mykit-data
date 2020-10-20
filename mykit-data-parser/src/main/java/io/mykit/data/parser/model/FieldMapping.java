package io.mykit.data.parser.model;


import io.mykit.data.connector.config.Field;

/**
 * 字段映射关系
 */
public class FieldMapping {

    private Field source;

    private Field target;

    public FieldMapping() {
    }

    public FieldMapping(Field source, Field target) {
        this.source = source;
        this.target = target;
    }

    public Field getSource() {
        return source;
    }

    public void setSource(Field source) {
        this.source = source;
    }

    public Field getTarget() {
        return target;
    }

    public void setTarget(Field target) {
        this.target = target;
    }
}