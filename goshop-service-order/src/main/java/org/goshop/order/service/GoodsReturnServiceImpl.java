package org.goshop.order.service;

import org.goshop.order.i.GoodsReturnService;
import org.goshop.order.mapper.master.GsGoodsReturnMapper;
import org.goshop.order.mapper.read.ReadGsGoodsReturnMapper;
import org.goshop.order.pojo.GsGoodsReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 02/03/2018.
 */
@Service("goodsReturnService")
public class GoodsReturnServiceImpl implements GoodsReturnService {

    @Autowired
    GsGoodsReturnMapper gsGoodsReturnMapper;
    @Autowired
    ReadGsGoodsReturnMapper readGsGoodsReturnMapper;

    @Override
    public int save(GsGoodsReturn record) {
        return gsGoodsReturnMapper.insertSelective(record);
    }

    @Override
    public GsGoodsReturn findOne(Long id) {
        return readGsGoodsReturnMapper.selectByPrimaryKey(id);
    }
}
