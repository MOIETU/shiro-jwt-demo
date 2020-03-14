package com.shaylee.shirojwt.module.member.constant;

/**
 * Title: 会员登录日志 常量
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class MemberLoginLogConstant {

    /**
     * operation字段
     */
    /**
     * 登录操作
     */
    public static final Integer OPERATION_LOGIN = 1;
    /**
     * 登出操作
     */
    public static final Integer OPERATION_LOGOUT = 2;

    /**
     * status字段
     */
    /**
     * 失败
     */
    public static final Integer STATUS_ERROR = 0;
    /**
     * 成功
     */
    public static final Integer STATUS_SUCCESS = 1;
    /**
     * 账号锁定
     */
    public static final Integer STATUS_LOCK = 2;


}
