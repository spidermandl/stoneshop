<div class="ncsc-layout-left" id="layoutLeft">
    <div class="sidebar" id="sidebar">
        <div id="main-nav" class="column-title">
            <span class="ico-goods">
            </span>
            <h2>
                <#if P_CURRENT_OP=="StoreAdd" >商家信息提交
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
                    <li <#if (P_CURRENT_OP!"")=="StoreSetting">class="current"</#if>>
                        <a href="${S_URL}/store/store_set">
                            店铺设置
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreNav">class="current"</#if>>
                        <a href="${S_URL}/store/">
                            店铺导航
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreInfo">class="current"</#if>>
                        <a href="${S_URL}/store/">
                            店铺信息
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreClassify">class="current"</#if>>
                        <a href="${S_URL}/store/">
                            店铺分类
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StoreBrand">class="current"</#if>>
                        <a href="${S_URL}/store/">
                            品牌申请
                        </a>
                    </li>

                </#if>
            </ul>
        </div>
    </div>
</div>
