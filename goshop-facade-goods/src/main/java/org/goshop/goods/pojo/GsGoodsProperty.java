package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsProperty implements Serializable {
    private Long goodsId;

    private Long specId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }
}
