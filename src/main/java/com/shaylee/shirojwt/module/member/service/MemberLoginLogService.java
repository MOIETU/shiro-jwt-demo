package com.shaylee.shirojwt.module.member.service;

import com.shaylee.shirojwt.module.member.entity.MemberLoginLog;

/**
 * 会员登录日志 服务层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface MemberLoginLogService {

    /**
     * 新增会员登录日志
     *
     * @param memberLoginLog 会员登录日志信息
     * @return 结果
     */
    int saveMemberLoginLog(MemberLoginLog memberLoginLog);
}
