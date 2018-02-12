﻿<#assign S_URL=request.contextPath  />
<script>
jQuery(document).ready(function(){
  jQuery(".navul a").each(function(){
    var original_url=jQuery(this).attr("original_url");
	if("$!{current_url}".indexOf(original_url)>=0){
	   jQuery(this).addClass("this");
	}
  });
  jQuery(".left_menu_dl").mouseover(function(){
	    var child_count = jQuery(this).attr("child_count");
		var sc_id=jQuery(this).attr("id");
		var sc_color=jQuery(this).attr("sc_color");
	    var eq =jQuery(this).index();
		if(jQuery(".left_menu_dd[id=child_"+sc_id+"]").html()==null){
	   	  jQuery.ajax({type:'POST',url:'http://b2b2c.iskyshop.com/nav_data.htm',data:{"sc_id":sc_id},success:function(data){
		    if(jQuery(".left_menu_dd[id=child_"+sc_id+"]").html()==null){
      	      jQuery(".left_menu_dt[id=dts_"+sc_id+"]").after(data);
		    }
	        if(child_count>0){
		     jQuery("#dts_"+sc_id).addClass("left_menu_this").removeClass("left_menu_dt");
		     jQuery("#child_"+sc_id).show();
		    }
	    	jQuery("#left_menu_con_"+sc_id).attr("style","border:1px solid "+sc_color+"; border-left:1px solid "+sc_color+";").find(".menu_con_right_top").css("background-color",sc_color);
	        var z = -35;
        	var x = eq*z;
	        jQuery("#left_menu_con_"+sc_id).css('margin-top',x+'px');
		    jQuery(".left_menu_dd[id=child_"+sc_id+"]").show();
	      }});
		}else{
		   if(child_count>0){
		      jQuery("#dts_"+sc_id).addClass("left_menu_this").removeClass("left_menu_dt");
		      jQuery("#child_"+sc_id).show();
		    }
		    jQuery("#left_menu_con_"+sc_id).attr("style","border:1px solid "+sc_color+"; border-left:1px solid "+sc_color+";").find(".menu_con_right_top").css("background-color",sc_color);
	        var z = -35;
    	    var x = eq*z;
	        jQuery("#left_menu_con_"+sc_id).css('margin-top',x+'px');
		    jQuery(".left_menu_dd[id=child_"+sc_id+"]").show();
		}

  }).mouseleave(function(e){
	  jQuery("dt[id^=dts_]").removeAttr("style");
	  jQuery("div[id^=left_menu_con_]").removeAttr("style");
	  var child_count = jQuery(this).attr("child_count");
　　 　jQuery("dt[id^=dts_]").removeClass("left_menu_this").addClass("left_menu_dt");
	  jQuery(".left_menu_dd[id^=child_]").hide();
  });
  jQuery(".nav_left").mouseover(function(){
	  jQuery("#other_menu").show();

   });
  jQuery(".nav_left").mouseleave(function(){
	  jQuery("#other_menu").hide();
   });
});

</script>
<div class="nav">

  <div class="nav_center">
    <div class="nav_left">
      <h2><a href="${S_URL}/goods_class"><span>全部分类<em>
		  <img src="${S_URL}/static/styles/system/front/default/images/nav_left.png"></em></span>
	  </a></h2>
      <div id="other_menu" class="other_menu" style="display:none;">
        <div id="left_menu" class="left_menu">
         <#list gcs! as gc >
            <#assign icon_img="${S_URL}/static/images/common/icon/default_icon.png" />
        	<dl sc_color="#E60012" style="position:relative;"
				id="${(gc.id)!}" child_count="${(gc.childs)!?size}"
				class="left_menu_dl">

	            <dt id="dts_15" class="left_menu_dt">
		            <#if gc.icon_type==0 >
		                <#if ((gc.icon_sys)!'')!=''>
		                	<#assign icon_img="${S_URL}/static/images/common/icon/icon_${(gc.icon_sys)!}.png" />
						</#if>
	                <#else>
		                <#if (gc.icon_acc)!??>
		                	<#assign icon_img="${S_URL}/${(gc.icon_acc.path)!}/${(gc.icon_acc.name)!}" />
						</#if>
					</#if>
		            <i class="left_menu_i">
						<img width="16" height="16" src="${icon_img!}"></i>
		            <strong class="left_menu_str">
		            	<a href="${S_URL}/store_goods_list_${(gc.id)!}">${(gc.className)!}</a>
		            </strong>
	            </dt>
				<dd id="child_${(gc.id)!}" style="display: none;" class="left_menu_dd">
				    <div id="left_menu_con_15" class="left_menu_con this ">
				    <span class="left_menu_sp">
					    <div class="left_menu_con_center">
					        <div class="menu_con_right_top" style="background-color: rgb(230, 0, 18);">
					        <dl>
					          <dt>
					          <#list (gc.childs)! as gc1>
	                    		  <#if ((gc1.display)!'false')=='true' >
						          <span><a href="${S_URL}/store_goods_list_${(gc1.id)!}" target="_blank">
									  ${(gc1.classname)!}
								  </a></span>
								  </#if>
							  </#list>
					          </dt>
					        </dl>
					      </div>

					      <div class="left_menu_con_center_left">
					      	  <#list (gc.childs)! as gc1>
	                    		  <#if ((gc1.display)!'false')=='true' >
							          <dl>
								          <dt><b> &gt; </b>
											  <a href="${S_URL}/store_goods_list_${(gc1.id)!}" target="_blank">
												  ${(gc1.classname)!}</a></dt>
								          <dd>
								          <#list (gc1.childs)! as gc2>
											  <#if ((gc1.display)!'false')=='true' >
					                          <a href="${S_URL}/store_goods_list_${(gc2.id)!}" target="_blank">
												  ${(gc2.classname)!}</a>
											  </#if>
										  </#list>
								          </dd>
							          </dl>
								  </#if>
							  </#list>
					       </div>
					    </div>
				    </span>
				  	</div>
				</dd>
			</dl>
		 </#list>
		</div>
      </div>
    </div>

    <ul id="navul">
    <#assign navs=navTools.queryNav(0,-1) />
    <#list navs as nav >
	    <#if CommUtil.indexOf("${(nav.url)!}","http://") gte 0 >
	      	<#assign url="${(nav.url)!}" />
	    <#else>
			<#assign url="${S_URL}/${(nav.url)!}" />
		</#if>
	    <li><a id="${(nav.id)!}"
			   <#if ((nav.new_win)!0)==1 >target="_blank"</#if>
				original_url="${(nav.original_url)!}" href="${url!}">
			<span>${(nav.title)!}</span>
		</a></li>
	</#list>
    </ul>
  </div>
</div>