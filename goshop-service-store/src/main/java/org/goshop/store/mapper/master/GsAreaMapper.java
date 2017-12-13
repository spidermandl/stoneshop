package org.goshop.store.mapper.master;

import org.goshop.store.pojo.GsArea;

public interface GsAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsArea record);

    int insertSelective(GsArea record);

    GsArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsArea record);

    int updateByPrimaryKey(GsArea record);
}
