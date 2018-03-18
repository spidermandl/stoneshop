<#assign P_CURRENT_TOP='order' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="订单" />
<#assign P_NAV3="我的订单" />
<#assign P_CURRENT_OP="OrderSetting" />

<@override name="main">
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
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
  jQuery("#order_status").val("${order_status!}");
  //query_user_information
  jQuery("ul[id^=ul_03_]").find("li[mark='name']").hover(
			 function(){
				var id= jQuery(this).attr("obj_id");
				jQuery(".xx").hide();
				jQuery(this).find(".xx").fadeIn();
				jQuery.post("${S_URL}/order_query_userinfor",
							{
							"id":id
							},function(data){
								jQuery("#table_"+id).html(data);
								},"text");
			},
			function(){
			jQuery(".xx").hide();
			});
});
</script>
<div class="ncsc-layout wrapper">
    <#include "layout_order.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
          <div class="productmain">
            <div class="ordernav">
              <ul class="orderul">
                <li class="this" id="all"><a href="${S_URL}/order?order_status=all">所有订单</a></li>
                <li><a href="${S_URL}/order?order_status=order_submit">已经提交</a></li>
                <li><a href="${S_URL}/order?order_status=order_pay">已经付款</a></li>
                <li><a href="${S_URL}/order?order_status=order_shipping">已经发货</a></li>
                <li><a href="${S_URL}/order?order_status=order_receive">已经收货</a></li>
                <li><a href="${S_URL}/order?order_status=order_evaluate">等待评价</a></li>
                <li><a href="${S_URL}/order?order_status=order_finish">已经完成</a></li>
                <li><a href="${S_URL}/order?order_status=order_cancel">已经取消</a></li>
              </ul>
            </div>
            <script>
			<#if order_status??>
			  jQuery(".orderul li").removeClass("this");
		     jQuery(".orderul li a[href$=${order_status!}]").parent().addClass("this");
            </#if>
		  </script>
            <form action="${S_URL}/order" method="post" id="ListForm">
              <div class="ordercon">
                <div class="ordersear"> <span>订单编号：</span> <span class="ordersp1 size3">
                  <input name="order_id" type="text" id="order_id" value="${order_id!}" />
                  </span> <span class="px10">下单时间</span> <span class="ordersp1 size4">
                  <input name="beginTime" type="text" id="beginTime" value="${beginTime!}" readonly="readonly" />
                  </span><span>——</span><span class="ordersp1 size4">
                  <input name="endTime" type="text" id="endTime" value="${endTime!}" readonly="readonly" />
                  </span> <span class="px10">买家</span><span class="ordersp1 size5">
                  <input name="buyer_userName" type="text" id="buyer_userName" value="${buyer_userName!}" />
                  </span><span class="orderbtn">
                  <input name="" type="submit" value="搜索" style="cursor:pointer;"/>
                  </span> </div>
                <div class="myorder">
                <#list objs! as obj >
                    <#assign group=false />
                    <#list (obj.gcs)! as gc >
                      <#if  (gc.goods.group)?? && gc.goods.groupBuy==2 >
                          <#assign group=true />
                      </#if>
                    </#list>
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
                          <li class="li_02">下单时间：<span>${CommUtil.formatLongDate(obj.addTime)!}</span></li>
                          <li class="li_03"> <a href="${S_URL}/order_view?id=${(obj.id)!}" target="_blank">查看订单</a></li>
                          <#if payment?? >
                          <li class="li_02">支付方式：<span>${payment!}</span></li>
                          </#if>
                          <li class="li_02">订单状态：<span>${status!}</span></li>
                          <#assign order_type="PC订单" />
                          <#if ((obj.orderType)!"")=="weixin" >
                            <#assign order_type="微信订单" />
                          </#if>
                          <#if ((obj.orderType)!"")=="android" >
                              <#assign order_type="Android订单" />
                          </#if>
                          <#if ((obj.orderType)!"")=="android" >
                              <#assign order_type="IOS订单" />
                          </#if>
                          <li class="li_02">订单类型：<span>${order_type!}</span></li>
                          <li class="li_021"><a href="${S_URL}/order_print?id=${(obj.id)!}" target="_blank" title="打印订单">
                              <img src="${S_URL}/static/images/print_01.png" width="19" height="20" />
                          </a></li>
                        </ul>
                      </div>
                      <div class="line_02">
                        <table width="100%" cellpadding="0" cellspacing="0"  class="order_table">
                          <tr>
                            <td width="449px">
                                <#list (obj.gcs)! as gc>
                                    <#if (gc.goods.goods_main_photo)??>
                                        <#assign img="${S_URL}/${(gc.goods.goods_main_photo.path)!}/${(gc.goods.goods_main_photo.name)!}_small.${(gc.goods.goods_main_photo.ext)!}" />
                                    <#else>
                                        <#assign img="${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                    </#if>
                                    <ul class="ul_01">
                                        <li class="li_05"><a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">
                                            <img src="${img!}" width="60" height="60" />
                                        </a></li>
                                        <li class="li_06"><a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">
                                            ${(gc.goods.goodsName)!}</a>
                                            <#if group==true ><strong style="color:#F00">(团)</strong></#if>
                                            <#if ((gc.goods.activityStatus)!0)==2> <strong style="color:#F00">【活动】</strong> </#if>
                                            <span>数量:${(gc.count)!}</span>
                                            <span>${(gc.specInfo)!}</span>
                                        </li>
                                    </ul>
                                </#list>
                            </td>
                            <td><ul class="ul_02">
                                <#if ((obj.orderStatus)!0)==50 >
                                <li> <a href="javascript:void(0);">&nbsp;</a></li>
                                </#if>
                                <#if ((obj.orderStatus)!0) gte 20 >
                                <li class="li_10">
                                <a href="${S_URL}/complaint_handle?order_id=${(obj.id)!}">投诉</a></li>
                                </#if>

                                <#assign remain=(obj.totalPrice-CommUtil.null2Double(obj.refund))!0 />
                                <#if ((obj.orderStatus)!0) gte 30 && ((obj.orderStatus)!100) lt 50 && remain gt 0 >
                                    <li class="li_10">
                                        <a href="javascript:void(0);" dialog_uri="${S_URL}/order_refund?id=${(obj.id)!}&currentPage=${currentPage!}"
                                           dialog_title="退款" dialog_width="500" dialog_height="100" dialog_id="order_cancel">退款</a></li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==40 || ((obj.orderStatus)!0)==46 || ((obj.orderStatus)!0)==47 || ((obj.orderStatus)!0)==48 >
                                    <li class="li_10">
                                        <a href="javascript:void(0);" dialog_uri="${S_URL}/order_return?id=${(obj.id)!}&currentPage=${currentPage!}"
                                           dialog_title="退货" dialog_width="500" dialog_height="100" dialog_id="order_cancel">退货</a></li>
                                </#if>
                              </ul></td>
                            <td><ul class="ul_03" id="ul_03_${(obj.id)!}">
                                <#if ((obj.user.QQ)!"")==""|| (((obj.user.QQ)!true)==false) >
                                    <li> <a href="javascript:void(0);">&nbsp; </a></li>
                                </#if>
                                <li class="li_pot" mark="name" obj_id="${(obj.id)!}">
                                    <span><a href="javascript:void(0);">${(obj.user.userName)!}</a></span>
                                    <a href="${S_URL}/buyer/message_send?userName=${(obj.user.userName)!}" target="_blank">
                                        <img src="${S_URL}/static/images/order_04.gif"
                                             width="15" height="11" /></a>
                                    <div class="xx" style="display:none">
                                        <span>联系信息</span><i><img src="${S_URL}/static/images/user_left.png" /></i>
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="table_${(obj.id)!}"  >
                                        </table>
                                </div>
                                </li>
                                <#if (obj.user.QQ)?? &&$ ((obj.user.QQ)!"") != "">
                                <li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(obj.user.QQ)!}&Site=${(obj.user.QQ)!}&Menu=yes">
                                    <img src="http://wpa.qq.com/pa?p=2:${(obj.user.QQ)!}:51" /></a>
                                </li>
                                </#if>
                              </ul></td>
                            <td><ul class="ul_02">
                                <li style="color:#999;">¥${(obj.totalprice)!?string("0.00")}</li>
                                <li style="color:#59cfff;">(含运费:¥${CommUtil.null2Float(obj.shipPrice)!?string("0.00")})</li>
                                <#if ((obj.orderStatus)!0)==10 >
                                    <li class="li_09">
                                        <a href="javascript:void(0);" dialog_uri="${S_URL}/order_fee?id=${(obj.id)!}"
                                           dialog_title="调整费用" dialog_width="400" dialog_height="100" dialog_id="order_fee">调整费用</a></li>
                                </#if>
                              </ul></td>
                            <td style="border:none">
                                <ul class="ul_04">
                                <#if ((obj.orderStatus)!0)==10 >
                                <li>${status!}</li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==0 >
                                <li><a href="javascript:void(0);">&nbsp;</a></li>
                                <li style="color:#ff4f19; text-decoration:underline;">${status!}</li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==15 >
                                <li><a href="javascript:void(0);">&nbsp;</a></li>
                                <li class="li_11">
                                    <a href="javascript:void(0);" dialog_uri="${S_URL}/seller_order_outline?id=${(obj.id)!}"
                                       dialog_title="确认收款" dialog_width="400" dialog_height="100" dialog_id="order_outline_fee">确认收款</a></li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==10 || ((obj.orderStatus)!0)==15 >
                                <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                       dialog_uri="${S_URL}/order_cancel?id=${(obj.id)!}" dialog_title="取消订单"
                                       dialog_width="400" dialog_height="100" dialog_id="order_cancel">取消订单</a></li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==45 >
                                <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                       dialog_uri="${S_URL}/seller_order_return_apply_view?id=${(obj.id)!}"
                                       dialog_title="买家退货申请" dialog_width="400" dialog_height="100" dialog_id="order_cancel">买家退货申请</a></li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==46 >
                                    <li><a style="color:#ff4f19; text-decoration:underline;" href="${S_URL}/seller_order_return_ship_view?id=${(obj.id)!}"
                                           title="查看退货详情" target="_blank">退货物流</a></li>
                                    <li><a style="color:#ff4f19; text-decoration:underline;" href="javascript:void(0);"
                                           onclick="if(confirm('请确认买家已经发货，同时需要执行退货操作更新商品库存信息，是否继续？'))window.location.href='${S_URL}/seller_order_return_confirm?id=${(obj.id)!}'">确认退货</a></li>
                                </#if>
                                <#if ((obj.orderStatus)!0)==48 >
                                    <li class="li_04" style="color:#ff4f19;">拒绝退货</li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==49 >
                                    <li class="li_04" style="color:#ff4f19;">退货失败</li>
                                </#if>

                                <#if ((obj.orderStatus)!0)==65 >
                              <li class="li_04">已结束,不可评价</li>
                                </#if>
                                <#if ((obj.orderStatus)!0)==20 || ((obj.orderStatus)!0)==16 >
                                <li><a href="javascript:void(0);">&nbsp;</a></li>
                                <li class="li_11">
                                    <a href="javascript:void(0);" dialog_uri="${S_URL}/order_shipping?id=${(obj.id)!}&currentPage=${currentPage!}"
                                       dialog_title="确认发货" dialog_width="400" dialog_height="100" dialog_id="order_cancel">确认发货</a></li>
                                </#if>

                                <#if ((obj.orderStatus)!0) gte 30 >
                                <li class="li_11">
                                <a href="${S_URL}/ship_view.htm?id=${(obj.id)!}" target="_blank">查看物流</a>
                                </li>
                                </#if>
                                <#if ((obj.orderStatus)!0)==30 >
                                <li class="li_11">
                                    <a href="javascript:void(0);" dialog_uri="${S_URL}/order_shipping_code?id=${(obj.id)!}&currentPage=${currentPage!}"
                                       dialog_title="修改物流" dialog_width="400" dialog_height="100" dialog_id="order_shipping">修改物流</a></li>
                                </#if>
                                <#if ((obj.orderStatus)!0)==50 || ((obj.orderStatus)!0)==49 >
                              	 <li class="li_11">
                              	 <a href="${S_URL}/order_evaluate?id=${(obj.id)!}" target="_blank" >评价买家</a>
                              	</li>
                                </#if>
                              </ul></td>
                          </tr>
                        </table>
                      </div>
                    </div>
                  </div>

                </#list>
                </div>
                <div class="alldel">
                  <div  class="userfenye">
                    <input name="order_status" type="hidden" id="order_status" value="${order_status!}" />
                    <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />
                    ${gotoPageFormHTML!}</div>
                </div>
              </div>
            </form>
          </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
