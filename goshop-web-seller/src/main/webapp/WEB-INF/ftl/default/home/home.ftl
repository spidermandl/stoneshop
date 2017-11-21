<#assign P_CURRENT_TOP='home' />
<#assign P_NAV1="商家中心" />
<#assign P_NAV2="首页" />
<#assign P_NAV3="" />
<@override name="main">
<div class="ncsc-layout wrapper">
    <#include "layout_home.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">

        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>