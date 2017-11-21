package org.goshop.goods.service;

import org.goshop.goods.i.GoodsCommonService;
import org.goshop.goods.mapper.master.GoodsCommonMapper;
import org.goshop.goods.pojo.GoodsCommonWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsCommonService")
public class GoodsCommonServiceImpl implements GoodsCommonService {

    @Autowired
    GoodsCommonMapper goodsCommonMapper;

    @Override
    public int save(GoodsCommonWithBLOBs goodsCommonWithBLOBs) {
        return goodsCommonMapper.insert(goodsCommonWithBLOBs);
    }
}
