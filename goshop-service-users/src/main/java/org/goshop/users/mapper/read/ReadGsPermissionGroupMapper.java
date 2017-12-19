package org.goshop.users.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.users.pojo.GsPermissionGroup;

import java.util.List;

public interface ReadGsPermissionGroupMapper {
    GsPermissionGroup selectByPrimaryKey(Long id);

    List<GsPermissionGroup> selectByType(@Param("type") String type,
                                         @Param("orderBy") String orderBy,
                                         @Param("orderType") String orderType);
}
