package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;

public interface GsOrderformMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsOrderformWithBLOBs record);

    int insertSelective(GsOrderformWithBLOBs record);

    GsOrderformWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsOrderformWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsOrderformWithBLOBs record);

    int updateByPrimaryKey(GsOrderform record);
}
