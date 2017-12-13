package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsPhoto implements Serializable {
    private Long goodsId;

    private Long photoId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }
}
