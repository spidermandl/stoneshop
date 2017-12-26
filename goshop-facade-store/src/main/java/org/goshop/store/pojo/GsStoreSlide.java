package org.goshop.store.pojo;

import org.goshop.assets.pojo.GsAccessory;

import java.io.Serializable;
import java.util.Date;

public class GsStoreSlide implements Serializable{
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String url;

    private Long accId;

    private Long storeId;

    /***** 手动加入 *******/
    private GsAccessory acc;
    /***** end *******/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GsAccessory getAcc() {
        return acc;
    }

    public void setAcc(GsAccessory acc) {
        this.acc = acc;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
