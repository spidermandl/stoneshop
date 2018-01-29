package org.goshop.goods.pojo;

import java.util.Date;

public class GsGroupClass {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Integer gcLevel;

    private String gcName;

    private Integer gcSequence;

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

    public Integer getGcLevel() {
        return gcLevel;
    }

    public void setGcLevel(Integer gcLevel) {
        this.gcLevel = gcLevel;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public Integer getGcSequence() {
        return gcSequence;
    }

    public void setGcSequence(Integer gcSequence) {
        this.gcSequence = gcSequence;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}