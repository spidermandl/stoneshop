package org.goshop.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class GsRefundLog {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private BigDecimal refund;

    private String refundId;

    private String refundLog;

    private String refundType;

    private Long ofId;

    private Long refundUserId;

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

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundLog() {
        return refundLog;
    }

    public void setRefundLog(String refundLog) {
        this.refundLog = refundLog;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public Long getOfId() {
        return ofId;
    }

    public void setOfId(Long ofId) {
        this.ofId = ofId;
    }

    public Long getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(Long refundUserId) {
        this.refundUserId = refundUserId;
    }
}