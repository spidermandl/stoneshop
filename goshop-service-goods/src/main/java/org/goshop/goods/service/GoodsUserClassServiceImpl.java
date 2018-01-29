package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsUserClassService;
import org.goshop.goods.mapper.master.GsGoodsUserClassMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsUserClassMapper;
import org.goshop.goods.pojo.GsGoodsUserClass;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 30/11/2017.
 */
@Service("goodsUserClassService")
public class GoodsUserClassServiceImpl implements GoodsUserClassService {
    @Autowired
    GsGoodsUserClassMapper gsGoodsUserClassMapper;

    @Autowired
    ReadGsGoodsUserClassMapper readGsGoodsUserClassMapper;

    @Override
    public GsGoodsUserClass findOne(Long id) {
        return readGsGoodsUserClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<GsGoodsUserClass> findRootClassByUserId(User user,Integer currentPage,Integer pageSize) {
        PageUtils.startPage(currentPage,pageSize);
        List<GsGoodsUserClass> list = readGsGoodsUserClassMapper.selectByUserIdAndNullParent(user.getId(),null);
        return new PageInfo<>(list);
    }

    @Override
    public List<GsGoodsUserClass> findByUserIdAndParentId(Long userId, Long parentId,Boolean display) {
        if (parentId == null)
            return readGsGoodsUserClassMapper.selectByUserIdAndNullParent(userId,display);
        return readGsGoodsUserClassMapper.selectByUserIdAndParentId(userId,parentId);
    }

    @Override
    public List<GsGoodsUserClass> findByCondition(Map condition) {
        return readGsGoodsUserClassMapper.selectByCondition(condition);
    }

    @Override
    public Long save(GsGoodsUserClass userClass) {
        if(userClass.getDeletestatus()==null)
            userClass.setDeletestatus(false);
        if(userClass.getLevel()==null)
            userClass.setLevel(0);
        gsGoodsUserClassMapper.insert(userClass);
        return userClass.getUserId();
    }

    @Override
    public int update(GsGoodsUserClass userClass) {
        return gsGoodsUserClassMapper.updateByPrimaryKeySelective(userClass);
    }

    @Override
    public int delete(Long id) {
        return gsGoodsUserClassMapper.deleteByPrimaryKey(id);
    }
}
