<!--平邮 开始-->
<div class="db_box_main">
  <div class="db_box_main_input">
    <label>
      <input name="trans_mail" type="checkbox" id="trans_mail" value="true" <#if ((obj.transMail)!false) == true > checked="checked"</#if> />
      平邮 </label>
  </div>
  <div class="db_box_main_rdinary" id="trans_mail_info"<#if ((obj.transMail)!false) == false > style="display:none;" </#if>>
    <div class="rdinary_top">默认运费：
      <input name="mail_trans_weight" type="text" id="mail_trans_weight" value='${(transportTools.query_transprot(obj.transMailInfo,"trans_weight"))!}' size="5" />
      m³内，
      <input name="mail_trans_fee" type="text" id="mail_trans_fee" value='${(transportTools.query_transprot(obj.transMailInfo,"trans_fee"))!}' size="8" />
      元， 每增加
      <input name="mail_trans_add_weight" type="text" id="mail_trans_add_weight" value='${(transportTools.query_transprot(obj.transMailInfo,"trans_add_weight"))!}' size="5" />
      m³，增加运费
      <input name="mail_trans_add_fee" type="text" id="mail_trans_add_fee" value='${(transportTools.query_transprot(obj.transMailInfo,"trans_add_fee"))!}' size="8" />
      元</div>
    <#assign mail_trans_list = (transportTools.query_all_transprot((obj.transMailInfo),1))!/>
    <div class="rdinary_ul" <#if (mail_trans_list?size)==0 >style="display:none;"</#if> id="mail_trans_city_info">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr bgcolor="#f5f5f5">
          <td width="46%" align="center"><span class="width1">运送到</span></td>
          <td width="11%"><span class="width2">首体积(m³)</span></td>
          <td width="13%"><span class="width2">首费(元)</span></td>
          <td width="11%"><span class="width2">续体积(m³)</span></td>
          <td width="12%"><span class="width2">续费(元)</span></td>
          <td width="7%"><span class="width3">操作</span></td>
        </tr>
        <#list mail_trans_list! as info>
        <tr index="${info_index}">
          <td>
              <span class="width2">
                  <i>
                      <input id="trans_ck_${info_index}" name="trans_ck_${info_index}" type="checkbox" value="" style="display:none;" />
                  </i>
                  <input id="mail_city_ids${info_index}" name="mail_city_ids${info_index}" type="hidden" value='${(info.value("city_id"))!}' />
                  <input id="mail_city_names${info_index}" name="mail_city_names${info_index}" type="hidden" value='${(info.value("city_name"))!}' />
                  <a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="mail">编辑</a>
              </span>
              <span class="width1" id="mail${info_index}">${(info.value("city_name"))!}</span>
          </td>
          <td>
              <input type="text" value='${(info.value("trans_weight"))!}' class="in" id="mail_trans_weight${info_index}" name="mail_trans_weight${info_index}" />
          </td>
          <td>
              <input type="text" value='${(info.value("trans_fee"))!}' class="in" id="mail_trans_fee${info_index}" name="mail_trans_fee${info_index}" />
          </td>
          <td>
              <input type="text" value='${(info.value("trans_add_weight"))!}' class="in" id="mail_trans_add_weight${info_index}" name="mail_trans_add_weight${info_index}" />
          </td>
          <td>
              <input type="text" value='${(info.value("trans_add_fee"))!}' class="in" id="mail_trans_add_fee${info_index}" name="mail_trans_add_fee${info_index}" />
          </td>
          <td><span class="width3"><a href="javascript:void(0);" onclick="if(confirm('确认要删除当前地区的设置么？'))remove_trans_city(this)">删除</a></span></td>
        </tr>
        </#list>
      </table>
    </div>
    <div class="rdinary_ul_bottom" style="display:none;" id="mail_trans_city_op">
      <label>
        <input name="mail_trans_all" id="mail_trans_all" type="checkbox" value="" />
        全选 </label>
      &nbsp; <a href="javascript:void(0);" id="batch_config_mail" style="display:none;">批量设置</a> &nbsp; <a href="javascript:void(0);" id="batch_del_mail">批量删除</a> </div>
    <div class="rdinary_ul_bottom"><a href="javascript:void(0);" onclick="trans_city('mail')">为指定地区城市设置运费</a>&nbsp;<a  href="javascript:void(0);" id="batch_set_mail" trans_type="mail">批量操作</a>&nbsp; <a href="javascript:void(0);" id="batch_cancle_mail" trans_type="mail" style="display:none;">取消批量</a></div>
  </div>
</div>
<!--平邮 结束-->
<!--快递 开始-->
<div class="db_box_main">
  <div class="db_box_main_input">
    <label>
      <input name="trans_express" type="checkbox" id="trans_express" value="true" <#if ((obj.transExpress)!false) == true > checked="checked"</#if> />
      快递 </label>
  </div>
  <div class="db_box_main_rdinary" id="trans_express_info" <#if ((obj.transExpress)!false) == false > style="display:none;" </#if>>
    <div class="rdinary_top">默认运费：
      <input name="express_trans_weight" type="text" id="express_trans_weight" value='${(transportTools.query_transprot(obj.transExpressInfo,"trans_weight"))!}' size="5" />
      m³内，
      <input name="express_trans_fee" type="text" id="express_trans_fee" value='${(transportTools.query_transprot(obj.transExpressInfo,"trans_fee"))!}' size="8" />
      元， 每增加
      <input name="express_trans_add_weight" type="text" id="express_trans_add_weight" value='${(transportTools.query_transprot(obj.transExpressInfo,"trans_add_weight"))!}' size="5" />
      m³，增加运费
      <input name="express_trans_add_fee" type="text" id="express_trans_add_fee" value='${(transportTools.query_transprot(obj.transExpressInfo,"trans_add_fee"))!}' size="8" />
      元</div>
    <#assign express_trans_list = (transportTools.query_all_transprot((obj.transExpressInfo),1))!/>
    <div class="rdinary_ul" <#if (express_trans_list?size)==0>style="display:none;"</#if> id="express_trans_city_info">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr bgcolor="#f5f5f5">
          <td width="46%" align="center"><span class="width1">运送到</span></td>
          <td width="11%"><span class="width2">首体积(m³)</span></td>
          <td width="13%"><span class="width2">首费(元)</span></td>
          <td width="11%"><span class="width2">续体积(m³)</span></td>
          <td width="12%"><span class="width2">续费(元)</span></td>
          <td width="7%"><span class="width3">操作</span></td>
        </tr>
        <#list express_trans_list as info>
        <tr index="${info_index}">
          <td>
              <span class="width2">
                  <i>
                      <input id="trans_ck_${info_index}" name="trans_ck_${info_index}" type="checkbox" value="" style="display:none;" />
                  </i>
                  <input id="express_city_ids${info_index}" name="express_city_ids${info_index}" type="hidden" value='${(info.value("city_id"))!}' />
                  <input id="express_city_names${info_index}" name="express_city_names${info_index}" type="hidden" value='${(info.value("city_name"))!}' />
                  <a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="express">编辑</a>
              </span>
              <span class="width1" id="express${info_index}">${(info.value("city_name"))!}</span>
          </td>
          <td>
              <input type="text" value='${(info.value("trans_weight"))!}' class="in" id="express_trans_weight${info_index}" name="express_trans_weight${info_index}" />
          </td>
          <td>
              <input type="text" value='${(info.value("trans_fee"))!}' class="in" id="express_trans_fee${info_index}" name="express_trans_fee${info_index}" />
          </td>
          <td>
              <input type="text" value='${(info.value("trans_add_weight"))!}' class="in" id="express_trans_add_weight${info_index}" name="express_trans_add_weight${info_index}" />
          </td>
          <td>
              <input type="text" value='${(info.value("trans_add_fee"))!}' class="in" id="express_trans_add_fee${info_index}" name="express_trans_add_fee${info_index}" />
          </td>
          <td>
              <span class="width3">
                  <a href="javascript:void(0);" onclick="if(confirm('确认要删除当前地区的设置么？'))remove_trans_city(this)">删除</a>
              </span>
          </td>
        </tr>
        </#list>
      </table>
    </div>
    <div class="rdinary_ul_bottom" style="display:none;" id="express_trans_city_op">
      <label>
        <input name="express_trans_all" id="express_trans_all" type="checkbox" value="" />
        全选 </label>
        &nbsp; <a href="javascript:void(0);" id="batch_config_express" style="display:none;">批量设置</a>
        &nbsp; <a href="javascript:void(0);" id="batch_del_express">批量删除</a> </div>
    <div class="rdinary_ul_bottom">
        <a href="javascript:void(0);" onclick="trans_city('express')">为指定地区城市设置运费</a>
        &nbsp;<a  href="javascript:void(0);" id="batch_set_express" trans_type="express">批量操作</a>
        &nbsp; <a href="javascript:void(0);" id="batch_cancle_express" trans_type="express" style="display:none;">取消批量</a>
    </div>
  </div>
</div>
<!--快递 结束-->
<!--EMS 开始-->
<div class="db_box_main">
  <div class="db_box_main_input">
    <label>
      <input name="trans_ems" type="checkbox" id="trans_ems" value="true" <#if ((obj.transEms)!false) == true > checked="checked"</#if>/>
      EMS </label>
  </div>
  <div class="db_box_main_rdinary" id="trans_ems_info" <#if ((obj.transEms)!false) == false > style="display:none;" </#if>>
    <div class="rdinary_top">默认运费：
      <input name="ems_trans_weight" type="text" id="ems_trans_weight" value='${(transportTools.query_transprot(obj.transEmsInfo,"trans_weight"))!}' size="5" />
      m³内，
      <input name="ems_trans_fee" type="text" id="ems_trans_fee" value='${(transportTools.query_transprot(obj.transEmsInfo,"trans_fee"))!}' size="8" />
      元， 每增加
      <input name="ems_trans_add_weight" type="text" id="ems_trans_add_weight" value='${(transportTools.query_transprot(obj.transEmsInfo,"trans_add_weight"))!}' size="5" />
      m³，增加运费
      <input name="ems_trans_add_fee" type="text" id="ems_trans_add_fee" value='${(transportTools.query_transprot(obj.transEmsInfo,"trans_add_fee"))!}' size="8" />
      元</div>
  <#assign ems_trans_list = (transportTools.query_all_transprot((obj.transEmsInfo),1))!/>
    <div class="rdinary_ul" <#if (ems_trans_list?size)==0 >style="display:none;"</#if> id="ems_trans_city_info">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr bgcolor="#f5f5f5">
          <td width="46%" align="center"><span class="width1">运送到</span></td>
          <td width="11%"><span class="width2">首体积(m³)</span></td>
          <td width="13%"><span class="width2">首费(元)</span></td>
          <td width="11%"><span class="width2">续体积(m³)</span></td>
          <td width="12%"><span class="width2">续费(元)</span></td>
          <td width="7%"><span class="width3">操作</span></td>
        </tr>
        <#list ems_trans_list! as info >
          <tr index="${info_index}">
            <td>
                <span class="width2">
                    <i>
                        <input id="trans_ck_${info_index}" name="trans_ck_${info_index}" type="checkbox" value="" style="display:none;" />
                    </i>
                    <input id="ems_city_ids${info_index}" name="ems_city_ids${info_index}" type="hidden" value='${(info.value("city_id"))!}' />
                    <input id="ems_city_names${info_index}" name="ems_city_names${info_index}" type="hidden" value='${(info.value("city_name"))!}' />
                    <a  href="javascript:void(0);" onclick="edit_trans_city(this);" trans_city_type="ems">编辑</a>
                </span>
                <span class="width1" id="ems${info_index}">${(info.value("city_name"))!}</span>
            </td>
            <td>
                <input type="text" value='${(info.value("trans_weight"))!}' class="in" id="ems_trans_weight${info_index}" name="ems_trans_weight${info_index}" />
            </td>
            <td>
                <input type="text" value='${(info.value("trans_fee"))!}' class="in" id="ems_trans_fee${info_index}" name="ems_trans_fee${info_index}" />
            </td>
            <td>
                <input type="text" value='${(info.value("trans_add_weight"))!}' class="in" id="ems_trans_add_weight${info_index}" name="ems_trans_add_weight${info_index}" />
            </td>
            <td>
                <input type="text" value='${(info.value("trans_add_fee"))!}' class="in" id="ems_trans_add_fee${info_index}" name="ems_trans_add_fee${info_index}" />
            </td>
            <td>
                <span class="width3">
                    <a href="javascript:void(0);" onclick="if(confirm('确认要删除当前地区的设置么？'))remove_trans_city(this)">删除</a>
                </span>
            </td>
          </tr>
        </#list>
      </table>
    </div>
    <div class="rdinary_ul_bottom" style="display:none;" id="ems_trans_city_op">
      <label>
        <input name="ems_trans_all" id="ems_trans_all" type="checkbox" value="" />
        全选 </label>
      &nbsp; <a href="javascript:void(0);" id="batch_config_ems" style="display:none;">批量设置</a> &nbsp; <a href="javascript:void(0);" id="batch_del_ems">批量删除</a> </div>
    <div class="rdinary_ul_bottom"><a href="javascript:void(0);" onclick="trans_city('ems')">为指定地区城市设置运费</a>&nbsp;<a  href="javascript:void(0);" id="batch_set_ems" trans_type="ems">批量操作</a>&nbsp; <a href="javascript:void(0);" id="batch_cancle_ems" trans_type="ems" style="display:none;">取消批量</a></div>
  </div>
</div>
<!--EMS 结束-->
