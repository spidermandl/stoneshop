package org.goshop.goods.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class GoodsCommon implements Serializable {
    private Integer goodsCommonid;

    private String goodsName;

    private String goodsJingle;

    private Integer gcId;

    private String gcName;

    private Integer storeId;

    private String storeName;

    private String specName;

    private Integer brandId;

    private String brandName;

    private Integer typeId;

    private String goodsImage;

    private Integer goodsState;

    private String goodsStateremark;

    private Integer goodsVerify;

    private String goodsVerifyremark;

    private Integer goodsLock;

    private Timestamp goodsAddtime;

    private Timestamp goodsSelltime;

    private BigDecimal goodsPrice;

    private BigDecimal goodsMarketprice;

    private BigDecimal goodsCostprice;

    private Float goodsDiscount;

    private String goodsSerial;

    private Integer transportId;

    private String transportTitle;

    private Integer goodsCommend;

    private BigDecimal goodsFreight;

    private Integer goodsVat;

    private Integer areaid1;

    private Integer areaid2;

    private String goodsStcids;

    private Integer plateidTop;

    private Integer plateidBottom;

    public Integer getGoodsCommonid() {
        return goodsCommonid;
    }

    public void setGoodsCommonid(Integer goodsCommonid) {
        this.goodsCommonid = goodsCommonid;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsJingle() {
        return goodsJingle;
    }

    public void setGoodsJingle(String goodsJingle) {
        this.goodsJingle = goodsJingle == null ? null : goodsJingle.trim();
    }

    public Integer getGcId() {
        return gcId;
    }

    public void setGcId(Integer gcId) {
        this.gcId = gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName == null ? null : gcName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    public String getGoodsStateremark() {
        return goodsStateremark;
    }

    public void setGoodsStateremark(String goodsStateremark) {
        this.goodsStateremark = goodsStateremark == null ? null : goodsStateremark.trim();
    }

    public Integer getGoodsVerify() {
        return goodsVerify;
    }

    public void setGoodsVerify(Integer goodsVerify) {
        this.goodsVerify = goodsVerify;
    }

    public String getGoodsVerifyremark() {
        return goodsVerifyremark;
    }

    public void setGoodsVerifyremark(String goodsVerifyremark) {
        this.goodsVerifyremark = goodsVerifyremark == null ? null : goodsVerifyremark.trim();
    }

    public Integer getGoodsLock() {
        return goodsLock;
    }

    public void setGoodsLock(Integer goodsLock) {
        this.goodsLock = goodsLock;
    }

    public Date getGoodsAddtime() {
        return goodsAddtime;
    }

    public void setGoodsAddtime(Timestamp goodsAddtime) {
        this.goodsAddtime = goodsAddtime;
    }

    public Date getGoodsSelltime() {
        return goodsSelltime;
    }

    public void setGoodsSelltime(Timestamp goodsSelltime) {
        this.goodsSelltime = goodsSelltime;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsMarketprice() {
        return goodsMarketprice;
    }

    public void setGoodsMarketprice(BigDecimal goodsMarketprice) {
        this.goodsMarketprice = goodsMarketprice;
    }

    public BigDecimal getGoodsCostprice() {
        return goodsCostprice;
    }

    public void setGoodsCostprice(BigDecimal goodsCostprice) {
        this.goodsCostprice = goodsCostprice;
    }

    public Float getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Float goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getGoodsSerial() {
        return goodsSerial;
    }

    public void setGoodsSerial(String goodsSerial) {
        this.goodsSerial = goodsSerial == null ? null : goodsSerial.trim();
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getTransportTitle() {
        return transportTitle;
    }

    public void setTransportTitle(String transportTitle) {
        this.transportTitle = transportTitle == null ? null : transportTitle.trim();
    }

    public Integer getGoodsCommend() {
        return goodsCommend;
    }

    public void setGoodsCommend(Integer goodsCommend) {
        this.goodsCommend = goodsCommend;
    }

    public BigDecimal getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(BigDecimal goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public Integer getGoodsVat() {
        return goodsVat;
    }

    public void setGoodsVat(Integer goodsVat) {
        this.goodsVat = goodsVat;
    }

    public Integer getAreaid1() {
        return areaid1;
    }

    public void setAreaid1(Integer areaid1) {
        this.areaid1 = areaid1;
    }

    public Integer getAreaid2() {
        return areaid2;
    }

    public void setAreaid2(Integer areaid2) {
        this.areaid2 = areaid2;
    }

    public String getGoodsStcids() {
        return goodsStcids;
    }

    public void setGoodsStcids(String goodsStcids) {
        this.goodsStcids = goodsStcids == null ? null : goodsStcids.trim();
    }

    public Integer getPlateidTop() {
        return plateidTop;
    }

    public void setPlateidTop(Integer plateidTop) {
        this.plateidTop = plateidTop;
    }

    public Integer getPlateidBottom() {
        return plateidBottom;
    }

    public void setPlateidBottom(Integer plateidBottom) {
        this.plateidBottom = plateidBottom;
    }
}
