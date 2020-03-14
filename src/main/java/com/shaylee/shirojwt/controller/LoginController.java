package com.shaylee.shirojwt.controller;

import com.shaylee.shirojwt.base.BaseController;
import com.shaylee.shirojwt.base.BaseException;
import com.shaylee.shirojwt.exception.ErrorCode;
import com.shaylee.shirojwt.module.member.constant.MemberLoginLogConstant;
import com.shaylee.shirojwt.module.member.constant.MemberSecurityConstant;
import com.shaylee.shirojwt.module.member.entity.MemberLoginLog;
import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.module.member.service.MemberLoginLogService;
import com.shaylee.shirojwt.module.member.service.MemberSecurityService;
import com.shaylee.shirojwt.module.member.service.MemberService;
import com.shaylee.shirojwt.security.constant.SecurityConstant;
import com.shaylee.shirojwt.security.model.SignInfo;
import com.shaylee.shirojwt.security.serivce.MemberTokenService;
import com.shaylee.shirojwt.security.utils.JwtSecurityUtils;
import com.shaylee.shirojwt.security.utils.PasswordUtils;
import com.shaylee.shirojwt.utils.useragent.UserAgentUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: APP端会员登陆 控制器
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@RestController
public class LoginController extends BaseController {

    @Autowired
    private MemberSecurityService memberSecurityService;
    @Autowired
    private MemberLoginLogService memberLoginLogService;
    @Autowired
    private MemberTokenService memberTokenService;
    @Autowired
    private MemberService memberService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("areaCode") String areaCode,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO 目前只支持手机登录，username为手机号
        MemberSecurity memberSecurity = memberSecurityService.getMemberSecurityByPhone(areaCode, username);

        // 登录信息
        MemberLoginLog memberLoginLog = new MemberLoginLog();
        memberLoginLog.setLoginType(MemberSecurityConstant.LOGIN_TYPE_PHONE);
        memberLoginLog.setLoginName(username);
        memberLoginLog.setAreaCode(areaCode);
        memberLoginLog.setOperation(MemberLoginLogConstant.OPERATION_LOGIN);
        memberLoginLog.setOperateIp(UserAgentUtils.getIpAddr(request));
        memberLoginLog.setCreateDate(new Date());

        // 会员不存在
        if (memberSecurity == null) {
            memberLoginLog.setStatus(MemberLoginLogConstant.STATUS_ERROR);
            memberLoginLog.setPhone(username);
            memberLoginLogService.saveMemberLoginLog(memberLoginLog);
            // member didn't existed!
            throw new BaseException(ErrorCode.MEMBER_NOT_EXISTED);
        }

        // 密码错误
        if (!PasswordUtils.matches(password, memberSecurity)) {
            memberLoginLog.setStatus(MemberLoginLogConstant.STATUS_ERROR);
            memberLoginLog.setMemberId(memberSecurity.getMemberId());
            memberLoginLog.setMemberNo(memberSecurity.getMemberNo());
            memberLoginLog.setPhone(memberSecurity.getPhone());
            memberLoginLogService.saveMemberLoginLog(memberLoginLog);
            // password error
            throw new BaseException(ErrorCode.MEMBER_PASSWORD_ERROR);
        }

        // 登录成功
        memberLoginLog.setPhone(memberSecurity.getPhone());
        memberLoginLog.setStatus(MemberLoginLogConstant.STATUS_SUCCESS);
        memberLoginLog.setMemberId(memberSecurity.getMemberId());
        memberLoginLog.setMemberNo(memberSecurity.getMemberNo());
        memberLoginLogService.saveMemberLoginLog(memberLoginLog);

        // 获取token
        SignInfo signInfo = memberTokenService.refreshToken(memberSecurity);
        memberSecurityService.refreshLoginInfo(request, memberSecurity);

        // 将刷新的Token存放在Response的Header中的token字段返回
        response.setHeader(SecurityConstant.REQUEST_HEADER_TOKEN, signInfo.getToken());
        response.setHeader("Access-Control-Expose-Headers", SecurityConstant.REQUEST_HEADER_TOKEN);
        
        // 添加token信息到响应数据
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("token", signInfo.getToken());
        resultMap.put("status", memberSecurity.getStatus());
        resultMap.put("expire", signInfo.getExpire());
        resultMap.put("expireDate", signInfo.getExpireDate());
        return super.responseSuccessJSON(resultMap);
    }

    /**
     * 退出登录接口
     */
    @GetMapping("/logout")
    @RequiresAuthentication
    public String logout(HttpServletRequest request) throws Exception {
        Long memberId = JwtSecurityUtils.getCurrentMemberId();
        String memberNo = JwtSecurityUtils.getCurrentMemberNo();
        memberTokenService.destroyToken(memberNo);
        // 退登信息
        MemberSecurity memberSecurity = memberSecurityService.getMemberSecurityByMemberId(memberId);
        MemberLoginLog memberLoginLog = new MemberLoginLog();
        memberLoginLog.setOperation(MemberLoginLogConstant.OPERATION_LOGOUT);
        memberLoginLog.setOperateIp(UserAgentUtils.getIpAddr(request));
        memberLoginLog.setCreateDate(new Date());
        memberLoginLog.setStatus(MemberLoginLogConstant.STATUS_SUCCESS);
        memberLoginLog.setMemberId(memberId);
        memberLoginLog.setMemberNo(memberNo);
        memberLoginLog.setLoginName(memberSecurity.getPhone());
        memberLoginLog.setAreaCode(memberSecurity.getAreaCode());
        memberLoginLog.setDeviceType(memberSecurity.getPayDeviceType());
        memberLoginLogService.saveMemberLoginLog(memberLoginLog);
        return super.responseSuccessJSON();
    }
}
