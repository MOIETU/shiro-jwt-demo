package com.shaylee.shirojwt.module.member.service.impl;

import com.shaylee.shirojwt.module.member.constant.MemberConstant;
import com.shaylee.shirojwt.module.member.dao.MemberDao;
import com.shaylee.shirojwt.module.member.entity.Member;
import com.shaylee.shirojwt.module.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员 服务层实现
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Service
public class MemberServiceImpl implements MemberService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member getMemberById(Long id) {
        Member member = new Member();
        member.setId(id);
        member.setDelFlag(MemberConstant.DEL_FLAG_NORMAL);
        return memberDao.selectOne(member);
    }

    @Override
    public void saveMember(Member member) {
        memberDao.insertSelective(member);
    }

    @Override
    public void updateMember(Member member) {
        memberDao.updateByPrimaryKeySelective(member);
    }
}
