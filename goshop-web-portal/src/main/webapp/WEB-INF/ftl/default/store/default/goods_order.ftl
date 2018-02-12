<#if rows==0 >
 <div class="novalue"> 还没有成交记录 </div>
<#else>
<div class="okcode" id="strike_tab" style="margin-top:10px;">
  <ul>
     <li>
      <table width="797" border="0" cellspacing="0" cellpadding="0" class="okcodetable">
        <tr style=" background:#EEEDED; font-weight:bold">
          <td width="130">买家</td>
          <td width="80">商品价格</td>
          <td width="100">商品规格</td>
          <td width="80">购买数量</td>
          <td width="100">成交时间</td>
        </tr>
        <#list objs! as obj >
        <tr>
            <#assign credit=storeViewTools.generic_user_credit("${(obj.of.user.id)!}") />
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
            <#if credit gt 5 ><#assign credit=5 /></#if>
          <td class="blue" valign="middle" width="130">${(obj.of.user.userName)!}
              <#if credit==0 >
                  <img src="${imageWebServer!}/static/images/common/level_-1.gif"/>
              <#else>
                  <#list 1..credit as count >
                      <img style="margin-left:1px;" src="${img!}" />
                  </#list>
              </#if>
          <td><strong class="orange">¥${(obj.goods.storePrice)!}</strong></td>
          <td>${(obj.specInfo)!}</td>
          <td>${(obj.count)!}</td>
          <td>${(CommUtil.formatLongDate(obj.of.payTime))!}</td>
        </tr>
        </#list>
      </table>
    </li>
  </ul>
</div>
<div class="fenye">
  <div class="fenyes">${gotoPageAjaxHTML!}</div>
</div>
</#if>
