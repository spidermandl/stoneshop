package org.goshop.users.mapper.read;


import org.goshop.users.pojo.Role;

public interface ReadRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role findByName(String name);

    Role findOfUserOne(Long id);

}
