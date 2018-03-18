package org.goshop.order.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GsCoupon  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private BigDecimal couponAmount;

    private Date couponBeginTime;

    private Integer couponCount;

    private Date couponEndTime;

    private String couponName;

    private BigDecimal couponOrderAmount;

    private Long couponAccId;

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

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Date getCouponBeginTime() {
        return couponBeginTime;
    }

    public void setCouponBeginTime(Date couponBeginTime) {
        this.couponBeginTime = couponBeginTime;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Date getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(Date couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getCouponOrderAmount() {
        return couponOrderAmount;
    }

    public void setCouponOrderAmount(BigDecimal couponOrderAmount) {
        this.couponOrderAmount = couponOrderAmount;
    }

    public Long getCouponAccId() {
        return couponAccId;
    }

    public void setCouponAccId(Long couponAccId) {
        this.couponAccId = couponAccId;
    }
}
