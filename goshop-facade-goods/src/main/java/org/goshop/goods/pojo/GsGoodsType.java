package org.goshop.goods.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsGoodsType implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String name;

    private Integer sequence;

    private List<GsGoodsSpecification> gss = new ArrayList();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<GsGoodsSpecification> getGss(){
        return this.gss;
    }

    public void setGss(List<GsGoodsSpecification> gss){
        this.gss = gss;
    }
}
