package org.goshop.store.service;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.goshop.common.exception.MapperException;
import org.goshop.common.exception.PageException;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.store.i.*;
import org.goshop.store.mapper.master.StoreJoinMapper;
import org.goshop.store.mapper.read.ReadStoreJoinMapper;
import org.goshop.store.model.JsonManagement;
import org.goshop.store.model.JsonManagementClass;
import org.goshop.store.pojo.*;
import org.goshop.store.model.StoreInfoModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service("storeJoinService")
public class StoreJoinServiceImpl implements StoreJoinService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    private static final String verify_type_fail="fail";

    private static final String verify_type_pass="pass";


    @Autowired
    StoreJoinMapper storeJoinMapper;
    @Autowired
    StoreClassService storeClassService;
    @Autowired
    StoreGradeService storeGradeService;
    @Autowired
    GoodsClassService goodsClassService;
    @Autowired
    StoreAreaService storeAreaService;
    @Autowired
    StoreService storeService;

    @Autowired
    ReadStoreJoinMapper readStoreJoinMapper;

    @Override
    public void applySeller(User user, StoreJoin storeJoin) {
        StoreJoin userStoreJoin = this.getCurrentUserStoreJoin(user.getId());
        storeJoin.setMemberId(user.getId());
        if (userStoreJoin == null) {
            storeJoin.setMemberName(user.getLoginName());
            storeJoinMapper.insertSelective(storeJoin);
        } else {
            storeJoinMapper.updateByPrimaryKeySelective(storeJoin);
        }
    }

    @Override
    public StoreInfoModel applySellerThree(User user, StoreJoin storeJoin) {
        StoreJoin userStoreJoin = getCurrentUserStoreJoin(user.getId());
        Assert.notNull(userStoreJoin, "请先执行第一步！");
        storeJoin.setMemberId(user.getId());
        storeJoinMapper.updateByPrimaryKeySelective(storeJoin);
        //店铺分类
        List<GsStoreClass> storeClassParentList = storeClassService.findTreeByParentId(null);
        //店铺等级
        List<StoreGrade> storeGradeList = storeGradeService.findAll();
        //经营类目
        List<GsGoodsClass> goodsClassParentList = goodsClassService.findByGcParentId(0L);

        StoreInfoModel storeInfoModel = new StoreInfoModel();
        storeInfoModel.setStoreClassParentList(storeClassParentList);
        storeInfoModel.setStoreGradeList(storeGradeList);
        storeInfoModel.setGoodsClassParentList(goodsClassParentList);
        return storeInfoModel;
    }

    @Override
    public boolean verificationSellerName(String sellerName,Long userId) {
        List<StoreJoin> list=readStoreJoinMapper.findBySellerName(sellerName);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(list.get(0).getMemberId()!=userId){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean verificationStoreName(String storeName,Long userId) {
        List<StoreJoin> list=readStoreJoinMapper.findByStoreName(storeName);
        if(list.size()>1){
            throw new MapperException("数据异常");
        }else if(list.size()==1){
            if(list.get(0).getMemberId()!=userId){
                return false;
            }
        }
        return true;
    }

    @Override
    public StoreJoin save(User user,StoreJoin storeJoin, String[] ids, String[] names) {
        StoreJoin userStoreJoin = getCurrentUserStoreJoin(user.getId());
        Assert.notNull(userStoreJoin, "请先执行第一步!");
        Assert.isTrue(ids.length == names.length, "经营类目,数据异常!");
        storeJoin.setMemberId(user.getId());

        List<JsonManagement> jsonManagementList = new ArrayList<>();


        for (int i = 0; i < ids.length; i++) {
            JsonManagement jm = new JsonManagement();

            String[] idArray = ids[i].split(",");
            String[] nameArray = names[i].split(",");
            if (ids.length == 4 && idArray.length == 1) {
                List<JsonManagementClass> JsonManagementClassList = new ArrayList<>();
                for (int y = 0; y < 3; y++) {
                    try {
                        JsonManagementClass jsonManagementClass = new JsonManagementClass();
                        jsonManagementClass.setId(ids[y]);
                        jsonManagementClass.setName(names[y]);
                        JsonManagementClassList.add(jsonManagementClass);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                jm.setJmcs(JsonManagementClassList);
                jsonManagementList.add(jm);
                break;
            } else {
                List<JsonManagementClass> JsonManagementClassList = new ArrayList<>();
                for (int y = 0; y < idArray.length; y++) {
                    try {
                        JsonManagementClass jsonManagementClass = new JsonManagementClass();
                        jsonManagementClass.setId(idArray[y]);
                        jsonManagementClass.setName(nameArray[y]);
                        JsonManagementClassList.add(jsonManagementClass);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                jm.setJmcs(JsonManagementClassList);
                jsonManagementList.add(jm);
            }
        }

        storeJoin.setStoreClassIds(JSONArray.fromObject(jsonManagementList).toString());
        storeJoin.setJoininState(StoreJoinMapper.JOIN_STATIC_APPLY);
        storeJoinMapper.updateByPrimaryKeySelective(storeJoin);
        return storeJoin;
    }

    @Override
    public Store getCurrentStore(User user) {
        Store store = storeService.findByMemberId(user.getId());
        if (store==null&&user.getParentId()!=null){//子账户获取store
            store = storeService.findByMemberId(user.getParentId());
        }
        return store;
    }

    @Override
    public StoreJoin getCurrentUserStoreJoin(User user) {
        return readStoreJoinMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public int paySave(StoreJoin storeJoin) {
        storeJoin.setJoininState(storeJoinMapper.JOIN_STATIC_PAY);
        return storeJoinMapper.updateByPrimaryKeySelective(storeJoin);
    }

    private StoreJoin getCurrentUserStoreJoin(Long userId) {
        return readStoreJoinMapper.selectByPrimaryKey(userId);
    }

    @Override
    public PageInfo<StoreJoin> findAll(Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<StoreJoin> list = readStoreJoinMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public StoreJoin find(Long memberId) {
        return readStoreJoinMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public void saveVerify(Long memberId, String verify_type, String join_message, String[] commis_rate) {
        StoreJoin storeJoin= readStoreJoinMapper.selectByPrimaryKey(memberId);
        storeJoin.setJoininMessage(join_message);
        boolean isStore = false;
        if(storeJoin.getJoininState().equals(storeJoinMapper.JOIN_STATIC_APPLY)) {
            if (verify_type_pass.equals(verify_type)) {
                storeJoin.setJoininState(storeJoinMapper.JOIN_STATIC_EXMINE_YES);
            } else {
                storeJoin.setJoininState(storeJoinMapper.JOIN_STATIC_EXMINE_NO);
            }
        }else{
            if (verify_type_pass.equals(verify_type)) {
                storeJoin.setJoininState(storeJoinMapper.JOIN_STATIC_YES);
                isStore=true;
            } else {
                storeJoin.setJoininState(storeJoinMapper.JOIN_STATIC_PAY_NO);
            }
        }
        storeJoinMapper.updateByPrimaryKeySelective(storeJoin);
        if(isStore){
            try {
                storeService.openStore(storeJoin);
            }catch (Exception e){
                throw new PageException("开店失败");
            }
        }
    }
}
