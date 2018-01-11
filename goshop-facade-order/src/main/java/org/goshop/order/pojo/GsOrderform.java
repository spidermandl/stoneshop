package org.goshop.order.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GsOrderform  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Date finishtime;

    private BigDecimal goodsAmount;

    private String invoice;

    private Integer invoicetype;

    private String orderId;

    private Integer orderStatus;

    private Date paytime;

    private BigDecimal refund;

    private String refundType;

    private String shipcode;

    private Date shiptime;

    private BigDecimal shipPrice;

    private BigDecimal totalprice;

    private Long addrId;

    private Long paymentId;

    private Long storeId;

    private Long userId;

    private Boolean autoConfirmEmail;

    private Boolean autoConfirmSms;

    private String transport;

    private String outOrderId;

    private Long ecId;

    private Long ciId;

    private String returnShipcode;

    private Long returnEcId;

    private Date returnShiptime;

    private String orderType;

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

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Integer getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(Integer invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getShipcode() {
        return shipcode;
    }

    public void setShipcode(String shipcode) {
        this.shipcode = shipcode;
    }

    public Date getShiptime() {
        return shiptime;
    }

    public void setShiptime(Date shiptime) {
        this.shiptime = shiptime;
    }

    public BigDecimal getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(BigDecimal shipPrice) {
        this.shipPrice = shipPrice;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getAutoConfirmEmail() {
        return autoConfirmEmail;
    }

    public void setAutoConfirmEmail(Boolean autoConfirmEmail) {
        this.autoConfirmEmail = autoConfirmEmail;
    }

    public Boolean getAutoConfirmSms() {
        return autoConfirmSms;
    }

    public void setAutoConfirmSms(Boolean autoConfirmSms) {
        this.autoConfirmSms = autoConfirmSms;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Long getEcId() {
        return ecId;
    }

    public void setEcId(Long ecId) {
        this.ecId = ecId;
    }

    public Long getCiId() {
        return ciId;
    }

    public void setCiId(Long ciId) {
        this.ciId = ciId;
    }

    public String getReturnShipcode() {
        return returnShipcode;
    }

    public void setReturnShipcode(String returnShipcode) {
        this.returnShipcode = returnShipcode;
    }

    public Long getReturnEcId() {
        return returnEcId;
    }

    public void setReturnEcId(Long returnEcId) {
        this.returnEcId = returnEcId;
    }

    public Date getReturnShiptime() {
        return returnShiptime;
    }

    public void setReturnShiptime(Date returnShiptime) {
        this.returnShiptime = returnShiptime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
