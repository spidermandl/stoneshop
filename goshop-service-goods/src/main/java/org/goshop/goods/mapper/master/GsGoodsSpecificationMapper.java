package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsSpecification;

public interface GsGoodsSpecificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsSpecification record);

    int insertSelective(GsGoodsSpecification record);

    GsGoodsSpecification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsSpecification record);

    int updateByPrimaryKey(GsGoodsSpecification record);
}
