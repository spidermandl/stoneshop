package org.goshop.order.pojo;

import java.io.Serializable;
import java.util.Date;

public class GsOrderLog implements Serializable{
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String logInfo;

    private Long logUserId;

    private Long ofId;

    private String stateInfo;

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

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public Long getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(Long logUserId) {
        this.logUserId = logUserId;
    }

    public Long getOfId() {
        return ofId;
    }

    public void setOfId(Long ofId) {
        this.ofId = ofId;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
