package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsEvaluateService;
import org.goshop.goods.mapper.master.GsGoodsEvaluateMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsEvaluateMapper;
import org.goshop.goods.pojo.GsGoodsEvaluateWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 10/01/2018.
 */
@Service("goodsEvaluateService")
public class GoodsEvaluateServiceImpl implements GoodsEvaluateService{

    @Autowired
    ReadGsGoodsEvaluateMapper readGsGoodsEvaluateMapper;

    @Override
    public List<GsGoodsEvaluateWithBLOBs> findByCondition(Map condition) {
        return readGsGoodsEvaluateMapper.selectByCondition(condition);
    }

    @Override
    public PageInfo<GsGoodsEvaluateWithBLOBs> findByCondition(Map condition, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsEvaluateWithBLOBs> list = readGsGoodsEvaluateMapper.selectByCondition(condition);
        return new PageInfo<>(list);
    }
}
