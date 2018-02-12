package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.exception.MapperException;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.mapper.master.GsGoodsClassMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsClassMapper;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsClassWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsClassService")
public class GoodsClassServiceImpl implements GoodsClassService {

    @Autowired
    GsGoodsClassMapper gsGoodsClassMapper;
    @Autowired
    ReadGsGoodsClassMapper readGsGoodsClassMapper;

    @Override
    public List<GsGoodsClass> findTreeByGcParentId(Long gcParentId) {
        return readGsGoodsClassMapper.findTreeByGcParentId(gcParentId);
    }

    @Override
    public List<GsGoodsClass> findByGcParentId(Long parentId) {
        if(parentId==null){
            parentId=0L;
        }
        return readGsGoodsClassMapper.findByGcParentId(parentId);
    }

    @Override
    public int save(GsGoodsClassWithBLOBs goodsClass) {
        if (goodsClass.getDeletestatus()==null)
            goodsClass.setDeletestatus(false);
        return gsGoodsClassMapper.insert(goodsClass);
    }

    @Override
    public boolean checkByIdNameParentId(Long gcId, String gcName, Long gcParentId) {
        List<GsGoodsClass> list = readGsGoodsClassMapper.findByGcNameGcParentId(gcName,gcParentId);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(gcId==null){
                return false;
            }else if(list.get(0).getId()!=gcId){
                return false;
            }
        }
        return true;
    }

    @Override
    public PageInfo<GsGoodsClass> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsClass> list=readGsGoodsClassMapper.findAll();
        return new PageInfo<GsGoodsClass>(list);
    }

    @Override
    public PageInfo<GsGoodsClass> findGradeByGcParentId(Long gcParentId, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsClass> list=readGsGoodsClassMapper.findGradeByGcParentId(gcParentId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<GsGoodsClass> findByParentIdAndDisplay(Long gcParentId, Boolean display, String orderBy, String orderType, Integer curPage, Integer size) {
        PageUtils.startPage(curPage,curPage);
        Map condition = new HashMap();
        condition.put("parent_id",gcParentId);
        condition.put("display",display);
        condition.put("orderBy",orderBy);
        condition.put("orderType",orderType);
        List<GsGoodsClass> list=readGsGoodsClassMapper.findByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public List<GsGoodsClass> findGradeByGcParentId(Long gcParentId) {
        return readGsGoodsClassMapper.findGradeByGcParentId(gcParentId);
    }

    @Override
    public GsGoodsClass findOne(Long gcId) {
        return readGsGoodsClassMapper.selectByPrimaryKey(gcId);
    }

    @Override
    public List<GsGoodsClass> findByCondition(Map condition) {
        return readGsGoodsClassMapper.findByCondition(condition);
    }

    @Override
    public int delete(Long id) {
        return gsGoodsClassMapper.deleteByPrimaryKey(id);
    }
}
