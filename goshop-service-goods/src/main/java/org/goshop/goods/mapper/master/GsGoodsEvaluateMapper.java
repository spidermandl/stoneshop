package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsEvaluate;
import org.goshop.goods.pojo.GsGoodsEvaluateWithBLOBs;

public interface GsGoodsEvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsEvaluateWithBLOBs record);

    int insertSelective(GsGoodsEvaluateWithBLOBs record);

    int updateByPrimaryKeySelective(GsGoodsEvaluateWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsEvaluateWithBLOBs record);

    int updateByPrimaryKey(GsGoodsEvaluate record);

    int deleteByGoodsId(Long evaluate_goods_id);
}
