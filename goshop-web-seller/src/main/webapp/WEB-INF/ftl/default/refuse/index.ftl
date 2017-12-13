<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="异常" />
<#assign P_NAV3="仓库中的商品" />
<#assign P_CURRENT_OP= "${refuse_type}" />

<@override name="main">
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<link href="${S_URL}/static/styles/shop/skin_0.css" rel="stylesheet" type="text/css">

<div class="ncsc-layout wrapper">
    <#include "layout_refuse.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <#if (P_CURRENT_OP!"")=="NoUser">
                <#include "../refuse/nouser.ftl"/>
            <#elseif (P_CURRENT_OP!"")=="NoShop">
                <#include "../refuse/noshop.ftl"/>
            </#if>

        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
