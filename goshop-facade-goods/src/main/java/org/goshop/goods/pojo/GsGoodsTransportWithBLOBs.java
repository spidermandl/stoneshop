package org.goshop.goods.pojo;

public class GsGoodsTransportWithBLOBs extends GsGoodsTransport {
    private String transEmsInfo;

    private String transExpressInfo;

    private String transMailInfo;

    public String getTransEmsInfo() {
        return transEmsInfo;
    }

    public void setTransEmsInfo(String transEmsInfo) {
        this.transEmsInfo = transEmsInfo;
    }

    public String getTransExpressInfo() {
        return transExpressInfo;
    }

    public void setTransExpressInfo(String transExpressInfo) {
        this.transExpressInfo = transExpressInfo;
    }

    public String getTransMailInfo() {
        return transMailInfo;
    }

    public void setTransMailInfo(String transMailInfo) {
        this.transMailInfo = transMailInfo;
    }
}