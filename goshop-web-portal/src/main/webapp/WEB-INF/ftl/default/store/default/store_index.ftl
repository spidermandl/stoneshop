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
    <script src="${S_URL}/static/scripts/jquery/jcarousellite_1.0.1.min.js"></script>
    <#assign store_second_url="${S_URL}" />

    <!-- -->
    <#if second_domain_view?? >
      <#assign store_second_url="http://${(store.storeDomain)!}.${domainPath!}" />
    </#if>

    <script src="${store_second_url}/static/scripts/jquery.shop.common.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script>
    jQuery(document).ready(function(){
      jQuery(".smallgoods .goodsimgs img").lazyload({effect:"fadeIn",width:178,height:170});
      jQuery(".bigshopimg .img1").addClass("this");
      //
      jQuery(".bigshopimg").jCarouselLite({
         btnGo:[".img1",".img2",".img3",".img4",".img5"],
         auto: 3000,
         speed: 600,
         vertical:false,
         visible:1,
         start:1,
         afterEnd:function(a){
          var index=jQuery(a[0]).index();
          <#if (store.slides)!?size gt 0 >
            if(index>"${(store.slides)?size}") index=1;
          <#else>
            if(index>4)index=1;
          </#if>
          jQuery(".bigshopimg a").removeClass("this");
          jQuery(".bigshopimg .img"+index).addClass("this");
         }
      });
    });
    </script>
</head>
<body>
    ${httpInclude.include("/top")}
    ${httpInclude.include("/store_head?store_id=${(store.storeId)!}")}
      <#assign banner="${S_URL}/resources/style/shop/${(store.template)!}/images/banner.jpg" />
      <#if (store.banner)!??>
          <#assign banner="${S_URL}/${(store.banner.path)!}/${(store.banner.name)!}" />
      </#if>
    <div class="banner_width">
      <div class="shopbanner"><img src="${banner!}" width="1200px" /></div>
    </div>
    <div class="nav_width">
      <div class="main">
       <div class="nav_bg">
        <div class="shopnav">
          ${httpInclude.include("/store_nav?id=${(store.storeId)!}")}
          <script>
         jQuery(".shopnavul li").each(function(){
             var nav_id=jQuery(this).attr("id");
             if(nav_id=="${nav_id!}"){
               jQuery(this).addClass("this");
             }else jQuery(this).removeClass("this");
         });
        </script>
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
            <div class="bigshopimg">
                <#if ((store.slides)!?size) gt 0 >
              <ul class="shopslider">
                <#list (store.slides)! as slide >
                <li><a href="${(slide.url)!}" target="_blank">
                    <img src="${imageWebServer!}/${(slide.acc.path)!}/${(slide.acc.name)!}" width="797" height="393" />
                </a></li>
                </#list>
              </ul>
              <span style="z-index:3;">
                  <#list (store.slides)! as slide>
                  <a href="javascript:void(0);" class="img${slide_index}">${slide_index}</a>
                  </#list>
              </span>
                <#else>
              <ul class="shopslider">
                <li><a href="#" target="_blank">
                    <img src="${imageWebServer!}/resources/style/common/images/slide1.jpg" width="797" height="393" /></a></li>
                <li><a href="#" target="_blank">
                    <img src="${imageWebServer!}/resources/style/common/images/slide2.jpg" width="797" height="393" /></a></li>
                <li><a href="#" target="_blank">
                    <img src="${imageWebServer!}/resources/style/common/images/slide3.jpg" width="797" height="393" /></a></li>
                <li><a href="#" target="_blank">
                    <img src="${imageWebServer!}/resources/style/common/images/slide4.jpg" width="797" height="393" /></a></li>
              </ul>
              <span style="z-index:3;"><a href="javascript:void(0);" class="img1">1</a>
                  <a href="javascript:void(0);" class="img2">2</a><a href="javascript:void(0);" class="img3">3</a>
                  <a href="javascript:void(0);" class="img4">4</a></span>
                </#if>
            </div>
            <div class="regoods">
              <h1><a href="${S_URL}/goods_list?store_id=${(store.storeId)!}&recommend=true">更多</a><span>推荐商品</span></h1>
              <div class="smallgoods">
                  <#list goods_recommend! as goods>
                <#if (goods.goods_main_photo)!?? >
                    <#assign img="${imageWebServer!}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}" />
                <#else>
                    <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                </#if>
                   <#assign goods_url="${S_URL}/goods_${(goods.id)!}" />
                   <#if (config.second_domain_open)!?? >
                     <#assign goods_url="http://${(goods.goods_store.storeDomain)!}.${domainPath!}/goods_${(goods.id)!}" />
                   </#if>
                <ul>
                  <li class="goodsimgs">
                      <span class="goods_sp_span">
                          <p>
                              <a href="${goods_url!}" target="_blank">
                              <img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
                                   onerror="this.src='${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';" width="28" height="28"/>
                              </a>
                          </p>
                      </span>
                  </li>
                  <li class="goodslook">
                      <a href="${goods_url!}" target="_blank" class="look">查看详情</a>
                      <strong>¥${(goods.goodsPrice)!}</strong></li>
                  <li class="goodsnames">
                      <a href="${goods_url!}" target="_blank">${(CommUtil.substring("${(goods.goodsName)!}",28))!}</a>
                  </li>
                  <li class="recentgoodsok">最近成交<strong>${(goods.goodsSalenum)!}</strong>笔</li>
                </ul>
                  </#list>
              </div>
            </div>
            <div class="regoods">
              <h1>
                  <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}">更多</a>
                  <span>新品上市</span>
              </h1>
              <div class="smallgoods">
                <#list goods_new! as goods >
                <#if (goods.goods_main_photo)!?? >
                    <#assign img="${imageWebServer!}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}" />
                <#else>
                    <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
                </#if>
                   <#assign goods_url="${S_URL}/goods_${(goods.id)!}" />
                   <#if (config.second_domain_open)!?? >
                     <#assign goods_url="http://${(goods.goods_store.storeDomain)!}.${domainPath!}/goods_${(goods.id)!}" />
                   </#if>
                <ul>
                  <li class="goodsimgs">
                      <span class="goods_sp_span">
                        <p>
                            <a href="${goods_url!}" target="_blank">
                            <img src="${imageWebServer!}/static/images/common/loader.gif" original="${img!}"
                                 onerror="this.src='${S_URL}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}';" width="28" height="28"/>
                            </a>
                        </p>
                      </span>
                  </li>
                  <li class="goodslook">
                      <a href="${goods_url!}" target="_blank" class="look">查看详情</a>
                      <strong>¥${(goods.storePrice)!}</strong>
                  </li>
                  <li class="goodsnames">
                      <a href="${goods_url!}" target="_blank">${(CommUtil.substring("${(goods.goodsName)!}",28))!}</a></li>
                  <li class="recentgoodsok">最近成交<strong>${(goods.goodsSalenum)!}</strong>笔</li>
                </ul>
                </#list>
              </div>
            </div>
          </div>
        </div>
      </div>
      ${httpInclude.include("/footer")}
    </div>
</body>
</html>
