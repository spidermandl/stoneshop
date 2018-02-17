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
    <link href="${S_URL}/static/styles/jquery/jquery-ui.custom.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/overlay.css" type="text/css" rel="stylesheet" />
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>

<script>
jQuery(document).ready(function(){
   //
  <#if addrs!?size==0 >
   jQuery("body").append("<div id='cart_address'><div class='white_content'><div class='white_box'><h1>新增收货地址</h1>" +
           "<div class='content_load'></div></div></div><div class='black_overlay'></div></div>");
   var top=(document.documentElement.clientHeight-100)/2+document.documentElement.scrollTop+document.body.scrollTop;
   var h=document.body.scrollHeight;
   jQuery('.black_overlay').css("height",h);
   jQuery(".white_content").css("position","absolute").css("top",top);
   jQuery.ajax({type:'POST',url:"${S_URL}/cart_address",async:false,data:{"store_id":"${store_id!}"},success:function(html){
	    	jQuery(".content_load").remove();
			jQuery("#cart_address .white_content").css("width","500");
		    jQuery("#cart_address .white_box h1").after(html);
		    jQuery("#cart_address").show();
	   }});
  </#if>
  jQuery(":radio[name=invoiceType]").click(function(){
     var val=jQuery(this).val();
	 if(val=="1"){
	   jQuery("#invoice_info").show();
	 }else{
	   jQuery("#invoice_info").hide();
	 }
  });
  //
  jQuery(":radio[name=addr_id]").click(function(){
    var addr=jQuery(this).parent().parent().find("#address").html();
	var person=jQuery(this).parent().parent().find("#person").html()+" "+jQuery(this).parent().parent().find("#mobile").html();
	jQuery("#order_address_info").html("寄送至:"+addr);
	jQuery("#order_person_info").html("收货人:"+person);
  });
  //
  jQuery("#coupon_id").change(function(){
      var coupon_amount=parseFloat(jQuery(this).find("option:selected").attr("coupon_amount"));
	  if(isNaN(coupon_amount)){
		 coupon_amount=0;
	  }
	  var goods_amount=parseFloat(jQuery("#goods_amount").val());
	  jQuery("#order_coupon_div").show();
	  var coupon_info="-¥"+coupon_amount;
	  jQuery("#order_coupon").html(coupon_info);
	  var ship_price=parseFloat(jQuery("#ship_price").val());
	  if(isNaN(ship_price)){
		  ship_price=0;
	  }
	  var order_fee=goods_amount-coupon_amount+ship_price;
	  jQuery("#order_store_amount").html("¥"+order_fee);
	  jQuery("#order_pay_fee").html("¥"+order_fee);
	  if(coupon_amount==0){
	    jQuery("#order_coupon_div").hide();
	  }
  });
  //
  jQuery(":radio[name^=addr_id]").click(function(){
       var addr_id=jQuery(this).val();
	   jQuery.ajax({type:'POST',url:'${S_URL}/order_address',data:{'addr_id':addr_id,"store_id":"${(sc.storeId)!}"},dataType:'json',
				  beforeSend:function(){
					    jQuery("#order_save").attr("disabled",true);
					  },
				  success:function(data){
	                 jQuery("#ship_price").empty();
					 jQuery(data).each(function(index,item){
					     jQuery("#ship_price").append("<option value='"+item.value+"'>"+item.key+"</option>");
					 });
				     var ship_price=parseFloat(jQuery("#ship_price").val());
					 if(isNaN(ship_price)){
					    ship_price=0;
					 }
	                 var coupon_amount=parseFloat(jQuery("#coupon_id").find("option:selected").attr("coupon_amount"));
					 if(isNaN(coupon_amount)){
					   coupon_amount=0;
					 }
	                 var goods_amount=parseFloat(jQuery("#goods_amount").val());
	                 var order_fee=goods_amount-coupon_amount+ship_price;
	                 jQuery("#order_store_amount").html("¥"+order_fee);
	                 jQuery("#order_pay_fee").html("¥"+order_fee);
					 jQuery("#order_save").attr("disabled",false);
				  }
	   });
  });
  //
  jQuery("#ship_price").change(function(){
     var ship_price=parseFloat(jQuery(this).val());
	 if(isNaN(ship_price)){
		 ship_price=0;
	 }
	 var coupon_amount=parseFloat(jQuery("#coupon_id").find("option:selected").attr("coupon_amount"));
	 if(isNaN(coupon_amount)){
         coupon_amount=0;
     }
	 var goods_amount=parseFloat(jQuery("#goods_amount").val());
	 var order_fee=goods_amount-coupon_amount+ship_price;
	 jQuery("#order_store_amount").html("¥"+order_fee);
	 jQuery("#order_pay_fee").html("¥"+order_fee);
	 var text=jQuery(this).find("option:selected").text();
	 var transport=text.substring(0,text.indexOf("["));
	 jQuery("#transport").val(transport);
  });
  //
  jQuery(".baby_gp>a").mouseover(function(){
	jQuery(this).parent().find(".arrow").show();
    jQuery(this).parent().find(".baby_group").show();
  });
  jQuery(".baby_gp").mouseleave(function(){
    jQuery(this).parent().find(".arrow").hide();
    jQuery(this).parent().find(".baby_group").hide();
  });
  //
  jQuery("#coupon_id").val("0");
  jQuery("#ship_price").find("option:first").attr("selected",true);
  jQuery(":radio[name^=addr_id]:first").attr("checked",true);
});
function save_order(){
  jQuery("#cart_form").submit();
}
</script>
</head>
<body>
${httpInclude.include("/top")}
<div class="main"> ${httpInclude.include("/head")}
  <div class="Steps_box">
    <div class="Steps">
      <ul>
        <li class="done prev">1.查看购物车</li>
        <li class="this">2.确认订单信息</li>
        <li>3.付款到卖家</li>
        <li>4.确认收货</li>
        <li class="last">5.评价</li>
      </ul>
    </div>
    <form action="${S_URL}/goods_cart3.htm" method="post" enctype="${S_URL}/goods_cart3" id="cart_form">
      <div class="h1">
          <span class="h1_l">店铺名称：
              <a href="${S_URL}/store?id=${(sc.storeId)!}" target="_blank">${(sc.store.storeName)!}</a>
              <input name="store_id" type="hidden" id="store_id" value="${(sc.storeId)!}" />
          </span>
      </div>
      <div class="table">
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td width="57%" align="center" class="title">店铺商品</td>
            <td width="10%" align="center" class="title">单价</td>
            <td width="11%" align="center" class="title">数量</td>
            <td width="11%" align="center" class="title">小计</td>
          </tr>
          <#list (sc.gcs)! as gc>
          <tr>
            <td class="baby">
                <a href="${S_URL}/goods?id=${(sc.storeId)!}" target="_blank">
                    <img src="${RES_URL}/${imageWebServer!}/${(gc.goods.goods_main_photo.path)!}/${(gc.goods.goods_main_photo.name)!}_small.${(gc.goods.goods_main_photo.ext)!}" width="65" height="65" /></a>
              <p><a href="${S_URL}/goods?id=${(sc.storeId)!}" target="_blank">${CommUtil.substring("${(gc.goods.goodsName)!}",42)}</a>
              <#if ((gc.goods.groupBuy)!0)==2> <span style="color:#F00">(团购)</span> </#if>
              <#if ((gc.goods.bargainStatus)!0)==2><span style="color:#F00">(特价)</span> </#if>
              <#if ((gc.goods.deliveryStatus)!0)==2><span style="color:#F00">[买就送]</span> </#if>

              <#if ((gc.cartType)!'')=="combin" >
              <div class="baby_gp">
                <a href="javascript:void(0);" style="color:#F00;">[组合商品]</a>
                <div class="baby_group" style="display:none;">
                  <div class="baby_group_box">
                    <ul class="group_ul">
                      <#list goodsViewTools.query_combin_goods("${(gc.goodsId)!}") as info >
                      <li>
                          <a href="${S_URL}/goods?id=${(info.id)!}" target="_blank">
                          <#if (info.goods_main_photo)!??>
                              <#assign img="${RES_URL}/${imageWebServer!}/${(info.goods_main_photo.path)!}/${(info.goods_main_photo.name)!}_small.${(info.goods_main_photo.ext)!}" />
                          <#else>
                              <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                          </#if>
                              <img src="${img!}" />
                          </a>
                          <span>
                              <a href="${S_URL}/goods?id=${(info.id)!}" target="_blank">${CommUtil.substring("${(info.goodsName)!}",18)!}</a>
                          </span>
                      </li>
                      </#list>
                    </ul>
                  </div>
                </div>
                <span class="arrow" style="display:none;"></span>
              </div>
              </#if>
              <br />
                <#list (obj.gsps)! as gsp>
                <#if (gsp.spec)!??> <span>${(gsp.spec.name)!}: ${(gsp.value)!}</span><br />
                </#if>
                </#list>
                </p>
            </td>
            <td align="center">${(gc.price)!}</td>
            <td align="center">${(gc.count)!}</td>
            <#assign total_price=gc.count * gc.price />
            <td align="center"><strong class="orange">¥${total_price!}</strong></td>
          </tr>
          </#list>
        </table>
      </div>
      <div class="sendadress">
        <h1>
          <div class="sendhright">
              <span class="sendperbtn">
                  <a href="javascript:void(0);" dialog_uri="${S_URL}/cart_address?store_id=${store_id!}"
                     dialog_title="新增地址" dialog_width="480" dialog_height="100" dialog_id="cart_address">新增地址</a>
              </span>
              <span class="sendperbtn"><a href="${S_URL}/buyer/address" target="_blank">管理收货地址</a></span>
          </div>
          <span class="sendperadr">收货人地址</span>
        </h1>
        <div class="writeok">
          <ul>
            <#assign addr_id="" />
            <#list addrs! as addr>
              <#if addr_index==1>
                  <#assign default_address_info="${(addr.area.parent.parent.areaName)!}${(addr.area.parent.areaName)!}${(addr.area.areaName)!}${(addr.area_info)!}" />
                  <#assign addr_id="${(addr.area.id)!}" />
                  <#assign default_person_info="${(addr.trueName)!} ${(addr.mobile)!}" />
              </#if>
            <li>
                <strong>
                    <img src="${S_URL}/styles/system/front/default/images/Steps_02.gif" width="14" height="23" />
                </strong>
                <label>
                    <strong>
                        <input type="radio" name="addr_id" value="${(addr.id)!}" <#if addr_index==1> checked="checked" </#if>/>
                    </strong>
                    <span id="address">${(addr.area.parent.parent.areaName)!}${(addr.area.parent.areaName)!}${(addr.area.areaName)!}${(addr.area_info)!}</span>
                    <span id="person">${(addr.trueName)!}</span>
                    <span id="mobile">${(addr.mobile)!}</span>
                </label>
            </li>
            </#list>
          </ul>
        </div>
        <h1><span class="sendperadr margin10">发票信息</span></h1>
        <div class="sendmethod">
          <ul>
            <li>
              <label>
                <input name="invoiceType" type="radio" value="0" checked="checked" />
                个人 </label>
              <label>
                <input name="invoiceType" type="radio" value="1" />
                单位 </label>
            </li>
            <li id="invoice_info" style="display:none;"><span style="margin-bottom:5px;">发票抬头：</span>
              <input name="invoice" type="text" id="invoice" size="40" style="height:20px;" />
            </li>
          </ul>
        </div>
        <#if couponinfos?size gt 0>
        <h1><span class="sendperadr margin10">使用优惠券</span></h1>
        <div class="sendmethod">
          <ul>
            <li>
                <span style="margin-bottom:5px;">可用优惠券：</span>
                <select name="coupon_id" id="coupon_id">
                <option value="" selected="selected" coupon_amount="0">请选择优惠券</option>
                    <#list couponinfos! as info >
                        <option value="${(info.id)!}" coupon_amount="${(info.coupon.couponAmount)!}">${(info.couponSn)!}[${(info.coupon.couponName)!}]</option>
                    </#list>
                </select>
            </li>
          </ul>
        </div>
        </#if>
        <#if goods_delivery!??>
            <h1><span class="sendperadr margin10">配送方式</span></h1>
            <div class="sendmethod">
                <ul>
                    <li>
                        <span style="margin-bottom:5px;">可用配送方式：</span>
                        <select name="ship_price" style="width:110px;" id="ship_price">
                            <#assign ship_price=0 />
                            <#list transportTools.query_cart_trans(sc,"${addr_id!}") as sm >
                                <#if sm_index==1 >
                                    <#assign ship_price=sm.value />
                                </#if>
                            <option value="${(sm.value)!}">${(sm.key)!}</option>
                            </#list>
                        </select>
                        <input name="transport" type="hidden" id="transport" value="平邮" />
                    </li>
                </ul>
            </div>
        </#if>
        <h1><span class="sendperadr margin10">买家附言</span></h1>
        <div class="sendmeg">
          <textarea name="msg" cols="" rows="" id="msg"></textarea>
        </div>
        <div class="paysend" id="order_coupon_div" style="display:none;">优惠券抵消：
            <strong class="red" style=" font-size:24px;" id="order_coupon"></strong>
        </div>
        <#assign order_total_price = CommUtil.null2Float(ship_price)+sc.totalPrice />
        <div class="paysend">店铺合计：
            <strong class="red" style=" font-size:24px;" id="order_store_amount">¥${order_total_price!}</strong>
        </div>
        <div class="paysend">
          <div class="paysd">
            <div class="paysd_box">
            <span>实付款：<strong></strong><b id="order_pay_fee">¥${order_total_price!}</b></span>
              <ul>
                <li><span id="order_address_info">寄送至:${default_address_info!}</span></li>
                <li><span id="order_person_info">收货人:${default_person_info!}</span></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="paybtn">
            <input  name="order_save" type="button"  value="提交订单并支付" onclick="save_order();"  style="cursor:pointer;" id="order_save"/>
            <input name="cart_session" type="hidden" id="cart_session" value="${cart_session!}" />
            <input name="goods_amount" type="hidden" id="goods_amount" value="${(sc.totalPrice)!}" />
        </div>
      </div>
    </form>
  </div>
  ${httpInclude.include("/footer")} </div>
</body>
</html>
