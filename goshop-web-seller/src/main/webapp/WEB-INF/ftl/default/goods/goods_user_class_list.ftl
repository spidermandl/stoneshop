<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="商品分类" />
<#assign P_CURRENT_OP="GoodsCategroy" />

<@override name="main">
<#--<link href="${S_URL}/static/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/static/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/static/style/common/css/jquery-ui-1.8.22.custom.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/static/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<link href="${S_URL}/static/styles/transport.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<div class="ncsc-layout wrapper">
  <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
          <@shiro.hasPermission name="seller:read">
              <div class="productmain" style="width: 910px;">
                <div class="pdctitle blue2">商品分类</div>
                <div class="addnewclass">
                    <div class="alldel">
                        <div class="alldel_l">
                            <span class="alldel_la">
                              <input name="all" type="checkbox" id="all" value="" onclick="selectAll(this);"/>
                            </span>
                            <span class="alldel_lb"><label for="all">全选</label></span>
                            <span  class="alldel_lc">
                                <a href="javascript:void(0);" onclick="cmd('${S_URL}/goods_category/goods_user_class_del','')" class="button_common">删除</a>
                            </span>
                        </div>
                        <div class="addnew">
                            <a class="button_common" href="javascript:void(0);" dialog_uri="${S_URL}/goods_category/goods_user_class_add?currentPage=${(currentPage)!}" dialog_title="新增分类" dialog_width="480" dialog_height="100" dialog_id="cart_address">新增分类</a>
                        </div>
                    </div>
                </div>
                <form action="" method="post" name="ListForm" id="ListForm">
                  <div class="operation">
                    <table width="900" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                        <tr id="opertitle">
                          <td class="classname">分类名称
                            <input name="mulitId" type="hidden" id="mulitId" /></td>
                          <td  class="sort" align="center">排序</td>
                          <td  class="show">显示</td>
                          <td align="center">操作</td>
                        </tr>
                        <#list objs! as obj>
                          <tr class="opertr">
                            <td class="classname" >
                                <span>
                                  <input name="id" type="checkbox" id="id" value="${(obj.id)!}" />
                                </span>
                                <span class="classname2">
                                  <#if ((obj.childs)!?size >0) >
                                      <img src="${S_URL}/static/images/goods/jian.jpg" width="14" height="14" />
                                  <#else>
                                      <img src="${S_URL}/static/images/goods/xiaji.jpg" />
                                  </#if>
                                </span>
                                <span class="classname3">${(obj.classname)!}</span>
                                <span class="classname4">
                                    <a href="javascript:void(0);" dialog_uri="${S_URL}/goods_category/goods_user_class_add?pid=${(obj.id)!}&currentPage=${currentPage!}" dialog_title="新增分类" dialog_width="480" dialog_height="100" dialog_id="cart_address">新增下级</a>
                                </span>
                            </td>
                            <td class="sort" align="center">${(obj.sequence)!}</td>
                            <td class="show"><img src="${S_URL}/static/images/goods/${(obj.display)?string("true","false")}.jpg" width="16" height="14" /></td>
                            <td class="operajt"><span class="edit"><a href="javascript:void(0);" dialog_uri="${S_URL}/goods_category/goods_user_class_edit?id=${(obj.id)!}&currentPage=${currentPage!}" dialog_title="编辑地址" dialog_width="480" dialog_height="100" dialog_id="cart_address">编辑</a></span>
                            <#--<#if ((obj.audit)!0) != 1 >-->
                              <#--<span class="del">-->
                                  <#--<a href="javascript:void(0);" onclick="if(confirm('是否确定删除商品分类?'))window.location.href='${S_URL}/goods_category/goods_user_class_del?mulitId=${(obj.id)!}'">删除</a>-->
                              <#--</span>-->
                            <#--</#if>-->
                            </td>
                          </tr>
                          <#list (obj.childs)! as child >
                              <tr class="opertr">
                                <td class="classname" >
                                    <span>
                                        <input name="id" type="checkbox" id="id" value="${(child.id)!}" />
                                    </span>
                                    <span class="classname2">&nbsp;</span>
                                    <span class="classname5">${(child.classname)!}</span>
                                </td>
                                <td class="sort" align="center">${(child.sequence)!}</td>
                                <td class="show"><img src="${S_URL}/static/images/goods/${(child.display)?string("true","false")}.jpg" width="16" height="14" /></td>
                                <td class="operajt">
                                    <span class="edit"><a href="javascript:void(0);" dialog_uri="${S_URL}/goods_category/goods_user_class_edit?id=${(child.id)!}&currentPage=${currentPage!}" dialog_title="编辑地址" dialog_width="480" dialog_height="100" dialog_id="cart_address">编辑</a></span>
                                    <span class="del"><a onclick="if(confirm('是否确定删除商品分类?'))window.location.href='${S_URL}/goods_category/goods_user_class_del?mulitId=${(child.id)!}'" href="javascript:void(0);">删除</a></span>
                                </td>
                              </tr>
                          </#list>
                        </#list>
                    </table>
                  </div>
                </form>
                <div class="alldel">
                  <div class="alldel_l"> <span class="alldel_la">
                    <input name="all1" type="checkbox" id="all1" value="" onclick="selectAll(this);"/>
                    </span><span class="alldel_lb"><label for="all">全选</label></span> <span  class="alldel_lc"><a href="javascript:void(0);" onclick="cmd('${S_URL}/goods_category/goods_user_class_del','')" class="button_common">删除</a></span> </div>
                  <div  class="userfenye">${gotoPageHTML!}</div>
                </div>
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
