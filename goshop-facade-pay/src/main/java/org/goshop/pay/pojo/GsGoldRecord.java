package org.goshop.pay.pojo;

import java.util.Date;

public class GsGoldRecord {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Date goldAdminTime;

    private Integer goldCount;

    private Integer goldMoney;

    private Integer goldPayStatus;

    private String goldPayment;

    private String goldSn;

    private Integer goldStatus;

    private Long goldAdminId;

    private Long goldUserId;

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

    public Date getGoldAdminTime() {
        return goldAdminTime;
    }

    public void setGoldAdminTime(Date goldAdminTime) {
        this.goldAdminTime = goldAdminTime;
    }

    public Integer getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(Integer goldCount) {
        this.goldCount = goldCount;
    }

    public Integer getGoldMoney() {
        return goldMoney;
    }

    public void setGoldMoney(Integer goldMoney) {
        this.goldMoney = goldMoney;
    }

    public Integer getGoldPayStatus() {
        return goldPayStatus;
    }

    public void setGoldPayStatus(Integer goldPayStatus) {
        this.goldPayStatus = goldPayStatus;
    }

    public String getGoldPayment() {
        return goldPayment;
    }

    public void setGoldPayment(String goldPayment) {
        this.goldPayment = goldPayment;
    }

    public String getGoldSn() {
        return goldSn;
    }

    public void setGoldSn(String goldSn) {
        this.goldSn = goldSn;
    }

    public Integer getGoldStatus() {
        return goldStatus;
    }

    public void setGoldStatus(Integer goldStatus) {
        this.goldStatus = goldStatus;
    }

    public Long getGoldAdminId() {
        return goldAdminId;
    }

    public void setGoldAdminId(Long goldAdminId) {
        this.goldAdminId = goldAdminId;
    }

    public Long getGoldUserId() {
        return goldUserId;
    }

    public void setGoldUserId(Long goldUserId) {
        this.goldUserId = goldUserId;
    }
}