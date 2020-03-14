package com.shaylee.shirojwt.module.member.exception;

/**
 * Title: 会员注册错误码
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * 如：11 01 08（11代表API模块(10代表通用接口模块，12为HOST模块)，01代表注册模块，01代表验证码错误）
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface RegisterErrorCode {
    /**
     * 会员短信验证码错误
     */
    String SMS_CAPTCH_ERROR = "110101";
    /**
     * 会员邀请码失效错误
     */
    String INVITE_CODE_INVALID = "110102";
    /**
     * 通用注册信息缺填错误
     */
    String REGISTER_INFO_ERROR = "110103";
    /**
     * 女士社交联系方式缺填错误
     */
    String FEMALE_SOCIAL_CONTACT_ERROR = "110104";

    /**
     * 手机区号或手机号为空 错误码
     */
    String AREA_CODE_PHONE_BLANK = "110105";
    /**
     * 手机区号和手机号重复 错误码
     */
    String AREA_CODE_PHONE_REPEAT = "110106";

    /**
     * 重设密码用户不存在 错误码
     */
    String RESET_PWD_MEMBER_NOT_EXIST = "110107";

    /**
     * 会员详细信息重复 错误码
     */
    String EXTEND_INFO_REPEAT = "110108";
}
