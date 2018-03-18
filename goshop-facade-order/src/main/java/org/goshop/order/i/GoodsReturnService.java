package org.goshop.order.i;

import org.goshop.order.pojo.GsGoodsReturn;

/**
 * Created by Desmond on 02/03/2018.
 */
public interface GoodsReturnService {
    int save(GsGoodsReturn record);

    GsGoodsReturn findOne(Long id);
}
