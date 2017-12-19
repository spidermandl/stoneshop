<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="导航列表" />
<#assign P_CURRENT_OP="StoreNavigation" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
    <div class="ncsc-layout wrapper">
      <#include "layout_store.ftl"/>
        <div class="ncsc-layout-right" id="layoutRight">
          <#include "../nav.ftl"/>
            <div id="mainContent" class="main-content">
                <form action="${S_URL}/store/store_nav" method="post" name="ListForm" id="ListForm">
                  <div class="productmain">
                      <div class="ordernav">
                        <ul class="orderul">
                          <li class="this"><a href='${S_URL}/store/store_nav'>导航列表</a></li>
                          <li><a href='${S_URL}/store/store_nav_add' class="other">新增导航</a></li>
                        </ul>
                      </div>
                      <div class="ordercon" style="width: 910px">
                          <div class="alldel">
                              <div class="alldel_l">
                                  <span class="alldel_la">
                                      <input name="all" type="checkbox" id="all" value=""  onclick="selectAll(this)"/>
                                  </span>
                                  <span class="alldel_lb">
                                      <label for="all">全选</label>
                                  </span>
                                  <span class="alldel_lc">
                                      <a href="javascript:void(0);" onclick="cmd('${S_URL}/store/store_nav_del','')">删除</a>
                                  </span>
                              </div>
                          </div>
                          <div class="operation">
                            <table width="900" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                              <tr id="opertitle">
                                <td width="470" >导航标题<input name="mulitId" type="hidden" id="mulitId" />
                               </td>
                                <td width="50">是否显示</td>
                                <td width="60" align="center">序号</td>
                                <td align="center">操作</td>
                              </tr>
                              <#list objs as obj>
                              <tr class="opertr">
                                <td >
                                    <input name="id" type="checkbox" value="${(obj.id)!}" id="id" />
                                  　${(obj.title)!}</td>
                                <#assign display="是" />
                                <#if ((obj.display)!false) == false>
                                    <#assign display="否" />
                                </#if>
                                <td align="center">${display!}</td>
                                <td align="center">${(obj.sequence)!}</td>
                                <td class="operajt">
                                    <span class="edit">
                                        <a href="${S_URL}/store/store_nav_edit?id=${(obj.id)!}">编辑</a>
                                    </span>
                                    <span class="del">
                                        <a href="${S_URL}/store/store_nav_del?mulitId=${(obj.id)!}&currentPage=${currentPage!}">删除</a>
                                    </span>
                                </td>
                              </tr>
                              </#list>
                            </table>
                          </div>
                          <div class="alldel">
                              <div class="alldel_l">
                                  <span class="alldel_la">
                                      <input name="all1" type="checkbox" id="all1" value=""  onclick="selectAll(this)"/>
                                  </span>
                                  <span class="alldel_lb">
                                      <label for="all">全选</label>
                                  </span>
                                  <span  class="alldel_lc">
                                      <a href="javascript:void(0);" onclick="cmd('${S_URL}/store/store_nav_del','')">删除</a>
                                  </span>
                              </div>
                              <div  class="userfenye">${gotoPageHTML!}</div>
                          </div>
                      </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@override>

<@extends name="../framework.ftl"/>
