package com.shaylee.shirojwt.security.serivce.impl;

import com.shaylee.shirojwt.module.member.constant.MemberSecurityConstant;
import com.shaylee.shirojwt.module.member.entity.Member;
import com.shaylee.shirojwt.module.member.entity.MemberExtend;
import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.module.member.factory.MemberRegisterFactory;
import com.shaylee.shirojwt.module.member.request.RegisterExtendRequest;
import com.shaylee.shirojwt.module.member.request.RegisterRequest;
import com.shaylee.shirojwt.module.member.service.MemberExtendService;
import com.shaylee.shirojwt.module.member.service.MemberSecurityService;
import com.shaylee.shirojwt.module.member.service.MemberService;
import com.shaylee.shirojwt.security.serivce.MemberRegisterService;
import com.shaylee.shirojwt.utils.random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 会员注册 服务实现
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Service
public class MemberRegisterServiceImpl implements MemberRegisterService {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberExtendService memberExtendService;
    @Autowired
    private MemberSecurityService memberSecurityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberSecurity registerMember(HttpServletRequest request, RegisterRequest registerReq) {
        String memberNo = this.generateMemberNo();
        Member member = MemberRegisterFactory.buildRegisterMember(registerReq, memberNo);
        MemberSecurity memberSecurity = MemberRegisterFactory.buildRegisterMemberSecurity(request, registerReq, memberNo);
        memberService.saveMember(member);
        memberSecurity.setMemberId(member.getId());
        memberSecurityService.saveMemberSecurity(memberSecurity);
        return memberSecurity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerMemberExtend(RegisterExtendRequest registerExtendRequest) {
        // 会员基础信息
        Member memberInfo = memberService.getMemberById(registerExtendRequest.getMemberId());
        Member member = MemberRegisterFactory.buildRegisterMemberDetail(registerExtendRequest, memberInfo);
        // 会员详细信息
        MemberExtend memberExtend = MemberRegisterFactory.buildRegisterMemberExtend(registerExtendRequest);
        // 会员状态
        MemberSecurity security = memberSecurityService.getMemberSecurityByMemberId(registerExtendRequest.getMemberId());
        MemberSecurity memberSecurity = MemberRegisterFactory.buildExtendMemberSecurity(security);
        memberService.updateMember(member);
        memberExtendService.saveMemberExtend(memberExtend);
        memberSecurityService.updateMemberSecurity(memberSecurity);
    }

    /**
     * 生成会员号
     * TODO 唯一性检测，生成算法优化
     */
    private String generateMemberNo() {
        // 生成8位纯数字会员号 10000000 ~ 99999999
        return RandomUtil.genRandomCode(MemberSecurityConstant.MEMBER_NO_PREFIX, MemberSecurityConstant.MEMBER_NO_LENGTH);
    }
}
