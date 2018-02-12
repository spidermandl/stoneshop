package org.goshop.pay.pojo;

public class GsPaymentWithBLOBs extends GsPayment {
    private String content;

    private String weixinAppid;

    private String weixinAppsecret;

    private String weixinPartnerid;

    private String weixinPartnerkey;

    private String weixinPaysignkey;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeixinAppid() {
        return weixinAppid;
    }

    public void setWeixinAppid(String weixinAppid) {
        this.weixinAppid = weixinAppid;
    }

    public String getWeixinAppsecret() {
        return weixinAppsecret;
    }

    public void setWeixinAppsecret(String weixinAppsecret) {
        this.weixinAppsecret = weixinAppsecret;
    }

    public String getWeixinPartnerid() {
        return weixinPartnerid;
    }

    public void setWeixinPartnerid(String weixinPartnerid) {
        this.weixinPartnerid = weixinPartnerid;
    }

    public String getWeixinPartnerkey() {
        return weixinPartnerkey;
    }

    public void setWeixinPartnerkey(String weixinPartnerkey) {
        this.weixinPartnerkey = weixinPartnerkey;
    }

    public String getWeixinPaysignkey() {
        return weixinPaysignkey;
    }

    public void setWeixinPaysignkey(String weixinPaysignkey) {
        this.weixinPaysignkey = weixinPaysignkey;
    }
}