package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsRefundLog;

public interface ReadGsRefundLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsRefundLog record);

    int insertSelective(GsRefundLog record);

    GsRefundLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsRefundLog record);

    int updateByPrimaryKey(GsRefundLog record);
}
