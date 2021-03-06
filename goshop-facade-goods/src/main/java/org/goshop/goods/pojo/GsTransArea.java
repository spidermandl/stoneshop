package org.goshop.goods.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GsTransArea implements Serializable{
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String areaname;

    private Integer level;

    private Integer sequence;

    private Long parentId;

    private List<GsTransArea> childs;

    public List<GsTransArea> getChilds() {
        return childs;
    }

    public void setChilds(List<GsTransArea> childs) {
        this.childs = childs;
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

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
