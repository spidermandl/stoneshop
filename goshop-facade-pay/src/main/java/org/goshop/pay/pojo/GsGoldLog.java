package org.goshop.pay.pojo;

import java.util.Date;

public class GsGoldLog {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Date glAdminTime;

    private Integer glCount;

    private Integer glMoney;

    private String glPayment;

    private Integer glType;

    private Long glAdminId;

    private Long glUserId;

    private Long grId;

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

    public Date getGlAdminTime() {
        return glAdminTime;
    }

    public void setGlAdminTime(Date glAdminTime) {
        this.glAdminTime = glAdminTime;
    }

    public Integer getGlCount() {
        return glCount;
    }

    public void setGlCount(Integer glCount) {
        this.glCount = glCount;
    }

    public Integer getGlMoney() {
        return glMoney;
    }

    public void setGlMoney(Integer glMoney) {
        this.glMoney = glMoney;
    }

    public String getGlPayment() {
        return glPayment;
    }

    public void setGlPayment(String glPayment) {
        this.glPayment = glPayment;
    }

    public Integer getGlType() {
        return glType;
    }

    public void setGlType(Integer glType) {
        this.glType = glType;
    }

    public Long getGlAdminId() {
        return glAdminId;
    }

    public void setGlAdminId(Long glAdminId) {
        this.glAdminId = glAdminId;
    }

    public Long getGlUserId() {
        return glUserId;
    }

    public void setGlUserId(Long glUserId) {
        this.glUserId = glUserId;
    }

    public Long getGrId() {
        return grId;
    }

    public void setGrId(Long grId) {
        this.grId = grId;
    }
}