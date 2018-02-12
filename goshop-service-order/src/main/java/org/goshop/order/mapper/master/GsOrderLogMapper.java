package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsOrderLog;

public interface GsOrderLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsOrderLog record);

    int insertSelective(GsOrderLog record);

    GsOrderLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsOrderLog record);

    int updateByPrimaryKeyWithBLOBs(GsOrderLog record);

    int updateByPrimaryKey(GsOrderLog record);
}
