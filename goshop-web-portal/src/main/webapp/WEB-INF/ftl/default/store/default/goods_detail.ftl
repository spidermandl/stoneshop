<script>
jQuery(document).ready(function(){
    <#if (obj.goodsProperty)!?? >
    var data='${(obj.goodsProperty)!}';
    var properties=eval("("+data+")");
    jQuery.each(properties,function(index,item){
        jQuery(".goodsintro").append("<span>"+item.name+":"+item.val+"</span>");
    });
    </#if>
});
</script>
<div class="goodsdetails" id="details_tab">
  <div class="goodsintro"></div>
  <div style="margin-top:10px;">${(obj.goodsDetails)!}</div>
</div>
