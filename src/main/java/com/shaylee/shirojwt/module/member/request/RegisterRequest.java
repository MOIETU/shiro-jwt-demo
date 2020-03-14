package com.shaylee.shirojwt.module.member.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title: 注册基础信息入参VO
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = -6654578907237984923L;
    /**
     * 手机区号
     */
    private String areaCode;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 短信验证码
     */
    private String smsCaptcha;
    /**
     * 密码
     */
    private String password;
    /**
     * 语言(zh-CN zh-HK en-US)
     */
    private String language;
}
