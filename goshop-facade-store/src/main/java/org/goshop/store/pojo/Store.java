package org.goshop.store.pojo;

import org.goshop.assets.pojo.GsAccessory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Store  implements Serializable {
    private Long storeId;

    private String storeName;

    private Boolean storeAuth;

    private Boolean nameAuth;

    private Integer gradeId;

    private Long memberId;

    private String memberName;

    private String sellerName;

    private String storeOwnerCard;

    private Integer scId;

    private String storeCompanyName;

    private Long areaId;

    private String areaInfo;

    private String storeAddress;

    private String storeZip;

    private String storeTel;

    private Long storeImage;

    private Long storeImage1;

    private Integer storeState;

    private String storeCloseInfo;

    private Integer storeSort;

    private Date storeTime;

    private Date storeEndTime;

    private Long storeLabel;

    private Long storeBanner;

    private String storeKeywords;

    private String storeDescription;

    private String storeQq;

    private String storeWw;

    private String storeDomain;

    private Integer storeDomainTimes;

    private Boolean storeRecommend;

    private String storeTheme;

    private Integer storeCredit;

    private Float praiseRate;

    private Float storeDesccredit;

    private Float storeServicecredit;

    private Float storeDeliverycredit;

    private Integer storeCollect;

    private String storeStamp;

    private String storePrintdesc;

    private Integer storeSales;

    private String storeWorkingtime;

    private BigDecimal storeFreePrice;

    private Byte storeStorageAlarm;

    private String mapType;


    /************手动添加*****************/
    private StoreGrade storeGrade;//店铺等级
    private GsArea area;//店铺地点
    private GsAccessory logo;//店铺logo
    private GsAccessory banner;//店铺横幅
    private List<GsStoreSlide> slides;//店铺slide
    /************end*****************/

    public List<GsStoreSlide> getSlides() {
        return slides;
    }

    public void setSlides(List<GsStoreSlide> slides) {
        this.slides = slides;
    }

    public GsAccessory getLogo() {
        return logo;
    }

    public void setLogo(GsAccessory logo) {
        this.logo = logo;
    }

    public GsAccessory getBanner() {
        return banner;
    }

    public void setBanner(GsAccessory banner) {
        this.banner = banner;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public GsArea getArea() {
        return area;
    }

    public void setArea(GsArea area) {
        this.area = area;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Boolean getStoreAuth() {
        return storeAuth;
    }

    public void setStoreAuth(Boolean storeAuth) {
        this.storeAuth = storeAuth;
    }

    public Boolean getNameAuth() {
        return nameAuth;
    }

    public void setNameAuth(Boolean nameAuth) {
        this.nameAuth = nameAuth;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getStoreOwnerCard() {
        return storeOwnerCard;
    }

    public void setStoreOwnerCard(String storeOwnerCard) {
        this.storeOwnerCard = storeOwnerCard == null ? null : storeOwnerCard.trim();
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public String getStoreCompanyName() {
        return storeCompanyName;
    }

    public void setStoreCompanyName(String storeCompanyName) {
        this.storeCompanyName = storeCompanyName == null ? null : storeCompanyName.trim();
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo == null ? null : areaInfo.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public String getStoreZip() {
        return storeZip;
    }

    public void setStoreZip(String storeZip) {
        this.storeZip = storeZip == null ? null : storeZip.trim();
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel == null ? null : storeTel.trim();
    }

    public Long getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(Long storeImage) {
        this.storeImage = storeImage;
    }

    public Long getStoreImage1() {
        return storeImage1;
    }

    public void setStoreImage1(Long storeImage1) {
        this.storeImage1 = storeImage1;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }

    public String getStoreCloseInfo() {
        return storeCloseInfo;
    }

    public void setStoreCloseInfo(String storeCloseInfo) {
        this.storeCloseInfo = storeCloseInfo == null ? null : storeCloseInfo.trim();
    }

    public Integer getStoreSort() {
        return storeSort;
    }

    public void setStoreSort(Integer storeSort) {
        this.storeSort = storeSort;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    public Date getStoreEndTime() {
        return storeEndTime;
    }

    public void setStoreEndTime(Date storeEndTime) {
        this.storeEndTime = storeEndTime ;
    }

    public Long getStoreLabel() {
        return storeLabel;
    }

    public void setStoreLabel(Long storeLabel) {
        this.storeLabel = storeLabel;
    }

    public Long getStoreBanner() {
        return storeBanner;
    }

    public void setStoreBanner(Long storeBanner) {
        this.storeBanner = storeBanner;
    }

    public String getStoreKeywords() {
        return storeKeywords;
    }

    public void setStoreKeywords(String storeKeywords) {
        this.storeKeywords = storeKeywords == null ? null : storeKeywords.trim();
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription == null ? null : storeDescription.trim();
    }

    public String getStoreQq() {
        return storeQq;
    }

    public void setStoreQq(String storeQq) {
        this.storeQq = storeQq == null ? null : storeQq.trim();
    }

    public String getStoreWw() {
        return storeWw;
    }

    public void setStoreWw(String storeWw) {
        this.storeWw = storeWw == null ? null : storeWw.trim();
    }

    public String getStoreDomain() {
        return storeDomain;
    }

    public void setStoreDomain(String storeDomain) {
        this.storeDomain = storeDomain == null ? null : storeDomain.trim();
    }

    public Integer getStoreDomainTimes() {
        return storeDomainTimes;
    }

    public void setStoreDomainTimes(Integer storeDomainTimes) {
        this.storeDomainTimes = storeDomainTimes;
    }

    public Boolean getStoreRecommend() {
        return storeRecommend;
    }

    public void setStoreRecommend(Boolean storeRecommend) {
        this.storeRecommend = storeRecommend;
    }

    public String getStoreTheme() {
        return storeTheme;
    }

    public void setStoreTheme(String storeTheme) {
        this.storeTheme = storeTheme == null ? null : storeTheme.trim();
    }

    public Integer getStoreCredit() {
        return storeCredit;
    }

    public void setStoreCredit(Integer storeCredit) {
        this.storeCredit = storeCredit;
    }

    public Float getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(Float praiseRate) {
        this.praiseRate = praiseRate;
    }

    public Float getStoreDesccredit() {
        return storeDesccredit;
    }

    public void setStoreDesccredit(Float storeDesccredit) {
        this.storeDesccredit = storeDesccredit;
    }

    public Float getStoreServicecredit() {
        return storeServicecredit;
    }

    public void setStoreServicecredit(Float storeServicecredit) {
        this.storeServicecredit = storeServicecredit;
    }

    public Float getStoreDeliverycredit() {
        return storeDeliverycredit;
    }

    public void setStoreDeliverycredit(Float storeDeliverycredit) {
        this.storeDeliverycredit = storeDeliverycredit;
    }

    public Integer getStoreCollect() {
        return storeCollect;
    }

    public void setStoreCollect(Integer storeCollect) {
        this.storeCollect = storeCollect;
    }

    public String getStoreStamp() {
        return storeStamp;
    }

    public void setStoreStamp(String storeStamp) {
        this.storeStamp = storeStamp == null ? null : storeStamp.trim();
    }

    public String getStorePrintdesc() {
        return storePrintdesc;
    }

    public void setStorePrintdesc(String storePrintdesc) {
        this.storePrintdesc = storePrintdesc == null ? null : storePrintdesc.trim();
    }

    public Integer getStoreSales() {
        return storeSales;
    }

    public void setStoreSales(Integer storeSales) {
        this.storeSales = storeSales;
    }

    public String getStoreWorkingtime() {
        return storeWorkingtime;
    }

    public void setStoreWorkingtime(String storeWorkingtime) {
        this.storeWorkingtime = storeWorkingtime == null ? null : storeWorkingtime.trim();
    }

    public BigDecimal getStoreFreePrice() {
        return storeFreePrice;
    }

    public void setStoreFreePrice(BigDecimal storeFreePrice) {
        this.storeFreePrice = storeFreePrice;
    }

    public Byte getStoreStorageAlarm() {
        return storeStorageAlarm;
    }

    public void setStoreStorageAlarm(Byte storeStorageAlarm) {
        this.storeStorageAlarm = storeStorageAlarm;
    }

    public StoreGrade getStoreGrade() {
        return storeGrade;
    }

    public void setStoreGrade(StoreGrade storeGrade) {
        this.storeGrade = storeGrade;
    }
}
