package com.shaylee.shirojwt.security.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Title: JwtToken 实体
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = -6543904206035209569L;

    /**
     * 令牌
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}