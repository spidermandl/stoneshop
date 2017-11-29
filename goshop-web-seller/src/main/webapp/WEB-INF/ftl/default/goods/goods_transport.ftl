<#assign S_URL=request.contextPath />
<script>
function select_template(name,id){
  jQuery("#transport_template_name").html(name);
  jQuery("#transport_id").val(id);
  jQuery("#transport_template_frm").remove();
}
</script>

<table width="580" border="0" cellspacing="0" cellpadding="0" class="user_table">
  <tr>
    <td id="centerbg" valign="top">
        <form action="${S_URL}/transport/transport_list.htm" method="post" id="ListForm">
            <#list objs as obj>
              <table width="98%" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                <tr>
                  <td width="100px;">${(obj.trans_name)!}</td>
                  <td ><span style=" margin-left:20px;display:block; border:1px #CCC solid; width:70px; text-align:center;">
                      <a href="javascript:void(0);" onClick="select_template('${(obj.trans_name)!}','${(obj.id)!}')">选择模板</a></span>
                  </td>
                </tr>
              </table>
              <table width="560" border="0" cellspacing="0" cellpadding="0" id="opertable">
                <#if ((obj.trans_type)!0)==0>
                  <tr id="opertitle">
                    <td width="12%" >配送方式</td>
                    <td>配送区域</td>
                    <td width="8%">首件(件)</td>
                    <td width="8%">运费(¥)</td>
                    <td width="8%">续件(件)</td>
                    <td width="8%">运费(¥)</td>
                  </tr>
                </#if>
                <#if ((obj.trans_type)!0)==1>
                  <tr id="opertitle">
                    <td width="12%" >配送方式</td>
                    <td>配送区域</td>
                    <td width="8%">首重(kg)</td>
                    <td width="8%">运费(${(config.currency_code)!})</td>
                    <td width="8%" >续重(kg)</td>
                    <td width="8%" >运费(¥)</td>
                  </tr>
                </#if>
                <#if ((obj.trans_type)!0)==2>
                  <tr id="opertitle">
                    <td width="12%" >配送方式</td>
                    <td>配送区域</td>
                    <td width="10%">首体积(m³)</td>
                    <td width="10%">运费(¥)</td>
                    <td width="10%">续体积(m³)</td>
                    <td width="10%">运费(¥)</td>
                  </tr>
                </#if>
                <#list (transportTools.query_all_transprot(obj.trans_mail_info,0))! as info>
                    <tr class="opertr" align="left">
                        <td>平邮</td>
                        <#assign city_name = info.value("city_name")/>
                        <td>${(CommUtil.substring("${city_name!}",22))!}</td>
                        <td>${(info.value("trans_weight"))!}</td>
                        <td>${(info.value("trans_fee"))!}</td>
                        <td>${(info.value("trans_add_weight"))!}</td>
                        <td>${(info.value("trans_add_fee"))!}</td>
                    </tr>
                </#list>
                <#list (transportTools.query_all_transprot(obj.trans_express_info,0))! as info>
                    <tr class="opertr" align="left">
                        <td>快递</td>
                        <#assign city_name = info.value("city_name")/>
                        <td>${(CommUtil.substring("${city_name!}",22))!}</td>
                        <td>${(info.value("trans_weight"))!}</td>
                        <td>${(info.value("trans_fee"))!}</td>
                        <td>${(info.value("trans_add_weight"))!}</td>
                        <td>${(info.value("trans_add_fee"))!}</td>
                    </tr>
                </#list>
                <#list (transportTools.query_all_transprot(obj.trans_ems_info,0))! as info>
                    <tr class="opertr" align="left">
                        <td>EMS</td>
                        <#assign city_name = info.value("city_name")/>
                        <td>${(CommUtil.substring("${city_name!}",22))!}</td>
                        <td>${(info.value("trans_weight"))!}</td>
                        <td>${(info.value("trans_fee"))!}</td>
                        <td>${(info.value("trans_add_weight"))!}</td>
                        <td>${(info.value("trans_add_fee"))!}</td>
                    </tr>
                </#list>
              </table>
            </#list>
            <script>
            alert("${gotoPageAjaxHTML!}");
            </script>
            <div style="width:500px; float:right;">
              <div  class="userfenye" ajax_page="goods_transport">${gotoPageAjaxHTML!} </div>
            </div>
        </form>
    </td>
  </tr>
</table>
