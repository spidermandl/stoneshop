<div class="ncsc-layout-left" id="layoutLeft">
    <div class="sidebar" id="sidebar">
        <div id="main-nav" class="column-title">
            <span class="ico-goods">
            </span>
            <h2>
                商品
            </h2>
        </div>
        <div class="column-menu">
            <ul id="seller_center_left_menu">
                <li <#if P_CURRENT_OP=="GoodsAdd">class="current"</#if>>
                <a href="${S_URL}/goods_add/step_one">
                    商品发布
                </a>
                </li>
                <li <#if P_CURRENT_OP=="Online">class="current"</#if>>
                <a href="${S_URL}/se/goodsonline/list">
                    出售中的商品
                </a>
                </li>
                <li <#if P_CURRENT_OP=="Offline">class="current"</#if>>
                <a href="${S_URL}/se/goodsonline/list?goodsState=0">
                    仓库中的商品
                </a>
                </li>
                <li <#if P_CURRENT_OP=="storeWarning">class="current"</#if>>
                <a href="${S_URL}/se/warning/edit">
                    库存警报
                </a>
                </li>
                <li <#if P_CURRENT_OP=="plate">class="current"</#if>>
                <a href="${S_URL}/se/plate/list">
                    关联板式
                </a>
                </li>
                <li <#if P_CURRENT_OP=="spec">class="current"</#if>>
                <a href="${S_URL}/se/spec/list">
                    商品规格
                </a>
                </li>
                <li <#if P_CURRENT_OP=="Album">class="current"</#if>>
                <a href="${S_URL}/album/space">
                    图片空间
                </a>
                </li>
            </ul>
        </div>
    </div>
</div>