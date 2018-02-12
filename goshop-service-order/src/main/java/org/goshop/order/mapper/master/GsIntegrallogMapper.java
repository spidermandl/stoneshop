package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsIntegrallog;

public interface GsIntegrallogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsIntegrallog record);

    int insertSelective(GsIntegrallog record);

    GsIntegrallog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsIntegrallog record);

    int updateByPrimaryKeyWithBLOBs(GsIntegrallog record);

    int updateByPrimaryKey(GsIntegrallog record);
}
