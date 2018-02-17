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
  jQuery(".brand_sort a").removeClass("this");
  jQuery(".brand_sort a i").removeClass().css("padding-right","0px");
  jQuery(".brand_sort a[id=${orderBy!}]").addClass("this");
  jQuery(".brand_sort a[id=${orderBy!}]").attr("orderType","${orderType!}");
  <#if orderType! =="asc" >
    jQuery(".brand_sort a[id=${orderBy!}] i").css("padding-right","10px").addClass("sort_b");
  </#if>
  <#if orderType! =="desc" >
    jQuery(".brand_sort a[id=${orderBy!}] i").css("padding-right","10px").addClass("sort_a");
  </#if>
  jQuery(".brand_sort a").click(function(){
    var orderBy=jQuery(this).attr("id");
	var orderType=jQuery(this).attr("orderType");
	if(orderType=="asc"){
	  orderType="desc";
	}else orderType="asc";
	jQuery("#orderBy").val(orderBy);
	jQuery("#orderType").val(orderType);
	jQuery("#ListForm").submit();
  });
});
function query_price(){
  var store_price_begin=jQuery("#store_price_begin").val();
  var store_price_end=jQuery("#store_price_end").val();
  jQuery("#begin_price").val(store_price_begin);
  jQuery("#end_price").val(store_price_end);
  jQuery("#ListForm").submit();
}
</script>
</head>
<body>
  ${httpInclude.include("/top")}
  ${httpInclude.include("/store_head?store_id=${(store.storeId)!}")}
  <#assign banner="${S_URL}/static/images/shop/${(store.template)!}/images/banner.jpg" />
  <#if (store.banner)!??>
    <#assign banner="${RES_URL}/${(store.banner.path)!}/${(store.banner.name)!}" />
  </#if>
  <div class="banner_width">
      <div class="shopbanner"><img src="${banner!}"  width="1200px" /></div>
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
          <div class="shop_index"> ${httpInclude.include("/store_left?id=${(store.storeId)!}")}
              <div class="shopindex_right">
                  <div class="regoods">
                      <#if ugc!?? ><#assign title="${(ugc.className)!}" /></#if>
                      <#if recommend! =="true" ><#assign title="推荐商品" /></#if>
                      <h1><span>${title!}</span></h1>
                      <div class="brand_ser">
                          <div class="brand_serin"><span class="brand_px"><b>排序：</b>
                              <div class="brand_sort">
                                  <a href="javascript:void(0);" class="this" id="addTime">
                                      <i class="sort_a">默认</i>
                                  </a>
                                  <a href="javascript:void(0);" id="store_price"><i>价格</i></a>
                                  <a href="javascript:void(0);" id="goods_salenum"><i>销量</i></a>
                                  <a href="javascript:void(0);" id="goods_click"><i>人气</i></a>
                              </div>
                          </span><span class="brand_money"><i>价格：</i><b class="bran_int">
                              <input name="store_price_begin" id="store_price_begin" type="text" />
                          </b><b>~</b><b class="bran_int">
                              <input name="store_price_end" id="store_price_end" type="text" />
                          </b><b class="bran_btn">
                              <input name="input" value="提交" style="cursor:pointer;" type="button" id="input" onclick="query_price();" />
                          </b></span>
                          </div>
                      </div>
                      <#if objs!??>
                      <form action="${S_URL}/goods_list" method="post" id="ListForm">
                      <div class="smallgoods">
                          <#list objs! as goods >
                            <#if (goods.goods_main_photo)!??>
                            <#assign img="${imageWebServer!}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}" />
                            <#else>
                            <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                            </#if>
                              <ul>
                                  <li class="goodsimgs">
                                      <a href="${S_URL}/goods_${goods.id}" target="_blank">
                                          <span class="goods_sp_span">
                                              <p><img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
                                                      onerror="this.src='${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';" width="28" height="28"/>
                                              </p>
                                          </span>
                                      </a>
                                  </li>
                                  <li class="goodslook"><a href="${S_URL}/goods_${(goods.id)!}" target="_blank" class="look">查看详情</a>
                                      <strong>¥${(goods.storePrice)!}</strong>
                                  </li>
                                  <li class="goodsnames">
                                      <a href="${S_URL}/goods_${(goods.id)!}" target="_blank">
                                          ${CommUtil.substring("${(goods.goodsName)!}",22)}</a>
                                      <#if ((goods.groupBuy)!0)==2 >
                                          <span style="padding:3px 3px;color:#FFF;background:#F00">团购</span>
                                      </#if>
                                      <#if ((goods.activityStatus)!0)==2 >
                                          <span style="padding:3px 3px;color:#FFF;background:#F00">活动</span>
                                      </#if>
                                      <#if ((goods.bargainStatus)!0)==2 >
                                          <span style="padding:3px 3px;color:#FFF;background:#F00">特价</span>
                                      </#if>
                                      <#if ((goods.combinStatus)!0)==2 >
                                          <span style="padding:3px 3px;color:#FFF;background:#F00">组合</span>
                                      </#if>
                                      <#if ((goods.deliveryStatus)!0)==2 >
                                          <span style="padding:3px 3px;color:#FFF;background:#F00">买就送</span>
                                      </#if>
                                  </li>
                                  <li class="recentgoodsok">最近成交<strong>${(goods.goodsSalenum)!}</strong>笔</li>
                              </ul>
                          </#list>
                      </div>
                      <div class="shop_page"><span>
                          <input name="orderBy" type="hidden" id="orderBy" value="${orderBy!}" />
                          <input name="orderType" type="hidden" id="orderType" value="${orderType!}" />
                          <input name="store_id" type="hidden" id="store_id" value="${(store.storeId)!}" />
                          <input name="gc_id" type="hidden" id="gc_id" value="${(ugc.id)!}" />
                          <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />
                          <input name="recommend" type="hidden" id="recommend" value="${recommend!}" />
                          <input name="begin_price" type="hidden" id="begin_price" value="${begin_price!}" />
                          <input name="end_price" type="hidden" id="end_price" value="${end_price!}" />
                      ${gotoPageFormHTML!}</span>
                      </div>
                      </form>
                      <#else>
                      <div class="sigh">
                          <span>
                          <img src="${imageWebServer!}/static/styles/system/front/default/images/sigh.png" width="120" height="109" /></span>
                          <b>对不起，没有对应商品信息！</b>
                      </div>
                      </#if>
                  </div>
              </div>
          </div>
      </div>
      ${httpInclude.include("/footer")} </div>
</body>
</html>
