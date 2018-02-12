package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsCouponInfo;

public interface GsCouponInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsCouponInfo record);

    int insertSelective(GsCouponInfo record);

    int updateByPrimaryKeySelective(GsCouponInfo record);

    int updateByPrimaryKey(GsCouponInfo record);
}
