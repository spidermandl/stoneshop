<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>热卖商品 - ${(config.poweredby)!}</title>
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
    <link href="${S_URL}/static/styles/system/front/default/css/index.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/system/front/default/css/goodshidden.css" type="text/css" rel="stylesheet" />
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.lazyload.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script>
jQuery(document).ready(function(){
 jQuery(".pro_ul_one img").lazyload({effect:"fadeIn",width:220,height:220});
 jQuery(".product_one").mouseover(function(){
	 jQuery(".product_border").removeClass("this");
	 jQuery(this).find(".product_border").addClass("this");
	 });
});
</script>
</head>

<body>
${httpInclude.include("/top")}
<div class="main">
    ${httpInclude.include("/head?type=${type!}")}
    ${httpInclude.include("/nav1")}
  <div class="index">
    <div class="position">当前位置：<a href="${S_URL}/index" >首页</a> > <span>热卖商品</span></div>
    <div class="index2">
      <form action="${S_URL}/ztc_goods_list"  method="post" id="ListForm">
        <div class="product_list_hot">
            <#if objs!?? >
                <#list objs as obj >
                <#if  (obj.goods_main_photo)!??>
                  <#assign img="${imageWebServer}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}" />
                <#else>
                  <#assign img="${imageWebServer}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                </#if>
                <div class="product_one">
                    <div class="product_border <#if obj_index==1 > this </#if>">
                        <ul class="product_ul">
                            <li class="pro_ul_one"><a href="${S_URL}/goods?id=${(obj.id)!}" target="_blank">
                                <img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
                                     onerror="this.src='${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';" width="28" height="28"/>
                            </a></li>
                            <li class="pro_price">
                                <i>¥${(obj.goodsPrice)!}</i>
                                <span><strong>¥</strong>${(obj.goodsCurrentPrice)!}</span>
                            </li>
                            <li class="pro_name_s">
                                <a href="${S_URL}/goods?id=${(obj.id)!}" target="_blank">${CommUtil.substring("${(obj.goodsName)!}",30)}</a>
                            </li>
                            <li class="pro_month">月销量
                                <strong>${(obj.goodsSalenum)!}</strong>|<a href="${S_URL}/goods?id=${(obj.id)!}" target="_blank">
                                    累计评价:${(obj.evaluates)!?size}</a></li>
                            <li class="pro_shop_name">
                                <a href="${S_URL}/store?id=${(obj.storeId)!}" target="_blank"> ${(obj.goods_store.store_name)!} </a></li>
                        </ul>
                    </div>
                </div>
              </#list>
          <#else>
          <div class="starshop_list">
            <div class="sigh">
                <span>
                    <img src="${imageWebServer!}/static/styles/shop/default/images/sigh.png" width="120" height="109" />
                </span> <b>对不起，没有对应的数据!</b> </div>
          </div>
          </#if>
        </div>
        <div class="fenye" style="text-align:left; width:600px;">
          <div class="fenyes">
            <blockquote>
              <p>
                <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />
                ${gotoPageFormHTML!}</p>
            </blockquote>
          </div>
        </div>
      </form>
    </div>
  </div>
  ${httpInclude.include("/footer")} </div>
</body>
</html>
