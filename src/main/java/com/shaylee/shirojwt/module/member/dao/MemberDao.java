package com.shaylee.shirojwt.module.member.dao;

import com.shaylee.shirojwt.base.BaseMapper;
import com.shaylee.shirojwt.module.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员 数据层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Mapper
public interface MemberDao extends BaseMapper<Member> {

    /**
     * 查询会员信息
     *
     * @param id 会员ID
     * @return 会员信息
     */
    Member getMemberById(@Param("id") Long id);

    /**
     * 查询会员信息
     *
     * @param ids 会员ID
     * @return 会员信息
     */
    List<Member> getMemberByIds(@Param("ids") List<Long> ids);

    /**
     * 根据会员 no 查询会员信息
     *
     * @param no 会员no
     * @return 会员信息
     */
    Member getMemberByNo(@Param("no") String no);
}