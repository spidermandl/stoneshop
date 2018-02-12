package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsCartGsp;

import java.util.List;

public interface ReadGsCartGspMapper {

    List<GsCartGsp> selectByCartId(Long cart_id);

}
