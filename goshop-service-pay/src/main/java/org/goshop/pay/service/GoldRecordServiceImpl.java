package org.goshop.pay.service;

import org.goshop.pay.i.GoldRecordService;
import org.goshop.pay.mapper.read.ReadGsGoldRecordMapper;
import org.goshop.pay.pojo.GsGoldRecordWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 12/02/2018.
 */
@Service("goldRecordService")
public class GoldRecordServiceImpl implements GoldRecordService {
    @Autowired
    ReadGsGoldRecordMapper readGsGoldRecordMapper;

    @Override
    public GsGoldRecordWithBLOBs findOne(Long id) {
        return readGsGoldRecordMapper.selectByPrimaryKey(id);
    }
}
