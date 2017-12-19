package org.goshop.users.mapper.master;

import org.goshop.users.pojo.GsPermissionGroup;

public interface GsPermissionGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPermissionGroup record);

    int insertSelective(GsPermissionGroup record);

    GsPermissionGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPermissionGroup record);

    int updateByPrimaryKey(GsPermissionGroup record);
}
