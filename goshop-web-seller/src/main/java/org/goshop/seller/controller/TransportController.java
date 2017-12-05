package org.goshop.seller.controller;

import com.github.pagehelper.PageInfo;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.HttpInclude;
import org.goshop.common.web.utils.WebForm;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.seller.controller.tools.TransportTools;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.AreaService;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.TransportService;
import org.goshop.store.pojo.GsTransArea;
import org.goshop.store.pojo.GsTransport;
import org.goshop.store.pojo.GsTransportWithBLOBs;
import org.goshop.store.pojo.Store;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 卖家运费模板控制器
 */
@Controller
@RequestMapping(value =  "/transport")
public class TransportController {
    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private AreaService transAreaService;

    @Autowired
    private TransportTools transportTools;

    @Autowired
    private StoreJoinService storeJoinService;

    //卖家运费模板列表
    @RequestMapping({"/transport_list"})
    public String transport_list(@CurrentUser User user,
                                 Model model,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   String currentPage,
                                   String orderBy,
                                   String orderType){
        String ret = "transport_list";
        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        Store store = storeJoinService.getCurrentStore(user);
        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        PageInfo<GsTransportWithBLOBs> plist = this.transportService.findByStoreId(store,index,12,orderBy,orderType);
        CommUtil.saveIPageList2ModelAndView("", "", params, plist, model);
        model.addAttribute("transportTools", this.transportTools);
        model.addAttribute("CommUtil",new CommUtil());

        return "transport/"+ret;
    }

    //卖家运费模板添加
    @RequestMapping({"/transport_add"})
    public String transport_add(Model model,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                String currentPage){
        String ret = "transport_add";
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("httpInclude", new HttpInclude(request, response));
        return "transport/"+ret;
    }

    //卖家运费模板编辑
    @RequestMapping({"/transport_edit"})
    public String transport_edit( Model model,
                                        HttpServletRequest request,
                                        HttpServletResponse response,
                                        String id,
                                        String currentPage){
        String ret = "transport_add";
        if ((id != null) && (!id.equals(""))){
            GsTransport transport = this.transportService.findOne(Long.valueOf(Long.parseLong(id)));
            model.addAttribute("obj", transport);
            model.addAttribute("currentPage", currentPage);
        }
        model.addAttribute("httpInclude", new HttpInclude(request, response));
        model.addAttribute("transportTools", this.transportTools);

        return "transport/"+ret;
    }

    //卖家运费模板复制
    @RequestMapping({"/transport_copy"})
    public String transport_copy(Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String id,
                                       String currentPage){
        String ret = "transport_add";
        if ((id != null) && (!id.equals(""))){
            GsTransportWithBLOBs transport = this.transportService.findOne(
                                      Long.valueOf(Long.parseLong(id)));
            GsTransportWithBLOBs obj = new GsTransportWithBLOBs();
            obj.setStoreId(transport.getStoreId());
            obj.setTransEms(transport.getTransEms());
            obj.setTransEmsInfo(transport.getTransEmsInfo());
            obj.setTransExpress(transport.getTransExpress());
            obj.setTransExpressInfo(transport.getTransExpressInfo());
            obj.setTransMail(transport.getTransMail());
            obj.setTransMailInfo(transport.getTransMailInfo());
            obj.setTransName(transport.getTransName());
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }
        model.addAttribute("httpInclude", new HttpInclude(request, response));
        model.addAttribute("transportTools", this.transportTools);

        return "transport/"+ret;
    }

    //卖家运费模板保存
    @RequestMapping({"/transport_save"})
    public String transport_save(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String id, String currentPage,
                                 String trans_mail,
                                 String trans_express,
                                 String trans_ems,
                                 String mail_city_count,
                                 String express_city_count,
                                 String ems_city_count){
        WebForm wf = new WebForm();
        GsTransportWithBLOBs transport = null;
        if (id.equals("")){
            transport = (GsTransportWithBLOBs)wf.toPo(request, GsTransportWithBLOBs.class);
            transport.setAddtime(new Date());
        }else{
            GsTransportWithBLOBs obj = this.transportService.findOne(Long.valueOf(Long.parseLong(id)));
            transport = (GsTransportWithBLOBs)wf.toPo(request, obj);
        }
        if (CommUtil.null2Boolean(trans_mail)){
            List trans_mail_info = new ArrayList();
            Map map = new HashMap();
            map.put("city_id", "-1");
            map.put("city_name", "全国");
            map.put("trans_weight", Integer.valueOf(CommUtil.null2Int(request.getParameter("mail_trans_weight"))));
            map.put("trans_fee", Float.valueOf(CommUtil.null2Float(request.getParameter("mail_trans_fee"))));
            map.put("trans_add_weight", Integer.valueOf(CommUtil.null2Int(request.getParameter("mail_trans_add_weight"))));
            map.put("trans_add_fee", Float.valueOf(CommUtil.null2Float(request.getParameter("mail_trans_add_fee"))));
            trans_mail_info.add(map);
            for (int i = 1; i <= CommUtil.null2Int(mail_city_count); i++){
                int trans_weight = CommUtil.null2Int(request.getParameter("mail_trans_weight" + i));
                String city_ids = CommUtil.null2String(request.getParameter("mail_city_ids" + i));
                if ((!city_ids.equals("")) && (trans_weight > 0)){
                    float trans_fee = CommUtil.null2Float(request.getParameter("mail_trans_fee" + i));
                    int trans_add_weight = CommUtil.null2Int(request.getParameter("mail_trans_add_weight" + i));
                    float trans_add_fee = CommUtil.null2Float(request.getParameter("mail_trans_add_fee" + i));
                    String city_name = CommUtil.null2String(request.getParameter("mail_city_names" + i));
                    Map map1 = new HashMap();
                    map1.put("city_id", city_ids);
                    map1.put("city_name", city_name);
                    map1.put("trans_weight", Integer.valueOf(trans_weight));
                    map1.put("trans_fee", Float.valueOf(trans_fee));
                    map1.put("trans_add_weight", Integer.valueOf(trans_add_weight));
                    map1.put("trans_add_fee", Float.valueOf(trans_add_fee));
                    trans_mail_info.add(map1);
                }
            }

            transport.setTransMail(true);
            transport.setTransMailInfo(JsonUtils.objectToJson(trans_mail_info));
        }
        if (CommUtil.null2Boolean(trans_express)){
            List trans_express_info = new ArrayList();
            Map map = new HashMap();
            map.put("city_id", "-1");
            map.put("city_name", "全国");
            map.put("trans_weight", Integer.valueOf(CommUtil.null2Int(request.getParameter("express_trans_weight"))));
            map.put("trans_fee", Float.valueOf(CommUtil.null2Float(request.getParameter("express_trans_fee"))));
            map.put("trans_add_weight", Integer.valueOf(CommUtil.null2Int(request.getParameter("express_trans_add_weight"))));
            map.put("trans_add_fee", Float.valueOf(CommUtil.null2Float(request.getParameter("express_trans_add_fee"))));
            trans_express_info.add(map);
            for (int i = 1; i <= CommUtil.null2Int(express_city_count); i++){
                int trans_weight = CommUtil.null2Int(request.getParameter("express_trans_weight" + i));
                String city_ids = CommUtil.null2String(request.getParameter("express_city_ids" + i));
                if ((!city_ids.equals("")) && (trans_weight > 0)){
                    float trans_fee = CommUtil.null2Float(request.getParameter("express_trans_fee" + i));
                    int trans_add_weight = CommUtil.null2Int(request.getParameter("express_trans_add_weight" + i));
                    float trans_add_fee = CommUtil.null2Float(request.getParameter("express_trans_add_fee" + i));
                    String city_name = CommUtil.null2String(request.getParameter("express_city_names" + i));
                    Map map1 = new HashMap();
                    map1.put("city_id", city_ids);
                    map1.put("city_name", city_name);
                    map1.put("trans_weight", Integer.valueOf(trans_weight));
                    map1.put("trans_fee", Float.valueOf(trans_fee));
                    map1.put("trans_add_weight", Integer.valueOf(trans_add_weight));
                    map1.put("trans_add_fee", Float.valueOf(trans_add_fee));
                    trans_express_info.add(map1);
                }
            }
            transport.setTransExpress(true);
            transport.setTransExpressInfo(JsonUtils.objectToJson(trans_express_info));
        }
        if (CommUtil.null2Boolean(trans_ems)){
            List trans_ems_info = new ArrayList();
            Map map = new HashMap();
            map.put("city_id", "-1");
            map.put("city_name", "全国");
            map.put("trans_weight", Integer.valueOf(CommUtil.null2Int(request.getParameter("ems_trans_weight"))));
            map.put("trans_fee", Float.valueOf(CommUtil.null2Float(request.getParameter("ems_trans_fee"))));
            map.put("trans_add_weight", Integer.valueOf(CommUtil.null2Int(request.getParameter("ems_trans_add_weight"))));
            map.put("trans_add_fee", Float.valueOf(CommUtil.null2Float(request.getParameter("ems_trans_add_fee"))));
            trans_ems_info.add(map);
            for (int i = 1; i <= CommUtil.null2Int(ems_city_count); i++){
                int trans_weight = CommUtil.null2Int(request.getParameter("ems_trans_weight" + i));
                String city_ids = CommUtil.null2String(request.getParameter("ems_city_ids" + i));
                if ((!city_ids.equals("")) && (trans_weight > 0)){
                    float trans_fee = CommUtil.null2Float(request.getParameter("ems_trans_fee" + i));
                    int trans_add_weight = CommUtil.null2Int(request.getParameter("ems_trans_add_weight" + i));
                    float trans_add_fee = CommUtil.null2Float(request.getParameter("ems_trans_add_fee" + i));
                    String city_name = CommUtil.null2String(request.getParameter("ems_city_names" + i));
                    Map map1 = new HashMap();
                    map1.put("city_id", city_ids);
                    map1.put("city_name", city_name);
                    map1.put("trans_weight", Integer.valueOf(trans_weight));
                    map1.put("trans_fee", Float.valueOf(trans_fee));
                    map1.put("trans_add_weight", Integer.valueOf(trans_add_weight));
                    map1.put("trans_add_fee", Float.valueOf(trans_add_fee));
                    trans_ems_info.add(map1);
                }
            }
            transport.setTransEms(true);
            transport.setTransEmsInfo(JsonUtils.objectToJson(trans_ems_info));
        }
        transport.setAddtime(new Date());
        Store store = storeJoinService.getCurrentStore(user);
        transport.setStoreId(store.getStoreId());
        if (id.equals(""))
            this.transportService.save(transport);
        else
            this.transportService.update(transport);

        return "redirect:"+"/transport/"+"transport_success?currentPage=" + currentPage;
    }

    //卖家运费模板保存成功
    @RequestMapping({"/transport_success"})
    public String transport_success(Model model,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          String currentPage){
        String ret = "transport_success";

        model.addAttribute("op_title", "运费模板保存成功");
        model.addAttribute("url", CommUtil.getURL(request) +
                     "/transport/transport_list?currentPage=" + currentPage);

        return "transport/"+ret;
    }

    //卖家运费模板删除
    @RequestMapping({"/transport_del"})
    public String transport_del(HttpServletRequest request,
                                HttpServletResponse response,
                                String mulitId,
                                String currentPage){
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                GsTransportWithBLOBs transport = this.transportService.findOne(Long.valueOf(Long.parseLong(id)));
                this.transportService.delete(transport);
            }
        }

        return "redirect:/transport/"+"transport_list?currentPage=" + currentPage;
    }

    //卖家运费模板详细信息
    @RequestMapping({"/transport_info"})
    public String transport_info(Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String type,
                                 String id){//
        if ((type == null) || (type.equals(""))){
            type = CommUtil.null2String(request.getAttribute("type"));
        }
        if ((id == null) || (id.equals(""))){
            id = CommUtil.null2String(request.getAttribute("id"));
        }
        if (CommUtil.null2String(type).equals("")){
            type = "mail";
        }
        String ret = "transport_" + type;
        if ((id != null) && (!id.equals(""))){
            GsTransportWithBLOBs transport = this.transportService.findOne(Long.valueOf(Long.parseLong(id)));
            model.addAttribute("obj", transport);
            model.addAttribute("transportTools", this.transportTools);
        }

        return "transport/"+ret;
    }

    //卖家运费模板区域编辑
    @RequestMapping({"/transport_area"})
    public String transport_area(Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String id,
                                       String trans_city_type,
                                       String trans_index){
        String ret = "transport_area";
        List<GsTransArea> objs = this.transAreaService.findByRootArea();
        for (GsTransArea area : objs){
            List<GsTransArea> childs = this.transAreaService.findByRootArea(area);
            area.setChilds(childs);
            for (GsTransArea ch : childs){
                List<GsTransArea> grands = this.transAreaService.findByRootArea(ch);
                ch.setChilds(grands);
            }
        }
        model.addAttribute("objs", objs);
        model.addAttribute("trans_city_type", trans_city_type);
        model.addAttribute("trans_index", trans_index);

        return "transport/"+ret;
    }
}




