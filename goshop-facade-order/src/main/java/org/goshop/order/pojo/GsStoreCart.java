package org.goshop.order.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsStoreCart  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String cartSessionId;

    private BigDecimal totalPrice;

    private Long storeId;

    private Long userId;

    private Integer scStatus;

    /******************手动添加***************************/
    private List<GsGoodsCart> gcs = new ArrayList();//goods cart
    /****************************************************/

    public List<GsGoodsCart> getGcs() {
        return gcs;
    }

    public void setGcs(List<GsGoodsCart> gcs) {
        this.gcs = gcs;
    }
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

    public String getCartSessionId() {
        return cartSessionId;
    }

    public void setCartSessionId(String cartSessionId) {
        this.cartSessionId = cartSessionId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public Integer getScStatus() {
        return scStatus;
    }

    public void setScStatus(Integer scStatus) {
        this.scStatus = scStatus;
    }
}
