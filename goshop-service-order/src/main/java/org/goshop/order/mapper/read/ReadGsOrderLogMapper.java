package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsOrderLog;

public interface ReadGsOrderLogMapper {
    GsOrderLog selectByPrimaryKey(Long id);
}
