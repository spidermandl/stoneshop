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
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>

    <#if ((store.mapType)!)=="baidu" >
        <script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
    </#if>
    <#if ((store.mapType)!)=="google" >
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
    </#if>
</head>
<body>
    ${httpInclude.include("/top")}
    ${httpInclude.include("/store_head?store_id=${(store.storeId)!}")}
    <#assign banner="${S_URL}/static/images/shop/${(store.template)!}/images/banner.jpg" />
    <#if (store.banner)!??>
        <#assign banner="${S_URL}/${(store.banner.path)!}/${(store.banner.name)!}" />
    </#if>
    <div class="banner_width">
      <div class="shopbanner"><img src="${RES_URL}${banner!}"  width="1200px" /></div>
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
            <div class="shop_index">
                <div class="shopindex_center">
                    <#if (store.store_info)!?? && ((store.store_info)!"") != "" >
                    <div class="shop_introduct">
                        <div class="shop_introt"></div>
                        <div class="shop_intromid">
                            <h1>店铺介绍</h1>
                            <div class="shop_introp">
                                ${(store.store_info)!}
                            </div>
                        </div>
                        <div class="shop_introb"></div>
                    </div>
                    </#if>
                    <div class="shopinfo_box">
                        <div class="shopinfo_top">
                            <#if (store.logo)!??>
                                <#assign store_logo="${S_URL}/${(store.logo.path)!}/${(store.logo.name)!}" />
                            <#else>
                                <#assign store_logo="${S_URL}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
                            </#if>
                            <div class="shopinfo_topbox">
                                <span class="code_2d">
                                    <i><img src="${imageWebServer!}/${(config.uploadFilePath)!}/store/${(store.storeId)!}/code.png" width="85" height="85" /></i>
                                    <b>二维码收藏店铺</b>
                                </span>
                                <dl class="shopcode_left">
                                    <dt><img src="${RES_URL}${store_logo!}" width="90" height="90" /></dt>
                                    <dd><em>店铺名称：${(store.storeName)!}</em>
                                        <em>店铺等级：${(store.storeGrade.sgName)!}</em>
                                        <em>认证信息：
                                            <i><img  src="${S_URL}/static/images/common/card_approve_${(store.cardApprove)!false?string("true","false")}.gif" /></i>
                                            <i><img src="${imageWebServer!}/static/images/common/realstore_approve_${(store.realstoreApprove)!false?string("true","false")}.gif" /></i>
                                        </em>
                                    </dd>
                                </dl>
                            </div>
                            <div class="shopinfo_center"></div>
                        </div>
                        <h6 class="shopinfo_h6">基本信息</h6>
                        <ul class="shop_info">
                            <li>创店时间：${CommUtil.formatShortDate(store.storeTime)}</li>
                            <li>所在地区：${areaViewTools.generic_area_info("${(store.area.id)!}")}</li>
                            <li>详细地址：${(store.storeAddress)!}</li>
                            <#assign goods_count=0 />
                            <#list store.goods_list! as goods_info >
                                <#if goods_info.goodsStatus==0 >
                                    <#assign goods_count= goods_count+1 />
                                </#if>
                            </#list>
                            <li>商品数量：<strong class="blue">${goods_count!}</strong>件商品</li>
                            <li>店铺收藏：<strong class="blue">${(store.favorite_count)!}</strong>人收藏</li>
                            <li>联系电话：${(store.store_telephone)!}</li>
                            <li>店铺QQ：${(store.storeQq)!}</li>
                            <li>店铺MSN：${(store.store_msn)!}</li>
                        </ul>
                        <div class="shopinfo_bottom"></div>
                    </div>
                        <div class="shop_map">
                            <div class="shop_add_map" id="map" style="height:500px;"></div>
                        </div>
                </div>
            </div>
        ${httpInclude.include("/footer")}
        </div>
    </div>
</body>
</html>
<#if ((store.mapType)!)=="baidu" >
    <script type="text/javascript">
        <#assign store_logo="${S_URL}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
        <#if store.logo!?? >
           <#assign store_logo="${S_URL}/${(store.logo.path)!}/${(store.logo.name)!}" />
        </#if>
        var map = new BMap.Map("map");
        <#if (store.storeLng)!?? && (store.storeLat)!?? >
           map.centerAndZoom(new BMap.Point(${(store.storeLng)!},${(store.storeLat)!}), 16);
           var sContent ="<h4 style='margin:0 0 5px 0;padding:0.2em 0'>${(store.storeName)!}</h4>" +
                        "<img style='float:right;margin:4px' id='imgDemo' src='${store_logo!}' width='100' height='100' title='${(store.storeName)!}'/></div>";
            var point = new BMap.Point(${(store.storeLng)!},${(store.storeLat)!});
            var marker = new BMap.Marker(point);
            var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
            map.centerAndZoom(point, 15);
            map.addOverlay(marker);
            marker.enableDragging();
            marker.setAnimation(BMAP_ANIMATION_BOUNCE);
            marker.openInfoWindow(infoWindow);
            marker.addEventListener("click", function(){
              this.openInfoWindow(infoWindow);
            });
            //
       <#else>
       map.centerAndZoom(new BMap.Point(123.425329,41.792454), 11);
       </#if>
       map.enableScrollWheelZoom();
       map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
     </script>
 </#if>
 <#if ((store.mapType)!"") =="google">
    <script>
    var map;
    var marker;
    function initialize() {
    <#if (store.storeLng)!?? && (store.storeLat)!?? >
        var pyrmont = new google.maps.LatLng(${(store.storeLat)!},${(store.storeLng)!});
    <#else>
        var pyrmont = new google.maps.LatLng(39.92,116.46);
    </#if>
    map = new google.maps.Map(document.getElementById('map'), {
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: pyrmont,
        zoom: 15
    });
    marker = new google.maps.Marker({
        position: pyrmont,
        title:"${(store.storeName)!}"
    });
    // To add the marker to the map, call setMap();
    marker.setMap(map);
    var infowindow = new google.maps.InfoWindow({
        content:"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>${(store.storeName)!}</h4><img style='float:right;margin:4px' id='imgDemo' src='${store_logo!}' width='100' height='100' title='${(store.storeName)!}'/>"
    });
    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(marker.get('map'), marker);
    });
    //
    }
    google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</#if>
