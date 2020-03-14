package com.shaylee.shirojwt.module.member.service.impl;

import com.shaylee.shirojwt.module.member.dao.MemberExtendDao;
import com.shaylee.shirojwt.module.member.entity.MemberExtend;
import com.shaylee.shirojwt.module.member.service.MemberExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员扩张 服务层实现
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Service
public class MemberExtendServiceImpl implements MemberExtendService {

    @Autowired
    private MemberExtendDao memberExtendDao;

    @Override
    public void saveMemberExtend(MemberExtend memberExtend) {
        memberExtendDao.insertSelective(memberExtend);
    }
}
