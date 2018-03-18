package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsGoodsReturnGsp;

import java.util.List;

public interface GsGoodsReturnGspMapper {
    int insert(GsGoodsReturnGsp record);

    int insertSelective(GsGoodsReturnGsp record);

    int insertBatch(List<GsGoodsReturnGsp> list);
}
