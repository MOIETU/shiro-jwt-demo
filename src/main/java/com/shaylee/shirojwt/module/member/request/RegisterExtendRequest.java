package com.shaylee.shirojwt.module.member.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Title: 注册详细信息入参VO
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
public class RegisterExtendRequest {
    private static final long serialVersionUID = 8225781888787899547L;
    /**
     * 会员ID
     */
    private Long memberId;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别(1男 2女)
     */
    private Integer gender;
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
     * 注册ip
     */
    private String ip;
}
