package org.goshop.order.service;

import org.goshop.order.i.OrderFormLogService;
import org.goshop.order.i.OrderFormService;
import org.goshop.order.mapper.master.GsOrderLogMapper;
import org.goshop.order.pojo.GsOrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 07/02/2018.
 */
@Service("orderFormLogService")
public class OrderFormLogServiceImpl implements OrderFormLogService{

    @Autowired
    GsOrderLogMapper gsOrderLogMapper;

    @Override
    public int save(GsOrderLog log) {
        if (log.getDeletestatus()==null)
            log.setDeletestatus(false);
        return gsOrderLogMapper.insertSelective(log);
    }
}
