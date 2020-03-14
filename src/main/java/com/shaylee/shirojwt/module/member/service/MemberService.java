package com.shaylee.shirojwt.module.member.service;

import com.shaylee.shirojwt.module.member.entity.Member;

/**
 * 会员 服务层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-01
 */
public interface MemberService {

    /**
     * 查询会员信息
     *
     * @param id 会员ID
     * @return 会员信息
     */
    Member getMemberById(Long id);

    /**
     * 新增会员
     *
     * @param member 会员信息
     */
    void saveMember(Member member);

    /**
     * 修改会员
     *
     * @param member 会员信息
     */
    void updateMember(Member member);
}
