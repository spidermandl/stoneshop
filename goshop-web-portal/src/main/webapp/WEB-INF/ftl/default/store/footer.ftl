<#assign S_URL=request.contextPath  />
<script>
jQuery(document).ready(function(){
    //滚动条滚动事件
	jQuery(window).scroll(function(){
	var top = jQuery(document).scrollTop();
	if(top==0){
		jQuery(".back_box").hide();
    }else{
		jQuery(".back_box").show();
    }
	});
});
</script>
<#--<div class="wrapper" id="footer">-->
    <#--<p><a href="${S_URL}">首页</a>-->
        <#--| <a href="${S_URL}">招聘英才</a>-->
        <#--| <a href="${S_URL}">合作及洽谈</a>-->
        <#--| <a href="${S_URL}">联系我们</a>-->
        <#--| <a href="h${S_URL}">关于ShopNC</a>-->
    <#--</p>-->
    <#--Copyright 2015-2016 GoShop Inc.,All rights reserved.<br>-->
    <#--Powered by <span class="vol"><font class="b">Go</font><font class="o">Shop</font></span> <br>-->
<#--</div>-->
<div class="footer">
<div class="footerArea">
  <ul>
    <li>
    <#assign navs=navTools.queryNav(1,-1) />
    <#list navs as nav >
        <#if CommUtil.indexOf("${(nav.url)!}","http://") gte 0 >
            <#assign url="${(nav.url)!}" />
        <#else>
            <#assign url="${S_URL}/${(nav.url)!}" />
        </#if>
        <a href="${url!}" <#if nav.newWin==1 >target="_blank"
        </#if>>${(nav.title)!}</a><#if nav_index lt navs!?size >|</#if>
    </#list>
    </li>
    <li class="hui2">Copyright 2011-2017 wemall Inc. All rights reserved</li>
    <li class="hui2">${(config.poweredby)!} V2.5</li>
    <li>${(config.codeStat)!}</li>
  </ul>
</div>
</div>


<!--返回最顶部-->
<div class="back_box"  style=" display:none;width:50px; height:50px;position:fixed; bottom:180px; right:25px; _position:absolute;/*兼容IE6的代码*/
_bottom:auto;/*兼容IE6的代码*/
_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0))); ">
    <div class="back_index"><a href="${S_URL}/index" target="_blank"  title="返回主页"></a></div>
    <div class="back_top">
        <a id="toTop" href="#" bosszone="hometop" style="display: block;" onclick="window.scrollTo(0,0);return false" title="返回顶部" target="_self" ></a>
    </div>
</div>
