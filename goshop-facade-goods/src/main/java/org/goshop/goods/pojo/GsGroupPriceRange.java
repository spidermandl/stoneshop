package org.goshop.goods.pojo;

import java.util.Date;

public class GsGroupPriceRange {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Integer gprBegin;

    private Integer gprEnd;

    private String gprName;

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

    public Integer getGprBegin() {
        return gprBegin;
    }

    public void setGprBegin(Integer gprBegin) {
        this.gprBegin = gprBegin;
    }

    public Integer getGprEnd() {
        return gprEnd;
    }

    public void setGprEnd(Integer gprEnd) {
        this.gprEnd = gprEnd;
    }

    public String getGprName() {
        return gprName;
    }

    public void setGprName(String gprName) {
        this.gprName = gprName;
    }
}