<#assign S_URL=request.contextPath  />
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
    <#list objs as obj>
    <tr>
        <#if ((obj.evaluate_buyer_val)!-2)==1 >
          <#assign evaluate_buyer_val="好评" />
        </#if>
        <#if ((obj.evaluate_buyer_val)!-2)==0 >
          <#assign evaluate_buyer_val="中评" />
        </#if>
        <#if ((obj.evaluate_buyer_val)!-2)==-1 >
          <#assign evaluate_buyer_val="差评" />
        </#if>
      <td>${evaluate_buyer_val!}</td>
      <td><#if obj.evaluate_info!="" >${(obj.evaluate_info)!} <#else> 这家伙什么都没说 </#if></td>
      <td class="goodsevalu">
          <span class="nameevalu">
              <a href="${S_URL}/goods_${(obj.evaluate_goods.id)!}" target="_blank">${(obj.evaluate_goods.goods_name)!}</a>
          </span>
      </td>
      <td align="center"><strong class="orange">¥${(obj.of.totalPrice)!}</strong></td>
      <td align="center">${(obj.evaluate_user.userName)!}</td>
      <td align="center">${CommUtil.formatLongDate(obj.addTime)}</td>
    </tr>
    </#list>
  </table>
</div>
<div class="fenye">
  <div class="fenyes">${gotoPageAjaxHTML!}</div>
</div>
