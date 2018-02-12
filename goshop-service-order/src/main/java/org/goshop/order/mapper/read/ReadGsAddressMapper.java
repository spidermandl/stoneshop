package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsAddress;

import java.util.List;
import java.util.Map;

public interface ReadGsAddressMapper {

    GsAddress selectByPrimaryKey(Long id);

    List<GsAddress> selectByCondition(Map condition);

}
