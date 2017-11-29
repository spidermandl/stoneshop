<#assign P_CURRENT_TOP='transport' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="物流" />
<#assign P_NAV3="物流工具" />
<#assign P_CURRENT_OP="TransportList" />

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
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
          <@shiro.hasPermission name="seller:read">
          <div class="productmain">
              <div class="ordernav">
                  <ul class="orderul">
                      <li class="this"><span class="position">运费模板列表</span></li>
                  </ul>
              </div>
              <form action="${S_URL}/transport/transport_list" method="post" id="ListForm">
                <div class="ordercon">
                    <div class="addnewclass">
                        <div class="addnew">
                            <a href="${S_URL}/transport/transport_add" class="button_common">新增运费模板</a>
                        </div>
                    </div>
                    <div class="operation">
                        <#list objs! as obj>
                          <table width="98%" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                            <tr>
                              <td >${(obj.trans_name)!}
                                  <span style="float:right;">最后编辑时间:$!CommUtil.formatLongDate(${(obj.addTime)!})
                                      <a href="${S_URL}/transport/transport_copy?id=${(obj.id)!}">复制模板</a>|
                                      <a href="${S_URL}/transport/transport_edit?id=${(obj.id)!}">编辑</a>|
                                      <a href="javascript:void(0);" onclick="if(confirm('删除后不可恢复，是否继续?'))window.location.href='${S_URL}/transport/transport_del?mulitId=${(obj.id)!}&currentPage=${currentPage!}'">删除</a>
                                  </span></td>
                            </tr>
                          </table>
                          <table width="98%" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                              <#if ((obj.trans_type)!0)==0>
                                <tr id="opertitle">
                                  <td width="12%" >配送方式</td>
                                  <td>配送区域</td>
                                  <td width="8%">首件(件)</td>
                                  <td width="8%">运费(¥)</td>
                                  <td width="8%" align="center">续件(件)</td>
                                  <td width="8%" align="center">运费(¥)</td>
                                </tr>
                              </#if>
                              <#if ((obj.trans_type)!0)==1>
                                <tr id="opertitle">
                                  <td width="12%" >配送方式</td>
                                  <td>配送区域</td>
                                  <td width="8%">首重(kg)</td>
                                  <td width="8%">运费(¥)</td>
                                  <td width="8%" align="center">续重(kg)</td>
                                  <td width="8%" align="center">运费(¥)</td>
                                </tr>
                              </#if>
                              <#if ((obj.trans_type)!0)==2>
                                <tr id="opertitle">
                                  <td width="12%" >配送方式</td>
                                  <td>配送区域</td>
                                  <td width="8%">首体积(m³)</td>
                                  <td width="8%">运费(¥)</td>
                                  <td width="8%" align="center">续体积(m³)</td>
                                  <td width="8%" align="center">运费(¥)</td>
                                </tr>
                              </#if>
                              <#list (transportTools.query_all_transprot(obj.trans_mail_info,0))! as info>
                                <tr class="opertr">
                                  <td>平邮</td>
                                  <td>${(info.value("city_name"))!}</td>
                                  <td>${(info.value("trans_weight"))!}</td>
                                  <td>${(info.value("trans_fee"))!}</td>
                                  <td>${(info.value("trans_add_weight"))!}</td>
                                  <td>${(info.value("trans_add_fee"))!}</td>
                                </tr>
                              </#list>
                              <#list (transportTools.query_all_transprot(obj.trans_express_info,0))! as info>
                                <tr class="opertr">
                                  <td>快递</td>
                                  <td>${(info.value("city_name"))!}</td>
                                  <td>${(info.value("trans_weight"))!}</td>
                                  <td>${(info.value("trans_fee"))!}</td>
                                  <td>${(info.value("trans_add_weight"))!}</td>
                                  <td>${(info.value("trans_add_fee"))!}</td>
                                </tr>
                              </#list>
                              <#list (transportTools.query_all_transprot(obj.trans_ems_info,0))! as info>
                                <tr class="opertr">
                                  <td>EMS</td>
                                  <td>${(info.value("city_name"))!}</td>
                                  <td>${(info.value("trans_weight"))!}</td>
                                  <td>${(info.value("trans_fee"))!}</td>
                                  <td>${(info.value("trans_add_weight"))!}</td>
                                  <td>${(info.value("trans_add_fee"))!}</td>
                                </tr>
                              </#list>
                          </table>
                        </#list>
                    </div>
                    <div class="alldel">
                        <div  class="userfenye"> ${gotoPageFormHTML!} </div>
                    </div>
                </div>
              </form>
          </div>

          </@shiro.hasPermission>
          <@shiro.lacksPermission name="seller:read">
              <#include "../noshop.ftl"/>
          </@shiro.lacksPermission>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
