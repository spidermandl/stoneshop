<#assign S_URL=request.contextPath  />
<script>
jQuery(function(jQuery){
  jQuery(".collection a").click(function(){
    <#if user!?? >
     <#if ((user.id)!0) == ((obj.goods_store.user.id)!-1) >
	   alert("不能收藏自己的店铺");
	 <#else>
	   jQuery.post("${S_URL}/add_store_favorite",{"id":"${(store.storeId)!}"},function(data){
		 if(data==0){
		     alert("店铺收藏成功！");
		 }
		 if(data==1){
		    alert("该店铺已经被收藏！");
		 }
	   },"text");
     </#if>
    </#if>
  });
  //
  jQuery("span[id^=ugc_]").css("cursor","pointer").click(function(){
     var ugc_type=jQuery(this).attr("ugc_type");
	 var ugc_id=jQuery(this).attr("ugc_id");
	 if(ugc_type=="show"){
	    jQuery(this).find("img").attr("src","${S_URL}/static/styles/shop/${(store.template)!}/images/add.jpg");
		jQuery("#ugc_child_"+ugc_id).hide();
	    jQuery(this).attr("ugc_type","hide");
	 }else{
	    jQuery(this).find("img").attr("src","${S_URL}/static/styles/shop/${(store.template)!}/images/add2.jpg");
		jQuery("#ugc_child_"+ugc_id).show();
	    jQuery(this).attr("ugc_type","show");
	 }
  });
  //
  jQuery(".twocoad").css("cursor","pointer").mouseover(function(){
     jQuery(".shop_botmhbox").fadeIn('fast');
  }).mouseleave(function(){
     jQuery(".shop_botmhbox").fadeOut('fast');
  });
  //
  jQuery(".shop_rank_top li").css("cursor","pointer").mouseover(function(){
    var sort_id=jQuery(this).attr("sort_id");
	jQuery(".shop_rank_top li").removeClass("this");
	jQuery(this).addClass("this");
	jQuery(".shop_rank_botm").hide();
	jQuery("#"+sort_id).show();
  });
});
</script>

<div class="shopindex_left">
  <div class="shoptop">
    <h1>${(store.storeName)!}</h1>
    <div class="shopvalue">
      <dl class="shopvdl">
        <dt><span>
            <#if (store.logo)!?? >
                <#assign store_logo="${RES_URL}/${(store.logo.path)!}/${(store.logo.name)!}" />
            <#else>
                <#assign store_logo="${S_URL}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
            </#if> <img src="${store_logo!}" width="60" height="60" />
        </span></dt>
        <#assign credit=storeViewTools.generic_store_credit("${(store.storeId)!}") />
        <#assign img="${imageWebServer!}/resources/style/common/images/level_0.gif" />
        <#if credit lt 10 && credit gt 0>
        <#assign credit = credit / 2 />
        <#assign credit = credit+1 />
        </#if>
        <#if credit gte 20 >
        <#assign img="${imageWebServer!}/resources/style/common/images/level_2.gif" />
        <#assign credit=(credit - 20)/2 />
        <#assign credit = credit+1 />
        </#if>
        <#if credit gte 10 >
        <#assign img="${imageWebServer!}/resources/style/common/images/level_1.gif" />
        <#assign credit=(credit - 10)/2 />
        <#assign credit=credit+1 />
        </#if>
        <#if credit gt 5 > <#assign credit=5 /></#if>
        <dd>
            <span >${(store.storeOwer)!}
                <a href="${S_URL}/buyer/message_send?userName=${(store.user.userName)!}" target="_blank" class="shopemail">
                    <img src="${imageWebServer!}/static/images/common/mail.jpg" width="15" height="11" />
                </a>
            </span>
            <span>
            <#if credit==0 >
              <img src="${imageWebServer!}/static/images/common/level_-1.gif"/>
            <#else>
              <#list 1..credit as count ><img style="margin-left:1px;" src="${img!}" /></#list>
            </#if>
            </span>
            <#assign store_evaluate1= CommUtil.mul("${(store.point.storeEvaluate1)!}",100)+"%" />
            <span class="hui2">好评率：${store_evaluate1!}
            </span>
        </dd>
      </dl>
      <h1>动态评价<i>与同行比较</i></h1>
      <ul class="shop_movepj">
        <li><i>描述相符：</i>
            <b>${(store.point.descriptionEvaluate)!}</b>
            <em class="${description_css!}">
                <strong>${description_type!}</strong>${description_result!}
            </em>
        </li>
        <li><i>服务态度：</i>
            <b>${(store.point.serviceEvaluate)!}</b>
            <em class="${service_css!}"><strong>${service_type!}</strong>${service_result!}</em></li>
        <li><i>发货速度：</i>
            <b>${(store.point.shipEvaluate)!}</b>
            <em class="${ship_css!}"><strong>${ship_type!}</strong>${ship_result!}</em></li>
      </ul>
      <h1>店铺信息</h1>
      <ul>
        <li>创店时间：${CommUtil.formatShortDate(store.storeTime)}</li>
        <li>所在地区：${areaViewTools.generic_area_info("${(store.area.id)!}")}</li>
        <#assign goods_count=0 />
        <#list (store.goods_list)! as goods_info >
        <#if goods_info.goods_status == 0 >
            <#assign goods_count=goods_count+1 />
        </#if>
        </#list>
        <li>商品数量：<strong class="blue">${goods_count!}</strong>件商品</li>
        <li>店铺收藏：<strong class="blue">${(store.favoriteCount)!}</strong>人收藏</li>
        <li>联系电话：${(store.storeTelephone)!}</li>
      </ul>
      <h1>联系方式</h1>
      <ul>
           <li class="shopcusser">
               <span>站内客服：</span>
               <span>
                 <#if user?? >
                     <a class="im_common" href="javascript:void(0);" user_id="${(store.memberId)!}" id="userDialog_img_contact_${(store.memberId)!}" user_name="${(store.user.userName)!}"> 咨询客服</a>
                 <#else>
                     <a class="im_common" href="javascript:void(0);" dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login">咨询客服 </a>
                  </#if>
               </span>
           </li>
           <li class="shopcusser">
               <span>站外客服：</span>
               <span>
                   <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${(store.storeQq)!}&Site=${(store.storeQq)!}&Menu=yes">
                       <img alt="点击这里给我发消息" src="http://wpa.qq.com/pa?p=2:${(store.storeQq)!}:51" />
                   </a>
               </span>
           </li>
      </ul>
    </div>
    <div class="shop_botm_hid">
      <ul class="shopboh">
        <li class="collection">
            <a href="javascript:void(0);" id="store_fav"
               <#if user!?? >
               <#else>
               dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"
               </#if>>
                <span>收藏店铺</span>
            </a>
        </li>
        <li class="this">
            <span class="twocoad">店铺二维码
                <div class="shop_botmhbox" style="display:none;">
                    <span>
                        <img src="${imageWebServer!}/${(config.uploadFilePath)!}/store/${(store.storeId)!}/code.png" width="140" height="140" />
                    </span>
                    <i>手机扫描二维码<br />快速收藏店铺</i>
                </div>
            </span>
        </li>
      </ul>
    </div>
  </div>
  <form action="${S_URL}/store_goods_search" method="post" id="store_search_form">
    <div class="shopsearch">
      <ul>
        <li>
            <span class="searspan">关键字：</span>
            <span class="shopsear1">
                <input name="keyword" type="text" id="keyword" />
            </span>
        </li>
        <li>
            <span class="searspan">&nbsp;</span>
            <span class="searbtns">
                <input name="input2" type="submit"  value="搜索"  style="cursor:pointer;"/>
            </span>
            <input name="store_id" type="hidden" id="store_id" value="${(store.storeId)!}" />
        </li>
      </ul>
    </div>
  </form>
  <div class="shopclassify">
    <h1>商品分类</h1>
    <!--箭头sort_a,sort_b-->
    <div class="shop_sort">
        <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}&orderBy=addTime&orderType=desc">按默认</a>
        <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}&orderBy=store_price&orderType=desc">按价格</a>
        <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}&orderBy=goods_salenum&orderType=desc">按销量</a>
        <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}&orderBy=goods_click&orderType=desc">按人气</a></div>
    <ul class="shopcul">
      <li>
          <span>
              <img src="${imageWebServer!}/static/styles/shop/${(store.template)!}/images/add2.jpg" width="15" height="15" />
          </span>
          <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}" class="oneclass">所有商品</a>
      </li>
      <#list ugcs as ugc >
      <#if ((ugc.display)!false) == true >
          <li>
              <span id="ugc_${(ugc.id)!}" ugc_type="show" ugc_id="${(ugc.id)!}">
                  <img src="${imageWebServer!}/static/styles/shop/${(store.template)!}/images/add2.jpg" width="15" height="15" />
              </span>
              <a href="${S_URL}/goods_list?gc_id=${(ugc.id)!}&store_id=${(store.storeId)!}" class="oneclass">${(ugc.classname)!}</a>
              <#if (ugc.childs)!?size gt 0 >
                <ul id="ugc_child_${(ugc.id)!}">
                  <#list (ugc.childs)! as cugc >
                    <#if ((cugc.display)!false) == true >
                        <li>
                            <a href="${S_URL}/goods_list?gc_id=${(cugc.id)!}&store_id=${store.storeId}">${(cugc.classname)!}</a>
                        </li>
                    </#if>
                  </#list>
                </ul>
              </#if>
          </li>
      </#if>
      </#list>
    </ul>
  </div>
  <div class="shop_rank">
    <h3>商品排行</h3>
    <div class="shop_rank_box">
      <div class="shop_rank_top">
        <ul>
          <li class="this" sort_id="sort_sale_goods">热销商品排行</li>
          <li sort_id="sort_collect_goods">热门收藏排行</li>
        </ul>
      </div>
      <div class="shop_rank_botm" id="sort_sale_goods">
          <#list goodsViewTools.sort_sale_goods(store.storeId,5) as goods >
            <#if (goods.goods_main_photo)!??>
                <#assign img="${RES_URL!}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}_small.${(goods.goods_main_photo.ext)!}" />
            <#else>
                <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
            </#if>
            <#assign goods_url="${S_URL}/goods_${(goods.id)!}" />
            <#if config.second_domain_open!?? >
                <#assign goods_url="http://${(goods.goods_store.storeDomain)!}.${domainPath!}/goods_${(goods.id)!}" />
            </#if>
            <dl>
              <dt><span class="imgcenter_span ">
                <p><a href="${goods_url!}" target="_blank"><img width="58" height="58" src="${img!}" /></a></p>
                </span></dt>
              <dd>
                  <span class="shop_rank_name">
                  <a href="${goods_url!}" target="_blank">
                      ${CommUtil.substring("${(goods.goodsName)!}",8)}
                  </a>
                  </span>
                  <span class="shop_rank_money">¥<b>${(goods.goodsPrice)!}</b></span>
                  <span class="shop_rank_show">出售：<strong>${(goods.goods_salenum)!}</strong>笔</span>
              </dd>
            </dl>
          </#list>
      </div>
      <div class="shop_rank_botm" style="display:none;" id="sort_collect_goods">
        <#list goodsViewTools.sort_collect_goods("${store.storeId}",5) as goods >
        <#if (goods.goods_main_photo)!??>
            <#assign img="${RES_URL!}/${(goods.goods_main_photo.path)!}/${(goods.goods_main_photo.name)!}_small.${(goods.goods_main_photo.ext)!}" />
        <#else>
            <#assign img="${imageWebServer!}/${(config.goodsImage.path)!}/${(config.goodsImage.name)!}" />
        </#if>
            <#assign goods_url="${S_URL}/goods_${(goods.id)!}" />
        <#if (config.second_domain_open)!??>
            <#assign goods_url="http://${(goods.goods_store.storeDomain)!}.${domainPath!}/goods_${(goods.id)!}" />
        </#if>
        <dl>
          <dt>
              <span class="imgcenter_span ">
                  <p><a href="${goods_url!}" target="_blank"><img width="58" height="58" src="${img!}" /></a></p>
              </span>
          </dt>
          <dd>
              <span class="shop_rank_name">
                <a href="${goods_url!}" target="_blank">${CommUtil.substring("${(goods.goodsName)!}",8)}</a>
              </span>
              <span class="shop_rank_money">¥<b>${(goods.goodsPrice)!}</b></span>
              <span class="shop_rank_show">收藏：<strong>${(goods.goods_collect)!}</strong>次</span>
          </dd>
        </dl>
        </#list>
      </div>
    </div>
    <div class="shop_check">
        <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}" class="shop_check_a">查看本店其他商品</a>
    </div>
  </div>
  <div class="shopf">
    <h1>友情链接</h1>
    <ul>
      <#list partners as partner >
        <li><a href="${(partner.url)!}" target="_blank">${(partner.title)!}</a></li>
      </#list>
    </ul>
  </div>
</div>
