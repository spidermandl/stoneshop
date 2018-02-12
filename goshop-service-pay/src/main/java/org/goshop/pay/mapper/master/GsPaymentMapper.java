package org.goshop.pay.mapper.master;

import org.goshop.pay.pojo.GsPayment;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;

public interface GsPaymentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsPaymentWithBLOBs record);

    int insertSelective(GsPaymentWithBLOBs record);

    GsPaymentWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsPaymentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GsPaymentWithBLOBs record);

    int updateByPrimaryKey(GsPayment record);
}
