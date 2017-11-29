package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.mapper.master.GsGoodsMapper;
import org.goshop.goods.mapper.master.GsGoodsPhotoMapper;
import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("goodsService")
public class GoodServiceImpl implements GoodsService {

    @Autowired
    GsGoodsMapper gsGoodsMapper;

    @Autowired
    GsGoodsPhotoMapper gsGoodsPhotoMapper;

    @Override
    public GsGoodsWithBLOBs findOne(Long id) {
        return gsGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsGoodsWithBLOBs> findGoodsByMainPhoto(GsGoodsAccessory accessory) {
        return gsGoodsMapper.findByMainPhoto(accessory.getId());
    }

    @Override
    public List<GsGoodsWithBLOBs> findGoodsByAccessoryId(GsGoodsAccessory accessory) {
        return null;
    }

    @Override
    public int removeLinkByAccessoryId(GsGoodsAccessory accessory) {
        return gsGoodsPhotoMapper.deleteByAccessoryId(accessory.getId());
    }

    @Override
    public int update(GsGoodsWithBLOBs goods) {
        return gsGoodsMapper.updateByPrimaryKeyWithBLOBs(goods);
    }

    @Override
    public long save(GsGoodsWithBLOBs goods) {
        if (goods.getDeliveryStatus()==null){
            goods.setDeletestatus(false);
        }
        long ret = gsGoodsMapper.insert(goods);
        return ret;
    }

}
