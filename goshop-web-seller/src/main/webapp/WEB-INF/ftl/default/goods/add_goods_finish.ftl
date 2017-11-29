<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="商品发布" />
<#assign P_CURRENT_OP="GoodsAdd" />
<#assign P_STEP=3 />
<@override name="main">
<#if (config.website_ico)?? >
<link rel="shortcut icon" href="${imageWebServer!}/${(config.website_ico.path)!}/${(config.website_ico.name)!}" type="image/x-icon"/>
</#if>

<div class="ncsc-layout wrapper">
  <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
      <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
          <#include "setp.ftl" />

          <div class="main">
              <div class="success">
                  <div class="okimg">
                      <img src="${S_URL}/resources/style/system/front/default/images/usercenter/dui.jpg" width="60" height="66" />
                  </div>
                  <div class="success_mid">
                    <h1>恭喜您！商品发布成功！</h1>
                      <span><a href="${S_URL}/goods_${(obj.id)!}.htm" target="_blank">查看商品详情>> </a></span>
                      <span> <a href="${S_URL}/seller/goods_edit.htm?id=${(obj.id)!}">编辑刚发布的商品>></a></span>
                  </div>
                  <div class="success_right">
                      <h2>您还可以:</h2>
                      <ul>
                        <li> 1. 继续 <a href="${S_URL}/seller/add_goods_first.htm">"发布新商品"</a></li>
                        <li> 2. 进入 <a href="${S_URL}/seller/index.htm">"用户中心"</a> 管理 <a href="${S_URL}/seller/goods.htm">"商品列表"</a></li>
                        <li> 3. 进入 <a href="${S_URL}/seller/ztc_apply.htm">"直通车管理"</a> 选择商品添加申请</li>
                        <li> 4. 参与<a href="${S_URL}/seller/activity.htm">"商城活动"</a></li>
                      </ul>
                  </div>
              </div>
          </div>

        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
