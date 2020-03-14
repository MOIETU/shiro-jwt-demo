package com.shaylee.shirojwt.security.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Title: 会员登录信息DTO
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2019-10-11
 */
@Getter
@Setter
public class MemberLoginDTO {

    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 密码原文
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 密码密文
     */
    private String cryptograph;

    public MemberLoginDTO() {
    }

    public MemberLoginDTO(String memberNo, String password, String salt) {
        this.memberNo = memberNo;
        this.password = password;
        this.salt = salt;
    }
}
