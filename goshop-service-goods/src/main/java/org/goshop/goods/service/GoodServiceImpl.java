package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.mapper.master.GsGoodsMapper;
import org.goshop.goods.mapper.master.GsGoodsPhotoMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsPhotoMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsUgcMapper;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsUgc;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 24/11/2017.
 */
@Service("goodsService")
public class GoodServiceImpl implements GoodsService {

    @Autowired
    GsGoodsMapper gsGoodsMapper;
    @Autowired
    ReadGsGoodsMapper readGsGoodsMapper;

    @Autowired
    GsGoodsPhotoMapper gsGoodsPhotoMapper;
    @Autowired
    ReadGsGoodsPhotoMapper readGsGoodsPhotoMapper;

    @Autowired
    ReadGsGoodsUgcMapper readGsGoodsUgcMapper;

    @Override
    public GsGoodsWithBLOBs findOne(Long id) {
        return gsGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsGoodsWithBLOBs> findGoodsByMainPhoto(GsGoodsAccessory accessory) {
        return readGsGoodsMapper.findGoodsByMainPhoto(accessory.getId());
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

    @Override
    public int delete(Long id) {
        return gsGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<GsGoodsWithBLOBs> findByCondition(Map condition,Integer curPage,Integer pageSize ) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsWithBLOBs> list = findByCondition(condition);
        return new PageInfo<GsGoodsWithBLOBs>(list);
    }

    @Override
    public List<GsGoodsWithBLOBs> findByCondition(Map condition) {
        if (condition.containsKey("user_class_id")){//有用户分类条件
            Long classId = Long.parseLong(condition.get("user_class_id").toString());
            List<GsGoodsUgc> ugcs = readGsGoodsUgcMapper.findByUserClassId(classId);
            if (ugcs.size()>0){
                List<Long> ids = new ArrayList<>();
                condition.put("ids",ids);
                for (GsGoodsUgc ug:ugcs){
                    ids.add(ug.getGoodsId());
                }
            }
        }
        return readGsGoodsMapper.findByCondition(condition);
    }

}
