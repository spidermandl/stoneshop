package org.goshop.order.i;

import com.github.pagehelper.PageInfo;
import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 10/01/2018.
 */
public interface OrderFormService {

    GsOrderformWithBLOBs findOne(Long id);

    List<GsOrderform> findByStoreId(Long storeId);

    PageInfo<GsOrderformWithBLOBs> findByCondition(Map map, Integer curPage, Integer pageSize);

    List<GsOrderformWithBLOBs> findByCondition(Map map);

    long save(GsOrderformWithBLOBs form);

    int update(GsOrderformWithBLOBs form);

    int delete(Long id);

}
