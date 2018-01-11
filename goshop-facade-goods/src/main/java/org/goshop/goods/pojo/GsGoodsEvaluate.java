package org.goshop.goods.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GsGoodsEvaluate  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Integer evaluateBuyerVal;

    private Date evaluateSellerTime;

    private Integer evaluateSellerVal;

    private Integer evaluateStatus;

    private String evaluateType;

    private Long evaluateGoodsId;

    private Long evaluateSellerUserId;

    private Long evaluateUserId;

    private Long ofId;

    private BigDecimal descriptionEvaluate;

    private BigDecimal serviceEvaluate;

    private BigDecimal shipEvaluate;

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

    public Integer getEvaluateBuyerVal() {
        return evaluateBuyerVal;
    }

    public void setEvaluateBuyerVal(Integer evaluateBuyerVal) {
        this.evaluateBuyerVal = evaluateBuyerVal;
    }

    public Date getEvaluateSellerTime() {
        return evaluateSellerTime;
    }

    public void setEvaluateSellerTime(Date evaluateSellerTime) {
        this.evaluateSellerTime = evaluateSellerTime;
    }

    public Integer getEvaluateSellerVal() {
        return evaluateSellerVal;
    }

    public void setEvaluateSellerVal(Integer evaluateSellerVal) {
        this.evaluateSellerVal = evaluateSellerVal;
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public String getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
    }

    public Long getEvaluateGoodsId() {
        return evaluateGoodsId;
    }

    public void setEvaluateGoodsId(Long evaluateGoodsId) {
        this.evaluateGoodsId = evaluateGoodsId;
    }

    public Long getEvaluateSellerUserId() {
        return evaluateSellerUserId;
    }

    public void setEvaluateSellerUserId(Long evaluateSellerUserId) {
        this.evaluateSellerUserId = evaluateSellerUserId;
    }

    public Long getEvaluateUserId() {
        return evaluateUserId;
    }

    public void setEvaluateUserId(Long evaluateUserId) {
        this.evaluateUserId = evaluateUserId;
    }

    public Long getOfId() {
        return ofId;
    }

    public void setOfId(Long ofId) {
        this.ofId = ofId;
    }

    public BigDecimal getDescriptionEvaluate() {
        return descriptionEvaluate;
    }

    public void setDescriptionEvaluate(BigDecimal descriptionEvaluate) {
        this.descriptionEvaluate = descriptionEvaluate;
    }

    public BigDecimal getServiceEvaluate() {
        return serviceEvaluate;
    }

    public void setServiceEvaluate(BigDecimal serviceEvaluate) {
        this.serviceEvaluate = serviceEvaluate;
    }

    public BigDecimal getShipEvaluate() {
        return shipEvaluate;
    }

    public void setShipEvaluate(BigDecimal shipEvaluate) {
        this.shipEvaluate = shipEvaluate;
    }
}
