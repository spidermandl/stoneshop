<div class="ncsc-layout-left" id="layoutLeft">
    <div class="sidebar" id="sidebar">
        <div id="main-nav" class="column-title">
            <span class="ico-goods">
            </span>
            <h2>
                <#if ((P_CURRENT_OP)!"")=="StoreAdd" >商家信息提交
                <#else>订单
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
                    <li <#if (P_CURRENT_OP!"")=="OrderSetting">class="current"</#if>>
                        <a href="${S_URL}/order">
                            订单管理
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="PaymentSetting">class="current"</#if>>
                        <a href="${S_URL}/payment">
                            支付方式
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="StartDelivery">class="current"</#if>>
                        <a href="${S_URL}/deliver/deliverQuery">
                            发货
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="DeliverySetting">class="current"</#if>>
                        <a href="${S_URL}/deliver/deliverSetQuery">
                            发货设置
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="EvaluateSetting">class="current"</#if>>
                        <a href="${S_URL}/deliver/evaluateQuery">
                            发货设置
                        </a>
                    </li>
                    <li <#if (P_CURRENT_OP!"")=="PrintSetting">class="current"</#if>>
                        <a href="${S_URL}/printsetup/printsetupQuery">
                            打印设置
                        </a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</div>
