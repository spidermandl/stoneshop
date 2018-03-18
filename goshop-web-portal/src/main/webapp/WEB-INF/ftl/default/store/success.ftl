<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统提示 - ${(config.poweredby)!}</title>
    <meta name="keywords" content="${(store.storeKeywords)!}" />
    <meta name="description" content="${(store.storeDescription)!}" />
    <meta name="generator" content="${(config.meta_generator)!}" />
    <meta name="author" content="${(config.meta_author)!}">
    <meta name="copyright" content="${(config.copyRight)!}">
    <#if (config.website_ico)??>
        <link rel="shortcut icon" href="${imageWebServer!}/${(config.website_ico.path)!}/${(config.website_ico.name)!}" type="image/x-icon"/>
    </#if>
    <link href="${S_URL}/static/styles/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
    <link href="${S_URL}/static/styles/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />
    <#--<link href="${S_URL}/static/styles/system/front/default/css/index.css" type="text/css" rel="stylesheet" />-->
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>

    <#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
    <#--<link href="${S_URL}/resources/style/system/front/default/css/index.css" type="text/css" rel="stylesheet" />-->
    <#--<link href="${S_URL}/resources/style/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />-->
    <#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
</head>
<body>
${httpInclude.include("/top")}
<div class="main">
  ${httpInclude.include("/head")}
  ${httpInclude.include("/nav1")}
  ${uc_login_js!}
  <div class="index" style="height:300px;">
    <div style="height:280px;border:1px solid #CCCCCC;overflow:hidden; margin-left:auto; margin-right:auto; margin-top:10px;">
      <div style="font-size:16px; color:#666666;margin-top:100px; padding-left:40%;">
      <span style="float:left;">
      <img src="${S_URL}/static/images/common/icon/succeed.png" />
      </span>
      <span style=" padding-top:10px; line-height:30px; float:left;">${op_title!}！</span>
      </div>
    </div>
  </div>
<script>
	  var count=3;
	  window.setInterval(go,1000);
	  function go(){
	    count--;
	    if(count==0) window.location.href="${url!}";
	  }
	</script>
  ${httpInclude.include("/footer")}
</div>
</body>
</html>
