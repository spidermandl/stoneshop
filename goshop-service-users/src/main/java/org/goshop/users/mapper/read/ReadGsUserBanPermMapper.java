package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsUserBanPerm;

import java.util.List;

public interface ReadGsUserBanPermMapper {

    List<GsUserBanPerm> selectByUserId(Long uId);
}
