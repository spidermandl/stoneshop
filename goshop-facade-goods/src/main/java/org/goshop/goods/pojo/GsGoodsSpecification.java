package org.goshop.goods.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GsGoodsSpecification implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String name;

    private Integer sequence;

    private String type;

    /**
     * non auto created
     */
    private List<GsGoodsSpecProperty> properties = new ArrayList<>();
    /**  end  ***/
    public List<GsGoodsSpecProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<GsGoodsSpecProperty> properties) {
        this.properties = properties;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
