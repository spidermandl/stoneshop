package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsSpecProperty;

public interface GsGoodsSpecPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsSpecProperty record);

    int insertSelective(GsGoodsSpecProperty record);

    GsGoodsSpecProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsSpecProperty record);

    int updateByPrimaryKey(GsGoodsSpecProperty record);
}
