<#assign S_URL=request.contextPath  />
<link href="${S_URL}/static/styles/window.css" type="text/css" rel="stylesheet" />
<script>
jQuery(document).ready(function(){
  //
 jQuery("#theForm").validate({
	ignore: "",
    rules:{
	    classname:{required :true},
        sequence:{
            required:true,
            number:true,
            range:[0,1000000]
        },
    },
	messages:{
	    classname:{required :"分类不能为空"},
        sequence:{required :"序号不能为空"}
	}
  });
 //
 jQuery("#pid").val("${(obj.parent.id)!}");//设置上级名称
 jQuery(":radio[name=display][value=${((obj.display)!false)?string("true","false")}]").prop("checked",true);
});
</script>
<form action="${S_URL}/goods_category/goods_user_class_save" method="post" id="theForm">
  <table style="float:left;" width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table">
    <tr>
      <td width="120" align="right" class="hui_table"><input name="id" type="hidden" id="id" value="${(obj.id)!}" />
        分类名称:</td>
      <td align="left"><span class="dia_txt">
        <input name="classname" type="text" id="classname" value="${(obj.classname)!}" />
        </span></td>
    </tr>
    <tr>
      <td width="120" align="right" class="hui_table">上级分类:</td>
      <td align="left"><select name="pid" id="pid">
          <option value="">请选择上级分类</option>
          <#list ugcs! as ugc >
          <option value="${(ugc.id)!}">${(ugc.classname)!}</option>
          </#list>
        </select></td>
    </tr>
    <tr>
      <td width="120" align="right" class="hui_table">分类排序:</td>
      <td align="left"><span class="dia_txt">
        <input name="sequence" type="text" id="sequence" value="${(obj.sequence)!}" />
        </span></td>
    </tr>
    <tr>
      <td width="120" align="right" class="hui_table">显示状态:</td>
      <td align="left">
          <input type="radio" name="display" value="true" checked="checked" />
            &nbsp;是 &nbsp;
          <input type="radio" name="display" value="false" />
            &nbsp;否</td>
    </tr>
    <tr>
      <td colspan="2" align="center"><span class="inputbtn">
        <input name="" type="submit" value="提交" style="cursor:pointer;"/>
        <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />
        </span></td>
    </tr>
  </table>
</form>
