package org.goshop.users.pojo;

import java.io.Serializable;

public class GsPermRes implements Serializable {
    private Long permId;

    private Long resId;

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }
}