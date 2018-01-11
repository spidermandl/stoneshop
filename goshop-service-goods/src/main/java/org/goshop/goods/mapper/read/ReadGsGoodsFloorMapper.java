package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsFloor;
import org.goshop.goods.pojo.GsGoodsFloorWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsFloorMapper {

    GsGoodsFloorWithBLOBs selectByPrimaryKey(Long id);

    List<GsGoodsFloorWithBLOBs> selectByCondition(Map conditioin);
}
