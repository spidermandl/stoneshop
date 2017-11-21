<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>电商测试</title>
    <link href="${S_URL}/static/styles/shop/skin_0.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>GoShop系统消息</h3>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <table class="table tb-type2 msg">
        <tbody class="noborder">
        <tr>
            <td rowspan="5" class="msgbg"></td>
            <td class="tip"></td>
        </tr>
        <tr>
            <td class="tip2">您还没有注册为商家，<a  href="${SELLER_DOMAIN}${SHOP_ROOT}/register.html">点击注册</a></td>
        </tr>
        <tr>
            <td>          <a href="javascript :history.back(-1)" class="btns"><span>返回上一页</span></a>
                       </td>
        </tr>
        </tbody>
    </table>
</div>
</body></html>
