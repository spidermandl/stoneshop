<#assign S_URL=request.contextPath />
<div class="sidebar">
    <div class="member-handle"><h3>我的购物</h3>
        <ul>
            <li <#if (P_SIDEBAR!'') == "ORDER" >class="active"<#else>class="normal" </#if>><a href="${S_URL}/order">订单管理</a></li>
            <li <#if (P_SIDEBAR!'') == "POINT" >class="active"<#else>class="normal" </#if>><a href="#">积分兑换</a></li>
            <li <#if (P_SIDEBAR!'') == "COUPON" >class="active"<#else>class="normal" </#if>><a href="#base_set.html">我的优惠券</a></li>
        </ul></div>
    <div class="member-handle"><h3>退款维权</h3>
        <ul>
            <li <#if (P_SIDEBAR!'') == "REPORT" >class="active"<#else>class="normal" </#if>><a href="#">举报管理</a></li>
            <li <#if (P_SIDEBAR!'') == "CONSULT" >class="active"<#else>class="normal" </#if>><a href="#">咨询管理</a></li>
            <li <#if (P_SIDEBAR!'') == "COMPLAIN" >class="active"<#else>class="normal" </#if>><a href="#">投诉管理</a></li>
        </ul></div>
    <div class="member-handle"><h3>收藏管理</h3>
        <ul>
            <li class="normal"><a href="#">店铺收藏</a></li>
            <li class="normal"><a href="#">商品收藏</a></li>
        </ul></div>
</div>
