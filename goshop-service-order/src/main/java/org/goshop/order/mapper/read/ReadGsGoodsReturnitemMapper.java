package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsGoodsReturnitem;

public interface ReadGsGoodsReturnitemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsReturnitem record);

    int insertSelective(GsGoodsReturnitem record);

    GsGoodsReturnitem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsReturnitem record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsReturnitem record);

    int updateByPrimaryKey(GsGoodsReturnitem record);
}
