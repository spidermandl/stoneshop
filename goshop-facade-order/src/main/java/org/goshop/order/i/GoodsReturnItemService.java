package org.goshop.order.i;

import org.goshop.order.pojo.GsGoodsCart;
import org.goshop.order.pojo.GsGoodsReturnitem;

/**
 * Created by Desmond on 02/03/2018.
 */
public interface GoodsReturnItemService {

    Long save(GsGoodsReturnitem item, GsGoodsCart gc);
}
