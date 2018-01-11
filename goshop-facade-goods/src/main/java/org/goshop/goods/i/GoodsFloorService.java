package org.goshop.goods.i;

import org.goshop.goods.pojo.GsGoodsFloorWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * 商品楼层 service
 */
public interface GoodsFloorService {

    List<GsGoodsFloorWithBLOBs> findByCondition(Map condition);
}
