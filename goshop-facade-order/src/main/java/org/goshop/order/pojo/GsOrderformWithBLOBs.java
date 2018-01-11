package org.goshop.order.pojo;

public class GsOrderformWithBLOBs extends GsOrderform {
    private String msg;

    private String payMsg;

    private String orderSellerIntro;

    private String returnContent;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }

    public String getOrderSellerIntro() {
        return orderSellerIntro;
    }

    public void setOrderSellerIntro(String orderSellerIntro) {
        this.orderSellerIntro = orderSellerIntro;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }
}