package org.goshop.users.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.users.pojo.GsPermission;
import org.goshop.users.pojo.Role;
import org.goshop.users.pojo.User;

import java.util.List;

public interface ReadUserMapper {

    User selectByPrimaryKey(Long id);

    User findByLoginName(String loginName);

    User findByUserName(String userName);

    User findOfRoleOne(Long id);

    int findByLoginNameCount(String loginName);

    List<Role> findByRole(Long userId);

    int findByRoleCount(@Param("userId") Long userId, @Param("roleName") String roleName);

    List<User> selectByParentId(@Param("parentId") Long parentId);

    List<GsPermission> findPermissionListByUserId(Long userId);

}
