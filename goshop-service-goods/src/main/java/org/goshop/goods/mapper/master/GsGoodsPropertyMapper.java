package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsProperty;

public interface GsGoodsPropertyMapper {
    int insert(GsGoodsProperty record);

    int insertSelective(GsGoodsProperty record);
}
