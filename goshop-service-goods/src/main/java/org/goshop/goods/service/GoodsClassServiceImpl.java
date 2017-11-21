package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.exception.MapperException;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.mapper.master.GsGoodsClassMapper;
import org.goshop.goods.mapper.master.GsGoodsMapper;
import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.mapper.master.GoodsClassMapper;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsClassService")
public class GoodsClassServiceImpl implements GoodsClassService {

    @Autowired
    GoodsClassMapper goodsClassMapper;

    @Autowired
    GsGoodsClassMapper gsGoodsClassMapper;

    @Override
    public List<GoodsClass> findTreeByGcParentId(Integer gcParentId) {
        return goodsClassMapper.findTreeByGcParentId(gcParentId);
    }

    @Override
    public List<GsGoodsClass> findByGcParentId(Long parentId) {
        if(parentId==null){
            parentId=0L;
        }
        return gsGoodsClassMapper.findByGcParentId(parentId);
    }

    @Override
    public int save(GoodsClass goodsClass) {
        return goodsClassMapper.insert(goodsClass);
    }

    @Override
    public boolean checkByIdNameParentId(Integer gcId, String gcName, Integer gcParentId) {
        List<GoodsClass> list = goodsClassMapper.findByGcNameGcParentId(gcName,gcParentId);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(gcId==null){
                return false;
            }else if(list.get(0).getGcId()!=gcId){
                return false;
            }
        }
        return true;
    }

    @Override
    public PageInfo<GoodsClass> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GoodsClass> list=goodsClassMapper.findAll();
        return new PageInfo<GoodsClass>(list);
    }

    @Override
    public PageInfo<GoodsClass> findGradeByGcParentId(Integer gcParentId, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GoodsClass> list=goodsClassMapper.findGradeByGcParentId(gcParentId);
        return new PageInfo<GoodsClass>(list);
    }

    @Override
    public List<GoodsClass> findGradeByGcParentId(Integer gcParentId) {
        return goodsClassMapper.findGradeByGcParentId(gcParentId);
    }

    @Override
    public GsGoodsClass findOne(Long gcId) {
        return gsGoodsClassMapper.selectByPrimaryKey(gcId);
        //return goodsClassMapper.selectByPrimaryKey(gcId);
    }

    @Override
    public int delete(Integer id) {
        return goodsClassMapper.deleteByPrimaryKey(id);
    }
}
