package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsCoupon;

public interface ReadGsCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsCoupon record);

    int insertSelective(GsCoupon record);

    GsCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsCoupon record);

    int updateByPrimaryKey(GsCoupon record);
}
