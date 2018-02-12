package org.goshop.goods.i;

import org.goshop.goods.pojo.GsGroupGoods;

/**
 * Created by Desmond on 07/02/2018.
 */
public interface GroupGoodsService {

    GsGroupGoods findOne(Long id);

    int save(GsGroupGoods obj);

    int update(GsGroupGoods obj);
}
