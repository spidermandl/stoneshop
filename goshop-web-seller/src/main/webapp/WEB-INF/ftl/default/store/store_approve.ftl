<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="店铺设置" />
<#assign P_CURRENT_OP="StoreSetting" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.poshytip.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.shop.common.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<script src="${S_URL}/static/scripts/jquery/jquery.poshytip.min.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script>
jQuery(document).ready(function(){
 jQuery("#theForm").validate({
    rules:{
	  card_img:{
	    accept :"${(config.imageSuffix)!}"
	  },
	  license_img:{
	    accept:"${(config.imageSuffix)!}"
	  }
	 },
	messages:{
	  card_img:{accept:"身份证只能为jpg、jpeg、png、bmp格式"},
	  license_img:{accept:"营业执照只能为jpg、jpeg、png、bmp格式"}
	}
  });
});
</script>

<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div class="productmain">
            <div class="ordernav">
                <ul class="orderul">
                  <li><a href="${S_URL}/store/store_set">店铺设置</a></li>
                  <li><a href="${S_URL}/store/store_slide">店铺幻灯</a></li>
                  <li><a href="${S_URL}/store/store_map">店铺地图</a></li>
                  <li class="this"><a href="${S_URL}/store/store_approve">店铺认证</a></li>
                </ul>
            </div>
            <form action="${S_URL}/store/store_approve_save" method="post" enctype="multipart/form-data" id="theForm">
            <div class="ordercon">
              <div class="setshop">
                <table width="705" border="0" cellspacing="0" cellpadding="0" class="setshoptable">
                  <tr>
                    <td width="98" align="right" valign="top">身份证：</td>
                    <td width="607" style="padding-left:30px;">
                      <ul class="setlogo">
                        <li class="shoplogo">
                            <#if (store.card)??>
                                <#if (store.card_approve)?? >
                                    <img src="${S_URL}/${(store.card.path)!}/${(store.card.name)!}" width="200" height="140" />
                                <#else> 认证审核中[
                                    <a href="${S_URL}/${(store.card.path)!}/${(store.card.name)!}" target="_blank">查看认证文件</a>]
                                </#if>
                            <#else> 尚未认证
                            </#if>
                        </li>
                        <li>
                            <#if (store.card_approve)?? >
                              审核通过
                            <#else>
                               <input name="card_img" type="file" id="card_img" size="30" />
                            </#if>
                        </li>
                        <li class="setinfo">上传个人第二代身份扫描件或者数码件<strong></strong></li>
                      </ul>
                      </td>
                  </tr>
                  <tr>
                    <td width="98" align="right" valign="top">营业执照：</td>
                    <td style="padding-left:30px;">
                      <ul class="setbanner">
                        <li class="shopbanner">
                            <#if (store.store_license)??>
                                <#if (store.realstore_approve)??>
                                    <img src="${banner!}" width="400" height="300" />
                                <#else> 认证审核中[
                                    <a href="${S_URL}/${(store.store_license.path)!}/${(store.store_license.name)!}" target="_blank">查看认证文件</a>]
                                </#if>
                            <#else> 尚未认证
                            </#if>
                        </li>
                        <li>
                        <#if (store.realstore_approve)??>
                          审核通过
                        <#else>
                          <input name="license_img" type="file" id="license_img" size="30" />
                        </#if>
                        </li>
                        <li class="setinfo">上传实体店铺营业执照，获取更多的卖家信任</li>
                      </ul>
                      </td>
                  </tr>
                  <tr>
                    <td width="98" align="right">&nbsp;</td>
                    <td style="padding-left:30px;">
                        <span class="setsub">
                            <input name="" type="submit"  value="提交" style="cursor:pointer;"/>
                        </span>
                    </td>
                  </tr>
                </table>
              </div>
            </div>
            </form>
          </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
