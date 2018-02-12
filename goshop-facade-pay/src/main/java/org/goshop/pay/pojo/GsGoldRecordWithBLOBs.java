package org.goshop.pay.pojo;

public class GsGoldRecordWithBLOBs extends GsGoldRecord {
    private String goldAdminInfo;

    private String goldExchangeInfo;

    public String getGoldAdminInfo() {
        return goldAdminInfo;
    }

    public void setGoldAdminInfo(String goldAdminInfo) {
        this.goldAdminInfo = goldAdminInfo;
    }

    public String getGoldExchangeInfo() {
        return goldExchangeInfo;
    }

    public void setGoldExchangeInfo(String goldExchangeInfo) {
        this.goldExchangeInfo = goldExchangeInfo;
    }
}