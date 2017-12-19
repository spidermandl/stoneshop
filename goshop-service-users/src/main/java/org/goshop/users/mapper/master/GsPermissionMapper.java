package org.goshop.users.mapper.master;

import org.goshop.users.pojo.GsPermission;

public interface GsPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPermission record);

    int insertSelective(GsPermission record);

    GsPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPermission record);

    int updateByPrimaryKey(GsPermission record);
}
