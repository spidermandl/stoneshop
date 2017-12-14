package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.AccessoryService;
import org.goshop.goods.mapper.master.GsAccessoryMapper;
import org.goshop.goods.mapper.read.ReadGsAccessoryMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsPhotoMapper;
import org.goshop.goods.pojo.GsAccessory;
import org.goshop.goods.pojo.GsGoodsPhoto;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("accessoryService")
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    GsAccessoryMapper gsAccessoryMapper;

    @Autowired
    ReadGsAccessoryMapper readGsAccessoryMapper;
    @Autowired
    ReadGsGoodsPhotoMapper readGsGoodsPhotoMapper;

    @Override
    public GsAccessory findOne(Long id) {
        return readGsAccessoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(GsAccessory accessory) {
        return gsAccessoryMapper.deleteByPrimaryKey(accessory.getId());
    }

    @Override
    public long save(GsAccessory accessory) {
        if(accessory.getDeletestatus()==null) {
            accessory.setDeletestatus(false);
        }
        gsAccessoryMapper.insert(accessory);
        return  accessory.getId();
    }

    @Override
    public int update(GsAccessory accessory) {
        return gsAccessoryMapper.updateByPrimaryKey(accessory);
    }

    @Override
    public PageInfo<GsAccessory> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsAccessory> list = readGsAccessoryMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<GsAccessory> findByUserId(User user, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsAccessory> list = readGsAccessoryMapper.selectByUserId(user.getId());
        return new PageInfo<>(list);
    }

    @Override
    public List<GsAccessory> findByGoodsId(Long goodsId) {
        List<GsGoodsPhoto> photos = readGsGoodsPhotoMapper.findByGoodsId(goodsId);
        List<Long> ids = new ArrayList<>();
        for (GsGoodsPhoto gp:photos){
            ids.add(gp.getPhotoId());
        }
        if (ids.size()==0)
            return new ArrayList<>();
        return readGsAccessoryMapper.selectByPrimaryKeys(ids);
    }

}
