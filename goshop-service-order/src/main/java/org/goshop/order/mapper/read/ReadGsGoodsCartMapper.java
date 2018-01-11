package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsGoodsCart;

public interface ReadGsGoodsCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsCart record);

    int insertSelective(GsGoodsCart record);

    GsGoodsCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsCart record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsCart record);

    int updateByPrimaryKey(GsGoodsCart record);
}
