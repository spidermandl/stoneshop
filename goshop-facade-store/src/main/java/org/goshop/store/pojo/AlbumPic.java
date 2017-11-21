package org.goshop.store.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AlbumPic implements Serializable {

    private Integer apicId;

    private String apicName;

    private String apicTag;

    private Integer aclassId;

    private String apicCover;

    private Integer apicSize;

    private String apicSpec;

    private Integer storeId;

    private Date uploadTime;

    public Integer getApicId() {
        return apicId;
    }

    public void setApicId(Integer apicId) {
        this.apicId = apicId;
    }

    public String getApicName() {
        return apicName;
    }

    public void setApicName(String apicName) {
        this.apicName = apicName == null ? null : apicName.trim();
    }

    public String getApicTag() {
        return apicTag;
    }

    public void setApicTag(String apicTag) {
        this.apicTag = apicTag == null ? null : apicTag.trim();
    }

    public Integer getAclassId() {
        return aclassId;
    }

    public void setAclassId(Integer aclassId) {
        this.aclassId = aclassId;
    }

    public String getApicCover() {
        return apicCover;
    }

    public void setApicCover(String apicCover) {
        this.apicCover = apicCover == null ? null : apicCover.trim();
    }

    public Integer getApicSize() {
        return apicSize;
    }

    public void setApicSize(Integer apicSize) {
        this.apicSize = apicSize;
    }

    public String getApicSpec() {
        return apicSpec;
    }

    public void setApicSpec(String apicSpec) {
        this.apicSpec = apicSpec == null ? null : apicSpec.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
