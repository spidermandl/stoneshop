package org.goshop.goods.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoodsConsultWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 23/01/2018.
 */
public interface GoodsConsultService {

    GsGoodsConsultWithBLOBs findOne(Long id);

    int save(GsGoodsConsultWithBLOBs consult);

    List<GsGoodsConsultWithBLOBs> findByCondition(Map condition);

    PageInfo<GsGoodsConsultWithBLOBs> findByCondition(Map condition,Integer curPage,Integer pageNum);
}
