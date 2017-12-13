<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="店铺设置" />
<#assign P_CURRENT_OP="StoreSetting" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.poshytip.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true&libraries=places"></script>
<script>
jQuery(document).ready(function(){
  jQuery(":radio[name=map_type]").click(function(){
     var map_type=jQuery(this).val();
	 window.location.href="${S_URL}/seller/store_map.htm?map_type="+map_type;	 
  });
});
</script>
</head>
<body>
<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div class="productmain">
            <div class="ordernav">
              <ul class="orderul">
                <li><a href="${S_URL}/seller/store_set.htm">店铺设置</a></li>
                <li><a href="${S_URL}/seller/store_slide.htm">店铺幻灯</a></li>
                <li class="this"><a href="${S_URL}/seller/store_map.htm">店铺地图</a></li>
                <li><a href="${S_URL}/seller/store_approve.htm">店铺认证</a></li>
              </ul>
            </div>
            <form action="${S_URL}/seller/store_map_save.htm" method="post" enctype="multipart/form-data" id="theForm">
              <div class="ordercon">
                <div class="setshop">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="setshoptable">
                    <tr>
                      <td height="30" align="right" valign="middle">选择地图：</td>
                      <td align="left" valign="middle"><label>
                          <input name="map_type" type="radio" value="baidu" />
                          百度地图 </label>
                        <label>
                          <input name="map_type" type="radio" value="google" checked="checked" />
                          Google地图 </label></td>
                    </tr>
                    <tr>
                      <td height="30" align="right" valign="middle"><input name="store_lat" type="hidden" id="store_lat" value="$!store.store_lat" />
                        <input name="store_lng" type="hidden" id="store_lng" value="$!store.store_lng" />
                        输入地址：</td>
                      <td align="left" valign="middle"><input name="location" type="text" id="location" size="40" />
                        </td>
                    </tr>
                    <tr id="google_map_info">
                      <td colspan="2" align="right" valign="top"><div id="g_map" style="width:100%;height:500px;"> </div></td>
                    </tr>
<script>
var map;
var marker;
function initialize() {
#if($!{store.store_lng}&&$!{store.store_lat})
   var pyrmont = new google.maps.LatLng($!{store.store_lat},$!{store.store_lng});
#else
   var pyrmont = new google.maps.LatLng(39.92,116.46);
#end
  map = new google.maps.Map(document.getElementById('g_map'), {
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      center: pyrmont,
      zoom: 15
    });
    marker = new google.maps.Marker({
      position: pyrmont,
      title:"$!{store.store_name}"
    });
// To add the marker to the map, call setMap();
marker.setMap(map);
marker.setDraggable(true);
google.maps.event.addListener(marker, 'dragend', function() {      
   var lat=marker.getPosition().lat();
   var lng=marker.getPosition().lng();
   jQuery("#store_lng").val(lng);
   jQuery("#store_lat").val(lat);
}); 
var infowindow = new google.maps.InfoWindow({
    content:"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>$!{store.store_name}</h4><img style='float:right;margin:4px' id='imgDemo' src='$!store_logo' width='100' height='100' title='$!{store.store_name}'/>"
});
google.maps.event.addListener(marker, 'click', function() {
    infowindow.open(marker.get('map'), marker);
});
//
  var input = /** @type {HTMLInputElement} */(document.getElementById('location'));
  var searchBox = new google.maps.places.SearchBox(input);
  google.maps.event.addListener(searchBox, 'places_changed', function() {
    var places = searchBox.getPlaces();
	if(places.length>0){
	   map.setCenter(places[0].geometry.location);	
       marker = new google.maps.Marker({
         position: places[0].geometry.location,
         title:"$!{store.store_name}"
       });
	   marker.setMap(map);
       marker.setDraggable(true);
       google.maps.event.addListener(marker, 'dragend', function() {      
         var lat=marker.getPosition().lat();
         var lng=marker.getPosition().lng();
         jQuery("#store_lng").val(lng);
         jQuery("#store_lat").val(lat);
       }); 
       var infowindow = new google.maps.InfoWindow({
         content:"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>$!{store.store_name}</h4><img style='float:right;margin:4px' id='imgDemo' src='$!store_logo' width='100' height='100' title='$!{store.store_name}'/>"
       });
      google.maps.event.addListener(marker, 'click', function() {
          infowindow.open(marker.get('map'), marker);
      });	   
	}
 });

  google.maps.event.addListener(map, 'bounds_changed', function() {
    var bounds = map.getBounds();
    searchBox.setBounds(bounds);
  }); 
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
                    <tr>
                      <td width="103" align="right">&nbsp;</td>
                      <td width="897" style="padding-left:30px;"><span class="setsub">
                        <input name="input" type="submit"  value="提交" style="cursor:pointer;"/>
                        </span></td>
                    </tr>
                  </table>
                </div>
              </div>
            </form>
          </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
