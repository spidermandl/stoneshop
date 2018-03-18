package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsStoreCart;

public interface GsStoreCartMapper {
    int deleteByPrimaryKey(Long id);

    long insert(GsStoreCart record);

    long insertSelective(GsStoreCart record);

    int updateByPrimaryKeySelective(GsStoreCart record);

    int updateByPrimaryKey(GsStoreCart record);
}
