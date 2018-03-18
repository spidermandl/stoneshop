package org.goshop.pay.i;

import org.goshop.pay.pojo.GsRefundLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 27/02/2018.
 */
public interface RefundService {

    List<GsRefundLog> findByCondition(Map condition);

}
