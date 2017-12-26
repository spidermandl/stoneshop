<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="子账户管理" />
<#assign P_CURRENT_OP="StoreSubAccount" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/jquery-ui-1.8.22.custom.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.poshytip.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<script src="${S_URL}/static/scripts/jquery/jquery.poshytip.min.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/jquery.shop.common.js"></script>

<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
        <div class="productmain">
            <div class="pdctitle blue2">子账户列表[当期店铺等级允许${(store.storeGrade.sgAccountNum)!}个子账户]</div>
            <div class="alldel">
              <div class="right">
               <a href="${S_URL}/store/sub_account_add" class="button_common">新增子账户</a>
              </div>
            </div>
            <form action="${S_URL}/store/sub_account_list" method="post" id="ListForm">
            <div class="operation">
              <table width="942" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                <tr id="opertitle">
                  <td width="115">用户名</td>
                  <td width="115">真实姓名</td>
                  <td width="162">最后一次登录</td>
                  <td width="111">联系电话</td>
                  <td width="88">手机号码</td>
                  <td width="211" align="left">操作</td>
                </tr>
                <#list objs as obj >
                <tr class="opertr" id="${(obj.id)!}">
                  <td width="115">${(obj.userName)!}</td>
                  <td width="115">${(obj.trueName)!}</td>
                  <#assign address="${(obj.area.parent.parent.areaName)!} ${(obj.area.parent.areaName)!} ${(obj.area.areaName)!}" />
                  <td width="162" area_info_id="${(obj.id)!}">
                      <#if (obj.oldLoginTime)??>${(CommUtil.formatLongDate(obj.oldLoginTime))!}
                      <#else> 暂无登录记录
                      </#if>
                  </td>
                  <td width="111">${(obj.telephone)!}</td>
                  <td width="88">${(obj.mobile)!}</td>
                  <td align="left">
                      <span class="edit">
                          <a href="${S_URL}/store/sub_account_edit?id=${(obj.id)!}&currentPage=${currentPage!}">编辑</a>
                      </span>
                      <span class="del">
                          <a href="javascript:void(0);" onclick="if(confirm('删除后不可恢复，是否继续?'))window.location.href='${S_URL}/store/sub_account_del?mulitId=${(obj.id)!}&currentPage=${currentPage!}'">删除</a>
                      </span>
                  </td>
                </tr>
                </#list>
              </table>
            </div>
            <div class="alldel">
              <div class="userfenye">${gotoFormPageHTML!}</div>
            </div>
            </form>
          </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
