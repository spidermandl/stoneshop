package org.goshop.goods.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GsGoodsUserClass implements Serializable{
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String classname;

    private Boolean display;

    private Integer level;

    private Integer sequence;

    private Long parentId;

    private Long userId;

    private List<GsGoodsUserClass> childs;

    private GsGoodsUserClass parent;

    public GsGoodsUserClass getParent() {
        return parent;
    }

    public void setParent(GsGoodsUserClass parent) {
        this.parent = parent;
    }

    public List<GsGoodsUserClass> getChilds() {
        return childs;
    }

    public void setChilds(List<GsGoodsUserClass> childs) {
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
