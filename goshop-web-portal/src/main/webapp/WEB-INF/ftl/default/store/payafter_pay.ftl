<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>货到付款 - ${(config.poweredby)!}</title>
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
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>

    <#--<link href="${S_URL}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />-->
    <#--<link href="${S_URL}/resources/style/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />-->
    <#--<script src="${S_URL}/resources/js/jquery-1.8.3.min.js"></script>-->
    <#--<script src="${S_URL}/resources/js/jquery.validate.min.js"></script>-->
<script>
jQuery(document).ready(function(){
  jQuery("#theForm").validate({
    rules:{
		pay_msg:{required:true}
	},
	messages:{
	    pay_msg:{
			required:"付款说明不能为空"
		}
	}
 });
});
</script>
</head>
<body>
${httpInclude.include("/top")}
<div class="main">
  ${httpInclude.include("/head")}
  ${httpInclude.include("/nav")}
  <div class="index">
  <form action="${S_URL}/order_pay_payafter" method="post" id="theForm">
    <div class="index2">
      <div class="buy_step_last">
        <ul>
          <li>
              <h1>货到付款</h1>
          </li>
          <li>
              <h2>${paymentTools.queryStorePayment("payafter","${(store_id)!}")["content"]!}</h2>
          </li>
          <li>
              <h3>确定使用支付方式为“货到付款”支付该订单吗？</h3>
          </li>
          <li><span class="steptext">

          </span></li>
          <li><span class="stepbtn">
              <input name="" type="submit"  value="提交" style="cursor:pointer;"/>
          </span>
              <input name="order_id" type="hidden" id="order_id" value="${order_id!}" />
              <input name="pay_session" type="hidden" id="pay_session" value="${pay_session!}" />
          </li>
        </ul>
      </div>
    </div>
    </form>
  </div>
  ${httpInclude.include("/footer")}
</div>
</body>
</html>
