package org.goshop.users.pojo;

import java.io.Serializable;

public class GsUserBanPerm implements Serializable {
    private Long uId;

    private Long permissionId;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}