package org.goshop.pay.pojo;

public class GsPredepositCashWithBLOBs extends GsPredepositCash {
    private String cashAdminInfo;

    private String cashInfo;

    public String getCashAdminInfo() {
        return cashAdminInfo;
    }

    public void setCashAdminInfo(String cashAdminInfo) {
        this.cashAdminInfo = cashAdminInfo;
    }

    public String getCashInfo() {
        return cashInfo;
    }

    public void setCashInfo(String cashInfo) {
        this.cashInfo = cashInfo;
    }
}