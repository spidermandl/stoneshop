package org.goshop.order.i;

import org.goshop.order.pojo.GsOrderLog;

/**
 * Created by Desmond on 07/02/2018.
 */
public interface OrderFormLogService {
    int save(GsOrderLog log);
}
