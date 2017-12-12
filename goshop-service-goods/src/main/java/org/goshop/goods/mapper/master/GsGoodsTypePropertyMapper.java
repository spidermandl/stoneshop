package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsTypeProperty;

public interface GsGoodsTypePropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsTypeProperty record);

    int insertSelective(GsGoodsTypeProperty record);

    GsGoodsTypeProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsTypeProperty record);

    int updateByPrimaryKey(GsGoodsTypeProperty record);
}
