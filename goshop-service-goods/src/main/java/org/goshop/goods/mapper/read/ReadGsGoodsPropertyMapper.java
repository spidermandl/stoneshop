package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsProperty;

public interface ReadGsGoodsPropertyMapper {
    int insert(GsGoodsProperty record);

    int insertSelective(GsGoodsProperty record);
}
