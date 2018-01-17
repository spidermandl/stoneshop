<#assign S_URL=request.contextPath  />
<div class="top" style="">
  <div class="top_page">
    <div class="pageright" id="site-nav">
      <ul class="quick-menu">
        <li class="mytaobao menu-item menupx">
          <div class="menu"> <a class="menu-hd" href="${S_URL}/buyer/index" rel="nofollow">我的订单<b></b></a>
            <div class="menu-bd">
              <div class="menu-bd-panel">

                <div>
	                <!-- <a href="${S_URL}/buyer/order.htm" rel="nofollow">已买到的宝贝</a>
	                <a href="${S_URL}/buyer/index.htm?type=4"  rel="nofollow">店铺动态</a>
	                <a href="${S_URL}/buyer/index.htm?type=2"  rel="nofollow">好友动态</a> -->
                  <a href="${S_URL}/buyer/order.htm?order_status=order_submit" rel="nofollow">待支付</a>
		          <a href="${S_URL}/buyer/order.htm?order_status=order_shipping" rel="nofollow">待收货</a>
		          <a href="${S_URL}/buyer/order.htm?order_status=order_receive" rel="nofollow">待评价</a>
                </div>
              </div>
            </div>
          </div>
        </li>
		<li class="mytaobao menu-item menupx">
          <div class="menu"> <a class="menu-hd" href="${S_URL}/seller/index.htm" rel="nofollow">商家支持<b></b></a>
            <div class="menu-bd">
              <div class="menu-bd-panel">
                <div>
                <a href="${S_URL}/seller/index.htm" rel="nofollow">商家中心</a>
                <a href="${S_URL}/seller/order.htm"  rel="nofollow">商家订单</a>
                <a href="${S_URL}/seller/goods.htm" rel="nofollow">商家商品</a>
                <#if (user.store)!??>
                <a href="${S_URL}/store_${(user.store.id)!}.htm" rel="nofollow">我的店铺</a>
                <#else>
                <a href="${S_URL}/seller/store_create_first" rel="nofollow">商家入驻</a>
                </#if>
                 </div>
              </div>
            </div>
          </div>
        </li>
        <script>
		 var goods_count=${cart!?size};
		 var total_price=0;
		 <#assign total_price=0 />
		 <#list cart as gc >
		  <#if (gc.goods.group_buy)!0 == 2 >
		    <#assign total_price = gc.goods.group_goods.gg_price * gc.count + total_price />
		  <#else>
		    <#assign total_price= gc.goods.store_price*gc.count+total_price />
          </#if>
         </#list>
		function cart_remove(id,store_id){
           jQuery.post('${S_URL}/remove_goods_cart.htm',{"id":id,"store_id":store_id},function(data){
           jQuery("div[class=table] tr[id="+id+"]").remove();
		   jQuery(".wemall[id="+id+"]").remove();
		   jQuery(".shopp_ingtop[id="+id+"]").remove();
           jQuery("#cart_goods_count_top").html(data.count);
           jQuery("#cart_goods_price_top").html(data.total_price);
		   jQuery("#top_total_price_"+store_id).html(data.total_price);
           if(store_id!=""){
              jQuery("#total_price_"+store_id).html(data.sc_total_price);
           }
		   if( jQuery("form[id=cart_"+store_id+"]").find("tr[goods_list^=goods_info]").length==0){
		  	 jQuery("form[id=cart_"+store_id+"]").remove();
		   }
           if(jQuery("tr[goods_list^=goods_info]").length==0){
              jQuery(".car_nogoods").show();
           }
         },"json");
       }
	    jQuery(document).ready(function(){
		   jQuery("#cart_goods_top_menu").mouseover(function(){
			  jQuery.ajax({type:'POST',url:'${S_URL}/cart_menu_detail.htm',data:'',
						   beforeSend:function(){
							     jQuery("#cart_goods_top_info").empty().html('<div class="menu-bd-panel"><div style="text-align:center;"><img src="${S_URL}/resources/style/common/images/loader.gif" /></div></div>');
							     jQuery("#cart_goods_top_info").show();
							   },
						  success:function(data){
							     jQuery("#cart_goods_top_info").empty();
								 jQuery("#cart_goods_top_info").html(data);
							   }
						});
		   });
		   jQuery("#cart_menu").mouseleave(function(){
			  jQuery("#cart_goods_top_info").hide();
		   });
		});
	   </script>
        <li class="search menu-item menupx">
          <div class="menu" id="cart_menu">
              <span class="menu-hd" id="cart_goods_top_menu">
                  <s></s>购物车
                  <span id="cart_goods_count_top" class="top_car">${cart!?size}</span>种商品
                  <b></b>
              </span>
            <div class="menu-bd" id="cart_goods_top_info">
             <div class="menu-bd-panel">
                <div style="text-align:center;">
                    <img src="${S_URL}/static/images/common/loader.gif" />
                </div>
             </div>

            </div>
          </div>
        </li>
        <li class="menupx"><a href="${S_URL}/buyer/message.htm">站内短信
            <#if msgs!?size gt 0 >
                (<span style="color:#FF3300;">${(msgs.size())!}</span>)
            </#if></a></li>

		<li class="mytaobao menu-item menupx">
          <div class="menu"> <a class="menu-hd" href="${S_URL}/buyer/favorite_goods.htm" rel="nofollow">收藏夹<b></b></a>
            <div class="menu-bd">
              <div class="menu-bd-panel">
                <div>
                <a href="${S_URL}/buyer/favorite_goods.htm" rel="nofollow">收藏商品</a>
                <a href="${S_URL}/buyer/favorite_store.htm"  rel="nofollow">收藏店铺</a>

                 </div>
              </div>
            </div>
          </div>
        </li>

        <#if CommUtil.indexOf("${(user.userRole)!}","ADMIN") gte 0 >
		<li class="menupx"><a href="${S_URL}/admin/index.htm" target="_blank">管理后台</a></li>
        </#if>
        <#assign navs= navTools.queryNav(-1,-1) />
		<#if navs!?size gt 0 >
        <li class="menupx"><a href="${S_URL}/articlelist_help.htm">帮助中心</a></li>
		<#else>
        <li class="menupx" style="background:none;"><a href="${S_URL}/articlelist_help">帮助中心</a></li>
        </#if>
		<#if navs!?size gt 0 >
		<li class="mytaobao menu-item menupx" style="background:none;">
          <div class="menu"> <a class="menu-hd" href="${S_URL}/buyer/index" rel="nofollow">更多链接<b></b></a>
            <div class="menu-bd" style="height:auto;">
              <div class="menu-bd-panel">
                <div>
                <#list navs as nav>
                    <#--<#assign map= navTools.genericURL("${S_URL}","${(nav.id)!}") />-->
                    <#--<#assign url=map.get("url") />-->
                    <#assign url = S_URL+"/"+nav.url />
                    <a href="${url!}"
                       <#if nav.newWin == 1 >target="_blank"</#if>
                       rel="nofollow">${(nav.title)!}
                    </a>
                </#list>
                </div>
              </div>
            </div>
          </div>
        </li>
        </#if>
      </ul>
    </div>
    <div class="pageleft">
    <#if user?? >
        <span>${CommUtil.substring("${(user.userName)!}",12)}
            您好,欢迎来到${CommUtil.substring("${(config.websiteName)!}",30)}！</span>
        <a href="${S_URL}/wemall_logout" class="lightblue">[退出]</a>
    <#else>
        <span>亲，欢迎来到${(config.websiteName)!}！</span>
        <span class="pxlr"><a href="${S_URL}/user/login.htm" class="lightblue">登录</a></span><span class="pxlr">或</span>
        <span class="pxlr"><a href="${S_URL}/register.htm" class="lightblue">注册</a></span>
    </#if>
    </div>
  </div>
</div>
