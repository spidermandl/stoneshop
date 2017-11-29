<div class="ncsc-layout-left" id="layoutLeft">
    <div class="sidebar" id="sidebar">
        <div id="main-nav" class="column-title">
            <span class="ico-goods">
            </span>
            <h2>
                物流
            </h2>
        </div>
        <div class="column-menu">
            <ul id="seller_center_left_menu">
                <li <#if P_CURRENT_OP=="TransportList">class="current"</#if>>
                <a href="${S_URL}/transport/transport_list">
                    物流工具
                </a>
                </li>
                <li <#if P_CURRENT_OP=="Album">class="current"</#if>>
                <a href="${S_URL}/album/space">
                    免运费额度
                </a>
                </li>
            </ul>
        </div>
    </div>
</div>
