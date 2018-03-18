<header class="pngFix" id="header">
    <div class="wrapper">
        <h1 title="电商测试" id="logo"><a href="#"><img class="pngFix" alt="电商测试" src="${S_URL}/static/images/shop/logo.png"></a></h1>
        <nav>
            <ul>
                <li class="frist"><a title="买家首页" href="${S_URL}/buyer/index" <#if (CHANNEL!'')=='MAIN' > class="active" <#else>class="normal"</#if>>买家首页</a></li>
                <li><a title="个人主页" href="#" <#if (CHANNEL!'')=='PERSONAL' > class="active" <#else>class="normal"</#if>>个人主页</a></li>
                <li><a title="好友" href="#" <#if (CHANNEL!'')=='SOCIAL' > class="active" <#else>class="normal"</#if>>好友</a></li>
                <li><a title="站内信" href="#" <#if (CHANNEL!'')=='MAIL' > class="active" <#else>class="normal"</#if>>站内信</a></li>
                <li><a title="设置" href="${S_URL}/member/base_set.html" <#if (CHANNEL!'')=='SETTING' > class="active" <#else>class="normal"</#if>>设置</a></li>
            </ul>
            <div class="search-box">
                <form id="formSearch" action="#" method="get">
                    <input type="hidden" value="search" id="search_act" name="act">
                    <input type="text" maxlength="200" placeholder="" class="text" id="keyword" name="keyword">
                    <input type="submit" class="submit pngFix" value="" name="">
                </form>
            </div>
        </nav>
    </div>
</header>
