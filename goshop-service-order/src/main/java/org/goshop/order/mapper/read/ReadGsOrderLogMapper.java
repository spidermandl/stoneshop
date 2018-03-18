package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsOrderLog;

import java.util.List;
import java.util.Map;

public interface ReadGsOrderLogMapper {

    GsOrderLog selectByPrimaryKey(Long id);

    List<GsOrderLog> selectByCondition(Map condition);
}
