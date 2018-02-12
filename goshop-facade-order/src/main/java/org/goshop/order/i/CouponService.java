package org.goshop.order.i;

import org.goshop.order.pojo.GsCoupon;
import org.goshop.order.pojo.GsCouponInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 11/02/2018.
 */
public interface CouponService {

    GsCouponInfo findOne(Long id);

    GsCoupon findCoupon(Long id);

    List<GsCouponInfo> findByCondition(Map condition);

    int update(GsCouponInfo info);
}
