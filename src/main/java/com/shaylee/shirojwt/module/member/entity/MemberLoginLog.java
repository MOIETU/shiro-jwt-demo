package com.shaylee.shirojwt.module.member.entity;

import com.shaylee.shirojwt.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会员登录日志表 member_login_log
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
@Table(name = "member_login_log")
public class MemberLoginLog extends BaseEntity {
    private static final long serialVersionUID = 2135646956839586861L;

    /**
     * 数据ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 登录类型枚举
     */
    private Integer loginType;
    
    /**
     * 手机区域编号
     */
    private String areaCode;

    /**
     * 手机号码
     */
    private String phone;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户操作(0登录 1退出)
     */
    private Integer operation;

    /**
     * 用户操作IP
     */
    private String operateIp;
    /**
     * 登陆系统类型
     */
    private Integer deviceType;
    
    /**
     * 状态(0失败 1成功 2账号锁定)
     */
    private Integer status;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建日期
     */
    private Date createDate;
}
