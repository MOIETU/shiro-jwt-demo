package com.shaylee.shirojwt.module.member.service.impl;

import com.shaylee.shirojwt.base.BaseException;
import com.shaylee.shirojwt.module.member.constant.MemberSecurityConstant;
import com.shaylee.shirojwt.module.member.dao.MemberSecurityDao;
import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import com.shaylee.shirojwt.module.member.exception.RegisterErrorCode;
import com.shaylee.shirojwt.module.member.service.MemberSecurityService;
import com.shaylee.shirojwt.security.model.MemberLoginDTO;
import com.shaylee.shirojwt.security.utils.PasswordUtils;
import com.shaylee.shirojwt.utils.redis.service.CacheService;
import com.shaylee.shirojwt.utils.useragent.UserAgentUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 会员登录 服务层实现
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Service("memberSecurityService")
public class MemberSecurityServiceImpl implements MemberSecurityService {

    @Autowired
    private MemberSecurityDao memberSecurityDao;
    @Autowired
    private CacheService cacheService;

    @Override
    public MemberSecurity getMemberSecurityCache(String memberNo) {
        Object memberSecurityCache = cacheService.hGet(MemberSecurityConstant.REDIS_KEY_MEMBER_SECURITY, memberNo);
        MemberSecurity memberSecurity;
        if (memberSecurityCache instanceof MemberSecurity) {
            memberSecurity = (MemberSecurity) memberSecurityCache;
        } else {
            // 缓存找不到，刷新缓存
            memberSecurity = this.refreshMemberSecurityCache(memberNo);
        }
        return memberSecurity;
    }

    @Override
    public MemberSecurity refreshMemberSecurityCache(String memberNo) {
        MemberSecurity memberSecurity = this.getMemberSecurityByMemberNo(memberNo);
        cacheService.hSet(MemberSecurityConstant.REDIS_KEY_MEMBER_SECURITY, memberNo, memberSecurity);
        return memberSecurity;
    }

    @Override
    public MemberSecurity getMemberSecurityByMemberNo(String memberNo) {
        MemberSecurity memberSecurity = new MemberSecurity();
        memberSecurity.setMemberNo(memberNo);
        memberSecurity.setDelFlag(MemberSecurityConstant.DEL_FLAG_NORMAL);
        return memberSecurityDao.selectOne(memberSecurity);
    }

    @Override
    public MemberSecurity getMemberSecurityByPhone(String areaCode, String phone) {
        MemberSecurity memberSecurity = new MemberSecurity();
        memberSecurity.setAreaCode(areaCode);
        memberSecurity.setPhone(phone);
        memberSecurity.setDelFlag(MemberSecurityConstant.DEL_FLAG_NORMAL);
        return memberSecurityDao.selectOne(memberSecurity);
    }

    @Override
    public MemberSecurity getMemberSecurityByMemberId(Long id) {
        MemberSecurity memberSecurity = new MemberSecurity();
        memberSecurity.setMemberId(id);
        memberSecurity.setDelFlag(MemberSecurityConstant.DEL_FLAG_NORMAL);
        return memberSecurityDao.selectOne(memberSecurity);
    }

    @Override
    public void refreshLoginInfo(HttpServletRequest request, MemberSecurity memberSecurity) {
        UserAgent userAgent = UserAgentUtils.parseUserAgent(request);
        String ip = UserAgentUtils.getIpAddr(request);
        String os = userAgent.getOperatingSystem().getName();
        Date currentDate = new Date();
        MemberSecurity memberSecurityParam = new MemberSecurity();
        memberSecurityParam.setLastLoginIp(ip);
        memberSecurityParam.setLastLoginOs(os);
        memberSecurityParam.setLastLoginDate(currentDate);
        memberSecurityParam.setUpdateDate(currentDate);
        memberSecurityParam.setPayDeviceType(memberSecurity.getPayDeviceType());
        memberSecurityParam.setId(memberSecurity.getId());
        memberSecurityDao.updateByPrimaryKeySelective(memberSecurityParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetMemberPassword(String areaCode, String phone, String password) {
        MemberSecurity memberSecurity = this.getMemberSecurityByPhone(areaCode, phone);
        if (memberSecurity == null) {
            throw new BaseException(RegisterErrorCode.RESET_PWD_MEMBER_NOT_EXIST);
        }
        Date currentDate = new Date();
        String salt = PasswordUtils.randomSalt();
        String memberNo = memberSecurity.getMemberNo();
        // 密码加密
        MemberLoginDTO memberLoginVO = new MemberLoginDTO(memberNo, password, salt);
        String cryptograph = PasswordUtils.encode(memberLoginVO);
        // 设置Security属性
        memberSecurity.setSalt(salt);
        memberSecurity.setPassword(cryptograph);
        memberSecurity.setUpdateDate(currentDate);
        memberSecurityDao.updateByPrimaryKeySelective(memberSecurity);
        // 删除缓存(用户更新密码成功，再将旧密码的缓存删除)
        cacheService.hDel(MemberSecurityConstant.REDIS_KEY_MEMBER_SECURITY, memberNo);
    }

    @Override
    public void saveMemberSecurity(MemberSecurity memberSecurity) {
        memberSecurityDao.insertSelective(memberSecurity);
    }

    @Override
    public void updateMemberSecurity(MemberSecurity memberSecurity) {
        memberSecurityDao.updateByPrimaryKeySelective(memberSecurity);
    }
}
