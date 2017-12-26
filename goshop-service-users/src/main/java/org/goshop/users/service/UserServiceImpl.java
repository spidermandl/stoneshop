package org.goshop.users.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.exception.MapperException;
import org.goshop.common.utils.PageUtils;
import org.goshop.common.utils.RandomUtils;
import org.goshop.users.i.PasswordService;
import org.goshop.users.i.UserService;
import org.goshop.users.mapper.master.GsUserBanPermMapper;
import org.goshop.users.mapper.master.UserMapper;
import org.goshop.users.mapper.master.UserRoleMapper;
import org.goshop.users.mapper.read.*;
import org.goshop.users.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/6/11.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ReadUserMapper readUserMapper;

    @Autowired
    ReadRoleMapper readRoleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PasswordService passwordService;

    @Autowired
    ReadGsUserBanPermMapper readGsUserBanPermMapper;

    @Autowired
    ReadGsPermissionGroupMapper readGsPermissionGroupMapper;

    @Autowired
    ReadGsPermissionMapper readGsPermissionMapper;

    @Autowired
    GsUserBanPermMapper gsUserBanPermMapper;

    @Override
    public long save(User user) {
        User userDataBase=this.findByLoginName(user.getLoginName());
        if(userDataBase!=null){
            throw new MapperException("登录名称重复");
        }
        userMapper.insertSelective(passWordUser(user));
        return user.getId();
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(passWordUser(user));
    }

    @Override
    public User findOne(Long userId) {
        return readUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findByLoginName(String loginName) {
        return readUserMapper.findByLoginName(loginName);
    }

    @Override
    public int delete(Long userId ){
        userRoleMapper.deleteRoleByUId(userId);
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User findOfRoleOne(Long userId) {
        return readUserMapper.findOfRoleOne(userId);
    }

    @Override
    public void updateLoginInfo(User user, String ip) {
        User loginUserInfo = new User();
        loginUserInfo.setId(user.getId());
        loginUserInfo.setLoginNum(user.getLoginNum()==null?0:user.getLoginNum() + 1);
        loginUserInfo.setLoginIp(ip);
        loginUserInfo.setOldLoginIp(user.getLoginIp());
        loginUserInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
        loginUserInfo.setOldLoginTime(user.getLoginTime());
        userMapper.updateLoginInfo(loginUserInfo);
    }

    @Override
    public List<GsPermission> findPermissionListByUserId(Long userId) {
        List<GsPermission> result = readUserMapper.findPermissionListByUserId(userId);
        List<GsUserBanPerm> bans = readGsUserBanPermMapper.selectByUserId(userId);
        Iterator<GsPermission> it = result.iterator();
        while(it.hasNext()){
            GsPermission p = it.next();
            for (GsUserBanPerm b:bans){
                if(b.getPermissionId()==p.getId()){
                    it.remove();
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<GsPermission> findPermissionListByIds(List<Long> ids) {
        return readGsPermissionMapper.selectByPrimaryKeys(ids);
    }

    @Override
    public List<GsPermission> findPermissionListByType(String type) {
        return readGsPermissionMapper.selectByType(type);
    }

    @Override
    public int addBanPermission(List<GsUserBanPerm> bans) {
        return gsUserBanPermMapper.insertBatch(bans);
    }

    @Override
    public List<Role> findByRole(Long userId) {
        return readUserMapper.findByRole(userId);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return readUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public long insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }


    @Override
    public int addRole2User(User user, String role_name) {
        Role role = readRoleMapper.findByName(role_name);
        UserRole userRole = userRoleMapper.findByUIdAndRId(user.getId(),role.getId());
        if (userRole !=null)//已存在角色
            return 0;
        userRole = new UserRole();
        userRole.setuId(user.getId());
        userRole.setrId(role.getId());
        return userRoleMapper.insert(userRole);
    }

    @Override
    public List<User> findChilds(Long parentId) {
        return readUserMapper.selectByParentId(parentId);
    }

    @Override
    public PageInfo<User> findChilds(Long parentId, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<User> list = readUserMapper.selectByParentId(parentId);
        return new PageInfo<>(list);
    }

    @Override
    public List<GsPermissionGroup> findPermissionGroupByType(String type, String orderBy, String orderType) {
        return readGsPermissionGroupMapper.selectByType(type,orderBy,orderType);
    }

    /**
     * 将密码加密
     * @param user
     * @return
     */
    private  User passWordUser(User user){
        if(StringUtils.hasText(user.getPassword())){
            String salt= RandomUtils.generateString(5);
            user.setPassword(passwordService.encryptPassword(user.getPassword(),salt));
            user.setSalt(salt);
        }
        return user;
    }
}
