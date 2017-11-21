package org.goshop.goods.pojo;

import java.io.Serializable;

public class GsGoodsTypeSpec implements Serializable {
    private Long typeId;

    private Long specId;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }
}
