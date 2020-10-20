package io.mykit.data.parser.enums;

import io.mykit.data.parser.ParserException;
import org.apache.commons.lang.StringUtils;

/**
 * 驱动同步方式枚举
 */
public enum ModelEnum {

    /**
     * 全量
     */
    FULL("full", "全量"),
    /**
     * 增量
     */
    INCREMENT("increment", "增量");

    private String code;
    private String name;

    ModelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ModelEnum getModelEnum(String code) throws ParserException {
        for (ModelEnum e : ModelEnum.values()) {
            if (StringUtils.equals(code, e.getCode())) {
                return e;
            }
        }
        throw new ParserException(String.format("Model code \"%s\" does not exist.", code));
    }

    public static boolean isFull(String model) {
        return StringUtils.equals(FULL.getCode(), model);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}