package org.goshop.store.mapper.read;

import org.goshop.store.pojo.GsTransArea;

public interface ReadGsTransAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsTransArea record);

    int insertSelective(GsTransArea record);

    GsTransArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsTransArea record);

    int updateByPrimaryKey(GsTransArea record);
}
