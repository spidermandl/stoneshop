<@override name="main">
<#assign P_NAV1="我的商城" />
<#assign P_NAV2="买家中心" />
<#assign P_NAV3="错误" />
<#assign P_SIDEBAR = "ERROR" />

<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<script>
    jQuery(document).ready(function(){

    });
</script>
<#include "../nav.ftl"/>
<div class="main">
    <style type="text/css">
        dl dd span {
            display: inline-block;
        }
    </style>

    <div class="wrap">

    </div>
</div>
</@override>
<@extends name="framework.ftl"/>
