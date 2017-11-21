<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="店铺入驻" />
<#assign P_CURRENT_OP="StoreAdd" />
<#assign P_STEP=0 />

<@override name="main">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/store_joinin.css">
<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <#include "header.ftl"/>
            <div class="main">
                <#if P_STORE_JOIN.joininState=='10'>
                    <div class="explain"><i></i>入驻申请已经提交，请等待管理员审核</div>
                    <div class="bottom"></div>
                </#if>
                <#if P_STORE_JOIN.joininState=='11'>
                    <div class="explain"><i></i>已经提交，请等待管理员核对后为您开通店铺</div>
                    <div class="bottom"></div>
                </#if>
                <#if P_STORE_JOIN.joininState=='30'>
                    <div class="explain"><i></i>审核失败:${(P_STORE_JOIN.joininMessage)!}</div>
                    <div class="bottom">
                        <a class="btn" href="${S_URL}/store_join/step1.html">下一步</a>
                    </div>
                </#if>

                <#if P_STORE_JOIN.joininState=='20'>
                    <div class="explain"><i></i>审核成功，请完成付款，付款后点击下一步提交付款凭证</div>
                    <div class="bottom">
                        <a class="btn" href="${S_URL}/store_join/pay.html">下一步</a>
                    </div>
                </#if>


                <#if P_STORE_JOIN.joininState=='31'>
                    <div class="explain"><i></i>付款审核失败:${(P_STORE_JOIN.joininMessage)!}</div>
                    <div class="bottom">
                        <a class="btn" href="${S_URL}/store_join/pay.html">下一步</a>
                    </div>
                </#if>

                <#if P_STORE_JOIN.joininState=='40'>
                    <div class="explain"><i></i>付款审核成功，开店成功</div>
                    <div class="bottom">
                        <a class="btn" href="${S_URL}/store_join/verify.html">完成</a>
                    </div>
                </#if>

            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="../framework.ftl"/>
