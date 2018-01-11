<#assign S_URL=request.contextPath  />
<div class="userphoto">图片列表</div>
<script>
  function editor_photo_set(obj){
     var html="<img src='"+jQuery(obj).attr("src")+"' />";
     editor.insertHtml(html);
  }
 </script>
<div class="photo">
  <div id="editor_photo_list">
      <#list photos! as photo>
          <a href="javascript:void(0);" onclick="editor_photo_set(this);" image_id="${(photo.id)!}"
             src="${S_URL}/${(photo.path)!}/${(photo.name)!}">
              <img src="${S_URL}/${(photo.path)!}/${(photo.name)!}_small.${(photo.ext)!}" width="90" height="90" />
          </a>
      </#list>
  </div>
  <div class="tab_page">
    <div class="tab_page2" id="editor_photo" ajax_type=".editor_photo_detail" type="goods_detail_album">${gotoPageAjaxHTML}</div>
  </div>
</div>
