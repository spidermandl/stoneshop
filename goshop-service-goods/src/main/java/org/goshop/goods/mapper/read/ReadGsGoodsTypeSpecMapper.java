package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsTypeSpec;

public interface ReadGsGoodsTypeSpecMapper {
    int insert(GsGoodsTypeSpec record);

    int insertSelective(GsGoodsTypeSpec record);
}
