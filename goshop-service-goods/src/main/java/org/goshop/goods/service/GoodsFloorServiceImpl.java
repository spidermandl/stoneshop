package org.goshop.goods.service;

import org.goshop.goods.i.GoodsFloorService;
import org.goshop.goods.mapper.read.ReadGsGoodsFloorMapper;
import org.goshop.goods.pojo.GsGoodsFloorWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 08/01/2018.
 */
@Service("goodsFloorService")
public class GoodsFloorServiceImpl implements GoodsFloorService {

    @Autowired
    ReadGsGoodsFloorMapper readGsGoodsFloorMapper;

    @Override
    public List<GsGoodsFloorWithBLOBs> findByCondition(Map condition) {
        return readGsGoodsFloorMapper.selectByCondition(condition);
    }
}
