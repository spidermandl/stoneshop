package org.goshop.goods.pojo;

import java.io.Serializable;
import java.util.Date;

public class GsGoodsBrand implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private int audit;

    private String name;

    private Boolean recommend;

    private Integer sequence;

    private Long brandlogoId;

    private Long categoryId;

    private Integer userstatus;

    private Long userId;

    private Boolean weixinShopRecommend;

    private Date weixinShopRecommendtime;

    private String firstWord;

    private String remark;

    public GsGoodsAccessory getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(GsGoodsAccessory brandLogo) {
        this.brandLogo = brandLogo;
    }

    private GsGoodsAccessory brandLogo;

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

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getBrandlogoId() {
        return brandlogoId;
    }

    public void setBrandlogoId(Long brandlogoId) {
        this.brandlogoId = brandlogoId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getWeixinShopRecommend() {
        return weixinShopRecommend;
    }

    public void setWeixinShopRecommend(Boolean weixinShopRecommend) {
        this.weixinShopRecommend = weixinShopRecommend;
    }

    public Date getWeixinShopRecommendtime() {
        return weixinShopRecommendtime;
    }

    public void setWeixinShopRecommendtime(Date weixinShopRecommendtime) {
        this.weixinShopRecommendtime = weixinShopRecommendtime;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
