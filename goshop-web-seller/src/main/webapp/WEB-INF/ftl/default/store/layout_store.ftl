<div class="ncsc-layout-left" id="layoutLeft">
    <div class="sidebar" id="sidebar">
        <div id="main-nav" class="column-title">
            <span class="ico-goods">
            </span>
            <h2>
                <#if ((P_CURRENT_OP)!"")=="StoreAdd" >商家信息提交
                <#else>店铺
                </#if>
            </h2>
        </div>

        <div class="column-menu">
            <ul id="seller_center_left_menu">
                <#if P_CURRENT_OP=="StoreAdd" >
                    <li <#if P_STEP==1>class="current"</#if>>
                    <a href="#">
                        公司信息
                    </a>
                    </li>
                    <li <#if P_STEP==2>class="current"</#if>>
                    <a href="#">
                        财务信息
                    </a>
                    </li>
                    <li <#if P_STEP==3>class="current"</#if>>
                    <a href="#">
                        店铺信息
                    </a>
                    </li>
                    <li <#if P_STEP==4>class="current"</#if>>
                    <a href="#">
                        申请状态
                    </a>
                <#else>
                    <li <#if (P_CURRENT_OP!"")=="StoreInfo">class="current"</#if>>
                        <a href="${SHOP_URL}/store_me">
                            我的店铺
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreSetting">class="current"</#if>>
                        <a href="${S_URL}/store/store_set">
                            店铺设置
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreNavigation">class="current"</#if>>
                        <a href="${S_URL}/store/store_nav">
                            店铺导航
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreClassify">class="current"</#if>>
                        <a href="${S_URL}/store/aaa">
                            店铺分类
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreSubAccount">class="current"</#if>>
                        <a href="${S_URL}/store/sub_account_list">
                            子账户管理
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreTheme">class="current"</#if>>
                        <a href="${S_URL}/store/store_theme">
                            主题设置
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="GoodsBrandRequest">class="current"</#if>>
                        <a href="${S_URL}/brand/usergoodsbrand_list">
                            品牌申请
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StorePartner">class="current"</#if>>
                        <a href="${S_URL}/store/store_partner">
                            友情链接
                        </a>
                    </li>

                </#if>
            </ul>
        </div>
    </div>
</div>
