package org.goshop.users.mapper.master;

import org.goshop.users.pojo.GsUserBanPerm;

import java.util.List;

public interface GsUserBanPermMapper {
    int insert(GsUserBanPerm record);

    int insertSelective(GsUserBanPerm record);

    int insertBatch(List<GsUserBanPerm> bans);
}
