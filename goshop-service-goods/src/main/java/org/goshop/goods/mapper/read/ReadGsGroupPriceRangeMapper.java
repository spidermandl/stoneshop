package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGroupPriceRange;

public interface ReadGsGroupPriceRangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGroupPriceRange record);

    int insertSelective(GsGroupPriceRange record);

    GsGroupPriceRange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGroupPriceRange record);

    int updateByPrimaryKey(GsGroupPriceRange record);
}
