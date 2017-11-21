package org.goshop.goods.i;

import org.goshop.goods.pojo.GoodsCommon;
import org.goshop.goods.pojo.GoodsCommonWithBLOBs;

/**
 * Created by Administrator on 2016/4/27.
 */
public interface GoodsCommonService {

    int save(GoodsCommonWithBLOBs goodsCommonWithBLOBs);

}
