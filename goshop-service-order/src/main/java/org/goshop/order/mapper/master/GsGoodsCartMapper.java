package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsGoodsCart;

public interface GsGoodsCartMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByStorecartId(Long sc_id);

    int deleteByOrderId(Long order_id);

    long insert(GsGoodsCart record);

    long insertSelective(GsGoodsCart record);

    int updateByPrimaryKeySelective(GsGoodsCart record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsCart record);

    int updateByPrimaryKey(GsGoodsCart record);
}
