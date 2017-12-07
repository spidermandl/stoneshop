package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsCombin;

public interface ReadGsGoodsCombinMapper {
    int insert(GsGoodsCombin record);

    int insertSelective(GsGoodsCombin record);
}
