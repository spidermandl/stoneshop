<@override name="main">
<#assign P_NAV1="我的商城" />
<#assign P_NAV2="买家中心" />
<#assign P_NAV3="订单详情" />
<#assign P_SIDEBAR = "ORDER" />

<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<script>
jQuery(document).ready(function(){
jQuery("#ship_info_a").click(
        function(){
        var dis = jQuery("#ship_info_ul").css("display");
        if(dis=="none"){
            jQuery("#ship_info_ul").slideDown();
            jQuery(this).find("img").attr("src","${S_URL}/static/images/user_down.png");
            }else{
            jQuery("#ship_info_ul").slideUp();
            jQuery(this).find("img").attr("src","${S_URL}/static/images/user_up.png");
                }
    });
//
});
</script>

<#include "../nav.ftl"/>

<div class="main">
    <style type="text/css">
        dl dd span {
            display: inline-block;
        }
    </style>

<div class="wrap">
  <div class="orderdetail">
    <div class="orderdh">
      <h1>订单详情</h1>
    </div>
    <div class="ordersee">
      <div class="ordersee1">
        <table border="0" cellspacing="0" cellpadding="0" class="tablesee1">
          <tr>
            <td width="80" align="right" class="blue2">订单状态：</td>
              <#if ((obj.orderStatus)!'-1')==0 >
                  <#assign status="已取消" />
              </#if>
              <#if ((obj.orderStatus)!'10')==10 >
                  <#assign status="待付款" />
              </#if>
              <#if ((obj.orderStatus)!'15')==15 >
                  <#assign status="线下支付待审核" />
              </#if>
              <#if ((obj.orderStatus)!'16')==16 >
                  <#assign status="货到付款待发货" />
              </#if>
              <#if ((obj.orderStatus)!'20')==20 >
                  <#assign status="已付款" />
              </#if>
              <#if ((obj.orderStatus)!'30')==30 >
                  <#assign status="已发货" />
              </#if>
              <#if ((obj.orderStatus)!'40')==40 >
                  <#assign status="已收货" />
              </#if>
              <#if ((obj.orderStatus)!'50')==50 >
                  <#assign status="已完成,已评价" />
              </#if>
              <#if ((obj.orderStatus)!'60')==60 >
                  <#assign status="已结束" />
              </#if>
            <td width="220" align="left"><strong style="color:#F60">${status!}</strong></td>
            <td width="80" align="right"  class="blue2">订单编号：</td>
            <td width="220" align="left">${(obj.orderId)!}</td>
            <td width="80" align="right"  class="blue2">下单时间：</td>
            <td width="220" align="left">${CommUtil.formatLongDate(obj.addtime)!}</td>
          </tr>
        </table>
      </div>
      <div class="ordersee2">
        <h2>卖家信息</h2>
        <table  border="0" cellspacing="0" cellpadding="0" class="tablesee2">
          <tr>
            <td width="80" align="right" class="blue2">店铺名：</td>
            <td width="220" align="left">${(obj.store.storeName)!}</td>
            <td width="80" align="right"  class="blue2">电话号码：</td>
            <td width="220" align="left">${(obj.store.storeTel)!}</td>
            <td width="80" align="right"  class="blue2">所在地区：</td>
            <td width="220" align="left">${(obj.store.area.parent.parent.areaname)!} ${(obj.store.area.parent.areaname)!} ${(obj.store.area.areaname)!}</td>
          </tr>
          <tr>
            <td width="80" align="right" class="blue2">MSN：</td>
            <td width="220" align="left">${(obj.store.storeMsn)!}</td>
            <td width="80" align="right"  class="blue2">QQ：</td>
            <td width="220" align="left">${(obj.store.storeQq)!}</td>
            <td width="80" align="right"  class="blue2">旺旺：</td>
            <td width="220" align="left">${(obj.store.storeWw)!}</td>
          </tr>
          <tr>
            <td width="80" align="right" class="blue2">详细地址：</td>
            <td colspan="5" align="left">${(obj.store.storeAddress)!}</td>
          </tr>
        </table>
      </div>
      <div class="ordersee3">
        <h2>订单信息</h2>
        <div class="orderinfo">
          <div class="orderinfo_r">
          <span class="frspan">运费：¥${CommUtil.null2Float(obj.shipPrice)?string('0.00')}<#if (obj.transport)??>[${(obj.transport)!}]</#if></span>
          <#if (obj.ci)??>
          <span class="frspan">使用优惠券：${(obj.ci.couponSn)!}<strong style="color:#F90">[¥${(obj.ci.coupon.couponAmount)!}]</strong></span>
          </#if>
          </div>
            <#assign payment = "未支付" />
            <#if ((obj.payment.mark)!'')=="alipay" >
                <#assign payment="支付宝"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="alipay_wap" >
                <#assign payment="手机网页支付宝"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="tenpay" >
                <#assign payment="财付通"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="bill" >
                <#assign payment="快钱"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="chinabank" >
                <#assign payment="网银在线"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="outline" >
                <#assign payment="线下支付"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="balance" >
                <#assign payment="预存款支付"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="payafter" >
                <#assign payment="货到付款"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="paypal" >
                <#assign payment="paypal"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="wxcodepay" >
                <#assign payment="微信扫码支付"/>
            </#if>
            <#if ((obj.payment.mark)!'')=="weixin_wap" >
                <#assign payment="微信公众号支付"/>
            </#if>
          <div class="orderinfo_r"><span class="frspan">订单总价：<strong style="color:#F90">¥${(obj.totalprice)?string('0.00')}</strong></span>
              <#if (obj.refund)??><span class="frspan">订单退款：<strong style="color:#F90">¥${(obj.refund)?string('0.00')}</strong></span></#if>
              <span class="frspan">支付方式：${payment!}</span>
              <span class="frspan">支付时间：${CommUtil.formatLongDate(obj.payTime)!}</span>
          </div>
          <div class="orderinfo_l">
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="119" align="center" style="background:#F9F9F9"><strong>商品图片</strong></td>
                <td width="352" height="31" style="background:#F9F9F9"><strong>商品名称</strong></td>
                <td width="62" align="center" style="background:#F9F9F9">商品属性</td>
                <td width="62" align="center" style="background:#F9F9F9">商品数量</td>
                <td width="90" align="center" style="background:#F9F9F9">商品价格</td>
              </tr>
              <#list (obj.gcs)! as gc>
              <tr style="border-bottom:1px solid #EEE;">
                  <#if (gc.goods.goods_main_photo)??>
                      <#assign img="${RES_URL}/${(gc.goods.goods_main_photo.path)!}/${(gc.goods.goods_main_photo.name)!}_small.${(gc.goods.goods_main_photo.ext)!}" />
                  <#else>
                      <#assign img="${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                  </#if>
                <td width="119" align="center" style="border-right:1px solid #EEE">
                    <span class="infoimg"><a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">
                        <img src="${img!}" width="62" height="62" /></a></span>
                </td>
                <td height="71">
                    <a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">${(gc.goods.goodsName)!}</a>
                    <#if ((gc.goods.groupBuy)!-1)==2 > <span style="color:#F00">(团购)</span> </#if>
                    <#if ((obj.goods.deliveryStatus)!-1)==2 > <span style="color:#F00">[买就送]</span> </#if>
                    <#if ((gc.goods.bargainStatus)!-1)==2 ><span style="color:#F00">(特价)</span> </#if>
                    <#if ((gc.cartType)!'')=="combin" >
                      <div class="baby_gp">
                        <a href="javascript:void(0);" style="color:#F00;">【组合商品】</a>
                      </div>
                    </#if>
                </td>
                <td align="center">${(gc.specInfo)!}</td>
                <td align="center">${(gc.count)!}</td>
                <td align="center"><strong style="color:#F90">¥${(gc.goods.goodsCurrentPrice)?string('0.00')}</strong></td>
              </tr>
              </#list>
            </table>
          </div>
        </div>
      </div>
      <div class="ordersee4">


        <h2>物流信息</h2>
        <#--<ul>-->
          <#--<li><span class="orseet">收货人:</span><span class="orseet2">${(obj.addr.trueName)!}</span></li>-->
          <#--<li><span class="orseet">收货地址:</span><span class="orseet2">${(obj.store.addr.area.parent.parent.areaname)!} ${(obj.store.addr.area.parent.areaname)!} ${(obj.addr.area.areaname)!} ${(obj.addr.area_info)!}</span></li>-->
          <#--<li><span class="orseet">邮政编码:</span><span class="orseet2">${(obj.addr.zip)!}</span></li>-->
          <#--<li><span class="orseet">电话号码:</span><span class="orseet2">${(obj.addr.telephone)!}</span></li>-->
          <#--<li><span class="orseet">手机号码:</span><span class="orseet2">${(obj.addr.mobile)!}</span></li>-->
          <#--<li><span class="orseet">配送方式:</span><span class="orseet2">${(obj.ship.shippingName)!}</span></li>-->
          <#--<li><span class="orseet">配送时间:</span><span class="orseet2">${CommUtil.formatLongDate(obj.shipTime)}</span></li>-->
            <#--<#if (obj.msg)?? && obj.msg !="" >-->
                <#--<li><span class="orseet">买家留言:</span><span class="orseet2">${(obj.msg)!}</span></li>-->
            <#--</#if>-->
          <#--<li>-->
          <#--<span class="orseet">发票类型:</span>-->
          <#--<span class="orseet2"><#if ((obj.invoiceType)!-1)==0>个人 <#else> 单位</#if></span></li>-->
          <#--<#if (obj.invoice)?? && obj.invoice !="" >-->
              <#--<li><span class="orseet">发票台头:</span>-->
                  <#--<span class="orseet2">${(obj.invoice)!}</span>-->
              <#--</li>-->
          <#--</#if>-->
        <#--</ul>-->
          <table  border="0" cellspacing="0" cellpadding="0" class="tablesee2">
              <tr>
                  <td align="right" class="orseet">收货人:</td>
                  <td width="800" align="left">${(obj.addr.trueName)!}</td>
              </tr>
              <tr>
                  <td align="right" class="orseet">收货地址:</td>
                  <td width="800" align="left">${(obj.addr.area.parent.parent.areaname)!} ${(obj.addr.area.parent.areaname)!} ${(obj.addr.area.areaname)!} ${(obj.addr.area_info)!}</td>
              </tr>
              <tr>
                  <td align="right" class="orseet">邮政编码:</td>
                  <td width="800" align="left">${(obj.addr.zip)!}</td>
              </tr>
              <tr>
                  <td align="right" class="orseet">电话号码:</td>
                  <td width="800" align="left">${(obj.addr.telephone)!}</td>
              </tr>
              <tr>
                  <td align="right" class="orseet">手机号码:</td>
                  <td width="800" align="left">${(obj.addr.mobile)!}</td>
              </tr>
              <tr>
                  <td align="right" class="orseet">配送方式:</td>
                  <td width="800" align="left">${(obj.ship.shippingName)!}</td>
              </tr>
              <tr>
                  <td align="right" class="orseet">配送时间:</td>
                  <td width="800" align="left">${CommUtil.formatLongDate(obj.shipTime)}</td>
              </tr>
              <#if (obj.msg)?? && obj.msg !="" >
                  <td align="right" class="orseet">配送时间:</td>
                  <td width="800" align="left">${(obj.msg)!}</td>
              </#if>
              <tr>
                  <td align="right" class="orseet">发票类型:</td>
                  <td width="800" align="left"><#if ((obj.invoiceType)!-1)==0>个人 <#else> 单位</#if></td>
              </tr>
              <#if (obj.invoice)?? && obj.invoice !="" >
                  <td align="right" class="orseet">发票台头:</td>
                  <td width="800" align="left">${(obj.invoice)!}</td>
              </#if>
          </table>


        <h2><span>物流动态</span>
            <a href="javascript:void(0);" id="ship_info_a"><img src="${S_URL}/static/images/user_up.png" /></a>
        </h2>
          <#--<table  style="display:auto" id="ship_info_ul" border="0" cellspacing="0" cellpadding="0" class="tablesee2">-->
              <#--<tr>-->
                  <#--<td align="right" class="orseet">物流公司:</td>-->
                  <#--<td width="800" align="left">${(obj.ec.companyName)!}</td>-->
              <#--</tr>-->
              <#--<tr>-->
                  <#--<td align="right" class="orseet">物流单号:</td>-->
                  <#--<td width="800" align="left">${(obj.shipCode)!}</td>-->
              <#--</tr>-->
              <#--<#if ((transInfo.status)!0)==1>-->
                  <#--<#list (transInfo.data)! as info >-->
                      <#--<td align="right" class="orseet">&nbsp;</td>-->
                      <#--<td width="800" align="left" class="orseet2">${(info.time)!} ${(info.context)!}</td>-->
                  <#--</#list>-->
              <#--</#if>-->
              <#--<#if ((transInfo.status)!-1)==0 >-->
                  <#--<li><span class="orseet"></span>-->
                      <#--<span class="orseet2" style=" margin-left:90px; _margin-left:0px; font-size:14px; color:#F90">-->
                      <#--${(transInfo.message)!}</span>-->
                  <#--</li>-->
              <#--</#if>-->
          <#--</table>-->

        <ul style="display:none" id="ship_info_ul">
          <li><span class="orseet">物流公司:</span><span class="orseet2">${(obj.ec.companyName)!}</span></li>
          <li><span class="orseet">物流单号:</span><span class="orseet2">${(obj.shipcode)!}</span></li>
             <#if ((transInfo.status)!0)==1>
                 <#list (transInfo.data)! as info >
                    <li><span class="orseet"> &nbsp;</span>
                        <span class="orseet2">${(info.time)!} ${(info.context)!}</span></li>
                 </#list>
             </#if>
             <#if ((transInfo.status)!-1)==0 >
                 <li><span class="orseet"></span>
                     <span class="orseet2" style=" margin-left:90px; _margin-left:0px; font-size:14px; color:#F90">
                     ${(transInfo.message)!}</span>
                 </li>
             </#if>
        </ul>
      </div>
      <div class="ordersee5">
        <h2>操作历史</h2>
        <#list (obj.ofls)! as ofl>
            <div class="ophistory">
                <strong style="color:#F90">${(ofl.logUser.userName)!}</strong>于
                <strong><em>${CommUtil.formatLongDate(ofl.addtime)!}</em></strong>
                ${(ofl.logInfo)!}
                <#if (ofl.stateInfo)??>操作原因:<strong><em>${(ofl.stateInfo)!}</em></strong></#if></div>
        </#list>
      </div>
      <#if (obj.rls)?? && (obj.rls)?size gt 0>
      <div class="ordersee5">
        <h2>退款日志</h2>
        <#list (obj.rls)! as rl >
            <div class="ophistory">
                <strong style="color:#F90">${(rl.refundUser.userName)!}</strong>于
                <strong><em>${CommUtil.formatLongDate(rl.addtime)}</em></strong>使用
                <strong><em>${(rl.refundType)!}</em></strong>退款
                <strong><em>¥${(rl.refund)!}</em></strong>
            </div>
        </#list>
      </div>
      </#if>
      <#if (obj.grls)?? && (obj.grls)?size gt 0 >
      <div class="ordersee5">
          <h2>退货日志</h2>
          <#list (obj.grls)! as grl >
              <#list (grl.gr.items)! as item >
                  <div class="ophistory"><strong><em>${CommUtil.formatLongDate(grl.addtime)}</em>
                  </strong>订单：<strong><em>${(item.gr.of.orderId)!}</em></strong>${(item.goods.goodsName)!}
                      <#list (obj.gsps)! as gsp >
                          <#if (gsp.spec)??>
                             ${(gsp.spec.name)!}: ${(gsp.value)!}
                          </#if>
                      </#list>
                  数量:${(item.count)!}</div>
              </#list>
          </#list>
      </div>
      </#if>
    </div>
  </div>
</div>
</div>
</@override>
<@extends name="framework.ftl"/>
