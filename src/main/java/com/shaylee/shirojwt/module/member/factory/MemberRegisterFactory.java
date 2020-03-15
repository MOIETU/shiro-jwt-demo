package com.shaylee.shirojwt.module.member.factory;

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
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Title: 会员实体工厂
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public class MemberRegisterFactory {

    public static Member buildRegisterMember(RegisterRequest registerRequest, String memberNo) {
        Member member = new Member();
        Date currentDate = new Date();
        // 设置基础信息
        member.setAreaCode(registerRequest.getAreaCode());
        member.setPhone(registerRequest.getPhone());
        // 设置业务信息
        member.setNo(memberNo);
        member.setType(MemberConstant.TYPE_NORMAL);
        // 设置基础属性
        member.setCreateDate(currentDate);
        member.setUpdateDate(currentDate);
        member.setDelFlag(MemberConstant.DEL_FLAG_NORMAL);
        return member;
    }

    public static MemberSecurity buildRegisterMemberSecurity(HttpServletRequest request, RegisterRequest registerRequest, String memberNo) {
        MemberSecurity memberSecurity = new MemberSecurity();
        Date currentDate = new Date();
        String phone = registerRequest.getPhone();
        String password = registerRequest.getPassword();
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
        memberSecurity.setAreaCode(registerRequest.getAreaCode());
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

    public static Member buildRegisterMemberDetail(RegisterExtendRequest registerExtendRequest, Member member) {
        Integer gender = registerExtendRequest.getGender();
        member.setName(registerExtendRequest.getName());
        member.setGender(gender);
        member.setUpdateDate(new Date());
        return member;
    }

    public static MemberExtend buildRegisterMemberExtend(RegisterExtendRequest registerExtendRequest) {
        Date currentDate = new Date();
        MemberExtend memberExtend = new MemberExtend();
        BeanUtils.copyProperties(registerExtendRequest, memberExtend);
        memberExtend.setMemberId(registerExtendRequest.getMemberId());
        memberExtend.setCreateDate(currentDate);
        memberExtend.setUpdateDate(currentDate);
        memberExtend.setDelFlag(MemberConstant.DEL_FLAG_NORMAL);
        return memberExtend;
    }
}
