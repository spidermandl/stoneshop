package org.goshop.order.pojo;

import java.io.Serializable;
import java.util.Date;

public class GsIntegrallog  implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Integer integral;

    private String type;

    private Long integralUserId;

    private Long operateUserId;

    private String content;

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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIntegralUserId() {
        return integralUserId;
    }

    public void setIntegralUserId(Long integralUserId) {
        this.integralUserId = integralUserId;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
