<div class="evaluation">
  <div class="eva_bar_box">
    <div class="eva_bar">
      <div class="eva_bar_left">
        <h4>与描述相符</h4>
        <strong>${(CommUtil.null2Double(goods.descriptionEvaluate))!'0.0'}</strong> </div>
      <#assign long = (CommUtil.null2Double(goods.descriptionEvaluate)/5*100)!0.0 />
      <div class="eva_bar_right">
        <div class="eva_scroller">
            <span style="width:${long!}%;">
            <em>${(CommUtil.null2Double(goods.descriptionEvaluate))!'0.0'}</em>
            </span>
        </div>
        <ul class="scroller_ul">
          <li>非常不满意</li>
          <li>不满意</li>
          <li>一般</li>
          <li>满意</li>
          <li>非常满意</li>
        </ul>
      </div>
    </div>
  </div>
  <#if rows==0 >
  <div class="novalue"> 还没有评价内容 </div>
  <#else>
  <#list objs as obj >
  <div class="evalone">
    <ul>
      <li><span class="blue evalonesp">${(obj.evaluate_user.userName)!}</span>${(obj.evaluateInfo)!}</li>
      <li>${(CommUtil.formatShortDate(obj.addtime))!}</li>
    </ul>
  </div>
  </#list>
  <div class="fenye">
    <div class="fenyes">${gotoPageAjaxHTML}</div>
  </div>
  </#if> </div>
