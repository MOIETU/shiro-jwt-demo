package com.shaylee.shirojwt.module.member.service;

import com.shaylee.shirojwt.module.member.entity.MemberExtend;

/**
 * 会员扩张 服务层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
public interface MemberExtendService {

    /**
     * 新增会员扩张
     *
     * @param memberExtend 会员扩张信息
     */
    void saveMemberExtend(MemberExtend memberExtend);
}
