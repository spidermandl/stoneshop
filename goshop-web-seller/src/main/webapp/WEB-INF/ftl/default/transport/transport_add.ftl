<#assign P_CURRENT_TOP='transport' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="物流" />
<#assign P_NAV3="物流工具" />
<#assign P_CURRENT_OP="TransportList" />

<@override name="main">
<#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/system/front/default/css/seller.css" type="text/css" rel="stylesheet" />-->
<#--<link href="${S_URL}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->

<link href="${S_URL}/static/styles/transport.css" type="text/css" rel="stylesheet" />
<script src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script>
    var mail_city_count=0;
    var express_city_count=0;
    var ems_city_count=0;
    var mail_batch=0;
    var express_batch=0;
    var ems_batch=0;
    jQuery(document).ready(function(){
         mail_city_count=jQuery("#mail_trans_city_info table tr").length-1;
         express_city_count=jQuery("#express_trans_city_info table tr").length-1;
         ems_city_count=jQuery("#ems_trans_city_info table tr").length-1;
         jQuery("#mail_city_count").val(mail_city_count);
         jQuery("#express_city_count").val(express_city_count);
         jQuery("#ems_city_count").val(ems_city_count);
         jQuery("#theForm").validate({
           rules: {
                trans_name:{required:true},
                mail_trans_weight:{required:true,digits:true,range:[1,999]},
                mail_trans_fee:{required:true,number:true},
                mail_trans_add_weight:{required:true,digits:true,range:[1,999]},
                mail_trans_add_fee:{required:true,number:true},
                express_trans_weight:{required:true,digits:true,range:[1,999]},
                express_trans_fee:{required:true,number:true},
                express_trans_add_weight:{required:true,digits:true,range:[1,999]},
                express_trans_add_fee:{required:true,number:true},
                ems_trans_weight:{required:true,digits:true,range:[1,999]},
                ems_trans_fee:{required:true,number:true},
                ems_trans_add_weight:{required:true,digits:true,range:[1,999]},
                ems_trans_add_fee:{required:true,number:true}
              },
           messages: {
                trans_name:{required:"模板名称不能为空"},
                mail_trans_weight:{required:"不能为空",digits:"只能为整数",range:"只能为1-999的数字"},
                mail_trans_fee:{required:"不能为空",number:"只能为数字"},
                mail_trans_add_weight:{required:"不能为空",digits:"只能为整数",range:"只能为1-999的数字"},
                mail_trans_add_fee:{required:"不能为空",number:"只能为数字"},
                express_trans_weight:{required:"不能为空",digits:"只能为整数",range:"只能为1-999的数字"},
                express_trans_fee:{required:"不能为空",number:"只能为数字"},
                express_trans_add_weight:{required:"不能为空",digits:"只能为整数",range:"只能为1-999的数字"},
                express_trans_add_fee:{required:"不能为空",number:"只能为数字"},
                ems_trans_weight:{required:"不能为空",digits:"只能为整数",range:"只能为1-999的数字"},
                ems_trans_fee:{required:"不能为空",number:"只能为数字"},
                ems_trans_add_weight:{required:"不能为空",digits:"只能为整数",range:"只能为1-999的数字"},
                ems_trans_add_fee:{required:"不能为空",number:"只能为数字"}
            }
      });
      jQuery(":checkbox").on("click",null,function(){
         var ck=jQuery(this).attr("checked");
         if(ck=="checked"){
           var id=jQuery(this).attr("id");
           jQuery("#"+id+"_info").show();
         }else{
            var id=jQuery(this).attr("id");
           jQuery("#"+id+"_info").hide();
         }
       });
      jQuery("a[id^=batch_set_]").on("click",null,function(){
         jQuery(this).parent().parent().find(":checkbox").show();
         var type=jQuery(this).attr("trans_type");
         jQuery("#"+type+"_trans_city_op").show();
         jQuery(this).hide();
         jQuery("#batch_cancle_"+type).show();
         if(type=="mail"){
           mail_batch=1;
         }
         if(type=="express"){
           express_batch=1;
         }
         if(type=="ems"){
            ems_batch=1;
         }
      });
      jQuery("a[id^=batch_cancle_]").on("click",null,function(){
         jQuery(this).parent().parent().find(":checkbox").hide();
         var type=jQuery(this).attr("trans_type");
         jQuery("#"+type+"_trans_city_op").hide();
         jQuery(this).hide();
         jQuery("#batch_set_"+type).show();
         if(type=="mail"){
           mail_batch=0;
         }
         if(type=="express"){
           express_batch=0;
         }
         if(type=="ems"){
            ems_batch=0;
         }
      });
      jQuery("a[id^=batch_del_]").on("click",null,function(){
         jQuery(this).parent().parent().find(":checkbox:checked[id^=trans_ck]").each(function(){
             jQuery(this).parent().parent().parent().parent().remove();
         });
         jQuery("#mail_trans_all").attr("checked",false);
      });
      jQuery("a[id^=batch_config_]").click(function(){

      });
      jQuery(":checkbox[id$=mail_trans_all]").on("click",null,function(){
         var ck=jQuery(this).attr("checked");
         if(ck=="checked"){
           jQuery(this).parent().parent().parent().find(":checkbox").attr("checked",true);
         }else{
           jQuery(this).parent().parent().parent().find(":checkbox").attr("checked",false);
         }
      });
      jQuery(":radio[name=trans_type]").click(function(){
         if(confirm("正在切换计价方式，确定继续么？")){
             var trans_type=jQuery(this).val();
             if(trans_type=="0"){
                trans_type="mail";
             }
             if(trans_type=="1"){
                 trans_type="express";
             }
             if(trans_type=="2"){
                 trans_type="ems";
             }
             jQuery.ajax({type:'POST',url:'${S_URL}/transport/transport_info',data:{"id":"${(obj.id)!}","type":trans_type},
                           success:function(data){
                               jQuery("#trans_detail").empty().html(data);
                           }
             });
          }
       });
      <#if (obj.trans_mail)??>
        jQuery("#trans_mail_info").show();
      </#if>
      <#if (obj.trans_express)??>
        jQuery("#trans_express_info").show();
      </#if>
      <#if (obj.trans_ems)??>
       jQuery("#trans_ems_info").show();
      </#if>
      jQuery("#trans_time").val("${(obj.trans_time)!}");
      jQuery(":radio[value=${(obj.trans_type)!}]").attr("checked",true);
    });
    function trans_city(id){
      var the_id="";
      var s="";
      if(id=="express"){
        express_city_count=express_city_count+1;
        the_id="express"+express_city_count;
        jQuery("#express_city_count").val(express_city_count);
        if(express_batch==1){
           s='<tr index="'+express_city_count+'"><td><span class="width2"><i><input id="trans_ck_'+express_city_count+'" name="trans_ck_'+express_city_count+'" type="checkbox" value="" /></i><input id="express_city_ids'+express_city_count+'" name="express_city_ids'+express_city_count+'" type="hidden" value="" /><input id="express_city_names'+express_city_count+'" name="express_city_names'+express_city_count+'" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'+id+'">编辑</a></span><span class="width1" id="'+the_id+'">未添加地区</span></td><td><input type="text" value="1" class="in" id="express_trans_weight'+express_city_count+'" name="express_trans_weight'+express_city_count+'" /></td><td><input type="text" value="1" class="in" id="express_trans_fee'+express_city_count+'" name="express_trans_fee'+express_city_count+'" /></td><td><input type="text" value="1" class="in" id="express_trans_add_weight'+express_city_count+'" name="express_trans_add_weight'+express_city_count+'" /></td><td><input type="text" value="1" class="in" id="express_trans_add_fee'+express_city_count+'" name="express_trans_add_fee'+express_city_count+'" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
        }else{
          s='<tr index="'+express_city_count+'"><td><span class="width2"><i><input id="trans_ck_'+express_city_count+'" name="trans_ck_'+express_city_count+'" type="checkbox" value="" style="display:none;" /></i><input id="express_city_ids'+express_city_count+'" name="express_city_ids'+express_city_count+'" type="hidden" value="" /><input id="express_city_names'+express_city_count+'" name="express_city_names'+express_city_count+'" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'+id+'">编辑</a></span><span class="width1" id="'+the_id+'">未添加地区</span></td><td><input type="text" value="1" class="in" id="express_trans_weight'+express_city_count+'" name="express_trans_weight'+express_city_count+'" /></td><td><input type="text" value="1" class="in" id="express_trans_fee'+express_city_count+'" name="express_trans_fee'+express_city_count+'" /></td><td><input type="text" value="1" class="in" id="express_trans_add_weight'+express_city_count+'" name="express_trans_add_weight'+express_city_count+'" /></td><td><input type="text" value="1" class="in" id="express_trans_add_fee'+express_city_count+'" name="express_trans_add_fee'+express_city_count+'" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
       }
      jQuery("#"+id+"_trans_city_info table tr:last").after(s);
      jQuery("#"+id+"_trans_city_info").show();
      }
      if(id=="ems"){
        ems_city_count=ems_city_count+1;
        the_id="ems"+ems_city_count;
        jQuery("#ems_city_count").val(ems_city_count);
        if(ems_batch==1){
           s='<tr index="'+ems_city_count+'"><td><span class="width2"><i><input id="trans_ck_'+ems_city_count+'" name="trans_ck_'+ems_city_count+'" type="checkbox" value="" /></i><input id="ems_city_ids'+ems_city_count+'" name="ems_city_ids'+ems_city_count+'" type="hidden" value="" /><input id="ems_city_names'+ems_city_count+'" name="ems_city_names'+ems_city_count+'" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'+id+'">编辑</a></span><span class="width1" id="'+the_id+'">未添加地区</span></td><td><input type="text" value="1" class="in" id="ems_trans_weight'+ems_city_count+'" name="ems_trans_weight'+ems_city_count+'" /></td><td><input type="text" value="1" class="in" id="ems_trans_fee'+ems_city_count+'" name="ems_trans_fee'+ems_city_count+'" /></td><td><input type="text" value="1" class="in" id="ems_trans_add_weight'+ems_city_count+'" name="ems_trans_add_weight'+ems_city_count+'" /></td><td><input type="text" value="1" class="in" id="ems_trans_add_fee'+ems_city_count+'" name="ems_trans_add_fee'+ems_city_count+'" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
        }else{
          s='<tr index="'+ems_city_count+'"><td><span class="width2"><i><input id="trans_ck_'+ems_city_count+'" name="trans_ck_'+ems_city_count+'" type="checkbox" value="" style="display:none;" /></i><input id="ems_city_ids'+ems_city_count+'" name="ems_city_ids'+ems_city_count+'" type="hidden" value="" /><input id="ems_city_names'+ems_city_count+'" name="ems_city_names'+ems_city_count+'" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'+id+'">编辑</a></span><span class="width1" id="'+the_id+'">未添加地区</span></td><td><input type="text" value="1" class="in" id="ems_trans_weight'+ems_city_count+'" name="ems_trans_weight'+ems_city_count+'" /></td><td><input type="text" value="1" class="in" id="ems_trans_fee'+ems_city_count+'" name="ems_trans_fee'+ems_city_count+'" /></td><td><input type="text" value="1" class="in" id="ems_trans_add_weight'+ems_city_count+'" name="ems_trans_add_weight'+ems_city_count+'" /></td><td><input type="text" value="1" class="in" id="ems_trans_add_fee'+ems_city_count+'" name="ems_trans_add_fee'+ems_city_count+'" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
       }
      jQuery("#"+id+"_trans_city_info table tr:last").after(s);
      jQuery("#"+id+"_trans_city_info").show();
      }
      if(id=="mail"){
        mail_city_count=mail_city_count+1;
        the_id="mail"+mail_city_count;
        jQuery("#mail_city_count").val(mail_city_count);
        if(mail_batch==1){
           s='<tr index="'+mail_city_count+'"><td><span class="width2"><i><input id="trans_ck_'+mail_city_count+'" name="trans_ck_'+mail_city_count+'" type="checkbox" value="" /></i><input id="mail_city_ids'+mail_city_count+'" name="mail_city_ids'+mail_city_count+'" type="hidden" value="" /><input id="mail_city_names'+mail_city_count+'" name="mail_city_names'+mail_city_count+'" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'+id+'">编辑</a></span><span class="width1" id="'+the_id+'">未添加地区</span></td><td><input type="text" value="1" class="in" id="mail_trans_weight'+mail_city_count+'" name="mail_trans_weight'+mail_city_count+'" /></td><td><input type="text" value="1" class="in" id="mail_trans_fee'+mail_city_count+'" name="mail_trans_fee'+mail_city_count+'" /></td><td><input type="text" value="1" class="in" id="mail_trans_add_weight'+mail_city_count+'" name="mail_trans_add_weight'+mail_city_count+'" /></td><td><input type="text" value="1" class="in" id="mail_trans_add_fee'+mail_city_count+'" name="mail_trans_add_fee'+mail_city_count+'" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
        }else{
          s='<tr index="'+mail_city_count+'"><td><span class="width2"><i><input id="trans_ck_'+mail_city_count+'" name="trans_ck_'+mail_city_count+'" type="checkbox" value="" style="display:none;" /></i><input id="mail_city_ids'+mail_city_count+'" name="mail_city_ids'+mail_city_count+'" type="hidden" value="" /><input id="mail_city_names'+mail_city_count+'" name="mail_city_names'+mail_city_count+'" type="hidden" value="" /><a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="'+id+'">编辑</a></span><span class="width1" id="'+the_id+'">未添加地区</span></td><td><input type="text" value="1" class="in" id="mail_trans_weight'+mail_city_count+'" name="mail_trans_weight'+mail_city_count+'" /></td><td><input type="text" value="1" class="in" id="mail_trans_fee'+mail_city_count+'" name="mail_trans_fee'+mail_city_count+'" /></td><td><input type="text" value="1" class="in" id="mail_trans_add_weight'+mail_city_count+'" name="mail_trans_add_weight'+mail_city_count+'" /></td><td><input type="text" value="1" class="in" id="mail_trans_add_fee'+mail_city_count+'" name="mail_trans_add_fee'+mail_city_count+'" /></td><td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm(\'确认要删除当前地区的设置么？\'))remove_trans_city(this)">删除</a></span></td></tr>';
        }
      jQuery("#"+id+"_trans_city_info table tr:last").after(s);
      jQuery("#"+id+"_trans_city_info").show();
      }
    }
    function edit_trans_city(obj){
     var trans_city_type=jQuery(obj).attr("trans_city_type");
     var trans_index=jQuery(obj).parent().parent().parent().attr("index");
     jQuery.ajax({type:'POST',url:'${S_URL}/transport/transport_area',data:{"trans_city_type":trans_city_type,"trans_index":trans_index},
                  success:function(data){
                             jQuery(".main").append(data);
                             var left=jQuery(obj).offset().left-280;
                             var top=jQuery(obj).offset().top+32;
                             jQuery(".area_box").css({"top":top+"px","left":left+"px"}).show();
                          }
                })
    }
    function remove_trans_city(obj){
      jQuery(obj).parent().parent().parent().remove();
    }
</script>

<div class="ncsc-layout wrapper">
    <#include "layout_transport.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <div class="productmain">
                <div class="ordernav">
                    <ul class="orderul">
                    <li><a href="${S_URL}/transport/transport_list">运费模板</a></li>
                    <li class="this"><span class="position">添加运费模板</span></li>
                    </ul>
                </div>
                <div class="ordercon">
                    <form action="${S_URL}/transport/transport_save" method="post" name="theForm" id="theForm">
                        <div class="db_box">
                          <div class="db_box_top">
                            <ul>
                              <li> <span class="li_left">模板名称:</span> <span class="li_right">
                                <input name="trans_name" type="text" id="trans_name" value="${(obj.trans_name)!}"  />
                                <input name="id" type="hidden" id="id" value="$!obj.id" />
                                </span>
                                <input type="hidden" name="mail_city_count" id="mail_city_count" />
                                <input type="hidden" name="express_city_count" id="express_city_count" />
                                <input type="hidden" name="ems_city_count" id="ems_city_count" />
                              </li>
                              <li> <span class="li_left">发货时间:</span> <span class="li_right_font">
                                <select name="trans_time" id="trans_time">
                                  <option selected="selected">请选择</option>
                                  <option value="12">12小时内</option>
                                  <option value="24">24小时内</option>
                                  <option value="48">48小时内</option>
                                  <option value="72">72小时内</option>
                                  <option value="120">5天内</option>
                                  <option value="168">7天内</option>
                                  <option value="360">15天内</option>
                                  <option value="720">30天内</option>
                                  <option value="1080">45天内</option>
                                </select>
                                承诺买家付款后该时间内录入物流信息并发货，以物流公司收单信息为准</span> </li>
                              <li> <span class="li_left">计价方式:</span> <span class="li_right_font">
                                <label>
                                  <input type="radio" name="trans_type" value="0" checked="checked" />
                                  按件数 </label>
                                <label>
                                  <input type="radio" name="trans_type" value="1" />
                                  按重量 </label>
                                <label>
                                  <input type="radio" name="trans_type" value="2" />
                                  按体积 </label>
                                </span> </li>
                              <li> <span class="li_left">运送方式:</span> <span class="li_right_font">除指定地区外，其他地区的运费采用“默认运费”</span> </li>
                            </ul>
                          </div>
                          <div id="trans_detail">
                         <#if obj??>
                            <#if ((obj.trans_type)!0)==0 >
                                ${httpInclude.include("/transport/transport_info?id=$!obj.id&type=mail")}
                            </#if>
                            <#if ((obj.trans_type)!0)==1 >
                                ${httpInclude.include("/transport/transport_info?id=$!obj.id&type=express")}
                            </#if>
                            <#if ((obj.trans_type)!0)==2 >
                                ${httpInclude.include("/transport/transport_info?id=$!obj.id&type=ems")}
                            </#if>
                         <#else>
                          <!--平邮 开始-->
                          <div class="db_box_main">
                            <div class="db_box_main_input">
                              <label>
                                <input name="trans_mail" type="checkbox" id="trans_mail" value="true"
                                       <#if (obj.trans_mail)??> checked="checked" </#if> />
                                平邮 </label>
                            </div>
                            <div class="db_box_main_rdinary" id="trans_mail_info" <#if (obj.trans_mail)!??> style="display:none;" </#if>>
                              <div class="rdinary_top">默认运费：
                                <input name="mail_trans_weight" type="text" id="mail_trans_weight" value='${(transportTools.query_transprot("${(obj.trans_mail_info)!}","trans_weight"))!}' size="5" />
                                件内，
                                <input name="mail_trans_fee" type="text" id="mail_trans_fee" value='${(transportTools.query_transprot("${(obj.trans_mail_info)!}","trans_fee"))!}' size="8" />
                                元， 每增加
                                <input name="mail_trans_add_weight" type="text" id="mail_trans_add_weight" value='${(transportTools.query_transprot("${(obj.trans_mail_info)!}","trans_add_weight"))!}' size="5" />
                                件，增加运费
                                <input name="mail_trans_add_fee" type="text" id="mail_trans_add_fee" value='${(transportTools.query_transprot("${(obj.trans_mail_info)!}","trans_add_fee"))!}' size="8" />
                                元</div>
                                <#assign mail_trans_list= (transportTools.query_all_transprot("${(obj.trans_mail_info)!}",1))! />
                              <div class="rdinary_ul" <#if ((mail_trans_list.size())!0)==0 >style="display:none;"</#if> id="mail_trans_city_info">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                  <tr bgcolor="#f5f5f5">
                                    <td width="46%" align="center"><span class="width1">运送到</span></td>
                                    <td width="11%"><span class="width2">首件(件)</span></td>
                                    <td width="13%"><span class="width2">首费(元)</span></td>
                                    <td width="11%"><span class="width2">续件(件)</span></td>
                                    <td width="12%"><span class="width2">续费(元)</span></td>
                                    <td width="7%"><span class="width3">操作</span></td>
                                  </tr>
                                  <#list mail_trans_list! as info >
                                    <tr index="${info_index}">
                                        <td>
                                            <span class="width2">
                                            <i><input id="trans_ck_${info_index}" name="trans_ck_${info_index}" type="checkbox" value="" style="display:none;" /></i>
                                            <input id="mail_city_ids${info_index}" name="mail_city_ids${info_index}" type="hidden" value='${(info.value("city_id"))!}' />
                                            <input id="mail_city_names${info_index}" name="mail_city_names${info_index}" type="hidden" value='${(info.value("city_name"))!}' />
                                            <a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="mail">编辑</a>
                                            </span>
                                            <span class="width1" id="mail${info_index}">$!info.value("city_name")</span>
                                        </td>
                                        <td><input type="text" value='${(info.value("trans_weight"))!}' class="in" id="mail_trans_weight${info_index}" name="mail_trans_weight${info_index}" />
                                        </td>
                                        <td><input type="text" value='${(info.value("trans_fee"))!}' class="in" id="mail_trans_fee${info_index}" name="mail_trans_fee${info_index}" /></td>
                                        <td><input type="text" value='${(info.value("trans_add_weight"))!}' class="in" id="mail_trans_add_weight${info_index}" name="mail_trans_add_weight${info_index}" />
                                        </td>
                                        <td><input type="text" value='${(info.value("trans_add_fee"))!}' class="in" id="mail_trans_add_fee${info_index}" name="mail_trans_add_fee${info_index}" />
                                        </td>
                                        <td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm('确认要删除当前地区的设置么？'))remove_trans_city(this)">删除</a></span>
                                        </td>
                                    </tr>
                                  </#list>
                                </table>
                              </div>
                              <div class="rdinary_ul_bottom" style="display:none;" id="mail_trans_city_op">
                                <label>
                                  <input name="mail_trans_all" id="mail_trans_all" type="checkbox" value="" />
                                  全选 </label>
                                &nbsp; <a href="javascript:void(0);" id="batch_config_mail" style="display:none;">批量设置</a> &nbsp; <a href="javascript:void(0);" id="batch_del_mail">批量删除</a>
                              </div>
                              <div class="rdinary_ul_bottom">
                                  <a href="javascript:void(0);" onclick="trans_city('mail')">为指定地区城市设置运费</a>&nbsp;
                                  <a href="javascript:void(0);" id="batch_set_mail" trans_type="mail">批量操作</a>&nbsp;
                                  <a href="javascript:void(0);" id="batch_cancle_mail" trans_type="mail" style="display:none;">取消批量</a>
                              </div>
                            </div>
                          </div>
                          <!--平邮 结束-->
                          <!--快递 开始-->
                          <div class="db_box_main">
                            <div class="db_box_main_input">
                              <label>
                                <input name="trans_express" type="checkbox" id="trans_express" value="true" <#if (obj.trans_express)?? > checked="checked"</#if> />
                                快递 </label>
                            </div>
                            <div class="db_box_main_rdinary" id="trans_express_info" <#if (obj.trans_express)!?? > style="display:none;" </#if>>
                              <div class="rdinary_top">默认运费：
                                <input name="express_trans_weight" type="text" id="express_trans_weight" value='${(transportTools.query_transprot("${(obj.trans_express_info)!}","trans_weight"))!}' size="5" />
                                件内，
                                <input name="express_trans_fee" type="text" id="express_trans_fee" value='${(transportTools.query_transprot("${(obj.trans_express_info)!}","trans_fee"))!}' size="8" />
                                元， 每增加
                                <input name="express_trans_add_weight" type="text" id="express_trans_add_weight" value='${(transportTools.query_transprot("${(obj.trans_express_info)!}","trans_add_weight"))!}' size="5" />
                                件，增加运费
                                <input name="express_trans_add_fee" type="text" id="express_trans_add_fee" value='${(transportTools.query_transprot("${(obj.trans_express_info)!}","trans_add_fee"))!}' size="8" />
                                元</div>
                                <#assign mail_trans_list= (transportTools.query_all_transprot("${(obj.trans_express_info)!}",1))!/>
                              <div class="rdinary_ul" <#if ((express_trans_list.size())!0)==0>style="display:none;" </#if> id="express_trans_city_info">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                  <tr bgcolor="#f5f5f5">
                                    <td width="46%" align="center"><span class="width1">运送到</span></td>
                                    <td width="11%"><span class="width2">首件(件)</span></td>
                                    <td width="13%"><span class="width2">首费(元)</span></td>
                                    <td width="11%"><span class="width2">续件(件)</span></td>
                                    <td width="12%"><span class="width2">续费(元)</span></td>
                                    <td width="7%"><span class="width3">操作</span></td>
                                  </tr>
                                  <#list express_trans_list! as info>
                                  <tr index="${info_index}">
                                      <td>
                                          <span class="width2">
                                          <i><input id="trans_ck_${info_index}" name="trans_ck_${info_index}" type="checkbox" value="" style="display:none;" /></i>
                                          <input id="express_city_ids${info_index}" name="express_city_ids${info_index}" type="hidden" value='${(info.value("city_id"))!}' />
                                          <input id="express_city_names${info_index}" name="express_city_names${info_index}" type="hidden" value='${(info.value("city_name"))!}' />
                                          <a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="express">编辑</a>
                                          </span>
                                          <span class="width1" id="express${info_index}">${(info.value("city_name"))!}</span>
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_weight"))!}' class="in" id="express_trans_weight${info_index}" name="express_trans_weight${info_index}" />
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_fee"))!}' class="in" id="express_trans_fee${info_index}" name="express_trans_fee${info_index}" />
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_add_weight"))!}' class="in" id="express_trans_add_weight${info_index}" name="express_trans_add_weight${info_index}" />
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_add_fee"))!}' class="in" id="express_trans_add_fee${info_index}" name="express_trans_add_fee${info_index}" />
                                      </td>
                                      <td>
                                          <span class="width3"><a href="javascript:void(0);" onclick="if(confirm('确认要删除当前地区的设置么？'))remove_trans_city(this)">删除</a></span>
                                      </td>
                                  </tr>
                                  </#list>
                                </table>
                              </div>
                              <div class="rdinary_ul_bottom" style="display:none;" id="express_trans_city_op">
                                <label>
                                  <input name="express_trans_all" id="express_trans_all" type="checkbox" value="" />
                                  全选 </label>
                                &nbsp; <a href="javascript:void(0);" id="batch_config_express" style="display:none;">批量设置</a> &nbsp; <a href="javascript:void(0);" id="batch_del_express">批量删除</a>
                              </div>
                              <div class="rdinary_ul_bottom"><a href="javascript:void(0);" onclick="trans_city('express')">为指定地区城市设置运费</a>&nbsp;<a  href="javascript:void(0);" id="batch_set_express" trans_type="express">批量操作</a>&nbsp; <a href="javascript:void(0);" id="batch_cancle_express" trans_type="express" style="display:none;">取消批量</a></div>
                            </div>
                          </div>
                          <!--快递 结束-->
                          <!--EMS 开始-->
                          <div class="db_box_main">
                            <div class="db_box_main_input">
                              <label>
                                <input name="trans_ems" type="checkbox" id="trans_ems" value="true" <#if (obj.trans_ems)??> checked="checked" </#if>/>
                                EMS </label>
                            </div>
                            <div class="db_box_main_rdinary" id="trans_ems_info" <#if (obj.trans_ems)!??> style="display:none;" </#if>>
                              <div class="rdinary_top">默认运费：
                                <input name="ems_trans_weight" type="text" id="ems_trans_weight" value='${(transportTools.query_transprot("${(obj.trans_ems_info)!}","trans_weight"))!}' size="5" />
                                件内，
                                <input name="ems_trans_fee" type="text" id="ems_trans_fee" value='${(transportTools.query_transprot("${(obj.trans_ems_info)!}","trans_fee"))!}' size="8" />
                                元， 每增加
                                <input name="ems_trans_add_weight" type="text" id="ems_trans_add_weight" value='${(transportTools.query_transprot("${(obj.trans_ems_info)!}","trans_add_weight"))!}' size="5" />
                                件，增加运费
                                <input name="ems_trans_add_fee" type="text" id="ems_trans_add_fee" value='${(transportTools.query_transprot("${(obj.trans_ems_info)!}","trans_add_fee"))!}' size="8" />
                                元</div>
                                <#assign mail_trans_list= (transportTools.query_all_transprot("${(obj.trans_ems_info)!}",1))! />
                              <div class="rdinary_ul" <#if ((ems_trans_list.size())!0)==0 >style="display:none;"</#if> id="ems_trans_city_info">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                  <tr bgcolor="#f5f5f5">
                                    <td width="46%" align="center"><span class="width1">运送到</span></td>
                                    <td width="11%"><span class="width2">首件(件)</span></td>
                                    <td width="13%"><span class="width2">首费(元)</span></td>
                                    <td width="11%"><span class="width2">续件(件)</span></td>
                                    <td width="12%"><span class="width2">续费(元)</span></td>
                                    <td width="7%"><span class="width3">操作</span></td>
                                  </tr>
                                  <#list ems_trans_list! as info>
                                  <tr index="${info_index}">
                                      <td>
                                          <span class="width2">
                                              <i><input id="trans_ck_${info_index}" name="trans_ck_${info_index}" type="checkbox" value="" style="display:none;" /></i>
                                              <input id="ems_city_ids${info_index}" name="ems_city_ids${info_index}" type="hidden" value='${(info.value("city_id"))!}' />
                                              <input id="ems_city_names${info_index}" name="ems_city_names${info_index}" type="hidden" value='${(info.value("city_name"))!}' />
                                              <a href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="ems">编辑</a>
                                          </span>
                                          <span class="width1" id="ems${info_index}">${(info.value("city_name"))!}</span>
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_weight"))!}' class="in" id="ems_trans_weight${info_index}" name="ems_trans_weight${info_index}" />
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_fee"))!}' class="in" id="ems_trans_fee${info_index}" name="ems_trans_fee${info_index}" />
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_add_weight"))!}' class="in" id="ems_trans_add_weight${info_index}" name="ems_trans_add_weight${info_index}" />
                                      </td>
                                      <td>
                                          <input type="text" value='${(info.value("trans_add_fee"))!}' class="in" id="ems_trans_add_fee${info_index}" name="ems_trans_add_fee${info_index}" />
                                      </td>
                                      <td>
                                          <span class="width3"><a href="javascript:void(0);" onclick="if(confirm('确认要删除当前地区的设置么？'))remove_trans_city(this)">删除</a></span>
                                      </td>
                                  </tr>
                                  </#list>
                                </table>
                              </div>
                              <div class="rdinary_ul_bottom" style="display:none;" id="ems_trans_city_op">
                                <label>
                                  <input name="ems_trans_all" id="ems_trans_all" type="checkbox" value="" />
                                  全选 </label>
                                &nbsp; <a href="javascript:void(0);" id="batch_config_ems" style="display:none;">批量设置</a> &nbsp; <a href="javascript:void(0);" id="batch_del_ems">批量删除</a>
                              </div>
                              <div class="rdinary_ul_bottom"><a href="javascript:void(0);" onclick="trans_city('ems')">为指定地区城市设置运费</a>&nbsp;<a  href="javascript:void(0);" id="batch_set_ems" trans_type="ems">批量操作</a>&nbsp; <a href="javascript:void(0);" id="batch_cancle_ems" trans_type="ems" style="display:none;">取消批量</a></div>
                            </div>
                          </div>
                          <!--EMS 结束-->
                         </#if>
                          </div>
                          <div class="db_box_save">
                            <input name="提交" type="submit" value="保存" />
                          </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
