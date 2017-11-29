package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsAccessoryService;
import org.goshop.goods.mapper.master.GsGoodsAccessoryMapper;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("goodsAccessoryService")
public class GoodsAccessoryServiceImpl implements GoodsAccessoryService {

    @Autowired
    GsGoodsAccessoryMapper gsGoodsAccessoryMapper;

    @Override
    public GsGoodsAccessory findOne(Long id) {
        return gsGoodsAccessoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(GsGoodsAccessory accessory) {
        return gsGoodsAccessoryMapper.deleteByPrimaryKey(accessory.getId());
    }

    @Override
    public long save(GsGoodsAccessory accessory) {
        if(accessory.getDeletestatus()==null) {
            accessory.setDeletestatus(false);
        }
        int ret = gsGoodsAccessoryMapper.insert(accessory);
        return  accessory.getId();
    }

    @Override
    public PageInfo<GsGoodsAccessory> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsAccessory> list = gsGoodsAccessoryMapper.findAll();
        return new PageInfo<GsGoodsAccessory>(list);
    }

    @Override
    public PageInfo<GsGoodsAccessory> findByUserId(User user, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsAccessory> list = gsGoodsAccessoryMapper.selectByUserId(user.getId());
        return new PageInfo<GsGoodsAccessory>(list);
    }

}
