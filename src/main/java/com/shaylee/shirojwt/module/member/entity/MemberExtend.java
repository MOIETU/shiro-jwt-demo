package com.shaylee.shirojwt.module.member.entity;

import com.shaylee.shirojwt.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会员信息扩展表 member_extend
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
@Table(name = "member_extend")
public class MemberExtend extends BaseEntity {
    private static final long serialVersionUID = -5458856484912052095L;

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
     * 国家ID
     */
    private String countryId;

    /**
     * 省份/州ID
     */
    private String provinceId;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 职业
     */
    private String career;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 体重
     */
    private Integer weight;

    /**
     * 自我介绍
     */
    private String introduction;

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
