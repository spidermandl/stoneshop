package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;

public interface GsOrderformMapper {
    int deleteByPrimaryKey(Long id);

    long insert(GsOrderformWithBLOBs record);

    long insertSelective(GsOrderformWithBLOBs record);

    int updateByPrimaryKeySelective(GsOrderformWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsOrderformWithBLOBs record);

    int updateByPrimaryKey(GsOrderform record);
}
