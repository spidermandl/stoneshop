package org.goshop.pay.service;

import org.goshop.pay.i.PaymentService;
import org.goshop.pay.mapper.read.ReadGsPaymentMapper;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 12/02/2018.
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private ReadGsPaymentMapper readGsPaymentMapper;

    @Override
    public GsPaymentWithBLOBs findOne(Long id) {
        return readGsPaymentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsPaymentWithBLOBs> findByCondition(Map condition) {
        return readGsPaymentMapper.selectByCondition(condition);
    }
}
