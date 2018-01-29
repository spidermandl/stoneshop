package org.goshop.goods.pojo;

import java.util.Date;

public class GsGroupArea {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Integer gaLevel;

    private String gaName;

    private Integer gaSequence;

    private Long parentId;

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

    public Integer getGaLevel() {
        return gaLevel;
    }

    public void setGaLevel(Integer gaLevel) {
        this.gaLevel = gaLevel;
    }

    public String getGaName() {
        return gaName;
    }

    public void setGaName(String gaName) {
        this.gaName = gaName;
    }

    public Integer getGaSequence() {
        return gaSequence;
    }

    public void setGaSequence(Integer gaSequence) {
        this.gaSequence = gaSequence;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}