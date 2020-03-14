package com.shaylee.shirojwt.module.member.service.impl;

import com.shaylee.shirojwt.module.member.dao.MemberLoginLogDao;
import com.shaylee.shirojwt.module.member.entity.MemberLoginLog;
import com.shaylee.shirojwt.module.member.service.MemberLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员登录日志 服务层实现
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Service
public class MemberLoginLogServiceImpl implements MemberLoginLogService {

    @Autowired
    private MemberLoginLogDao memberLoginLogDao;

    @Override
    public int saveMemberLoginLog(MemberLoginLog memberLoginLog) {
        return memberLoginLogDao.insertSelective(memberLoginLog);
    }
}
