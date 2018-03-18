package org.goshop.pay.service;

import org.goshop.pay.i.RefundService;
import org.goshop.pay.mapper.read.ReadGsRefundLogMapper;
import org.goshop.pay.pojo.GsRefundLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 27/02/2018.
 */
@Service("refundService")
public class RefundServiceImpl implements RefundService {

    @Autowired
    ReadGsRefundLogMapper readGsRefundLogMapper;

    @Override
    public List<GsRefundLog> findByCondition(Map condition) {
        return readGsRefundLogMapper.selectByCondition(condition);
    }
}
