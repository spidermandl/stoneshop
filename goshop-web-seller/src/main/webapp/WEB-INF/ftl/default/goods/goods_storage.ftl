<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="仓库中的商品" />
<#assign P_CURRENT_OP="GoodsStorage" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.poshytip.min.js"></script>-->
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery/jquery.poshytip.min.js"></script>
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<div class="ncsc-layout wrapper">
  <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
          <@shiro.hasPermission name="seller:read">
              <form action="${S_URL}/goods/goods_storage" method="post" name="ListForm" id="ListForm">
                  <div class="productmain" style="width: 910px;">
                      <div class="pdctitle blue2">仓库中的商品
                          <span class="classname">
                              <input name="mulitId" type="hidden" id="mulitId" />
                              <input name="op" type="hidden" id="op" value="storage" />
                          </span>
                      </div>
                      <div class="alldel">
                          <div class="alldel_r">
                              <span class="alldelsel">商品分类
                                  <select name="user_class_id" id="user_class_id">
                                    <option value="">请选择商品分类...</option>
                                    <#list (goodsViewTools.query_user_class("",uid))! as user_class>
                                        <option value="${(user_class.id)!}">${(user_class.classname)!}</option>
                                           <#list (user_class.childs)! as c_user_class>
                                        <option value="${(c_user_class.id)!}">&nbsp;&nbsp;${(c_user_class.classname)!}</option>
                                        </#list>
                                    </#list>
                                  </select>
                              </span>
                              <span class="alldelsear">　商品名称
                                  <input name="goods_name" type="text" id="goods_name" />
                              </span>
                              <span class="alldelbtn">
                                  <input name="" type="button"  value="搜索" style="cursor:pointer;" onclick="query();"/>
                          </span>
                          </div>
                          <div class="alldel_l">
                              <span class="alldel_la">
                                  <input name="all" type="checkbox" id="all" value=""  onclick="selectAll(this)"/>
                              </span>
                              <span class="alldel_lb">
                                  <label for="all">全选</label>
                              </span>
                              <span class="alldel_lc">
                                  <a class="button_common"  href="javascript:void(0);" onclick="cmd('${S_URL}/goods/goods_del','')">删除</a>
                              </span>
                              <span class="alldel_ld1">
                                  <a class="button_common" href="javascript:void(0);" onclick="cmd('${S_URL}/goods/goods_sale','')">上架</a>
                              </span>
                          </div>
                      </div>
                      <div class="operation">
                          <table width="900" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                              <tr id="opertitle">
                                  <td class="proname" width="220">商品名称</td>
                                  <td width="217" class="proclassify">商品分类</td>
                                  <td width="130" class="promoney">价格</td>
                                  <td width="51"  class="prorec">推荐</td>
                                  <td width="240" align="center">操作</td>
                              </tr>
                              <#list objs! as obj >
                              <tr class="opertr">
                                  <td class="proname" valign="middle" >
                                      <span class="checkpro">
                                          <input type="checkbox" value="${(obj.id)!}" />
                                            <#if (obj.goods_main_photo)?? >
                                              <#assign img="${S_URL}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}" />
                                            <#else>
                                              <#assign img="${S_URL}/static/images/goods/img.jpg" />
                                            </#if>
                                      </span>
                                      <span class="imgpro">
                                          <img src="${img!}" width="60" height="63" /></span>
                                      <span class="nameproduct">
                                          <a href="${S_URL}/goods_${(obj.id)!}.htm" target="_blank">${(obj.goodsName)!}</a>
                                      </span>
                                  </td>
                                  <td class="proclassify"><#if (obj.gc)?? >${(storeTools.generic_goods_class_info(obj.gc))!}</#if></td>
                                  <td class="promoney">${(obj.storePrice)!}</td>
                                  <td class="prorec">
                                      <img src="${S_URL}/static/images/goods/${((obj.goodsRecommend)!false)?string("true","false")}.jpg" width="16" height="14" />
                                  </td>
                                  <td class="operajt">
                                      <span class="sale">
                                          <a href="${S_URL}/goods/goods_sale?mulitId=${(obj.id)!}">上架</a>
                                      </span>
                                      <span class="edit">
                                          <a href="${S_URL}/goods/goods_edit?id=${(obj.id)!}">编辑</a> <!--target="_blank" -->
                                      </span>
                                      <span class="del">
                                          <a href="javascript:if(confirm('删除后不可恢复?'))window.location.href='${S_URL}/goods/goods_del?mulitId=${(obj.id)!}&op=storage'">删除</a>
                                      </span>
                                  </td>
                              </tr>
                              </#list>
                          </table>
                      </div>
                      <div class="alldel">
                          <div  class="userfenye">${gotoPageHTML!}</div>
                          <div class="alldel_l">
                              <span class="alldel_la">
                                  <input name="all1" type="checkbox" id="all1" value="" onclick="selectAll(this)" />
                              </span>
                              <span class="alldel_lb">
                                  <label for="all">全选</label>
                              </span>
                              <span class="alldel_lc">
                                  <a class="button_common" href="javascript:void(0);" onclick="cmd('${S_URL}/goods/goods_del','')">删除</a>
                              </span>
                              <span class="alldel_ld1">
                                  <a href="javascript:void(0);" onclick="cmd('${S_URL}/goods/goods_sale','')" class="button_common">上架</a>
                              </span>
                          </div>
                      </div>
                  </div>
              </form>
          </@shiro.hasPermission>
          <@shiro.lacksPermission name="seller:read">
            <#include "../noshop.ftl"/>
          </@shiro.lacksPermission>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
