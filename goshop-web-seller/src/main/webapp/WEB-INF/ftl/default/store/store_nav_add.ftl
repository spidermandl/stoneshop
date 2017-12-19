<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="导航列表" />
<#assign P_CURRENT_OP="StoreNavigation" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link  href="${S_URL}/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.zh.cn.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<#--<script charset="utf-8" src="${S_URL}/resources/editor/kindeditor.js"></script>-->
<#--<script charset="utf-8" src="${S_URL}/resources/editor/lang/zh_CN.js"></script>-->

<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet"/>
<link href="${S_URL}/static/styles/public.css" type="text/css" rel="stylesheet"/>
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script charset="utf-8" src="${S_URL}/static/editor/kindeditor.js"></script>
<script charset="utf-8" src="${S_URL}/static/editor/lang/zh_CN.js"></script>
<script>
    jQuery(document).ready(function () {
        var options = {
            cssPath: '${S_URL}/static/editor/themes/default/default.css',
            filterMode: true,
            uploadJson: '${S_URL}/upload',
            width: '700px',
            height: '400px'
        };
        var editor = KindEditor.create('#content', options);
        //
        jQuery("#theForm").validate({
            rules: {
                title: {required: true}
            },
            messages: {
                title: {required: "标题不能为空"}
            }
        });
        //
        jQuery(":radio[value=${(obj.display)!}]").attr("checked", true);
        jQuery(":radio[value='${(obj.win_type)!}']").attr("checked", true);
    });
</script>
<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <div class="productmain">
                <div class="ordernav">
                    <ul class="orderul">
                        <li class="other"><a href='${S_URL}/store/store_nav'>导航列表</a></li>
                        <li <#if (edit!false)== false >class="this"</#if>><a
                                href='${S_URL}/store/store_nav_add'>新增导航</a></li>
                        <#if edit?? >
                            <li class="this"><a href='${S_URL}/store/store_nav_edit?id=${(obj.id)!}'>编辑导航</a></li>
                        </#if>
                    </ul>
                </div>
                <div class="ordercon">
                    <div class="addnav">
                        <form action="${S_URL}/store/store_nav_save" method="post" name="theForm" id="theForm">
                            <table width="98%" border="0" cellspacing="0" cellpadding="0" class="addnavtable">
                                <tr>
                                    <td width="195" align="right">
                                        <input name="id" type="hidden" id="id" value="${(obj.id)!}"/>
                                        导航名称：
                                    </td>
                                    <td width="510" class="px10">
                          <span class="setinput">
                              <input name="title" type="text" id="title" value="${(obj.title)!}"/>
                          </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">导航序号：</td>
                                    <td class="px10">
                          <span class="setinput">
                              <input name="sequence" type="text" id="sequence" value="${(obj.sequence)!}"/>
                              序号越小越靠前
                          </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">导航URL：</td>
                                    <td class="px10">
                          <span class="setinput">
                              <input name="url" type="text" id="url" value="${(obj.url)!}"/>
                              URL需要带http://,输入URL后导航内容不显示
                          </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">是否显示：</td>
                                    <td class="px10"><input type="radio" name="display" value="true" checked="checked"/>
                                        是
                                        <input type="radio" name="display" value="false"/>
                                        否
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">打开方式：</td>
                                    <td class="px10">
                                        <input type="radio" name="win_type" value="cur_win" checked="checked"/>
                                        本页面
                                        <input type="radio" name="win_type" value="new_win"/>
                                        新窗口
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">导航内容：</td>
                                    <td class="px10"><span class="setinput">
                        <textarea name="content" id="content">${(obj.content)!}</textarea>
                        </span></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td class="px10"><span class="setsub">
                        <input type="submit" value="保存" style="cursor:pointer;"/>
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
