<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出售中的商品 - $!{config.poweredby}</title>
<meta name="keywords" content="$!config.keywords" />
<meta name="description" content="$!config.description" />
<meta name="generator" content="$!{config.meta_generator}" />
<meta name="author" content="$!{config.meta_author}">
<meta name="copyright" content="$!{config.copyRight}">
#if($!config.website_ico)
<link rel="shortcut icon" href="$!imageWebServer/$!config.website_ico.path/$!config.website_ico.name" type="image/x-icon"/>
#end
<link href="$!webPath/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
<link href="$!webPath/resources/style/system/front/default/css/user.css" type="text/css" rel="stylesheet" />
<script src="$!webPath/resources/js/jquery-1.8.3.min.js"></script>
<script src="$!webPath/resources/js/jquery.shop.common.js"></script>
<script src="$!webPath/resources/js/jquery.validate.min.js"></script>
<script src="$!webPath/resources/js/jquery.poshytip.min.js"></script>
</head>
<body>
$!httpInclude.include("/top.htm")
<div class="main"> 
  $!httpInclude.include("/seller/nav_head.htm") 
  <div class="user_center">
    <table width="1200" border="0" cellspacing="0" cellpadding="0" class="user_table">
      <tr>
        $!httpInclude.include("/seller/nav.htm?op=goods_storage")
        <td id="centerbg" valign="top">
        <div class="buyer_position"><div class="buyer_p_box"><a href="$!webPath/seller/index.htm">卖家中心</a> > <span>仓库中的商品</span></div></div>
		<form action="$!webPath/seller/goods_storage.htm" method="post" name="ListForm" id="ListForm">
		<div class="productmain">
            <div class="pdctitle blue2">仓库中的商品
              <span class="classname">
              <input name="mulitId" type="hidden" id="mulitId" />
              <input name="op" type="hidden" id="op" value="storage" />
              </span>
              
            </div>
            <div class="alldel">
              <div  class="alldel_r"><span class="alldelsel">商品分类
                <select name="user_class_id" id="user_class_id">
                  <option value="">请选择商品分类...</option>
                  #foreach($user_class in $goodsViewTools.query_user_class(""))
                  <option value="$!user_class.id">$!user_class.className</option>
                  #foreach($c_user_class in $user_class.childs)
                  <option value="$!c_user_class.id">&nbsp;&nbsp;$!c_user_class.className</option>
                  #end
                  #end
                </select>
                </span><span class="alldelsear">　商品名称
                <input name="goods_name" type="text" id="goods_name" />
                </span><span class="alldelbtn">
                <input name="" type="button"  value="搜索" style="cursor:pointer;" onclick="query();"//>
              </span></div>
              <div class="alldel_l"> <span class="alldel_la">
                <input name="all" type="checkbox" id="all" value=""  onclick="selectAll(this)"/>
              </span><span class="alldel_lb"><label for="all">全选</label></span> <span  class="alldel_lc"><a class="button_common"  href="javascript:void(0);" onclick="cmd('$!webPath/seller/goods_del.htm','')">删除</a></span> <span class="alldel_ld1"><a class="button_common" href="javascript:void(0);" onclick="cmd('$!webPath/seller/goods_sale.htm','')">上架</a></span> </div>
            </div>
            <div class="operation">
              <table width="990" border="0" cellspacing="0" cellpadding="0" id="opertable" >
                <tr id="opertitle">
                  <td class="proname" width="220">商品名称</td>
                  <td width="217" class="proclassify">商品分类</td>
                  <td width="130" class="promoney">价格</td>
                  <td width="51"  class="prorec">推荐</td>
                  <td width="240" align="center">操作</td>
                </tr>
				#foreach($obj in $objs)
                <tr class="opertr">
                  <td class="proname" valign="middle" ><span class="checkpro">
                    <input type="checkbox" value="$!obj.id" />
					#if($!obj.goods_main_photo)
					  #set($img="$!webPath/$!{obj.goods_main_photo.path}/$!{obj.goods_main_photo.name}")
					#else
					  #set($img="$!webPath/$!{config.goodsImage.path}/$!{config.goodsImage.name}")
					#end
                    </span><span class="imgpro"><img src="$!img" width="60" height="63" /></span><span class="nameproduct"><a href="$!webPath/goods_$!{obj.id}.htm" target="_blank">$!obj.goods_name</a></span></td>
                  <td class="proclassify">#if($!obj.gc)$!storeTools.generic_goods_class_info($!obj.gc)#end</td>
                  <td  class="promoney">$!obj.store_price</td>
                  <td class="prorec"><img src="$!webPath/resources/style/system/front/default/images/usercenter/$!{obj.goods_recommend}.jpg" width="16" height="14" /></td>
                  <td class="operajt"><span class="sale"><a href="$!webPath/seller/goods_sale.htm?mulitId=$!obj.id">上架</a></span><span class="edit"><a href="$!webPath/seller/goods_edit.htm?id=$!obj.id" target="_blank">编辑</a></span><span class="del"><a href="javascript:if(confirm('删除后不可恢复?'))window.location.href='$!webPath/seller/goods_del.htm?mulitId=$!obj.id&op=storage'">删除</a></span></td>
                </tr>
                #end
              </table>
            </div>
            <div class="alldel">
              <div  class="userfenye">$!gotoPageHTML</div>
              <div class="alldel_l"> <span class="alldel_la">
                <input name="all1" type="checkbox" id="all1" value="" onclick="selectAll(this)" />
              </span><span class="alldel_lb"><label for="all">全选</label></span> <span  class="alldel_lc"><a class="button_common" href="javascript:void(0);" onclick="cmd('$!webPath/seller/goods_del.htm','')">删除</a></span> <span class="alldel_ld1"><a href="javascript:void(0);" onclick="cmd('$!webPath/seller/goods_sale.htm','')" class="button_common">上架</a></span> </div>
            </div>
          </div>
		  </form>
		  </td>
      </tr>
    </table>
  </div>
  $!httpInclude.include("/footer.htm")
</div>
</body>
</html>
