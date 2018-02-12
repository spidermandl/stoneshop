<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="商品发布" />
<#assign P_CURRENT_OP="GoodsAdd" />
<#assign P_STEP=2 />
<@override name="main">
<#--<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/user.css">-->
<#--<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/public.css">-->
<#--<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/overlay.css">-->
<link type="text/css" rel="stylesheet" href="${S_URL}/static/styles/basic.css">
<link type="text/css" rel="stylesheet" href="${S_URL}/static/editor/themes/default/default.css">
<script charset="utf-8" src="${S_URL}/static/editor/kindeditor.js"></script>
<script charset="utf-8" src="${S_URL}/static/editor/lang/zh_CN.js"></script>
<#--<script charset="utf-8" src="${S_URL}/static/scripts/jquery.shop.base.js"></script>-->
<script charset="utf-8" src="${S_URL}/static/scripts/jquery.shop.common.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/swfupload/swfupload.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/swfupload/swfupload.queue.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/swfupload/handlers.js"></script>
<script charset="utf-8" src="${S_URL}/static/scripts/swfupload/fileprogress.js"></script>
<script src="${S_URL}/static/scripts/goods/store_goods_add.step2.js"></script>
<script>
    jQuery.validator.addMethod("transportId",
        function(value, element) {
            if (jQuery(':radio[name=goodsTransfee][value=0]').attr('checked') &&
                    jQuery(':radio[name=transport_type][value=0]').attr('checked')){
                if (jQuery('#transport_id').val() == ''){
                    return false;
                }else{
                    return true;
                }
            }else{
                return true;
            }}
    );
    //options为编辑配置属性
    var options = {
        cssPath : '${S_URL}/static/editor/plugins/code/prettify.css',
        filterMode : true,
        uploadJson:'${S_URL}/upload',
        width : '860px',
        height:'400px',
        resizeType : 1,
        allowImageUpload : true,
        allowFlashUpload : true,
        allowMediaUpload : true,
        allowFileManager : false,
        syncType:"form",
        afterCreate : function() {
            var self = this;
            self.sync();
        },
        afterChange : function() {
            var self = this;
            self.sync();
        },
        afterBlur : function() {
            var self = this;
            self.sync();
        },
        items:['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', '|', 'selectall', 'clearhtml','quickformat','|',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image','flash', 'media',
            'table', 'hr', 'emoticons', 'link', 'unlink', '|', 'about']
    };

    var swf_upload;
    var photo_count=0;
    var user_goods_class_count=2;
    jQuery(document).ready(function() {
        var settings_object = {//定义参数配置对象
            upload_url : "${S_URL}/goods/swf_upload.htm",
            flash_url :
                    "${S_URL}/static/scripts/swfupload/swfupload.swf",
                    <#--"${S_URL}/static/flash/swfupload.swf",-->
            file_post_name : "imgFile",
            post_params : {
                "user_id" : "${(user.id)!}"
            },
            use_query_string : false,
            requeue_on_error : false,
            file_types : "${imageSuffix!}",
            file_types_description: "商品图片",
            file_size_limit : "${(config.imageFilesize)!}",
            file_upload_limit : 0,
            file_queue_limit : 0,
            debug : false,
            prevent_swf_caching : true,
            preserve_relative_urls : false,

            button_placeholder_id : "upload_imgs",
            button_image_url :
                    <#--"${S_URL}/static/images/goods/loader.gif",-->
                    "${S_URL}/static/images/goods/upload.jpg",
            button_width : 160,
            button_height : 28,
            button_text :
//                    '<span class="theFont">Hello</span>',
                    "<b></b><span class='upload_text'>选择上传商品图片</span>",
            button_text_style : ".upload_text { color: #666666;font-size:12px;margin-left:40px; }",
            button_text_left_padding : 3,
            button_text_top_padding : 5,
            button_action : SWFUpload.BUTTON_ACTION.SELECT_FILES,
            button_disabled : false,
            button_cursor : SWFUpload.CURSOR.HAND,
            button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
            file_dialog_start_handler:fileDialogStart,
            file_dialog_complete_handler:fileDialogComplete,
            upload_start_handler:upload_start_handler,
            upload_success_handler:uploadSuccess,
            upload_complete_handler:uploadComplete

        };
        swf_upload = new SWFUpload(settings_object);//实例化一个SWFUpload，传入参数配置对象
        <#if (obj.goods_main_photo)??>
            photo_count=1;
        </#if>
        <#if (obj.goodsPhotos)??>
            photo_count=photo_count+${(obj.goodsPhotos?size)!0};
        </#if>
        function fileDialogStart(){
            var stats = this.getStats();
            stats.successful_uploads=photo_count;
            this.setStats(stats);
        }
        function fileDialogComplete(numFilesSelected, numFilesQueued) {
            try {
                var upload=0;
                var stats = this.getStats();
                var select_count=5-photo_count;
                if (select_count!=0&&numFilesSelected > select_count) {
                    alert("当前最多上传"+select_count+"张商品图片");
                    this.cancelQueue();
                    upload=1;
                }
                if(stats.successful_uploads>=5){
                    alert("已经上传5张图片,如要修改请先删除图片！");
                    this.cancelQueue();
                    upload=1;
                }
                if(upload==0){
                    this.startUpload();
                }
            } catch (ex)  {
                this.debug(ex);
            }
        }
        function upload_start_handler(){
            jQuery("#upload_wait").show();
        }
        function uploadSuccess(file, serverData){//单个图片上传成功
            var obj=eval("("+serverData+")");
            photo_count++;
            var stats = this.getStats();
            if(obj.url!=""){
                if(stats.successful_uploads==1){
                    jQuery("#goods_image_0").attr("src",obj.url);
                }
                jQuery("#goods_image_"+stats.successful_uploads).attr("src",obj.url);
                jQuery("#goods_image_"+stats.successful_uploads).attr("image_id",obj.id);
            }else{
                alert("您的店铺图片空间不足，请选择升级店铺或删除相册图片！");
            }
            if(obj.remainSpace==0){
                jQuery("#img_remain_size").html("空间大小不受限制");
            }else{
                jQuery("#img_remain_size").html(obj.remainSpace+"M");
            }
        }
        function uploadComplete(file) {
            try {
                if (this.getStats().files_queued == 0) {
                    jQuery("#upload_wait").hide();
                } else {
                    if(this.getStats.successful_uploads>=5){
                        this.cancelQueue();
                    }else{
                        this.startUpload();
                    }
                }
            } catch (ex) {
                this.debug(ex);
            }
        }
        //默认赋值
        jQuery(":radio[name='goodsTransfee'][value='${(obj.goodsTransfee)!}']").prop("checked",true);
        jQuery(":radio[name='goodsStatus'][value='${(obj.goodsStatus)!}']").prop("checked",true);
        jQuery(":radio[name='goodsRecommend'][value='${((obj.goodsRecommend)!false)?string("true","false")}']").prop("checked",true);
        jQuery(":radio[name='inventoryType'][value='${(obj.inventoryType)!}']").prop("checked",true);
        jQuery(":radio[name='goodsChoiceType'][value='${(obj.goodsChoiceType)!}']").prop("checked",true);
        jQuery("#goods_brand_id").val("${(obj.goodsBrand.id)!}");
        <#list (obj.goodsUgcs)! as ugc>
            jQuery("#ugc_id_${ugc_index+1}").val("${(ugc.id)!}");
        </#list>
        <#list (obj.goodsSpecs)! as spec>
            jQuery(":checkbox[id=spec_${(spec.id)!}]").prop("checked",true);
        </#list>

        <#if ((obj.inventoryType)!)=="spec">
            var goods_spec_ids="";
            jQuery(":checkbox[id^=spec_]:checked").each(function(){
                goods_spec_ids=jQuery(this).val()+","+goods_spec_ids;
            });

            jQuery.post("${S_URL}/goods/goods_inventory.htm",{"goods_spec_ids":goods_spec_ids},function(data){
                jQuery("#inventory_detail_content").append(data);
                jQuery("#inventory_detail").show();
                var inventory_detail='${(obj.goodsInventoryDetail)!}';
                var goods_inventory_detail=eval("("+inventory_detail+")");
                jQuery.each(goods_inventory_detail, function(index,item){
                    jQuery("#inventory_details_count_"+item.id).val(item.count);
                    jQuery("#inventory_details_price_"+item.id).val(item.price);
                });
            },"text");
        </#if>
        <#if ((obj.goodsProperty)!'')!='' >
            var data='${(obj.goodsProperty)!}';
            var properties=eval("("+data+")");
            jQuery.each(properties,function(index,item){
                jQuery("#property_"+item.id).val(item.val);
            });
        </#if>
        //表单验证
        jQuery("#theForm").validate({
            ignore: "",
            rules: {
                goods_class_id:{
                    required:true
                },
                goods_serial:{
                    maxlength:20
                },
                goods_name:{
                    required:true,
                    minlength:3,
                    maxlength:50
                },
                goods_price:{
                    required:true,
                    number:true,
                    range:[0.01,1000000]
                },
                goods_weight:{
                    number:true
                },
                goods_volume:{
                    number:true
                },
                store_price:{
                    required:true,
                    number:true,
                    range:[0.01,1000000]
                },
                goods_inventory:{
                    number:true,
                    range:[1,1000000000]
                },
                transport_id:{transportId:true}
            },
            messages: {
                goods_class_id:{required:"商品分类不能为空"},
                goods_serial:{maxlength:"输入字符长度不得超过20"},
                goods_name:{
                    required: "商品名不能为空",
                    minlength:"商品名最少为3个字符",
                    maxlength:"商品名最多为50个字符"
                },
                goods_price:{
                    required:"商品原价不能为空",
                    number:"商品原价只能是数字格式",
                    range:"商品价格只能在0.01-1000000之间"
                },
                goods_weight:{
                    number:"只能输入小数及整数"
                },
                goods_volume:{
                    number:"只能输入小数及整数"
                },
                store_price:{
                    required:"店铺价格不能为空",
                    number:"店铺价格只能是数字格式",
                    range:"店铺价格只能在0.01-1000000之间"
                },
                goods_inventory:{
                    number:"商品库存只能为数字",
                    range:"商品库存数量只能在0-1000000000之间"
                },
                transport_id:{transportId:"请选择一个运费模板"}
            }

        });
        //
        jQuery(":input[id^=inventory_details_count_]").on("keyup",null,function(){
            var goods_inventory=0;
            jQuery(":input[id^=inventory_details_count_]").each(function(){
                var reg = new RegExp("^[0-9]*$");
                if(!reg.test(jQuery(this).val())){
                    jQuery(this).val("0");
                }
                goods_inventory=parseInt(jQuery(this).val())+goods_inventory;
            });
            jQuery("#goods_inventory").val(goods_inventory);
        });
        //
        jQuery(":input[id^=inventory_details_price_]").on("keyup",null,function(){
            jQuery(":input[id^=inventory_details_price_]").each(function(){
                //var reg = new RegExp("^[0-9]*$");
                var reg = new RegExp("^\\d+(\\.\\d{2}+)?$");
                if(!reg.test(jQuery(this).val())){
                    jQuery(this).val("0.0");
                }
            });
        });
        //
        jQuery(":input[id='mail_trans_fee']").on("keyup",null,function(){
            jQuery(":input[id='mail_trans_fee']").each(function(){
                var reg = new RegExp("^[0-9]*$");
                if(!reg.test(jQuery(this).val())){
                    jQuery(this).val("0.0");
                }
            });
        });
        //
        jQuery(":input[id='express_trans_fee']").on("keyup",null,function(){
            jQuery(":input[id='express_trans_fee']").each(function(){
                var reg = new RegExp("^[0-9]*$");
                if(!reg.test(jQuery(this).val())){
                    jQuery(this).val("0.0");
                }
            });
        });
        //
        jQuery(":input[id='ems_trans_fee']").on("keyup",null,function(){
            jQuery(":input[id='ems_trans_fee']").each(function(){
                var reg = new RegExp("^[0-9]*$");
                if(!reg.test(jQuery(this).val())){
                    jQuery(this).val("0.0");
                }
            });
        });
        //
        jQuery(":radio[name=inventoryType]").on("click",null,inventory_type);
        jQuery(":checkbox[id^=spec_]").on("click",null,inventory_type);
        //
        editor = KindEditor.create('#goods_details',options);
        //
        jQuery(":radio[name='goodsTransfee']").click(function(){
            var val=jQuery(this).val();
            if(val==0){
                jQuery("#buyer_transport_info").show();
            }else{
                jQuery("#buyer_transport_info").hide();
            }
        });
        //
        jQuery("select[id^=ugc_id_]").on("change",null,function(){
            var the_ugc_id=jQuery(this).attr("id");
            var the_val=jQuery(this).val();
            jQuery("select[id!="+the_ugc_id+"]").each(function(){
                if(jQuery(this).val()==the_val){
                    alert("已经存在该分类");
                    jQuery("#"+the_ugc_id).val("");
                }
            });
        });
    });
    function inventory_type(){
        var rv=jQuery(":radio[name=inventoryType]:checked").val();
        if(rv=="all"){
            jQuery("#inventory_detail_content").empty();
            jQuery("#inventory_detail").hide();
        }else{
            jQuery("#inventory_detail_content").empty();
            var goods_spec_ids="";
            jQuery(":checkbox[id^=spec_]:checked").each(function(){
                goods_spec_ids=jQuery(this).val()+","+goods_spec_ids;
            });

            jQuery.post("${S_URL}/goods/goods_inventory",{"goods_spec_ids":goods_spec_ids},function(data){
                jQuery("#inventory_detail_content").append(data);
                jQuery("#inventory_detail").show();
            },"text");
        }
    }
    function arrow_left(id){
        var i=parseInt(id);
        if(i>1){
            var temp_src=jQuery("#goods_image_"+(i-1)).attr("src");
            var temp_id=jQuery("#goods_image_"+(i-1)).attr("image_id");
            var the_src=jQuery("#goods_image_"+i).attr("src");
            var the_id=jQuery("#goods_image_"+i).attr("image_id");
            if(temp_id!=""&&temp_id!=undefined&&the_id!=""&&the_id!=undefined){
                jQuery("#goods_image_"+(i-1)).attr("src",the_src);
                jQuery("#goods_image_"+(i-1)).attr("image_id",the_id);
                jQuery("#goods_image_"+i).attr("src",temp_src);
                jQuery("#goods_image_"+i).attr("image_id",temp_id);
                if(i==2){
                    jQuery("#goods_image_0").attr("src",the_src);
                    jQuery("#goods_image_0").attr("image_id",the_id);
                }
            }
        }
    }
    function arrow_right(id){
        var i=parseInt(id);
        if(i<5){
            var temp_src=jQuery("#goods_image_"+(i+1)).attr("src");
            var temp_id=jQuery("#goods_image_"+(i+1)).attr("image_id");
            var the_src=jQuery("#goods_image_"+i).attr("src");
            var the_id=jQuery("#goods_image_"+i).attr("image_id");
            if(temp_id!=""&&temp_id!=undefined&&the_id!=""&&the_id!=undefined){
                jQuery("#goods_image_"+(i+1)).attr("src",the_src);
                jQuery("#goods_image_"+(i+1)).attr("image_id",the_id);
                jQuery("#goods_image_"+i).attr("src",temp_src);
                jQuery("#goods_image_"+i).attr("image_id",temp_id);
                if(i==1){
                    jQuery("#goods_image_0").attr("src",temp_src);
                    jQuery("#goods_image_0").attr("image_id",temp_id);
                }
            }
        }
    }
    function arrow_del(id){
        if(confirm("删除后不可恢复，是否继续？")){
            var stats = swf_upload.getStats();
            var image_id=jQuery("#goods_image_"+id).attr("image_id");
            jQuery.post("${S_URL}/goods/goods_image_del.htm",{"image_id":image_id},function(data){
                if(data.result==true){
                    jQuery("#img_remain_size").html(data.remainSpace+"M");
                    jQuery("#goods_image_"+id).attr("src","${S_URL}/static/images/goods/smallimg.jpg");
                    jQuery("#goods_image_"+id).attr("image_id","");
                    if(id==1){
                        jQuery("#goods_image_0").attr("src","${S_URL}/static/images/goods/img.jpg");
                    }
                    stats.successful_uploads--;
                    swf_upload.setStats(stats);
                    photo_count--;
                    var i=parseInt(id);
                    while(i<5){
                        var image_id=jQuery("#goods_image_"+(i+1)).attr("image_id");
                        var image_src=jQuery("#goods_image_"+(i+1)).attr("src");

                        if(image_id!=""){
                            jQuery("#goods_image_"+i).attr("image_id",image_id);
                            jQuery("#goods_image_"+i).attr("src",image_src);
                            if(i==1){
                                jQuery("#goods_image_0").attr("image_id",image_id);
                                jQuery("#goods_image_0").attr("src",image_src);
                            }
                            jQuery("#goods_image_"+(i+1)).attr("image_id","");
                            jQuery("#goods_image_"+(i+1)).attr("src","${S_URL}/static/images/goods/smallimg.jpg");
                        }
                        i++;
                    }
                }else alert("删除失败，请重新删除！");
            },"json");
        }
    }
    function arrow_remove(id){
        var stats = swf_upload.getStats();
        var image_id=jQuery("#goods_image_"+id).attr("image_id");
        jQuery("#goods_image_"+id).attr("src","${S_URL}/static/images/goods/smallimg.jpg");
        jQuery("#goods_image_"+id).attr("image_id","");
        if(id==1){
            jQuery("#goods_image_0").attr("src","${S_URL}/static/images/goods/img.jpg");
        }
        stats.successful_uploads--;
        swf_upload.setStats(stats);
        photo_count--;
        var i=parseInt(id);
        while(i<5){
            var image_id=jQuery("#goods_image_"+(i+1)).attr("image_id");
            var image_src=jQuery("#goods_image_"+(i+1)).attr("src");
            if(image_id!=""){
                jQuery("#goods_image_"+i).attr("image_id",image_id);
                jQuery("#goods_image_"+i).attr("src",image_src);
                if(i==1){
                    jQuery("#goods_image_0").attr("image_id",image_id);
                    jQuery("#goods_image_0").attr("src",image_src);
                }
                jQuery("#goods_image_"+(i+1)).attr("image_id","");
                jQuery("#goods_image_"+(i+1)).attr("src","${S_URL}/static/images/goods/smallimg.jpg");
            }
            i++;
        }

    }
    /*Ajax分页*/
    function ajaxPage(url,currentPage,obj){
        var ajax_page=jQuery(obj).parent().attr("ajax_page");
        if(ajax_page=="goods_transport"){
            jQuery.post("${S_URL}/goods/goods_transport",{"currentPage":currentPage,"ajax":true},function(data){
                jQuery("#ListForm").empty().html(data);
            },"html");
        }else{
            var ajax_type=jQuery(obj).parent().attr("ajax_type");
            var type=jQuery(obj).parent().attr("type");
            jQuery.post("${S_URL}/goods/goods_img_album.htm",{"currentPage":currentPage,"type":type},function(text){
                jQuery(ajax_type).empty();
                jQuery(ajax_type).append(text);
            },"text");
        }
    }
    function saveForm(){
        editor.sync();//同步编辑器的数据到textarea
        var image_ids="";
        for(var i=2;i<=5;i++){
            var image_id=jQuery("#goods_image_"+i).attr("image_id");
            if(image_id!=""&&image_id!=undefined){
                image_ids=image_id+","+image_ids;
            }
        }
        jQuery("#image_ids").val(image_ids);
        var main_image_id=jQuery("#goods_image_1").attr("image_id");
        jQuery("#goods_main_img_id").val(main_image_id);
        var user_class_ids="";
        jQuery("select[id^=ugc_id_]").each(function(){
            if(jQuery(this).val()!=""){
                user_class_ids=jQuery(this).val()+","+user_class_ids;
            }
        });
        jQuery("#user_class_ids").val(user_class_ids);
        var goods_spec_ids="";
        jQuery(":checkbox[id^=spec_]:checked").each(function(){
            goods_spec_ids=jQuery(this).val()+","+goods_spec_ids;
        });
        jQuery("#goods_spec_ids").val(goods_spec_ids);
        var goods_properties="";
        jQuery("select[id^=property_]").each(function(){
            if(jQuery(this).val()!=""){
                goods_properties=jQuery(this).attr("id").substring(9)+","+jQuery(this).val()+";"+goods_properties;
            }
        });
        jQuery("#goods_properties").val(goods_properties);
        var inventory_details="";
        jQuery("input[id^=inventory_details_count_]").each(function(){
            var id=jQuery(this).attr("name");
            var count=jQuery(this).val();
            var price=jQuery("#inventory_details_price_"+id).val();
            inventory_details=id+","+count+","+price+";"+inventory_details;
        });
        jQuery("#inventory_details").val(inventory_details);
        jQuery("#theForm").submit();
    }

</script>

<div class="ncsc-layout wrapper">
    <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <#include "setp.ftl" />
            <#--<div class="wrapper_search">-->
                <div class="main">
                    <form action="${S_URL}/goods/step_three" method="post" enctype="multipart/form-data" name="theForm" id="theForm">
                        <table border="0" cellspacing="0" cellpadding="0" class="tabledetail" >
                            <tr>
                                <td colspan="2" class="tableh1">商品详细信息
                                    <input name="id" type="hidden" id="goods_id" value="${(obj.id)!}" /></td>
                            </tr>
                            <tr>
                                <td width="95" align="right">商品分类：</td>
                                <td><span>${(goods_class_info)!}</span>
                                    <span class="editbtn">
                                    <input name="goods_class_id" type="hidden" id="goods_class_id" value="${(goods_class.id)!}" />
                                    <input name="goods_main_img_id" type="hidden" id="goods_main_img_id" />
                                    <input name="image_ids" type="hidden" id="image_ids" />
                                    <input name="user_class_ids" type="hidden" id="user_class_ids" />
                                    <input name="goods_spec_ids" type="hidden" id="goods_spec_ids" />
                                    <input name="goods_properties" type="hidden" id="goods_properties" />
                                    <input type="hidden" name="inventory_details" id="inventory_details" />
                                    <input name="edit_class" type="button" id="edit_class" value="编辑" style="cursor:pointer;" onclick="window.location.href='${S_URL}/goods/step_one?id=${(obj.id)!}'" />
                                    </span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品名称：</td>
                                <td class="sptable"><span class="tabtxt1 size1">
                                    <input name="goodsName" type="text" id="goods_name" value="${(obj.goodsName)!}" />
                                    </span><span class="hui2">商品标题名称长度至少3个字符，最长50个汉字</span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品原价：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                    <input name="goodsPrice" type="text" id="goods_price" value="${(obj.goodsPrice)!}" />
                                    </span> <span class="hui2">商品原价必须是0.01~1000000之间的数字</span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品类型：</td>
                                <td class="sptable">
                                    <label>
                                        <input name="goodsChoiceType" type="radio" value="0" checked="checked" />
                                        实物商品
                                    </label>
                                    <label>
                                        <input type="radio" name="goodsChoiceType" value="1" />虚拟商品
                                    </label>
                                    <span class="hui2">充值卡、消费券等属于虚拟商品</span>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">店铺价格：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                    <input name="storePrice" type="text" id="store_price" value="${(obj.storePrice)!}" />
                                    </span> <span class="hui2">
                                    <ul class="tableli">
                                      <li>商品原价必须是0.01~1000000之间的数字</li>
                                      <li>若启用了库存配置，请在下方商品库存区域进行管理</li>
                                      <li>商品规格库存表中如有价格差异，店铺价格显示为价格区间形式</li>
                                    </ul>
                                    </span></td>
                            </tr>
                            <#list (goods_class.goodsType.gss)! as gs>
                            <tr id="gs_${gs.id}" gs_name="${(gs.name)!}">
                                <td align="right" valign="top">${(gs.name)!}：</td>
                                <td class="sptable"><ul class="color_chose">
                                    <#list gs.properties as gsp>
                                    <li> <span class="cc_sp1">
                                        <input name="spec_${gsp.id}" type="checkbox" id="spec_${gsp.id}" gs_id="${gs.id}"  gsp_val="${(gsp.value)!}" value="${(gsp.id)!}" />
                                        </span>
                                        <label for="spec_${gsp.id}">
                                            <#if gs.type =='img'>
                                            <span class="cc_sp2">
                                            <img src="${S_URL}/${(gsp.specImage.path)!}/${(gsp.specImage.name)!}" width="16" height="16" />
                                            </span>
                                            </#if>
                                            <span class="cc_sp3">${gsp.value}</span>
                                        </label>
                                    </li>
                                    </#list>
                                </ul></td>
                            </tr>
                            </#list>
                            <tr >
                                <td align="right" valign="top">库存配置：</td>
                                <td class="sptable">
                                    <label>
                                        <input type="radio" name="inventoryType" value="all" checked="checked" />
                                        全局配置
                                    </label>
                                    <label>
                                        <input type="radio" name="inventoryType" value="spec" />
                                        规格配置
                                    </label>
                                    <span class="hui2">
                                    <ul class="tableli">
                                    <li>全局配置表示所有规格无单独库存、价格配置</li>
                                    <li>规格配置需要配置对应属性的库存、价格</li>
                                    </ul>
                                    </span>
                                </td>
                            </tr>
                            <tr id="inventory_detail" style="display:none;">
                                <td align="right" valign="top">详细库存：</td>
                                <td class="sptable"><div id="inventory_detail_content" style="width:98%;height:350px;overflow:auto;"></div></td>
                            </tr>
                            <tr >
                                <td align="right" valign="top">商品库存：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                <input name="goodsInventory" type="text" id="goods_inventory" value="${(obj.goodsInventory)!}" />
                                </span> <span class="hui2">
                                <ul class="tableli">
                                  <li>商铺库存数量必须为1~1000000000之间的整数</li>
                                  <li>若启用了规格配置，则系统自动计算商品的总数，此处无需卖家填写</li>
                                </ul>
                                </span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品货号：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                <input name="goodsSerial" type="text" id="goods_serial" value="${(obj.goodsSerial)!}" />
                                </span> <span class="hui2">
                                <ul class="tableli">
                                  <li>商品货号是指卖家个人管理商品的编号，买家不可见</li>
                                  <li>最多可输入20个字符，支持输入中文、字母、数字、_、/、-和小数点</li>
                                </ul>
                                </span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品图片：</td>
                                <td></td>
                            </tr>
                            <tr>
                                <script>
                                    function photo_switch(clz,obj){
                                        jQuery(".tabbody_ul li").removeClass("this");
                                        jQuery(".tab_body_b").hide();
                                        jQuery(obj).addClass("this");
                                        if(clz=="tab_body_b"){
                                            jQuery.post("${S_URL}/goods/goods_img_album.htm",{"type":"goods_img_album"},function(text){
                                                jQuery(".tab_body_b").empty();
                                                jQuery(".tab_body_b").append(text);
                                            },"text");
                                        }
                                        jQuery("."+clz).show();
                                    }
                                </script>
                                <td colspan="2" align="left" valign="top">
                                    <div class="tab_body">
                                        <ul class="tabbody_ul">
                                            <li class="this" style="cursor:pointer;" onclick="photo_switch('tab_body_a',this)">上传图片</li>
                                            <li style="cursor:pointer;" onclick="photo_switch('tab_body_b',this)">从用户相册选择</li>
                                        </ul>
                                        <div class="tabbodys">
                                            <div class="tab_body_b" style="display:none;"> </div>
                                            <div class="tab_body_a">
                                                <div class="tabimg">
                                                    <#if (obj.goods_main_photo)?? >
                                                        <#assign img="${RES_URL}/${(obj.goods_main_photo.path)!}/${(obj.goods_main_photo.name)!}"/>
                                                    <#else>
                                                        <#assign img="${S_URL}/static/images/goods/img.jpg"/>
                                                    </#if>
                                                    <div class="tabimgbig">
                                                        <img id="goods_image_0" src="${img}" width="197" height="196" />
                                                    </div>
                                                    <div class="tabimgcent">
                                                        <div class="tabupload">
                                                            <a href="javascript:void(0);">
                                                                <span id="upload_imgs"></span>
                                                                <img id="upload_wait" style="display:none;" src="${S_URL}/static/images/goods/loader.gif" />
                                                                <#--<img id="upload_wait" src="${S_URL}/static/images/goods/loader.gif" />-->
                                                            </a>
                                                        </div>
                                                        <div class="tabimgsmall">
                                                            <ul>
                                                                <li class="tabimgs">
                                                                    <img id="goods_image_1" image_id="${(obj.goods_main_photo.id)!}" src="${img!}" width="73" height="73" />
                                                                </li>
                                                                <li class="taboper">
                                                                    <a href="javascript:void(0);"  title="左移" onclick="arrow_left('1');" class="tableft">
                                                                        <img src="${S_URL}/static/images/goods/arrow_left.png" width="16" height="16" />
                                                                    </a>
                                                                    <a href="javascript:void(0);" title="从服务器删除" onclick="arrow_del('1');" class="tabdel">
                                                                        <img src="${S_URL}/static/images/goods/arrow_del.gif" width="15" height="13" />
                                                                    </a>
                                                                    <a href="javascript:void(0);" title="从商品删除" onclick="arrow_remove('1');" class="tabdel">
                                                                        <img src="${S_URL}/static/images/goods/arrow_remove.gif" width="15" height="13" />
                                                                    </a>
                                                                    <a href="javascript:void(0);" title="右移" onclick="arrow_right('1');" class="tabright">
                                                                        <img src="${S_URL}/static/images/goods/arrow_right.png" width="16" height="16" />
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                            <#assign img2="${S_URL}/static/images/goods/smallimg.jpg" />
                                                            <#assign img2_id="" />
                                                            <#assign img3="${S_URL}/static/images/goods/smallimg.jpg" />
                                                            <#assign img3_id="" />
                                                            <#assign img4="${S_URL}/static/images/goods/smallimg.jpg" />
                                                            <#assign img4_id="" />
                                                            <#assign img5="${S_URL}/static/images/goods/smallimg.jpg" />
                                                            <#assign img5_id="" />
                                                            <#list (obj.goodsPhotos)! as img>
                                                                <#if img_index==0 >
                                                                    <#assign img2="${S_URL}/${(img.path)!}/${(img.name)!}" />
                                                                    <#assign img2_id="${(img.id)!}" />
                                                                </#if>
                                                                <#if img_index==1 >
                                                                    <#assign img3="${S_URL}/${(img.path)!}/${(img.name)!}" />
                                                                    <#assign img3_id="${(img.id)!}" />
                                                                </#if>
                                                                <#if img_index==2 >
                                                                    <#assign img4="${S_URL}/${(img.path)!}/${(img.name)!}" />
                                                                    <#assign img4_id="${(img.id)!}" />
                                                                </#if>
                                                                <#if img_index==3>
                                                                    <#assign img5="${S_URL}/${(img.path)!}/${(img.name)!}" />
                                                                    <#assign img5_id="${(img.id)!}" />
                                                                </#if>
                                                            </#list>
                                                            <ul>
                                                                <li class="tabimgs"><img id="goods_image_2" image_id="${img2_id!}" src="${img2!}" width="73" height="73" /></li>
                                                                <li class="taboper"><a href="javascript:void(0);" title="左移" onclick="arrow_left('2');" class="tableft"><img src="${S_URL}/static/images/goods/arrow_left.png" width="16" height="16" /></a><a href="javascript:void(0);" title="从服务器删除" onclick="arrow_del('2');" class="tabdel"><img  src="${S_URL}/static/images/goods/arrow_del.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="从商品删除" onclick="arrow_remove('2');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_remove.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="右移" onclick="arrow_right('2');" class="tabright"><img src="${S_URL}/static/images/goods/arrow_right.png" width="16" height="16" /></a></li>
                                                            </ul>
                                                            <ul>
                                                                <li class="tabimgs"><img id="goods_image_3" image_id="${img3_id!}" src="${img3!}" width="73" height="73" /></li>
                                                                <li class="taboper"><a href="javascript:void(0);" title="左移" onclick="arrow_left('3');" class="tableft"><img src="${S_URL}/static/images/goods/arrow_left.png" width="16" height="16" /></a><a href="javascript:void(0);" title="从服务器删除" onclick="arrow_del('3');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_del.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="从商品删除" onclick="arrow_remove('3');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_remove.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="右移" onclick="arrow_right('3');" class="tabright"><img src="${S_URL}/static/images/goods/arrow_right.png" width="16" height="16" /></a></li>
                                                            </ul>
                                                            <ul>
                                                                <li class="tabimgs"><img id="goods_image_4" image_id="${img4_id!}" src="${img4!}" width="73" height="73" /></li>
                                                                <li class="taboper"><a href="javascript:void(0);" title="左移" onclick="arrow_left('4');" class="tableft"><img src="${S_URL}/static/images/goods/arrow_left.png" width="16" height="16" /></a><a href="javascript:void(0);" title="从服务器删除" onclick="arrow_del('4');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_del.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="从商品删除" onclick="arrow_remove('4');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_remove.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="右移" onclick="arrow_right('4');" class="tabright"><img src="${S_URL}/static/images/goods/arrow_right.png" width="16" height="16" /></a></li>
                                                            </ul>
                                                            <ul>
                                                                <li class="tabimgs"><img id="goods_image_5" image_id="${img5_id!}" src="${img5!}" width="73" height="73" /></li>
                                                                <li class="taboper"><a href="javascript:void(0);" title="左移" onclick="arrow_left('5');" class="tableft"><img src="${S_URL}/static/images/goods/arrow_left.png" width="16" height="16" /></a><a href="javascript:void(0);" title="从服务器删除" onclick="arrow_del('5');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_del.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="从商品删除" onclick="arrow_remove('5');" class="tabdel"><img src="${S_URL}/static/images/goods/arrow_remove.gif" width="15" height="13" /></a><a href="javascript:void(0);" title="右移" onclick="arrow_right('5');" class="tabright"><img src="${S_URL}/static/images/goods/arrow_right.png" width="16" height="16" /></a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="tabshowimg"> <img src="${S_URL}/static/images/goods/thumb.jpg" width="170" height="192" /></div>
                                                </div>
                                                <div class="warning">
                                                    <h2>最多可发布5张商品图片</h2>
                                                    <ul>
                                                        <li>支持多图片同时选取上传。支持${imageSuffix!}格式上传，建议选择尺寸300x300以上、大小2M内的图片。</li>
                                                        <li>用户图空间剩余:<span style="color:#FF0000;" id="img_remain_size"><#if img_remain_size??&&img_remain_size==0 >空间不限制大小<#else> ${img_remain_size!}M</#if></span>，空间不足时将无法上传图片</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品重量：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                    <input name="goodsWeight" type="text" id="goods_weight" value="${(obj.goodsWeight)!}" />
                                    </span> <span class="hui2"> 单位：千克(Kg) </span>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品体积：</td>
                                <td class="sptable"><span class="tabtxt1 size2">
                                    <input name="goodsVolume" type="text" id="goods_volume" value="${(obj.goodsVolume)!}" />
                                    </span> <span class="hui2"> 单位：立方米(m³) </span>
                                </td>
                            </tr>
                            <tr>
                                <td rowspan="2" align="right" valign="middle">运费：</td>
                                <td>
                                    <label><input name="goodsTransfee" type="radio" value="1" checked="checked" />
                                    卖家承担运费</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label><input name="goodsTransfee" type="radio" value="0" />
                                    买家承担运费</label>
                                    <script>
                                        jQuery(document).ready(function(){
                                            jQuery(":radio[name=transport_type]").click(function(){
                                                var val=jQuery(this).val();
                                                if(val==0){
                                                    jQuery("#transport_template_select").show();
                                                    jQuery("#mail_trans_fee").attr("readonly","readonly");
                                                    jQuery("#express_trans_fee").attr("readonly","readonly");
                                                    jQuery("#ems_trans_fee").attr("readonly","readonly");
                                                }else{
                                                    jQuery("#transport_template_select").hide();
                                                    jQuery("#mail_trans_fee").removeAttr("readonly");
                                                    jQuery("#express_trans_fee").removeAttr("readonly");
                                                    jQuery("#ems_trans_fee").removeAttr("readonly");
                                                }
                                            });
			                                <#if (obj.transport)??>
                                            jQuery(":radio[name=transport_type][value=0]").attr("checked","checked");
                                            jQuery("#transport_template_select").show();
			                                <#else>
                                            jQuery(":radio[name=transport_type][value=1]").attr("checked","checked");
                                            jQuery("#transport_template_select").hide();
                                            </#if>
                                            <#if ((obj.goodsTransfee)!1)==0 >
                                            jQuery("#buyer_transport_info").show();
                                            </#if>
                                        });
                                    </script>
                                    <div id="buyer_transport_info" style=" display:none;width:80%; background:#F8F8F8; border:1px solid #CCC; margin-top:10px;padding-left:20px;">
                                        <ul style="line-height:30px;">
                                            <li>
                                                <label>
                                                    <input type="radio" name="transport_type" value="0" checked="checked" />
                                                    使用运费模板
                                                </label>
                                                <#if (trans_count!false)==true>
                                                <div id="transport_template_select" style="padding-left:30px;">
                                                    <input type="hidden" value="${(obj.transport.id)!}" name="transport_id" id="transport_id" />
                                                    <span style="color:#06C;" id="transport_template_name">${(obj.transport.transName)!}</span>
                                                    <a href="javascript:void(0);" dialog_uri="${S_URL}/goods/goods_transport" dialog_title="选择运费模板"
                                                       dialog_width="600" dialog_height="500" dialog_id="transport_template_frm">选择模板</a>
                                                </div>
                                                <#else>
                                                    <div id="transport_template_select" style="padding-left:30px;">
                                                        <a href="${S_URL}/transport/transport_list">创建模板</a>
                                                    </div>
                                                </#if>
                                            </li>
                                            <li><label>
                                                <input type="radio" name="transport_type" value="1" />
                                                固定运费</label>
                                                平邮 <input name="mailTransFee" type="text" id="mail_trans_fee" value="${(CommUtil.null2Float(obj.mail_trans_fee))!}" size="10" />
                                                元,快递
                                                <input name="expressTransFee" type="text" id="express_trans_fee" value="${(CommUtil.null2Float(obj.express_trans_fee))!}" size="10" />
                                                元,EMS
                                                <input name="emsTransFee" type="text" id="ems_trans_fee" value="${(CommUtil.null2Float(obj.ems_trans_fee))!}" size="10" />
                                                元
                                            </li>
                                        </ul>
                                    </div></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="tableh1">商品详情描述</td>
                            </tr>
                            <tr>
                                <td align="right" valign="middle">商品品牌：</td>
                                <td>
                                    <select name="goodsBrandId" id="goods_brand_id">
                                        <option value="">请选择...</option>
                                        <#list gbs! as gb>
                                        <option value="${(gb.id)!}">${(gb.name)!}</option>
                                        </#list>
                                    </select>
                                </td>
                            </tr>
                            <#list (goods_class.goodsType.properties)! as property>
                                <tr>
                                    <td align="right" valign="top">${(property.name)!}：</td>
                                    <td>
                                        <select name="property_${(property.id)!}" id="property_${(property.id)!}">
                                            <option value="">请选择...</option>
                                            <#list CommUtil.splitByChar("${(property.value)!}",",") as info >
                                            <option value="${info!}">${info!}</option>
                                            </#list>
                                        </select>
                                    </td>
                                </tr>
                            </#list>
                            <tr>
                                <td align="right" valign="top">商品描述 ↓ </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <textarea name="goodsDetails" style="width:100%;height:400px;visibility:hidden;" id="goods_details">${(obj.goodsDetails)!}</textarea>
                                    <script>
                                        function switch_editor_photo(){
                                            var dis=jQuery(".editor_photo_detail").css("display");
                                            if(dis=="none"){
                                                jQuery.post("${S_URL}/goods/goods_img_album.htm",
                                                        {"type":"goods_detail_album"},function(text){
                                                    jQuery(".editor_photo_detail").empty();
                                                    jQuery(".editor_photo_detail").append(text);
                                                },"text");
                                                jQuery(".editor_photo_detail").show();
                                            }else{
                                                jQuery(".editor_photo_detail").hide();
                                            }
                                        }
                                    </script>
                                    <div class="photoimg">
                                        <div class="addphoto">
                                            <a href="javascript:void(0);" onclick="switch_editor_photo();">插入相册图片</a></div>
                                        <div class="editor_photo_detail" style="display:none;"> </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="tableh1">其他信息</td>
                            </tr>
                            <script>
                                function add_goods_class(){
                                    var s="<select name='ugc_id_0' id='ugc_id_0'><option value=''>请选择...</option><#list ugcs! as ugc><option value='${(ugc.id)!}'>${(ugc.classname)!}</option> <#list (ugc.childs)! as c_ugc> <option value='${(c_ugc.id)!}'>&nbsp;&nbsp;${(c_ugc.classname)!}</option></#list> </#list> </select>";
                                    s=s.replaceAll("ugc_id_0","ugc_id_"+user_goods_class_count);
                                    jQuery("#ugc_id_"+(user_goods_class_count-1)).after(s);
                                    user_goods_class_count++;
                                }
                            </script>
                            <tr>
                                <td align="right" valign="top">本店分类：</td>
                                <td><span> <span id="ugc_info">
                                    <#if ((obj.goodsUgcs?size)!0)==0>
                                        <select name="ugc_id_1" id="ugc_id_1">
                                          <option value="">请选择...</option>
                                          <#list ugcs! as ugc >
                                          <option value="${ugc.id}">${(ugc.classname)!}</option>
                                              <#list (ugc.childs)! as c_ugc>
                                              <option value="${c_ugc.id}">&nbsp;&nbsp;${c_ugc.classname}</option>
                                              </#list>
                                          </#list>
                                        </select>
                                    <#else>
                                        <#list (obj.goodsUgcs)! as goods_ugc >
                                            <select name="ugc_id_${goods_ugc_index+1}" id="ugc_id_${goods_ugc_index+1}">
                                              <option value="">请选择本店分类...</option>
                                              <#list ugcs as ugc>
                                                  <option value="${ugc.id}">${ugc.classname}</option>
                                                  <#list ugc.childs as c_ugc>
                                                      <option value="${c_ugc.id}">&nbsp;&nbsp;${c_ugc.classname}</option>
                                                  </#list>
                                              </#list>
                                            </select>
                                        </#list>
                                    </#if>
                                </span>
                                </span><a class="button_common" href="javascript:void(0);" onclick="add_goods_class();">新增分类</a> <span class="hui2 tableli">
                                <ul>
                                  <li>商品可以从属于店铺的多个分类之下,</li>
                                  <li>店铺分类可以由 "卖家中心 ->  商品管理 -> <a href="${S_URL}/goods_category/goods_user_class_list">商品分类</a>" 中自定义 </li>
                                </ul>
                                </span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">商品发布：</td>
                                <td class="prorelease"><span>
                                <label>
                                <input name="goodsStatus" type="radio" value="0" checked="checked" />
                                &nbsp;立即发布
                                </label>
                                </span> <span>
                                <label>
                                <input name="goodsStatus" type="radio" value="1" />
                                &nbsp;放入仓库
                                </label>
                                </span></td>
                            </tr>
                            <tr>
                                <td valign="top" align="right">商品推荐：</td>
                                <td class="prorelease"><span><strong>
                                <label>
                                <input name="goodsRecommend" type="radio" value="true" checked="checked" />
                                &nbsp;是
                                </label>
                                </strong><strong>
                                <label>
                                <input name="goodsRecommend" type="radio" value="false" />
                                &nbsp;否
                                </label>
                              </strong></span> <span class="hui2">被推荐的商品会显示在店铺首页</span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">SEO关键字：</td>
                                <td class="sptable"><span class="tabtxt1 size1">
                                <input name="seoKeywords" type="text" id="seo_keywords" value="${(obj.seoKeywords)!}" />
                                </span> <span class="hui2 noteswidth">SEO关键字 (keywords) 出现在商品详细页面头部的 Meta 标签中，
                                用于记录本页面商品的关键字，多个关键字间请用半角逗号 "," 隔开</span></td>
                            </tr>
                            <tr>
                                <td align="right" valign="top">SEO描述：</td>
                                <td class="sptable"><span class="texttable">
                                <textarea name="seoDescription" cols="" rows="" id="seo_description">${(obj.seoDescription)!}</textarea>
                                </span> <span class="hui2 noteswidth">SEO关键字 (keywords) 出现在商品详细页面头部的 Meta 标签中，
                                用于记录本页面商品的关键字，多个关键字间请用半角逗号 "," 隔开</span></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td><span class="submittable">
                                    <input class="btn" name="" type="button" value="提交" onclick="saveForm();" style="cursor:pointer;"/>
                                    </span>
                                    <input name="goods_session" type="hidden" id="goods_session" value="${goods_session!}" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            <#--</div>-->

        </div>
    </div>
</div>
</@override>

<@extends name="../framework.ftl"/>
