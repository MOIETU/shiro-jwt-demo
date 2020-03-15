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
     * type字段
     */
    /** 普通会员 */
    public static final Integer TYPE_NORMAL = 1;
    /** VIP会员 */
    public static final Integer TYPE_VIP = 1;

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
}
