<#assign P_CURRENT_TOP='transport' />
<#assign P_CURRENT_OP="SUCCESS_NOTICE" />

<@override name="main">
<#--<link href="${S_URL}/static/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/static/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/static/style/common/css/jquery-ui-1.8.22.custom.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/static/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<link href="${S_URL}/static/styles/transport.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<div class="ncsc-layout wrapper">
    <#include "layout_transport.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <div id="mainContent" class="main-content">
            <div class="index" style="height:300px;">
                <div style="height:280px;border:1px solid #CCCCCC;overflow:hidden; margin-left:auto; margin-right:auto; margin-top:10px;">
                    <div style="font-size:16px; color:#666666;margin-top:100px; padding-left:40%;">
                        <span style="float:left;">
                            <img src="${S_URL}/static/images/succeed.png" />
                        </span>
                        <span style=" padding-top:10px; line-height:30px; float:left;">${op_title!}</span>
                    </div>
                </div>
            </div>
            <#--<script>-->
                <#--var count=3;-->
                <#--window.setInterval(go,1000);-->
                <#--function go(){-->
                    <#--count--;-->
                    <#--if(count==0) window.location.href="${url!}";-->
                <#--}-->
            <#--</script>-->
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
