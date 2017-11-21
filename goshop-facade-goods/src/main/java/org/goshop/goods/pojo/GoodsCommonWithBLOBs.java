package org.goshop.goods.pojo;

public class GoodsCommonWithBLOBs extends GoodsCommon {
    private String specValue;

    private String goodsAttr;

    private String goodsBody;

    private String goodsSpecname;

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue == null ? null : specValue.trim();
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr == null ? null : goodsAttr.trim();
    }

    public String getGoodsBody() {
        return goodsBody;
    }

    public void setGoodsBody(String goodsBody) {
        this.goodsBody = goodsBody == null ? null : goodsBody.trim();
    }

    public String getGoodsSpecname() {
        return goodsSpecname;
    }

    public void setGoodsSpecname(String goodsSpecname) {
        this.goodsSpecname = goodsSpecname == null ? null : goodsSpecname.trim();
    }
}
