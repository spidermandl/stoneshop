package org.goshop.goods.i;

import org.goshop.goods.pojo.GsGoodsTypeProperty;

import java.util.List;

/**
 * Created by Desmond on 23/01/2018.
 */
public interface GoodsTypePropertyService {

    GsGoodsTypeProperty findOne(Long id);

    List<Long> selectByPropertyGroup(List condition);
}
