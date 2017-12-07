package org.goshop.goods.pojo;

import java.util.Date;

public class GsGoodsSpecProperty {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Integer sequence;

    private String value;

    private Long specId;

    private Long specimageId;

    private GsGoodsSpecification spec;

    public GsGoodsSpecification getSpec() {
        return spec;
    }

    public void setSpec(GsGoodsSpecification spec) {
        this.spec = spec;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecimageId() {
        return specimageId;
    }

    public void setSpecimageId(Long specimageId) {
        this.specimageId = specimageId;
    }
}
