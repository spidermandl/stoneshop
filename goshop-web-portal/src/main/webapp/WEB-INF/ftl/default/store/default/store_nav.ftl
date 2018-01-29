<#assign S_URL=request.contextPath  />
<div class="shopnavul">
  <ul>
    <li id="store_index">
        <a href="${S_URL}/store?id=${(store.storeId)!}" >首页</a>
    </li>
    <#if (goods_view?string) == 'true' >
    <li id="store_goods" class="this"><a href="${S_URL}/goods_${goods_id!}">商品详情</a></li>
    </#if>
    <#if (goods_list?string) == 'true' >
        <li id="store_goods" class="this">
            <a href="${S_URL}/goods_list?store_id=${(store.storeId)!}">商品列表</a>
        </li>
    </#if>
    <li id="store_credit"> <a href="${S_URL}/store_credit?id=${(store.storeId)!}">信用评价</a></li>
    <li id="store_info"><a href="${S_URL}/store_info?id=${(store.storeId)!}">店铺详情</a></li>
    <#list navs as nav>
      <#assign url="${S_URL}/store_url?id=${(nav.id)!}" />
      <#if (nav.url)!?? && (nav.url) != "" >
        <#assign url="${(nav.url)!}" />
      </#if>
    <li id="${(nav.id)!}">
        <a href="${url!}" <#if (nav.win_type)! == "new_win"> target="_blank" </#if>>${(nav.title)!}</a>
    </li>
    </#list>
  </ul>
</div>
