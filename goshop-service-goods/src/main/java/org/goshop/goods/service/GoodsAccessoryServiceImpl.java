package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsAccessoryService;
import org.goshop.goods.mapper.master.GsGoodsAccessoryMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsAccessoryMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsBrandMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsPhotoMapper;
import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsPhoto;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("goodsAccessoryService")
public class GoodsAccessoryServiceImpl implements GoodsAccessoryService {

    @Autowired
    GsGoodsAccessoryMapper gsGoodsAccessoryMapper;

    @Autowired
    ReadGsGoodsAccessoryMapper readGsGoodsAccessoryMapper;
    @Autowired
    ReadGsGoodsPhotoMapper readGsGoodsPhotoMapper;

    @Override
    public GsGoodsAccessory findOne(Long id) {
        return readGsGoodsAccessoryMapper.selectByPrimaryKey(id);
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
    public int update(GsGoodsAccessory accessory) {
        return gsGoodsAccessoryMapper.updateByPrimaryKey(accessory);
    }

    @Override
    public PageInfo<GsGoodsAccessory> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsAccessory> list = readGsGoodsAccessoryMapper.findAll();
        return new PageInfo<GsGoodsAccessory>(list);
    }

    @Override
    public PageInfo<GsGoodsAccessory> findByUserId(User user, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsAccessory> list = readGsGoodsAccessoryMapper.selectByUserId(user.getId());
        return new PageInfo<GsGoodsAccessory>(list);
    }

    @Override
    public List<GsGoodsAccessory> findByGoodsId(Long goodsId) {
        List<GsGoodsPhoto> photos = readGsGoodsPhotoMapper.findByGoodsId(goodsId);
        List<Long> ids = new ArrayList<>();
        for (GsGoodsPhoto gp:photos){
            ids.add(gp.getPhotoId());
        }
        if (ids.size()==0)
            return new ArrayList<>();
        return readGsGoodsAccessoryMapper.selectByPrimaryKeys(ids);
    }

}
