package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGroupArea;

public interface GsGroupAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGroupArea record);

    int insertSelective(GsGroupArea record);

    GsGroupArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGroupArea record);

    int updateByPrimaryKey(GsGroupArea record);
}
