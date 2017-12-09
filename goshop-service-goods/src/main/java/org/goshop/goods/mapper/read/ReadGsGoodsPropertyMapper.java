package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsProperty;

import java.util.List;

public interface ReadGsGoodsPropertyMapper {
    int insert(GsGoodsProperty record);

    int insertSelective(GsGoodsProperty record);

    void insertBatch(List<GsGoodsProperty> gps);
}
