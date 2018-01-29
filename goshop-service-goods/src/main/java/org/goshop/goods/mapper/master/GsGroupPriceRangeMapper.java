package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGroupPriceRange;

public interface GsGroupPriceRangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGroupPriceRange record);

    int insertSelective(GsGroupPriceRange record);

    GsGroupPriceRange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGroupPriceRange record);

    int updateByPrimaryKey(GsGroupPriceRange record);
}
