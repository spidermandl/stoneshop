<#assign S_URL=request.contextPath />

<div class="public-top-layout w">
    <div class="topbar">
        <div class="user-entry">
            您好<span><@shiro.guest>游客</@shiro.guest>
        <@shiro.user> <@shiro.principal property="loginName"/></@shiro.user>
    </span>
            ，欢迎来到      <a alt="首页" title="首页" href="#"><span>电商系统</span></a>
        <@shiro.guest>
            <span>[<a href="${S_URL}/login.html">登录</a>]</span>
            <span>[<a href="${S_URL}/register.html">注册</a>]</span>
        </@shiro.guest>
        <@shiro.user>
            <span>[<a href="${S_URL}/logout.html">退出</a>]</span>
            <@shiro.hasRole name="seller">
            <span class="seller-login">
                <a title="登录商家管理中心" target="_blank" href="${SELLER_URL}/">
                    <i class="icon-signin"></i>
                    商家管理中心
                </a>
            </span>
            </@shiro.hasRole>
            <@shiro.lacksRole name="seller">
            <span class="seller-login">
                <a title="申请店铺" target="_blank" href="${SELLER_URL}/store_join/agreement.html">
                    <i class="icon-signin"></i>
                    申请店铺
                </a>
            </span>
            </@shiro.lacksRole>
            <span class="seller-login">
                <a title="个人中心" target="_blank" href="${S_URL}/member/base_set.html">
                    <i class="icon-signin"></i>
                    个人中心
                </a>
            </span>
        </@shiro.user>
        </div>

        <div class="quick-menu">
            <dl>
                <dt><a href="#">我的订单</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="#">待付款订单</a></li>
                        <li><a href="#">待确认收货</a></li>
                        <li><a href="#">待评价交易</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="#">商家支持</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${SELLER_URL}">商家中心</a></li>
                        <li><a href="${SELLER_URL}/order">商家订单</a></li>
                        <li><a href="#">商家商品</a></li>
                        <@shiro.hasRole name="seller">
                        <li><a href="${SELLER_URL}/store/store_set" rel="nofollow">我的店铺</a></li>
                        </@shiro.hasRole>
                        <@shiro.lacksRole name="seller">
                        <li><a href="${SELLER_URL}/store_join/store_create_first" rel="nofollow">商家入驻</a></li>
                        </@shiro.lacksRole>
                    </ul>
                </dd>
            </dl>
            <script>
                var goods_count=${cart!?size};
                var total_price=0;
                <#assign total_price=0 />
                <#list cart_goods! as gc >
                    <#if ((gc.goods.groupBuy)!0) == 2 >
                        <#assign total_price = gc.goods.groupGoods.ggPrice * gc.count + total_price />
                    <#else>
                        <#assign total_price= gc.goods.storePrice*gc.count+total_price />
                    <#--<#assign total_price= 0 />-->
                    </#if>
                </#list>
                function cart_remove(id,store_id){
                    jQuery.post('${S_URL}/remove_goods_cart',{"id":id,"store_id":store_id},function(data){
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
                        jQuery.ajax({type:'POST',url:'${S_URL}/cart_menu_detail',data:'',
                            beforeSend:function(){
                                jQuery("#cart_goods_top_info").empty().html(
                                        '<div class="menu-bd-panel"><div style="text-align:center;">' +
                                        '<img src="${S_URL}/static/images/common/loader.gif" /></div></div>');
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
            <dl class="cart">
                <dt class="search menu-item">
                    <div class="menu" id="cart_menu">
                      <span class="menu-hd" id="cart_goods_top_menu">
                          <s></s>
                          <c>
                              购物车<span id="cart_goods_count_top" class="top_car">${cart_goods!?size}</span>种商品
                          </c>
                          <i></i>
                          <#--<b></b>-->
                      </span>
                        <div class="menu-bd" id="cart_goods_top_info">
                            <div class="menu-bd-panel">
                                <div style="text-align:center;">
                                    <img src="${S_URL}/static/images/common/loader.gif" />
                                </div>
                            </div>
                        </div>
                    </div>
                </dt>
            </dl>
            <dl>
                <dt><a href="${S_URL}/buyer/message">站内短信</a></dt>
            </dl>
            <dl>
                <dt><a href="#">收藏夹</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${S_URL}/buyer/favorite_goods">商品收藏</a></li>
                        <li><a href="${S_URL}/buyer/favorite_store">店铺收藏</a></li>
                    </ul>
                </dd>
            </dl>
            <@shiro.hasRole name="admin">
            <dl>
                <dt><a href="${S_URL}/admin/index">管理后台</a><i></i></dt>
            </dl>
            </@shiro.hasRole>
            <#assign navs= navTools.queryNav(-1,-1) />
            <#if navs!?size gt 0 >
            <dl>
                <dt><a href="${S_URL}/articlelist_help">帮助中心</a></dt>
            </dl>
            <#else>
            <dl>
                <dt style="background:none;"><a href="${S_URL}/articlelist_help">帮助中心</a></dt>
            </dl>
            </#if>
            <#if navs!?size gt 0 >
            <dl>
                <dt><a href="${S_URL}/buyer/index">更多链接</a><i></i></dt>
                <dd>
                    <ul>
                        <#list navs as nav>
                        <#--<#assign map= navTools.genericURL("${S_URL}","${(nav.id)!}") />-->
                        <#--<#assign url=map.get("url") />-->
                            <#assign url = S_URL+"/"+nav.url />
                        <li><a href="${url!}"
                               <#if nav.newWin == 1 >target="_blank"</#if>
                               rel="nofollow">${(nav.title)!}
                        </a></li>
                        </#list>
                    </ul>
                </dd>
            </dl>
            </#if>
            <#--<dl>-->
                <#--<dt>客户服务<i></i></dt>-->
                <#--<dd>-->
                    <#--<ul>-->
                        <#--<li><a href="#">帮助中心</a></li>-->
                        <#--<li><a href="#">售后服务</a></li>-->
                        <#--<li><a href="#">客服中心</a></li>-->
                    <#--</ul>-->
                <#--</dd>-->
            <#--</dl>-->
        </div>
    </div>
</div>
<script type="application/javascript">
  $(function(){
    $(".quick-menu dl").hover(function() {
              $(this).addClass("hover");
            },
            function() {
              $(this).removeClass("hover");
            });
  });
</script>
