package org.goshop.goods.pojo;

import java.io.Serializable;

public class GoodsClassStaple implements Serializable {
    private Integer stapleId;

    private String stapleName;

    private Integer gcId1;

    private Integer gcId2;

    private Integer gcId3;

    private Integer typeId;

    private Long memberId;

    private Integer counter;

    public Integer getStapleId() {
        return stapleId;
    }

    public void setStapleId(Integer stapleId) {
        this.stapleId = stapleId;
    }

    public String getStapleName() {
        return stapleName;
    }

    public void setStapleName(String stapleName) {
        this.stapleName = stapleName == null ? null : stapleName.trim();
    }

    public Integer getGcId1() {
        return gcId1;
    }

    public void setGcId1(Integer gcId1) {
        this.gcId1 = gcId1;
    }

    public Integer getGcId2() {
        return gcId2;
    }

    public void setGcId2(Integer gcId2) {
        this.gcId2 = gcId2;
    }

    public Integer getGcId3() {
        return gcId3;
    }

    public void setGcId3(Integer gcId3) {
        this.gcId3 = gcId3;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
