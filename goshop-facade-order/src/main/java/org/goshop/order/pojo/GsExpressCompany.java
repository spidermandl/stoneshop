package org.goshop.order.pojo;

import java.io.Serializable;
import java.util.Date;

public class GsExpressCompany implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String companyMark;

    private String companyName;

    private Integer companyStatus;

    private Integer companySequence;

    private String companyType;

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

    public String getCompanyMark() {
        return companyMark;
    }

    public void setCompanyMark(String companyMark) {
        this.companyMark = companyMark;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Integer getCompanySequence() {
        return companySequence;
    }

    public void setCompanySequence(Integer companySequence) {
        this.companySequence = companySequence;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
