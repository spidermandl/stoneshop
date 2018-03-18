package org.goshop.order.pojo;

import java.util.Date;

public class GsGoodsReturnlog {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Long grId;

    private Long ofId;

    private Long returnUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Boolean getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Boolean deletestatus) {
        this.deletestatus = deletestatus;
    }

    public Long getGrId() {
        return grId;
    }

    public void setGrId(Long grId) {
        this.grId = grId;
    }

    public Long getOfId() {
        return ofId;
    }

    public void setOfId(Long ofId) {
        this.ofId = ofId;
    }

    public Long getReturnUserId() {
        return returnUserId;
    }

    public void setReturnUserId(Long returnUserId) {
        this.returnUserId = returnUserId;
    }
}