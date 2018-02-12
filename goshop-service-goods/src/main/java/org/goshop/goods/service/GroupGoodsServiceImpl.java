package org.goshop.goods.service;

import org.goshop.goods.i.GroupGoodsService;
import org.goshop.goods.mapper.master.GsGroupGoodsMapper;
import org.goshop.goods.mapper.read.ReadGsGroupGoodsMapper;
import org.goshop.goods.pojo.GsGroupGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 07/02/2018.
 */
@Service("groupGoodsService")
public class GroupGoodsServiceImpl implements GroupGoodsService {

    @Autowired
    ReadGsGroupGoodsMapper readGsGroupGoodsMapper;
    @Autowired
    GsGroupGoodsMapper gsGroupGoodsMapper;

    @Override
    public GsGroupGoods findOne(Long id) {
        return readGsGroupGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(GsGroupGoods obj) {
        if (obj.getDeletestatus()==null)
            obj.setDeletestatus(false);
        return gsGroupGoodsMapper.insertSelective(obj);
    }

    @Override
    public int update(GsGroupGoods obj) {
        return gsGroupGoodsMapper.updateByPrimaryKeySelective(obj);
    }
}
