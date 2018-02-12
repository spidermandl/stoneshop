package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsStoreCart;

import java.util.List;
import java.util.Map;

public interface ReadGsStoreCartMapper {

    GsStoreCart selectByPrimaryKey(Long id);

    List<GsStoreCart> selectOwnCartByCondition(Map condition);

    List<GsStoreCart> selectByCondition(Map condition);

}
