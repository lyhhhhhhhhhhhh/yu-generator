package com.lyh.maker.meta;

/**
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-18-14:55
 **/

/**
 * 元信息异常
 */
public class MetaException extends RuntimeException{

    public MetaException(String message) {
        super(message);
    }


    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
