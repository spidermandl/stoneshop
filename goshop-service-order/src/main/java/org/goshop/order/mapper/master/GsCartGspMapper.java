package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsCartGsp;

import java.util.List;

public interface GsCartGspMapper {
    int insert(GsCartGsp record);

    int insertSelective(GsCartGsp record);

    int insertBatch(List<GsCartGsp> list);

    int deleteByCartId(Long cart_id);
}
