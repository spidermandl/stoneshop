package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsIntegralGoodscart;

public interface GsIntegralGoodscartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsIntegralGoodscart record);

    int insertSelective(GsIntegralGoodscart record);

    GsIntegralGoodscart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsIntegralGoodscart record);

    int updateByPrimaryKey(GsIntegralGoodscart record);
}
