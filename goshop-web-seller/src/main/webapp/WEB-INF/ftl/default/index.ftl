<#assign S_URL=request.contextPath />
<html>
<head>
    <title></title>
</head>
<body>
电商首页

<@shiro.guest>
欢迎游客访问，<a href="${S_URL}/login">登录</a> <a href="${SHOP_URL}/register.html">注册</a>
</@shiro.guest>

<@shiro.user>
欢迎[<@shiro.principal property="loginName"/>]登录，<a href="${S_URL}/logout">退出</a>
</@shiro.user>
</body>
</html>
