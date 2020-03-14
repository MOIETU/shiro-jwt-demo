package com.shaylee.shirojwt.security.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Title: JWT签名信息
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Getter
@Setter
public class SignInfo {

    /**
     * 令牌
     */
    private String token;
    /**
     * 失效日期
     */
    private Date expireDate;
    /**
     * 失效时长(毫秒)
     */
    private Long expire;
}
