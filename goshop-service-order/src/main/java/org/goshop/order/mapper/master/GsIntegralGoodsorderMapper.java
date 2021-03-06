package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsIntegralGoodsorder;
import org.goshop.order.pojo.GsIntegralGoodsorderWithBLOBs;

public interface GsIntegralGoodsorderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsIntegralGoodsorderWithBLOBs record);

    int insertSelective(GsIntegralGoodsorderWithBLOBs record);

    GsIntegralGoodsorderWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsIntegralGoodsorderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsIntegralGoodsorderWithBLOBs record);

    int updateByPrimaryKey(GsIntegralGoodsorder record);
}
