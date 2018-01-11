package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGoodsEvaluateWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsEvaluateMapper {
    GsGoodsEvaluateWithBLOBs selectByPrimaryKey(Long id);

    List<GsGoodsEvaluateWithBLOBs> selectByCondition(Map conditioin);
}
