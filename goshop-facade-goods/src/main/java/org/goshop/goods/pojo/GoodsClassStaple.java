package org.goshop.goods.pojo;

import java.io.Serializable;

public class GoodsClassStaple implements Serializable {
    private Long stapleId;

    private String stapleName;

    private Long gcId1;

    private Long gcId2;

    private Long gcId3;

    private Long typeId;

    private Long memberId;

    private Integer counter;

    public Long getStapleId() {
        return stapleId;
    }

    public void setStapleId(Long stapleId) {
        this.stapleId = stapleId;
    }

    public String getStapleName() {
        return stapleName;
    }

    public void setStapleName(String stapleName) {
        this.stapleName = stapleName == null ? null : stapleName.trim();
    }

    public Long getGcId1() {
        return gcId1;
    }

    public void setGcId1(Long gcId1) {
        this.gcId1 = gcId1;
    }

    public Long getGcId2() {
        return gcId2;
    }

    public void setGcId2(Long gcId2) {
        this.gcId2 = gcId2;
    }

    public Long getGcId3() {
        return gcId3;
    }

    public void setGcId3(Long gcId3) {
        this.gcId3 = gcId3;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
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
