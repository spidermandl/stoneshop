<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="商品发布" />
<#assign P_CURRENT_OP="GoodsAdd" />
<#assign P_STEP=1 />
<@override name="main">
<script src="${S_URL}/static/scripts/goods/store_goods_add.step1.js"></script>
<div class="ncsc-layout wrapper">
    <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
        <@shiro.hasPermission name="seller:read">
        <#include "setp.ftl" />
            <!--S 分类选择区域-->
            <div class="wrapper_search">
                <div class="wp_sort">
                    <div class="wp_data_loading" id="dataLoading">
                        <div class="data_loading">
                            加载中...
                        </div>
                    </div>
                    <div class="sort_selector">
                        <div class="sort_title">
                            您常用的商品分类：
                            <div id="commSelect" class="text">
                                <div>
                                    请选择
                                </div>
                                <div id="commListArea" class="select_list" >
                                    <ul>
                                        <#list P_GOODS_CLASS_STAPLE_LIST as staple>
                                            <li data-param="{stapleid:'${staple.stapleId}'}">
                                                <span nctype="staple_name">${staple.stapleName}</span>
                                                <a title="删除" nctype="del-comm-cate" href="JavaScript:void(0);">X</a>
                                            </li>
                                        </#list>
                                        <li <#if (P_GOODS_CLASS_STAPLE_LIST?size>0)>style="display: none;" </#if> id="select_list_no"><span class="title">您还没有添加过常用的分类</span></li>
                                    </ul>
                                </div>
                            </div>
                            <i class="icon-angle-down"></i>
                        </div>
                    </div>
                    <div class="wp_sort_block" id="class_div">
                        <div class="sort_list">
                            <div class="wp_category_list">
                                <div class="category_list ps-container" id="class_div_1">
                                    <ul>
                                        <#list P_GOODS_CLASS_LIST as c1 >
                                            <li data-param="{gcid:'${c1.id}',deep:1,tid:0}" nctype="selClass" >
                                                <a href="javascript:void(0)" class="">
                                                    <i class="icon-double-angle-right"></i>${c1.classname}</a></li>
                                        </#list>
                                    </ul>
                                    <div class="ps-scrollbar-x" style="left: 0px; bottom: 3px; width: 0px;"></div>
                                    <div class="ps-scrollbar-y" style="top: 0px; right: 3px; height: 172px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="sort_list">
                            <div class="wp_category_list blank">
                                <div class="category_list ps-container" id="class_div_2" style="">
                                    <ul>
                                    </ul>
                                    <div class="ps-scrollbar-x" style="left: 0px; bottom: 3px; width: 0px;"></div>
                                    <div class="ps-scrollbar-y" style="top: 0px; right: 3px; height: 0px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="sort_list sort_list_last">
                            <div class="wp_category_list blank">
                                <div class="category_list ps-container" id="class_div_3" style="">
                                    <ul>
                                    </ul>
                                    <div class="ps-scrollbar-x" style="left: 0px; bottom: 3px; width: 0px;"></div>
                                    <div class="ps-scrollbar-y" style="top: 0px; right: 3px; height: 0px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="alert">
                    <dl class="hover_tips_cont">
                        <dt id="commodityspan">
                            <span style="color:#F00;">请选择商品类别</span>
                        </dt>
                        <dt class="current_sort" style="display: none;" id="commoditydt">
                            您当前选择的商品类别是：
                        </dt>
                        <dd id="commoditydd"></dd>
                    </dl>
                </div>
                <div class="wp_confirm">
                    <form method="get" action="${S_URL}/goods_add/step_two">
                        <input type="hidden" value="" id="class_id" name="class_id" />
                        <input type="hidden" value="" id="t_id" name="t_id" />
                        <div class="bottom tc">
                            <label class="submit-border">
                                <input type="submit" style=" width: 200px;" class="submit" value="下一步，填写商品信息" nctype="buttonNextStep" disabled="disabled" /></label>
                        </div>
                    </form>
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
