package com.shaylee.shirojwt.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title: 错误响应实体
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
public class ErrorResult implements Serializable {
    private static final long serialVersionUID = 6292065172737641507L;

    /**
     * 消息状态码
     */
    private int status;
    /**
     * 错误信息
     */
    private String error;
    /**
     * 消息错误码
     */
    private String errorCode;
    /**
     * 提示消息
     */
    private String message;

    public ErrorResult() {
        super();
    }

    public ErrorResult(int code, String msg) {
        super();
        this.status = code;
        this.message = msg;
    }

    public ErrorResult(int code, String errorCode, String msg) {
        super();
        this.status = code;
        this.errorCode = errorCode;
        this.message = msg;
    }
}
