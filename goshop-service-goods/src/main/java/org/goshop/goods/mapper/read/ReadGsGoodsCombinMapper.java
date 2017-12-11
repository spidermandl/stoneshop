package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsCombin;
import org.goshop.goods.pojo.GsGoodsProperty;

import java.util.List;

public interface ReadGsGoodsCombinMapper {
    int insert(GsGoodsCombin record);

    int insertSelective(GsGoodsCombin record);

    void insertBatch(List<GsGoodsCombin> coms);

    List<GsGoodsCombin> findByGoodsId(Long goodsId);

}
