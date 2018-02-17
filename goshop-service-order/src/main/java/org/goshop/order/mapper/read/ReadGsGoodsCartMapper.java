package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsGoodsCart;

import java.util.List;
import java.util.Map;

public interface ReadGsGoodsCartMapper {

    GsGoodsCart selectByPrimaryKey(Long id);

    List<GsGoodsCart> selectByCondition(Map condition);

    int selectCountByOrderId(Long order_id);
}
