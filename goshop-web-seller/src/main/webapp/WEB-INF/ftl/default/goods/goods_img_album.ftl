<#assign S_URL=request.contextPath  />
<h1> <i>图片列表</i></h1>
<script>
function goods_photo_set(obj){
  var src=jQuery(obj).attr("src");
  var image_id=jQuery(obj).attr("image_id");
	if(photo_count==0){
		 jQuery("#goods_image_0").attr("src",src).attr("image_id",image_id);
	}
  jQuery("#goods_image_"+(photo_count+1)).attr("src",src).attr("image_id",image_id);
  photo_count++;
  photo_count=photo_count>=5?5:photo_count;
}
</script>
<ul class="tabphoto">
  <#list photos! as photo>
  <li>
      <a href="javascript:void(0);" onclick="goods_photo_set(this);" image_id="${(photo.id)!}"
         src="${S_URL}/${(photo.path)!}/${(photo.name)!}">
        <img src="${S_URL}/${(photo.path)!}/${(photo.name)!}_small.${(photo.ext)!}" width="90" height="90" />
      </a>
  </li>
  </#list>
</ul>
<div class="tab_page">
  <div class="tab_page2" id="img_photo" ajax_type=".tab_body_b" type="goods_img_album">${gotoPageAjaxHTML!}</div>
</div>
