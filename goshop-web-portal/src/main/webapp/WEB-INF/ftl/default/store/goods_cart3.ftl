<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${(store.storeName)!} - ${(config.poweredby)!}</title>
    <meta name="keywords" content="${(store.storeKeywords)!}" />
    <meta name="description" content="${(store.storeDescription)!}" />
    <meta name="generator" content="${(config.meta_generator)!}" />
    <meta name="author" content="${(config.meta_author)!}">
    <meta name="copyright" content="${(config.copyRight)!}">
    <#if (config.website_ico)??>
        <link rel="shortcut icon" href="${imageWebServer!}/${(config.website_ico.path)!}/${(config.website_ico.name)!}" type="image/x-icon"/>
    </#if>
    <link href="${S_URL}/static/styles/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/overlay.css" type="text/css" rel="stylesheet" />
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script>
//返回当前页面高度
function order_pay(){
  var h=document.body.clientHeight;
  var top= document.documentElement.scrollTop;
  top=top+(h-top)/2;
  jQuery('.black_overlay').css("height",h);
  jQuery(".white_content").css("top",top);

  var payType = jQuery('#selectpay input[name="payType"]:checked ').val();
  var orderId = jQuery("#order_id").val();

  if(payType=="wxcodepay"){
  	var html = '';
  	jQuery.get("${S_URL}/wechat/wxcodepay",{"order_id":orderId},function(data){
  		html += data;
		jQuery(html).appendTo("#wxPayCode");
		jQuery("#wxcodepay_overlay").show();
  	},"text");
  } else {
  	jQuery('#pay_overlay').show();
  	jQuery('#theForm').submit();
  }

}
window.onscroll=function(){
  var h=document.body.clientHeight;
  var top= document.documentElement.scrollTop;
  top=top-50+(h-top)/2;
  jQuery(".white_content").css("top",top);
}
</script>
</head>
<body>
${httpInclude.include("/top")}
<div class="main">
    ${httpInclude.include("/head")}
    <div class="index">
        <div class="index2">
            <div class="Steps">
                <ul>
                    <li>1.查看购物车</li>
                    <li>2.确认订单信息</li>
                    <li class="this">3.付款到卖家</li>
                    <li>4.确认收货</li>
                    <li class="last">5.评价</li>
                </ul>
            </div>
            <form action="${S_URL}/order_pay" method="post" target="_blank" id="theForm">
                <div class="payorder">
                    <div class="pay_top">
                        <div class="payhh"></div>
                        <div class="paybot">
                            <h1>订单提交成功</h1>
                            <h2>您的订单已成功生成，选择您想用的支付支付方式进行支付</h2>
                            <ul>
                                <li><strong>订单编号：</strong><strong class="orange">${(of.orderId)!}</strong></li>
                                <li><strong>应付金额：</strong><strong class="orange">¥${(of.totalPrice)!}</strong></li>
                            </ul>
                            <h3>您可以在用户中心中<a href="${S_URL}/buyer/order">“我的订单”</a>查看该订单</h3>
                        </div>
                    </div>
                    <div class="bank">
                        <div class="banktitle">
                            <h1><span>您可以选择付款方式</span></h1>
                        </div>
                        <div id="selectpay" class="bankcar">
                        <#assign paym=config.getConfig_payment_type() />
                        <!-- 平台统一支付 -->
                        <#if paym==1>
                        <ul>
                            <li>
                                <h1>在线支付</h1>
                                <#assign install=paymentTools.queryShopPayment("alipay").get("install") />
                                <#assign content=paymentTools.queryShopPayment("alipay").get("content") />
                                <#if (install!false)==true >
                                    <div class="bankone">
                                        <span class="banksp">
                                            <input name="payType" type="radio" value="alipay" checked="checked" />
                                        </span>
                                        <span class="bankimg">
                                            <img src="${imageWebServer!}/styles/common/images/payment/alipay.jpg" width="125" height="47" />
                                        </span>
                                        <span class="bank_txt">${content!}</span>
                                    </div>
                                </#if>
                                <#assign install=paymentTools.queryShopPayment("paypal").get("install") />
                                <#assign content=paymentTools.queryShopPayment("paypal").get("content") />
                                <#if (install!false)==true >
                                <div class="bankone">
                                    <span class="banksp">
                                        <input name="payType" type="radio" value="paypal" />
                                    </span>
                                    <span class="bankimg">
                                        <img src="${imageWebServer!}/styles/common/images/payment/paypal.jpg" width="125" height="47" /></span>
                                    <span class="bank_txt">${content!}</span>
                                </div>
                                </#if>
                                <#assign install=paymentTools.queryShopPayment("tenpay").get("install") />
                                <#assign content=paymentTools.queryShopPayment("tenpay").get("content") />
                                <#if (install!false)==true >
                                <div class="bankone">
                                  <span class="banksp">
                                  <input name="payType" type="radio" value="tenpay" />
                                  </span> <span class="bankimg">
                                    <img src="${imageWebServer!}/styles/common/images/payment/tenpay.jpg" width="125" height="47" /></span>
                                  <span class="bank_txt">${content!}</span>
                                </div>
                                </#if>
                                <#assign install = paymentTools.queryShopPayment("wxcodepay").get("install") />
                              <#assign content = paymentTools.queryShopPayment("wxcodepay").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                      <span class="banksp">
                                          <input name="payType" type="radio" value="wxcodepay" />
                                      </span> <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/weixin.png" width="125" height="47" /></span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                              <#assign install=paymentTools.queryShopPayment("chinabank").get("install") />
                              <#assign install=paymentTools.queryShopPayment("chinabank").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                      <span class="banksp">
                                          <input name="payType" type="radio" value="chinabank" />
                                      </span> <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/chinabank.jpg" width="125" height="47" /></span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                              <#assign install=paymentTools.queryShopPayment("bill").get("install") />
                              <#assign content=paymentTools.queryShopPayment("bill").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                      <span class="banksp">
                                          <input name="payType" type="radio" value="bill" />
                                      </span>
                                      <span class="bankimg">
                                          <img src="${imageWebServer!}/styles/common/images/payment/bill.jpg" width="125" height="47" /></span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                            </li>
                            <li>
                                <h1>线下支付</h1>
                              <#assign install=paymentTools.queryShopPayment("outline").get("install") />
                              <#assign install=paymentTools.queryShopPayment("outline").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                      <span class="banksp">
                                          <input name="payType" type="radio" value="outline" />
                                      </span><span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/outline.jpg" width="125" height="47" /></span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                            </li>
                            <li>
                            <h1>货到付款</h1>
                              <#assign install = paymentTools.queryShopPayment("payafter").get("install") />
                              <#assign content = paymentTools.queryShopPayment("payafter").get("content") />
                              <#if (install!false)==true >
                              <div class="bankone">
                                  <span class="banksp">
                                      <input name="payType" type="radio" value="payafter" />
                                  </span>
                                  <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/payafter.jpg" width="125" height="47" /></span>
                                  <span class="bank_txt">${content!}</span>
                              </div>
                              </#if>
                            </li>
                            <li>
                                <h1>预存款支付</h1>
                              <#assign install = paymentTools.queryShopPayment("balance").get("install") />
                              <#assign content = paymentTools.queryShopPayment("balance").get("content") />
                              <#if (install!false)==true >
                              <div class="bankone">
                                  <span class="banksp">
                                      <input name="payType" type="radio" value="balance" />
                                  </span>
                                  <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/balance.jpg" width="125" height="47" /></span>
                                  <span class="bank_txt">${content!}</span>
                              </div>
                              </#if>
                            </li>
                        </ul>
                            <!-- 店铺支付 -->
                        <#else>
                        <ul>
                            <li>
                                <h1>在线支付</h1>
                              <#assign install = paymentTools.queryStorePayment("alipay","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("alipay","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone"> <span class="banksp">
                                      <input name="payType" type="radio" value="alipay" checked="checked" />
                                  </span>
                                  <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/alipay.jpg" width="125" height="47" />
                                  </span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                              <#assign install = paymentTools.queryStorePayment("paypal","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("paypal","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                              <div class="bankone">
                              <span class="banksp">
                              <input name="payType" type="radio" value="paypal" />
                              </span>
                                  <span class="bankimg">
                                  <img src="${imageWebServer!}/styles/common/images/payment/paypal.jpg" width="125" height="47" />
                                  </span>
                                  <span class="bank_txt">${content!}</span>
                              </div>
                              </#if>
                              <#assign install = paymentTools.queryStorePayment("tenpay","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("tenpay","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                  <span class="banksp">
                                      <input name="payType" type="radio" value="tenpay" />
                                  </span>
                                  <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/tenpay.jpg" width="125" height="47" />
                                  </span>
                                  <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                              <#assign install = paymentTools.queryStorePayment("wxcodepay","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("wxcodepay","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                      <span class="banksp">
                                          <input name="payType" type="radio" value="wxcodepay" />
                                      </span>
                                      <span class="bankimg">
                                          <img src="${imageWebServer!}/styles/common/images/payment/weixin.png" width="125" height="47" /></span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                              <#assign install = paymentTools.queryStorePayment("chinabank","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("chinabank","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone"> <span class="banksp">
                                      <input name="payType" type="radio" value="chinabank" />
                                  </span><span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/chinabank.jpg" width="125" height="47" />
                                  </span>
                                      <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                              <#assign install = paymentTools.queryStorePayment("bill","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("bill","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone">
                                      <span class="banksp">
                                          <input name="payType" type="radio" value="bill" />
                                      </span>
                                      <span class="bankimg">
                                          <img src="${imageWebServer!}/styles/common/images/payment/bill.jpg" width="125" height="47" />
                                      </span>
                                      <span class="bank_txt">${(obj.content)!}</span>
                                  </div>
                              </#if>
                            </li>
                        <li>
                            <h1>线下支付</h1>
                          <#assign install = paymentTools.queryStorePayment("outline","${(of.storeId)!}").get("install") />
                          <#assign content = paymentTools.queryStorePayment("outline","${(of.storeId)!}").get("content") />
                          <#if (install!false)==true >
                              <div class="bankone"> <span class="banksp">
                                  <input name="payType" type="radio" value="outline" />
                              </span><span class="bankimg">
                                  <img src="${imageWebServer!}/styles/common/images/payment/outline.jpg" width="125" height="47" />
                              </span>
                              <span class="bank_txt">${content!}</span>
                              </div>
                          </#if>
                        </li>
                            <li>
                                <h1>货到付款</h1>
                              <#assign install = paymentTools.queryStorePayment("payafter","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("payafter","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone"> <span class="banksp">
                                      <input name="payType" type="radio" value="payafter" />
                                  </span>
                                  <span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/payafter.jpg" width="125" height="47" />
                                  </span>
                                      <span class="bank_txt">${content!}</span> </div>
                              </#if>
                            </li>
                            <li>
                                <h1>预存款支付</h1>
                              <#assign install = paymentTools.queryStorePayment("balance","${(of.storeId)!}").get("install") />
                              <#assign content = paymentTools.queryStorePayment("balance","${(of.storeId)!}").get("content") />
                              <#if (install!false)==true >
                                  <div class="bankone"> <span class="banksp">
                                      <input name="payType" type="radio" value="balance" />
                                  </span><span class="bankimg">
                                      <img src="${imageWebServer!}/styles/common/images/payment/balance.jpg" width="125" height="47" />
                                  </span>
                                  <span class="bank_txt">${content!}</span>
                                  </div>
                              </#if>
                            </li>
                        </ul>
                        </#if>
                        </div>
                        <div class="banknextbtn">
                          <input name="" type="button"  value="确认支付" onclick="order_pay();" style="cursor:pointer;"/>
                          <input name="order_id" type="hidden" id="order_id" value="${(of.id)!}" />
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    ${httpInclude.include("/footer")}
</div>
<div style="display:none;" id="pay_overlay">
  <div class="white_content">
  	<a href="javascript:void(0);" onclick="javascript:jQuery('#pay_overlay').hide();" class="white_close">
        <img src="${imageWebServer!}/styles/system/front/default/images/close.jpg" width="14" height="14" /></a>
      <div class="white_box">
      <h1>支付提醒</h1>
      <dl class="white_dl">
        <dt></dt>
        <dd>
          <div class="payf"><a href="${S_URL}/order_finish?order_id=${(of.id)!}">已经完成支付</a></div>
          <div class="payf"><a href="javascript:void(0);"  onclick="javascript:jQuery('#pay_overlay').hide();" >支付出现问题</a></div>
        </dd>
      </dl>
    </div>
  </div>
  <div class="black_overlay"></div>
</div>

<div style="display:none;" id="wxcodepay_overlay">
  <div class="wx_content">
  	<a href="javascript:void(0);" onclick="javascript:jQuery('#wxcodepay_overlay').hide();" class="white_close">
        <img src="${imageWebServer!}/styles/system/front/default/images/close.jpg" width="14" height="14" />
    </a>
      <div class="wx_box">
      <h1>微信二维码扫码支付</h1>
      <dl class="wx_dl">
        <dt id="wxPayCode"></dt>
        <dd>
          <div class="payf">请根据支付结果点击：</div>
          <div class="payf"><input type="button" value="完成支付" onclick="window.open('${S_URL}/order_finish?order_id=${(of.id)!}')">
          </div>
          <div class="payf"><a href="javascript:void(0);"  onclick="javascript:jQuery('#wxcodepay_overlay').hide();" >支付出现问题</a>
          </div>
        </dd>
      </dl>
    </div>
  </div>
  <div class="black_overlay"></div>
</div>

</body>
</html>
