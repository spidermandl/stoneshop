package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsIntegralGoodscart;

public interface ReadGsIntegralGoodscartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsIntegralGoodscart record);

    int insertSelective(GsIntegralGoodscart record);

    GsIntegralGoodscart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsIntegralGoodscart record);

    int updateByPrimaryKey(GsIntegralGoodscart record);
}
