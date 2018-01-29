package org.goshop.goods.pojo;

public class GsGoodsConsultWithBLOBs extends GsGoodsConsult {
    private String consultContent;

    private String consultReply;

    public String getConsultContent() {
        return consultContent;
    }

    public void setConsultContent(String consultContent) {
        this.consultContent = consultContent;
    }

    public String getConsultReply() {
        return consultReply;
    }

    public void setConsultReply(String consultReply) {
        this.consultReply = consultReply;
    }
}