package org.goshop.pay.mapper.master;

import org.goshop.pay.pojo.GsRefundLog;

public interface GsRefundLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsRefundLog record);

    int insertSelective(GsRefundLog record);

    GsRefundLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsRefundLog record);

    int updateByPrimaryKey(GsRefundLog record);
}
