package io.mykit.data.parser.model;



/**
 * 字段转换
 */
public class Convert {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 转换名称
     *
     * @see com.example.sqlsynchronization.parser.enums.ConvertEnum
     */
    private String convertName;

    /**
     * 转换方式
     *
     * @see com.example.sqlsynchronization.parser.enums.ConvertEnum
     */
    private String convertCode;

    /**
     * 转换参数
     *
     * @see com.example.sqlsynchronization.parser.enums.ConvertEnum
     */
    private String args;

    public String getName() {
        return name;
    }

    public String getConvertName() {
        return convertName;
    }

    public String getConvertCode() {
        return convertCode;
    }

    public String getArgs() {
        return args;
    }
}
