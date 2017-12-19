package org.goshop.users.mapper.master;

import org.goshop.users.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Long memberId);

    int insert(Member record);

    int insertSelective(Member record);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    int updateByUserId(Member member);

    int updateEmail(@Param("userId") Long userId, @Param("memberEmail") String email);

    int saveAvatar(@Param("userId") Long userId, @Param("memberAvatar") String memberAvatar);
}