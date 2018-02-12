package org.goshop.order.i;

import com.github.pagehelper.PageInfo;
import org.goshop.order.pojo.GsAddress;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 05/02/2018.
 */
public interface OrderAddressService {

    GsAddress findOne(Long address_id);

    List<GsAddress> findByCondition(Map condition);

    PageInfo<GsAddress> findByCondition(Map condition,Integer curPage,Integer pageSize);

    int save(GsAddress address);

    int update(GsAddress address);

    int delete(Long id);

}
