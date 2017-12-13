package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsTypeBrand implements Serializable {
    private Long typeId;

    private Long brandId;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
