package com.lyh.maker.meta.enums;

/**
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-18-16:12
 **/

public enum FileTypeEnum {

    DIR("目录","dir"),

    FILE("文件","file");

    private final String text;

    private final String value;

    FileTypeEnum(String text,String value){
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
