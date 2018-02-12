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
<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
<script>
jQuery(document).ready(function(){
  jQuery(":radio[name=map_type]").click(function(){
     var map_type=jQuery(this).val();
	 window.location.href="${S_URL}/store/store_map?map_type="+map_type;
  });
});
</script>
<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div class="productmain">
            <div class="ordernav">
              <ul class="orderul">
                <li><a href="${S_URL}/store/store_set">店铺设置</a></li>
                <li><a href="${S_URL}/store/store_slide">店铺幻灯</a></li>
                <li class="this"><a href="${S_URL}/store/store_map">店铺地图</a></li>
                <li><a href="${S_URL}/store/store_approve">店铺认证</a></li>
              </ul>
            </div>
            <form action="${S_URL}/store/store_map_save" method="post" enctype="multipart/form-data" id="theForm">
              <div class="ordercon">
                <div class="setshop">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="setshoptable">
                    <tr>
                      <td height="30" align="right" valign="middle">选择地图：</td>
                      <td align="left" valign="middle"><label>
                          <input name="map_type" type="radio" value="baidu" checked="checked" />
                          百度地图 </label>
                        <label>
                          <input type="radio" name="map_type" value="google" />
                          Google地图 </label></td>
                    </tr>
                     <tr>
                      <td height="30" align="right" valign="middle"><input name="store_lat" type="hidden" id="store_lat" value="${(store.storeLat)!}" />
                        <input name="store_lng" type="hidden" id="store_lng" value="${(store.storeLng)!}" />
                        输入地址：</td>
                      <td align="left" valign="middle"><input name="location" type="text" id="location" size="40" />
                        <input type="button" name="button" id="button" value="搜索位置" onclick="search_location();"  style="cursor:pointer;" /></td>
                    </tr>
                    <tr id="baidu_map_info">
                      <td colspan="2" align="right" valign="top"><div id="map" style="width:100%;height:500px;"> </div></td>
                    </tr>

<script type="text/javascript">
    <#assign store_logo="${S_URL}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
    <#if (store.logo)?? >
       <#assign store_logo="${RES_URL}/${(store.logo.path)!}/${(store.logo.name)!}" />
    </#if>
   var map = new BMap.Map("map");
   var marker;
   var sContent ="<h4 style='margin:0 0 5px 0;padding:0.2em 0'>${(store.storeName)!}</h4>" +
"<img style='float:right;margin:4px' id='imgDemo' src='${store_logo!}' width='100' height='100' title='${(store.storeName)!}'/>";
   map.addControl(new BMap.NavigationControl());
 //  map.addControl(new BMap.ScaleControl());
//   map.addControl(new BMap.OverviewMapControl());
   //map.addControl(new BMap.MapTypeControl());
   var point_add=0;//标注是否Add标注点
   <#if (store.storeLng)?? && (store.storeLat)?? >
   map.centerAndZoom(new BMap.Point(${(store.storeLng)!},${(store.storeLat)!}, 16));
   var point = new BMap.Point(${(store.storeLng)!},${(store.storeLat)!});
    marker = new BMap.Marker(point);
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建 Info窗口对象
    map.centerAndZoom(point, 15);
    map.addOverlay(marker);
	marker.enableDragging();
	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
	marker.openInfoWindow(infoWindow);
	marker.addEventListener("click", function(){
      this.openInfoWindow(infoWindow);
    });
	//
	marker.addEventListener("dragend",function(e){
	  jQuery("#store_lng").val(e.point.lng);
	  jQuery("#store_lat").val(e.point.lat);
    });
    point_add=1;
   <#else>
   map.centerAndZoom(new BMap.Point(123.425329,41.792454), 11);
   </#if>
   map.enableScrollWheelZoom();
   map.addControl(new BMap.NavigationControl());  //Add默认缩放平移控件
   map.addEventListener("click",function(e){
   // alert(e.point.lng + "," + e.point.lat);
  if(point_add==0){
   if(confirm("确认添加店铺地图位置吗？")){
    var point = new BMap.Point(e.point.lng, e.point.lat);
	jQuery("#store_lng").val(e.point.lng);
	jQuery("#store_lat").val(e.point.lat);
    marker = new BMap.Marker(point);
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建 Info窗口对象
    map.centerAndZoom(point, 15);
    map.addOverlay(marker);
	marker.enableDragging();
	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
	point_add=1;
    marker.addEventListener("click", function(){
    this.openInfoWindow(infoWindow);
   //Picture加载完毕重绘infowindow
     document.getElementById('imgDemo').onload = function (){
        infoWindow.redraw();
     }
    });
	//
	marker.addEventListener("dragend",function(e){
	  jQuery("#store_lng").val(e.point.lng);
	  jQuery("#store_lat").val(e.point.lat);
    });
  }
 }
});
   function search_location(){
	 var options = {
       onSearchComplete: function(results){
       if (local.getStatus() == BMAP_STATUS_SUCCESS){
       // 判断状态是否正确
	   map.clearOverlays();
	   if(results.getCurrentNumPois()>0){
	    var point=results.getPoi(0).point;
		  marker= new BMap.Marker(point);
		  map.addOverlay(marker);
	      marker.enableDragging();
          marker.setAnimation(BMAP_ANIMATION_BOUNCE);
		  var infoWindow = new BMap.InfoWindow(sContent);  // 创建 Info窗口对象
		  map.centerAndZoom(point, 15);
		  marker.openInfoWindow(infoWindow);
	      point_add=1;
		  jQuery("#store_lng").val(point.lng);
	      jQuery("#store_lat").val(point.lat);
          marker.addEventListener("click", function(){
             this.openInfoWindow(infoWindow);
             //Picture加载完毕重绘infowindow
             document.getElementById('imgDemo').onload = function (){
             infoWindow.redraw();
           }
         });
	    //
         marker.addEventListener("dragend",function(e){
	      jQuery("#store_lng").val(e.point.lng);
	      jQuery("#store_lat").val(e.point.lat);
         });
	   }
      }
    }
   };
     var local = new BMap.LocalSearch(map,options);
	 var location=jQuery("#location").val();
     local.search(location);
}
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
