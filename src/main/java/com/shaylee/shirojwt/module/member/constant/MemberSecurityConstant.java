package com.shaylee.shirojwt.module.member.constant;

import com.shaylee.shirojwt.base.BaseConstant;

/**
 * Title: 会员登录注册 常量类
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class MemberSecurityConstant implements BaseConstant {

    /**
     * status字段常量
     */
    /** 发布 */
    public static final Integer STATUS_POSTED = 1;
    /** 编辑 */
    public static final Integer STATUS_EDIT = 2;
    /** 冻结 */
    public static final Integer STATUS_FREEZE = 3;
    /** 禁用 */
    public static final Integer STATUS_DELETE = 4;

    /**
     * 异常类型
     */
    /** 正常 */
    public static final Integer ABNORMAL_NORMAL = 1;
    /** 异常(打广告、宣传黄赌毒、挖客户、反政府、商业竞争关系等) */
    public static final Integer ABNORMAL_ILLEGAL_OPERATION = 2;

    /**
     * 会员编号生成相关常量
     */
    /** 会员编号长度(8位数) */
    public static final Integer MEMBER_NO_LENGTH = 8;
    /** 会员编号前缀 */
    public static final String MEMBER_NO_PREFIX = "";

    /**
     * 登录相关常量
     */
    /** 手机号登录默认区号 */
    public static final String LOGIN_DEFAULT_AREA_CODE = "86";

    /**
     * 会员登录类型
     */
    /** 手机号登录 */
    public static final Integer LOGIN_TYPE_PHONE = 1;
    /** 微信登录 */
    public static final Integer LOGIN_TYPE_WX = 2;
    /** QQ登录 */
    public static final Integer LOGIN_TYPE_QQ = 3;

    /**
     * 会员登录信息Redis缓存
     */
    /** 会员安全实体key(缓存结构 member_security field:{memberNo} value:{memberSecurity}) */
    public static final String REDIS_KEY_MEMBER_SECURITY = "member_security";
}
