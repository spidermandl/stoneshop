package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsTypeProperty;

public interface ReadGsGoodsTypePropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsTypeProperty record);

    int insertSelective(GsGoodsTypeProperty record);

    GsGoodsTypeProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsTypeProperty record);

    int updateByPrimaryKey(GsGoodsTypeProperty record);
}
