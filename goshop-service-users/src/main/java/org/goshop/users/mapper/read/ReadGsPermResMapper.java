package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsPermRes;

public interface ReadGsPermResMapper {
    int insert(GsPermRes record);

    int insertSelective(GsPermRes record);
}
