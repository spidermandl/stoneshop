package org.goshop.store.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AlbumClass implements Serializable {
    private Integer aclassId;

    private String aclassName;

    private Long storeId;

    private String aclassDes;

    private Integer aclassSort;

    private String aclassCover;

    private Timestamp uploadTime;

    private Integer isDefault;

    //虚拟求相册中的图合计字段
    private Integer picNumber;

    public Integer getPicNumber() {
        return picNumber;
    }

    public void setPicNumber(Integer picNumber) {
        this.picNumber = picNumber;
    }

    public Integer getAclassId() {
        return aclassId;
    }

    public void setAclassId(Integer aclassId) {
        this.aclassId = aclassId;
    }

    public String getAclassName() {
        return aclassName;
    }

    public void setAclassName(String aclassName) {
        this.aclassName = aclassName == null ? null : aclassName.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getAclassDes() {
        return aclassDes;
    }

    public void setAclassDes(String aclassDes) {
        this.aclassDes = aclassDes == null ? null : aclassDes.trim();
    }

    public Integer getAclassSort() {
        return aclassSort;
    }

    public void setAclassSort(Integer aclassSort) {
        this.aclassSort = aclassSort;
    }

    public String getAclassCover() {
        return aclassCover;
    }

    public void setAclassCover(String aclassCover) {
        this.aclassCover = aclassCover == null ? null : aclassCover.trim();
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
