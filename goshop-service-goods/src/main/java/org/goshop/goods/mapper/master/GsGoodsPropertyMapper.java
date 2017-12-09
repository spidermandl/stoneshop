package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsProperty;

import java.util.List;

public interface GsGoodsPropertyMapper {
    int insert(GsGoodsProperty record);

    int insertSelective(GsGoodsProperty record);

    void insertBatch(List<GsGoodsProperty> gps);

    int deleteByGoodsId(Long goods_id);

}
