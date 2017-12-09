package org.goshop.goods.service;

import org.goshop.goods.i.GoodsPropertyService;
import org.goshop.goods.mapper.master.GsGoodsSpecPropertyMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsSpecPropertyMapper;
import org.goshop.goods.pojo.GsGoodsSpecProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 08/12/2017.
 */
@Service("goodsPropertyService")
public class GoodsPropertyServiceImpl implements GoodsPropertyService{

    @Autowired
    GsGoodsSpecPropertyMapper gsGoodsSpecPropertyMapper;

    @Autowired
    ReadGsGoodsSpecPropertyMapper readGsGoodsSpecPropertyMapper;

    @Override
    public GsGoodsSpecProperty findOne(Long id) {
        return readGsGoodsSpecPropertyMapper.selectByPrimaryKey(id);
    }
}
