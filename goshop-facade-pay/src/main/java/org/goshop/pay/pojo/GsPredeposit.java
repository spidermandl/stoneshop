package org.goshop.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class GsPredeposit {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private BigDecimal pdAmount;

    private Integer pdPayStatus;

    private String pdPayment;

    private String pdRemittanceBank;

    private Date pdRemittanceTime;

    private String pdRemittanceUser;

    private String pdSn;

    private Integer pdStatus;

    private Long pdAdminId;

    private Long pdUserId;

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

    public BigDecimal getPdAmount() {
        return pdAmount;
    }

    public void setPdAmount(BigDecimal pdAmount) {
        this.pdAmount = pdAmount;
    }

    public Integer getPdPayStatus() {
        return pdPayStatus;
    }

    public void setPdPayStatus(Integer pdPayStatus) {
        this.pdPayStatus = pdPayStatus;
    }

    public String getPdPayment() {
        return pdPayment;
    }

    public void setPdPayment(String pdPayment) {
        this.pdPayment = pdPayment;
    }

    public String getPdRemittanceBank() {
        return pdRemittanceBank;
    }

    public void setPdRemittanceBank(String pdRemittanceBank) {
        this.pdRemittanceBank = pdRemittanceBank;
    }

    public Date getPdRemittanceTime() {
        return pdRemittanceTime;
    }

    public void setPdRemittanceTime(Date pdRemittanceTime) {
        this.pdRemittanceTime = pdRemittanceTime;
    }

    public String getPdRemittanceUser() {
        return pdRemittanceUser;
    }

    public void setPdRemittanceUser(String pdRemittanceUser) {
        this.pdRemittanceUser = pdRemittanceUser;
    }

    public String getPdSn() {
        return pdSn;
    }

    public void setPdSn(String pdSn) {
        this.pdSn = pdSn;
    }

    public Integer getPdStatus() {
        return pdStatus;
    }

    public void setPdStatus(Integer pdStatus) {
        this.pdStatus = pdStatus;
    }

    public Long getPdAdminId() {
        return pdAdminId;
    }

    public void setPdAdminId(Long pdAdminId) {
        this.pdAdminId = pdAdminId;
    }

    public Long getPdUserId() {
        return pdUserId;
    }

    public void setPdUserId(Long pdUserId) {
        this.pdUserId = pdUserId;
    }
}