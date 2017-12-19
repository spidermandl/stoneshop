package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsUserBanPerm;

import java.util.List;

public interface ReadGsUserBanPermMapper {
    int insert(GsUserBanPerm record);

    int insertSelective(GsUserBanPerm record);

    List<GsUserBanPerm> selectByUserId(Long uId);
}