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
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.lazyload.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>

<script>
jQuery(document).ready(function(){
  jQuery(".smallgoods .goodsimgs img").lazyload({effect:"fadeIn",width:178,height:170});
});
</script>
</head>
<body>
    ${httpInclude.include("/top")}
    ${httpInclude.include("/store_head?store_id=${(store.storeId)!}")}
    <#assign banner="${S_URL}/static/images/shop/${(store.template)!}/images/banner.jpg" />
    <#if (store.banner)!??>
      <#assign banner="${S_URL}/${(store.banner.path)!}/${(store.banner.name)!}" />
    </#if>
    <div class="banner_width">
        <div class="shopbanner"><img src="${RES_URL}${banner!}" width="1200px" /></div>
    </div>
    <div class="nav_width">
        <div class="main">
            <div class="nav_bg">
                <div class="shopnav">
                ${httpInclude.include("/store_nav?id=${(store.storeId)!}&goods_list=true")}
                    <div class="shopnavs"></div>
                </div>
            </div>
        </div>
        <div class="navbotm"></div>
    </div>

    <div class="main">
        <div class="shop">
            <div class="shop_index">
            ${httpInclude.include("/store_left?id=${(store.storeId)!}")}
                <form method="post" id="ListForm">
                    <div class="shopindex_right">
                        <div class="regoods">
                            <h1>包含“${keyword!}”搜索结果</h1>
                        <#if objs!?size gt 0 >
                            <div class="smallgoods">
                            <#list objs! as goods >
                              <#if (goods.goods_main_photo)!??>
                                  <#assign img="${imageWebServer!}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}" />
                              <#else>
                                  <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                              </#if>
                              <ul>
                                <li class="goodsimgs">
                                    <a href="${S_URL}/goods?id=${(goods.id)!}" target="_blank">
                                        <span class="goods_sp_span"><p>
                                            <img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
                                                 onerror="this.src='${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';"
                                                 width="28" height="28"/></p>
                                        </span>
                                    </a>
                                </li>
                                <li class="goodslook">
                                    <a href="${S_URL}/goods?id=${(goods.id)!}" target="_blank" class="look">查看详情</a>
                                    <strong>¥${(goods.storePrice)!}</strong></li>
                                <li class="goodsnames">
                                    <a href="${S_URL}/goods?id=${(goods.id)!}" target="_blank">
                                        ${CommUtil.substring("${(goods.goodsName)!}",28)}</a></li>
                                <li class="recentgoodsok">最近成交
                                    <strong>${(goods.goodsSalenum)!}</strong>笔</li>
                              </ul>
                            </#list>
                            </div>
                        <#else>
                            <div class="sigh">
                                <span>
                                    <img src="${imageWebServer!}/static/styles/shop/${(store.template)}/images/sigh.jpg"
                                         width="120" height="109" />
                                </span>
                                <b>对不起，没有对应商品信息！</b>
                            </div>
                        </#if>
                        <div class="shop_page">
                            <span>
                            <input name="store_id" type="hidden" id="store_id" value="${(store.storeId)!}" />
                            <input name="keyword" type="hidden" id="keyword" value="${keyword!}" />
                            <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />
                            ${gotoPageFormHTML!}
                            </span>
                        </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        ${httpInclude.include("/footer")}
</body>
</html>
