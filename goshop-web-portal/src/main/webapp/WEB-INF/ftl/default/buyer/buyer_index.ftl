﻿<@override name="main">
<#assign P_NAV1="我的商城" />
<#assign P_NAV2="买家中心" />
<#assign P_NAV3="首页" />
<link href="${S_URL}/static/styles/basic.css" type="text/css" rel="stylesheet" />

<#--<link href="$!webPath/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
<#--<link href="$!webPath/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />-->
<#--<link href="$!webPath/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />-->
<#--<link  href="$!webPath/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>-->
<#--<script src="$!webPath/resources/js/jquery-1.8.3.min.js"></script>-->
<#--<script src="$!webPath/resources/js/jquery-ui-1.8.21.js"></script>-->
<#--<script src="$!webPath/resources/js/jquery.shop.common.js"></script>-->
<#--<script>-->
<#--jQuery(document).ready(function(){								-->
	<#--jQuery("a[class=life_txt]").click(-->
			    <#--function(){		-->
				<#--var higth =	jQuery("#sns_content").css("height");-->
				<#--if(higth=="20px"){-->
					<#--jQuery("#sns_content").css({"height":"55px","width":"555px"});-->
					<#--}else{-->
					<#--jQuery("#sns_content").css({"height":"20px","width":"480px"});	-->
						<#--}-->
    		   <#--});-->
	<#--//-->
	<#--jQuery("#my_account").mouseover(function(){-->
	<#--jQuery("#my_account").find("s").attr("class","user_act_up");-->
	<#--jQuery("ul.user_act_ul").attr("style","display:block");-->
	<#--});-->
	<#--jQuery("ul.user_act_ul").mouseleave(function(){-->
	<#--jQuery("#my_account").find("s").attr("class","user_act_down");-->
	<#--jQuery(this).attr("style","display:none");-->
	<#--});-->
	<#--//分享动态ajax-->
	<#--jQuery("#share").click(function(){							-->
		<#--var content = jQuery("#sns_content").val();-->
		<#--var length = content.length;-->
		<#--if(length>140){-->
			 <#--showDialog("share_sns","系统提示","输入字数不能多于140个字！",0,"warning",3);-->
			<#--}-->
		<#--else{-->
			<#--if(length<5){-->
			 <#--showDialog("share_sns","系统提示","请输入不少于5个字！",0,"warning",3);	-->
				<#--}else{		-->
			<#--content =replace_em(content);		-->
		   <#--jQuery.post("$!webPath/buyer/dynamic_save.htm",-->
				   <#--{-->
				    <#--"content":content-->
				   <#--},-->
				   <#--function(data){-->
					   <#--if(data){-->
						   <#--jQuery("#ListForm").html(data);-->
						   <#--jQuery("#sns_content").val("");-->
                           <#--showDialog("share_sns","系统提示"," 发布成功！",0,"succeed",3);-->
						   <#--}-->
						   <#--else{-->
						   <#--showDialog("share_sns","系统提示"," 发布失败！",0,"error",3);   -->
							   <#--}-->
	   					<#--},"text");	  -->
		   <#--}}-->
			<#--});-->
	<#--//回复动态ajax、转发动态-->
	<#--jQuery("input[id^=reply_]").click(function(){	-->
	<#--var parent_id=jQuery(this).attr("parent_id");-->
	<#--var reply_content = jQuery("#reply_content_"+parent_id).val();-->
	<#--var length = reply_content.length;-->
	<#--if(length>0){-->
	<#--if(jQuery("#reply_content_"+parent_id).attr("returnMark")){-->
		<#--jQuery.post("$!webPath/buyer/dynamic_ajax_turn.htm",-->
			   <#--{-->
				<#--"dynamic_id":parent_id,-->
				<#--"content":reply_content-->
			   <#--},-->
			   <#--function(data){-->
				   <#--if(data){-->
					   <#--jQuery("#reply_content_"+parent_id).val("");-->
					   <#--jQuery("#ListForm").html(data);-->
					   <#--showDialog("share_sns","系统提示","转发成功！",0,"succeed",3);-->
				   <#--}else{-->
					 <#--showDialog("share_sns","系统提示","转发失败！",0,"error",3); -->
					   <#--}-->
					<#--},"text");-->
		<#--}-->
	<#--else{-->
			<#--jQuery.post("$!webPath/buyer/dynamic_ajax_reply.htm",-->
			   <#--{-->
				<#--"reply_content":reply_content,-->
				<#--"parent_id":parent_id-->
			   <#--},-->
			   <#--function(data){-->
				   <#--if(data){-->
					   <#--jQuery("#reply_content_"+parent_id).val("");-->
					   <#--jQuery("#child_list_"+parent_id).html(data).show();-->
					   <#--showDialog("share_sns","系统提示","回复成功！",0,"succeed",3);-->
				   <#--}else{-->
					    <#--showDialog("share_sns","系统提示","回复失败！",0,"error",3); -->
					   <#--}-->
					<#--},"text");-->
		 <#--}-->
		 <#--}-->
	 <#--else{-->
		<#--showDialog("share_sns","系统提示","请输入内容！",0,"warning",3);-->
		 <#--}-->
	<#--});-->
	<#--//赞动态ajax-->
	 <#--jQuery("a[id^=praise_]").click(function(){-->
	 <#--var praise_id=jQuery(this).attr("praise_id");-->
				<#--jQuery.post("$!webPath/buyer/dynamic_ajax_praise.htm",-->
			    <#--{-->
				<#--"dynamic_id":praise_id-->
			    <#--},-->
			    <#--function(data){-->
				   <#--if(data){-->
					 <#--jQuery("#praise_span_"+praise_id).html(data); -->
					 <#--jQuery("#praise_div_span_"+praise_id).html(data);  -->
					   <#--}-->
						<#--},"text");	   -->
		<#--});-->
	 <#--//点击转发按钮ajax-->
	  <#--jQuery("a[id^=turn_]").click(function(){				-->
	            <#--var turn_id=jQuery(this).attr("turn_id");-->
				<#--var turn_name = jQuery(this).attr("turn_name");-->
				<#--jQuery("textarea[id^=reply_content_]").val("");-->
				<#--if(jQuery.browser.msie==true)-->
				<#--jQuery("#reply_content_"+turn_id).attr("returnMark",true).focus();-->
				<#--else{-->
				<#--jQuery("#reply_content_"+turn_id).attr("placeholder","//转自"+turn_name).attr("returnMark",true).focus();	-->
					<#--}-->
			<#---->
		<#--});-->
	 	 <#---->
	<#--//好友评论点击回复按钮ajax-->
	<#--jQuery("a[id^=child_reply_]").click(function(){-->
	<#--jQuery("textarea[id^=reply_content_]").val("");-->
	 <#--var child_name = jQuery(this).attr("child_name");-->
	 <#--var parent_id = jQuery(this).attr("parent_id");-->
	 <#--jQuery("#reply_content_"+parent_id).val("回复"+child_name+":");-->
		<#--});-->
	<#--//动态显示评论的回复与删除-->
	<#--jQuery("dl[id^=id_dl_]").hover(-->
		  <#--function(){-->
			 <#--var hide_child_id =jQuery(this).attr("child_id");	-->
			 <#--jQuery("#hide_span_"+hide_child_id).show(); -->
			<#--},-->
		  <#--function(){-->
			 <#--var hide_child_id =jQuery(this).attr("child_id");	-->
			 <#--jQuery("#hide_span_"+hide_child_id).hide();-->
		<#--});-->
	<#--//动态显示隐藏删除隐藏按钮-->
	<#--jQuery("span[id^=enjoy_triangle_]").toggle(								   -->
		<#--function(){-->
			<#--jQuery("div[id^=enjhid_list_]").hide();-->
			 <#--var obj_id =jQuery(this).attr("obj_id");-->
			 <#--jQuery("#enjhid_list_"+obj_id).show();-->
		<#--},function(){-->
			 <#--var obj_id =jQuery(this).attr("obj_id");-->
			 <#--jQuery("#enjhid_list_"+obj_id).hide();-->
		<#--});-->
	<#--//隐藏动态-->
	<#--jQuery("a[id^=hide_button_]").click(function(){-->
		<#--var obj_id =jQuery(this).attr("obj_id");-->
		<#--jQuery("#enjoy_pro_one_"+obj_id).slideUp(500);-->
		<#--});-->
	<#--//评论框动态变大-->
	<#--jQuery("textarea[id^=reply_content_]").focus(function(){									  -->
			<#--jQuery("textarea[id^=reply_content_]").attr("class","enjoy_reply_text2");-->
			<#--jQuery("input[id^=reply_]").hide();-->
			<#--jQuery(this).attr("class","enjoy_reply_text");-->
			<#--jQuery(this).parent().find("input").show();-->
		<#--});-->
	<#--//删除动态下方的评论-->
	<#--jQuery("a[id^=reply_dele_ajax_]").click(function(){										 -->
			<#--jQuery("#hidden_dynamic_id").val(jQuery(this).attr("child_id"));-->
			<#--jQuery("#hidden_parent_dynamic_id").val(jQuery(this).attr("parent_id"));-->
			<#--showDialog("share_sns","系统提示","删除后不可恢复，是否继续？",1,"question","",dele_dynamic_reply);-->
			<#--});-->
	<#---->
	<#---->
	<#--//删除动态弹出确认框-->
	<#--jQuery("a[id^=dele_button_]").click(function(){									 -->
		<#--showDialog("share_sns","系统提示","删除后不可恢复，是否继续？",1,"question","",dele_dynamic);	-->
		<#--jQuery("#hidden_dynamic_id").val(jQuery(this).attr("obj_id"));-->
	<#--});-->

	<#--//动态获得输入框内字符数量-->
	<#--jQuery("#sns_content").keydown(function(){-->
		<#--var length =jQuery(this).val().length + 1;-->
		<#--jQuery("span.user_enbtn b span").html(length);-->
	    <#--});-->
	<#--//动态改变分享框大小-->
	<#--jQuery("#sns_content").focus(function(){							  -->
		<#--jQuery(this).css({"height":"55px","width":"555px"});								  -->
		<#--});-->
	<#--//展开评论列表-->
	<#--jQuery("a[id^=show_childs_]").click(function(){-->
	<#--var parent_id =	jQuery(this).attr("parent_id");-->
	<#--var display = jQuery("#child_list_"+parent_id).css("display");-->
		<#--if(display=="none"){-->
			<#--jQuery("#child_list_"+parent_id).slideDown(500);-->
			<#--}else{-->
			<#--jQuery("#child_list_"+parent_id).slideUp(500);	-->
				<#--}-->
	<#--});-->
	<#--//展开表情框-->
	<#--jQuery(".user_face").click(function(){-->
        <#--var display =jQuery("#emo_box").css("display");-->
		<#--if(display=="none"){-->
		<#--jQuery("#emo_box").show();	-->
			<#--}-->
		<#--else{-->
		<#--jQuery("#emo_box").hide();	-->
			<#--}	-->
	<#--});-->
	<#--//鼠标离开表情框事件-->
	<#--jQuery("#emo_box").mouseleave(function(){-->
		<#--jQuery(this).css("display","none");		  -->
	<#--});-->
	<#---->
	<#--//鼠标点击表情况事件-->
	<#--jQuery(".Container_content_QQ ul li img").click(function(){-->
	<#--var emoNum = jQuery(this).attr("emoNum");-->
    <#--var count =	jQuery("#sns_content").val();-->
	<#--jQuery("#sns_content").val(count+"[em_"+emoNum+"]");-->
	<#--});-->
	<#---->
	<#--//关闭表情框事件-->
	<#---->
	<#--jQuery("#emo_close").click(function(){-->
	<#--jQuery("#emo_box").hide();									-->
	<#--});-->
<#--});-->
<#--//删除动态-->
<#--function dele_dynamic(){-->
	<#--var currentPage = jQuery("#currentPage").val();-->
	<#--var obj_id = jQuery("#hidden_dynamic_id").val();-->
		<#--jQuery.post("$!webPath/buyer/dynamic_del.htm",-->
					   <#--{ -->
						 <#--"currentPage":currentPage,   -->
						<#--"id":obj_id-->
					   <#--},-->
					   <#--function(data){-->
						   <#--if(data){-->
							   <#--jQuery("#ListForm").html(data);-->
							    <#--showDialog("share_sns","系统提示","删除成功！",0,"succeed",3);-->
							   <#--}-->
	   							<#--},"text");-->
	<#--};-->
	<#--//删除动态下方评论-->
<#--function dele_dynamic_reply(){-->
	<#--var child_id = jQuery("#hidden_dynamic_id").val();-->
	<#--var parent_id = jQuery("#hidden_parent_dynamic_id").val();	-->
 	<#--jQuery.post("$!webPath/buyer/dynamic_reply_del.htm",-->
					   <#--{    -->
						<#--"id":child_id,-->
						<#--"parent_id":parent_id-->
					   <#--},-->
					   <#--function(data){-->
						   <#--if(data){-->
							   <#--jQuery("#child_list_"+parent_id).html(data);-->
							   <#--showDialog("share_sns","系统提示","删除成功！",0,"succeed",3);-->
							   <#--}-->
	   							<#--},"text");-->
	<#---->
<#--}-->


<#--</script>-->
<#--<script type="text/javascript">-->
<#--//表情过滤-->
<#--function replace_em(str){-->
	<#--str = str.replace(/\</g,'&lt;');-->
	<#--str = str.replace(/\>/g,'&gt;');-->
	<#--str = str.replace(/\n/g,'<br/>');-->
	<#--str = str.replace(/\[em_([0-9]*)\]/g,'<img src="$!webPath/resources/editor/plugins/emoticons/images/$1.gif" border="0" />');-->
	<#--return str;-->
<#--}-->
<#--</script>-->

<#include "../nav.ftl"/>

<div class="main">
    <style type="text/css">
        dl dd span {
            display: inline-block;
        }
    </style>

<div class="wrap">

<#--$!httpInclude.include("/top.htm")-->
<#--<div class="main"> $!httpInclude.include("/buyer/head.htm")-->
  <#--<div class="user_center">-->
    <#--<table width="1200" border="0" cellspacing="0" cellpadding="0" class="user_table">-->
      <#--<tr> $!httpInclude.include("/buyer/nav.htm")-->
        <#--<td id="centerbg" valign="top"><div class="table_left">-->
            <#--<div class="user_info"> <span class="user_info_mes">您有<b>#if($!msgs)$!msgs.size()#else 0 #end</b>条站内信息<a href="$!webPath/buyer/message.htm">点击查看</a></span> <span class="user_info_name"><i>$!{user.userName}</i></span> #set($credit=$!storeViewTools.generic_user_credit("$!user.id"))-->
              <#--#set($img="$!webPath/resources/style/common/images/level_0.gif")-->
              <#--#if($credit<10 && $credit>0)-->
              <#--#set($credit=${credit} / 2)-->
              <#--#set($credit=$credit+1)-->
              <#--#end-->
              <#--#if($credit>=20)-->
              <#--#set($img="$!webPath/resources/style/common/images/level_2.gif") -->
              <#--#set($credit=($credit - 20)/2) -->
              <#--#set($credit=$credit+1)-->
              <#--#end-->
              <#--#if($credit>=10)-->
              <#--#set($img="$!webPath/resources/style/common/images/level_1.gif") -->
              <#--#set($credit=($credit - 10)/2)-->
              <#--#set($credit=$credit+1)-->
              <#--#end-->
              <#--#if($credit>5)#set($credit=5)#end <span class="user_info_grade"> #if($!credit==0) <img src="$!webPath/resources/style/common/images/level_-1.gif"/> #else #foreach($count in [1..$!credit])<img style="margin-left:1px;" src="$!img" />#end #end </span><span class="user_account">-->
              <#--<!--user_act_up向下三角user_act_down向上三角&ndash;&gt;-->
              <#--<strong id="my_account"><a href="javascript:void(0);">我的账户</a><s class="user_act_down"></s></strong>-->
              <#--<ul class="user_act_ul"  style="display:none">-->
                <#--<li><a href="$!webPath/buyer/account.htm">查看余额</a></li>-->
                <#--<li><a href="$!webPath/buyer/predeposit.htm">账户充值</a></li>-->
                <#--<li><a href="$!webPath/buyer/predeposit_log.htm">消费记录</a></li>-->
              <#--</ul>-->
              <#--</span></div>-->
            <#--<div class="user_buyinfo"> <span><a href="$!webPath/buyer/order.htm?order_status=order_submit">待付款(<b class="red">$!orderViewTools.query_user_order("order_submit")</b>)</a></span><span><a href="$!webPath/buyer/order.htm?order_status=order_shipping">待确认收货(<b class="black">$!orderViewTools.query_user_order("order_shipping")</b>)</a></span><span><a href="$!webPath/buyer/order.htm?order_status=order_receive">待评价(<b class="black">$!orderViewTools.query_user_order("order_receive")</b>)</a></span> <span> <a href="$!webPath/buyer/address.htm" class="user_ads_manager">收货地址管理</a></span></div>-->
            <#--<div class="user_life">-->
              <#--<div class="user_life_box">-->
                <#--<div class="user_life_edit">-->
                  <#--<textarea name="sns_content" id="sns_content" placeholder=" 生活每天都有新鲜事.每天都来聊一聊" class="user_life_text" style="height:20px;width:480px;" ></textarea>-->
                  <#--<!--user_life_text编辑前style=" height:20px;width:480px;"编辑时style=" height:55px;width:555px;&ndash;&gt;-->
                  <#--<div class="user_life_fun">-->
                   <#--<a href="javascript:void(0);" title="分享店铺" dialog_title="您关注的店铺" dialog_uri="$!webPath/buyer/fav_store_list.htm" dialog_width="500" dialog_height="70" dialog_id="fav_store_list" dialog_top="200" class="life_shop"></a> -->
                   <#--<a href="javascript:void(0);" title="分享商品" dialog_title="您关注的商品" dialog_uri="$!webPath/buyer/fav_goods_list.htm" dialog_width="500" dialog_height="70" dialog_id="fav_goods_list" dialog_top="200" class="life_gife"></a> -->
                   <#--<a href="javascript:void(0);" class="life_txt"></a>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="user_enjoy"> <span class="user_enbtn"><b><span>0</span>/140字</b>-->
                  <#--<input name="share" id="share" type="button" class="enbtn" value="分享"/>-->
                  <#--</span> <span class="user_face_box"> <a href="javascript:void(0);"  class="user_face">表情</a> -->
                  <#--</span> -->
                  <#--</div>-->
              <#--</div>-->
              <#---->
    <#--<!--表情框&ndash;&gt; -->
  <#---->
<#--<div class="Container" style="display:none" id="emo_box">-->
    <#--<div class="Container_top">-->
    <#--<img src="$!webPath/resources/style/system/front/default/images/usercenter/top_b.png" width="7" height="4" /></div>-->
    <#--<div class="Container_content">-->
        <#--<div class="Container_content_title"><span class="Container_content_QQ_left">默认表情</span>-->
        <#--<span class="Container_content_QQ_right"><a href="javascript:void(0);" id="emo_close"></a></span></div>-->
        <#--<div class="Container_content_QQ">-->
        	<#--<ul>-->
              <#--#set($emoNum=0) -->
           	 <#--#foreach($emo in $emoticons)-->
                <#--#set($emoNum = $!emoNum + 1)-->
  <#--<li><img src="$!webPath/resources/editor/plugins/emoticons/images/$!{emoNum}.gif" emoNum="$!emoNum" width="24" height="24" style="cursor:pointer"/></li>-->
	            <#--#end-->
            <#--</ul>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
			<#--<!--表情框&ndash;&gt; -->
            <#--</div>-->
            <#--<div class="friend_trends">-->
              <#--<div class="f_trends_tab">-->
                <#--<ul>-->
                  <#--<li #if($!type!=4 || !$!type) class="this" #end><a href="$!webPath/buyer/index.htm">好友动态</a></li>-->
                  <#--<li #if($!type==4)class="this" #end><a href="$!webPath/buyer/index.htm?type=4">店铺动态</a></li>-->
                <#--</ul>-->
              <#--</div>-->
              <#--<form id="ListForm" name="ListForm" action="$!webPath/buyer/index.htm" method="post">-->
                <#--<div class="friend_trebox">-->
                <#--#if($!type!=4 || !$!type)-->
                  <#--<div class="firend_group">-->
                   <#--<span class="fgroup_a">-->
                  <#--<a href="$!webPath/buyer/index.htm?type=2" #if($!type==2) class="this" #end>好友</a>-->
                  <#--<a href="$!webPath/buyer/index.htm?type=1" #if($!type==1) class="this" #end>自己</a>-->
                  <#--<a href="$!webPath/buyer/index.htm?type=3" #if($!type==3) class="this" #end>我的关注</a>-->
                  <#--</span> -->
                  <#--</div>-->
                  <#--#end-->
                  <#--<div class="enjoy_box">-->
                    <#---->
                    <#--<!--分享1店铺&ndash;&gt;-->
                    <#--#foreach($obj in $objs)-->
                    <#--<div class="enjoy_pro_one" id="enjoy_pro_one_$!{obj.id}">-->
                      <#--<div class="enjoy_del_hid"> <span class="enjoy_triangle" id="enjoy_triangle_$!{obj.id}" style="cursor:pointer" obj_id=$!obj.id><s></s></span>-->
                        <#--<div class="enjhid" id="enjhid_list_$!{obj.id}" style="display:none"> <a href="javascript:void(0);" id="hide_button_$!{obj.id}" obj_id=$!obj.id>隐藏</a> #if($!user.id==$!obj.user.id) <a href="javascript:void(0);" id="dele_button_$!{obj.id}" obj_id=$!obj.id>删除</a> #end </div>-->
                      <#--</div>-->
                      <#--<div class="enjoy_one_out"> <span class="enjoy_pro_per"> 																					#set($img="$!webPath/$!config.memberIcon.path/$!config.memberIcon.name")-->
                        <#--#if($!obj.user.photo)-->
                        <#--#set($img="$!webPath/$!obj.user.photo.path/$!obj.user.photo.name")-->
                        <#--#end <img src="$!img" width="60" height="60" /> </span>-->
                        <#--<div class="enjoy_pro_info">-->
                          <#--<p class="enjoy_p"><a href="javascript:void(0);">$!obj.user.userName :</a> $!obj.content</p>-->
                          <#--<!--动态中的店铺&ndash;&gt;-->
                          <#--#if($!obj.store)-->
                          <#--<dl class="enjoy_dl">-->
                            <#--<dt><span class="compimg_span">-->
                             <#--#if($!obj.store.store_logo)-->
                            <#--#set($store_logo="$!webPath/$!{obj.store.store_logo.path}/$!{obj.store.store_logo.name}")-->
                            <#--#else-->
                            <#--#set($store_logo="$!webPath/$!config.storeImage.path/$!config.storeImage.name")-->
                            <#--#end-->
                              <#--<p><img src="$!store_logo" width="100" height="100" /></p>-->
                              <#--</span> -->
                              <#--</dt>-->
                            <#--<dd>-->
                              <#--<ul class="enjoy_pro_deta">-->
                                <#--<li><span>店铺名称：</span><strong><a href="$!webPath/store_$!{obj.store.id}.htm" target="_blank" class="blue2">$!obj.store.store_name</a></strong></li>-->
                                <#--<li><span>店铺星级：</span>-->
                                <#--<strong class="level">-->
                                    <#--#set($credit=$!storeViewTools.generic_store_credit("$!obj.store.id"))-->
                                    <#--#set($img="$!imageWebServer/resources/style/common/images/level_0.gif")-->
                                    <#--#if($credit<10 && $credit>0)-->
                                    <#--#set($credit=${credit} / 2)-->
                                    <#--#set($credit=$credit+1)-->
                                    <#--#end-->
                                    <#--#if($credit>=20)-->
                                    <#--#set($img="$!imageWebServer/resources/style/common/images/level_2.gif") -->
                                    <#--#set($credit=($credit - 20)/2) -->
                                    <#--#set($credit=$credit+1)-->
                                    <#--#end-->
                                    <#--#if($credit>=10)-->
                                    <#--#set($img="$!imageWebServer/resources/style/common/images/level_1.gif") -->
                                    <#--#set($credit=($credit - 10)/2)-->
                                    <#--#set($credit=$credit+1)-->
                                    <#--#end-->
                                    <#--#if($credit>5)#set($credit=5)#end-->
                               <#--#if($!credit==0) -->
                               <#--<img src="$!imageWebServer/resources/style/common/images/level_-1.gif"/> -->
                               <#--#else-->
                                <#--#foreach($count in [1..$!credit])<img style="margin-left:1px;" src="$!img" />#end -->
                               <#--#end-->
                                <#--</strong>-->
                                <#--</li>-->
                                <#--<li>收藏数($!{obj.store.favorite_count})</li>-->
                                <#--<li><a href="$!webPath/store_$!{obj.store.id}.htm" target="_blank" class="seedeta">查看详情</a></li>-->
                              <#--</ul>-->
                            <#--</dd>-->
                          <#--</dl>-->
                          <#--#end-->
                         <#--<!--动态中的店铺&ndash;&gt;-->
                         <#---->
                            <#--<!--动态中的商品&ndash;&gt;-->
                            <#--#if($!obj.goods)-->
                          <#--<dl class="enjoy_dl">-->
                            <#--<dt><span class="compimg_span">-->
                             <#--#set($goods_logo="$!webPath/$!{obj.goods.goods_main_photo.path}/$!{obj.goods.goods_main_photo.name}")-->
                              <#--<p><a href="$!webPath/goods_$!{obj.goods.id}.htm" target="_blank"><img src="$!goods_logo" width="160" height="160" /></a></p>-->
                              <#--</span> <em class="enjoy_em"></em><i class="enjoy_money"><b class="enjoy_stype_gif"></b><strong>￥$!obj.goods.store_price</strong></i></dt>-->
                            <#--<dd>-->
                              <#--<ul class="enjoy_pro_deta">-->
                                <#--<li><span>宝贝名称：</span><strong><a href="$!webPath/goods_$!{obj.goods.id}.htm" target="_blank" class="blue2">$!obj.goods.goods_name</a></strong></li>-->
                                <#--<li><span>商品原价：</span><strong style="text-decoration:line-through;">￥ $!obj.goods.goods_price</strong></li>-->
                                 <#--<li><span>店铺价格：</span>￥ $!obj.goods.store_price</li>-->
                                <#--<li><span>所属店铺：</span><a href="$!webPath/store_$!{obj.goods.goods_store.id}.htm" target="_blank">$!obj.goods.goods_store.store_name</a></li>-->
                              <#--</ul>-->
                            <#--</dd>-->
                          <#--</dl>-->
                          <#--#end-->
                         <#--<!--   动态中的商品&ndash;&gt;-->
                          <#--<div class="enjoy_extra" > <span class="enjoy_exright"> <a href="javascript:void(0);" id="praise_$!{obj.id}" praise_id=$!obj.id>赞(<span id="praise_span_$!{obj.id}">$!obj.praiseNum</span>)</a> <a href="#reply_content_$!{obj.id}" id="turn_$!{obj.id}" turn_id=$!obj.id turn_name=$!obj.user.userName>转发(<span id="turn_span_$!{obj.turnNum}">$!obj.turnNum</span>)</a> <a href="javascript:void(0);" id="show_childs_$!obj.id" parent_id=$!obj.id>评论($!obj.childs.size())</a> </span> <span class="enjoy_exleft"> <b>$!CommUtil.formatLongDate($!obj.addTime)</b>来自：$!obj.user.userName</span></div>-->
                          <#--<!--评论开始&ndash;&gt;-->
                          <#--<div class="enjoy_agree"><span id="praise_div_span_$!{obj.id}">$!obj.praiseNum</span>人觉得很赞</div>-->
                          <#--<div class="enjoy_reply">-->
                            <#--<div class="enjoy_entri"></div>-->
                            <#--<div class="enjoy_reply_all" >-->
                              <#--<div class="enjoy_first" id="child_list_$!{obj.id}" style="display:none" >-->
                               <#--#if($!obj.childs.size()>0)-->
                                <#--#foreach($child in $obj.childs)-->
                                <#--#set($child_img="$!webPath/$!config.memberIcon.path/$!config.memberIcon.name")-->
                                <#--#if($!child.user.photo)-->
                                <#--#set($child_img="$!webPath/$!child.user.photo.path/$!child.user.photo.name")-->
                                <#--#end-->
                                <#--<dl id="id_dl_$!{child.id}" child_id=$!child.id>-->
                                  <#--<dt><img src="$!child_img" width="40" height="40" /></dt>-->
                                  <#--<dd>-->
                                    <#--<div class="enjoy_rptext"><a href="javascript:void(0);" class="blue2">$!child.user.userName：</a>$!child.content</div>-->
                                    <#--<div class="enjoy_fbtime">$!CommUtil.formatLongDate($!child.addTime) <span class="reply_fun" id="hide_span_$!{child.id}"  style="display:none"> #if($!child.user.id!=$!user.id) <a href="javascript:void(0);" id="child_reply_$!{child.id}" parent_id=$!obj.id  child_name=$!child.user.userName >回复</a> #end -->
                                      <#--#if($!child.user.id==$!user.id)<a href="javascript:void(0);" id="reply_dele_ajax_$!{child.id}" child_id=$!child.id parent_id=$!obj.id>删除</a>#end</span></div>-->
                                  <#--</dd>-->
                                <#--</dl>-->
                                <#--#end-->
                                <#--#end </div>-->
                              <#--<div class="reply_box">-->
                                <#--<textarea name="reply_content_$!{obj.id}" id="reply_content_$!{obj.id}" placeholder=" 我也说一句..." cols="" rows="" class="enjoy_reply_text2"></textarea>-->
                                <#--<input name="reply_$!{obj.id}" id="reply_$!{obj.id}" parent_id="$!{obj.id}" type="button"  class="enjoy_discuss" value="评论"-->
           <#--style="display:none"/>-->
                              <#--</div>-->
                            <#--</div>-->
                          <#--</div>-->
                          <#--<!--评论结束&ndash;&gt;-->
                        <#--</div>-->
                      <#--</div>-->
                    <#--</div>-->
                    <#--#end-->
                    <#--<div class="alldel">-->
                      <#--<div  class="userfenye"> $!gotoPageFormHTML</div>-->
                    <#--</div>-->
                  <#--</div>-->
                <#--</div>-->
                 <#--<input id="hidden_parent_dynamic_id"  type="hidden" value="">-->
                <#--<input id="hidden_dynamic_id"  type="hidden" value="">-->
                <#--<input id="type" name="type" type="hidden" value="$!type"/>-->
                <#--<input id="currentPage" name="currentPage" type="hidden" value="$!currentPage"/>-->
              <#--</form>-->
              <#--#if(!$!objs)-->
             <#--<div style="padding-left:300px; float:left;">-->
             <#--<span style="float:left;"><img src="$!webPath/resources/style/common/images/icon/face-sad.png"/></span>-->
             <#--<span style=" padding-top:12px; padding-left:5px; float:left; font-size:16px;">你没有任何动态！</span>-->
             <#--</div>-->
             <#--#end-->
            <#--</div>-->
          <#--</div>-->
      <#---->
          <#---->
          <#--</td>-->
      <#--</tr>-->
    <#--</table>            -->
  <#--</div>-->
  <#--$!httpInclude.include("/footer.htm") </div>-->

</div>
</div>
</@override>
<@extends name="framework.ftl"/>
