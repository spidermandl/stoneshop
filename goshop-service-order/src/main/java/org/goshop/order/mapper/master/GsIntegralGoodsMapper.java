package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsIntegralGoods;

public interface GsIntegralGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsIntegralGoods record);

    int insertSelective(GsIntegralGoods record);

    GsIntegralGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsIntegralGoods record);

    int updateByPrimaryKey(GsIntegralGoods record);
}
