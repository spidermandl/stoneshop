package org.goshop.store.model;

import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.store.pojo.StoreClass;
import org.goshop.store.pojo.StoreGrade;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/27.
 */
public class StoreInfoModel implements Serializable {

    //店铺分类
    private List<StoreClass> storeClassParentList;
    //店铺等级
    private List<StoreGrade> storeGradeList;
    //经营类目
    private List<GsGoodsClass> goodsClassParentList;

    public List<StoreClass> getStoreClassParentList() {
        return storeClassParentList;
    }

    public void setStoreClassParentList(List<StoreClass> storeClassParentList) {
        this.storeClassParentList = storeClassParentList;
    }

    public List<StoreGrade> getStoreGradeList() {
        return storeGradeList;
    }

    public void setStoreGradeList(List<StoreGrade> storeGradeList) {
        this.storeGradeList = storeGradeList;
    }

    public List<GsGoodsClass> getGoodsClassParentList() {
        return goodsClassParentList;
    }

    public void setGoodsClassParentList(List<GsGoodsClass> goodsClassParentList) {
        this.goodsClassParentList = goodsClassParentList;
    }
}
