<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">
    <title>商家管理中心登录</title>
    <script src="${S_URL}/static/scripts/jquery/jquery.js" type="text/javascript"></script>
    <script src="${S_URL}/static/scripts/jquery/jquery.validation.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/shop/base.css">
    <link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/seller_center.css">
    <link rel="stylesheet" href="${S_URL}/static/styles/font-awesome/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="${S_URL}/static/styles/font-awesome/css/font-awesome-ie7.min.css">
    <![endif]-->
     <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${S_URL}/static/scripts/utils/html5shiv.js"></script>
    <script src="${S_URL}/static/scripts/utils/respond.min.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="${S_URL}/static/scripts/utils/IE6_MAXMIX.js"></script>
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
    <script type="text/javascript" language="JavaScript">
        window.onload = function() {
            tips = new Array(2);
            tips[0] = document.getElementById("loginBG01");
            tips[1] = document.getElementById("loginBG02");
            index = Math.floor(Math.random() * tips.length);
            tips[index].style.display = "block";
        };
        $(document).ready(function() {
            $("#form_login").validate({
                errorPlacement:function(error, element) {
                    element.prev(".repuired").append(error);
                },
                rules:{
                    username:{
                        required:true
                    },
                    password:{
                        required:true
                    },
                    captcha:{
                        required:true,
                        remote:{
                            url:"${S_URL}/check/captcha",
                            type:"get",
                            data:{
                                captcha:function() {
                                    return $("#captcha").val();
                                }
                            }
                        }
                    }
                },
                messages:{
                    username:{
                        required:"<i class='icon-exclamation-sign'></i>用户名不能为空"
                    },
                    password:{
                        required:"<i class='icon-exclamation-sign'></i>密码不能为空"
                    },
                    captcha:{
                        required:"<i class='icon-exclamation-sign'></i>验证码不能为空",
                        remote:"<i class='icon-frown'></i>验证码错误"
                    }
                }
            });
            //Hide Show verification code
            $("#hide").click(function(){
                $(".code").fadeOut("slow");
            });
            $("#captcha").focus(function(){
                $(".code").fadeIn("fast");
            });

        });
        function codeImage(){
            $('#codeimage').attr("src",'${S_URL}/validationCodeServlet.png?t=' + Math.random());
        }

    </script>
    <style>
        label.error {
            color: #f30;
        }
    </style>
</head>
<body>
<div class="ncsc-login-bg" id="loginBG01">
    <p class="pngFix"></p>
</div>
<div class="ncsc-login-bg" id="loginBG02" style="display: block;">
    <p class="pngFix"></p>
</div>
<div class="ncsc-login-container">
    <div class="ncsc-login-title">
        <h2>商家管理中心</h2>
    <span>请输入您注册商铺时申请的商家名称<br>
    登录密码为商城用户通用密码</span></div>
<#if P_EXCEPTION?exists>
    <label class="error"  style="padding-left: 100px;">${P_EXCEPTION}</label>
</#if>
    <form method="post" action="${S_URL}/login.do" id="form_login">

        <div class="input">
            <label>用户名</label>
            <span class="repuired"></span>
            <input type="text" autofocus="" class="text" autocomplete="off" name="username">
            <span class="ico"><i class="icon-user"></i></span> </div>
        <div class="input">
            <label>密码</label>
            <span class="repuired"></span>
            <input type="password" class="text" autocomplete="off" name="password">
            <span class="ico"><i class="icon-key"></i></span> </div>
        <div class="input">
            <label>验证码</label>
            <span class="repuired"></span>
            <input type="text" size="10" maxlength="4" style="width: 80px;" class="text" autocomplete="off" id="captcha" name="captcha">
            <div class="code">
                <div class="arrow"></div>
                <div class="code-img">
                    <img border="0" id="codeimage" name="codeimage" src="${S_URL}/validationCodeServlet.png">
                </div>
                <a title="" class="close" id="hide" href="JavaScript:void(0);"><i></i></a>
                <a title="" class="change" onclick="javascript:codeImage();" href="JavaScript:void(0);"><i></i></a> </div>
            <span class="ico"><i class="icon-qrcode"></i></span>
            <input type="submit" value="商家登录" class="login-submit">
        </div>
    </form>
    <div class="copyright">Powered by GoShop2016 &copy; 2015-2016 <a target="_blank" href="#">goshop</a> </div>
</div>

</body></html>
