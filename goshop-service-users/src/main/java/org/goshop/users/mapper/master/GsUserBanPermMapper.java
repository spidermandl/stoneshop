package org.goshop.users.mapper.master;

import org.goshop.users.pojo.GsUserBanPerm;

public interface GsUserBanPermMapper {
    int insert(GsUserBanPerm record);

    int insertSelective(GsUserBanPerm record);
}