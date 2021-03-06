package org.goshop.store.pojo;

import org.goshop.goods.pojo.GsGoods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsArea implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String areaname;

    private Integer level;

    private Integer sequence;

    private Long parentId;

    private Boolean common;

    /*****手动加入*****/
    private List<GsArea> childs = new ArrayList<>();
    private GsArea parent;
    /*****************/

    public GsArea getParent() {
        return parent;
    }

    public void setParent(GsArea parent) {
        this.parent = parent;
    }

    public List<GsArea> getChilds() {
        return childs;
    }

    public void setChilds(List<GsArea> childs) {
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

    public Boolean getCommon() {
        return common;
    }

    public void setCommon(Boolean common) {
        this.common = common;
    }
}
