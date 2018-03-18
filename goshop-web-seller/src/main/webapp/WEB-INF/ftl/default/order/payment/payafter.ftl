<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="支付方式" />
<#assign P_CURRENT_OP="PaymentSetting" />

<@override name="main">
<link href="${S_URL}/static/styles/public.css" type="text/css" rel="stylesheet" />
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<link href="${S_URL}/static/styles/overlay.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>

<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<script>
jQuery(document).ready(function(){
   jQuery(":radio[name=install][value=${((obj.install)!false)?string('true','false')}]").prop("checked",true);
});
</script>
<div class="ncsc-layout wrapper">
  <#include "../layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <div class="productmain">
                <div class="ordernav">
                  <ul class="orderul">
                    <li><a href="${S_URL}/payment">支付方式</a></li>
                    <li class="this">支付配置</li>
                  </ul>
                </div>
                <div class="ordercon">
                    <form action="${S_URL}/payment_save" method="post" name="theForm" id="theForm">
                        <table width="705" border="0" cellspacing="0" cellpadding="0" class="addnavtable">
                            <tr>
                                <td width="100" align="right" valign="top">支付方式名称 ：</td>
                                <td class="px10 message">货到付款
                                    <input name="mark" type="hidden" id="mark" value="payafter" />
                                    <input name="id" type="hidden" id="id" value="${(obj.id)!}" /></td>
                            </tr>
                            <tr>
                                <td width="100" align="right" valign="top">简介：</td>
                                <td class="px10 message">
                                    <textarea name="content" cols="40" rows="6" id="content">${(obj.content)!}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">是否启用：</td>
                                <td class="px10"><input type="radio" name="install" value="true" />
                                    启用
                                    <input type="radio" name="install" value="false" />
                                    禁用
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="px10"><span class="setsub">
                                    <input name="提交" type="submit"  value="保存"  style="cursor:pointer;"/>
                                </span></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../../framework.ftl"/>
