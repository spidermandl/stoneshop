<#assign S_URL=request.contextPath  />
<div class="proevalueation">
 <#if rows==0 >
  <div class="novalue"> 还没有咨询内容 </div>
 <#else>
  <div class="havevaluelist">
    <#list objs! as obj>
    <dl>
      <dt><span class="blue">咨询内容：</span>
          <i>${(obj.consultContent)!}</i>
          <em><#if (obj.consult_user)!??>${(obj.consult_user.userName)!}<#else>匿名</#if></em>
          <b>${(CommUtil.formatLongDate(obj.addtime))!}</b></dt>
      <dd><span class="orange">店家回复：</span>
          <i>${(obj.consultReply)!}</i>
          <em></em>
          <b>${CommUtil.formatLongDate(obj.replyTime)!}</b>
      </dd>
    </dl>
    </#list>
  </div>
  <div class="fenye">
    <div class="fenyes">${gotoPageAjaxHTML!}</div>
  </div>
 </#if>
         <script>
         function refreshCode(){
	        jQuery("#code_img").attr("src","${S_URL}/verify?name=consult_code&d"+new Date().getTime());
         }
		 jQuery(document).ready(function(){
		   jQuery("#theForm").validate({
		       rules:{
				      consult_content:{required:true}<#if ((config.securityCodeConsult)!'false') == 'true' >,
					  consult_code:{
						     required:true,
							 remote:{
							       url: "${S_URL}/verify_code",     //后台处理程序
                                   type: "post",               //数据发送方式
                                   dataType: "json",           //接受数据格式
                                   data: {                     //要传递的数据
                                      "code": function(){return jQuery("#consult_code").val();},
									  "code_name":"consult_code"
		                           }
							 }
						  }
					  </#if>
				   },
			   messages:{
				      consult_content:{required:"咨询内容不能为空"}<#if ((config.securityCodeConsult)!'false') == 'true' >,
				      consult_code:{
						     required:"验证码不能为空",remote:"验证码不正确"
						  }
                        </#if>
				   }
		   });
		 });
       </script>
 <form action="${S_URL}/goods_consult_save" method="post" id="theForm">
  <div class="havevalue">
    <table width="797" border="0" cellspacing="0" cellpadding="0" class="havetable">
      <tr>
        <td width="60" valign="top">我要咨询：</td>
        <td colspan="3" class="havetext">
            <textarea name="consult_content" id="consult_content" cols="45" rows="5"></textarea></td>
      </tr>
      <tr>
        <td>电子邮件：</td>
        <td width="159" class="haveinput">
            <input name="consult_email" type="text" id="consult_email" value="${(user.email)!}" />
        </td>
        <td width="50" align="right">
        <#if ((config.securityCodeConsult)!'false') == 'true' >
        验证码：</#if></td>
        <td width="528" class="havecode">
        <#if ((config.securityCodeConsult)!'false') == 'true' >
        <span class="haveinput">
          <input name="consult_code" type="text" id="consult_code" size="10" style="width:60px;text-transform:uppercase;" />
           <#if ((config.securityCodeType)!)=="voice" >
            <a href="javascript:void(0);"  onclick="readCode('consult')" title="朗读验证码">
                <img src="${imageWebServer!}/static/images/common/speaker.gif" border="0" /></a>
               <span id="consult"></span>
            </#if>
            <span class="login_error_sp" style="display:none"></span>
                   <script>
				   function readCode(id){
                         var  s = "<embed id='sound_play' name='sound_play' src='${S_URL}/static/flash/soundPlayer.swf?" + (new Date().getTime()) + "' FlashVars='url=${S_URL}"+ "' width='0'   height='0' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' /></embed>";
                    jQuery("#"+id).html(s);
                    }
				   </script>
                      </span><span class="codesimg" <#if ((config.securityCodeType)!)=="voice" > style="display:none" </#if>>
            <img style="cursor:pointer;" src="${S_URL}/verify?name=consult_code" id="code_img" onclick="refreshCode();" width="59" height="27" /></span>
          </#if>
          <span class="nonamef">
          <input name="Anonymous" type="checkbox" value="true" id="Anonymous" />
          &nbsp;&nbsp;匿名发表</span><span class="havebtn">
          <input name="" type="submit"  value="发表咨询" style="cursor:pointer;"/>
          <input name="goods_id" type="hidden" id="goods_id" value="${goods_id!}" />
          </span></td>
      </tr>
    </table>
  </div>
  </form>
</div>
