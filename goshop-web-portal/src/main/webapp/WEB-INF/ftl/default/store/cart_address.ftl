<#assign S_URL=request.contextPath />

<link href="${S_URL}/static/styles/window.css" type="text/css" rel="stylesheet" />
<script>
jQuery.validator.addMethod("mobile_telephone",function(value,element){
  if(jQuery("#telephone").val()==""&&jQuery("#mobile").val()==""){
     return false;

  }else return true;
});
jQuery(document).ready(function(){
  //
 jQuery("#theForm").validate({
	ignore: "",
    rules:{
	  truename:{required :true},
	  area_id:{required :true},
	  areaInfo:{required :true},
	  telephone:{mobile_telephone :true}
	 },
	messages:{
	  truename:{required :"收货人不能为空"},
	  area_id:{required :"请选择详细区域"},
	  areaInfo:{required :"详细地址不能为空"},
	  telephone:{mobile_telephone :"联系电话、手机至少填写一项"}
	}
  });
    //
 jQuery("select").change(function(){
     var level=jQuery(this).attr("level");
	 var id=jQuery(this).val();
	 if(id!=""){
	  jQuery.post("${S_URL}/load_area",{"pid":id},function(data){
	     jQuery("#area"+level).empty();
		  jQuery("#area"+level).append("<option value=''>请选择</option>");
	    jQuery.each(data, function(index,item){
		  jQuery("#area"+level).append("<option value='"+item.id+"'>"+item.areaname+"</option>");
		  jQuery("#area"+level).show();
		});
	  },"json");
	 }else{
	   for(var i=level;i<=3;i++){
	    jQuery("#area"+i).empty();
	    jQuery("#area"+i).hide();
	   }
	 }
  });
  //
 jQuery("#area3").change(function(){
   var id=jQuery(this).val();
   jQuery("#area_id").val(id);
 });
});
</script>
<form action="${S_URL}/cart_address_save" method="post" id="theForm">
  <table style="float:left;" width="460" border="0" cellspacing="0" cellpadding="0" class="box_table">
    <tr>
      <td width="100" align="right" valign="top"><span class="hui_table">收货人姓名</span>：
        <input name="id" type="hidden" id="id" value="${(obj.id)!}" />
      </td>
      <td align="left"><span class="dia_txt">
        <input name="truename" type="text" id="truename" size="35" />
      </span></td>
    </tr>
    <tr>
      <td align="right" valign="top"><span class="hui_table">所在区域：</span></td>
      <td align="left">
      <select name="area1" id="area1" level="2" style="width:100px;">
          <option value="" selected="selected">请选择地区</option>

      <#list areas as area >
          <option value="${(area.id)!}">${(area.areaname)!}</option>
      </#list>
      </select>
          <input type="button" name="area_edit" id="area_edit" value="修改" style="display:none;"
                 onclick="javascript:jQuery('#area1').show();jQuery('#areaInfo').empty();jQuery(this).hide();" />
          <select name="area2" id="area2" style="display:none;width:60px;" level="3">
          </select>
          <select name="area3" id="area3" style="display:none;width:60px;" level="4">
          </select>
          <input name="area_id" type="hidden" id="area_id"  value=""/>
      </td>
    </tr>
    <tr>
      <td align="right" valign="top">
          <span class="hui_table">详细地址</span>：</td>
      <td align="left">
          <span class="dia_txt">
              <input name="areaInfo" type="text" id="areaInfo" size="35" />
          </span>
      </td>
    </tr>
    <tr>
      <td align="right" valign="top"><span class="hui_table">邮政编码</span>：</td>
      <td align="left"><span class="dia_txt">
        <input name="zip" type="text" id="zip" size="35" />
      </span></td>
    </tr>
    <tr>
      <td align="right" valign="top"><span class="hui_table">联系电话</span>：</td>
      <td align="left" id="other_reason2"><span class="dia_txt">
        <input name="telephone" type="text" id="telephone" size="35" />
      </span></td>
    </tr>
    <tr>
      <td align="right" valign="top"><span class="hui_table">手机号码</span>：</td>
      <td align="left"><span class="dia_txt">
        <input name="mobile" type="text" id="mobile" size="35" />
      </span></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><span class="inputbtn">
        <input name="" type="submit" value="提交" style="cursor:pointer;"/>
        <input name="currentPage" type="hidden" id="currentPage" value="${currentPage!}" />
        <input name="store_id" type="hidden" id="store_id" value="${store_id!}" />
      </span></td>
    </tr>
  </table>
</form>
