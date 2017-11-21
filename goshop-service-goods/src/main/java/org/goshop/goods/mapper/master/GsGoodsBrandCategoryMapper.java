package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsBrandCategory;

public interface GsGoodsBrandCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsBrandCategory record);

    int insertSelective(GsGoodsBrandCategory record);

    GsGoodsBrandCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsBrandCategory record);

    int updateByPrimaryKey(GsGoodsBrandCategory record);
}
