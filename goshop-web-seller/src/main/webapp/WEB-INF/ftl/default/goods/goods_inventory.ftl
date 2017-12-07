<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabledetail" >
    <tr style="background:#CCC">
    <#list specs! as spec>
        <td width="27%">${(spec.name)!}</td>
    </#list>
    <td width="27%">库存</td>
    <td width="46%">价格</td>
    </tr>
    <#list gsps! as gsp>
        <tr>
        <#assign id="" />
        <#list gsp! as c_gsp>
            <#assign id = c_gsp.id+"_"+id/>
            <td>${(c_gsp.value)!}</td>
        </#list>
        <td><input name="${id!}" type="text" id="inventory_details_count_${id!}" style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="0"></td>
        <td><input name="${id!}" type="text" id="inventory_details_price_${id!}" style="border:1px solid #A7A6AA; height:20px; line-height:20px; padding-left:5px;margin-bottom:5px;" value="0"></td>
      </tr>
    </#list>
</table>
