package org.goshop.assets.pojo;

import java.io.Serializable;
import java.util.Date;

public class GsAlbum implements Serializable {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private Boolean albumDefault;

    private String albumName;

    private Integer albumSequence;

    private Long albumCoverId;

    private Long userId;

    private String alblumInfo;

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

    public Boolean getAlbumDefault() {
        return albumDefault;
    }

    public void setAlbumDefault(Boolean albumDefault) {
        this.albumDefault = albumDefault;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getAlbumSequence() {
        return albumSequence;
    }

    public void setAlbumSequence(Integer albumSequence) {
        this.albumSequence = albumSequence;
    }

    public Long getAlbumCoverId() {
        return albumCoverId;
    }

    public void setAlbumCoverId(Long albumCoverId) {
        this.albumCoverId = albumCoverId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAlblumInfo() {
        return alblumInfo;
    }

    public void setAlblumInfo(String alblumInfo) {
        this.alblumInfo = alblumInfo;
    }
}
