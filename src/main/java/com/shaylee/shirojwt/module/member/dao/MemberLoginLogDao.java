package com.shaylee.shirojwt.module.member.dao;

import com.shaylee.shirojwt.base.BaseMapper;
import com.shaylee.shirojwt.module.member.entity.MemberLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录日志 数据层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLog> {

}