<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>商家中心</title>
    <script type="text/javascript">
        var SITEURL = '${S_URL}';
    </script>
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/shop/base.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/scripts/dialog/dialog.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/scripts/jquery-ui/themes/ui-lightness/jquery.ui.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/seller_center.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/overlay.css">
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
    <script src="${S_URL}/static/scripts/jquery/perfect-scrollbar.js"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.mousewheel.js"></script>
    <script charset="utf-8" id="dialog_js" src="${S_URL}/static/scripts/dialog/dialog.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/scripts/dialog/dialog.css">

    <link rel="stylesheet" href="${S_URL}/static/styles/font-awesome/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="${S_URL}/static/styles/font-awesome/css/font-awesome-ie7.min.css">
    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${S_URL}/static/scripts/utils/html5shiv.js"></script>
    <script src="${S_URL}/static/scripts/utils/respond.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="${S_URL}/static/scripts/utils/IE6_PNG.js"></script>
    <script>
        DD_belatedPNG.fix('.pngFix');
    </script>
    <script>
        // <![CDATA[
if((window.navigator.appName.toUpperCase().indexOf("MICROSOFT")>=0)&&(document.execCommand))
try{
document.execCommand("BackgroundImageCache", false, true);
   }
catch(e){}
// ]]>
</script>
<![endif]-->
    <script src="${S_URL}/static/scripts/seller.js"></script>
    <script>
        var COOKIE_PRE = '5BF5_';
        var _CHARSET = 'utf-8';
        var SITEURL = '${S_URL}';
        var RESOURCE_SITE_URL = '${S_URL}';
        var SHOP_RESOURCE_SITE_URL = '${S_URL}';
        var SHOP_TEMPLATES_URL = '${S_URL}';
    </script>


    <style type="text/css" id="poshytip-css-tip-yellowsimple">
        div.tip-yellowsimple{
            visibility:hidden;
            position:absolute;
            top:0;left:0;
        }
        div.tip-yellowsimple table, div.tip-yellowsimple td{
            margin:0;
            font-family:inherit;
            font-size:inherit;
            font-weight:inherit;
            font-style:inherit;
            font-variant:inherit;
        }
        div.tip-yellowsimple td.tip-bg-image span{
            display:block;
            font:1px/1px sans-serif;
            height:10px;
            width:10px;
            overflow:hidden;
        }
        div.tip-yellowsimple td.tip-right{
            background-position:100% 0;
        }
        div.tip-yellowsimple td.tip-bottom{
            background-position:100% 100%;}
        div.tip-yellowsimple td.tip-left{background-position:0 100%;}
        div.tip-yellowsimple div.tip-inner{background-position:-10px -10px;}
        div.tip-yellowsimple div.tip-arrow{
            visibility:hidden;position:absolute;overflow:hidden;font:1px/1px sans-serif;}
    </style>
</head>
<body>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<#include "header.ftl"/>

<@block name="main" ></@block>

<#include "footer.ftl"/>
</body>

</html>
