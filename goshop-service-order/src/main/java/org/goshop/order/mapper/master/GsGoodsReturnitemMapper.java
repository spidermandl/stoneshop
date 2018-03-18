package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsGoodsReturnitem;

public interface GsGoodsReturnitemMapper {
    int deleteByPrimaryKey(Long id);

    long insert(GsGoodsReturnitem record);

    long insertSelective(GsGoodsReturnitem record);

    int updateByPrimaryKeySelective(GsGoodsReturnitem record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsReturnitem record);

    int updateByPrimaryKey(GsGoodsReturnitem record);
}
