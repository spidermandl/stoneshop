package org.goshop.pay.mapper.read;

import org.goshop.pay.pojo.GsPayment;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ReadGsPaymentMapper {

    GsPaymentWithBLOBs selectByPrimaryKey(Long id);

    List<GsPaymentWithBLOBs> selectByCondition(Map condition);
}
