package org.goshop.goods.pojo;

import java.util.Date;

public class GsGoodsConsult {
    private Long id;

    private Date addtime;

    private Boolean deletestatus;

    private String consultEmail;

    private Boolean reply;

    private Date replyTime;

    private Long consultUserId;

    private Long goodsId;

    private Long replyUserId;

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

    public String getConsultEmail() {
        return consultEmail;
    }

    public void setConsultEmail(String consultEmail) {
        this.consultEmail = consultEmail;
    }

    public Boolean getReply() {
        return reply;
    }

    public void setReply(Boolean reply) {
        this.reply = reply;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Long getConsultUserId() {
        return consultUserId;
    }

    public void setConsultUserId(Long consultUserId) {
        this.consultUserId = consultUserId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }
}
