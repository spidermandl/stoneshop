<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>支付结果 - ${(config.poweredby)!}</title>
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

</head>
<body>
${httpInclude.include("/top")}
<div class="main">
    ${httpInclude.include("/head")}
    ${httpInclude.include("/nav1")}
        <div class="index">
            <div class="position">当前位置：<a href="${S_URL}/index" >首页</a> > <span>支付结果</span></div>
            <div class="index2">
                <div class="paystep_info">
                    <span class="payinfotop"><i>订单编号：</i>${(obj.orderId)!}</span>
                    <span class="payinfotop"> <i>支付金额：</i><em>¥${(obj.totalprice)!?string("0.00")}</em></span>
                        <#if ((obj.orderStatus)!-1)==0 >
                          <#assign status="已取消" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==10 >
                          <#assign status="已取消" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==15 >
                          <#assign status="线下支付待审核" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==20 >
                          <#assign status="付款成功" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==30 >
                          <#assign status="已发货" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==40 >
                          <#assign status="已收货" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==50 >
                          <#assign status="已完成,已评价" />
                        </#if>
                        <#if ((obj.orderStatus)!-1)==60 >
                          <#assign status="已结束" />
                        </#if>
                    <span class="paymode"><i>支付状态：</i><b style="color:#F00;">${status!}</b></span>
                    <span class="paymodebtn">
                        <#if ((obj.orderStatus)!0)==10 >
                            <i class="paym_btn">
                                <a href="${S_URL}/order_pay_view?id=${(obj.id)!}">返回重新支付</a>
                            </i>
                        </#if>
                            <i class="paym_btn">
                                <a href="${S_URL}/buyer/order_view?id=${(obj.id)!}">查看订单信息</a>
                            </i>
                    </span>
                </div>
            </div>
        </div>
    ${httpInclude.include("/footer")}
</div>
</body>
</html>
