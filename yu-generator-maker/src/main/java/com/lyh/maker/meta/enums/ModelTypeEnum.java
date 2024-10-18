package com.lyh.maker.meta.enums;

/**
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-18-16:12
 **/

public enum ModelTypeEnum {

    STRING("字符串","Sting"),

    BOOLEAN("布尔值","boolean");

    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value){
        this.text = text;
        this.value = value;
    }


    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
