package com.shaylee.shirojwt.module.member.constant;

import com.shaylee.shirojwt.base.BaseConstant;

/**
 * Title: 会员相关常量
 * Project: shaylee-framework
 *
 * @author Adrian
 * @date 2020-03-01
 */
public class MemberConstant implements BaseConstant {

    /**
     * 数值型参数(默认值0)
     */
    public static final Integer INTEGER_DEFAULT_VALUE = 0;

    /**
     * type字段
     */
    /** 未认证 */
    public static final Integer TYPE_UNAUTH = 1;
    /** 真人 */
    public static final Integer TYPE_REAL_AUTH = 2;
    /** 女神 */
    public static final Integer TYPE_GODDESS_AUTH = 3;
    /** 认证撤销 */
    public static final Integer TYPE_AUTH_REVOKE = 4;

    /**
     * 会员认证中间状态(1默认 2真人认证审核中 3女神认证审核中 4真人认证不通过 5女神认证不通过)
     */
    /** 默认 */
    public static final Integer CERTIFY_TYPE_DEFAULT = 1;
    /** 真人认证审核中 */
    public static final Integer CERTIFY_TYPE_REAL_UNDER_REVIEW = 2;
    /** 女神认证审核中 */
    public static final Integer CERTIFY_TYPE_GODDESS_UNDER_REVIEW = 3;
    /** 真人认证不通过 */
    public static final Integer CERTIFY_TYPE_REAL_FAIL = 4;
    /** 女神认证不通过 */
    public static final Integer CERTIFY_TYPE_GODDESS_FAIL = 5;

    /**
     * 会员认证撤销前状态(2真人 3女神)
     */
    /** 会员认证撤销前状态：默认 */
    public static final Integer REVOKE_TYPE_DEFAULT = 1;
    /** 会员认证撤销前状态：真人 */
    public static final Integer REVOKE_TYPE_REAL_AUTH = 2;
    /** 会员认证撤销前状态：女神 */
    public static final Integer REVOKE_TYPE_GODDESS_AUTH = 2;

    /**
     * gender字段
     */
    /** 性别男 */
    public static final Integer GENDER_MALE = 1;
    /** 性别女 */
    public static final Integer GENDER_FEMALE = 2;

    /**
     * online字段
     */
    /** 离线状态 */
    public static final Integer OFFLINE = 0;
    /** 在线状态 */
    public static final Integer ONLINE = 1;

    /**
     * isRecharge字段
     */
    /** 非会员 */
    public static final Integer RECHARGE_NOT_PAID = 0;
    /** 会员 */
    public static final Integer RECHARGE_PAID = 1;
    
    /**
     * paymentStatus字段
     */
    /** 非付费 */
    public static final Integer PAYMENTSTATUS_NOT_PAID = 0;
    /** 付费 */
    public static final Integer PAYMENTSTATUS_PAID = 1;

    /**
     * areaCode字段
     */
    /** 大陆手机号 */
    public static final String AREACODE_MAINLAND = "86";

    /**
     * sourceType字段
     */
    /** 默认(无邀请码) */
    public static final String SOURCE_TYPE_DEFAULT = "1";
    /** 好友邀请 */
    public static final String SOURCE_TYPE_SYSTEM = "2";
    /** 推广邀请 */
    public static final String SOURCE_TYPE_PROMOTION = "3";

    /**
     * 会员修改个人资料类型
     */
    /** 男会员修改资料 */
    public static final String MALE_PROFILE_COUNT = "male_profile_count";
    /** 女会员修改资料 */
    public static final String FEMALE_PROFILE_COUNT = "female_profile_count";

    /**
     * 会员修改资料业务ID
     */
    /** 员修改资料 */
    public static final String CHANGE_PROFILE_COUNT = "change_profile_count";

    /**
     * 认证状态变更方式
     */
    /** 认证状态变更方式：1系统 */
    public static final Integer CERTIFY_PATTERN_SYSTEM = 1;
    /** 认证状态变更方式：2人工 */
    public static final Integer CERTIFY_PATTERN_ARTIFICIAL = 2;

    /**
     * 会员语言KEY
     */
    public static final String MEMBER_LANGUAGE_KEY = "member_language_key";

    /**
     * 会员信息缓存KEY，结构 member_profile:{memberId} -> MemberProfileCacheVO
     */
    public static final String MEMBER_PROFILE_CACHE_KEY = "member_profile:{0}";
}
