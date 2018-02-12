package org.goshop.pay.pojo;

public class GsPredepositWithBLOBs extends GsPredeposit {
    private String pdAdminInfo;

    private String pdRemittanceInfo;

    public String getPdAdminInfo() {
        return pdAdminInfo;
    }

    public void setPdAdminInfo(String pdAdminInfo) {
        this.pdAdminInfo = pdAdminInfo;
    }

    public String getPdRemittanceInfo() {
        return pdRemittanceInfo;
    }

    public void setPdRemittanceInfo(String pdRemittanceInfo) {
        this.pdRemittanceInfo = pdRemittanceInfo;
    }
}