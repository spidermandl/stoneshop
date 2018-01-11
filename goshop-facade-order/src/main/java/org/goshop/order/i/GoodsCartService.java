package org.goshop.order.i;

import org.goshop.order.pojo.GsGoodsCart;

/**
 * Created by Desmond on 03/01/2018.
 */
public interface GoodsCartService {

    int delete(Long id);

    int update(GsGoodsCart goodsCart);

}
