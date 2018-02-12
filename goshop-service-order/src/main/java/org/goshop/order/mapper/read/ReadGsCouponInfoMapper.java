package org.goshop.order.mapper.read;

import org.apache.ibatis.annotations.Param;
import org.goshop.order.pojo.GsCouponInfo;

import java.util.List;
import java.util.Map;

public interface ReadGsCouponInfoMapper {

    GsCouponInfo selectByPrimaryKey(Long id);

    List<GsCouponInfo> selectByCondition(Map param);
}
