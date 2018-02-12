package org.goshop.pay.service;

import org.goshop.pay.i.PredepositLogService;
import org.goshop.pay.i.PredepositService;
import org.goshop.pay.mapper.read.ReadGsPredepositMapper;
import org.goshop.pay.pojo.GsPredepositWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 12/02/2018.
 */
@Service("predepositService")
public class PredepositServiceImpl implements PredepositService {
    @Autowired
    ReadGsPredepositMapper readGsPredepositMapper;

    @Override
    public GsPredepositWithBLOBs findOne(Long id) {
        return readGsPredepositMapper.selectByPrimaryKey(id);
    }
}
