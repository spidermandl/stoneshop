package org.goshop.order.i;

import org.goshop.order.pojo.GsOrderform;

import java.util.List;

/**
 * Created by Desmond on 10/01/2018.
 */
public interface OrderFormService {

    List<GsOrderform> findByStoreId(Long storeId);
}
