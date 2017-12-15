package org.goshop.goods.service;

import com.github.pagehelper.PageInfo;
import org.goshop.assets.i.AccessoryService;
import org.goshop.assets.pojo.GsAccessory;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.mapper.master.*;
import org.goshop.goods.mapper.read.*;
import org.goshop.goods.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    ReadGsGoodsBrandMapper readGsGoodsBrandMapper;
    @Autowired
    ReadGsGoodsClassMapper readGsGoodsClassMapper;
    @Autowired
    ReadGsTransportMapper readGsTransportMapper;
    @Autowired
    ReadGsGoodsPropertyMapper readGsGoodsPropertyMapper;
    @Autowired
    ReadGsGoodsCombinMapper readGsGoodsCombinMapper;

    @Autowired
    GsGoodsUgcMapper gsGoodsUgcMapper;
    @Autowired
    GsGoodsPropertyMapper gsGoodsPropertyMapper;
    @Autowired
    GsGoodsCombinMapper gsGoodsCombinMapper;
    @Autowired
    GsGoodsPhotoMapper gsGoodsPhotoMapper;

    @Autowired
    AccessoryService accessoryService;

    @Override
    public GsGoodsWithBLOBs findOne(Long id) {
        GsGoodsWithBLOBs one = gsGoodsMapper.selectByPrimaryKey(id);
        if (one.getGoodsMainPhotoId()!=null)
            one.setGoods_main_photo(accessoryService.findOne(one.getGoodsMainPhotoId()));
        if (one.getGoodsBrandId()!=null)
            one.setGoodsBrand(readGsGoodsBrandMapper.selectByPrimaryKey(one.getGoodsBrandId()));
        if (one.getGcId()!=null)
            one.setGc(readGsGoodsClassMapper.selectByPrimaryKey(one.getGcId()));
        if (one.getTransportId()!=null)
            one.setTransport(readGsTransportMapper.selectByPrimaryKey(one.getTransportId()));
        return one;
    }

    @Override
    public List<GsGoodsWithBLOBs> findGoodsByMainPhoto(GsAccessory accessory) {
        return readGsGoodsMapper.findGoodsByMainPhoto(accessory.getId());
    }

    @Override
    public List<GsGoodsWithBLOBs> findGoodsByAccessoryId(GsAccessory accessory) {
        return null;
    }

    @Override
    public int removeLinkByAccessoryId(GsAccessory accessory) {
        return gsGoodsPhotoMapper.deleteByAccessoryId(accessory.getId());
    }

    @Transactional
    @Override
    public int update(GsGoodsWithBLOBs goods) {
        /*******************************************************/
        List<GsGoodsUgc> add_ugcs = new ArrayList<>();
        List<GsGoodsUgc> db_ugcs = readGsGoodsUgcMapper.findByGoodsId(goods.getId());
        for ( GsGoodsUserClass userClass  : goods.getGoodsUgcs()){
            GsGoodsUgc del = null;
            for (GsGoodsUgc ugc:db_ugcs){
                if (ugc.getClassId().equals(userClass.getId())){
                    del=ugc;
                    break;
                }
            }
            if (del==null)//不需要更改项目
                db_ugcs.remove(del);
            else {//增加项目
                GsGoodsUgc ugc = new GsGoodsUgc();
                ugc.setGoodsId(goods.getId());
                ugc.setClassId(userClass.getId());
                add_ugcs.add(ugc);
            }

        }
        if (add_ugcs.size() > 0)
            gsGoodsUgcMapper.insertBatch(add_ugcs);
        if (db_ugcs.size() > 0)
            gsGoodsUgcMapper.deleteByGoodsAndClassId(db_ugcs);
        /*******************************************************/
        /*******************************************************/
        List<GsGoodsProperty> add_gps = new ArrayList<>();
        List<GsGoodsProperty> db_gps = readGsGoodsPropertyMapper.findByGoodsId(goods.getId());
        for ( GsGoodsSpecProperty specProperty : goods.getGoodsSpecs()){
            GsGoodsProperty del = null;
            for (GsGoodsProperty gp:db_gps){
                if (gp.getSpecId().equals(specProperty.getId())){
                    del=gp;
                    break;
                }
            }
            if (del==null)//不需要更改项目
                db_gps.remove(del);
            else {//增加项目
                GsGoodsProperty gp = new GsGoodsProperty();
                gp.setGoodsId(goods.getId());
                gp.setSpecId(specProperty.getId());
                add_gps.add(gp);
            }

        }
        if (add_gps.size() > 0)
            gsGoodsPropertyMapper.insertBatch(add_gps);
        if (db_gps.size() > 0)
            gsGoodsPropertyMapper.deleteByGoodsAndSpecId(db_gps);
        /*******************************************************/
        /*******************************************************/
        List<GsGoodsCombin> add_coms = new ArrayList<>();
        List<GsGoodsCombin> db_coms = readGsGoodsCombinMapper.findByGoodsId(goods.getId());
        for ( GsGoodsWithBLOBs gCom : goods.getCombinGoods()){
            GsGoodsCombin del = null;
            for (GsGoodsCombin dCom:db_coms){
                if (dCom.getCombinGoodsId().equals(gCom.getId())){
                    del=dCom;
                    break;
                }
            }
            if (del==null)//不需要更改项目
                db_coms.remove(del);
            else {//增加项目
                GsGoodsCombin com = new GsGoodsCombin();
                com.setGoodsId(goods.getId());
                com.setCombinGoodsId(gCom.getId());
                add_coms.add(com);
            }

        }
        if (add_coms.size() > 0)
            gsGoodsCombinMapper.insertBatch(add_coms);
        if (db_coms.size() > 0)
            gsGoodsCombinMapper.deleteByGoodsAndCombinId(db_coms);

        /*******************************************************/
        /*******************************************************/
        List<GsGoodsPhoto> add_photos = new ArrayList<>();
        List<GsGoodsPhoto> db_photos = readGsGoodsPhotoMapper.findByGoodsId(goods.getId());
        for ( GsAccessory gAcc : goods.getGoodsPhotos()){
            GsGoodsPhoto del = null;
            for (GsGoodsPhoto dAcc:db_photos){
                if (dAcc.getPhotoId().equals(gAcc.getId())){
                    del=dAcc;
                    break;
                }
            }
            if (del==null)//不需要更改项目
                db_photos.remove(del);
            else {//增加项目
                GsGoodsPhoto photo = new GsGoodsPhoto();
                photo.setGoodsId(goods.getId());
                photo.setPhotoId(gAcc.getId());
                add_photos.add(photo);
            }

        }
        if (add_photos.size() > 0)
            gsGoodsPhotoMapper.insertBatch(add_photos);
        if (db_photos.size() > 0)
            gsGoodsPhotoMapper.deleteByGoodsAndAccessoryId(db_photos);

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
        for (GsAccessory accessory:goods.getGoodsPhotos()){
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
        return new PageInfo<>(list);
    }

    @Override
    public List<GsGoodsWithBLOBs> findByCondition(Map condition) {
        if (condition.containsKey("user_class_id")){//有用户分类条件
            Long classId = Long.parseLong(condition.get("user_class_id").toString());
            List<GsGoodsUgc> ugcs = readGsGoodsUgcMapper.findByUserClassId(classId);
            List<Long> ids = new ArrayList<>();
            ids.add(-1L);
            condition.put("ids",ids);
            for (GsGoodsUgc ug:ugcs){
                ids.add(ug.getGoodsId());
            }
        }
//
//        if (condition.containsKey("goods_photos")){//有goods图片展示
//            Long classId = Long.parseLong(condition.get("user_class_id").toString());
//            List<GsGoodsPhoto> photos = readGsGoodsPhotoMapper.findByGoodsId("goods_photos")
//        }
        return readGsGoodsMapper.findByCondition(condition);
    }

    @Override
    public List<GsAccessory> findPhotoByGoodsId(Long goodsId) {
        List<GsGoodsPhoto> photos = readGsGoodsPhotoMapper.findByGoodsId(goodsId);
        List<Long> ids = new ArrayList<>();
        for (GsGoodsPhoto gp:photos){
            ids.add(gp.getPhotoId());
        }
        if (ids.size()==0)
            return new ArrayList<>();
        return accessoryService.findByIds(ids);
    }

}
