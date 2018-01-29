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
});
</script>

<div class="shopindex_left">
    <div class="shopclassify">
      <h1>商品分类</h1>
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
              <#if (ugc.childs)!?size gt 0>
                  <ul id="ugc_child_${(ugc.id)!}">
                    <#list (ugc.childs)! as cugc >
                      <#if ((cugc.display)!false) == true >
                        <li><a href="${S_URL}/goods_list?gc_id=${(cugc.id)!}&store_id=${(store.storeId)!}">${(ugc.classname)!}</a></li>
                      </#if>
                    </#list>
                  </ul>
              </#if>
          </li>
          </#if>
        </#list>
      </ul>
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
  <div class="shopf">
    <h1>友情链接</h1>
    <ul>
        <#list partners as partner >
            <li>
                <a href="${(partner.url)!}" target="_blank">${(partner.title)!}</a>
            </li>
        </#list>
    </ul>
  </div>
</div>
