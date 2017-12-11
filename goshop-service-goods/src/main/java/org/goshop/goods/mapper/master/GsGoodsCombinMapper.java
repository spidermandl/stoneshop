package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsCombin;
import org.goshop.goods.pojo.GsGoodsProperty;

import java.util.List;

public interface GsGoodsCombinMapper {
    int insert(GsGoodsCombin record);

    int insertSelective(GsGoodsCombin record);

    void insertBatch(List<GsGoodsCombin> coms);

    int deleteByGoodsId(Long goods_id);

    int deleteByGoodsAndCombinId(List<GsGoodsCombin> list);

}
