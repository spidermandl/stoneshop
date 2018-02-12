package org.goshop.order.service;

import org.goshop.order.i.IntegralGoodsOrderService;
import org.goshop.order.mapper.read.ReadGsIntegralGoodsorderMapper;
import org.goshop.order.pojo.GsIntegralGoodsorderWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 12/02/2018.
 */
@Service("integralGoodsOrderService")
public class IntegralGoodsOrderServiceImpl implements IntegralGoodsOrderService {

    @Autowired
    ReadGsIntegralGoodsorderMapper readGsIntegralGoodsorderMapper;

    @Override
    public GsIntegralGoodsorderWithBLOBs findOne(Long id) {
        return readGsIntegralGoodsorderMapper.selectByPrimaryKey(id);
    }
}
