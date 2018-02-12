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
    <link href="${S_URL}/static/styles/shop/${(store.template)!}/css/default.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/overlay.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/jquery.jqzoom.css" type="text/css" rel="stylesheet" />
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.lazyload.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jcarousellite_1.0.1.min.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.jqzoom-core.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
    <script src="${S_URL}/static/scripts/select_area.js"></script>

<script>
function img_switch(obj){
  jQuery(".photoimgul li").removeClass("this");
  jQuery(obj.parentNode.parentNode).addClass("this");
  var src=jQuery(obj).attr("big_img");
  jQuery("#main_img").attr("src",src);
}
<#assign group=0 />
<#assign current_price= obj.goodsCurrentPrice />
<#if (obj.groupBuy)!0==2 >
  <#assign group=1 />
</#if>
<#if group==1 >
  <#list obj.group_goods_list as info >
    <#if (info.groupId)!0 == (obj.groupId)!-1>
	  var store_price=${(info.ggPrice)!};
	  <#assign current_price= info.ggPrice />
      <#if (info.ggMaxCount)!-1 gt 0 >
        var goods_inventory=${(info.ggMaxCount)!};
      <#else>
        var goods_inventory=${(info.ggCount)!};
      </#if>
    </#if>
  </#list>
<#else>
  var store_price=${(obj.goodsCurrentPrice)!};
  var goods_inventory=${(obj.goodsInventory)!};
</#if>

var goods_price=${(obj.goodsPrice)!}
jQuery(document).ready(function(){
   	jQuery('.jqzoom').jqueryzoom({
            zoomType: 'standard',
			zoomWidth:480,
			zoomHeight:300,
            lens:true,
            preloadImages: false,
            alwaysOn:false,
			title:true,
			preloadText:'努力加载'
        });
    //初始化数据
      var data='${(obj.goodsProperty)!}';
      if(data!=""){
        var properties=eval("("+data+")");
        jQuery.each(properties,function(index,item){
            jQuery(".goodsintro").append("<span>"+item.name+":"+item.val+"</span>");
        });
      }
        //
    jQuery(".selled li").click(function(){
       jQuery(".selled li").each(function(){
           var id=jQuery(this).attr("id")+"_tab";
           jQuery(this).removeClass("this");
        });
        jQuery(this).addClass("this");
        var uri=jQuery(this).attr("uri");
        jQuery(".selleddetails").empty();
        jQuery(".img_center_load").show();
        jQuery.post(uri,"",function(data){
            jQuery(".img_center_load").hide();
            jQuery(".selleddetails").append(data);
         },"text");

    }).mouseover(function(){
      jQuery(this).css("cursor","pointer");
    });
    //
<#if user!?? >
jQuery(".collection_goods a[class=save_good]").click(function(){
    <#if ((user.id)!0) == ((store.memberId)!-1)>
	  alert("不能收藏自己的商品");
	<#else>
	  jQuery.post("${S_URL}/add_goods_favorite",{"id":"${(obj.id)!}"},function(data){
		 if(data==0){
		    alert("商品收藏成功！");
		 }
		 if(data==1){
		    alert("已经收藏该商品！");
		 }
	  },"text");
	</#if>
});
</#if>
//
<#if user!?? >
jQuery("#report_goods").click(function(){
    <#if ((user.id)!0) == ((store.memberId)!-1)>
	  alert("不能举报自己的商品");
    <#else>
	  window.location.href="${S_URL}/buyer/report_add?goods_id=${(obj.id)!}";
    </#if>
})
</#if>
//
jQuery("#goods_count").keyup(function(){
   var goods_count=jQuery(this).val();
   if(goods_count>goods_inventory){
	  <#if (obj.groupBuy)!0==2 >
	    <#if (obj.group_goods.ggMaxCount)!-1 gt 0>
		  alert("超出团购运行最大数量，请重新输入购买数量");
		<#else>
		  alert("超出团购库存数量，请重新输入购买数量");
        </#if>
	  <#else>
	  alert("超出店铺库存，请重新输入购买数量");
      </#if>
	  jQuery(this).val(goods_inventory);
	}
});
  //
  jQuery(".twocoad").css("cursor","pointer").mouseover(function(){
     jQuery(".shop_botmhbox").fadeIn('fast');
  }).mouseleave(function(){
     jQuery(".shop_botmhbox").fadeOut('fast');
  });
  jQuery(".shop_report").css("cursor","pointer").mouseover(function(){
     jQuery(".shop_reul").fadeIn("normal");
	 jQuery(".shop_rep_top").hide();
	 jQuery(".shop_rep_botm").show();
  }).mouseleave(function(){
     jQuery(".shop_reul").fadeOut("normal");
	 jQuery(".shop_rep_top").show();
	 jQuery(".shop_rep_botm").hide();
  });;
//
var switch_loaded = true;
var goods_tab_loaded=true;
var switch_top = jQuery("#switch_bar").offset().top;
var goods_tab_top = jQuery("#goods_tab").offset().top;
var switch_left = jQuery("#switch_bar").offset().left;

jQuery(window).scroll(function(){
  var scrolla=jQuery(window).scrollTop();
  var switch_cha=parseInt(switch_top)-parseInt(scrolla);
  if(switch_loaded && switch_cha<=30){
   jQuery("#switch_bar").removeClass("switch_bar").css({"position":"fixed","top":"30px","z-index":"100"});
   switch_loaded=false;
  }
  if(!switch_loaded && switch_cha>30){
   switch_loaded=true;
   jQuery("#switch_bar").removeClass().addClass("switch_bar").css({"position":"absolute","top":"30px","z-index":"100"});
  }
  var goods_tab_cha=parseInt(goods_tab_top)-parseInt(scrolla);
  if(goods_tab_loaded && goods_tab_cha<=0){
   jQuery("#goods_tab").css({"position":"fixed","top":"0px","background":"#fff","border-bottom":"#ccc solid 1px","overflow":"hidden"});
   jQuery(".selled_kf").show();
   goods_tab_loaded=false;
  }
  if(!goods_tab_loaded && goods_tab_cha>0){
   goods_tab_loaded=true;
   jQuery(".selled_kf").hide();
   jQuery("#goods_tab").css({"position":"relative","border-bottom":"none","overflow":"visible"});
  }
});
//
jQuery(".sbar_close").attr("cursor","pointer").click(function(){
  jQuery(".shopindex_left").fadeOut("normal");
  jQuery(".sbar_open").show();
  jQuery(".sbar_close").hide();
  jQuery(".shopindex_right").css({"width":"100%"});
  jQuery(".regoods").css("width","100%");
  jQuery(".regoods h1").css("width","100%");
  jQuery(".regoods .smallgoods").css("width","100%");
  jQuery(".regoods .smallgoods ul").css("margin-right","40px");
});
jQuery(".sbar_open").attr("cursor","pointer").click(function(){
  jQuery(".shopindex_left").fadeIn("normal");
  jQuery(".sbar_close").show();
  jQuery(".sbar_open").hide();
  jQuery(".shopindex_right").css("width","797px");
  jQuery(".regoods h1").css("width","797px");
  jQuery(".regoods .smallgoods").css("width","797px");
  jQuery(".regoods .smallgoods ul").css("margin-right","4px");
});
jQuery(".smallgoods .goodsimgs img").lazyload({effect:"fadeIn",width:178,height:170});

//
jQuery("#goods_spec_info_close>a").click(function(){
   jQuery("#goods_spec_info").removeClass().addClass("detailsbottom");
   jQuery("#goods_spec_info_close").hide();
   jQuery("#goods_spec_chose").hide();
});
//
jQuery(".enjoy_btn").mouseover(function(){
   jQuery(".enjoy_box").show();
 }).mouseleave(function(){
   jQuery(".enjoy_box").hide();
 });

  //城市tab切换
  $("#area_tab span").click(function(){
    var index =	$(this).index();
    var s =parseInt(index)-1;
    var city_id =$("#area_tab span").eq(s).find("a").attr("city_id");
    if(city_id!="" || index==0){
      load_area(city_id,index,"tab");
      var city_id =$("#city_info span").eq(index).attr("city_id");
      $("#citys ul li[id="+city_id+"]").addClass("this");
    }
  });

  //加载下级
  $("#citys a").on("click",function(){
    var id = $(this).attr("id");
    var level=$(this).attr("level");
    var name=$(this).html();
    load_area(id,level,"loading",name);
  });
});
var time=0;
var time_id;

// 商品添加到购物车
function add_cart(){
<#if ((user.id)!0) == ((store.memberId)!-1)>
 alert("不能购买自己的商品！");
<#else>
  var add=true
  var gsp="";
  if(jQuery(".colordate").length==0){
    add=true;
  }else{
	jQuery(".colordate").each(function(){
	   jQuery.each(jQuery(this).find("a[class=this]"),function(){
		  gsp=jQuery(this).attr("gsp")+","+gsp;
	   });
       if(jQuery(this).find("a[class=this]").length==0) add=false;
	});
  }
  var count=jQuery("#goods_count").val();
  if(count==0){
    add=false;
	alert("至少购买一件商品!");
	return;
  }
  if(goods_inventory==0){
    add=false;
	alert("商品库存为0,不能购买!");
	return;
  }
  if(add){
	jQuery.post("${S_URL}/add_goods_cart",{"id":"${(obj.id)!}","count":count,"price":store_price,"gsp":gsp},function(data){
	     jQuery("#cart_goods_count").html(data.count);
		 jQuery("#cart_goods_totalprice").html(data.total_price);
		 jQuery(".goodscar").fadeIn();
	     time_id=window.setInterval(cart_fadeOut,1000);
		 //更新顶部购物车信息
		 jQuery("#cart_goods_count_top").html(data.count);
	},"json");
  }else{
    jQuery("#goods_spec_info").removeClass().addClass("detailsbottom detailsbottom_hover");
	jQuery("#goods_spec_info_close").show();
	jQuery("#goods_spec_chose").show();
  }
 </#if>
}

function cart_fadeOut(){
  time++;
  if(time==3){
    jQuery(".goodscar").fadeOut();
	time=0;
	window.clearInterval(time_id);
  }
}

// 立即购买
function buy_goods(){
<#if ((user.id)!0) == ((store.memberId)!-1)>
 alert("不能购买自己的商品！");
<#else>
  var add=true;
  var gsp="";
  if(jQuery(".colordate").length==0){
    add=true;
  }else{
	jQuery(".colordate").each(function(){
	   jQuery.each(jQuery(this).find("a[class=this]"),function(){
		  gsp=jQuery(this).attr("gsp")+","+gsp;
	   });
       if(jQuery(this).find("a[class=this]").length==0) add=false;
	});
  }
  var count=jQuery("#goods_count").val();
  if(count==0){
    add=false;
	alert("至少购买一件商品!");
	return;
  }
  if(goods_inventory==0){
    add=false;
	alert("商品库存为0,不能购买!");
	return;
  }
  var buy_type=arguments[0];
  if(add){
	jQuery.post("${S_URL}/add_goods_cart",{"id":"${(obj.id)!}","count":count,"price":store_price,"gsp":gsp,"buy_type":buy_type},function(data){
	     jQuery("#cart_goods_count").html(data.count);
		 jQuery("#cart_goods_totalprice").html(data.total_price);
		 window.location.href="${S_URL}/goods_cart1";
	},"json");
  }else{
	jQuery("#goods_spec_info").removeClass().addClass("detailsbottom detailsbottom_hover");
	jQuery("#goods_spec_info_close").show();
	jQuery("#goods_spec_chose").show();
  }
 </#if>
}
function ajaxPage(url,currentPage,obj){
  jQuery.ajax({type:'POST',url:url,data:{"currentPage":currentPage,"goods_id":"${(obj.id)!}","id":"${(store.storeId)!}"},
			  beforeSend:function(){
			   },
			  success:function(data){
                 jQuery(".selleddetails").empty();
                 jQuery(".selleddetails").append(data);																									                }
		})
}
</script>
</head>
<body>
    ${httpInclude.include("/top")}
    ${httpInclude.include("/store_head?store_id=${(store.storeId)!}")}
    <#assign banner="${imageWebServer!}/static/images/shop/${(store.template)!}/images/banner.jpg" />
    <#if (store.banner)!??>
        <#assign banner="${RES_URL}/${(store.banner.path)!}/${(store.banner.name)!}" />
    </#if>
    <div class="banner_width">
      <div class="shopbanner"><img src="${banner!}"  width="1200px" /></div>
    </div>
    <div class="nav_width">
        <div class="main">
            <div class="nav_bg">
                <div class="shopnav"> ${httpInclude.include("/store_nav?id=${(store.storeId)!}&goods_view=true&goods_id=${(obj.id!)}")}
                    <div class="shopnavs"></div>
                </div>
            </div>
        </div>
        <div class="navbotm"></div>
    </div>
    <div class="main">
        <div class="shop">
            <div class="shop_index">
                <div class="shop_repright">
                    <div class="shop_report">
                        <span class="shop_respan">
                            <i>举报中心</i><b class="shop_rep_top"></b>
                            <b class="shop_rep_botm" style="display:none;"></b>
                        </span>
                        <ul class="shop_reul" style="display:none">
                            <li>
                                <a href="javascript:void(0);" id="report_goods"
                                   <#if user!?? >
                                   <#else>
                                   dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"
                                   </#if>>举报此商品</a>
                            </li>
                        </ul>
                    </div>
                    <div class="shoptop">
                        <h1>${(store.storeName)!}</h1>
                        <div class="shopvalue">
                            <dl class="shopvdl">
                                <dt><span>
                                    <#if (store.logo)!??>
                                        <#assign store_logo="${RES_URL}/${(store.logo.path)!}/${(store.logo.name)!}" />
                                    <#else>
                                        <#assign store_logo="${S_URL}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
                                    </#if>
                                <img src="${store_logo!}" width="60" height="60" />
                                </span></dt>
                                    <#assign credit=storeViewTools.generic_store_credit("${(store.storeId)!}") />
                                    <#assign img="${imageWebServer!}/static/images/common/level_0.gif" />
                                    <#if credit lt 10 && credit gt 0>
                                        <#assign credit = credit / 2 />
                                        <#assign credit = credit+1 />
                                    </#if>
                                    <#if credit gte 20 >
                                        <#assign img="${imageWebServer!}/static/images/common/level_2.gif" />
                                        <#assign credit=(credit - 20)/2 />
                                        <#assign credit = credit+1 />
                                    </#if>
                                    <#if credit gte 10 >
                                        <#assign img="${imageWebServer!}/static/images/common/level_1.gif" />
                                        <#assign credit=(credit - 10)/2 />
                                        <#assign credit=credit+1 />
                                    </#if>
                                    <#if credit gt 5 ><#assign credit=5 /></#if>
                                <dd>
                                    <span >${(store.memberId)!}
                                        <a href="${S_URL}/buyer/message_send?userName=${(store.user.userName)!}" target="_blank" class="shopemail">
                                            <img src="${imageWebServer!}/static/images/common/mail.jpg" width="15" height="11" />
                                        </a>
                                    </span>
                                    <span>
                                        <#if credit==0 >
                                            <img src="${imageWebServer!}/static/images/common/level_-1.gif"/>
                                        <#else>
                                            <#list 1..credit as count >
                                                <img style="margin-left:1px;" src="${img!}" />
                                            </#list>
                                        </#if>
                                    </span>
                                    <#assign store_evaluate1= CommUtil.mul("${(store.point.storeEvaluate1)!}",100)+"%" />
                                    <span class="hui2">好评率：${store_evaluate1!}</span>
                                </dd>
                            </dl>
                            <h1>动态评价</h1>
                            <ul class="shop_movepj">
                                <li><i>描述相符：</i>
                                  <b>${(store.point.descriptionEvaluate)!}</b>
                                  <em class="${description_css!}">
                                      <strong>${description_type!}</strong>${description_result!}
                                  </em>
                                </li>
                                <li><i>服务态度：</i>
                                  <b>${(store.point.serviceEvaluate)!}</b>
                                  <em class="${service_css!}">
                                      <strong>${service_type!}</strong>${service_result!}
                                  </em>
                                </li>
                                <li><i>发货速度：</i>
                                  <b>${(store.point.shipEvaluate)!}</b>
                                  <em class="${ship_css!}">
                                      <strong>${ship_type!}</strong>${ship_result!}
                                  </em>
                                </li>
                            </ul>
                            <h1>店铺信息</h1>
                            <ul>
                                <li>创店时间：${CommUtil.formatShortDate(store.storeTime)}</li>
                                <li>所在地区：${areaViewTools.generic_area_info(store.area.id)}</li>
                                <#assign goods_count=0 />
                                <#list store.goods_list! as goods_info >
                                    <#if goods_info.goodsStatus==0 >
                                        <#assign goods_count= goods_count+1 />
                                    </#if>
                                </#list>
                                <li>商品数量：<strong class="blue">${goods_count!}</strong>件商品</li>
                                <li>店铺收藏：<strong class="blue">${(store.favorite_count)!}</strong>人收藏</li>
                            </ul>
                            <h1>联系方式</h1>
                            <ul>
                                <li class="shopcusser">

                                    <span>站内客服：</span>
                                    <span>
                                    <#if user?? >
                                        <a class="im_common" href="javascript:void(0);" user_id="${(store.memberId)!}" id="userDialog_img_contact_${(store.memberId)!}" user_name="${(store.user.userName)!}"> 咨询客服</a>
                                    <#else>
                                        <a class="im_common" href="javascript:void(0);" dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login">咨询客服 </a>
                                    </#if>
                                    </span>
                                </li>
                                <li class="shopcusser">
                                    <span>站外客服：</span>
                                    <span>
                                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(store.storeQq)!}&Site=${(store.storeQq)!}&Menu=yes">
                                            <img alt="点击这里给我发消息" src="http://wpa.qq.com/pa?p=2:${(store.storeQq)!}:51" /></a></span></li>
                            </ul>
                        </div>
                        <div class="shop_botm_hid">
                            <ul class="shopboh">
                              <li class="collection">
                                  <a href="javascript:void(0);" id="report_goods"
                                     <#if user!??>
                                     <#else>
                                        dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"
                                     </#if>><span>收藏店铺</span>
                                  </a>
                              </li>
                              <li class="this">
                                  <span class="twocoad">店铺二维码
                                      <div class="shop_botmhbox" style="display:none;">
                                          <span>
                                              <img src="${imageWebServer!}/${(config.uploadFilePath)!}/store/${(store.storeId)!}/code.png" width="140" height="140" />
                                          </span>
                                          <i>手机扫描二维码<br />快速收藏店铺</i>
                                      </div>
                                  </span>
                              </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="productdeta">
                    <h1>
                        <strong class="tuang_h">${(obj.goodsName)!}</strong>
                        <#if group==1 ><span class="tuang">团购</span></#if>
                        <#if (obj.bargainStatus)!0==2 > <span class="tuang">特价</span> </#if>
                        <#if (goods.activityStatus)!0==2><span class="tuang">活动</span></#if>
                        <#if (goods.bargainStatus)!0==2><span class="tuang">特价</span></#if>
                        <#if (goods.combinStatus)!0==2><span class="tuang">组合</span></#if>
                        <#if (goods.deliveryStatus)!0==2><span class="tuang">买就送</span></#if>
                    </h1>
                    <div class="photoproduct">
                        <div class="photopro">
                            <#if (obj.goods_main_photo)!?? >
                                <#assign small_img="${RES_URL}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}_small.${(obj.goods_main_photo.ext)!}" />
                                <#assign big_img="${RES_URL}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}" />
                            <#else>
                                <#assign small_img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                <#assign big_img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                            </#if>
                                <a href="${big_img!}" class="jqzoom" rel='gal1' title="商品细节图" >
                                    <img src="${big_img!}" title="商品细节图" id="main_img" width="300" height="300"
                                         style="border:1px #666 solid;"/>
                                </a>
                                <ul class="photoimgul">
                                    <li class="this">
                                        <a class="zoomThumbActive" href='javascript:void(0);' rel="{gallery: 'gal1', smallimage: '${big_img!}',largeimage: '${big_img!}'}">
                                            <img src="${small_img!}" big_img="${big_img!}" width="40" height="40"  onclick="img_switch(this);"  style="cursor:pointer;"/></a>
                                    </li>
                                    <#list (obj.goods_photos)! as img>
                                        <li><a  href='javascript:void(0);' rel="{gallery: 'gal1', smallimage: '${RES_URL}/${(img.path)!}/${(img.name)!}',largeimage: '${RES_URL}/${(img.path)!}/${(img.name)!}'}">
                                            <img src="${imageWebServer!}/${(img.path)!}/${(img.name)!}_small.${(img.ext)!}" big_img="${RES_URL}/${(img.path)!}/${(img.name)!}" width="40" height="40" onclick="img_switch(this);" style="cursor:pointer;" />
                                        </a></li>
                                    </#list>
                                </ul>
                                <div class="collection_goods">
                                    <span><a class="save_good" href="javascript:void(0);"
                                             <#if user!?? >
                                             <#else>
                                             dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"</#if>>
                                            收藏(${(obj.goodsCollect)!})
                                    </a></span>
                                    <div class="enjoy_btn">
                                        <a class="enjoy_a" href="javascript:void(0);">分享到：站内/站外</a>
                                        <div class="enjoy_box" style="display:none;">
                                            <span class="enjoy_b">
                                            <input name="goods_share_btn" type="button" id="goods_share_btn" value="分享商品"
                                                   <#if user!??>
                                                   dialog_uri="${S_URL}/goods_share?goods_id=${(obj.id)!}" dialog_title="分享商品" dialog_width="450" dialog_height="400" dialog_id="goods_share"
                                                   <#else>
                                                   dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"
                                                   </#if> />
                                            </span>
                                            <span class="enjoy"> ${(config.share_code)!} </span>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <div class="productshop">
                            <div class="detailstop">
                                <ul>
                                    <li class="detailstop_ul_li">
                                        <span class="detbt">商品原价：</span>
                                        <span class="through">¥${(obj.goodsPrice)!}</span>
                                    </li>
                                    <#if group==0 >
                                        <li class="detailstop_ul_li">
                                            <span class="detbt">店铺价格：</span>
                                            <span class="pricedata">
                                                <strong class="orange" id="store_price">¥${(current_price)!}</strong>
                                            </span>
                                            <span class="greenp">(节省：<b id="rest" style="font-weight:normal;">
                                                ¥${CommUtil.subtract(obj.goodsPrice,current_price)}</b>)
                                            </span>
                                        </li>
                                    <#else>
                                        <li class="detailstop_ul_li">
                                            <span class="detbt">团购价格：</span>
                                            <span class="pricedata">
                                                <strong class="orange" id="store_price">¥${current_price!}</strong>
                                            </span>
                                            <span class="greenp">(节省：<b id="rest" style="font-weight:normal;">
                                                ¥${CommUtil.subtract(obj.goodsPrice,current_price)}</b>)
                                            </span>
                                        </li>
                                    </#if>
                                    <li class="detailstop_ul_li">
                                        <span class="detbt">商品重量：</span>
                                        <span>${(obj.goodsWeight)!}千克(kg)</span>
                                    </li>
                                    <li class="detailstop_ul_li">
                                        <span class="detbt">物流运费：</span>
                                        <div class="logistics">
                                            <span class="start">${(store.area.parent.areaName)!} | 至</span>
                                            <div>
                                                <span class="select_area" style="float:left; left:5px;top:-3px;">
                                                    <div class="select_area_top" style="cursor: pointer;" onclick="show_areas()">
                                                        <span id="city_info" city_id="" city_name="">
                                                          <span city_id="">请选择省份</span>
                                                          <span city_id="">请选择城市</span>
                                                          <span city_id="">请选择区县</span>
                                                        </span>
                                                        <em><img src="${imageWebServer!}/static/styles/shop/default/images/down_arrow.gif" data-bd-imgshare-binded="1"></em>
                                                    </div>
                                                    <div class="select_area_list" style="display: none;">
                                                        <div class="select_area_list_close">
                                                            <a onclick="javascript:$('.select_area_list').hide();" href="javascript:void(0);">X</a>
                                                        </div>
                                                        <div class="select_area_list_center">
                                                            <div class="select_area_list_center_t" id="area_tab">
                                                                <span class="this"><a href="javascript:void(0);" city_id="" city_name=""></a><em></em></span>
                                                                <span><a href="javascript:void(0);" city_id="" city_name=""></a><em></em></span>
                                                                <span><a href="javascript:void(0);" city_id="" city_name=""></a><em></em></span>
                                                            </div>
                                                            <div class="select_area_list_center_m" id="citys"><ul></ul></div>
                                                        </div>
                                                    </div>
                                                </span>
                                            </div>
                                            <!--物流运费新增start-->
                                            <#if ((obj.goodsTransfee)!0)==1>
                                                <span class="detbt"></span><span>卖家承担</span>
                                            <#else>
                                                <#if (obj.transport)!??>
                                                    <span>
                                                    <#if (obj.transport.transMail)!??>平邮: <i id="goods_mail_fee">
                                                        ¥${transportTools.cal_goods_trans_fee("${(obj.transport.id)!}","mail","${(obj.goodsWeight)!}","${(obj.goodsVolume)!}","${current_city!}")} </i>
                                                    </#if>
                                                    <#if (obj.transport.transExpress)!??>快递: <i id="goods_express_fee">
                                                        ¥${transportTools.cal_goods_trans_fee("${(obj.transport.id)!}","express","${(obj.goodsWeight)!}","${(obj.goodsVolume)!}","${current_city!}")}</i>
                                                    </#if>
                                                    <#if (obj.transport.transEms)!??>EMS: <i id="goods_ems_fee">
                                                        ¥${transportTools.cal_goods_trans_fee("${(obj.transport.id)!}","ems","${(obj.goodsWeight)!}","${(obj.goodsVolume)!}","${current_city!}")}</i>
                                                    </#if>
                                                    </span>
                                                <#else>
                                                    <span>平邮:¥${CommUtil.null2Float(obj.mailTransFee)}
                                                        快递:¥${CommUtil.null2Float(obj.expressTransFee)}
                                                        EMS:¥${CommUtil.null2Float(obj.emsTransFee)}
                                                    </span>
                                                </#if>
                                            </#if>
                                        </div>
                                    </li>
                                <#if (obj.dg)!??>
                                    <li class="detailstop_ul_li"><span class="detbt">赠品：</span>
                                        <span style="margin-right:10px;width:360px;overflow:hidden;">
                                            <a href="${S_URL}/goods_${(obj.dg.d_delivery_goods.id)!}" target="_blank">
                                            ${(obj.dg.d_delivery_goods.goodsName)!}
                                            </a>
                                        </span>
                                        <#if (obj.dg.d_delivery_goods.goods_main_photo)!??>
                                            <#assign img="${imageWebServer!}/${(obj.dg.d_delivery_goods.goods_main_photo.path)!}/${(obj.dg.d_delivery_goods.goods_main_photo.name)!}_small.${(obj.dg.d_delivery_goods.goods_main_photo.ext)!}" />
                                        <#else>
                                            <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                        </#if>
                                        <span style="float:left;margin-left:90px;width:360px;overflow:hidden;">
                                            <a href="${S_URL}/goods_${(obj.dg.d_delivery_goods.id)!}" target="_blank">
                                                <img src="${img!}" width="60" height="60" /></a>
                                        </span>
                                    </li>
                                </#if>
                                    <!--物流运费新增end-->
                                    <li class="detailstop_ul_li">
                                        <span class="detbt">销售情况：</span>
                                        <span>售出${(obj.goodsSalenum)!}件(${(obj.evaluates)!?size}条评论)</span>
                                    </li>
                                    <li class="detailstop_ul_li"><span class="detbt">关注次数：</span><span>${obj.goodsClick}次</span></li>
                                </ul>
                            </div>
                            <script>
                              // 记录买家选择的商品规格
                              function goods_spec_set(obj){
                                var spec=jQuery(obj).attr("spec");
                                var gsp="";
                                var load=true;
                                jQuery(".colordate a[spec="+spec+"]").removeClass("this");
                                jQuery(obj).addClass("this");
                                jQuery(".colordate").each(function(){
                                  jQuery.each(jQuery(this).find("a[class=this]"),function(){
                                    gsp=jQuery(this).attr("gsp")+","+gsp;
                                  });
                                });
                                jQuery(".colordate").each(function(){
                                  if(jQuery(this).find("a[class=this]").length==0) load=false;
                                });
                                if(load){
                                  jQuery("#goods_spec_info").removeClass().addClass("detailsbottom");
                                  jQuery("#goods_spec_info_close").hide();
                                  jQuery("#goods_spec_chose").hide();
                                  jQuery.post("${S_URL}/load_goods_gsp",{"id":"${(obj.id)!}","gsp":gsp},function(data){
                                    goods_inventory=data.count;
                                    store_price=data.price;
                                    jQuery("#goods_inventory").html(goods_inventory);
                                    jQuery("#store_price").html("¥"+store_price);
                                    jQuery("#rest").html("¥"+(goods_price-store_price));
                                  },"json");
                                }
                              }
                            </script>
                            <div class="detail_solid"></div>
                            <div class="detailsbottom_ps">
                                <!-- 商品多规格 -->
                                <div class="detailsbottom" id="goods_spec_info">
                                    <ul>
                                        <li id="goods_spec_chose" class="detail_chose" style="display:none;">请选择商品属性</li>
                                        <#list goodsViewTools.generic_spec("${(obj.id)!}") as spec>
                                          <li class="colordate">
                                              <span class="datespan">${(spec.name)!}：</span>
                                              <span class="chosecolor">
                                                  <#list (obj.goods_specs)! as gsp >
                                                      <#if gsp.specId==spec.id >
                                                          <b style="font-weight:lighter;">
                                                              <a href="javascript:void(0);" onclick="goods_spec_set(this);" spec="${(gsp.specId)!}" gsp="${(gsp.id)!}">${(gsp.value)!}</a>
                                                          </b>
                                                      </#if>
                                                  </#list>
                                              </span>
                                          </li>
                                        </#list>
                                        <li class="stockdate">
                                            <span class="datespan">数量：</span><span class="stock">
                                            <input name="goods_count" type="text" id="goods_count" value="1" /></span>
                                            <#if ((obj.groupBuy)!0)==2 >
                                                <#list  (obj.group_goods_list)! as gg_info>
                                                    <#if gg_info.ggGoodsId == obj.id>
                                                        <#assign goods_inventory = gg_info.ggCount />
                                                    </#if>
                                                </#list>
                                            <#else>
                                                <#assign goods_inventory="${(obj.goodsInventory)!}" />
                                            </#if>
                                            <span class="stockparts">件<#if group==1 >团购剩余<#else>库存</#if>
                                                <b id="goods_inventory" style="font-weight:normal;">${goods_inventory!}</b>件）
                                            </span>
                                        </li>
                                        <li>
                                            <span class="datespan">&nbsp;</span>
                                            <span class="addcar">
                                                <input name="input" type="button" value="添加到购物车" onclick="add_cart();" />
                                            </span>
                                            <span class="atonce">
                                                <input name="input" type="button" value="立刻购买" onclick="buy_goods();" />
                                            </span>
                                            <div class="goodscar" style="display:none;z-index:999;">
                                                <div>
                                                    <a href="javascript:void(0);" onclick="javascript:jQuery('.goodscar').hide();" class="closed">
                                                        <img src="${imageWebServer!}/static/styles/shop/default/images/closed.jpg" width="12" height="12" />
                                                    </a>
                                                </div>
                                                <ul>
                                                    <li>已成功添加到购物车！</li>
                                                    <li>购物车共有 <strong class="orange" id="cart_goods_count">0</strong> 种宝贝，合计：¥ <strong class="orange" id="cart_goods_totalprice">0.00</strong></li>
                                                    <li><a <#if user!?? >href="${S_URL}/goods_cart1" <#else> href="javascript:if(confirm('您尚未登录，现在登录?'))window.location.href='${S_URL}/user/login?url=${S_URL}/goods_cart1'" </#if> class="go_cart">立刻结算</a></li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="Close" id="goods_spec_info_close" style="display:none;"><a href="javascript:void(0);">X</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="shop_index_box"> ${httpInclude.include("/store_left1?id=${(store.storeId)!}")}
                <div class="shopindex_right">
                    <div class="switch_bar" id="switch_bar">
                        <span class="sbar_open" style="display:none">
                            <a class="sbar_open_btn" href="javascript:void(0);"></a>
                        </span>
                        <span class="sbar_close" >
                            <a class="sbar_close_btn" href="javascript:void(0);"></a>
                        </span>
                    </div>
                <#if ((obj.combinStatus)!0)==2 >
                    <div class="Group">
                        <div class="selled">
                            <ul><li class="this">推荐组合</li></ul>
                        </div>
                        <div class="Group_main">
                            <div class="Group_left">
                                <i><img src="${imageWebServer!}/static/styles/shop/default/images/23x23.png" /></i>
                                <div class="G_pic">
                                    <span>
                                        <#if (obj.goods_main_photo)!??>
                                            <#assign big_img="${RES_URL}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}" />
                                        <#else>
                                            <#assign big_img="${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                        </#if>
                                        <a href="${S_URL}/goods_${(obj.id)!}">
                                            <img src="${big_img!}" width="122" height="122" />
                                        </a>
                                    </span>
                                    <span><a href="${S_URL}/goods_${(obj.id)!}">${(obj.goodsName)!}</a></span>
                                </div>
                            </div>
                            <div class="Group_mid">
                                <ul>
                                    <#assign goods_total_price=obj.goodsCurrentPrice />
                                    <#list (obj.combinGoods)! as info >
                                        <#assign goods_total_price=goods_total_price+info.goodsCurrentPrice />
                                        <li>
                                            <#if info_index lt (obj.combinGoods)!?size >
                                                <i><img src="${S_URL}/static/styles/shop/default/images/23x23.png" /></i>
                                            </#if>
                                            <div class="li_lf">
                                                <#if (info.goods_main_photo)!??>
                                                    <#assign big_img="${RES_URL}/${(info.goods_main_photo.path)!}/${(info.goods_main_photo.name)!}" />
                                                <#else>
                                                    <#assign big_img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                                </#if>
                                                <span>
                                                    <a href="${S_URL}/goods_${(info.id)!}" target="_blank">
                                                        <img src="${big_img!}" width="122" height="122" />
                                                    </a>
                                                </span>
                                                <span>
                                                    <a href="${S_URL}/goods_${(info.id)!}" target="_blank">${CommUtil.substring("${(info.goodsName)!}",18)}</a>
                                                </span>
                                                <div class="choose"> <b>￥${(info.goodsCurrentPrice)!}</b></div>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                            <div class="Group_right">
                                <i><img src="${imageWebServer!}/static/styles/shop/default/images/23x15.png" /></i>
                                <div class="i_right">
                                    <span>组合购买更划算</span>
                                    <span class="sp_color">搭配价：￥<b>${(obj.combinPrice)!}</b></span>
                                    <#assign combin_save_price = goods_total_price - obj.combinPrice />
                                    <span class="sp_color">获得优惠：￥${(combin_save_price)!}</span>
                                    <span><a href="javascript:void(0);" onclick="buy_goods('combin');">购买组合</a></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
                    <div class="shopselled" id="goods_anchor" name="goods_anchor">
                        <div class="selled" id="goods_tab">
                            <ul class="selled_ul">
                                <li class="this" id="details" uri="${S_URL}/goods_detail?goods_id=${(obj.id)!}&id=${(store.storeId)!}" style="cursor:pointer">
                                    <a href="#goods_anchor">商品详情</a>
                                </li>
                                <li id="evaluation" uri="${S_URL}/goods_evaluation?goods_id=${(obj.id)!}&id=${(store.storeId)!}">
                                    <a href="#goods_anchor">评价详情</a>
                                </li>
                                <li id="strike" uri="${S_URL}/goods_order?goods_id=${(obj.id)!}&id=${(store.storeId)!}">
                                    <a href="#goods_anchor">成交记录</a>
                                </li>
                                <li id="advice" uri="${S_URL}/goods_consult?goods_id=${(obj.id)}&id=${store.storeId}">
                                    <a href="#goods_anchor">产品咨询</a>
                                </li>
                            </ul>
                            <span class="selled_kf" style="display:none;">
                                <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(store.storeQq)!}&Site=${(store.storeQq)!}&Menu=yes">
                                    <img alt="点击这里给我发消息" src="http://wpa.qq.com/pa?p=2:${(store.storeQq)!}:51" />
                                </a>
                            </span>
                        </div>
                        <div class="img_center_load" style="display:none;text-align:center; padding-top:5px;">
                            <img height="28" width="28" src="${S_URL}/static/images/common/loader.gif" /> </div>
                        <div class="selleddetails">
                            <!--商品详情-->
                            <div class="goodsdetails" id="details_tab">
                                <div class="goodsintro"></div>
                                <div style="margin-top:10px;">${(obj.goodsDetails)!}</div>
                            </div>
                        </div>
                    </div>
                    <div class="regoods">
                        <h1>
                            <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}&recommend=true">更多</a>
                            <span>推荐商品</span></h1>
                        <div class="smallgoods">
                            <#list (goods_recommend_list)! as goods >
                                <#if (goods.goods_main_photo)!?? >
                                    <#assign img="${RES_URL}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}" />
                                <#else>
                                    <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                                </#if>
                                <ul>
                                    <li class="goodsimgs">
                                        <span class="goods_sp_span">
                                            <p><a href="${S_URL}/goods_${(goods.id)!}">
                                                <img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
                                                     onerror="this.src='${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';"
                                                     width="28" height="28"/>
                                            </a></p>
                                        </span>
                                    </li>
                                    <li class="goodslook"><a href="${S_URL}/goods?id=${(goods.id)!}" class="look">查看详情</a>
                                        <strong>¥${(goods.storePrice)!}</strong>
                                    </li>
                                    <li class="goodsnames">
                                        <a href="${S_URL}/goods_${goods.id}">${CommUtil.substring("${(goods.goodsName)!}",28)}</a>
                                    </li>
                                    <li class="recentgoodsok">最近成交<strong>${(goods.goodsSalenum)!}</strong>笔</li>
                                </ul>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    ${httpInclude.include("/footer")} </div>
</body>
</html>
