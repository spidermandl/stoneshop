package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.mapper.master.*;
import org.goshop.goods.mapper.read.ReadGsGoodsMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsPhotoMapper;
import org.goshop.goods.mapper.read.ReadGsGoodsUgcMapper;
import org.goshop.goods.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    ReadGsGoodsPhotoMapper readGsGoodsPhotoMapper;

    @Autowired
    ReadGsGoodsUgcMapper readGsGoodsUgcMapper;

    @Autowired
    GsGoodsUgcMapper gsGoodsUgcMapper;
    @Autowired
    GsGoodsPropertyMapper gsGoodsPropertyMapper;
    @Autowired
    GsGoodsCombinMapper gsGoodsCombinMapper;
    @Autowired
    GsGoodsPhotoMapper gsGoodsPhotoMapper;

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

    @Transactional//(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public long save(GsGoodsWithBLOBs goods) {
        if (goods.getDeliveryStatus()==null)
            goods.setDeletestatus(false);
        if (goods.getGoodsClick() == null)
            goods.setGoodsClick(0);
        if (goods.getGoodsInventory() == null)
            goods.setGoodsInventory(0);
        if (goods.getGoodsRecommend() == null)
            goods.setGoodsRecommend(false);
        if (goods.getGoodsSalenum() == null)
            goods.setGoodsSalenum(0);
        if (goods.getGoodsStatus() == null)
            goods.setGoodsStatus(1);
        if (goods.getGoodsTransfee() == null)
            goods.setGoodsTransfee(1);
        if (goods.getStoreRecommend() == null)
            goods.setStoreRecommend(false);
        if (goods.getZtcClickNum() == null)
            goods.setZtcClickNum(0);
        if (goods.getZtcDredgePrice() == null)
            goods.setZtcDredgePrice(0);
        if (goods.getZtcPrice() == null)
            goods.setZtcPrice(0);
        if (goods.getZtcStatus() == null)
            goods.setZtcStatus(0);
        if (goods.getZtcGold() == null)
            goods.setZtcGold(0);
        if (goods.getZtcPayStatus() == null)
            goods.setZtcPayStatus(0);

        gsGoodsMapper.insert(goods);
        long ret = goods.getId();
        List<GsGoodsUgc> ugcs = new ArrayList<>();
        for ( GsGoodsUserClass userClass  : goods.getGoodsUgcs()){
            GsGoodsUgc ugc = new GsGoodsUgc();
            ugc.setGoodsId(ret);
            ugc.setClassId(userClass.getId());
            ugcs.add(ugc);
        }
        if (ugcs.size()>0)
            gsGoodsUgcMapper.insertBatch(ugcs);

        List<GsGoodsProperty> gps = new ArrayList<>();
        for (GsGoodsSpecProperty gsp : goods.getGoodsSpecs()){
            GsGoodsProperty gp = new GsGoodsProperty();
            gp.setGoodsId(ret);
            gp.setSpecId(gsp.getId());
            gps.add(gp);
        }
        if (gps.size()>0)
            gsGoodsPropertyMapper.insertBatch(gps);

        List<GsGoodsCombin> coms = new ArrayList<>();
        for (GsGoodsWithBLOBs gs : goods.getCombinGoods()){
            GsGoodsCombin com = new GsGoodsCombin();
            com.setGoodsId(ret);
            com.setCombinGoodsId(gs.getId());
            coms.add(com);
        }
        if (coms.size()>0)
            gsGoodsCombinMapper.insertBatch(coms);

        List<GsGoodsPhoto> photos = new ArrayList<>();
        for (GsGoodsAccessory accessory:goods.getGoodsPhotos()){
            GsGoodsPhoto gph = new GsGoodsPhoto();
            gph.setGoodsId(ret);
            gph.setPhotoId(accessory.getId());
            photos.add(gph);
        }
        if (photos.size()>0)
            gsGoodsPhotoMapper.insertBatch(photos);

        return ret;
    }

    @Transactional
    @Override
    public int delete(Long id) {
        deletePhotoRelationByGoodsId(id);
        deleteCombinRelationByGoodsId(id);
        deletePropertyRelationByGoodsId(id);
        deleteUGCRelationByGoodsId(id);
        return gsGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deletePhotoRelationByGoodsId(Long id) {
        return gsGoodsPhotoMapper.deleteByGoodsId(id);
    }

    @Override
    public int deletePropertyRelationByGoodsId(Long id) {
        return gsGoodsPropertyMapper.deleteByGoodsId(id);
    }

    @Override
    public int deleteUGCRelationByGoodsId(Long id) {
        return gsGoodsUgcMapper.deleteByGoodsId(id);
    }

    @Override
    public int deleteCombinRelationByGoodsId(Long id) {
        return gsGoodsCombinMapper.deleteByGoodsId(id);
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
