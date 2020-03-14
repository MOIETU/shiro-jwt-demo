package com.shaylee.shirojwt.security.serivce.impl;

import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.security.constant.SecurityConstant;
import com.shaylee.shirojwt.security.model.SignInfo;
import com.shaylee.shirojwt.security.serivce.MemberTokenService;
import com.shaylee.shirojwt.security.utils.JwtUtil;
import com.shaylee.shirojwt.utils.redis.service.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Title: 会员Token 服务实现
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Service("memberTokenService")
public class MemberTokenServiceImpl implements MemberTokenService {

    @Autowired
    private CacheService redisCache;

    @Override
    public Boolean isTokenValid(String token) {
        // token为空
        if (StringUtils.isBlank(token)) {
            return false;
        }
        // token解析memberNo为空
        String memberNo = JwtUtil.getMemberNo(token);
        if (memberNo == null) {
            return false;
        }
        // token是否有效
        String memberTokenKey = MessageFormat.format(SecurityConstant.REDIS_KEY_JWT, memberNo);
        String tokenCache = (String) redisCache.get(memberTokenKey);
        if (tokenCache == null) {
            return false;
        }
        return token.equals(tokenCache);
    }


    @Override
    public SignInfo refreshToken(MemberSecurity memberSecurity) {
        SignInfo signInfo = JwtUtil.sign(memberSecurity);
        // token信息写入缓存(毫秒失效)
        String memberTokenKey = MessageFormat.format(SecurityConstant.REDIS_KEY_JWT, memberSecurity.getMemberNo());
        // 删除旧token
        redisCache.delete(memberTokenKey);
        // 添加新token
        redisCache.set(memberTokenKey, signInfo.getToken(), signInfo.getExpireDate().getTime() - System.currentTimeMillis());
        return signInfo;
    }

    @Override
    public void destroyToken(String memberNo) {
        String memberTokenKey = MessageFormat.format(SecurityConstant.REDIS_KEY_JWT, memberNo);
        redisCache.delete(memberTokenKey);
    }
}
