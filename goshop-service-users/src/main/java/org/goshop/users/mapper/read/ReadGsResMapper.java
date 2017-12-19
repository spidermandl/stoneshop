package org.goshop.users.mapper.read;

import org.goshop.users.pojo.GsRes;

public interface ReadGsResMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsRes record);

    int insertSelective(GsRes record);

    GsRes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsRes record);

    int updateByPrimaryKey(GsRes record);
}
