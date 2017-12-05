<#list objs! as obj>
    <table width="98%" border="0" cellspacing="0" cellpadding="0" id="opertable" >
        <tr>
            <td width="100px;">${(obj.transName)!}</td>
            <td >
                <span style=" margin-left:20px;display:block; border:1px #CCC solid; width:70px; text-align:center;">
                    <a href="javascript:void(0);" onClick="select_template('${(obj.transName)!}','${(obj.id)!}')">选择模板</a>
                </span>
            </td>
        </tr>
    </table>
    <table width="560" border="0" cellspacing="0" cellpadding="0" id="opertable">
      <#if ((obj.transType)!0)==0>
          <tr id="opertitle">
            <td width="12%" >配送方式</td>
            <td>配送区域</td>
            <td width="8%">首件(件)</td>
            <td width="8%">运费(¥)</td>
            <td width="8%">续件(件)</td>
            <td width="8%">运费(¥)</td>
          </tr>
      </#if>
      <#if ((obj.transType)!0)==1>
          <tr id="opertitle">
            <td width="12%" >配送方式</td>
            <td>配送区域</td>
            <td width="8%">首重(kg)</td>
            <td width="8%">运费(${(config.currency_code)!})</td>
            <td width="8%" >续重(kg)</td>
            <td width="8%" >运费(¥)</td>
          </tr>
      </#if>
      <#if ((obj.transType)!0)==2>
          <tr id="opertitle">
            <td width="12%" >配送方式</td>
            <td>配送区域</td>
            <td width="10%">首体积(m³)</td>
            <td width="10%">运费(¥)</td>
            <td width="10%">续体积(m³)</td>
            <td width="10%">运费(¥)</td>
          </tr>
      </#if>
      <#list (transportTools.query_all_transprot(obj.transMailInfo,0))! as info>
          <tr class="opertr" align="left">
              <td>平邮</td>
              <#assign city_name = info.value("city_name")/>
              <td>${(CommUtil.substring(city_name,22))!}</td>
              <td>${(info.value("trans_weight"))!}</td>
              <td>${(info.value("trans_fee"))!}</td>
              <td>${(info.value("trans_add_weight"))!}</td>
              <td>${(info.value("trans_add_fee"))!}</td>
          </tr>
      </#list>
      <#list (transportTools.query_all_transprot(obj.transExpressInfo,0))! as info>
          <tr class="opertr" align="left">
              <td>快递</td>
              <#assign city_name = info.value("city_name")/>
              <td>${(CommUtil.substring(city_name,22))!}</td>
              <td>${(info.value("trans_weight"))!}</td>
              <td>${(info.value("trans_fee"))!}</td>
              <td>${(info.value("trans_add_weight"))!}</td>
              <td>${(info.value("trans_add_fee"))!}</td>
          </tr>
      </#list>
      <#list (transportTools.query_all_transprot(obj.transEmsInfo,0))! as info>
          <tr class="opertr" align="left">
            <td>EMS</td>
              <#assign city_name = info.value("city_name")/>
              <td>${(CommUtil.substring(city_name,22))!}</td>
              <td>${(info.value("trans_weight"))!}</td>
              <td>${(info.value("trans_fee"))!}</td>
              <td>${(info.value("trans_add_weight"))!}</td>
              <td>${(info.value("trans_add_fee"))!}</td>
          </tr>
      </#list>
    </table>
</#list>
<div style="width:500px; float:right;">
  <div  class="userfenye" ajax_page="goods_transport"> ${gotoPageAjaxHTML!} </div>
</div>
