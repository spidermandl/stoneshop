package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsBrandCategory;

public interface ReadGsGoodsBrandCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsBrandCategory record);

    int insertSelective(GsGoodsBrandCategory record);

    GsGoodsBrandCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsBrandCategory record);

    int updateByPrimaryKey(GsGoodsBrandCategory record);
}
