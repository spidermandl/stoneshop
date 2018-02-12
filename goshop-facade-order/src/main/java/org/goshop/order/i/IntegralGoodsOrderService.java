package org.goshop.order.i;

import org.goshop.order.pojo.GsIntegralGoodsorderWithBLOBs;

/**
 * Created by Desmond on 12/02/2018.
 */
public interface IntegralGoodsOrderService {

    GsIntegralGoodsorderWithBLOBs findOne(Long id);
}
