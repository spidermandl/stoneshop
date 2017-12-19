package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsPermission;

public interface ReadGsPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPermission record);

    int insertSelective(GsPermission record);

    GsPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPermission record);

    int updateByPrimaryKey(GsPermission record);
}
