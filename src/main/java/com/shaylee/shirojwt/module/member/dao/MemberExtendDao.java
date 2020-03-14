package com.shaylee.shirojwt.module.member.dao;

import com.shaylee.shirojwt.base.BaseMapper;
import com.shaylee.shirojwt.module.member.entity.MemberExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员扩张 数据层
 * Project: shaylee-shiro
 *
 * @author Adrian
 * @date 2020-03-14
 */
@Mapper
public interface MemberExtendDao extends BaseMapper<MemberExtend> {

    /**
     * 根据会员ID查询会员信息
     *
     * @param memberId member 主键 id
     * @return 会员信息
     */
    MemberExtend getMemberExtendByMemberId(@Param("memberId") Long memberId);

}