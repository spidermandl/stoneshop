<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="品牌申请" />
<#assign P_CURRENT_OP="GoodsBrandRequest" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.zh.cn.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.poshytip.min.js"></script>-->
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery/jquery.poshytip.min.js"></script>
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<div class="ncsc-layout wrapper">
  <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
              <div class="productmain">
                  <div class="ordernav">
                      <ul class="orderul">
                          <li class="this"><a href="${S_URL}/brand/usergoodsbrand_list">品牌列表</a></li>
                          <li ><a href="${S_URL}/brand/usergoodsbrand_add">申请品牌</a></li>
                      </ul>
                  </div>
              <div class="ordercon">
                  <form method="post" name="ListForm" id="ListForm" action="${S_URL}/brand/usergoodsbrand_list">
                      <div class="operation">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                              <tr id="opertitle">
                                  <td width="139">品牌名称</td>
                                  <td width="128">品牌标志</td>
                                  <td width="125">审核状态</td>
                                  <td width="100" align="left">操作</td>
                              </tr>
                              <#list objs! as obj >
                                  <#if ((obj.audit)!0)==-1>
                                      <#assign status = "审核未通过" />
                                  </#if>
                                  <#if ((obj.audit)!0)==0>
                                      <#assign status = "待审核" />
                                  </#if>
                                  <#if ((obj.audit)!0)==1>
                                      <#assign status = "审核通过" />
                                  </#if>
                                  <tr>
                                      <td>${(obj.name)!}</td>
                                      <td><img src="${S_URL}/${(obj.brandLogo.path)!}/${(obj.brandLogo.name)!}" height="44px" width="88px"/></td>
                                      <td>${status!}</td>
                                      <td align="left">
                                          <#if ((obj.audit)!0)==-1>
                                              <a href="${S_URL}/brand/usergoodsbrand_del?id=${(obj.id)!}&currentPage=${currentPage!}">删除</a>
                                          </#if>
                                          <#if ((obj.audit)!0)==0>
                                              <a href="${S_URL}/brand/usergoodsbrand_edit?id=${(obj.id)!}">编辑</a> |
                                              <a href="${S_URL}/brand/usergoodsbrand_del?id=${(obj.id)!}&currentPage=${currentPage!}">删除</a>
                                          </#if>
                                      </td>
                                  </tr>
                              </#list>
                          </table>
                      </div>
                      <input id="currentPage" name="currentPage" type="hidden" value="$!currentPage"/>
                      <div class="alldel">
                          <div  class="userfenye">${gotoPageFormHTML!}</div>
                      </div>
                  </form>
              </div>
        </div>
        </div>
    </div>
</@override>

<@extends name="../framework.ftl"/>
