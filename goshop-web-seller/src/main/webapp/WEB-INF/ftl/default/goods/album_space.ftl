<#assign P_CURRENT_TOP='goods' />
<#assign P_NAV1="商家管理中心" />
<#assign P_NAV2="商品" />
<#assign P_NAV3="图片空间" />
<#assign P_CURRENT_OP="Album" />
<#assign P_STEP=1 />
<@override name="main">
<script src="${S_URL}/static/scripts/goods/store_goods_add.step1.js"></script>
<div class="ncsc-layout wrapper">
    <#include "layout_goods.ftl"/>
    <div class="ncsc-layout-right" id="layoutRight">
        <#include "../nav.ftl"/>
        <div id="mainContent" class="main-content">
            <div id="mainContent" class="main-content">
                <div class="tabmenu">
                    <ul class="tab pngFix">
                        <li class="active">
                            <a href="${S_URL}/se/album/cate">
                                我的相册
                            </a>
                        </li>
                    </ul>
                    <a style="right: 100px;" class="ncsc-btn ncsc-btn-green" dialog_title="创建相册"
                       nc_type="dialog" uri="${S_URL}/album/add_page">
                        <i class="icon-folder-open ">
                        </i>
                        创建相册
                    </a>
                    <a class="ncsc-btn ncsc-btn-acidblue" href="JavaScript:void(0);" id="open_uploader">
                        <i class="icon-cloud-upload">
                        </i>
                        上传图片
                    </a>
                    <div style="display: none;" id="uploader" class="upload-con">
                        <form enctype="multipart/form-data" id="fileupload" action="" method="post">
                            <div class="upload-con-div">
                                选择相册：
                                <select class="select w80" id="category_id" name="category_id">
                                    <#list P_PAGE.list as album>
                                        <option class="w80" value="${album.aclassId}">${album.aclassName}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="upload-con-div">
                                选择文件：
                                <div class="ncsc-upload-btn">
                                    <a href="javascript:void(0);">
              <span>
                <input type="file" multiple="multiple" name="file" class="input-file"
                       size="1" hidefocus="true">
              </span>
                                        <p>
                                            <i class="icon-upload-alt">
                                            </i>
                                            上传图片
                                        </p>
                                    </a>
                                </div>
                            </div>
                            <div nctype="file_msg">
                            </div>
                            <div nctype="file_loading" class="upload-pmgressbar">
                            </div>
                            <div class="upload-txt">
          <span>
            支持Jpg、Gif、Png格式，大小不超过1024KB的图片上传；浏览文件时可以按住ctrl或shift键多选。
          </span>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="ncsc-picture-folder" id="pictureIndex">
                    <table class="search-form">
                        <tbody>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                            <th>
                                排序方式
                            </th>
                            <td class="w100">
                                <form class="sortord" id="select_sort" name="select_sort">
                                    <input type="hidden" value="store_album" name="act">
                                    <input type="hidden" value="album_cate" name="op">
                                    <select id="img_sort" name="sort">
                                        <option value="">请选择</option>
                                        <option <#if RequestParameters['sort']??&&RequestParameters['sort']=='4'>selected="selected"</#if> value="4">
                                            按排序从大到小
                                        </option>
                                        <option <#if RequestParameters['sort']??&&RequestParameters['sort']=='5'>selected="selected"</#if> value="5">
                                            按排序从小到大
                                        </option>
                                        <option <#if RequestParameters['sort']??&&RequestParameters['sort']=='0'>selected="selected"</#if> value="0">
                                            按创建时间从晚到早
                                        </option>
                                        <option <#if RequestParameters['sort']??&&RequestParameters['sort']=='1'>selected="selected"</#if> value="1">
                                            按创建时间从早到晚
                                        </option>
                                        <option <#if RequestParameters['sort']??&&RequestParameters['sort']=='2'>selected="selected"</#if> value="2">
                                            按相册名降序
                                        </option>
                                        <option <#if RequestParameters['sort']??&&RequestParameters['sort']=='3'>selected="selected"</#if> value="3">
                                            按相册名升序
                                        </option>
                                    </select>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="ncsc-album">
                        <ul>
    <#if (P_PAGE.size>0) >
                    <#list P_PAGE.list as album>
                        <li class="hidden" style="opacity: 1;">
                            <dl>
                                <dt>
                                <div class="covers">
                                    <a href="${S_URL}/se/albumpic/piclist?category_id=${album.aclassId}">

                                        <#if album.aclassCover??&&album.aclassCover!="">
                                            <img id="aclass_cover" src="${S_URL}/att?path=${album.aclassCover}">
                                        <#else>
                                            <i class="icon-camera-retro"></i>
                                        </#if>
                                    </a>
                                </div>
                                <h3 class="title">
                                    <a href="2">
                                    ${album.aclassName}
                                    </a>
                                </h3>
                                </dt>
                                <dd class="date">
                                    共${album.picNumber}张
                                </dd>
                                <dd class="buttons">
              <span uri="${S_URL}/album/update_page?id=${album.aclassId}" dialog_width="480"
                    dialog_id="album_1" dialog_title="编辑相册" nc_type="dialog">
                <a href="JavaScript:void(0);">
                    <i class="icon-pencil">
                    </i>
                    编辑
                </a>
              </span>
                                    <#if album.isDefault!=1>
                                        <a onclick="ajax_get_confirm('删除相册?\n注意：相册内的图片将转移到默认相册', 'album/del_class?id=${album.aclassId}');" href="javascript:void(0)"><i class="icon-remove-sign"></i>删除</a>
                                    </#if>
                                </dd>
                            </dl>
                        </li>
                    </#list>
        <#if (P_PAGE.pages>1)>
            <#import "../pagination.ftl" as pagination />
            <@pagination.page  pageInfo=P_PAGE pageNumName="p" />
        </#if>
    <#else>
         <li class="hidden" style="opacity: 1;">
            <dl>
                <dt>
                <div class="covers">
                    <a href="${S_URL}/albumpic/piclist?category_id=">
                        <i class="icon-camera-retro"></i>
                    </a>
                </div>
                <h3 class="title">
                    <a href="2">
                        默认相册
                    </a>
                </h3>
                </dt>
                <dd class="date">
                    共0张
                </dd>
                <dd class="buttons">
              <span uri="${S_URL}/album/update_page?id=" dialog_width="480"
                    dialog_id="album_1" dialog_title="编辑相册" nc_type="dialog">
                <a href="JavaScript:void(0);">
                    <i class="icon-pencil">
                    </i>
                    编辑
                </a>
              </span>
                </dd>
            </dl>
        </li>
    </#if>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </div>


</div>
<script src="${S_URL}/static/scripts/fileupload/jquery.iframe-transport.js"></script>
<script src="${S_URL}/static/scripts/fileupload/jquery.ui.widget.js"></script>
<script src="${S_URL}/static/scripts/fileupload/jquery.fileupload.js"></script>
<script src="${S_URL}/static/scripts/goods/album.js"></script>
<script src="${S_URL}/static/scripts/member.js"></script>
</@override>

<@extends name="../framework.ftl"/>
