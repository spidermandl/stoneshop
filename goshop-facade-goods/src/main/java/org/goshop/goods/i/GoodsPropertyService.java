package org.goshop.goods.i;

import org.goshop.goods.pojo.GsGoodsSpecProperty;

/**
 * Created by Desmond on 08/12/2017.
 */
public interface GoodsPropertyService {

    GsGoodsSpecProperty findOne(Long id);
}
