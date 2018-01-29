package org.goshop.store.service;

import com.github.pagehelper.PageInfo;
import org.goshop.assets.i.AccessoryService;
import org.goshop.common.utils.PageUtils;
import org.goshop.store.i.StoreService;
import org.goshop.store.mapper.master.StoreMapper;
import org.goshop.store.mapper.read.*;
import org.goshop.store.pojo.GsStoreSlide;
import org.goshop.store.pojo.Store;
import org.goshop.store.pojo.StoreJoin;
import org.goshop.store.pojo.StoreWithBLOBs;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;
    @Autowired
    ReadStoreMapper readStoreMapper;
    @Autowired
    ReadStoreGradeMapper readStoreGradeMapper;
    @Autowired
    ReadGsAreaMapper readGsAreaMapper;
    @Autowired
    ReadGsStoreSlideMapper readGsStoreSlideMapper;
    @Autowired
    ReadGsStorePointMapper readGsStorePointMapper;

    @Autowired
    AccessoryService accessoryService;

    @Override
    public PageInfo<Store> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<Store> list=readStoreMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public int openStore(StoreJoin storeJoin) {
        StoreWithBLOBs store = new StoreWithBLOBs();
        store.setStoreName(storeJoin.getStoreName());
        store.setGradeId(storeJoin.getSgId());
        store.setMemberName(storeJoin.getMemberName());
        store.setMemberId(storeJoin.getMemberId());
        store.setSellerName(storeJoin.getSellerName());
        store.setScId(storeJoin.getScId());
        store.setStoreCompanyName(storeJoin.getCompanyName());
        store.setStoreAddress(storeJoin.getCompanyAddressDetail());
        store.setStoreTel(storeJoin.getContactsPhone());
        store.setStoreImage1(storeJoin.getBusinessLicenceNumberElectronic());
        store.setStoreState(2);
        store.setStoreTime(new Date());
        return this.save(store);
    }

    @Override
    public int save(StoreWithBLOBs store) {
        return storeMapper.insertSelective(store);
    }

    @Override
    public Store getCurrentStore(User user) {
        return readStoreMapper.findByMemberId(user.getId());
    }

    @Override
    public PageInfo<Store> find(Integer gradeId, String sellerName, String storeType, String storeName, Integer curPage, Integer pageSize) {

        //店铺状态
        Integer storeState =null;
        //是否即将过期
        Boolean isExpire=null;
        //是否过期
        Boolean isExpired=null;

        if(StringUtils.hasText(storeType)) {
            switch (storeType) {
                case "open":
                    storeState = 1;
                    break;
                case "close":
                    storeState = 0;
                    break;
                case "expire":
                    isExpire = true;
                    break;
                case "expired":
                    isExpired = true;
                    break;
            }
        }
        PageUtils.startPage(curPage,pageSize);
        List<Store> list=readStoreMapper.find(gradeId,sellerName,storeName,storeState,isExpire,isExpired);
        return new PageInfo<>(list);
    }

    @Override
    public StoreWithBLOBs findOne(Long storeId) {
        StoreWithBLOBs store = readStoreMapper.selectByPrimaryKey(storeId);
        fillForeignTable(store);
        return store;
    }

    @Override
    public void update(StoreWithBLOBs store) {
        storeMapper.updateByPrimaryKeyWithBLOBs(store);
    }

    @Override
    public StoreWithBLOBs findByMemberId(Long userId) {
        StoreWithBLOBs store = readStoreMapper.findByMemberId(userId);
        return store;
    }

    @Override
    public Store findBySecondDomain(String domain) {
        Store store = readStoreMapper.selectBySecondDomain(domain);
        fillForeignTable(store);
        return store;
    }

    @Override
    public PageInfo<Store> findByCondition(Map map, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<Store> list=readStoreMapper.selectByCondition(map);
        return new PageInfo<>(list);
    }

    @Override
    public List<Long> findIndexByCondition(Map map) {
        return readStoreMapper.selectIndexByCondition(map);
    }

    private void fillForeignTable(Store store){
        if (store!=null) {
            if (store.getGradeId()!=null)
                store.setStoreGrade(readStoreGradeMapper.selectByPrimaryKey(store.getGradeId()));
            if (store.getAreaId()!=null)
                store.setArea(readGsAreaMapper.selectByPrimaryKey(store.getAreaId()));
            if (store.getStoreLabel()!=null)
                store.setLogo(accessoryService.findOne(store.getStoreLabel()));
            if (store.getStoreBanner()!=null)
                store.setBanner(accessoryService.findOne(store.getStoreBanner()));
            if (store.getStoreImage()!=null)
                store.setCard(accessoryService.findOne(store.getStoreImage()));
            if (store.getStoreImage1()!=null)
                store.setLicense(accessoryService.findOne(store.getStoreImage1()));
            store.setPoint(readGsStorePointMapper.selectByStoreId(store.getStoreId()));
            List<GsStoreSlide> slides = readGsStoreSlideMapper.selectByStoreId(store.getStoreId());
            for (GsStoreSlide s:slides){
                s.setAcc(accessoryService.findOne(s.getAccId()));
            }
            store.setSlides(slides);
        }
    }
}
