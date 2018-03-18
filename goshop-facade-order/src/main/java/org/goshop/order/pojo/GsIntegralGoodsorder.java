package org.goshop.order.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GsIntegralGoodsorder  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String igoOrderSn;

    private Date igoPayTime;

    private String igoPayment;

    private String igoShipCode;

    private Date igoShipTime;

    private Integer igoStatus;

    private Integer igoTotalIntegral;

    private BigDecimal igoTransFee;

    private Long igoAddrId;

    private Long igoUserId;

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

    public String getIgoOrderSn() {
        return igoOrderSn;
    }

    public void setIgoOrderSn(String igoOrderSn) {
        this.igoOrderSn = igoOrderSn;
    }

    public Date getIgoPayTime() {
        return igoPayTime;
    }

    public void setIgoPayTime(Date igoPayTime) {
        this.igoPayTime = igoPayTime;
    }

    public String getIgoPayment() {
        return igoPayment;
    }

    public void setIgoPayment(String igoPayment) {
        this.igoPayment = igoPayment;
    }

    public String getIgoShipCode() {
        return igoShipCode;
    }

    public void setIgoShipCode(String igoShipCode) {
        this.igoShipCode = igoShipCode;
    }

    public Date getIgoShipTime() {
        return igoShipTime;
    }

    public void setIgoShipTime(Date igoShipTime) {
        this.igoShipTime = igoShipTime;
    }

    public Integer getIgoStatus() {
        return igoStatus;
    }

    public void setIgoStatus(Integer igoStatus) {
        this.igoStatus = igoStatus;
    }

    public Integer getIgoTotalIntegral() {
        return igoTotalIntegral;
    }

    public void setIgoTotalIntegral(Integer igoTotalIntegral) {
        this.igoTotalIntegral = igoTotalIntegral;
    }

    public BigDecimal getIgoTransFee() {
        return igoTransFee;
    }

    public void setIgoTransFee(BigDecimal igoTransFee) {
        this.igoTransFee = igoTransFee;
    }

    public Long getIgoAddrId() {
        return igoAddrId;
    }

    public void setIgoAddrId(Long igoAddrId) {
        this.igoAddrId = igoAddrId;
    }

    public Long getIgoUserId() {
        return igoUserId;
    }

    public void setIgoUserId(Long igoUserId) {
        this.igoUserId = igoUserId;
    }
}
