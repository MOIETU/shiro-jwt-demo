package com.shaylee.shirojwt.module.member.service;

import com.shaylee.shirojwt.module.member.entity.MemberSecurity;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员登录 服务层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface MemberSecurityService {

    /**
     * 获取会员安全信息缓存(不存在会查数据库并刷新缓存)
     *
     * @param memberNo 会员号
     * @return 会员安全信息(memberId, memberNo, password)
     */
    MemberSecurity getMemberSecurityCache(String memberNo);

    /**
     * 刷新会员安全信息缓存
     *
     * @param memberNo 会员号
     * @return 会员安全信息(memberId, memberNo, password)
     */
    MemberSecurity refreshMemberSecurityCache(String memberNo);

    /**
     * 查询会员登录信息
     *
     * @param memberNo 会员编号
     * @return 会员登录信息
     */
    MemberSecurity getMemberSecurityByMemberNo(String memberNo);

    /**
     * 查询会员登录信息
     *
     * @param areaCode 手机区号
     * @param phone    手机号
     * @return 会员登录信息
     */
    MemberSecurity getMemberSecurityByPhone(String areaCode, String phone);

    /**
     * 查询会员登录信息
     *
     * @param id 会员ID
     * @return 会员登录信息
     */
    MemberSecurity getMemberSecurityByMemberId(Long id);

    /**
     * 刷新登陆信息(最后登录IP，最后登陆OS，最后登陆时间)
     *
     * @param request        HttpServletRequest
     * @param memberSecurity 会员安全实体
     */
    void refreshLoginInfo(HttpServletRequest request, MemberSecurity memberSecurity);

    /**
     * 修改密码
     *
     * @param areaCode 手机区号
     * @param phone    手机号
     * @param password 密码
     */
    void resetMemberPassword(String areaCode, String phone, String password);

    /**
     * 新增会员登录信息
     *
     * @param memberSecurity 会员登录信息
     */
    void saveMemberSecurity(MemberSecurity memberSecurity);

    /**
     * 修改会员登录信息
     *
     * @param memberSecurity 会员登录信息
     */
    void updateMemberSecurity(MemberSecurity memberSecurity);
}
