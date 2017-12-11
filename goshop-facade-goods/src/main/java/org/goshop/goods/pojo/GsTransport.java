package org.goshop.goods.pojo;

import java.io.Serializable;
import java.util.Date;

public class GsTransport  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Boolean transEms;

    private Boolean transExpress;

    private Boolean transMail;

    private String transName;

    private Long storeId;

    private Integer transTime;

    private Integer transType;

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

    public Boolean getTransEms() {
        return transEms;
    }

    public void setTransEms(Boolean transEms) {
        this.transEms = transEms;
    }

    public Boolean getTransExpress() {
        return transExpress;
    }

    public void setTransExpress(Boolean transExpress) {
        this.transExpress = transExpress;
    }

    public Boolean getTransMail() {
        return transMail;
    }

    public void setTransMail(Boolean transMail) {
        this.transMail = transMail;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getTransTime() {
        return transTime;
    }

    public void setTransTime(Integer transTime) {
        this.transTime = transTime;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }
}
