package org.goshop.store.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.exception.MapperException;
import org.goshop.common.exception.PageException;
import org.goshop.common.utils.PageUtils;
import org.goshop.store.i.StoreClassService;
import org.goshop.store.mapper.master.StoreClassMapper;
import org.goshop.store.mapper.read.ReadStoreClassMapper;
import org.goshop.store.pojo.GsStoreClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("storeClassService")
public class StoreClassServiceImpl implements StoreClassService {

    @Autowired
    StoreClassMapper storeClassMapper;
    @Autowired
    ReadStoreClassMapper readStoreClassMapper;

    @Override
    public List<GsStoreClass> findAllOrderBySort() {
        return readStoreClassMapper.findAllOrderBySort();
    }

    @Override
    public int save(GsStoreClass storeClass) {
        Assert.hasText(storeClass.getName(),"分类名称不能为空！");
        if(!checkByNameParentId(storeClass.getName(), storeClass.getParentId())){
            throw new PageException("分类名称已经存在！");
        }
        if (storeClass.getDeletestatus()==null)
            storeClass.setDeletestatus(false);
        return storeClassMapper.insert(storeClass);
    }

    @Override
    public boolean checkByNameParentId(String name,Long parentId) {
        int count = readStoreClassMapper.findCountByNameParentId(name,parentId);
        if(count>0) {
            return false;
        }
        return true;
    }

    @Override
    public PageInfo<GsStoreClass> findTreePageByParentId(Integer curPage,Integer pageSize,Long parentId) {
        PageUtils.startPage(curPage,pageSize);
        //2、查询结果
        List<GsStoreClass> list=readStoreClassMapper.findTreeByParentId(parentId);
        //3、取分页后结果
        PageInfo<GsStoreClass> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public GsStoreClass findOne(Long id) {
        return readStoreClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean checkByIdNameParentId(Long id, String name, Long parentId) {
        List<GsStoreClass> list = readStoreClassMapper.findByNameParentId(name,parentId);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(id==null){
                return false;
            }else if(list.get(0).getId()!=id){
                return false;
            }
        }
        return true;
    }

    @Override
    public int update(GsStoreClass storeClass) {
        Assert.hasText(storeClass.getName(),"分类名称不能为空！");
        Assert.notNull(storeClass.getId(),"没有修改id！");
        if(!checkByIdNameParentId(storeClass.getId(),storeClass.getName(), storeClass.getParentId())){
            throw new PageException("分类名称已经存在！");
        }
        return storeClassMapper.updateByPrimaryKeySelective(storeClass);
    }

    @Override
    public int updateSort(Long id, String value) {
        Assert.notNull(id,"id不能为空！");
        Assert.hasText(value,"排序值不能为空！");
        return storeClassMapper.updateSort(id,Integer.valueOf(value));
    }

    @Override
    public int updateName(Long id, String value) {
        Assert.notNull(id,"id不能为空！");
        Assert.hasText(value,"名称不能为空！");
        return storeClassMapper.updateName(id,value);
    }

    @Override
    public List<GsStoreClass> findByParentId(Long parentId) {
        return readStoreClassMapper.findByParentId(parentId);
    }

    @Override
    public int delete(Long id) {
        return storeClassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            this.delete(id);
        }
    }

    @Override
    public List<GsStoreClass> findTreeByParentId(Long parentId) {
        return readStoreClassMapper.findTreeByParentId(parentId);
    }

    @Override
    public PageInfo<GsStoreClass> findGradeByParentId(Integer curPage, Integer pageSize, Long parentId) {
        PageUtils.startPage(curPage,pageSize);
        //2、查询结果
        List<GsStoreClass> list=readStoreClassMapper.findGradeByParentId(parentId);
        //3、取分页后结果
        PageInfo<GsStoreClass> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
