<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>首页</title>
    <script type="text/javascript">
        var SITEURL = '${S_URL}';
    </script>
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/base.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/member.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/home_login.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/scripts/dialog/dialog.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/scripts/jquery-ui/themes/ui-lightness/jquery.ui.css">
    <script src="${S_URL}/static/scripts/jquery/jquery.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery-browser.js"></script>
    <script src="${S_URL}/static/scripts/jquery/waypoints.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/jquery.ui.js"></script>
    <script src="${S_URL}/static/scripts/jquery-ui/i18n/zh-CN.js"></script>
    <script src="${S_URL}/static/scripts/utils/area_array.js"></script>
    <script src="${S_URL}/static/scripts/shop/common.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.masonry.js"></script>
    <script src="${S_URL}/static/scripts/shop/common_select.js"></script>
    <script src="${S_URL}/static/scripts/qtip/jquery.qtip.min.js"></script>
    <script src="${S_URL}/static/scripts/shop.js"></script>
    <script charset="utf-8" id="dialog_js" src="${S_URL}/static/scripts/dialog/dialog.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/scripts/dialog/dialog.css">
</head>
<body>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<#--<#include "top.ftl"/>-->
${httpInclude.include("/up")}
<div id="container" class="wrapper">
        <div class="main">
            <style type="text/css">
                dl dd span {
                    display: inline-block;
                }
            </style>

            <div class="wrap">
                <@block name="main" ></@block>
            </div><!-- end wrap-->
        </div><!-- end main-->
<#include "footer.ftl"/>
</body>

</html>
