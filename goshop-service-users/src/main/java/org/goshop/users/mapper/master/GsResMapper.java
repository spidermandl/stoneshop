package org.goshop.users.mapper.master;

import org.goshop.users.pojo.GsRes;

public interface GsResMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsRes record);

    int insertSelective(GsRes record);

    GsRes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsRes record);

    int updateByPrimaryKey(GsRes record);
}
