package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsRefundLog;

import java.util.List;
import java.util.Map;

public interface ReadGsRefundLogMapper {
    GsRefundLog selectByPrimaryKey(Long id);

    List<GsRefundLog> selectByCondition(Map condition);
}
