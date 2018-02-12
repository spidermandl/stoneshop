package org.goshop.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class GsPredepositLog {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private BigDecimal pdLogAmount;

    private String pdOpType;

    private String pdType;

    private Long pdLogAdminId;

    private Long pdLogUserId;

    private Long predepositId;

    private String pdLogInfo;

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

    public BigDecimal getPdLogAmount() {
        return pdLogAmount;
    }

    public void setPdLogAmount(BigDecimal pdLogAmount) {
        this.pdLogAmount = pdLogAmount;
    }

    public String getPdOpType() {
        return pdOpType;
    }

    public void setPdOpType(String pdOpType) {
        this.pdOpType = pdOpType;
    }

    public String getPdType() {
        return pdType;
    }

    public void setPdType(String pdType) {
        this.pdType = pdType;
    }

    public Long getPdLogAdminId() {
        return pdLogAdminId;
    }

    public void setPdLogAdminId(Long pdLogAdminId) {
        this.pdLogAdminId = pdLogAdminId;
    }

    public Long getPdLogUserId() {
        return pdLogUserId;
    }

    public void setPdLogUserId(Long pdLogUserId) {
        this.pdLogUserId = pdLogUserId;
    }

    public Long getPredepositId() {
        return predepositId;
    }

    public void setPredepositId(Long predepositId) {
        this.predepositId = predepositId;
    }

    public String getPdLogInfo() {
        return pdLogInfo;
    }

    public void setPdLogInfo(String pdLogInfo) {
        this.pdLogInfo = pdLogInfo;
    }
}