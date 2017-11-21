package org.goshop.goods.pojo;

public class GsGoodsWithBLOBs extends GsGoods {
    private String goodsDetails;

    private String goodsInventoryDetail;

    private String goodsProperty;

    private String seoDescription;

    private String ztcAdminContent;

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public String getGoodsInventoryDetail() {
        return goodsInventoryDetail;
    }

    public void setGoodsInventoryDetail(String goodsInventoryDetail) {
        this.goodsInventoryDetail = goodsInventoryDetail;
    }

    public String getGoodsProperty() {
        return goodsProperty;
    }

    public void setGoodsProperty(String goodsProperty) {
        this.goodsProperty = goodsProperty;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getZtcAdminContent() {
        return ztcAdminContent;
    }

    public void setZtcAdminContent(String ztcAdminContent) {
        this.ztcAdminContent = ztcAdminContent;
    }
}