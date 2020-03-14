package com.shaylee.shirojwt.module.member.entity;

import com.shaylee.shirojwt.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会员表 member (Tips: 额外添加的不作为表字段使用的字段，必须加上@Transient注解)
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
@Table(name = "member")
public class Member extends BaseEntity {
    private static final long serialVersionUID = 8962891323268352733L;

    /**
     * 数据ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 会员号唯一标识
     */
    private String no;

    /**
     * 昵称
     */
    private String name;

    /**
     * 会员类型(0普通 1VIP)
     */
    private Integer type;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机区域编号
     */
    private String areaCode;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别(1男 2女)
     */
    private Integer gender;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 删除标志(0正常 1删除)
     */
    private Integer delFlag;
}
