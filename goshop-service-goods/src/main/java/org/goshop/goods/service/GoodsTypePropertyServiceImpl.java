package org.goshop.goods.service;

import org.goshop.goods.i.GoodsTypePropertyService;
import org.goshop.goods.mapper.read.ReadGsGoodsTypePropertyMapper;
import org.goshop.goods.pojo.GsGoodsTypeProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 23/01/2018.
 */
@Service("goodsTypePropertyService")
public class GoodsTypePropertyServiceImpl implements GoodsTypePropertyService {

    @Autowired
    ReadGsGoodsTypePropertyMapper readGsGoodsTypePropertyMapper;

    @Override
    public GsGoodsTypeProperty findOne(Long id) {
        return readGsGoodsTypePropertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Long> selectByPropertyGroup(List condition) {
        return readGsGoodsTypePropertyMapper.selectByPropertyGroup(condition);
    }
}
