package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsIntegralGoods;

public interface ReadGsIntegralGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsIntegralGoods record);

    int insertSelective(GsIntegralGoods record);

    GsIntegralGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsIntegralGoods record);

    int updateByPrimaryKey(GsIntegralGoods record);
}
