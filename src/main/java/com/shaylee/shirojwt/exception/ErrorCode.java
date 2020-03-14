package com.shaylee.shirojwt.exception;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * 如：110001（11代表APP模块，00代表系统层级错误码，01代表业务错误码类型）
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface ErrorCode {
    /**
     * code
     */
    Integer INTERNAL_SERVER_ERROR = 500;
    Integer UNAUTHORIZED = 401;
    Integer FORBIDDEN = 403;

    /**
     * errorCode
     */
    /** 基础错误码 */
    String BASE_ERROR_CODE = "110000";
    /** 接口入参通用异常 */
    String BASE_PARAMS_ERROR = "110001";
    /** 签名错误异常 */
    String SIGNATURE_ERROR = "110002";
    String PARAMS_GET_ERROR = "110003";
    /** 修改密码与原密码一致异常 */
    String SAME_PASSWORD_ERROR = "110004";
    /** 会员不存在异常 */
    String MEMBER_NOT_EXISTED = "110005";
    /** 密码错误异常 */
    String MEMBER_PASSWORD_ERROR = "110006";
    /** 验证码错误异常 */
    String CAPTCHA_ERROR = "110007";
}
