package org.goshop.order.service;

import org.goshop.order.i.GoodsReturnLogService;
import org.goshop.order.mapper.master.GsGoodsReturnlogMapper;
import org.goshop.order.pojo.GsGoodsReturnlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 02/03/2018.
 */
@Service("goodsReturnLogService")
public class GoodsReturnLogServiceImpl implements GoodsReturnLogService{

    @Autowired
    GsGoodsReturnlogMapper gsGoodsReturnlogMapper;

    @Override
    public int save(GsGoodsReturnlog log) {
        return gsGoodsReturnlogMapper.insertSelective(log);
    }
}
