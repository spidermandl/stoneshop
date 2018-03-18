<@override name="main">
<#assign P_NAV1="我的商城" />
<#assign P_NAV2="买家中心" />
<#assign P_NAV3="订单列表" />
<#assign P_SIDEBAR = "ORDER" />

<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<link  href="${S_URL}/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.zh.cn.js"></script>-->
<script>
jQuery(document).ready(function(){
  jQuery('#beginTime').datepicker({
	  dateFormat:"yy-mm-dd",
	  changeMonth: true,
	  changeYear: true
  });
  jQuery('#endTime').datepicker({
	  dateFormat:"yy-mm-dd",
	  changeMonth: true,
	  changeYear: true
  });
  //
  jQuery("#order_status").val("$!order_status");
  //QueryShip
  jQuery(".li_10>a[id^=li_ship_]").click(function(){
     jQuery("div[id^=li_ship_box]").hide();
					var order_id =jQuery(this).attr("order_id");
					var display =jQuery("#li_ship_box_"+order_id).css("display");
							if(display=="none"){
							jQuery("#li_ship_box_loading_"+order_id).show();
							}
							jQuery.post("${S_URL}/buyer/query_ship.htm",{
									"id":order_id
									},
									function(data){
											if(data){
											   jQuery(".li_ship_box_loading").hide();
											   jQuery("#li_ship_box_"+order_id).show().find("ul").html(data);
											  }
										},"text");
  });
  jQuery("div[id^=li_ship_box_]").mouseleave(function(){
	  jQuery(this).hide();
  });
//
jQuery("a[id^=delete_order_]").click(function(){
		var order_id=jQuery(this).attr("order_id");
		jQuery("#delete_order_id").val(order_id);
		showDialog("share_sns","系统提示","删除后不可恢复，是否继续？",1,"question","",order_delete);
	});
//
function order_delete(){
	 var order_id=jQuery("#delete_order_id").val();
	 window.location.href="${S_URL}/order_delete?id="+order_id+"&currentPage=${currentPage!}";
}
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

    <form action="${S_URL}/order" method="post" id="ListForm">
      <table  border="0" cellspacing="0" cellpadding="0" class="user_table">
        <tr>
            <#--$!httpInclude.include("/buyer/nav.htm?op=order")-->
            <td id="centerbg" valign="top">
            <div class="productmain">
              <div class="pdctitle blue2">订单列表
                <input type="hidden" name="delete_order_id" id="delete_order_id" />
              </div>
              <div class="ordersear">
                  <span>订单号：</span><span>
                      <input name="order_id" type="text" id="order_id" class="text" value="${order_id!}" />
                  </span><span>下单时间：</span><span>
                      <input name="beginTime" type="text" id="beginTime" class="text" value="${beginTime!}" readonly="readonly" /></span>
                  <span>——</span><span>
                      <input name="endTime" type="text" id="endTime" class="text" value="${endTime!}" readonly="readonly" />
                  </span>
                  <span>订单号：</span>
                  <span>订单状态：</span><span class="ordersel">
                    <select name="order_status" id="order_status">
                      <option value="">所有订单</option>
                      <option value="order_submit">待付款</option>
                      <option value="order_pay">待发货</option>
                      <option value="order_shipping">已发货</option>
                      <option value="order_receive">已收货</option>
                      <option value="order_finish">已完成</option>
                      <option value="order_cancel">已取消</option>
                    </select></span>
                  <span class="orderbtn">
                      <input name="input" type="submit" value="搜索" style="cursor:pointer;" />
                  </span>
              </div>
              <div class="myorder">
                  <#list objs! as obj>
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
                  <#if ((obj.orderStatus)!'45')==45 >
                      <#assign status="买家申请退货" />
                  </#if>
                  <#if ((obj.orderStatus)!'46')==46 >
                      <#assign status="退货中" />
                  </#if>
                  <#if ((obj.orderStatus)!'47')==47 >
                      <#assign status="退货完成，已结束" />
                  </#if>
                  <#if ((obj.orderStatus)!'48')==48 >
                      <#assign status="卖家拒绝退货" />
                  </#if>
                  <#if ((obj.orderStatus)!'49')==49 >
                      <#assign status="退货失败" />
                  </#if>
                  <#if ((obj.orderStatus)!'50')==50 >
                      <#assign status="已完成,已评价" />
                  </#if>
                  <#if ((obj.orderStatus)!'60')==60 >
                      <#assign status="已结束" />
                  </#if>
                  <#if ((obj.orderStatus)!'65')==65 >
                      <#assign status="已结束，不可评价" />
                  </#if>
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

                  <div class="Order">
                  <div class="Order_box">
                    <div class="line01">
                      <ul>
                        <li class="li_01">订单号：<span>${(obj.orderId)!}</span></li>
                        <li class="li_02">下单时间：<span>${CommUtil.formatLongDate(obj.addtime)!}</span></li>
                        <li class="li_03"><a href="${S_URL}/order_view?id=${(obj.id)!}" target="_blank">查看订单</a></li>
                        <li class="li_02">支付方式：<span>${payment!}</span></li>
                        <li class="li_02">订单状态：<span>${status!}</span></li>
                          <#assign order_type="PC订单" />
                          <#if ((obj.orderType)!'')=="weixin" >
                              <#assign order_type="微信订单" />
                          </#if>
                          <#if ((obj.orderType)!"")=="android" >
                              <#assign order_type="Android订单" />
                          </#if>
                          <#if ((obj.orderType)!"")=="ios" >
                              <#assign order_type="IOS订单" />
                          </#if>
                          <li class="li_02">订单类型：<span>${order_type!}</span></li>
                      </ul>
                    </div>
                    <div class="line_02">
                      <table width="100%" cellpadding="0" cellspacing="0" class="order_table">
                        <tr>
                          <td width="449px"  style="padding-bottom: 10px">
                              <#list (obj.gcs)! as gc>
                                <#if (gc.goods.goods_main_photo)??>
                                    <#assign img="${RES_URL}/${(gc.goods.goods_main_photo.path)!}/${(gc.goods.goods_main_photo.name)!}_small.${(gc.goods.goods_main_photo.ext)!}" />
                                <#else>
                                    <#assign img="${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                </#if>
                                <ul class="ul_01">
                                  <li class="li_05"><a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">
                                      <img src="${img!}" width="60" height="60" /></a>
                                  </li>
                                  <li class="li_06"><a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">${(gc.goods.goodsName)!}</a>
                                      <#if ((gc.goods.groupBuy)!0)==2 > <strong style="color:#F00">【团购】</strong> </#if>
                                      <#if ((gc.goods.activityStatus)!0)==2 > <strong style="color:#F00">【活动】</strong> </#if>
                                    <span>数量:${(gc.count)!}</span><span>${(gc.specInfo)!}</span>
                                  </li>
                                </ul>
                              </#list>
                          </td>
                          <td><ul class="ul_02">
                              <li style="color:#999;">物流跟踪</li>
                              <li class="li_07">
                              <#if (obj.shipcode)?? >
                                  <a title="${(obj.shipcode)!}"  href="${S_URL}/order_view?id=${(obj.id)!}" target="_blank" style="color: #06F">
                                      ${CommUtil.substring("${(obj.shipcode)!}",11)!}</a>
                              <#else>
                               暂无物流信息
                              </#if>
                                <div class="li_07_box" id="li_ship_box_${(obj.id)!}" style="display:none">
                                    <span>
                                        <img src="${S_URL}/static/images/order_10.png" />
                                    </span>
                                  <ul>
                                  </ul>
                                </div>
                                <div class="li_ship_box_loading" id="li_ship_box_loading_${(obj.id)!}" style="display:none">
                                    <span>
                                        <img src="${S_URL}/static/images/order_10.png" />
                                    </span>
                                    <b><img src="${S_URL}/static/images/loading.gif"/><br />正在查询物流信息...</b>
                                </div>
                              </li>
                              <#if (obj.shipcode)??>
                                  <li class="li_10"><a id="li_ship_${(obj.id)!}" order_id="${(obj.id)!}" href="javascript:void(0);">查看物流</a></li>
                              </#if>
                            </ul></td>
                          <td>
                              <ul class="ul_03">
                              <li><a href="${S_URL}/store?id=${(obj.storeId)}" target="_blank">${(obj.store.storeName)!}</a></li>
                              <li><span>${(obj.store.user.userName)!}</span>
                                  <a href="${S_URL}/buyer/message_send.htm?userName=${(obj.store.user.userName)!}" target="_blank">
                                      <img src="${S_URL}/static/images/order_04.gif" width="15" height="11" />
                                  </a>
                              </li>
                              <#if (obj.store.storeQq)?? >
                              <li> <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(obj.store.storeQq)!}&Site=${(obj.store.storeQq)!}&Menu=yes">
                                  <img src="http://wpa.qq.com/pa?p=2:${(obj.store.storeQq)!}:51" /></a>
                              </li>
                              </#if>
                            </ul></td>
                          <td>
                              <ul class="ul_02">
                              <li style="color:#999;">¥${(obj.totalprice)?string('0.00')}</li>
                              <li style="color:#59cfff;">(含运费:¥${CommUtil.null2Float(obj.shipPrice)?string('0.00')})</li>
                              </ul>
                          </td>
                          <td style="border:none">
                             <ul class="ul_04">
                              <#if ((obj.orderStatus)!-1)==0 || ((obj.orderStatus)!-1)==15 || ((obj.orderStatus)!-1)==50 || ((obj.orderStatus)!-1)==60 >
                              <#if ((obj.orderStatus)!-1) lt 20 >
                              <li><a href="javascript:void(0);">&nbsp;</a></li>
                              </#if>
                              <li>${status!}</li>
                              </#if>
                              <#if ((obj.orderStatus)!-1)==0 >
                              <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                     order_id="${(obj.id)!}" id="delete_order_${(obj.id)!}">删除订单</a></li>
                              </#if>
                              <#if ((obj.orderStatus)!-1)==10 >
                              <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                     dialog_uri="${S_URL}/order_cancel?id=${(obj.id)!}&currentPage=${currentPage!}"
                                     dialog_title="取消订单" dialog_width="400" dialog_height="100" dialog_id="order_cancel">取消订单</a>
                              </li>
                              </#if>
                              <#if ((obj.orderStatus)!-1)==45 >
                              <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                     dialog_uri="${S_URL}/order_return_apply?id=${(obj.id)!}&view='true'"
                                     dialog_title="申请详情" dialog_width="400" dialog_height="100" dialog_id="order_cancel">申请退货中</a>
                              </li>
                              </#if>

                              <#if ((obj.orderStatus)!-1)==40 >
                                  <#assign flag=0 />
                                  <#list (obj.gcs)! as gc>
                                      <#if ((gc.goods.goodsChoiceType)!0)==1>
                                          <#assign flag=1 />
                                      </#if>
                                  </#list>
                                  <#if flag != 1 >
                                      <li class="li_09">
                                          <a dialog_id="order_cancel" dialog_height="100" dialog_width="500" dialog_title="申请退货"
                                             dialog_uri="${S_URL}/order_return_apply?id=${(obj.id)!}&currentPage=1" href="javascript:void(0);">
                                              申请退货</a>
                                      </li>
                                  </#if>
                              </#if>
                              <#if ((obj.orderStatus)!-1)==46 >
                                  <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                         dialog_uri="${S_URL}/order_return_ship?id=${(obj.id)!}&currentPage=${currentPage!}"
                                         dialog_title="退货物流" dialog_width="400" dialog_height="100" dialog_id="order_cancel">
                                      退货物流</a></li>
                              </#if>
                              <#if ((obj.orderStatus)!-1)==47 >
                                  <li class="li_04">退货完成,已结束</li>
                              </#if>
                              <#if ((obj.orderStatus)!-1)==48 >
                                  <li class="li_04">卖家拒绝退货申请</li>
                              </#if>

                              <#if ((obj.orderStatus)!-1)==49 >
                              <li class="li_04">退货失败</li>
                              </#if>

 							  <#if ((obj.orderStatus)!-1)==65 >
                              <li class="li_04">已结束,不可评价</li>
                              </#if>

                              <#if ((obj.orderStatus)!-1)==10 >
                              <li class="li_09"><a href="${S_URL}/order_pay_view?id=${(obj.id)!}" target="_blank">付款</a></li>
                              </#if>

                              <#if ((obj.orderStatus)!-1) gte 20 >
                              <li class="li_09"><a href="${S_URL}/buyer/complaint_handle.htm?order_id=${(obj.id)!}">投诉</a></li>
                              </#if>

                              <#if ((obj.orderStatus)!-1) == 30 >
                              <li class="li_09"><a href="javascript:void(0);" dialog_uri="${S_URL}/order_cofirm?id=${(obj.id)!}&currentPage=${currentPage!}"
                                                   dialog_title="确认收货" dialog_width="400" dialog_height="100" dialog_id="order_cancel">确认收货</a> </li>
                              </#if>
                              <#if ((obj.orderStatus)!-1) gte 30 >
                                  <#assign flag=0 />
                                  <#list (obj.gcs)! as gc>
                                      <#if ((gc.goods.goodsChoiceType)!0)==1 >
                                          <#assign flag=1 />
                                      </#if>
                                  </#list>
                                  <#if flag==1 >
                                      <li class="li_09">
                                          <a href="javascript:void(0);" dialog_uri="${S_URL}/order_seller_intro?id=${(obj.id)!}"
                                             dialog_title="虚拟商品" dialog_width="400" dialog_height="100" dialog_id="order_cancel">虚拟商品</a></li>
                                  </#if>
                              </#if>

                              <#if obj.orderStatus gte 40 && obj.orderStatus != 47 && obj.orderStatus !=50 &&
                                        obj.orderStatus != 60 && obj.orderStatus !=65 >
                              <li class="li_09"><a href="${S_URL}/order_evaluate?id=${(obj.id)!}">我要评价</a></li>
                              </#if>
                            </ul></td>
                        </tr>
                      </table>
                    </div>
                  </div>
                </div>
                </#list>
              </div>
              <#--<div class="alldel">-->
                <div  class="userfenye">
                  <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />${gotoPageFormHTML!}</div>
              <#--</div>-->
            </div></td>
        </tr>
      </table>
    </form>

</div>
</div>
</@override>
<@extends name="framework.ftl"/>
