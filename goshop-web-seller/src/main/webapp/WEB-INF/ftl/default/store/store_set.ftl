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
<#--<script charset="utf-8" src="${S_URL}/resources/editor/kindeditor.js"></script>-->
<#--<script charset="utf-8" src="${S_URL}/resources/editor/lang/zh_CN.js"></script>-->
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<script src="${S_URL}/static/scripts/jquery/jquery.poshytip.min.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script charset="utf-8" src="${S_URL}/static/editor/kindeditor.js"></script>
<script charset="utf-8" src="${S_URL}/static/editor/lang/zh_CN.js"></script>
<script>
    var options = {
        cssPath : '${S_URL}/static/editor/plugins/code/prettify.css',
        filterMode : true,
		uploadJson:'${S_URL}/upload',
		width : '600px',
		height:'300px',
		resizeType : 1,
		allowImageUpload : false,
		allowFlashUpload : false,
		allowMediaUpload : false,
		allowFileManager : false,
		syncType:"form",
		afterCreate :
                function() {
                    var self = this;
                    self.sync();
                },
		afterChange : function() {
							var self = this;
							self.sync();
						},
		afterBlur : function() {
							var self = this;
							self.sync();
						},
		items:['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', '|', 'selectall', 'clearhtml','quickformat','|',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image','flash', 'media', 'baidumap','table', 'hr', 'emoticons', 'link', 'unlink', '|', 'about']
    };
    jQuery(document).ready(function(){
        //
        jQuery("#theForm").validate({
            rules:{
              store_ower_card:{required :true},
              store_name:{required :true}
            },
            messages:{
              store_ower_card:{required:"身份证号码不能为空"},
              store_name:{required :"店铺名称不能为空"}
            }
        });

        editor = KindEditor.create('#store_info',options);
        jQuery("select").change(function(){
            var level=jQuery(this).attr("level");
            var id=jQuery(this).val();
            if(id!=""){
                jQuery.post("${S_URL}/load_area",{"pid":id},function(data){
                    jQuery("#area"+level).empty();
                    jQuery("#area"+level).append("<option value=''>请选择</option>");
                    jQuery.each(data, function(index,item){
                        jQuery("#area"+level).append("<option value='"+item.id+"'>"+item.areaname+"</option>");
                    });
                    jQuery("#area"+level).show();
                },"json");
            }else{
                for(var i=level;i<=3;i++){
                jQuery("#area"+i).empty();
                jQuery("#area"+i).hide();
                }
            }
        });
        jQuery("#area3").change(function(){
            var id=jQuery(this).val();
            jQuery("#area_id").val(id);
        });
    });
    function save_form(){
       jQuery("#theForm").submit();
    }
</script>


<div class="ncsc-layout wrapper">
    <#include "layout_store.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div class="productmain">
            <div class="ordernav">
              <ul class="orderul">
                <li class="this"><a href="${S_URL}/seller/store_set.htm">店铺设置</a></li>
                <li><a href="${S_URL}/store/store_slide">店铺幻灯</a></li>
                <li><a href="${S_URL}/store/store_map">店铺地图</a></li>
                <li><a href="${S_URL}/store/store_approve">店铺认证</a></li>
              </ul>
            </div>
            <form action="${S_URL}/store/store_set_save" method="post" enctype="multipart/form-data" id="theForm">
                <div class="ordercon">
                    <div class="setshop">
                        <table width="705" border="0" cellspacing="0" cellpadding="0" class="setshoptable">
                            <tr>
                                <td width="12%" align="right" valign="top">店铺标志：</td>
                                <td style="padding-left:30px;">
                                    <ul class="setlogo">
                                        <#assign store_logo="${S_URL}/${(config.storeImage.path)!}/${(config.storeImage.name)!}" />
                                        <#if (store.logo)!?? >
                                          <#assign store_logo="${S_URL}/${(store.logo.path)!}/${(store.logo.name)!}"/>
                                        </#if>
                                        <li class="shoplogo">
                                            <img src="${store_logo!}" width="105" height="97" /></li>
                                        <li>
                                            <input name="logo" type="file" id="logo" size="30" />
                                        </li>
                                        <li class="setinfo">此处为您的店铺标志，将显示在店铺信息栏里<strong>，建议尺寸100*100像素</strong></li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td width="98" align="right" valign="top">店铺条幅：</td>
                                <td style="padding-left:30px;">
                                    <ul class="setbanner">
                                        <#assign banner="${S_URL}/resources/style/shop/${(store.template)!}/images/banner.jpg"/>
                                        <#if (store.store_banner)!?? >
                                          <#assign banner="${S_URL}/${(store.store_banner.path)!}/${(store.store_banner.name)!}"/>
                                        </#if>
                                        <li class="shopbanner"><img src="${banner!}" width="400" height="100" /></li>
                                        <li>
                                            <input name="banner" type="file" id="banner" size="30" />
                                        </li>
                                        <li class="setinfo">此处为您的店铺条幅，将显示在店铺导航上方的banner位置，<strong>建议尺寸宽度为1000像素</strong>， 店标与店铺条幅只有点击"提交"后才能生效</li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td width="98" align="right" >店主名称：</td>
                                <td style="padding-left:30px; color:#666">${(store.sellerName)!}</td>
                            </tr>
                            <tr>
                                <td width="98" align="right">身份证号：</td>
                                <td style="padding-left:30px; color:#666">
                                    <span class="setinput">
                                        <input name="storeOwnerCard" type="text" id="store_ower_card" value="${(store.storeOwnerCard)!}" />
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td width="98" align="right">店铺名称： </td>
                                <td style="padding-left:30px;">
                                    <span class="setinput">
                                        <input name="storeName" type="text" id="store_name" value="${(store.storeName)!}" />
                                    </span>
                                    <a href="${S_URL}/store.htm?id=${(user.store.id)!}" target="_blank" class="blue2 px20">我的店铺首页</a></td>
                            </tr>
                            <#if (config.second_domain_open)!?? >
                            <tr>
                              <td align="right">二级域名：</td>
                              <td style="padding-left:30px; color:#666"><span class="setinput">
                                <#if (((config.domain_allow_count)!0)>0) && (((store.domain_modify_count)!0)>=((config.domain_allow_count)!0))>
                                    <#assign modity=0 >
                                <#else>
                                    <#assign modity=1 >
                                </#if>
                                <input name="storeDomain" type="text" id="store_second_domain" value="${(store.storeDomain)!}"
                                       <#if (modity!0)==0 > readonly="readonly" </#if>  />填写二级域名前缀即可，如stone
                                  <#if (modity!0)==0 ><span style="color:#F00">[已经超过系统允许次数]</span></#if> </span></td>
                            </tr>
                            </#if>
                            <tr>
                              <td align="right">店铺等级： </td>
                              <td style="padding-left:30px; color:#666">
                                  <span>${(store.grade.sgName)!}
                                      <#if (store.update_grade)!??>升级审核中... <#else><a href="${S_URL}/store/store_grade" target="_blank" class="blue2 px20">升级店铺</a> </#if>
                                  </span>
                              </td>
                            </tr>
                            <tr>
                              <td width="98" align="right">所在地区：</td>
                              <td style="padding-left:30px; color:#666">${(areaViewTools.generic_area_info("${(store.area.id)!}"))!}
                                  <span class="setedit">
                                      <input name="input" type="button"  value="编辑" onclick="javascript:jQuery('#area1').show();" id="modify"/>
                                        <select name="area1" id="area1" level="2" style="width:80px;display:none;">
                                        <option value="" selected="selected">请选择地区</option>
                                            <#list  areas as area>
                                            <option value="${(area.id)!}">${(area.areaname)!}</option>
                                            </#list>
                                        </select>
                                        <select name="area2" id="area2" style="display:none;width:80px;" level="3">
                                        </select>
                                        <select name="area3" id="area3" style="display:none;width:80px;" level="4">
                                        </select>
                                        <input name="areaId" type="hidden" id="area_id"  value="${(store.area.id)!}"/>
                                  </span>
                              </td>
                            </tr>
                            <tr>
                              <td width="98" align="right">详细地址：</td>
                              <td style="padding-left:30px;"><span class="setinput">
                                <input name="storeAddress" type="text" id="store_address" value="${(store.storeAddress)!}" />
                                </span></td>
                            </tr>
                            <tr>
                              <td width="98" align="right">联系电话：</td>
                              <td style="padding-left:30px;">
                                  <span class="setinput">
                                      <input name="storeTel" type="text" id="store_telephone" value="${(store.storeTel)!}" />
                                  </span>
                              </td>
                            </tr>
                            <tr>
                              <td align="right" valign="top">联系QQ：</td>
                              <td style="padding-left:30px;"><span class="setinput">
                                <input name="storeQq" type="text" id="store_qq" value="${(store.storeQq)!}" />
                                </span></td>
                            </tr>
                            <tr>
                              <td align="right" valign="top">联系MSN：</td>
                              <td style="padding-left:30px;">
                                  <span class="setinput">
                                      <input name="store_msn" type="text" id="store_msn" value="${(store.store_msn)!}" />
                                  </span>
                              </td>
                            </tr>
                            <tr>
                              <td align="right" valign="top">阿里旺旺：</td>
                              <td style="padding-left:30px;"><span class="setinput">
                                <input name="storeWw" type="text" id="store_ww" value="${(store.storeWw)!}" />
                                </span>
                              </td>
                            </tr>
                            <tr>
                              <td width="98" align="right" valign="top">SEO关键字：</td>
                              <td style="padding-left:30px;"><ul class="setseo">
                                  <li><span class="setinput">
                                    <input name="storeKeywords" type="text" id="store_seo_keywords" value="${(store.storeKeywords)!}" />
                                    </span>
                                  </li>
                                  <li style="color:#666">用于店铺搜索引擎的优化，关键字之间请用英文逗号分隔</li>
                                </ul></td>
                            </tr>
                            <tr>
                              <td width="98" align="right" valign="top">SEO描述：</td>
                              <td style="padding-left:30px;"><ul class="setseo">
                                  <li><span class="setinput">
                                    <textarea name="storeDescription" cols="45" rows="5" id="store_seo_description">${(store.storeDescription)!}</textarea>
                                    </span></li>
                                  <li style="color:#666">用于店铺搜索引擎的优化，关键字之间请用英文逗号分隔</li>
                                </ul></td>
                            </tr>
                            <tr>
                              <td align="right">店铺介绍：</td>
                              <td style="padding-left:30px;"><ul class="setseo">
                                  <li>
                                      <span class="setinput">
                                          <textarea name="description" cols="45" rows="5" id="store_info">${(store.description)!}</textarea>
                                      </span>
                                  </li>
                                  <li style="color:#666">详细介绍店铺，为买家提供更多的了解</li>
                                </ul>
                              </td>
                            </tr>
                            <tr>
                              <td width="98" align="right">&nbsp;</td>
                              <td style="padding-left:30px;">
                                  <span class="setsub">
                                      <input type="button"  value="保 存" style="cursor:pointer;" onclick="save_form();"/>
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
