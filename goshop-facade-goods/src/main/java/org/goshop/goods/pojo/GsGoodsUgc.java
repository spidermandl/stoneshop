package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsUgc implements Serializable {
    private Long goodsId;

    private Long classId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
