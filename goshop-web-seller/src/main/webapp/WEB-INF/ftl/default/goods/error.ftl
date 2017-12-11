<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="错误" />
<#--<#assign P_CURRENT_OP="GoodsAdd" />-->
<#--<#assign P_STEP=3 />-->
<@override name="main">
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<#--<link href="$!webPath/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="$!webPath/resources/style/system/front/default/css/index.css" type="text/css" rel="stylesheet" />-->
<#--<link href="$!webPath/resources/style/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />-->
<div class="ncsc-layout wrapper">
  <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
          <#--<#include "setp.ftl" />-->
          <div class="main">
              <div class="index" style="height:300px;">
                  <div style="height:280px;border:1px solid #CCCCCC;overflow:hidden; margin-left:auto; margin-right:auto; margin-top:10px;">
                      <div style="font-size:16px; color:#666666;margin-top:100px; padding-left:40%;">
                      <span style="float:left;">
                      <img src="${S_URL}/static/images/goods/error.png" />
                      </span>
                      <span style=" padding-top:10px; line-height:30px; float:left;">$!{op_title}！</span>
                      </div>
                  </div>
              </div>
          </div>
          <#if url??>
            <script>
              var count=3;
              window.setInterval(go,1000);
              function go(){
                count--;
                if(count==0) window.location.href="${url!}";
              }
            </script>
          </#if>

        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
