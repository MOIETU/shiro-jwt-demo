package com.shaylee.shirojwt.module.member.factory;

import com.google.common.base.Joiner;
import com.shaylee.shirojwt.module.member.constant.MemberConstant;
import com.shaylee.shirojwt.module.member.constant.MemberSecurityConstant;
import com.shaylee.shirojwt.module.member.entity.Member;
import com.shaylee.shirojwt.module.member.entity.MemberExtend;
import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.module.member.request.RegisterExtendRequest;
import com.shaylee.shirojwt.module.member.request.RegisterRequest;
import com.shaylee.shirojwt.security.model.MemberLoginDTO;
import com.shaylee.shirojwt.security.utils.PasswordUtils;
import com.shaylee.shirojwt.utils.useragent.UserAgentUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title: 会员实体工厂
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class MemberRegisterFactory {

    public static Member buildRegisterMember(RegisterRequest registerReq, String memberNo) {
        Member member = new Member();
        Date currentDate = new Date();
        // 设置基础信息
        member.setAreaCode(registerReq.getAreaCode());
        member.setPhone(registerReq.getPhone());
        // 设置业务信息
        member.setNo(memberNo);
        member.setType(MemberConstant.TYPE_NORMAL);
        // 设置基础属性
        member.setCreateDate(currentDate);
        member.setUpdateDate(currentDate);
        member.setDelFlag(MemberConstant.DEL_FLAG_NORMAL);
        return member;
    }

    public static MemberSecurity buildRegisterMemberSecurity(HttpServletRequest request, RegisterRequest registerReq, String memberNo) {
        MemberSecurity memberSecurity = new MemberSecurity();
        Date currentDate = new Date();
        String phone = registerReq.getPhone();
        String password = registerReq.getPassword();
        String salt = PasswordUtils.randomSalt();
        String ipAddr = UserAgentUtils.getIpAddr(request);
        // 密码加密
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO(memberNo, password, salt);
        String cryptograph = PasswordUtils.encode(memberLoginDTO);
        // 设置Security属性
        memberSecurity.setMemberNo(memberNo);
        memberSecurity.setPhone(phone);
        memberSecurity.setSalt(salt);
        memberSecurity.setPassword(cryptograph);
        memberSecurity.setOnline(MemberConstant.OFFLINE);
        memberSecurity.setAreaCode(registerReq.getAreaCode());
        memberSecurity.setStatus(MemberSecurityConstant.STATUS_EDIT);
        memberSecurity.setAbnormal(MemberSecurityConstant.ABNORMAL_NORMAL);
        // 设置注册IP
        memberSecurity.setRegisterIp(ipAddr);
        memberSecurity.setRegisterDate(currentDate);
        // 设置基础属性
        memberSecurity.setCreateDate(currentDate);
        memberSecurity.setUpdateDate(currentDate);
        memberSecurity.setDelFlag(MemberConstant.DEL_FLAG_NORMAL);
        return memberSecurity;
    }

    public static MemberSecurity buildExtendMemberSecurity(MemberSecurity security) {
        MemberSecurity memberSecurity = new MemberSecurity();
        memberSecurity.setId(security.getId());
        memberSecurity.setStatus(MemberSecurityConstant.STATUS_POSTED);
        memberSecurity.setUpdateDate(new Date());
        return memberSecurity;
    }

    public static Member buildRegisterMemberDetail(RegisterExtendRequest registerReqExtend, Member member) {
        Integer gender = registerReqExtend.getGender();
        member.setName(registerReqExtend.getName());
        member.setGender(gender);
        member.setUpdateDate(new Date());
        return member;
    }

    public static MemberExtend buildRegisterMemberExtend(RegisterExtendRequest registerReqExtend) {
        Date currentDate = new Date();
        MemberExtend memberExtend = new MemberExtend();
        BeanUtils.copyProperties(registerReqExtend, memberExtend);
        memberExtend.setMemberId(registerReqExtend.getMemberId());
        memberExtend.setCreateDate(currentDate);
        memberExtend.setUpdateDate(currentDate);
        memberExtend.setDelFlag(MemberConstant.DEL_FLAG_NORMAL);
        return memberExtend;
    }
}
