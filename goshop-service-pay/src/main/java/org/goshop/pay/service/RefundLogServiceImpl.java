package org.goshop.pay.service;

import org.goshop.pay.i.RefundLogService;
import org.goshop.pay.mapper.master.GsRefundLogMapper;
import org.goshop.pay.pojo.GsRefundLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 01/03/2018.
 */
@Service("refundLogService")
public class RefundLogServiceImpl implements RefundLogService {

    @Autowired
    GsRefundLogMapper gsRefundLogMapper;

    @Override
    public int save(GsRefundLog log) {
        return gsRefundLogMapper.insertSelective(log);
    }
}
