package org.goshop.order.i;

import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;

import java.util.List;

/**
 * Created by Desmond on 10/01/2018.
 */
public interface OrderFormService {

    GsOrderformWithBLOBs findOne(Long id);

    List<GsOrderform> findByStoreId(Long storeId);

    int save(GsOrderformWithBLOBs form);

    int update(GsOrderformWithBLOBs form);

}
