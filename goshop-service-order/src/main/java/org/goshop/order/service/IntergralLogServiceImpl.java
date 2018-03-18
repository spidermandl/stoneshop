package org.goshop.order.service;

import org.goshop.order.i.IntergralLogService;
import org.goshop.order.mapper.master.GsIntegrallogMapper;
import org.goshop.order.pojo.GsIntegrallog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 02/03/2018.
 */
@Service("intergralLogService")
public class IntergralLogServiceImpl implements IntergralLogService{

    @Autowired
    GsIntegrallogMapper gsIntegrallogMapper;

    @Override
    public int save(GsIntegrallog log) {
        return gsIntegrallogMapper.insertSelective(log);
    }
}
