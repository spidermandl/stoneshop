package org.goshop.common.service;

import org.goshop.common.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Desmond on 25/11/2017.
 * 系统配置参数
 */
@Component("sysConfig")
public class SysConfig {

    @Value("${deleteStatus}")
    private Boolean deleteStatus;
    @Value("${address}")
    private String address;
    @Value("${bigHeight}")
    private Integer bigHeight;
    @Value("${bigWidth}")
    private Integer bigWidth;
    @Value("${closeReason}")
    private String closeReason;
    @Value("${codeStat}")
    private String codeStat;
    @Value("${complaint_time}")
    private Integer complaint_time;
    @Value("${consumptionRatio}")
    private Integer consumptionRatio;
    @Value("${copyRight}")
    private String copyRight;
    @Value("${creditrule}")
    private String creditrule;
    @Value("${deposit}")
    private Boolean deposit;
    @Value("${description}")
    private String description;
    @Value("${emailEnable}")
    private Boolean emailEnable;
    @Value("${emailHost}")
    private String emailHost;
    @Value("${emailPort}")
    private Integer emailPort;
    @Value("${emailPws}")
    private String emailPws;
    @Value("${emailTest}")
    private String emailTest;
    @Value("${emailUser}")
    private String emailUser;
    @Value("${emailUserName}")
    private String emailUserName;
    @Value("${everyIndentLimit}")
    private Integer everyIndentLimit;
    @Value("${gold}")
    private Integer gold;
    @Value("${goldMarketValue}")
    private Integer goldMarketValue;
    @Value("${groupBuy}")
    private Boolean groupBuy;
    @Value("${hotSearch}")
    private String hotSearch;
    @Value("${imageFilesize}")
    private Integer imageFilesize;
    @Value("${imageSaveType}")
    private String imageSaveType;
    @Value("${imageSuffix}")
    private String imageSuffix;
    @Value("${indentComment}")
    private Integer indentComment;
    @Value("${integral}")
    private Boolean integral;
    @Value("${integralRate}")
    private Integer integralRate;
    @Value("${integralStore}")
    private Boolean integralStore;
    @Value("${keywords}")
    private String keywords;
    @Value("${memberDayLogin}")
    private Integer memberDayLogin;
    @Value("${memberRegister}")
    private Integer memberRegister;
    @Value("${middleHeight}")
    private Integer middleHeight;
    @Value("${middleWidth}")
    private Integer middleWidth;
    @Value("${securityCodeConsult}")
    private Boolean securityCodeConsult;
    @Value("${securityCodeLogin}")
    private Boolean securityCodeLogin;
    @Value("${securityCodeRegister}")
    private Boolean securityCodeRegister;
    @Value("${securityCodeType}")
    private String securityCodeType;
    @Value("${share_code}")
    private String share_code;
    @Value("${smallHeight}")
    private Integer smallHeight;
    @Value("${smallWidth}")
    private Integer smallWidth;
    @Value("${smsEnbale}")
    private Boolean smsEnbale;
    @Value("${smsPassword}")
    private String smsPassword;
    @Value("${smsTest}")
    private String smsTest;
    @Value("${smsURL}")
    private String smsURL;
    @Value("${smsUserName}")
    private String smsUserName;
    @Value("${store_allow}")
    private Boolean store_allow;
    @Value("${store_payment}")
    private String store_payment;
    @Value("${sysLanguage}")
    private String sysLanguage;
    @Value("${templates}")
    private String templates;
    @Value("${title}")
    private String title;
    @Value("${uploadFilePath}")
    private String uploadFilePath;
    @Value("${user_creditrule}")
    private String user_creditrule;
    @Value("${visitorConsult}")
    private Boolean visitorConsult;
    @Value("${voucher}")
    private Boolean voucher;
    @Value("${websiteName}")
    private String websiteName;
    @Value("${websiteState}")
    private Boolean websiteState;
    @Value("${ztc_price}")
    private Integer ztc_price;
    @Value("${ztc_status}")
    private Boolean ztc_status;
    @Value("${goodsImage_id}")
    private Long goodsImage_id;
    @Value("${memberIcon_id}")
    private Long memberIcon_id;
    @Value("${storeImage_id}")
    private Long storeImage_id;
    @Value("${websiteLogo_id}")
    private Long websiteLogo_id;
    @Value("${domain_allow_count}")
    private Integer domain_allow_count;
    @Value("${second_domain_open}")
    private Boolean second_domain_open;
    @Value("${sys_domain}")
    private String sys_domain;
    @Value("${qq_login}")
    private Boolean qq_login;
    @Value("${qq_login_id}")
    private String qq_login_id;
    @Value("${qq_login_key}")
    private String qq_login_key;
    @Value("${qq_domain_code}")
    private String qq_domain_code;
    @Value("${sina_domain_code}")
    private String sina_domain_code;
    @Value("${sina_login}")
    private Boolean sina_login;
    @Value("${sina_login_id}")
    private String sina_login_id;
    @Value("${sina_login_key}")
    private String sina_login_key;
    @Value("${imageWebServer}")
    private String imageWebServer;
    @Value("${lucene_update}")
    private String lucene_update;
    @Value("${alipay_fenrun}")
    private Integer alipay_fenrun;
    @Value("${balance_fenrun}")
    private Integer balance_fenrun;
    @Value("${auto_order_confirm}")
    private Integer auto_order_confirm;
    @Value("${auto_order_notice}")
    private Integer auto_order_notice;
    @Value("${bargain_maximum}")
    private Integer bargain_maximum;
    @Value("${bargain_rebate}")
    private Float bargain_rebate;
    @Value("${bargain_state}")
    private String bargain_state;
    @Value("${bargain_status}")
    private Integer bargain_status;
    @Value("${bargain_title}")
    private String bargain_title;
    @Value("${service_qq_list}")
    private String service_qq_list;
    @Value("${service_telphone_list}")
    private String service_telphone_list;
    @Value("${sys_delivery_maximum}")
    private Integer sys_delivery_maximum;
    @Value("${uc_bbs}")
    private Boolean uc_bbs;
    @Value("${kuaidi_id}")
    private String kuaidi_id;
    @Value("${uc_api}")
    private String uc_api;
    @Value("${uc_appid}")
    private String uc_appid;
    @Value("${uc_database}")
    private String uc_database;
    @Value("${uc_database_port}")
    private String uc_database_port;
    @Value("${uc_database_pws}")
    private String uc_database_pws;
    @Value("${uc_database_url}")
    private String uc_database_url;
    @Value("${uc_database_username}")
    private String uc_database_username;
    @Value("${uc_ip}")
    private String uc_ip;
    @Value("${uc_key}")
    private String uc_key;
    @Value("${uc_table_preffix}")
    private String uc_table_preffix;
    @Value("${currency_code}")
    private String currency_code;
    @Value("${bargain_validity}")
    private Integer bargain_validity;
    @Value("${delivery_amount}")
    private Integer delivery_amount;
    @Value("${delivery_status}")
    private Integer delivery_status;
    @Value("${delivery_title}")
    private String delivery_title;
    @Value("${websiteCss}")
    private String websiteCss;
    @Value("${combin_amount}")
    private Integer combin_amount;
    @Value("${combin_count}")
    private Integer combin_count;
    @Value("${ztc_goods_view}")
    private Integer ztc_goods_view;
    @Value("${auto_order_evaluate}")
    private Integer auto_order_evaluate;
    @Value("${auto_order_return}")
    private Integer auto_order_return;
    @Value("${weixin_store}")
    private Boolean weixin_store;
    @Value("${weixin_amount}")
    private Integer weixin_amount;
    @Value("${config_payment_type}")
    private Integer config_payment_type;
    @Value("${weixin_account}")
    private String weixin_account;
    @Value("${weixin_appId}")
    private String weixin_appId;
    @Value("${weixin_appSecret}")
    private String weixin_appSecret;
    @Value("${weixin_token}")
    private String weixin_token;
    @Value("${weixin_welecome_content}")
    private String weixin_welecome_content;

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBigHeight() {
        return bigHeight;
    }

    public void setBigHeight(Integer bigHeight) {
        this.bigHeight = bigHeight;
    }

    public Integer getBigWidth() {
        return bigWidth;
    }

    public void setBigWidth(Integer bigWidth) {
        this.bigWidth = bigWidth;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getCodeStat() {
        return codeStat;
    }

    public void setCodeStat(String codeStat) {
        this.codeStat = codeStat;
    }

    public Integer getComplaint_time() {
        return complaint_time;
    }

    public void setComplaint_time(Integer complaint_time) {
        this.complaint_time = complaint_time;
    }

    public Integer getConsumptionRatio() {
        return consumptionRatio;
    }

    public void setConsumptionRatio(Integer consumptionRatio) {
        this.consumptionRatio = consumptionRatio;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getCreditrule() {
        return creditrule;
    }

    public void setCreditrule(String creditrule) {
        this.creditrule = creditrule;
    }

    public Boolean getDeposit() {
        return deposit;
    }

    public void setDeposit(Boolean deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEmailEnable() {
        return emailEnable;
    }

    public void setEmailEnable(Boolean emailEnable) {
        this.emailEnable = emailEnable;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public Integer getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(Integer emailPort) {
        this.emailPort = emailPort;
    }

    public String getEmailPws() {
        return emailPws;
    }

    public void setEmailPws(String emailPws) {
        this.emailPws = emailPws;
    }

    public String getEmailTest() {
        return emailTest;
    }

    public void setEmailTest(String emailTest) {
        this.emailTest = emailTest;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailUserName() {
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName) {
        this.emailUserName = emailUserName;
    }

    public Integer getEveryIndentLimit() {
        return everyIndentLimit;
    }

    public void setEveryIndentLimit(Integer everyIndentLimit) {
        this.everyIndentLimit = everyIndentLimit;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getGoldMarketValue() {
        return goldMarketValue;
    }

    public void setGoldMarketValue(Integer goldMarketValue) {
        this.goldMarketValue = goldMarketValue;
    }

    public Boolean getGroupBuy() {
        return groupBuy;
    }

    public void setGroupBuy(Boolean groupBuy) {
        this.groupBuy = groupBuy;
    }

    public String getHotSearch() {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch) {
        this.hotSearch = hotSearch;
    }

    public Integer getImageFilesize() {
        return imageFilesize;
    }

    public void setImageFilesize(Integer imageFilesize) {
        this.imageFilesize = imageFilesize;
    }

    public String getImageSaveType() {
        return imageSaveType;
    }

    public void setImageSaveType(String imageSaveType) {
        this.imageSaveType = imageSaveType;
    }

    public String getImageSuffix() {
        return imageSuffix;
    }

    public void setImageSuffix(String imageSuffix) {
        this.imageSuffix = imageSuffix;
    }

    public Integer getIndentComment() {
        return indentComment;
    }

    public void setIndentComment(Integer indentComment) {
        this.indentComment = indentComment;
    }

    public Boolean getIntegral() {
        return integral;
    }

    public void setIntegral(Boolean integral) {
        this.integral = integral;
    }

    public Integer getIntegralRate() {
        return integralRate;
    }

    public void setIntegralRate(Integer integralRate) {
        this.integralRate = integralRate;
    }

    public Boolean getIntegralStore() {
        return integralStore;
    }

    public void setIntegralStore(Boolean integralStore) {
        this.integralStore = integralStore;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getMemberDayLogin() {
        return memberDayLogin;
    }

    public void setMemberDayLogin(Integer memberDayLogin) {
        this.memberDayLogin = memberDayLogin;
    }

    public Integer getMemberRegister() {
        return memberRegister;
    }

    public void setMemberRegister(Integer memberRegister) {
        this.memberRegister = memberRegister;
    }

    public Integer getMiddleHeight() {
        return middleHeight;
    }

    public void setMiddleHeight(Integer middleHeight) {
        this.middleHeight = middleHeight;
    }

    public Integer getMiddleWidth() {
        return middleWidth;
    }

    public void setMiddleWidth(Integer middleWidth) {
        this.middleWidth = middleWidth;
    }

    public Boolean getSecurityCodeConsult() {
        return securityCodeConsult;
    }

    public void setSecurityCodeConsult(Boolean securityCodeConsult) {
        this.securityCodeConsult = securityCodeConsult;
    }

    public Boolean getSecurityCodeLogin() {
        return securityCodeLogin;
    }

    public void setSecurityCodeLogin(Boolean securityCodeLogin) {
        this.securityCodeLogin = securityCodeLogin;
    }

    public Boolean getSecurityCodeRegister() {
        return securityCodeRegister;
    }

    public void setSecurityCodeRegister(Boolean securityCodeRegister) {
        this.securityCodeRegister = securityCodeRegister;
    }

    public String getSecurityCodeType() {
        return securityCodeType;
    }

    public void setSecurityCodeType(String securityCodeType) {
        this.securityCodeType = securityCodeType;
    }

    public String getShare_code() {
        return share_code;
    }

    public void setShare_code(String share_code) {
        this.share_code = share_code;
    }

    public Integer getSmallHeight() {
        return smallHeight;
    }

    public void setSmallHeight(Integer smallHeight) {
        this.smallHeight = smallHeight;
    }

    public Integer getSmallWidth() {
        return smallWidth;
    }

    public void setSmallWidth(Integer smallWidth) {
        this.smallWidth = smallWidth;
    }

    public Boolean getSmsEnbale() {
        return smsEnbale;
    }

    public void setSmsEnbale(Boolean smsEnbale) {
        this.smsEnbale = smsEnbale;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }

    public String getSmsTest() {
        return smsTest;
    }

    public void setSmsTest(String smsTest) {
        this.smsTest = smsTest;
    }

    public String getSmsURL() {
        return smsURL;
    }

    public void setSmsURL(String smsURL) {
        this.smsURL = smsURL;
    }

    public String getSmsUserName() {
        return smsUserName;
    }

    public void setSmsUserName(String smsUserName) {
        this.smsUserName = smsUserName;
    }

    public Boolean getStore_allow() {
        return store_allow;
    }

    public void setStore_allow(Boolean store_allow) {
        this.store_allow = store_allow;
    }

    public String getStore_payment() {
        return store_payment;
    }

    public void setStore_payment(String store_payment) {
        this.store_payment = store_payment;
    }

    public String getSysLanguage() {
        return sysLanguage;
    }

    public void setSysLanguage(String sysLanguage) {
        this.sysLanguage = sysLanguage;
    }

    public String getTemplates() {
        return templates;
    }

    public void setTemplates(String templates) {
        this.templates = templates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getUser_creditrule() {
        return user_creditrule;
    }

    public void setUser_creditrule(String user_creditrule) {
        this.user_creditrule = user_creditrule;
    }

    public Boolean getVisitorConsult() {
        return visitorConsult;
    }

    public void setVisitorConsult(Boolean visitorConsult) {
        this.visitorConsult = visitorConsult;
    }

    public Boolean getVoucher() {
        return voucher;
    }

    public void setVoucher(Boolean voucher) {
        this.voucher = voucher;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Boolean getWebsiteState() {
        return websiteState;
    }

    public void setWebsiteState(Boolean websiteState) {
        this.websiteState = websiteState;
    }

    public Integer getZtc_price() {
        return ztc_price;
    }

    public void setZtc_price(Integer ztc_price) {
        this.ztc_price = ztc_price;
    }

    public Boolean getZtc_status() {
        return ztc_status;
    }

    public void setZtc_status(Boolean ztc_status) {
        this.ztc_status = ztc_status;
    }

    public Long getGoodsImage_id() {
        return goodsImage_id;
    }

    public void setGoodsImage_id(Long goodsImage_id) {
        this.goodsImage_id = goodsImage_id;
    }

    public Long getMemberIcon_id() {
        return memberIcon_id;
    }

    public void setMemberIcon_id(Long memberIcon_id) {
        this.memberIcon_id = memberIcon_id;
    }

    public Long getStoreImage_id() {
        return storeImage_id;
    }

    public void setStoreImage_id(Long storeImage_id) {
        this.storeImage_id = storeImage_id;
    }

    public Long getWebsiteLogo_id() {
        return websiteLogo_id;
    }

    public void setWebsiteLogo_id(Long websiteLogo_id) {
        this.websiteLogo_id = websiteLogo_id;
    }

    public Integer getDomain_allow_count() {
        return domain_allow_count;
    }

    public void setDomain_allow_count(Integer domain_allow_count) {
        this.domain_allow_count = domain_allow_count;
    }

    public Boolean getSecond_domain_open() {
        return second_domain_open;
    }

    public void setSecond_domain_open(Boolean second_domain_open) {
        this.second_domain_open = second_domain_open;
    }

    public String getSys_domain() {
        return sys_domain;
    }

    public void setSys_domain(String sys_domain) {
        this.sys_domain = sys_domain;
    }

    public Boolean getQq_login() {
        return qq_login;
    }

    public void setQq_login(Boolean qq_login) {
        this.qq_login = qq_login;
    }

    public String getQq_login_id() {
        return qq_login_id;
    }

    public void setQq_login_id(String qq_login_id) {
        this.qq_login_id = qq_login_id;
    }

    public String getQq_login_key() {
        return qq_login_key;
    }

    public void setQq_login_key(String qq_login_key) {
        this.qq_login_key = qq_login_key;
    }

    public String getQq_domain_code() {
        return qq_domain_code;
    }

    public void setQq_domain_code(String qq_domain_code) {
        this.qq_domain_code = qq_domain_code;
    }

    public String getSina_domain_code() {
        return sina_domain_code;
    }

    public void setSina_domain_code(String sina_domain_code) {
        this.sina_domain_code = sina_domain_code;
    }

    public Boolean getSina_login() {
        return sina_login;
    }

    public void setSina_login(Boolean sina_login) {
        this.sina_login = sina_login;
    }

    public String getSina_login_id() {
        return sina_login_id;
    }

    public void setSina_login_id(String sina_login_id) {
        this.sina_login_id = sina_login_id;
    }

    public String getSina_login_key() {
        return sina_login_key;
    }

    public void setSina_login_key(String sina_login_key) {
        this.sina_login_key = sina_login_key;
    }

    public String getImageWebServer() {
        return imageWebServer;
    }

    public void setImageWebServer(String imageWebServer) {
        this.imageWebServer = imageWebServer;
    }

    public Date getLucene_update() {
        return DateTimeUtils.parseDate(lucene_update);
    }

    public void setLucene_update(String lucene_update) {
        this.lucene_update = lucene_update;
    }

    public Integer getAlipay_fenrun() {
        return alipay_fenrun;
    }

    public void setAlipay_fenrun(Integer alipay_fenrun) {
        this.alipay_fenrun = alipay_fenrun;
    }

    public Integer getBalance_fenrun() {
        return balance_fenrun;
    }

    public void setBalance_fenrun(Integer balance_fenrun) {
        this.balance_fenrun = balance_fenrun;
    }

    public Integer getAuto_order_confirm() {
        return auto_order_confirm;
    }

    public void setAuto_order_confirm(Integer auto_order_confirm) {
        this.auto_order_confirm = auto_order_confirm;
    }

    public Integer getAuto_order_notice() {
        return auto_order_notice;
    }

    public void setAuto_order_notice(Integer auto_order_notice) {
        this.auto_order_notice = auto_order_notice;
    }

    public Integer getBargain_maximum() {
        return bargain_maximum;
    }

    public void setBargain_maximum(Integer bargain_maximum) {
        this.bargain_maximum = bargain_maximum;
    }

    public Float getBargain_rebate() {
        return bargain_rebate;
    }

    public void setBargain_rebate(Float bargain_rebate) {
        this.bargain_rebate = bargain_rebate;
    }

    public String getBargain_state() {
        return bargain_state;
    }

    public void setBargain_state(String bargain_state) {
        this.bargain_state = bargain_state;
    }

    public Integer getBargain_status() {
        return bargain_status;
    }

    public void setBargain_status(Integer bargain_status) {
        this.bargain_status = bargain_status;
    }

    public String getBargain_title() {
        return bargain_title;
    }

    public void setBargain_title(String bargain_title) {
        this.bargain_title = bargain_title;
    }

    public String getService_qq_list() {
        return service_qq_list;
    }

    public void setService_qq_list(String service_qq_list) {
        this.service_qq_list = service_qq_list;
    }

    public String getService_telphone_list() {
        return service_telphone_list;
    }

    public void setService_telphone_list(String service_telphone_list) {
        this.service_telphone_list = service_telphone_list;
    }

    public Integer getSys_delivery_maximum() {
        return sys_delivery_maximum;
    }

    public void setSys_delivery_maximum(Integer sys_delivery_maximum) {
        this.sys_delivery_maximum = sys_delivery_maximum;
    }

    public Boolean getUc_bbs() {
        return uc_bbs;
    }

    public void setUc_bbs(Boolean uc_bbs) {
        this.uc_bbs = uc_bbs;
    }

    public String getKuaidi_id() {
        return kuaidi_id;
    }

    public void setKuaidi_id(String kuaidi_id) {
        this.kuaidi_id = kuaidi_id;
    }

    public String getUc_api() {
        return uc_api;
    }

    public void setUc_api(String uc_api) {
        this.uc_api = uc_api;
    }

    public String getUc_appid() {
        return uc_appid;
    }

    public void setUc_appid(String uc_appid) {
        this.uc_appid = uc_appid;
    }

    public String getUc_database() {
        return uc_database;
    }

    public void setUc_database(String uc_database) {
        this.uc_database = uc_database;
    }

    public String getUc_database_port() {
        return uc_database_port;
    }

    public void setUc_database_port(String uc_database_port) {
        this.uc_database_port = uc_database_port;
    }

    public String getUc_database_pws() {
        return uc_database_pws;
    }

    public void setUc_database_pws(String uc_database_pws) {
        this.uc_database_pws = uc_database_pws;
    }

    public String getUc_database_url() {
        return uc_database_url;
    }

    public void setUc_database_url(String uc_database_url) {
        this.uc_database_url = uc_database_url;
    }

    public String getUc_database_username() {
        return uc_database_username;
    }

    public void setUc_database_username(String uc_database_username) {
        this.uc_database_username = uc_database_username;
    }

    public String getUc_ip() {
        return uc_ip;
    }

    public void setUc_ip(String uc_ip) {
        this.uc_ip = uc_ip;
    }

    public String getUc_key() {
        return uc_key;
    }

    public void setUc_key(String uc_key) {
        this.uc_key = uc_key;
    }

    public String getUc_table_preffix() {
        return uc_table_preffix;
    }

    public void setUc_table_preffix(String uc_table_preffix) {
        this.uc_table_preffix = uc_table_preffix;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public Integer getBargain_validity() {
        return bargain_validity;
    }

    public void setBargain_validity(Integer bargain_validity) {
        this.bargain_validity = bargain_validity;
    }

    public Integer getDelivery_amount() {
        return delivery_amount;
    }

    public void setDelivery_amount(Integer delivery_amount) {
        this.delivery_amount = delivery_amount;
    }

    public Integer getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(Integer delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getDelivery_title() {
        return delivery_title;
    }

    public void setDelivery_title(String delivery_title) {
        this.delivery_title = delivery_title;
    }

    public String getWebsiteCss() {
        return websiteCss;
    }

    public void setWebsiteCss(String websiteCss) {
        this.websiteCss = websiteCss;
    }

    public Integer getCombin_amount() {
        return combin_amount;
    }

    public void setCombin_amount(Integer combin_amount) {
        this.combin_amount = combin_amount;
    }

    public Integer getCombin_count() {
        return combin_count;
    }

    public void setCombin_count(Integer combin_count) {
        this.combin_count = combin_count;
    }

    public Integer getZtc_goods_view() {
        return ztc_goods_view;
    }

    public void setZtc_goods_view(Integer ztc_goods_view) {
        this.ztc_goods_view = ztc_goods_view;
    }

    public Integer getAuto_order_evaluate() {
        return auto_order_evaluate;
    }

    public void setAuto_order_evaluate(Integer auto_order_evaluate) {
        this.auto_order_evaluate = auto_order_evaluate;
    }

    public Integer getAuto_order_return() {
        return auto_order_return;
    }

    public void setAuto_order_return(Integer auto_order_return) {
        this.auto_order_return = auto_order_return;
    }

    public Boolean getWeixin_store() {
        return weixin_store;
    }

    public void setWeixin_store(Boolean weixin_store) {
        this.weixin_store = weixin_store;
    }

    public Integer getWeixin_amount() {
        return weixin_amount;
    }

    public void setWeixin_amount(Integer weixin_amount) {
        this.weixin_amount = weixin_amount;
    }

    public Integer getConfig_payment_type() {
        return config_payment_type;
    }

    public void setConfig_payment_type(Integer config_payment_type) {
        this.config_payment_type = config_payment_type;
    }

    public String getWeixin_account() {
        return weixin_account;
    }

    public void setWeixin_account(String weixin_account) {
        this.weixin_account = weixin_account;
    }

    public String getWeixin_appId() {
        return weixin_appId;
    }

    public void setWeixin_appId(String weixin_appId) {
        this.weixin_appId = weixin_appId;
    }

    public String getWeixin_appSecret() {
        return weixin_appSecret;
    }

    public void setWeixin_appSecret(String weixin_appSecret) {
        this.weixin_appSecret = weixin_appSecret;
    }

    public String getWeixin_token() {
        return weixin_token;
    }

    public void setWeixin_token(String weixin_token) {
        this.weixin_token = weixin_token;
    }

    public String getWeixin_welecome_content() {
        return weixin_welecome_content;
    }

    public void setWeixin_welecome_content(String weixin_welecome_content) {
        this.weixin_welecome_content = weixin_welecome_content;
    }

    public Long getStore_weixin_logo_id() {
        return store_weixin_logo_id;
    }

    public void setStore_weixin_logo_id(Long store_weixin_logo_id) {
        this.store_weixin_logo_id = store_weixin_logo_id;
    }

    public Long getWeixin_qr_img_id() {
        return weixin_qr_img_id;
    }

    public void setWeixin_qr_img_id(Long weixin_qr_img_id) {
        this.weixin_qr_img_id = weixin_qr_img_id;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }

    private Long store_weixin_logo_id;
    private Long weixin_qr_img_id;
    private String site_url;


}
