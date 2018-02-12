<#assign S_URL=request.contextPath  />
<script>
jQuery(function(jQuery){
    jQuery(".collection a").click(function(){
    <#if user!?? >
      <#if ((user.id)!0) == ((obj.goods_store.user.id)!-1) >
          alert("不能收藏自己的店铺");
      <#else>
          jQuery.post("${S_URL}/add_store_favorite",{"id":"${store.storeId}"},function(data){
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
    jQuery(".twocoad").css("cursor","pointer").mouseover(function(){
       jQuery(".shop_botmhbox").fadeIn('fast');
    }).mouseleave(function(){
       jQuery(".shop_botmhbox").fadeOut('fast');
    });
    //
});
</script>

<div class="shopindex_left">
  <div class="shoptop">
    <h1>${store.storeName}</h1>
    <div class="shopvalue">
      <dl class="shopvdl">
          <dt><span>
            <#if (store.logo)!?? >
              <#assign store_logo="${RES_URL}/${(store.logo.path)!}/${(store.logo.name)!}" />
            <#else>
              <#assign store_logo="${imageWebServer!}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
            </#if><img src="${store_logo!}" width="60" height="60" />
          </span></dt>
      <#assign credit=storeViewTools.generic_store_credit("${(store.storeId)!}") />
      <#assign img="${imageWebServer!}/static/images/common/level_0.gif" />
      <#if credit lt 10 && credit gt 0>
        <#assign credit = credit / 2 />
        <#assign credit = credit+1 />
      </#if>
      <#if credit gte 20 >
        <#assign img="${imageWebServer!}/static/images/common/level_2.gif" />
        <#assign credit=(credit - 20)/2 />
        <#assign credit = credit+1 />
      </#if>
      <#if credit gte 10 >
        <#assign img="${imageWebServer!}/static/images/common/level_1.gif" />
        <#assign credit=(credit - 10)/2 />
        <#assign credit=credit+1 />
      </#if>
      <#if credit gt 5 > <#assign credit=5 /></#if>
        <dd>
            <span >${(store.memberId)!}
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
      <h1>店铺信息</h1>
      <ul>
          <li>创店时间：${CommUtil.formatShortDate(store.storeTime)}</li>
          <li>所在地区：${areaViewTools.generic_area_info("${(store.area.id)!}")}</li>
          <li>店铺类型：${(store.sc.name)!}</li>
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
            <span>店铺客服：</span>
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
                <#else >
                dialog_uri="${S_URL}/user_dialog_login" dialog_title="会员登录" dialog_width="450" dialog_height="100" dialog_id="user_login"
                </#if>>
                <span>收藏店铺</span>
            </a>
        </li>
        <li class="this"><span class="twocoad">店铺二维码
          <div class="shop_botmhbox" style="display:none;">
              <span>
                  <img src="${imageWebServer!}/${(config.uploadFilePath)!}/store/${(store.storeId)!}/code.png" width="140" height="140" />
              </span><i>手机扫描二维码<br />
            快速收藏店铺</i></div>
          </span></li>
      </ul>
    </div>
  </div>
</div>
