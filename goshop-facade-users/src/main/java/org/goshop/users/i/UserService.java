package org.goshop.users.i;


import com.github.pagehelper.PageInfo;
import org.goshop.users.pojo.GsPermission;
import org.goshop.users.pojo.GsPermissionGroup;
import org.goshop.users.pojo.Role;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int save(User user);


    int update(User user);
    //int updateByPrimaryKey(User user);

    int updateByPrimaryKeySelective(User user);

    User findOne(Long userId);

    User findByLoginName(String loginName);

    int delete(Long userId);

    User findOfRoleOne(Long userId);

    /**
     * 获取用户的权限
     * @param userId
     * @return
     */
    List<GsPermission> findPermissionListByUserId(Long userId);

    void updateLoginInfo(User user, String ip);

    List<Role> findByRole(Long userId);

    int updateByPrimaryKey(User user);

    User selectByPrimaryKey(Long userId);

    int insert(User user);

    int addRole2User(User user,String role_name);

    /**
     * 获取子用户
     * @param parentId
     * @return
     */
    List<User> findChilds(Long parentId);

    PageInfo<User> findChilds(Long parentId,Integer curpage,Integer pages);

    /**
     * 获取permission组
     * @param type
     * @param orderBy
     * @param orderType
     * @return
     */
    List<GsPermissionGroup> findPermissionGroupByType(String type,String orderBy,String orderType);
}
