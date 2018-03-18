package org.goshop.users.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.users.pojo.Member;

import java.util.List;

public interface ReadMemberMapper {

    Member selectByPrimaryKey(Long memberId);

    int findByMemberEmailCount(String memberEmail);

    int checkLoginNameByEmail(@Param("loginName") String loginName, @Param("memberEmail") String memberEmail);

    List<Member> findAll();

    List<Member> findUserAll();

    Member findByMemberEmail(@Param("memberEmail") String memberEmail);

    /**
     *
     * @param loginName 登录名
     * @param memberEmail
     * @param memberTruename 真实姓名
     * @param informAllow 禁止举报
     * @param isBuy 禁止购买
     * @param isAllowtalk 禁止发送消息
     * @param memberState 禁止登录
     * @param sort 排序字段
     * @return
     */
    List<Member> query(@Param("loginName") String loginName, @Param("memberEmail") String memberEmail,
                       @Param("memberTruename") String memberTruename, @Param("informAllow") Integer informAllow,
                       @Param("isBuy") Integer isBuy, @Param("isAllowtalk") Integer isAllowtalk,
                       @Param("memberState") Integer memberState, @Param("sort") String sort);

    Member selectByUserId(@Param("userId") Long userId);

}
