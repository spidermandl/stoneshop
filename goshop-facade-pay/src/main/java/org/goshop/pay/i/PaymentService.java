package org.goshop.pay.i;

import org.goshop.pay.pojo.GsPaymentWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 12/02/2018.
 */
public interface PaymentService {

    GsPaymentWithBLOBs findOne(Long id);

    List<GsPaymentWithBLOBs> findByCondition(Map condition);
}
