package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsFloor;
import org.goshop.goods.pojo.GsGoodsFloorWithBLOBs;

public interface GsGoodsFloorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsFloorWithBLOBs record);

    int insertSelective(GsGoodsFloorWithBLOBs record);

    GsGoodsFloorWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsFloorWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsFloorWithBLOBs record);

    int updateByPrimaryKey(GsGoodsFloor record);
}
