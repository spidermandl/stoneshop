package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsConsultService;
import org.goshop.goods.mapper.master.GsGoodsConsultMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsConsultMapper;
import org.goshop.goods.pojo.GsGoodsConsultWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 23/01/2018.
 */
@Service("goodsConsultService")
public class GoodsConsultServiceImpl implements GoodsConsultService {

    @Autowired
    GsGoodsConsultMapper gsGoodsConsultMapper;

    @Autowired
    ReadGsGoodsConsultMapper readGsGoodsConsultMapper;

    @Override
    public GsGoodsConsultWithBLOBs findOne(Long id) {
        return readGsGoodsConsultMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(GsGoodsConsultWithBLOBs consult) {
        if (consult.getDeletestatus()==null)
            consult.setDeletestatus(false);
        return gsGoodsConsultMapper.insertSelective(consult);
    }

    @Override
    public List<GsGoodsConsultWithBLOBs> findByCondition(Map condition) {
        return readGsGoodsConsultMapper.selectByCondition(condition);
    }

    @Override
    public PageInfo<GsGoodsConsultWithBLOBs> findByCondition(Map condition,Integer curPage,Integer pageSize ) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsConsultWithBLOBs> list = findByCondition(condition);
        return new PageInfo<>(list);
    }

}
