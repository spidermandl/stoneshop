<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="品牌申请" />
<#assign P_CURRENT_OP="GoodsBrandRequest" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link  href="${S_URL}/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.zh.cn.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script>
jQuery(document).ready(function(){
jQuery.validator.addMethod("verify",function(value,element){
var re = /^[a-zA-Z]*$/;
        if(re.test(jQuery("#first_word").val())){
			return true;
		  }
		  else {
		    return false;
			  }
});
  jQuery("#theForm").validate({
       rules: {
			name:{required:true},
			first_word:{required:true,verify:true,maxlength:1},
			brandLogo:{<#if !(obj??)>required:true,</#if> accept:"jpg,gif,png,jpeg"}
		  },
	   messages: {
		    name:{required:"品牌名称不能为空"},
			first_word:{required:"名称首字母不能为空",verify:"请输入名称首字母",maxlength:"输入长度不正确"},
			brandLogo:{<#if !(obj??)>required:"品牌标志不能为空",</#if> accept:"格式不对，只能为jpg|gif|png|jpeg"}
	     }
  });
});
</script>
<div class="ncsc-layout wrapper">
    <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <div class="productmain" style="width: 910px;">
                <div class="ordernav">
                    <ul class="orderul">
                        <#if edit?? >
                        <li ><a href='${S_URL}/brand/usergoodsbrand_list' >品牌列表</a></li>
                        <li><a href='${S_URL}/brand/usergoodsbrand_add' >申请品牌</a></li>
                        <li class="this"><a href='javascript:void(0);' >编辑品牌</a></li>
                        <#else>
                        <li ><a href='${S_URL}/brand/usergoodsbrand_list' >品牌列表</a></li>
                        <li class="this"><a href='${S_URL}/brand/usergoodsbrand_add' >申请品牌</a></li>
                        </#if>
                    </ul>
                </div>
                <div class="ordercon">
                    <div class="addnav">
                        <form action="${S_URL}/brand/usergoodsbrand_save" method="post" id="theForm" enctype="multipart/form-data">
                            <input name="id" type="hidden" id="id"  value="${(obj.id)!}"/>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="addnavtable">
                                <tr>
                                    <td align="right" valign="top" class="modifysp" width="155">
                                        <span>品牌名称：</span><span><strong class="red" style="font-size:14px;">*</strong></span></td>
                                    <td  class="pl10"><span class="setinput">
                                        <input name="name" type="text" id="name"  value="${(obj.name)!}"/>
                                    </span></td>
                                </tr>
                                <tr>
                                    <td align="right" valign="top" class="modifysp" width="155">
                                        <span>首字母：</span><span><strong class="red" style="font-size:14px;">*</strong></span></td>
                                    <td  class="pl10">
                                        <span class="setinput">
                                        <input name="firstWord" type="text" id="first_word"  value="${(obj.firstWord)!}"/>
                                        </span></td>
                                </tr>
                                <#if (obj.brandLogo)??>
                                <tr>
                                    <td align="right" valign="top" class="modifysp" width="155"><span>品牌预览：</span></td>
                                    <td  class="pl10">
                                        <a href="${S_URL}/${(obj.brandLogo.path)!}/${(obj.brandLogo.name)!}" target="_blank">
                                            <img src="${S_URL}/${(obj.brandLogo.path)!}/${(obj.brandLogo.name)!}" height="44px" width="88px" title="点击查看大图"/></a>
                                    </td>
                                </tr>
                                </#if>
                                <tr>
                                    <td align="right" valign="top" class="modifysp">
                                        <span>品牌标志：</span><span><strong class="red" style="font-size:14px;">*</strong></span></td>
                                    <td  class="px10">
                                        <input name="brandLogo" type="file" id="brandLogo" />
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" valign="top" class="modifysp"><span>申请备注：</span></td>
                                    <td  class="pl10"><span class="setinput">
                                        <textarea name="remark" cols="40" rows="6" id="remark">${(obj.remark)!}</textarea>
                                    </span></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td class="pl10"><span class="setsub">
                                        <input name="" type="submit"  value="提交" style="cursor:pointer;"/>
                                    </span></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
