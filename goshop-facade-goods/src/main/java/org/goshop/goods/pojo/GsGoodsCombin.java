package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsCombin implements Serializable{
    private Long goodsId;

    private Long combinGoodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCombinGoodsId() {
        return combinGoodsId;
    }

    public void setCombinGoodsId(Long combinGoodsId) {
        this.combinGoodsId = combinGoodsId;
    }
}
