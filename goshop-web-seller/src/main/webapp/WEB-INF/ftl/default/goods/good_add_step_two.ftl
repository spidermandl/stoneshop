<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="商品发布" />
<#assign P_CURRENT_OP="GoodsAdd" />
<#assign P_STEP=2 />
<@override name="main">
<script src="${S_URL}/static/scripts/goods/store_goods_add.step2.js"></script>
<div class="ncsc-layout wrapper">
    <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <#include "setp.ftl" />

            <div class="wrapper_search">
                <div class="wp_sort">
                    <form action="${S_URL}/goods_add/step3.html" method="post" enctype="multipart/form-data" name="theForm" id="theForm">
                        <table width="1200" border="0" cellspacing="0" cellpadding="0" class="tabledetail" >
                            <tr>
                                <td colspan="2" class="tableh1">商品详细信息
                                    <input name="goods_commonid" type="hidden" id="goods_commonid" value="${(obj.id)!}" /></td>
                            </tr>
                            <tr>
                                <td width="95" align="right">商品分类：</td>
                                <td><span>${(goods_class_info)!}</span><span class="editbtn">
                                    <input name="goods_class_id" type="hidden" id="goods_class_id" value="${goods_class.id}" />
                                    <input name="goods_main_img_id" type="hidden" id="goods_main_img_id" />
                                    <input name="image_ids" type="hidden" id="image_ids" />
                                    <input name="user_class_ids" type="hidden" id="user_class_ids" />
                                    <input name="goods_spec_ids" type="hidden" id="goods_spec_ids" />
                                    <input name="goods_properties" type="hidden" id="goods_properties" />
                                    <input type="hidden" name="inventory_details" id="inventory_details" />
                                    <input name="edit_class" type="button" id="edit_class" value="编辑" style="cursor:pointer;" onclick="window.location.href='$!webPath/seller/add_goods_first.htm?id=$!{obj.id}'" />
                                    </span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品名称：</td>
                                <td class="sptable"><span class="tabtxt1 size1">
                                    <input name="goods_name" type="text" id="goods_name" value="${(obj.goods_name)!}" />
                                    </span><span class="hui2">商品标题名称长度至少3个字符，最长50个汉字</span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品原价：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                    <input name="goods_price" type="text" id="goods_price" value="${(obj.goods_price)!}" />
                                    </span> <span class="hui2">商品原价必须是0.01~1000000之间的数字</span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品类型：</td>
                                <td class="sptable">
                                    <label>
                                        <input name="goods_choice_type" type="radio" value="0" checked="checked" />
                                        实物商品
                                    </label>
                                    <label>
                                        <input type="radio" name="goods_choice_type" value="1" />虚拟商品
                                    </label>
                                    <span class="hui2">充值卡、消费券等属于虚拟商品</span>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">店铺价格：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                    <input name="store_price" type="text" id="store_price" value="${(obj.store_price)!}" />
                                    </span> <span class="hui2">
                                    <ul class="tableli">
                                      <li>商品原价必须是0.01~1000000之间的数字</li>
                                      <li>若启用了库存配置，请在下方商品库存区域进行管理</li>
                                      <li>商品规格库存表中如有价格差异，店铺价格显示为价格区间形式</li>
                                    </ul>
                                    </span></td>
                            </tr>
                            <#list goods_class.goodsType.gss as gs>
                            <tr id="gs_${gs.id}" gs_name="${(gs.name)!}">
                                <td align="right" valign="top">${(gs.name)!}：</td>
                                <td class="sptable"><ul class="color_chose">
                                    <#list gs.properties as gsp>
                                    <li> <span class="cc_sp1">
                                        <input name="spec_${gsp.id}" type="checkbox" id="spec_${gsp.id}" gs_id="${gs.id}"  gsp_val="$!gsp.value" value="$!gsp.id" />
                                        </span>
                                        <label for="spec_${gsp.id}">
                                            <#if gs.type =='img'>
                                            <span class="cc_sp2">
                                            <img src="${S_URL}/${gsp.specImage.path}/${gsp.specImage.name}" width="16" height="16" />
                                            </span>
                                            </#if>
                                            <span class="cc_sp3">${gsp.value}</span>
                                        </label>
                                    </li>
                                    </#list>
                                </ul></td>
                            </tr>
                            </#list>
                            <tr >
                                <td align="right" valign="top">库存配置：</td>
                                <td class="sptable">
                                    <label>
                                        <input type="radio" name="inventory_type" value="all" checked="checked" />
                                        全局配置
                                    </label>
                                    <label>
                                        <input type="radio" name="inventory_type" value="spec" />
                                        规格配置
                                    </label>
                                    <span class="hui2">
                                    <ul class="tableli">
                                    <li>全局配置表示所有规格无单独库存、价格配置</li>
                                    <li>规格配置需要配置对应属性的库存、价格</li>
                                    </ul>
                                    </span>
                                </td>
                            </tr>
                            <tr id="inventory_detail" style="display:none;">
                                <td align="right" valign="top">详细库存：</td>
                                <td class="sptable"><div id="inventory_detail_content" style="width:98%;height:350px;overflow:auto;"></div></td>
                            </tr>
                            <tr >
                                <td align="right" valign="top">商品库存：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                <input name="goods_inventory" type="text" id="goods_inventory" value="${obj.goods_inventory}" />
                                </span> <span class="hui2">
                                <ul class="tableli">
                                  <li>商铺库存数量必须为1~1000000000之间的整数</li>
                                  <li>若启用了规格配置，则系统自动计算商品的总数，此处无需卖家填写</li>
                                </ul>
                                </span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品货号：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                <input name="goods_serial" type="text" id="goods_serial" value="${obj.goods_serial}" />
                                </span> <span class="hui2">
                                <ul class="tableli">
                                  <li>商品货号是指卖家个人管理商品的编号，买家不可见</li>
                                  <li>最多可输入20个字符，支持输入中文、字母、数字、_、/、-和小数点</li>
                                </ul>
                                </span></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
