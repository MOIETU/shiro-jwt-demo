package com.shaylee.shirojwt.controller;

import com.shaylee.shirojwt.base.BaseController;
import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.module.member.request.RegisterExtendRequest;
import com.shaylee.shirojwt.module.member.request.RegisterRequest;
import com.shaylee.shirojwt.module.member.service.MemberSecurityService;
import com.shaylee.shirojwt.security.constant.SecurityConstant;
import com.shaylee.shirojwt.security.model.SignInfo;
import com.shaylee.shirojwt.security.serivce.MemberRegisterService;
import com.shaylee.shirojwt.security.serivce.MemberTokenService;
import com.shaylee.shirojwt.security.utils.JwtSecurityUtils;
import com.shaylee.shirojwt.utils.useragent.UserAgentUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: APP端会员注册 控制器
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@RestController
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Autowired
    private MemberRegisterService memberRegisterService;
    @Autowired
    private MemberSecurityService memberSecurityService;
    @Autowired
    private MemberTokenService memberTokenService;

    /**
     * 注册接口
     */
    @PostMapping
    public String register(@RequestBody RegisterRequest registerRequest,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 注册会员账号
        MemberSecurity memberSecurity = memberRegisterService.registerMember(request, registerRequest);
        // 会员注册成功，返回token
        SignInfo signInfo = memberTokenService.refreshToken(memberSecurity);
        // 刷新登录信息
        memberSecurityService.refreshLoginInfo(request, memberSecurity);
        // 将刷新的Token存放在Response的Header中的token字段返回
        response.setHeader(SecurityConstant.REQUEST_HEADER_TOKEN, signInfo.getToken());
        response.setHeader("Access-Control-Expose-Headers", SecurityConstant.REQUEST_HEADER_TOKEN);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("token", signInfo.getToken());
        resultMap.put("status", memberSecurity.getStatus());
        resultMap.put("expire", signInfo.getExpire());
        resultMap.put("expireDate", signInfo.getExpireDate());
        return super.responseSuccessJSON(resultMap);
    }

    /**
     * 会员完善注册信息接口
     */
    @PostMapping("/extend")
    @RequiresAuthentication
    public String registerExtend(@RequestBody RegisterExtendRequest registerExtendRequest, HttpServletRequest request) throws Exception {
        Long memberId = JwtSecurityUtils.getCurrentMemberId();
        registerExtendRequest.setMemberId(memberId);
        // 获取ip
        String ipAddr = UserAgentUtils.getIpAddr(request);
        registerExtendRequest.setIp(ipAddr);
        // 完善会员详细信息
        memberRegisterService.registerMemberExtend(registerExtendRequest);
        return super.responseSuccessJSON();
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody RegisterRequest registerRequest) throws Exception {
        String areaCode = registerRequest.getAreaCode();
        String phone = registerRequest.getPhone();
        String password = registerRequest.getPassword();
        memberSecurityService.resetMemberPassword(areaCode, phone, password);
        return super.responseSuccessJSON();
    }
}
