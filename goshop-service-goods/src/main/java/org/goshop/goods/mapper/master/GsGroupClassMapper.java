package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGroupClass;

public interface GsGroupClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGroupClass record);

    int insertSelective(GsGroupClass record);

    GsGroupClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGroupClass record);

    int updateByPrimaryKey(GsGroupClass record);
}
