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
      function goods_count_adjust(cart_id,store_id,count){
          jQuery.post("${S_URL}/goods_count_adjust",{"cart_id":cart_id,"store_id":store_id,"count":count},function(data){
             if(data.error=="100"){
               jQuery("#goods_count_"+cart_id).val(count);
               jQuery("#goods_count_"+cart_id).attr("goods_count",count)
               jQuery("#goods_total_price_"+cart_id).html("¥"+data.goods_total_price);
               jQuery("#total_price_"+store_id).html(data.sc_total_price);
               jQuery("#top_total_price_"+store_id).html(data.sc_total_price);
             }
             if(data.error=="200"){
               alert("库存不足，请重新选择数量！");
               jQuery("#goods_count_"+cart_id).val(jQuery("#goods_count_"+cart_id).attr("goods_count"));
             }
             if(data.error=="300"){
               alert("团购库存不足，请重新选择数量！");
               jQuery("#goods_count_"+cart_id).val(jQuery("#goods_count_"+cart_id).attr("goods_count"));
             }
           },"json");
      }
      function confirm_cart(sc_id){
        jQuery("#cart_"+sc_id).hide();
        jQuery("#cart_"+sc_id).attr("status","submit");
        if(jQuery("form[id^=cart_][status=no_submit]").length==0){
           jQuery("#cart_"+sc_id).attr("target","_self");
        }
        jQuery("#cart_"+sc_id).submit();
      }
    jQuery(document).ready(function(){
      jQuery("a[id^=favorite_]").click(function(){
      <#if user!?? >
        <#if ((user.id)!0)==((obj.goods_store.user.id)!1)>
          alert("不能收藏自己的商品");
        <#else>
          var id=jQuery(this).attr("id").substring(9);
          jQuery.post("${S_URL}/add_goods_favorite",{"id":id},function(data){
             if(data==0){
                alert("收藏成功!");
             }
             if(data==1){
                alert("您已经收藏过该商品!");
             }
          },"text");
        </#if>
      <#else>
       if(confirm("登录后才可收藏商品，现在登录？"))
         window.location.href="${S_URL}/user/login?url=${S_URL}/goods_cart1"
      </#if>
      });
      jQuery("a[id^=count_down]").click(function(){
        var cart_id=jQuery(this).attr("cart_id");
        var count=jQuery("#goods_count_"+cart_id).val();
        var store_id=jQuery(this).attr("store_id");
        if(count>1){
          count--;
          goods_count_adjust(cart_id,store_id,count)
        }
      });
      jQuery("a[id^=count_up]").click(function(){
        var cart_id=jQuery(this).attr("cart_id");
        var count=jQuery("#goods_count_"+cart_id).val();
        var store_id=jQuery(this).attr("store_id");
        if(count>0){
          count++;
          goods_count_adjust(cart_id,store_id,count);
        }
      });
      jQuery("input[id^=goods_count_]").keyup(function(){
        var cart_id=jQuery(this).attr("cart_id");
        var count=jQuery("#goods_count_"+cart_id).val().replace(/\D/g,'');
        if(count==""){
           count=1;
        }
        var store_id=jQuery(this).attr("store_id");
        if(count>0){
          goods_count_adjust(cart_id,store_id,count);
        }
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
    });
    </script>
<head>
<body>
${httpInclude.include("/top")}
<div class="main"> ${httpInclude.include("/head")}
  <div class="Steps_box">
    <div class="Steps">
      <ul>
        <li class="this">1.查看购物车</li>
        <li>2.确认订单信息</li>
        <li>3.付款到卖家</li>
        <li>4.确认收货</li>
        <li class="last">5.评价</li>
      </ul>
    </div>
    <#list cart as sc>
    <form status="no_submit" method="post" name="cart_${(sc.storeId)!}" target="_blank"
          id="cart_${(sc.storeId)!}" action="${S_URL}/goods_cart2">
      <div class="h1">
          <span class="h1_l">店铺名称：<a href="${S_URL}/store?id=${(sc.storeId)!}" target="_blank">${(sc.store.store_name)!}</a>
          </span>
          <span class="h1_r">商品总价(不含运费)：<b>¥<span id="top_total_price_${(sc.storeId)!}">${(sc.totalPrice)!}</span></b>
              <a href="javascript:void(0);"  onclick="confirm_cart('${(sc.storeId)!}');">结算</a>
          </span>
      </div>
      <div class="table">
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td width="55%" align="center" class="title">店铺商品</td>
            <td width="8%" align="center" class="title">单价</td>
            <td width="14%" align="center" class="title">数量</td>
            <td width="8%" align="center" class="title">小计（元）</td>
            <td  align="center" class="title">操作</td>
          </tr>
          <#list (sc.gcs)! as obj >
          <tr goods_list="goods_info_${(obj.id)!}" id="${(obj.id)!}">
            <td class="baby">
            <#if (obj.goods.goods_main_photo)!??>
                <#assign img="${RES_URL}/${imageWebServer!}/${(obj.goods.goods_main_photo.path)!}/${(obj.goods.goods_main_photo.name)!}_small.${(obj.goods.goods_main_photo.ext)!}" />
            <#else>
                <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
            </#if>
            <img src="${img!}" width="65" height="65" />
              <p>
                <a href="${S_URL}/goods?id=${(obj.goodsId)!}" target="_blank">${CommUtil.substring("${(obj.goods.goodsName)!}",42)}</a>
                <#if ((obj.goods.groupBuy)!0)==2> <span style="color:#F00">(团购)</span> </#if>
                <#if ((obj.goods.bargainStatus)!0)==2> <span style="color:#F00">(特价)</span> </#if>
                <#if ((obj.cartType)!'')=="combin">
                    <div class="baby_gp">
                        <a href="javascript:void(0);" style="color:#F00;">【组合商品】</a>
                        <div class="baby_group" style="display:none;">
                            <div class="baby_group_box">
                                <ul class="group_ul">
                                  <#list goodsViewTools.query_combin_goods("${(obj.goodsId)!}") as info >
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
                <#if ((obj.goods.deliveryStatus)!0)==2><span style="color:#F00">[买就送]</span> </#if><br />
                <#list (obj.gsps)! as gsp >
                    <#if (gsp.spec)!??><span class="color">${(gsp.spec.name)!}: ${(gsp.value)!}</span><br/></#if>
                </#list>

              </p></td>
            <td align="center">¥${(obj.price)!}</td>
            <td class="input" align="center">
                <span>
                    <a href="javascript:void(0);" id="count_down_${(obj.id)!}" cart_id="${(obj.id)!}" store_id="${(sc.storeId)!}">
                    <img src="${imageWebServer!}/static/styles/system/front/default/images/jian.jpg" width="12" height="12" /></a>
                </span>
                <input name="goods_count_${(obj.id)!}" type="text" id="goods_count_${(obj.id)!}" value="${(obj.count)!}" cart_id="${(obj.id)!}" store_id="${(sc.storeId)!}" goods_count="${(obj.count)!}" />
                <span>
                    <a href="javascript:void(0);" id="count_up_${(obj.id)!}" cart_id="${(obj.id)!}" store_id="${(sc.storeId)!}">
                        <img src="${imageWebServer!}/static/styles/system/front/default/images/add.jpg" width="12" height="12" />
                    </a>
                </span>
            </td>
            <#assign total_price= obj.count * obj.price />
            <td align="center"><strong class="orange" id="goods_total_price_${(obj.id)!}">¥${total_price!}</strong></td>
            <td align="center"><a href="javascript:void(0);" class="cart_common" id="favorite_${(obj.goodId)!}">收藏</a>
                <a href="javascript:void(0);" dialog_uri="${S_URL}/goods_share?goods_id=${(obj.goodId)!}" dialog_title="分享商品"
                   dialog_width="450" dialog_height="400" dialog_id="goods_share" class="cart_common">分享</a>
                <a href="javascript:void(0);" onclick="cart_remove('${(obj.id)!}','${(sc.storeId)!}');" class="cart_common">删除</a>
            </td>
          </tr>
          </#list>
        </table>
      </div>
      <div class="h2">
          <span class="h2_r"><em>商品总价(不含运费)：</em>
              <b>¥<strong class="orange" id="total_price_${(sc.storeId)!}">${(sc.totalPrice)!}</strong></b>
              <input name="store_id" type="hidden" id="store_id" value="${(sc.storeId)!}" />
              <a href="javascript:void(0);"  onclick="confirm_cart('${(sc.storeId)!}');">结算</a>
          </span>
      </div>
    </form>
    </#list>
    <div class="car_nogoods" <#if cart!?size gt 0 > style="display:none;"</#if> >
      <div class="shopcar">
        <dl>
          <dt><img src="${imageWebServer!}/static/styles/system/front/default/images/wemall_cart.jpg" width="130" height="118" /></dt>
          <dd>
            <h1>您的购物车还没有商品</h1>
            <span><a href="${S_URL}/index">马上去购物>></a></span>
              <span><a href="${S_URL}/buyer/order">查看自己的订单>></a></span>
          </dd>
        </dl>
      </div>
    </div>

    <div class="tm_hot">
        <h1><span class="tm_h1_left">商家热卖</span><span class="tm_h1_right"><a href="${S_URL}/ztc_goods_list" target="_blank">更多>></a>
        </span></h1>
        <ul>
            <#list ztc_goods! as ztc >
                <#assign ztc_img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                <#if (ztc.goods_main_photo)!?? >
                    <#assign ztc_img="${RES_URL}/${imageWebServer!}/${(ztc.goods_main_photo.path)!}/${(ztc.goods_main_photo.name)!}_small.${(ztc.goods_main_photo.ext)!}" />
                </#if>
                <li><a href="${S_URL}/goods?id=${(ztc.id)!}" target="_blank">
                    <img src="${ztc_img!}" width="160" height="160" />
                </a>
                    <strong>¥ ${(ztc.storePrice)!}</strong>
                    <span><a href="${S_URL}/goods?id=${(ztc.id)!}" target="_blank">${(ztc.goodsName)!}</a></span>
                </li>
            </#list>
        </ul>
    </div>
  </div>
  ${httpInclude.include("/footer")}
</div>
</body>
</html>
