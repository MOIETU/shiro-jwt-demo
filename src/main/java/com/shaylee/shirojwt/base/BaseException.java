package com.shaylee.shirojwt.base;

/**
 * Title: 异常基类
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1202275437782345906L;

    protected String code;

    public BaseException() {
        super();
    }

    public BaseException(String code) {
        super();
        this.code = code;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
