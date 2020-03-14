package com.shaylee.shirojwt.security.serivce;

import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.module.member.request.RegisterExtendRequest;
import com.shaylee.shirojwt.module.member.request.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 会员注册 服务
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface MemberRegisterService {

    /**
     * 注册会员(基础信息，手机+密码+会员号)
     *
     * @param request         HttpServletRequest
     * @param registerRequest 注册信息入参
     * @return 会员安全实体
     */
    MemberSecurity registerMember(HttpServletRequest request, RegisterRequest registerRequest);

    /**
     * 注册会员(详细信息)
     *
     * @param registerExtendReq 注册详情入参
     */
    void registerMemberExtend(RegisterExtendRequest registerExtendReq);
}
