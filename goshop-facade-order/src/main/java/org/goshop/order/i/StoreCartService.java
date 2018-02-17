package org.goshop.order.i;

import org.goshop.order.pojo.GsStoreCart;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 03/01/2018.
 */
public interface StoreCartService {

    List<GsStoreCart> findOwnCartByCondition(Map condition);

    int delete(Long id);

    List<GsStoreCart> findByCondition(Map condition);

    Long save(GsStoreCart cart);

    int update(GsStoreCart cart);

    GsStoreCart findOne(Long id);
}
