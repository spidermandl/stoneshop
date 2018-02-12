package org.goshop.pay.service;

import org.goshop.pay.i.PredepositLogService;
import org.goshop.pay.mapper.master.GsPredepositLogMapper;
import org.goshop.pay.pojo.GsPredepositLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 12/02/2018.
 */
@Service("predepositLogService")
public class PredepositLogServiceImpl implements PredepositLogService {
    @Autowired
    GsPredepositLogMapper gsPredepositLogMapper;

    @Override
    public int update(GsPredepositLog log) {
        return gsPredepositLogMapper.updateByPrimaryKeySelective(log);
    }

    @Override
    public int save(GsPredepositLog log) {
        if (log.getDeletestatus()==null)
            log.setDeletestatus(false);
        return gsPredepositLogMapper.insertSelective(log);
    }
}
