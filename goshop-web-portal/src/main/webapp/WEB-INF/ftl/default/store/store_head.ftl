<#assign S_URL=request.contextPath  />
<div class="page_width">
  <div class="main_top">
    <div class="head">
      <div class="logo">
          <#if (config.websiteLogo)!??>
              <a href="${S_URL}/index">
                  <img src="${imageWebServer!}/${(config.websiteLogo.path)!}/${(config.websiteLogo.name)!}" border="0" />
              </a>
          <#else>
              <a href="${S_URL}/index">
              <img src="${imageWebServer!}/images/logo.png"  width="200" border="0" />
              </a>
          </#if>
          </div>
      <div class="shop_top_search">
        <div class="top_search_left">
          <div class="filter_z">
            <div class="top_searleft"> </div>
            <div class="top_nofilter">
              <div class="top_sear_kf">
                  <a href="${S_URL}/store?id=${(store.storeId)!}">${(store.storeName)!}</a>
                  <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(store.storeQq)!}&Site=${(store.storeQq)!}&Menu=yes">
                      <img alt="点击这里给我发消息" src="http://wpa.qq.com/pa?p=2:${(store.storeQq)!}:51" />
                  </a>
              </div>
              <#assign credit= storeViewTools.generic_store_credit("${(store.storeId)!}") />
              <#assign img="${imageWebServer!}/resources/style/common/images/level_0.gif" />
              <#if credit lt 10 && credit gt 0>
              <#assign credit= credit / 2 />
              <#assign credit= credit+1 />
              </#if>
              <#if credit gte 20 >
              <#assign img="${imageWebServer!}/resources/style/common/images/level_2.gif" />
              <#assign credit=(credit - 20)/2 />
              <#assign credit=credit+1 />
              </#if>
              <#if credit gte 10 >
              <#assign img="${imageWebServer!}/resources/style/common/images/level_1.gif" />
              <#assign credit=(credit - 10)/2 />
              <#assign credit=credit+1 />
              </#if>
              <#if credit gt 5 ><#assign credit=5 /></#if>
              <div class="top_sear_star">
                  <a href="javascript:void(0);" class="top_sc"
                     <#if user??>
                     <#else>dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"
                     </#if>>点击收藏
                  </a>
                  <span>
                      <#if credit==0>
                          <img src="${imageWebServer!}/static/images/common/level_-1.gif"/>
                      <#else>
                        <#list 1..credit as count >
                            <img style="margin-left:1px;" src="${img}" />
                        </#list>
                      </#if>
                  </span>
              </div>
            </div>
          </div>
          <b class="top_b" ></b><b class="top_b2" style="display:none"></b>
          <div class="top_sear_bom" style="display:none;" >
            <dl class="top_dl">
              <dt><b>店铺动态评分</b><i>与同行业相比</i></dt>
              <dd>
                <ul class="top_dl_ul">
                  <li><i>描述相符：</i>
                      <b>${(store.point.descriptionEvaluate)!}</b>
                      <em class="${description_css!}">
                          <strong>${description_type!}</strong>
                          ${description_result!}
                      </em>
                  </li>
                  <li><i>服务态度：</i><b>${(store.point.serviceEvaluate)!}</b>
                      <em class="${service_css!}">
                          <strong>${service_type!}</strong>${service_result!}
                      </em>
                  </li>
                  <li><i>发货速度：</i><b>${(store.point.shipEvaluate)!}</b>
                      <em class="${ship_css!}"><strong>${ship_type!}</strong>${ship_result!}</em></li>
                </ul>
              <dd>
            </dl>
            <dl class="top_dl">
              <dt>店铺认证</dt>
              <dd>
                  <span>
                      <img src="${imageWebServer!}/static/images/common/card_approve_${(store.cardApprove)!false?string("true","false")}.gif" />
                  </span>
                  <span>
                      <img src="${imageWebServer!}/static/images/common/realstore_approve_${(store.realstoreApprove)!false?string("true","false")}.gif" />
                  </span>
              <dd>
            </dl>
            <dl  class="top_dl">
              <dt>店铺信息</dt>
              <#assign eva = 0 />
              <#if store.point??>
                <#assign eva = store.point.storeEvaluate1 />
              </#if>
              <#assign store_evaluate1="${CommUtil.mul(eva,100)}%" />
              <#assign goods_count=0 />
              <#list (store.goods_list)! as goods_info >
                <#if (goods_info.goods_status) == 0 >
                  <#assign goods_count = goods_count+1 />
                </#if>
              </#list>
              <dd>
                  <span class="top_dl_width">商品数量：${goods_count!}件</span>
                  <span class="top_dl_width">好评率：${store_evaluate1!}</span>
                  <span class="top_dl_width">创店时间：${CommUtil.formatShortDate(store.addTime)!}</span>
                  <span class="top_dl_width">收藏人气：${(store.favoriteCount)!}</span>
              </dd>
            </dl>
          </div>
        </div>
        <form method="post" target="_blank" id="store_top_search_form">
          <input name="store_id" type="hidden" value="${(store.id)!}" />
          <div class="top_search_right">
            <div class="top_search_bg"></div>
            <div class="top_shop_seacher"> <span class="sear_shop">
              <input name="keyword" type="text" id="keyword" />
              </span><span class="all_sear_btn">
              <input name="" type="button"  value="搜全站" search_area="site" style="cursor:pointer;" />
              </span><span class="shop_sear_btn">
              <input name="" type="button"  value="搜本店" search_area="store" style="cursor:pointer;" />
              </span> </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
  <#if user??>
    ${httpInclude.include("/chatting")}
  </#if>
<script>
jQuery(function(jQuery){
  <#if user!??>
  jQuery(".top_sc").click(function(){
     <#if ((user.id)!0) == ((obj.goods_store.user.id)!-1)>
	   alert("不能收藏自己的店铺");
	 <#else>
	   jQuery.post("${S_URL}/add_store_favorite",{"id":"${(store.id)!}"},function(data){
		 if(data==0){
		     alert("店铺收藏成功！");
		 }
		 if(data==1){
		    alert("该店铺已经被收藏！");
		 }
	   },"text");
     </#if>
  });
  </#if>
  jQuery(".top_shop_seacher input[type=button]").click(function(){
    var search_area=jQuery(this).attr("search_area");
	if(search_area=="site"){
	  jQuery("#store_top_search_form").attr("action","${S_URL}/search");
	}
	if(search_area=="store"){
	  jQuery("#store_top_search_form").attr("action","${S_URL}/store_goods_search");
	}
	jQuery("#store_top_search_form").submit();
  });
  jQuery(".top_nofilter").mouseover(function(){
	jQuery(".top_b").hide();
    jQuery(".top_b2").show();
	jQuery(".top_sear_bom").fadeIn("normal");
  })
  jQuery(".top_search_left").css("cursor","pointer").mouseleave(function(){
	jQuery(".top_b").show();
    jQuery(".top_b2").hide();
	jQuery(".top_sear_bom").fadeOut("normal");
  });
});
</script>
