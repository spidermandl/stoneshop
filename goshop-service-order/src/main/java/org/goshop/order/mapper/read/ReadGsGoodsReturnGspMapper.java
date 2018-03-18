package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsGoodsReturnGsp;

public interface ReadGsGoodsReturnGspMapper {
    int insert(GsGoodsReturnGsp record);

    int insertSelective(GsGoodsReturnGsp record);
}
