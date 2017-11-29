package org.goshop.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 25/11/2017.
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SysConfig sysConfig;

    @Override
    public SysConfig getConfig() {
//        if (sysConfig == null)
//            load();
        return sysConfig;
    }
//    public void load() {
//        Properties prop = new Properties();
//        try{
//            //读取属性文件a.properties
//            String path = ResourceUtils.getFile("classpath:system.properties").getPath();
//            InputStream in = new BufferedInputStream(new FileInputStream(path));
//            prop.load(in);     ///加载属性列表
//            Iterator<String> it=prop.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                System.out.println(key+":"+prop.getProperty(key));
//            }
//            in.close();
//
//            config = new SysConfig();
//            config.setDeleteStatus(CommUtil.null2Boolean(prop.getProperty("deleteStatus")));
//            config.setAddress(prop.getProperty("address"));
//            config.setBigHeight(CommUtil.null2Int(prop.getProperty("bigHeight")));
//            config.setBigWidth(CommUtil.null2Int(prop.getProperty("bigWidth")));
//            config.setCloseReason(prop.getProperty("closeReason"));
//            config.setCodeStat(prop.getProperty("codeStat"));
//            config.setComplaint_time(CommUtil.null2Int(prop.getProperty("complaint_time")));
//            config.setConsumptionRatio(CommUtil.null2Int(prop.getProperty("consumptionRatio")));
//            config.setCopyRight(prop.getProperty("copyRight"));
//            config.setCreditrule(prop.getProperty("creditrule"));
//            config.setDeposit(CommUtil.null2Boolean(prop.getProperty("deposit")));
//            config.setDescription(prop.getProperty("description"));
//            config.setEmailEnable(CommUtil.null2Boolean(prop.getProperty("emailEnable")));
//            config.setEmailHost(prop.getProperty("emailHost"));
//            config.setEmailPort(CommUtil.null2Int(prop.getProperty("emailPort")));
//            config.setEmailPws(prop.getProperty("emailPws"));
//            config.setEmailTest(prop.getProperty("emailTest"));
//            config.setEmailUser(prop.getProperty("emailUser"));
//            config.setEmailUserName(prop.getProperty("emailUserName"));
//            config.setEveryIndentLimit(CommUtil.null2Int(prop.getProperty("everyIndentLimit")));
//            config.setGold(CommUtil.null2Int(prop.getProperty("gold")));
//            config.setGoldMarketValue(CommUtil.null2Int(prop.getProperty("goldMarketValue")));
//            config.setGroupBuy(CommUtil.null2Boolean(prop.getProperty("groupBuy")));
//            config.setHotSearch(prop.getProperty("hotSearch"));
//            config.setImageFilesize(CommUtil.null2Int(prop.getProperty("imageFilesize")));
//            config.setImageSaveType(prop.getProperty("imageSaveType"));
//            config.setImageSuffix(prop.getProperty("imageSuffix"));
//            config.setIndentComment(CommUtil.null2Int(prop.getProperty("indentComment")));
//            config.setIntegral(CommUtil.null2Boolean(prop.getProperty("integral")));
//            config.setIntegralRate(CommUtil.null2Int(prop.getProperty("integralRate")));
//            config.setIntegralStore(CommUtil.null2Boolean(prop.getProperty("integralStore")));
//            config.setKeywords(prop.getProperty("keywords"));
//            config.setMemberDayLogin(CommUtil.null2Int(prop.getProperty("memberDayLogin")));
//            config.setMemberRegister(CommUtil.null2Int(prop.getProperty("memberRegister")));
//            config.setMiddleHeight(CommUtil.null2Int(prop.getProperty("middleHeight")));
//            config.setMiddleWidth(CommUtil.null2Int(prop.getProperty("middleWidth")));
//            config.setSecurityCodeConsult(CommUtil.null2Boolean(prop.getProperty("securityCodeConsult")));
//            config.setSecurityCodeLogin(CommUtil.null2Boolean(prop.getProperty("securityCodeLogin")));
//            config.setSecurityCodeRegister(CommUtil.null2Boolean(prop.getProperty("securityCodeRegister")));
//            config.setSecurityCodeType(prop.getProperty("securityCodeType"));
//            config.setShare_code(prop.getProperty("share_code"));
//            config.setSmallHeight(CommUtil.null2Int(prop.getProperty("smallHeight")));
//            config.setSmallWidth(CommUtil.null2Int(prop.getProperty("smallWidth")));
//            config.setSmsEnbale(CommUtil.null2Boolean(prop.getProperty("smsEnbale")));
//            config.setSmsPassword(prop.getProperty("smsPassword"));
//            config.setSmsTest(prop.getProperty("smsTest"));
//            config.setSmsURL(prop.getProperty("smsURL"));
//            config.setSmsUserName(prop.getProperty("smsUserName"));
//            config.setStore_allow(CommUtil.null2Boolean(prop.getProperty("store_allow")));
//            config.setStore_payment(prop.getProperty("store_payment"));
//            config.setSysLanguage(prop.getProperty("sysLanguage"));
//            config.setTemplates(prop.getProperty("templates"));
//            config.setTitle(prop.getProperty("title"));
//            config.setUploadFilePath(prop.getProperty("uploadFilePath"));
//            config.setUser_creditrule(prop.getProperty("user_creditrule"));
//            config.setVisitorConsult(CommUtil.null2Boolean(prop.getProperty("visitorConsult")));
//            config.setVoucher(CommUtil.null2Boolean(prop.getProperty("voucher")));
//            config.setWebsiteName(prop.getProperty("websiteName"));
//            config.setWebsiteState(CommUtil.null2Boolean(prop.getProperty("websiteState")));
//            config.setZtc_price(CommUtil.null2Int(prop.getProperty("ztc_price")));
//            config.setZtc_status(CommUtil.null2Boolean(prop.getProperty("ztc_status")));
//            config.setGoodsImage_id(CommUtil.null2Long(prop.getProperty("goodsImage_id")));
//            config.setMemberIcon_id(CommUtil.null2Long(prop.getProperty("memberIcon_id")));
//            config.setStoreImage_id(CommUtil.null2Long(prop.getProperty("storeImage_id")));
//            config.setWebsiteLogo_id(CommUtil.null2Long(prop.getProperty("websiteLogo_id")));
//            config.setDomain_allow_count(CommUtil.null2Int(prop.getProperty("domain_allow_count")));
//            config.setSecond_domain_open(CommUtil.null2Boolean(prop.getProperty("second_domain_open")));
//            config.setSys_domain(prop.getProperty("sys_domain"));
//            config.setQq_login(CommUtil.null2Boolean(prop.getProperty("qq_login")));
//            config.setQq_login_id(prop.getProperty("qq_login_id"));
//            config.setQq_login_key(prop.getProperty("qq_login_key"));
//            config.setQq_domain_code(prop.getProperty("qq_domain_code"));
//            config.setSina_domain_code(prop.getProperty("sina_domain_code"));
//            config.setSina_login(CommUtil.null2Boolean(prop.getProperty("sina_login")));
//            config.setSina_login_id(prop.getProperty("sina_login_id"));
//            config.setSina_login_key(prop.getProperty("sina_login_key"));
//            config.setImageWebServer(prop.getProperty("imageWebServer"));
//            config.setLucene_update(DateTimeUtils.parseDate(prop.getProperty("lucene_update")));
//            config.setAlipay_fenrun(CommUtil.null2Int(prop.getProperty("alipay_fenrun")));
//            config.setBalance_fenrun(CommUtil.null2Int(prop.getProperty("balance_fenrun")));
//            config.setAuto_order_confirm(CommUtil.null2Int(prop.getProperty("auto_order_confirm")));
//            config.setAuto_order_notice(CommUtil.null2Int(prop.getProperty("auto_order_notice")));
//            config.setBargain_maximum(CommUtil.null2Int(prop.getProperty("bargain_maximum")));
//            config.setBargain_rebate(Float.parseFloat(prop.getProperty("bargain_rebate")));
//            config.setBargain_state(prop.getProperty("bargain_state"));
//            config.setBargain_status(CommUtil.null2Int(prop.getProperty("bargain_status")));
//            config.setBargain_title(prop.getProperty("bargain_title"));
//            config.setService_qq_list(prop.getProperty("service_qq_list"));
//            config.setService_telphone_list(prop.getProperty("service_telphone_list"));
//            config.setSys_delivery_maximum(CommUtil.null2Int(prop.getProperty("sys_delivery_maximum")));
//            config.setUc_bbs(CommUtil.null2Boolean(prop.getProperty("uc_bbs")));
//            config.setKuaidi_id(prop.getProperty("kuaidi_id"));
//            config.setUc_api(prop.getProperty("uc_api"));
//            config.setUc_appid(prop.getProperty("uc_appid"));
//            config.setUc_database(prop.getProperty("uc_database"));
//            config.setUc_database_port(prop.getProperty("uc_database_port"));
//            config.setUc_database_pws(prop.getProperty("uc_database_pws"));
//            config.setUc_database_url(prop.getProperty("uc_database_url"));
//            config.setUc_database_username(prop.getProperty("uc_database_username"));
//            config.setUc_ip(prop.getProperty("uc_ip"));
//            config.setUc_key(prop.getProperty("uc_key"));
//            config.setUc_table_preffix(prop.getProperty("uc_table_preffix"));
//            config.setCurrency_code(prop.getProperty("currency_code"));
//            config.setBargain_validity(CommUtil.null2Int(prop.getProperty("bargain_validity")));
//            config.setDelivery_amount(CommUtil.null2Int(prop.getProperty("delivery_amount")));
//            config.setDelivery_status(CommUtil.null2Int(prop.getProperty("delivery_status")));
//            config.setDelivery_title(prop.getProperty("delivery_title"));
//            config.setWebsiteCss(prop.getProperty("websiteCss"));
//            config.setCombin_amount(CommUtil.null2Int(prop.getProperty("combin_amount")));
//            config.setCombin_count(CommUtil.null2Int(prop.getProperty("combin_count")));
//            config.setZtc_goods_view(CommUtil.null2Int(prop.getProperty("ztc_goods_view")));
//            config.setAuto_order_evaluate(CommUtil.null2Int(prop.getProperty("auto_order_evaluate")));
//            config.setAuto_order_return(CommUtil.null2Int(prop.getProperty("auto_order_return")));
//            config.setWeixin_store(CommUtil.null2Boolean(prop.getProperty("weixin_store")));
//            config.setWeixin_amount(CommUtil.null2Int(prop.getProperty("weixin_amount")));
//            config.setConfig_payment_type(CommUtil.null2Int(prop.getProperty("config_payment_type")));
//            config.setWeixin_account(prop.getProperty("weixin_account"));
//            config.setWeixin_appId(prop.getProperty("weixin_appId"));
//            config.setWeixin_appSecret(prop.getProperty("weixin_appSecret"));
//            config.setWeixin_token(prop.getProperty("weixin_token"));
//            config.setWeixin_welecome_content(prop.getProperty("weixin_welecome_content"));
//            config.setStore_weixin_logo_id(CommUtil.null2Long(prop.getProperty("store_weixin_logo_id")));
//            config.setWeixin_qr_img_id(CommUtil.null2Long(prop.getProperty("weixin_qr_img_id")));
//            config.setSite_url(prop.getProperty("site_url"));
//
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//    }



}
