package com.shaylee.shirojwt.module.member.dao;

import com.shaylee.shirojwt.base.BaseMapper;
import com.shaylee.shirojwt.module.member.entity.MemberSecurity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录 数据层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Mapper
public interface MemberSecurityDao extends BaseMapper<MemberSecurity> {

}