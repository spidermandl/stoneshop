package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsSpecification;

public interface ReadGsGoodsSpecificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsSpecification record);

    int insertSelective(GsGoodsSpecification record);

    GsGoodsSpecification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsSpecification record);

    int updateByPrimaryKey(GsGoodsSpecification record);
}
