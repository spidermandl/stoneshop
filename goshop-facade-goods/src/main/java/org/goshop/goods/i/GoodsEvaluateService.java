package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoodsEvaluateWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 10/01/2018.
 */
public interface GoodsEvaluateService {

    List<GsGoodsEvaluateWithBLOBs> findByCondition(Map condition);

    PageInfo<GsGoodsEvaluateWithBLOBs> findByCondition(Map condition, Integer curPage, Integer pageSize);
}
