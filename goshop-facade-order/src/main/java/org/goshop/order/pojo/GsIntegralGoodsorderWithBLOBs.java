package org.goshop.order.pojo;

public class GsIntegralGoodsorderWithBLOBs extends GsIntegralGoodsorder {
    private String igoMsg;

    private String igoPayMsg;

    private String igoShipContent;

    public String getIgoMsg() {
        return igoMsg;
    }

    public void setIgoMsg(String igoMsg) {
        this.igoMsg = igoMsg;
    }

    public String getIgoPayMsg() {
        return igoPayMsg;
    }

    public void setIgoPayMsg(String igoPayMsg) {
        this.igoPayMsg = igoPayMsg;
    }

    public String getIgoShipContent() {
        return igoShipContent;
    }

    public void setIgoShipContent(String igoShipContent) {
        this.igoShipContent = igoShipContent;
    }
}