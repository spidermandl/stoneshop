package org.goshop.order.i;

import org.goshop.order.pojo.GsOrderLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 07/02/2018.
 */
public interface OrderFormLogService {
    List<GsOrderLog> findByCondition(Map condition);
    int save(GsOrderLog log);
}
