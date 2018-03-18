<#assign P_CURRENT_TOP='order' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="订单" />
<#assign P_NAV3="支付方式" />
<#assign P_CURRENT_OP="PaymentSetting" />

<@override name="main">
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>

<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<div class="ncsc-layout wrapper">
  <#include "layout_order.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
        <div class="productmain">
            <div class="ordernav">
                <ul class="orderul">
                    <li class="this">支付方式</li>
                </ul>
            </div>
            <div class="ordercon">
                <div class="operation">
                    <table width="800" border="0" cellspacing="0" cellpadding="0" class="paymtable" >
                        <tr>
                            <td width="160" style="padding-left:15px;">名称</td>
                            <td align="center" width="180">插件说明</td>
                            <td align="center" width="40">启用</td>
                            <td align="center" width="150">操作</td>
                        </tr>
                        <#if (map["alipay"]!false) == true >
                        <tr>
                            <td style="padding-left:15px;">支付宝</td>
                            <td>支付宝网站(www.alipay.com) 是国内先进的网上支付平台，<br /></td>
                            <#assign ret=paymentTools.queryPayment("alipay",user_id) />
                            <#assign install=ret["install"] />
                            <#assign already=ret["already"] />
                            <#if (install!false) == true >
                                <#assign alipay_install="是" />
                            <#else>
                                <#assign alipay_install="否" />
                            </#if>
                            <td align="center">${alipay_install!}</td>
                            <td align="center" >
                                <#if (already!false) == true >
                                    <span class="configure">
                                        <a href="${S_URL}/payment_edit?mark=alipay">配置</a>
                                    </span>
                                    <span class="unloading">
                                        <a href="${S_URL}/payment_uninstall?mark=alipay">卸载</a>
                                    </span>
                                <#else>
                                    <span class="installation">
                                        <a  href="${S_URL}/payment_install?mark=alipay">安装</a>
                                    </span>
                                </#if>
                            </td>
                        </tr>
                        </#if>
                        <#if (map["alipay_wap"]!false)==true >
                        <tr>
                            <td style="padding-left:15px;">支付宝手机网页支付</td>
                            <td>支付宝网站(www.alipay.com) 是国内先进的网上支付平台，<br /></td>
                            <#assign ret=paymentTools.queryPayment("alipay_wap",user_id) />
                            <#assign install=ret["install"] />
                            <#assign already=ret["already"] />
                            <#if (install!false) == true >
                              <#assign alipay_install="是" />
                            <#else>
                              <#assign alipay_install="否" />
                            </#if>
                            <td align="center">${alipay_install!}</td>
                            <td align="center" >
                              <#if (already!false) == true >
                                  <span class="configure">
                                      <a href="${S_URL}/payment_edit?mark=alipay_wap">配置</a></span>
                                  <span class="unloading">
                                      <a href="${S_URL}/payment_uninstall?mark=alipay_wap">卸载</a></span>
                              <#else>
                                  <span class="installation">
                                      <a  href="${S_URL}/payment_install?mark=alipay_wap">安装</a></span>
                              </#if>
                            </td>
                        </tr>
                        </#if>
                      <#if (map["chinabank"]!false)==true >
                        <tr>
                            <td style="padding-left:15px;">网银在线</td>
                            <td>网银在线(www.chinabank.com.cn)以网上转账方式将相应交易款划转到商户<br />指定银行账号中。</td>
                          <#assign ret=paymentTools.queryPayment("chinabank",user_id) />
                          <#assign install=ret["install"] />
                          <#assign already=ret["already"] />
                          <#if (install!false) == true >
                            <#assign chinabank_install="是" />
                          <#else>
                            <#assign chinabank_install="否" />
                          </#if>
                            <td align="center">${chinabank_install!}</td>
                            <td align="center">
                              <#if (already!false) == true >
                                  <span class="configure">
                                      <a href="${S_URL}/payment_edit?mark=chinabank">配置</a></span>
                                  <span class="unloading">
                                      <a href="${S_URL}/payment_uninstall?mark=chinabank">卸载</a></span>
                              <#else>
                                  <span class="installation">
                                      <a  href="${S_URL}/payment_install?mark=chinabank">安装</a>
                              </#if>
                            </td>
                        </tr>
                      </#if>
                      <#if (map["tenpay"]!false)==true >
                        <tr>
                          <td style="padding-left:15px;">财付通</td>
                          <td>财付通(www.tenpay.com) - 本即时到账接口无需预付费，任何订单金额均<br />即时到达您的账户，只收单笔 1% 手续费。</td>
                              <#assign ret=paymentTools.queryPayment("tenpay",user_id) />
                              <#assign install=ret["install"] />
                              <#assign already=ret["already"] />
                              <#if (install!false) == true >
                                <#assign tenpay_install="是" />
                              <#else>
                                <#assign tenpay_install="否" />
                              </#if>
                          <td align="center">${tenpay_install!}</td>
                          <td align="center">
                              <#if (paymentTools.queryPayment("tenpay",user_id)["install"]!false)==true >
                                  <span class="configure">
                                      <a href="${S_URL}/payment_edit?mark=tenpay">配置</a></span>
                                  <span class="unloading">
                                      <a href="${S_URL}/payment_uninstall?mark=tenpay">卸载</a></span>
                              <#else>
                                  <span class="installation">
                                  <a href="${S_URL}/payment_install?mark=tenpay">安装</a>
                              </#if>
                          </td>
                        </tr>
                      </#if>
                      <#if (map["bill"]!false)==true >
                          <tr>
                              <td style="padding-left:15px;">快钱支付</td>
                              <td>快钱是国内领先的独立第三方支付企业，旨在为各类企业及个人提供安全、便捷和保密的支付清算与账务服务，其推出的支付产品包括但不限于人民币支付，外卡支  付，神州行卡支付，联通充值卡支付，VPOS支付等众多支付产品, 支持互联网、手机、电话和POS等多种终端,   以满足各类企业和个人的不同支付需求。</td>
                            <#if (paymentTools.queryPayment("bill",user_id)["install"]!false) == true >
                              <#assign bill_install="是" />
                            <#else>
                              <#assign bill_install="否" />
                            </#if>
                              <td align="center">${bill_install!}</td>
                              <td align="center">
                                  <#if (paymentTools.queryPayment("bill",user_id)["install"]!false)==true >
                                      <span class="configure">
                                          <a href="${S_URL}/payment_edit?mark=bill">配置</a></span>
                                      <span class="unloading">
                                          <a href="${S_URL}/payment_uninstall?mark=bill">卸载</a></span>
                                  <#else>
                                      <span class="installation">
                                          <a href="${S_URL}/payment_install?mark=bill">安装</a></span>
                                  </#if>
                              </td>
                          </tr>
                      </#if>
                      <#if (map["paypal"]!false)==true >
                        <#assign ret=paymentTools.queryPayment("paypal",user_id) />
                        <#assign install=ret["install"] />
                        <#assign already=ret["already"] />
                        <#if (install!false) == true >
                          <#assign paypal_install="是" />
                        <#else>
                          <#assign paypal_install="否" />
                        </#if>
                          <tr>
                            <td style="padding-left:15px;">Paypal</td>
                            <td>www.paypal.com</td>
                            <td align="center">${paypal_install!}</td>
                            <td align="center">
                              <#if (already!false) == true >
                                <span class="configure">
                                    <a href="${S_URL}/payment_edit?mark=paypal">配置</a></span>
                                <span class="unloading">
                                    <a href="${S_URL}/payment_uninstall?mark=paypal">卸载</a></span>
                              <#else>
                                <span class="installation">
                                    <a  href="${S_URL}/payment_install?mark=paypal">安装</a></span>
                              </#if>
                            </td>
                          </tr>
                      </#if>
                      <#if (map["payafter"]!false)==true >
                          <tr>
                              <td style="padding-left:15px;">货到付款</td>
                              <td>货到付款</td>
                            <#if (paymentTools.queryPayment("payafter",user_id)["install"]!false) == true >
                              <#assign payafter_install="是" />
                            <#else>
                              <#assign payafter_install="否" />
                            </#if>
                              <td align="center">${payafter_install!}</td>
                              <td align="center">
                                <#if (paymentTools.queryPayment("payafter",user_id)["install"]!false)==true >
                                  <span class="configure">
                                      <a href="${S_URL}/payment_edit?mark=payafter">配置</a></span>
                                  <span class="unloading">
                                      <a href="${S_URL}/payment_uninstall?mark=payafter">卸载</a></span>
                                <#else>
                                  <span class="installation">
                                      <a  href="${S_URL}/payment_install?mark=payafter">安装</a></span>
                                </#if>
                              </td>
                          </tr>
                      </#if>
                      <#if (map["outline"]!false)==true >
                        <tr>
                          <td style="padding-left:15px;">线下支付</td>
                          <td>线下支付</td>
                          <#if (paymentTools.queryPayment("outline",user_id)["install"]!false) == true >
                            <#assign outline_install="是" />
                          <#else>
                            <#assign outline_install="否" />
                          </#if>
                          <td align="center">${outline_install!}</td>
                          <td align="center">
                            <#if (paymentTools.queryPayment("outline",user_id)["install"]!false)==true >
                              <span class="configure">
                                  <a href="${S_URL}/payment_edit?mark=outline">配置</a></span>
                              <span class="unloading">
                                  <a href="${S_URL}/payment_uninstall?mark=outline">卸载</a></span>
                              <#else>
                              <span class="installation">
                                  <a  href="${S_URL}/payment_install?mark=outline">安装</a></span>
                            </#if>
                          </td>
                        </tr>
                      </#if>
                      <#if (map["balance"]!false)==true >
                        <tr>
                          <td style="padding-left:15px;">预存款支付</td>
                          <td>预存款支付</td>
                          <#if (paymentTools.queryPayment("balance",user_id)["install"]!false) == true >
                            <#assign outline_install="是" />
                          <#else>
                            <#assign outline_install="否" />
                          </#if>
                          <td align="center">${balance_install!}</td>
                          <td align="center">
                            <#if (paymentTools.queryPayment("balance",user_id)["install"]!false)==true >
                              <span class="configure">
                                  <a href="${S_URL}/payment_edit?mark=balance">配置</a></span>
                              <span class="unloading">
                                  <a href="${S_URL}/payment_uninstall?mark=balance">卸载</a></span>
                            <#else>
                              <span class="installation">
                                  <a href="${S_URL}/payment_install?mark=balance">安装</a></span>
                            </#if>
                          </td>
                        </tr>
                      </#if>
                    </table>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
