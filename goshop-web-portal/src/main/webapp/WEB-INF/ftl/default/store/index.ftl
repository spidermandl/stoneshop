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
		<link rel="shortcut icon" href="${imageWebServer!}/${(config.website_ico.path)!}/${(config.website_ico.name)!}"
			  type="image/x-icon"/>
	</#if>
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<#if (config.sina_domain_code)!?? >
		${(config.sina_domain_code)!}
	</#if>
	<#if (config.qq_domain_code)!?? >
		${(config.qq_domain_code)!}
	</#if>

    <link href="${S_URL}/static/styles/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/system/front/default/css/public_auto.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/system/front/default/css/index.css" type="text/css" rel="stylesheet" />
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.lazyload.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
	<script src="${S_URL}/static/scripts/DataLazyLoad.js"></script>
	<script src="${S_URL}/static/scripts/jquery.SuperSlide.2.1.1.js"></script>
	<script src="${S_URL}/static/scripts/jquery.shop.validate.js"></script>
	<script src="${S_URL}/static/scripts/jquery.KinSlideshow.min.js"></script>
<script>
jQuery(document).ready(function(){

  jQuery(".sale_two_img img").lazyload({effect:"fadeIn",width:150,height:150});
  //鼠标经过推荐商品tab
  jQuery("#index_sale_tab ul li").mouseover(function(){
	jQuery(this).siblings().removeClass("this");
	jQuery(this).addClass("this");
	var i = jQuery(this).index();
	jQuery("#index_sale_tab").siblings().hide();
	jQuery("#index_sale_tab").siblings().eq(i).show();
	jQuery("#sale_change").attr("mark",jQuery(this).attr("id").replace("goodscase",""));
  });

  /* jQuery(".shop_left_btn_con li").mouseover(function(){
	var child_count = jQuery(this).attr("child_count");
	if(child_count>0){
	 var id=jQuery(this).attr("id");
     jQuery("#child_"+id).show();
	}
  }).mouseleave(function(){
	 var child_count = jQuery(this).attr("child_count");
	if(child_count>0){
     var id=jQuery(this).attr("id");
     jQuery("#child_"+id).hide();
	}
  });
  jQuery(".specialde li").mouseover(function(){
     jQuery(this).find("i").show();
  }).mouseleave(function(){
     jQuery(this).find("i").hide();
  });
  jQuery(".productone ul").mouseover(function(){
    jQuery(".productone ul").removeClass("this");
	jQuery(this).addClass("this");
  }).mouseleave(function(){
    jQuery(".productone ul").removeClass("this");
  }); */

  //广告图片懒加载
  /* jQuery(".flr_advertisment img").lazyload({effect:"fadeIn",width:156,height:156});
  jQuery(".rank_advertisment img").lazyload({effect:"fadeIn",width:205,height:205});
  jQuery(".brand_bottom_adv img").lazyload({effect:"fadeIn",width:288,height:127});
  jQuery(".floor_brand img").lazyload({effect:"fadeIn",width:98,height:35}); */
  //团购图片轮播懒加载
  jQuery(".goods_tab img").lazyload({effect:"fadeIn",width:210,height:210});
  //楼层懒加载
  jQuery(".floor_main img").lazyload({effect:"fadeIn",width:147,height:147});

 //
 jQuery(".index_sales_left>h3>ul>li").mouseover(function(){
    jQuery(".index_sales_left>h3>ul>li").removeClass("this");
	jQuery(this).addClass("this");
	jQuery(".index_sales_box>[class^=index_sales_]").hide();
	var div_index=jQuery(this).attr("div_index");
	jQuery(".index_sales_"+div_index).show();
 });

  //
  var size="4";
  if(size>0){
  	  jQuery("html").scrollTop(0);	//刷新页面返回顶部
	  //Call DataLazyLoad
	  jQuery("#floors .floor_main").DataLazyLoad({load : function(page, unLocked) {
		var html = '';
		var max = 4+1;
		var count = page-1;
		var id =jQuery(".floor_main[count="+count+"]").attr("id");
		var load_url =jQuery(".floor_main[count="+count+"]").attr("load_url");
		//Generate the data
		jQuery.get("${S_URL}/"+load_url,{"id":id,"count":count},function(data){
					html += data;
					jQuery(html).appendTo('#floors .floor_main[count='+count+']');
					/* jQuery(".floorclass img").lazyload({effect:"fadeIn",width:147,height:147});
					jQuery(".ranking img").lazyload({effect:"fadeIn",width:73,height:73}); */
					//鼠标经过楼层tab时
					jQuery(".floorul li").mouseover(function(){
						var store_gc=jQuery(this).attr("store_gc");
					    jQuery(".floorul li[store_gc="+store_gc+"]").css("cursor","pointer").removeClass("this");
						jQuery(this).addClass("this");
					    var id=jQuery(this).attr("id");
						jQuery(".ftab[store_gc="+store_gc+"]").hide();
						jQuery(".ftab[store_gc="+store_gc+"][id="+id+"]").show();
					});
					//Check whether to end
					page = page >= max ? 0 : page + 1;
					//To prevent repeated load, The first parameter to the next page, No page is 0
					unLocked(page);
			},"text");
	  }});
  }else{
	//jQuery("#toolbar").load("toolbar.htm");
  }

  jQuery(window).scroll(function(){
	var top = jQuery(document).scrollTop();
	//楼层导航跟随
	jQuery(".back_floor").css("display","none");
	jQuery("li[floor_id^=floor_] b").css("display","block");
	jQuery("div[id^=floor_]").each(function(){//循环每一个楼层
	    var floor_top=jQuery(this).offset().top-top;
		 if(floor_top<=580&&floor_top>=0){//如果到达一个位置
			  var floor_id=jQuery(this).attr("id");
			  if(jQuery("li[floor_id="+floor_id+"] b").css("display","none"))
			  {
				jQuery(".back_floor").css("display","block");
			  }
	 }
	});
  });

  //back_floor
  jQuery(".back_floor>ul>li").click(function(){
  	 var id=jQuery(this).attr("floor_id");
     var top = jQuery("#"+id).offset().top-80;
     jQuery('body,html').animate({scrollTop:top},1000);
  });

  var l=jQuery(".main").offset().left;
  jQuery(".back_box_x").css("left",l-43+"px");

});

jQuery(window).resize(function() {
   var l=jQuery(".main").offset().left;
   jQuery(".back_box_x").css("left",l-43+"px");
});

/* var recommend_goods_random=1;//随机次数
function switch_recommend_goods(){
  jQuery.ajax({type:'POST',url:'${S_URL}/switch_recommend_goods.htm',data:{"recommend_goods_random":recommend_goods_random},
			  beforeSend:function(){
				     jQuery(".limishop_hidden").empty();
					 jQuery("#store_reommend_goods").html("<div style='width:100%;height:301px;text-align:center;'><img src='${S_URL}/resources/style/common/images/loader.gif' style='margin-top:145px;' /></div>");
				  },
			  success:function(html){
	                      jQuery("#store_reommend_goods").html(html);
	                      recommend_goods_random++;
	                      if(recommend_goods_random>${(store_reommend_goods_count)!}){
                              recommend_goods_random=1;
	                       }
					  }
			 });
} */

</script>
</head>
<body>
<!--低版本提示-->
<!--[if IE 6]>
<div class="top_tipe">
	<div class="top_tipe_center">
    	<em><img src="${S_URL}/static/styles/system/front/default/images/ie6_warning.png"
				 style="height:16px; width:16px;"/></em>
		<b>温馨提示：您当前使用的浏览器版本过低，兼容性和安全性较差，建议您升级：</b>
		<em><img src="${S_URL}/static/styles/system/front/default/images/ie_b.png"/></em>
		<b><a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie?tp=1.0.0.0.0.KdMt0Yu">IE8浏览器</a>或</b>
		<em><img src="${S_URL}/static/styles/system/front/default/images/google_b.png" /></em>
		<b><a href="http://www.google.cn/intl/zh-CN/chrome/">谷歌浏览器</a></b>
        <span><a href="javascript:void(0);" onclick="javascript:jQuery('.top_tipe').hide();">x</a></span>
    </div>
</div>
<![endif]-->
${httpInclude.include("/top")}
${httpInclude.include("/head")}
${httpInclude.include("/nav")}

<script src="${S_URL}/advert_invoke?id=1"></script>
<div class="main">
  <div class="index">
  	<div class="top_index">
	    <div class="top_mid">
	      <div class="top_mid">
	        <div class="banner_nothing"></div>
	      </div>
	    </div>
	    <div class="top_mid_right">
	      	<style>
	      	.phone_txt.error{width: 120px;
							height: 20px;
							line-height: 20px;
							border: 1px solid #f00;}
			</style>
		      <script>
			  jQuery(function(){
				 jQuery(".top_mid_nav div").css("cursor","pointer").mouseover(function(){
				    var target_tab_id=jQuery(this).attr("target_tab_id");
					jQuery(".top_mid_nav div").removeClass("this");
					jQuery(this).addClass("this");
					jQuery("#news_tab").hide();
					jQuery("#group_tab").hide();
					jQuery("#"+target_tab_id).show();
				 });
				 //
				 jQuery(".group_tab").slide({mainCell:".top_regiment ul",autoPlay:true,interTime:3000,prevCell:".top_mid_regiment_img_left",nextCell:".top_mid_regiment_img_right"});
				 jQuery(".goods_tab").slide({mainCell:".top_regiment ul",autoPlay:true,interTime:3000,prevCell:".top_mid_regiment_img_left",nextCell:".top_mid_regiment_img_right"});
				 //
				 jQuery(".top_regiment").mouseenter(function(){
				    jQuery(".top_mid_regiment_img_left").show();
					jQuery(".top_mid_regiment_img_right").show();
				 }).mouseleave(function(){
			        jQuery(".top_mid_regiment_img_left").hide();
					jQuery(".top_mid_regiment_img_right").hide();
				 });
			  })
			  </script>

			  <!-- 右上角轮播推荐商品 -->
		      <div class="goods_tab">
		          <div style="display:;" id="" class="top_regiment">
		              <ul class="top_mid_regiment_t">
		              	<#list store_reommend_goods! as obj >
			              <#if (obj.goods_main_photo)!?? >
			              		<#assign img="${imageWebServer!}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}_middle.${(obj.goods_main_photo.ext)!}" />
			              <#else>
			              		<#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
						  </#if>
			              <#assign goods_url="${S_URL}/goods?id=${(obj.id)!}" />
			              <#if ((config.second_domain_open)!false) == true>
							  <#assign goods_url="http://${(obj.goods_store.store_second_domain)!}.${domainPath!}/goods?id=${(obj.id)!}" />
						  </#if>

		                  <li style="display: none;">
		                  	<span class="top_mid_regiment_img_t">
								<a href="${goods_url!}" target="_blank">
									<img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
										 onerror="this.src='${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';"
										 width='28' height='28'>
								</a></span>
				          	<span class="top_mid_regiment_name_t">${CommUtil.substring("${(obj.goodsName)!}",18)}</span>
				            <p class="top_mid_regiment_img_bottom_t">
					            <span class="top_mid_regiment_img_bottom_left_t"><strong><i>¥</i>${(obj.goodsCurrentPrice)!}</strong></span>
					            <span class="top_mid_regiment_img_bottom_right_t"><a href="${goods_url!}" target="_blank">立即购买</a></span>
				            </p>
				          </li>
						</#list>
		              </ul>
		           </div>
		      </div>

		      <div class="top_mid_phone">
		          <div class="top_mid_nav">
		               <div target_tab_id="news_tab" class="top_mid_nav_phone this" style="cursor: pointer;">公告</div>
		               <div target_tab_id="group_tab" class="top_mid_nav_phone" style="cursor: pointer;">团购</div>
		          </div>
		          <div id="news_tab" class="top_mr_news">
		             <ul style=" display: block " u_id="1" class="top_mr_box">
		             	<#list articles! as article >
			            <#if (article.url)!="">
			            	<#assign url="${(article.url)!}" />
			            <#else>
			            	<#assign url="${S_URL}/article_${(article.id)!}" />
						</#if>
			            <li><b>
							<a href="${url!}" target="_blank">
							${CommUtil.formatShortDate("${(article.addtime)!}")!}</a></b>
							<a href="${url!}" target="_blank">${CommUtil.substring("${(article.title)!}",19)}</a>
						</li>
						</#list>
		             </ul>
		          </div>

		          <div class="group_tab">
		            <div style="display:none;" id="group_tab" class="top_regiment top_tg">
		              <ul class="top_mid_regiment">
		              	<#list ggs! as group >
							<li style="display: none;">
								<span class="top_mid_regiment_img">
									<a href="${S_URL}/group_view_${(group.id)!}" target="_blank">
										<img src="${imageWebServer!}/${(group.gg_img.path)!}/${(group.gg_img.name)!}" />
									</a>
								</span>
								<p class="top_mid_regiment_img_bottom">
								<span class="top_mid_regiment_img_bottom_left">团购价：
									<strong>¥${(group.gg_goods.goods_price)!}</strong></span>
								<span class="top_mid_regiment_img_bottom_right">
									<a href="${S_URL}/group_view_${(group.id)!}" target="_blank">
										<img src="${S_URL}/static/styles/system/front/default/images/ct.png">
									</a>
								</span>
								</p>
							</li>
						</#list>
		              </ul>
		              <div style="display: none;" class="top_mid_regiment_img_left">
						  <a href="javascript:void(0);"></a></div>
		              <div style="display: none;" class="top_mid_regiment_img_right">
						  <a href="javascript:void(0);"></a></div>
		            </div>
		          </div>
		       </div>
		       <!-- top_mid_phone end -->
	    </div>
	    <!-- top_mid_right end -->
  </div>
  <!-- top_index end -->
    <!-- 中间广告 -->
    <!-- <script src="${S_URL}/advert_invoke.htm?id=4"></script> -->
    <!-- tab页商品 -->
    <div class="index_center_box">
	    <div class="index_sale">
	    <div id="index_sale_tab" class="index_sale_tab">
	    	<!-- <a onclick="change_case_goods();" id="sale_change" mark="2" class="sale_change" href="javascript:void(0);">换一组</a> -->
	        <ul>
               <li goods_random="1" id="goodscase2" class="this">疯狂抢购<s></s></li>
               <li goods_random="1" id="goodscase3" class="">猜您喜欢<s></s></li>
               <li goods_random="1" id="goodscase4" class="">新品上架<s></s></li>
               <li goods_random="1" id="goodscase5" class="">满送商品<s></s></li>
               <li goods_random="1" id="goodscase6" class="">人气商品<s></s></li>
	        </ul>
	      </div>

	      <!-- 疯狂抢购 -->
	      <div id="index_sale_box_2" class="index_sale_box" style="display: block;">
	      	<div id="index_sale_con_2" class="index_sale_con">
	      		<#list fengKuangs! as fengKuang>
		        <#if (fengKuang.goods_main_photo)!?? >
		        <#assign img="${imageWebServer!}/${(fengKuang.goods_main_photo.path)!}/${(fengKuang.goods_main_photo.name)!}_small.${(fengKuang.goods_main_photo.ext)!}" />
		        <#else>
		        <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
				</#if>
		        <#assign goods_url="${S_URL}/goods?id=${(fengKuang.id)!}" />
		        <#if ((config.second_domain_open)!false) == true>
					<#assign goods_url="http://${(fengKuang.goods_store.store_second_domain)!}.${domainPath!}/goods?id=${(fengKuang.id)!}" />
				</#if>
			    <ul class="index_sale_two">
		          <li class="sale_two_img">
		          	<a target="_blank" href="${goods_url!}">
		          	<span class="img_cspan">
		              <p>
		              <img width="28" height="28"
						   src="${imageWebServer!}/static/images/common/loader.gif"
						   onerror="this.src='${S_URL}/static/images/common/good.jpg';"
						   original="${img!}" style="display: inline;">
		              </p>
		            </span>
		            </a>
		          </li>
		          <li class="sale_two_name">
					  <a target="_blank" href="${goods_url!}">${CommUtil.substring("${(fengKuang.goodsName)!}",20)}</a>
				  </li>
		          <li class="sale_two_price">售价：<strong>¥${(fengKuang.goodsCurrentPrice)!}</strong></li>
			    </ul>
				</#list>
	        </div>
	      </div>

	      <!-- 猜您喜欢 -->
	      <div id="index_sale_box_3" style="display: none;" class="index_sale_box">
		      <div id="index_sale_con_3" class="index_sale_con">
		            <#list cais! as niCai>
			        <#if (niCai.goods_main_photo)!??>
			        <#assign img="${imageWebServer!}/${(niCai.goods_main_photo.path)!}/${(niCai.goods_main_photo.name)!}_small.${(niCai.goods_main_photo.ext)!}" />
			        <#else>
			        <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
					</#if>
			        <#assign goods_url="${S_URL}/goods?id=${(niCai.id)!}" />
			        <#if ((config.second_domain_open)!false) == true>
			        <#assign goods_url="http://${(niCai.goods_store.store_second_domain)!}.${domainPath!}/goods?id=${(niCai.id)!}" />
					</#if>
					  <ul class="index_sale_two">
						<li class="sale_two_img">
						<a target="_blank" href="${goods_url!}">
						<span class="img_cspan">
						  <p>
						  <img width="150" height="150" src="${imageWebServer!}/static/images/common/loader.gif"
							   onerror="this.src='${S_URL}/static/images/common/good.jpg';"
							   original="${img!}" style="display: inline;">
						  </p>
						  </span>
						</a>
						</li>
						<li class="sale_two_name">
							<a target="_blank" href="${goods_url!}">${CommUtil.substring("${(niCai.goodsName)!}",20)!}</a>
						</li>
						<li class="sale_two_price">售价：<strong>¥${(niCai.goodsCurrentPrice)!}</strong></li>
					  </ul>
					</#list>
		      </div>
	      </div>

	      <!-- 新品上架 -->
	      <div id="index_sale_box_4" style="display: none;" class="index_sale_box">
	      <div id="index_sale_con_4" class="index_sale_con">
	        <#list xinShangs! as xinShang >
	        	<#if (xinShang.goods_main_photo)!?? >
	        		<#assign img="${imageWebServer!}/${(xinShang.goods_main_photo.path)!}/${(xinShang.goods_main_photo.name)!}_small.${(xinShang.goods_main_photo.ext)!}" />
	        	<#else>
	        		<#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
				</#if>
	        	<#assign goods_url="${S_URL}/goods?id=${(xinShang.id)!}" />
	        	<#if ((config.second_domain_open)!false) == true>
	        		<#assign goods_url="http://${(xinShang.goods_store.store_second_domain)!}.${domainPath!}/goods?id=${(xinShang.id)!}" />
				</#if>
	      	  <ul class="index_sale_two">
		          <li class="sale_two_img">
		          	<a target="_blank" href="${goods_url!}">
		          	<span class="img_cspan">
		              <p>
		              <img width="150" height="150" src="${imageWebServer!}/static/images/common/loader.gif"
						   onerror="this.src='${S_URL}/static/images/common/good.jpg';"
						   original="${img!}" style="display: inline;">
		              </p>
		            </span>
		            </a>
		          </li>
		          <li class="sale_two_name">
					  <a target="_blank" href="${goods_url!}">${CommUtil.substring("${(xinShang.goodsName)!}",20)!}</a>
				  </li>
		          <li class="sale_two_price">售价：<strong>¥${(xinShang.goodsCurrentPrice)!}</strong></li>
			  </ul>
			</#list>
	      </div>
	      </div>

	      <!-- 满送商品 -->
	      <div id="index_sale_box_5" style="display: none;" class="index_sale_box">
	      <div id="index_sale_con_5" class="index_sale_con">
	      	  <#list dgs! as obj>
              <#assign goods_url="${S_URL}/goods?id=${(obj.d_goods.id)!}" />
              <#if ((config.second_domain_open)!false) == true>
              	<#assign goods_url="http://${(obj.d_goods.goods_store.store_second_domain)!}.${domainPath!}/goods?id=${(obj.d_goods.id)!}" />
			  </#if>
			  <#if (obj.d_goods.goods_main_photo)!??>
				  <#assign img="${imageWebServer!}/${(obj.d_goods.goods_main_photo.path)!}/${(obj.d_goods.goods_main_photo.name)!}_small.${(obj.d_goods.goods_main_photo.ext)!}" />
			  <#else>
                  <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
			  </#if>
	      	  <ul class="index_sale_two">
		          <li class="sale_two_img">
		          	<a target="_blank" href="${goods_url!}">
		          	<span class="img_cspan">
		              <p>
		              <img width="150" height="150" src="${imageWebServer!}/static/images/common/loader.gif"
						   onerror="this.src='${S_URL}/static/images/common/good.jpg';"
						   original="${img!}" style="display:inline;">
		              </p>
		            </span>
		            </a>
		          </li>
		          <li class="sale_two_name">
					  <a target="_blank" href="${goods_url!}">${CommUtil.substring("${(obj.d_goods.goods_name)!}",20)!}</a>
				  </li>
		          <li class="sale_two_price">售价：<strong>¥${(obj.d_goods.goodsCurrentPrice)!}</strong></li>
			  </ul>
			  </#list>
	      </div>
	      </div>

	      <!-- 人气商品 -->
	      <div id="index_sale_box_6" style="display: none;" class="index_sale_box">
	      	<div id="index_sale_con_6" class="index_sale_con">
	      	  <#list hots! as obj >
	          <#assign goods_url="${S_URL}/goods?id=${(obj.id)!}" />
	          <#if ((config.second_domain_open)!false) == true >
	          	<#assign goods_url="http://${(obj.goods_store.store_second_domain)!}.${domainPath!}/goods?id=${(obj.id)!}" />
			  </#if>
	          <#if (obj.goods_main_photo)!?? >
	          	<#assign img="${imageWebServer!}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}_small.${(obj.goods_main_photo.ext)!}" />
	          <#else>
	            <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
			  </#if>
	      	  <ul class="index_sale_two">
		          <li class="sale_two_img">
		          	<a target="_blank" href="${goods_url!}">
		          	<span class="img_cspan">
		              <p>
		              <img width="150" height="150" src="${imageWebServer!}/static/images/common/loader.gif"
						   onerror="this.src='${S_URL}/static/images/common/good.jpg';"
						   original="${img!}" style="display: inline;">
		              </p>
		            </span>
		            </a>
		          </li>
		          <li class="sale_two_name">
					  <a target="_blank" href="${goods_url!}">${CommUtil.substring("${(obj.goodsName)!}",20)!}</a>
				  </li>
		          <li class="sale_two_price">售价：<strong>¥${(obj.goodsCurrentPrice)!}</strong></li>
			  </ul>
			  </#list>
	        </div>
	      </div>
	    </div>


	    <div class="index_brand_right">
	      <div class="brand">
	        <h3><span><a target="_blank" href="${S_URL}/brand">更多</a></span>推荐品牌</h3>
	        <ul>
              	<#list gbs! as gb >
	            <li <#if gb_index gt 4 >style="display:none;"</#if>>
					<img width="142" height="66" src="${imageWebServer!}/${(gb.brandLogo.path)!}/${(gb.brandLogo.name)!}"/>
					<a href="${S_URL}/brand_goods?id=${(gb.id)!}" target="_blank">${(gb.name)!}</a>
				</li>
	            </#list>
            </ul>
	      </div>
	      <div class="brand_bottom_adv">
	      	<script src="${S_URL}/advert_invoke?id=262150"></script>
	      </div>
	    </div>
  	</div>

    <!-- 楼层 -->
    ${httpInclude.include("/floor")! }
    <div class="back_box_x">
	  <div class="back_floor" >
	    <ul>
           <li floor_id="floor_1"><a href="javascript:void(0);"><b style="display: none;">
           <img src="${S_URL}/static/images/common/fushi.jpg">
           </b><span>流行服饰</span></a></li>
           <li floor_id="floor_2"><a href="javascript:void(0);"><b style="display: block;">
           <img src="${S_URL}/static/images/common/diannao.jpg">
           </b><span>鞋包配饰</span></a></li>
           <li floor_id="floor_3"><a href="javascript:void(0);"><b style="display: block;">
           <img src="${S_URL}/static/images/common/shuma.jpg">
           </b><span>家电数码</span></a></li>
           <li floor_id="floor_4"><a href="javascript:void(0);"><b style="display: block;">
           <img src="${S_URL}/static/images/common/meizhuang.jpg">
           </b><span>运动户外</span></a></li>
         </ul>
	  </div>
	</div>

    <script src="${S_URL}/advert_invoke?id=5"></script>
    <div class="friendlink">
      <h1>合作伙伴</h1>
      <ul class="linkimg">
        <li> <a href="#" target="_blank">
			<img src="${imageWebServer!}/static/images/common/shopping.jpg" width="92" height="35" /></a></li>
        <#list img_partners! as info>
        <li><a href="${(info.url)!}" target="_blank">
			<img src="${imageWebServer!}/${(info.image.path)!}/${(info.image.name)!}" width="92" height="35" /></a>
		</li>
		</#list>
      </ul>
      <div class="linka">
		  <#list text_partners! as info >
			  <a href="${(info.url)!}" target="_blank">${(info.title)!}</a></#list>
	  </div>
    </div>

    <div class="shopping">
      <#list acs! as ac>
		  <div class="shopone">
			<h1>${(ac.classname)!}</h1>
			<ul>
				<#list (ac.articles)! as article>
				<#if (article.url)!="" >
				<#assign url="${(article.url)!}" />
				<#else>
				<#assign url="${S_URL}/article_${(article.id)!}" />
				</#if>
				<li><a  href="${url!}" target="_blank">${CommUtil.substring("${(article.title)!}",12)!}</a></li>
				</#list>
			</ul>
		  </div>
	  </#list>
    </div>

    <script>
		jQuery(document).ready(function(){
			jQuery(window).scroll(function(){
				var top = jQuery(document).scrollTop();
				if(top==0){
					jQuery("#back_box").hide();
					jQuery(".back_box_x").hide();
				}else{
					jQuery("#back_box").show();
					jQuery(".back_box_x").show();
				}
			});
			jQuery("#toTop").click(function(){
				jQuery('body,html').animate({scrollTop:0},1000);
				return false;
			});
		});
	</script>

  </div>
</div>
${httpInclude.include("/footer")}
</body>
</html>
