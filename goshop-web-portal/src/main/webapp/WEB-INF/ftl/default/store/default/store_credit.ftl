<#assign S_URL=request.contextPath  />
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
    <link href="${S_URL}/static/styles/jquery.rating.css" type="text/css" rel="stylesheet" />
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
    <script src="${S_URL}/static/scripts/jquery.metadata.js"></script>
    <#--<script src="${S_URL}/static/scripts/jquery.rating.js"></script>-->
    <script src="${S_URL}/static/scripts/jquery.rating.pack.js"></script>
<script>
jQuery(document).ready(function(){
  jQuery(".evlua li").click(function(){
    jQuery(".evlua li").removeClass("this");
    jQuery(this).addClass("this");
	var uri=jQuery(this).attr("uri");
	jQuery.post(uri,"",function(data){
		jQuery(".evalubot").empty();
        jQuery(".evalubot").append(data);
	 },"text");

}).mouseover(function(){
  jQuery(this).css("cursor","pointer");
});
  //
 jQuery(".eval_tab li").mouseover(function(){
    jQuery(".eval_tab li").removeClass("this");
	jQuery(this).css("cursor","pointer").addClass("this");
	jQuery(".eval_tab_right").hide();
	var eva_type=jQuery(this).attr("eva_type");
	jQuery("#"+eva_type).show();
 });
 //
  <#if (store.point.descriptionEvaluateHalfyear)!0 gt 0 >
  jQuery(":radio.description_evaluate_halfyear").each(function(){
    var description_evaluate= ${store.point.descriptionEvaluateHalfyear};
	if(jQuery(this).val()>=description_evaluate){
		jQuery(this).prop("checked",true);
		return false;
	}
  });
  </#if>
  jQuery("input.description_evaluate_halfyear").rating();
  <#if (store.point.serviceEvaluateHalfyear)!0 gt 0 >
  jQuery(":radio.service_evaluate_halfyear").each(function(){
    var service_evaluate=${store.point.serviceEvaluateHalfyear};
	if(jQuery(this).val()>=service_evaluate){
		jQuery(this).prop("checked",true);
		return false;
	}
  });
  </#if>
  jQuery("input.service_evaluate_halfyear").rating();
  <#if (store.point.shipEvaluateHalfyear)!0 gt 0 >
  jQuery(":radio.ship_evaluate_halfyear").each(function(){
    var ship_evaluate=${store.point.shipEvaluateHalfyear};
	if(jQuery(this).val()>=ship_evaluate){
		jQuery(this).prop("checked",true);
		return false;
	}
  });
  </#if>
  jQuery("input.ship_evaluate_halfyear").rating();
});
function ajaxPage(url,currentPage,obj){
  jQuery.ajax({type:'POST',url:url,data:{"currentPage":currentPage,"id":"${(store.stireId)!}"},
			  beforeSend:function(){
			   },
			  success:function(data){
                 jQuery(".evalubot").empty();
                 jQuery(".evalubot").append(data);																									                }
		})
}
</script>
</head>
<body>
${httpInclude.include("/top")}
${httpInclude.include("/store_head?store_id=${(store.storeId)!}")}
  <#assign banner="${S_URL}/static/styles/shop/${(store.template)!}/images/banner.jpg" />
  <#if (store.banner)!?? >
    <#assign banner="${S_URL}/${(store.banner.path)!}/${(store.banner.name)!}" />
  </#if>
<div class="banner_width">
  <div class="shopbanner"><img src="${banner!}" width="1200px"/></div>
</div>
<div class="nav_width">
  <div class="main">
  <div class="nav_bg">
    <div class="shopnav"> ${httpInclude.include("/store_nav?id=${(store.storeId)!}")}
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
    <div class="shop_index"> ${httpInclude.include("/store_left2?id=${(store.storeId)!}")}
      <div class="shopindex_right">
        <div class="evaluation"><#assign rate=CommUtil.null2Double("${(store.point.storeEvaluate1)!}")*100 />
          <div class="goodevalu">好评率:（${rate!}%）</div>
          <div class="goodevt">
            <table width="797" border="0" cellspacing="0" cellpadding="0" class="goodevtable">
              <tr>
                <td width="90">&nbsp;</td>
                <td align="center" width="150">最近一周</td>
                <td align="center" width="150">最近一个月</td>
                <td align="center" width="150">最近6个月</td>
                <td align="center" width="150">6个月前</td>
                <td align="center" width="150">总计</td>
              </tr>
              <tr>
                <td align="center"><strong class="red"><i class="flower">
                    <img src="${imageWebServer!}/static/styles/shop/${(store.template)!}/images/flower3.gif" width="16" height="16">
                </i>好评</strong></td>
                <#assign eva_week_0=storeViewTools.query_evaluate("${(store.storeId)!}",1,"week","after",-1) />
                <td align="center">${eva_week_0!}</td>
                <#assign eva_month_0=storeViewTools.query_evaluate("${(store.storeId)!}",1,"month","after",-1) />
                <td align="center">${eva_month_0!}</td>
                <#assign eva_month6_0=storeViewTools.query_evaluate("${(store.storeId)!}",1,"month","after",-6) />
                <td align="center">${eva_month6_0!}</td>
                <#assign eva_month6_before0=storeViewTools.query_evaluate("${(store.storeId)!}",1,"month","before",-6) />
                <td align="center">${eva_month6_before0!}</td>
                  <#assign eva_all_total0= eva_month6_0 + eva_month6_before0 />
                <td align="center">${eva_all_total0!}</td>
              </tr>
              <tr>
                <td align="center"><strong class="green"><i class="flower">
                    <img src="${imageWebServer!}/static/styles/shop/${(store.template)!}/images/flower2.gif" width="16" height="16">
                </i>中评</strong></td>
                <#assign eva_week_1=storeViewTools.query_evaluate("${(store.storeId)!}",0,"week","after",-1) />
                <td align="center">${eva_week_1!}</td>
                <#assign eva_month_1=storeViewTools.query_evaluate("${(store.storeId)!}",0,"month","after",-1) />
                <td align="center">${eva_month_1!}</td>
                <#assign eva_month6_1=storeViewTools.query_evaluate("${(store.storeId)!}",0,"month","after",-6) />
                <td align="center">${eva_month6_1!}</td>
                <#assign eva_month6_before1=storeViewTools.query_evaluate("${(store.storeId)!}",0,"month","before",-6) />
                <td align="center">${eva_month6_before1!}</td>
                <#assign eva_all_total1= eva_month6_1+eva_month6_before1 />
                <td align="center">${eva_all_total1!}</td>
              </tr>
              <tr>
                <td align="center"><strong><i class="flower">
                    <img src="${imageWebServer!}/static/styles/shop/${(store.template)!}/images/flower1.gif" width="16" height="16">
                </i>差评</strong></td>
                <#assign eva_week_2=storeViewTools.query_evaluate("${(store.storeId)!}",-1,"week","after",-1) />
                <td align="center">${eva_week_2!}</td>
                <#assign eva_month_2=storeViewTools.query_evaluate("${(store.storeId)!}",-1,"month","after",-1) />
                <td align="center">${eva_month_2!}</td>
                <#assign eva_month6_2=storeViewTools.query_evaluate("${(store.storeId)!}",-1,"month","after",-6) />
                <td align="center">${eva_month6_2!}</td>
                <#assign eva_month6_before2=storeViewTools.query_evaluate("${(store.storeId)!}",-1,"month","before",-6) />
                <td align="center">${eva_month6_before2!}</td>
                <#assign eva_all_total2=eva_month6_2+eva_month6_before2 />
                <td align="center">${eva_all_total2!}</td>
              </tr>
              <tr>
                <td align="center"><strong class="blue2">总计</strong></td>
                <#assign eva_week_total=eva_week_0+eva_week_1+eva_week_2 />
                <td align="center">${eva_week_total!}</td>
                <#assign eva_month_total=eva_month_0+eva_month_1+eva_month_2 />
                <td align="center">${eva_month_total!}</td>
                <#assign eva_month6_total=eva_month6_0+eva_month6_1+eva_month6_2 />
                <td align="center">${eva_month6_total!}</td>
                <#assign eva_month6_before_total=eva_month6_before0+eva_month6_before1+eva_month6_before2 />
                <td align="center">${eva_month6_before_total!}</td>
                <#assign eva_total=eva_all_total0+eva_all_total1+eva_all_total2 />
                <td align="center">${eva_total!}</td>
              </tr>
            </table>
          </div>
        </div>
        <div class="eval_service">
          <div class="eval_service_box">
            <h3>店铺半年内动态评分</h3>
            <div class="eval_service_bt">
              <div class="eval_tab">
                <ul>
                  <li eva_type="description_evaluate"><b>宝贝与描述相符：</b>
                      <i>${CommUtil.null2Double("${(store.point.descriptionEvaluateHalfyear)!}")}</i>
                      <em>分</em></li>
                  <li eva_type="service_evaluate"><b>卖家的服务态度：</b>
                      <i>${CommUtil.null2Double("${(store.point.serviceEvaluateHalfyear)!}")}</i>
                      <em>分</em></li>
                  <li eva_type="ship_evaluate" class="this"><b>卖家的发货速度：</b>
                      <i>${CommUtil.null2Double("${(store.point.shipEvaluateHalfyear)!}")}</i>
                      <em>分</em></li>
                </ul>
              </div>
              <div class="eval_tab_right" id="description_evaluate" style="display:block;">
                <div class="eval_tab_con">
                  <ul class="eval_con_ul">
                    <li class="eval_tit">
                    <span>
                     <#list 1..25 as i >
                        <#assign val = i * 0.2 />
                        <#assign val= CommUtil.formatDouble(val,1) />
                        <input name="description_evaluate_halfyear" class="description_evaluate_halfyear {split:5}" type="radio" value="${val!}"  disabled="disabled" />
                     </#list>
                    </span>
                        <#assign description_total=
                        "${(store.point.descriptionEvaluateHalfyearCount5)!0}"?number +
                        "${(store.point.descriptionEvaluateHalfyearCount4)!0}"?number +
                        "${(store.point.descriptionEvaluateHalfyearCount3)!0}"?number +
                        "${(store.point.descriptionEvaluateHalfyearCount2)!0}"?number +
                        "${(store.point.descriptionEvaluateHalfyearCount1)!0}"?number />
                    <span>
                        <b>${CommUtil.null2Double("${(store.point.descriptionEvaluateHalfyear)!}")}</b>分
                    </span>
                        <span>共${description_total!}人</span>
                    </li>
                    <#assign description_total5=CommUtil.div("${(store.point.descriptionEvaluateHalfyearCount5)!}",
                                                                "${description_total!}")*100 />
                    <li><span class="smstart_5"></span><span>4-5分</span><span>
                      <div class="eval_bar" style="width:${description_total5!}px;"></div>
                      </span><span><b>${description_total5!}%</b></span>
                     </li>
                        <#assign description_total4=CommUtil.div("${(store.point.descriptionEvaluateHalfyearCount4)!}",
                                                                  "${description_total!}")*100 />
                    <li>
                    <span class="smstart_4"></span><span>3-4分</span>
                    <span>
                     <div class="eval_bar" style="width:${description_total4!}px;"></div>
                    </span>
                    <span><b>${description_total4!}%</b></span>
                    </li>
                        <#assign description_total3 = CommUtil.div("${(store.point.descriptionEvaluateHalfyearCount3)!}",
                                                                    "${description_total!}")*100 />
                    <li>
                    <span class="smstart_3"></span><span>2-3分</span>
                    <span>
                     <div class="eval_bar" style="width:${description_total3!}px;"></div>
                    </span>
                    <span><b>${description_total3!}%</b></span>
                    </li>
                        <#assign description_total2 = CommUtil.div("${(store.point.descriptionEvaluateHalfyearCount2)!}",
                                                                    "${description_total!}")*100 />
                    <li>
                    <span class="smstart_2"></span><span>1-2分</span>
                    <span>
                     <div class="eval_bar" style="width:${description_total2!}px;"></div>
                    </span>
                    <span><b>${description_total2!}%</b></span>
                    </li>
                        <#assign description_total1 = CommUtil.div("${(store.point.descriptionEvaluateHalfyearCount1)!}",
                                                                    "${description_total!}")*100 />
                   <li>
                    <span class="smstart_1"></span><span>0-1分</span>
                    <span>
                     <div class="eval_bar" style="width:${description_total1!}px;"></div>
                    </span>
                    <span><b>${description_total1!}%</b></span>
                    </li>
                  </ul>
                </div>
                <div class="eval_rbg"></div>
              </div>
              <div class="eval_tab_right" id="service_evaluate" style="display:none;">
                <div class="eval_tab_con">
                  <ul class="eval_con_ul">
                    <li class="eval_tit">
                    <span>
                     <#list 1..25 as i >
                      <#assign val=i * 0.2 />
                      <#assign val=CommUtil.formatDouble(val,1) />
                        <input name="service_evaluate_halfyear" class="service_evaluate_halfyear {split:5}" type="radio" value="${val!}" disabled="disabled" />
                     </#list>
                    </span>
                        <#assign service_total=
                        "${(store.point.serviceEvaluateHalfyearCount5)!0}"?number+
                        "${(store.point.serviceEvaluateHalfyearCount5)!0}"?number+
                        "${(store.point.serviceEvaluateHalfyearCount5)!0}"?number+
                        "${(store.point.serviceEvaluateHalfyearCount5)!0}"?number+
                        "${(store.point.serviceEvaluateHalfyearCount5)!0}"?number/>

                    <span><b>${CommUtil.null2Double("${(store.point.serviceEvaluateHalfyear)!}")}</b>分</span>
                        <span>共${service_total!}人</span></li>
                    <#assign service_total5=CommUtil.div("${(store.point.serviceEvaluateHalfyearCount5)!}",
                                                            service_total)*100 />
                    <li><span class="smstart_5"></span><span>4-5分</span><span>
                      <div class="eval_bar" style="width:${service_total5!}px;"></div>
                      </span><span><b>${service_total5!}%</b></span>
                     </li>
                    <#assign service_total4=CommUtil.div("${(store.point.serviceEvaluateHalfyearCount4)!}",
                                                            service_total)*100 />
                    <li>
                    <span class="smstart_4"></span><span>3-4分</span>
                    <span>
                     <div class="eval_bar" style="width:${service_total4!}px;"></div>
                    </span>
                    <span><b>${service_total4!}%</b></span>
                    </li>
                    <#assign service_total3=CommUtil.div("${(store.point.serviceEvaluateHalfyearCount3)!}",
                                                            service_total)*100 />
                   <li>
                    <span class="smstart_3"></span><span>2-3分</span>
                    <span>
                     <div class="eval_bar" style="width:${service_total3!}px;"></div>
                    </span>
                    <span><b>${service_total3!}%</b></span>
                    </li>
                    <#assign service_total2=CommUtil.div("${(store.point.serviceEvaluateHalfyearCount2)!}",
                                                            service_total)*100 />
                   <li>
                    <span class="smstart_2"></span><span>1-2分</span>
                    <span>
                     <div class="eval_bar" style="width:${service_total2!}px;"></div>
                    </span>
                    <span><b>${service_total2!}%</b></span>
                    </li>
                    <#assign service_total1=CommUtil.div("${(store.point.serviceEvaluateHalfyearCount1)!}",
                                                            service_total)*100 />
                   <li>
                    <span class="smstart_1"></span><span>0-1分</span>
                    <span>
                     <div class="eval_bar" style="width:${service_total1!}px;"></div>
                    </span>
                    <span><b>${service_total1!}%</b></span>
                    </li>
                  </ul>
                </div>
                <div class="eval_rbg"></div>
              </div>
              <div class="eval_tab_right" id="ship_evaluate" style="display:none;">
                <div class="eval_tab_con">
                  <ul class="eval_con_ul">
                    <li class="eval_tit">
                    <span>
                     <#list 1..25 as i >
                      <#assign val=i * 0.2 />
                      <#assign val=CommUtil.formatDouble(val,1) />
                        <input name="ship_evaluate_halfyear" class="ship_evaluate_halfyear {split:5}" type="radio" value="${val!}"  disabled="disabled" />
                     </#list>
                    </span>
                        <#assign ship_total =
                        "${(store.point.shipEvaluateHalfyearCount5)!0}"?number+
                        "${(store.point.shipEvaluateHalfyearCount4)!0}"?number+
                        "${(store.point.shipEvaluateHalfyearCount3)!0}"?number+
                        "${(store.point.shipEvaluateHalfyearCount2)!0}"?number+
                        "${(store.point.shipEvaluateHalfyearCount1)!0}"?number/>

                    <span><b>${CommUtil.null2Double("${(store.point.shipEvaluateHalfyear)!}")}</b>分</span>
                        <span>共${ship_total!}人</span></li>
                        <#assign ship_total5=CommUtil.div("${(store.point.shipEvaluateHalfyearCount5)!}",ship_total)*100 />
                    <li><span class="smstart_5"></span><span>4-5分</span><span>
                      <div class="eval_bar" style="width:${ship_total5!}px;"></div>
                      </span><span><b>${ship_total5!}%</b></span>
                     </li>
                        <#assign ship_total4=CommUtil.div("${(store.point.shipEvaluateHalfyearCount4)!}",ship_total)*100 />
                    <li>
                    <span class="smstart_4"></span><span>3-4分</span>
                    <span>
                     <div class="eval_bar" style="width:${ship_total4!}px;"></div>
                    </span>
                    <span><b>${ship_total4!}%</b></span>
                    </li>
                        <#assign ship_total3=CommUtil.div("${(store.point.shipEvaluateHalfyearCount3)!}",ship_total)*100 />
                   <li>
                    <span class="smstart_3"></span><span>2-3分</span>
                    <span>
                     <div class="eval_bar" style="width:${ship_total3!}px;"></div>
                    </span>
                    <span><b>${ship_total3!}%</b></span>
                    </li>
                        <#assign ship_total2=CommUtil.div("${(store.point.shipEvaluateHalfyearCount2)!}",ship_total)*100 />
                   <li>
                    <span class="smstart_2"></span><span>1-2分</span>
                    <span>
                     <div class="eval_bar" style="width:${ship_total2!}px;"></div>
                    </span>
                    <span><b>${ship_total2!}%</b></span>
                    </li>
                        <#assign ship_total1=CommUtil.div("${(store.point.shipEvaluateHalfyearCount1)!}",ship_total)*100 />
                   <li>
                    <span class="smstart_1"></span><span>0-1分</span>
                    <span>
                     <div class="eval_bar" style="width:${ship_total1!}px;"></div>
                    </span>
                    <span><b>${ship_total1!}%</b></span>
                    </li>
                  </ul>
                </div>
                <div class="eval_rbg"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="shop_index">
    <div class="evaludetail">
          <div class="evlua">
            <ul>
              <li class="this" uri="${S_URL}/store_eva?id=${(store.storeId)!}">
                  <strong><a href="javascript:void(0);">全部评价</a></strong></li>
              <li uri="${S_URL}/store_eva?id=${(store.storeId)!}&eva_val=1"><strong class="red">好评</strong></li>
              <li uri="${S_URL}/store_eva?id=${(store.storeId)!}&eva_val=0"><strong class="green">中评</strong></li>
              <li uri="${S_URL}/store_eva?id=${(store.storeId)!}&eva_val=-1"><strong>差评</strong></li>
            </ul>
          </div>
          <div class="evalubot">
            <div class="evaluone">
              <table width="797" border="0" cellspacing="0" cellpadding="0" class="evaluonetable">
                <tr>
                  <td width="80">评价</td>
                  <td width="140">内容</td>
                  <td width="100" align="center">商品</td>
                  <td width="60" align="center">金额</td>
                  <td width="100" align="center">买家</td>
                  <td width="100" align="center">时间</td>
                </tr>
                <#list objs! as obj >
                <tr>
                    <#if  (obj.evaluate_buyer_val)!-2 == 1 >
                        <#assign evaluate_buyer_val="好评" />
                    </#if>
                    <#if  (obj.evaluate_buyer_val)!-2 == 0 >
                        <#assign evaluate_buyer_val="中评" />
                    </#if>
                    <#if  (obj.evaluate_buyer_val)!-2 == -1 >
                    <#assign evaluate_buyer_val="差评" />
                    </#if>
                  <td>${evaluate_buyer_val}</td>
                  <td><#if ((obj.evaluate_info)!) !="" >${(obj.evaluate_info)!} <#else> 这家伙什么都没说 </#if></td>
                  <td class="goodsevalu"><span class="nameevalu">
                      <a href="${S_URL}/goods_${(obj.evaluate_goods.id)!}" target="_blank">${(obj.evaluate_goods.goodsName)!}</a></span></td>
                  <td align="center"><strong class="orange">¥${(obj.of.totalPrice)!}</strong></td>
                  <td align="center">${(obj.evaluate_user.userName)!}</td>
                  <td align="center">${CommUtil.formatLongDate("${(obj.addTime)!}")}</td>
                </tr>
                </#list>
              </table>
            </div>
            <div class="fenye">
              <div class="fenyes">${gotoPageAjaxHTML!}</div>
            </div>
        </div>
      </div>
    </div>
  </div>
  ${httpInclude.include("/footer")}</div>
</body>
</html>
