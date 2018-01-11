package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsFloorWithBLOBs extends GsGoodsFloor implements Serializable{
    private String gfGcGoods;

    private String gfGcList;

    private String gfLeftAdv;

    private String gfListGoods;

    private String gfRightAdv;

    private String gfBrandList;

    public String getGfGcGoods() {
        return gfGcGoods;
    }

    public void setGfGcGoods(String gfGcGoods) {
        this.gfGcGoods = gfGcGoods;
    }

    public String getGfGcList() {
        return gfGcList;
    }

    public void setGfGcList(String gfGcList) {
        this.gfGcList = gfGcList;
    }

    public String getGfLeftAdv() {
        return gfLeftAdv;
    }

    public void setGfLeftAdv(String gfLeftAdv) {
        this.gfLeftAdv = gfLeftAdv;
    }

    public String getGfListGoods() {
        return gfListGoods;
    }

    public void setGfListGoods(String gfListGoods) {
        this.gfListGoods = gfListGoods;
    }

    public String getGfRightAdv() {
        return gfRightAdv;
    }

    public void setGfRightAdv(String gfRightAdv) {
        this.gfRightAdv = gfRightAdv;
    }

    public String getGfBrandList() {
        return gfBrandList;
    }

    public void setGfBrandList(String gfBrandList) {
        this.gfBrandList = gfBrandList;
    }
}
