<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="子账户管理" />
<#assign P_CURRENT_OP="StoreSubAccount" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link  href="${S_URL}/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.zh.cn.js"></script>-->

<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<script src="${S_URL}/static/scripts/jquery/jquery.poshytip.min.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script src="${S_URL}/static/scripts/jquery/jquery.form.js"></script>

<script>
    jQuery(document).ready(function () {
        //
        jQuery("#theForm").validate({
            rules: {
                userName: {
                    required: true,
                    remote: {
                        url: "${S_URL}/verify_username.htm",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            "userName": function () {
                                return jQuery("#userName").val();
                            },
                            "id": function () {
                                return jQuery("#id").val()
                            }
                        }
                    }
                },
                password: {
                    required: true,
                    minlength: 6,
                    maxlength: 20
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true,
                    remote: {
                        url: "${S_URL}/verify_email.htm",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            "email": function () {
                                return jQuery("#email").val();
                            }
                        }
                    }
                }
            },
            messages: {
                userName: {
                    required: "用户名不能为空",
                    remote: "用户名已存在"
                },
                password: {
                    required: "密码不能为空",
                    minlength: "密码不能小于{0}个字符",
                    maxlength: "密码不能大于{0}个字符"
                },
                repassword: {
                    required: "重复密码不能为空",
                    equalTo: "两次输入密码不一致"
                },
                email: {
                    required: "email不能为空",
                    email: "email格式不正确",
                    remote: "该email已经存在"
                }
            }
        });
        //
        //
        jQuery(":checkbox[id^=rg_ck_]").click(function () {
            var val = jQuery(this).attr("id").substring(6);
            var ck = jQuery(this).attr("checked");
            var expr = "ul[id=rg_" + val + "] :checkbox";
            if (ck == "checked") {
                jQuery(expr).attr("checked", true);
            } else jQuery(expr).attr("checked", false);
        });

        jQuery('#birthday').datepicker({
            dateFormat: "yy-mm-dd",
            yearRange: "1950:2020",
            changeMonth: true,
            changeYear: true
        });

        jQuery(":radio[value=${(obj.sex)!}]").attr("checked", true);


    });
    function saveForm() {
        var roles = "";
        jQuery(":checkbox:checked").each(function () {
            if (jQuery(this).val() != "") {
                roles = roles + "," + jQuery(this).val();
            }
        });
        jQuery("#role_ids").val(roles);
        var options = {
            // target:        '#output1',   // target element(s) to be updated with server response
            beforeSubmit: before_ajax,  // pre-submit callback
            success: success_ajax,  // post-submit callback
            // other available options:
            //url:       url         // override for form's 'action' attribute
            //type:      type        // 'get' or 'post', override for form's 'method' attribute
            dataType: "json",       // 'xml', 'script', or 'json' (expected server response type)
            //clearForm: true        // clear all form fields after successful submit
            resetForm: true        // reset the form after successful submit
            //timeout:   3000
        };
        jQuery("#theForm").ajaxSubmit(options);
    }
    ;
    function before_ajax(formData, jqForm, options) {
        return true;
    }
    function success_ajax(data) {
        showDialog("share_sns", "系统提示", data.msg, 0, "succeed", 3);
    }
</script>

<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <div class="productmain">
                <div class="ordernav">
                    <ul class="orderul">
                        <li><a href='${S_URL}/store/sub_account_list'>子账户列表</a></li>
                        <li class="this"><a href='${S_URL}/store/sub_account_add'>子账户添加</a></li>
                    </ul>
                </div>
                <div class="ordercon">
                    <div class="addnav">
                        <form action="${S_URL}/store/sub_account_save" method="post" name="theForm" id="theForm">
                            <table width="99%" border="0" cellspacing="0" cellpadding="0" class="addnavtable">
                                <tr>
                                    <td align="right">用户店铺：</td>
                                    <td class="px10">${(store.storeName)!}</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right">用户名称：</td>
                                    <td width="80%" class="px10">
                                        <span class="setinput">
                                            <input name="userName" type="text" id="userName" value="${(obj.userName)!}"/>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">真实姓名：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="trueName" type="text" id="trueName" value="${(obj.trueName)!}"/>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">性别：</td>
                                    <td class="px10"><label>
                                        <input type="radio" name="sex" value="-1"/>
                                        保密 </label>
                                        &nbsp; &nbsp;
                                        <label>
                                            <input type="radio" name="sex" value="1"/>
                                            男 </label>
                                        &nbsp; &nbsp;
                                        <label>
                                            <input type="radio" name="sex" value="0"/>
                                            女 </label></td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">生日：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="birthday" type="text" id="birthday"
                                                   value="${(CommUtil.formatShortDate(obj.birthday))!}" readonly="true"/>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">QQ：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="QQ" type="text" id="QQ" value="${(obj.QQ)!}"/>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">联系电话：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="telephone" type="text" id="telephone" value="${(obj.telephone)!}"/>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">手机号码：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="mobile" type="text" id="mobile" value="${(obj.mobile)!}"/>
                                        </span>
                                    </td>
                                </tr>
                                <#if (obj!false)==false >
                                <tr>
                                    <td align="right" valign="top">密码：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="password" type="password" id="password" value="${(obj.password)!}"/>
                                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top">重复密码：</td>
                                    <td class="px10">
                                        <span class="setinput">
                                            <input name="repassword" type="password" id="repassword"/>
                                        </span>
                                    </td>
                                </tr>
                                </#if>
                                <tr>
                                    <td align="right" valign="top">用户权限：</td>
                                    <td class="px10">
                                        <div class="manage_box">
                                            <#list rgs! as rg >
                                            <div class="manage_box_title">
                                                <label>
                                                    <input name="rg_ck_${(rg.id)!}" type="checkbox" id="rg_ck_${(rg.id)!}"
                                                           value=""/>${(rg.name)!}
                                                </label>
                                            </div>
                                            <div class="manage_box_main">
                                                <ul id="rg_${(rg.id)!}">
                                                    <#list (rg.perms)! as perm >
                                                        <#assign r=0 />
                                                        <#list (obj.roles)! as info >
                                                        <#if (perm.id == info.id)! >
                                                            <#assign r=1 />
                                                        </#if>
                                                        </#list>
                                                    <li>
                                                        <label>
                                                            <input name="role_${(perm.id)!}" type="checkbox"
                                                                      id="role_${(perm.id)!}" value="${(perm.id)!}"
                                                                      <#if r==1> checked="checked" </#if>/>
                                                            ${(perm.name)!}
                                                        </label>
                                                    </li>
                                                    </#list>
                                                </ul>
                                            </div>
                                            </#list>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td class="px10">
                                        <span class="setsub">
                                            <input name="role_ids" type="hidden" id="role_ids" value=""/>
                                            <input name="" type="button" onclick="saveForm()" value="提交" style="cursor:pointer;"/>
                                            <input name="id" type="hidden" id="id" value="${(obj.id)!}"/>
                                        </span>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            </td>
            </tr>
            </table>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
