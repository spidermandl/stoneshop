package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsPermission;

import java.util.List;

public interface ReadGsPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPermission record);

    int insertSelective(GsPermission record);

    GsPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPermission record);

    int updateByPrimaryKey(GsPermission record);

    List<GsPermission> selectByPrimaryKeys(List<Long> ids);

    List<GsPermission> selectByType(String type);
}
