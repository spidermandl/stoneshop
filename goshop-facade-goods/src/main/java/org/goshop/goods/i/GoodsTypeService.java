package org.goshop.goods.i;



import org.goshop.goods.pojo.GoodsType;
import org.goshop.goods.pojo.GsGoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GsGoodsType> findAll();

    GsGoodsType findOne(long id);
}
