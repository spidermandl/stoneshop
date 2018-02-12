<#assign S_URL=request.contextPath />

<div class="menu-bd-panel">
  <div class="gwc">
    <#if cart!?size gt 0>
    <h1>购物车商品总价：¥<span id="cart_goods_price_top">${total_price!}</span></h1>
    <#list cart! as gc >
    <div class="shopp_ingtop" id="${(gc.id)!}">
      <div class="shopimg">
          <a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">
          <img src="${imageWebServer!}/${(gc.goods.goods_main_photo.path)!}/${(gc.goods.goods_main_photo.name)!}_small.${(gc.goods.goods_main_photo.ext)!}" width="40" height="40" />
          </a>
      </div>
      <div>
          <span class="shopl">
              <a href="${S_URL}/goods?id=${(gc.goodsId)!}" target="_blank">
                  ${CommUtil.substring("${(gc.goods.goods_name)!}",12)!}
                      <#if (gc.cart_type)!''=="combin"><span style="color:#F00;float:right;">[组合]</span></#if>
              </a>
          </span>
          <span style="color:#F60; padding-left:15px;">¥${(gc.price)!}×${(gc.count)!}</span>
      </div>
      <div><span class="shopl_del">
          <a href="javascript:void(0);" onclick="cart_remove('${(gc.id)!}','${(gc.goods.goods_store.id)!}');">删除</a>
      </span></div>
    </div>
    </#list>
    <#else>
    购物车暂时为空，赶紧挑选自己喜欢的商品去
    </#if>
    <#if cart!?size gt 0 >
    <div class="shopbtn"><a href="${S_URL}/goods_cart1">查看我的购物车</a></div>
    </#if>
  </div>
</div>
