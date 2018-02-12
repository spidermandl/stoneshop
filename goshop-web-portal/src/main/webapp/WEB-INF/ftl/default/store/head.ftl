<#assign S_URL=request.contextPath  />
<script>
jQuery(document).ready(function(){
  jQuery(".toph_bgsear li").mouseover(function(){
      jQuery(".toph_bgsear>li").show();
  }).mouseleave(function(){
       jQuery(".toph_bgsear>li").last().hide();
  }).click(function(){
	 var index=jQuery(this).index();
	 if(index==1){
	   var f_text=jQuery(".toph_bgsear li").find("a").first().text();
       jQuery(".toph_bgsear li").find("a").first().html(jQuery(this).find("a").text()+"<s></s>");
	   jQuery(".toph_bgsear li").find("a").last().text(f_text);
	   jQuery("#type").val(jQuery(this).attr("type"));
	   jQuery(".toph_bgsear>li").last().hide();
	 }
  });

  jQuery(".two_code_a").click(function(){
	  jQuery(this).parent().remove();
	  });

});
function search_form(){
  var keyword=arguments[0];
  var type=arguments[1];
  if(keyword!=""&&keyword!=undefined){
   jQuery("#keyword").val(keyword);
  }
  if(type!=""&&type!=undefined){
    jQuery("#type").val(type);
  }
  jQuery("#searchForm").submit();
  jQuery("#keyword").val("");
}
</script>

<!--
  <div class="top_banner clearFix">
  	<img src="${S_URL}/upload/advert/topbanner.jpg">
  </div>
 -->
<!--[if lt IE 9]>
    <script src="${S_URL}/static/scripts/css3-mediaqueries.js"></script>
<![endif]-->
<div id="header_form" >
  <script src="${S_URL}/static/scripts/jquery.cookie.js"></script>
  <div id="head_h" class="head_width">
	  <div class="head clearFix">
	    <div class="logo">
			<#if (config.websiteLogo)!?? >
				<a href="${S_URL}/index">
					<img src="${imageWebServer!}/${(config.websiteLogo.path)!}/${(config.websiteLogo.name)!}" border="0" />
				</a>
			<#else>
				<a href="${S_URL}/index">
					<img src="${imageWebServer!}/static/images/logo.png" border="0" />
				</a>
			</#if>
		</div>
	    <div class="searchForm">
		    <form action="${S_URL}/search" method="post" target="_blank" id="searchForm">
			  	<input name="type" type="hidden" id="type" value="${type!}" />
			    <div class="toph_bigsearch">
			      <div class="toph_sear">
			        <ul class="toph_bgsear">
			          <li class="this" type="goods">
						  <a href="javascript:void(0);">宝贝<s></s></a>
					  </li>
			          <li style="display:none" type="store">
						  <a href="javascript:void(0);">店铺</a>
					  </li>
			        </ul>
			        <input name="keyword" type="text" id="keyword" placeholder="搜索其实真的很简单！" x-webkit-speech lang="zh-CN" onwebkitspeechchange="jQuery('#searchForm').submit()" class="toph_sear_txt"/>
			        <input name="input" type="button" value="搜索" style="cursor:pointer;" onclick="search_form();" class="toph_sear_btn" />
			      </div>
			      <div class="keyword">
					  <#list  CommUtil.splitByChar("${(config.hotSearch)!}",",") as info >
						  <a href="javascript:void(0);" onclick="search_form('$info','goods');">${info!}</a>
					  </#list>
				  </div>
			    </div>
		    </form>
	    </div>
	  </div>
  </div>
</div>
