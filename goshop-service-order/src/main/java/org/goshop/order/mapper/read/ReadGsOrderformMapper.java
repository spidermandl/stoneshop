package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ReadGsOrderformMapper {

    GsOrderformWithBLOBs selectByPrimaryKey(Long id);

    List<GsOrderform> selectByStoreId(Long storeId);

    List<GsOrderformWithBLOBs> selectByCondition(Map condition);
}
