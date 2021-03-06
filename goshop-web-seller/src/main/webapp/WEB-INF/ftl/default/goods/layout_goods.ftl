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
                <li <#if (P_CURRENT_OP!"")=="GoodsAdd">class="current"</#if>>
                <a href="${S_URL}/goods/step_one">
                    商品发布
                </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="GoodsForSell">class="current"</#if>>
                <a href="${S_URL}/goods/forsell_list">
                    出售中的商品
                </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="GoodsStorage">class="current"</#if>>
                <a href="${S_URL}/goods/goods_storage">
                    仓库中的商品
                </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="GoodsOut">class="current"</#if>>
                    <a href="${S_URL}/goods/goods_out">
                        违规下架商品
                    </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="GoodsCategory">class="current"</#if>>
                    <a href="${S_URL}/goods_category/goods_user_class_list">
                        商品分类
                    </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="GoodsBrandRequest">class="current"</#if>>
                    <a href="${S_URL}/brand/usergoodsbrand_list">
                        品牌申请
                    </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="storeWarning">class="current"</#if>>
                    <a href="${S_URL}/se/warning/edit">
                        库存警报
                    </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="plate">class="current"</#if>>
                <a href="${S_URL}/se/plate/list">
                    关联板式
                </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="spec">class="current"</#if>>
                <a href="${S_URL}/se/spec/list">
                    商品规格
                </a>
                </li>
                <li <#if (P_CURRENT_OP!"")=="Album">class="current"</#if>>
                <a href="${S_URL}/album/space">
                    图片空间
                </a>
                </li>
            </ul>
        </div>
    </div>
</div>
