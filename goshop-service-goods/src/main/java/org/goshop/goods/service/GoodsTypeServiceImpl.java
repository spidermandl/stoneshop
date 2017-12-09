package org.goshop.goods.service;

import org.goshop.goods.i.GoodsTypeService;
import org.goshop.goods.mapper.master.GsGoodsTypeMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsTypeMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsTypeSpecMapper;
import org.goshop.goods.pojo.GoodsType;
import org.goshop.goods.mapper.master.GoodsTypeMapper;
import org.goshop.goods.pojo.GsGoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    GsGoodsTypeMapper gsGoodsTypeMapper;

    @Autowired
    ReadGsGoodsTypeMapper readGsGoodsTypeMapper;

    @Override
    public List<GsGoodsType> findAll() {
        return readGsGoodsTypeMapper.findAll();
    }

    @Override
    public GsGoodsType findOne(long id) {
        return readGsGoodsTypeMapper.selectByPrimaryKey(id);
    }
}
