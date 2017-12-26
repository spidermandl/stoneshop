<#assign P_CURRENT_TOP='store' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="店铺" />
<#assign P_NAV3="店铺设置" />
<#assign P_CURRENT_OP="StoreSetting" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<script>
jQuery(document).ready(function(){
   jQuery("#theForm").validate({
       rules: {
		       acc1:{accept:"${(config.imageSuffix)!}"},
			   acc2:{accept:"${(config.imageSuffix)!}"},
			   acc3:{accept:"${(config.imageSuffix)!}"},
			   acc4:{accept:"${(config.imageSuffix)!}"},
			   acc5:{accept:"${(config.imageSuffix)!}"}
	          },
		messages: {
			   acc1:{accept:"幻灯格式不正确"},
			   acc2:{accept:"幻灯格式不正确"},
			   acc3:{accept:"幻灯格式不正确"},
			   acc4:{accept:"幻灯格式不正确"},
			   acc5:{accept:"幻灯格式不正确"}
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
                    <li class="this"><a href="${S_URL}/store/store_slide">店铺幻灯</a></li>
                    <li><a href="${S_URL}/store/store_map">店铺地图</a></li>
                    <li><a href="${S_URL}/store/store_approve">店铺认证</a></li>
                </ul>
            </div>
            <form action="${S_URL}/store/store_slide_save" method="post" enctype="multipart/form-data" id="theForm">
            <div class="ordercon">
              <div class="setshop">
                <table width="705" border="0" cellspacing="0" cellpadding="0" class="setshoptable">
                  <tr>
                    <td width="98" align="right" valign="top">幻灯1：</td>
                    <td width="607" style="padding-left:30px;"><ul class="setlogo">
                        <#if store.slides?size gte 1 >
                          <#assign slide1= store.slides[0]/>
                        </#if>
                        <#if slide1?? >
                        <li class="shoplogo"><img src="${S_URL}/${(slide1.acc.path)!}/${(slide1.acc.name)!}" width="210" height="104" /></li>
                        </#if>
                        <li>图片：
                          <input name="acc1" type="file" id="acc1" size="30" />
                        </li>
                        <li>
                          URL：
                            <input name="acc_url1" type="text" id="acc_url1" value="${(slide1.url)!}" size="40" />
                        </li>
                        <li class="setinfo">此处为您的店铺首页幻灯，<strong>建议尺寸797*393像素</strong></li>
                      </ul></td>
                  </tr>
                 <tr>
                    <td width="98" align="right" valign="top">幻灯2：</td>
                    <td width="607" style="padding-left:30px;"><ul class="setlogo">
                        <#if store.slides?size gte 2 >
                          <#assign slide2=store.slides[1]/>
                        </#if>
                        <#if slide2?? >
                        <li class="shoplogo"><img src="${S_URL}/${(slide2.acc.path)!}/${(slide2.acc.name)!}" width="210" height="104" /></li>
                        </#if>
                        <li>图片：
                          <input name="acc2" type="file" id="acc2" size="30" />
                        </li>
                        <li>
                          URL：
                            <input name="acc_url2" type="text" id="acc_url2" value="${(slide2.url)!}" size="40" />
                        </li>
                        <li class="setinfo">此处为您的店铺首页幻灯，<strong>建议尺寸797*393像素</strong></li>
                      </ul></td>
                  </tr>
                  <tr>
                    <td width="98" align="right" valign="top">幻灯3：</td>
                    <td width="607" style="padding-left:30px;"><ul class="setlogo">
                        <#if store.slides?size gte 3 >
                          <#assign slide3=store.slides[2]/>
                        </#if>
                        <#if slide3?? >
                        <li class="shoplogo"><img src="${S_URL}/${(slide3.acc.path)!}/${(slide3.acc.name)!}" width="210" height="104" /></li>
                        </#if>
                        <li>图片：
                          <input name="acc3" type="file" id="acc3" size="30" />
                        </li>
                        <li>
                          URL：
                            <input name="acc_url3" type="text" id="acc_url3" value="${(slide3.url)!}" size="40" />
                        </li>
                        <li class="setinfo">此处为您的店铺首页幻灯，<strong>建议尺寸797*393像素</strong></li>
                      </ul></td>
                  </tr>
                  <tr>
                    <td width="98" align="right" valign="top">幻灯4：</td>
                    <td width="607" style="padding-left:30px;"><ul class="setlogo">
                        <#if store.slides?size gte 4 >
                          <#assign slide4=store.slides[3] />
                        </#if>
                        <#if slide4?? >
                        <li class="shoplogo"><img src="${S_URL}/${(slide4.acc.path)!}/${(slide4.acc.name)!}" width="210" height="104" /></li>
                        </#if>
                        <li>图片：
                          <input name="acc4" type="file" id="acc4" size="30" />
                        </li>
                        <li>
                          URL：
                            <input name="acc_url4" type="text" id="acc_url4" value="${(slide4.url)!}" size="40" />
                        </li>
                        <li class="setinfo">此处为您的店铺首页幻灯，<strong>建议尺寸797*393像素</strong></li>
                      </ul></td>
                  </tr>
                  <tr>
                    <td width="98" align="right" valign="top">幻灯5：</td>
                    <td width="607" style="padding-left:30px;"><ul class="setlogo">
                        <#if store.slides?size gte 5 >
                          <#assign slide5=store.slides[4] />
                        </#if>
                        <#if slide5?? >
                        <li class="shoplogo"><img src="${S_URL}/${(slide5.acc.path)!}/${(slide5.acc.name)!}" width="210" height="104" /></li>
                        </#if>
                        <li>图片：
                          <input name="acc5" type="file" id="acc5" size="30" />
                        </li>
                        <li>
                          URL：
                            <input name="acc_url5" type="text" id="acc_url5" value="${(slide5.url)!}" size="40" />
                        </li>
                        <li class="setinfo">此处为您的店铺首页幻灯，<strong>建议尺寸797*393像素</strong></li>
                      </ul></td>
                  </tr>
                  <tr>
                    <td width="98" align="right">&nbsp;</td>
                    <td style="padding-left:30px;"><span class="setsub">
                      <input name="" type="submit"  value="提交" style="cursor:pointer;"/>
                      <input name="store_id" type="hidden" id="store_id" value="${(store.id)!}" />
                    </span></td>
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
