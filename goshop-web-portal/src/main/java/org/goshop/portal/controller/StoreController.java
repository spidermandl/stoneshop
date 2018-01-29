package org.goshop.portal.controller;

import com.github.pagehelper.PageInfo;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsEvaluateService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.i.GoodsUserClassService;
import org.goshop.goods.pojo.GsGoodsEvaluateWithBLOBs;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.order.i.OrderFormService;
import org.goshop.order.pojo.GsOrderform;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreClassService;
import org.goshop.store.i.StoreNavigationService;
import org.goshop.store.i.StorePartnerService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.GsStoreClass;
import org.goshop.store.pojo.GsStoreNav;
import org.goshop.store.pojo.Store;
import org.goshop.tools.service.AreaViewTools;
import org.goshop.tools.service.GoodsViewTools;
import org.goshop.tools.service.StoreViewTools;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺控制器
 */
@Controller
public class StoreController extends BaseController{
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsUserClassService userGoodsClassService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StoreClassService storeClassService;
    @Autowired
    private StoreNavigationService storeNavigationService;
    @Autowired
    private GoodsEvaluateService goodsEvaluateService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private StorePartnerService storePartnerService;


    @Autowired
    private AreaViewTools areaViewTools;
    @Autowired
    private GoodsViewTools goodsViewTools;
    @Autowired
    private StoreViewTools storeViewTools;


    @RequestMapping({"/store"})
    public String store(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        String id){
        reCapsuleModel(model,request,response);
        String serverName = request.getServerName().toLowerCase();
        Store store = null;
        if ((id == null) && (serverName.indexOf(".") >= 0) &&
                (serverName.indexOf(".") != serverName.lastIndexOf(".")) &&
                (this.systemConfigService.getConfig().getSecond_domain_open())){
            String secondDomain = serverName.substring(0,serverName.indexOf("."));
            store = this.storeService.findBySecondDomain(secondDomain);
        }else{
            store = this.storeService.findOne(CommUtil.null2Long(id));
        }
        if (store == null){
            String ret = generateViewURL("error");
            model.addAttribute("op_title", "不存在该店铺信息");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
            return ret;
        }
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_index");
        if (store.getStoreState() == 2){
            add_store_common_info(model, store);
            model.addAttribute("store", store);
            model.addAttribute("nav_id", "store_index");
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "店铺已经关闭或者未开通店铺");
            model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");
        }
        generic_evaluate(store, model);

        return ret;
    }

    @RequestMapping({"/store_left"})
    public String store_left(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response){
        reCapsuleModel(model,request,response);
        Store store = this.storeService.findOne(CommUtil.null2Long(request.getAttribute("id")));
        String template = "default";
        if ((store != null) && (store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_left");
        model.addAttribute("store", store);
        add_store_common_info(model, store);
        generic_evaluate(store, model);
        Map params = new HashMap();
        params.put("store_id", store.getStoreId());
        params.put("orderBy","sequence");
        params.put("orderType","asc");
        List partners = this.storePartnerService.findByCondition(params);
//                .query("select obj from StorePartner obj where obj.store.id=:store_id order by obj.sequence asc",params, -1, -1);
        model.addAttribute("partners", partners);
        model.addAttribute("goodsViewTools", this.goodsViewTools);

        return ret;
    }

    @RequestMapping({"/store_left1"})
    public String store_left1(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response){
        reCapsuleModel(model,request,response);
        Store store = this.storeService.findOne(CommUtil.null2Long(request.getAttribute("id")));
        String template = "default";
        if ((store != null) && (store.getTemplate() != null) &&(!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_left1");
        model.addAttribute("store", store);
        add_store_common_info(model, store);
        Map params = new HashMap();
        params.put("store_id", store.getStoreId());
        params.put("orderBy","sequence");
        params.put("orderType","asc");
        List partners = this.storePartnerService.findByCondition(params);
//                .query("select obj from StorePartner obj where obj.store.id=:store_id order by obj.sequence asc",params, -1, -1);
        model.addAttribute("partners", partners);

        return ret;
    }

    @RequestMapping({"/store_left2"})
    public String store_left2(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response){
        reCapsuleModel(model,request,response);
        Store store = this.storeService.findOne(CommUtil.null2Long(request.getAttribute("id")));
        String template = "default";
        if ((store != null) && (store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_left2");
        model.addAttribute("store", store);
        add_store_common_info(model, store);

        return ret;
    }

    @RequestMapping({"/store_nav"})
    public String store_nav(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        reCapsuleModel(model,request,response);
        Long id = CommUtil.null2Long(request.getAttribute("id"));
        Store store = this.storeService.findOne(id);
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_nav");
        if (store.getStoreState() == 2){
            Map params = new HashMap();
            params.put("store_id", store.getStoreId());
            params.put("display", Boolean.valueOf(true));
            params.put("orderBy", "sequence");
            params.put("orderType", "asc");
            List navs = this.storeNavigationService.findByCondition(params);
//                    .query("select obj from StoreNavigation obj where obj.store.id=:store_id and obj.display=:display order by obj.sequence asc", params, -1, -1);
            model.addAttribute("navs", navs);
            model.addAttribute("store", store);
            String goods_view = CommUtil.null2String(request.getAttribute("goods_view"));
            model.addAttribute("goods_view", Boolean.valueOf(CommUtil.null2Boolean(goods_view)));
            model.addAttribute("goods_id",CommUtil.null2String(request.getAttribute("goods_id")));
            model.addAttribute("goods_list",Boolean.valueOf(CommUtil.null2Boolean(request.getAttribute("goods_list"))));
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "店铺信息错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    @RequestMapping({"/store_credit"})
    public String store_credit(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response, String id){
        reCapsuleModel(model,request,response);
        Store store = this.storeService.findOne(CommUtil.null2Long(id));
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_credit");
        if (store.getStoreState() == 2){
            List<GsOrderform> orders = orderFormService.findByStoreId(store.getStoreId());
            List<Long> of_ids = new ArrayList<>();
            of_ids.add(-1L);
            for (GsOrderform order:orders){
                of_ids.add(order.getId());
            }
            Map param = new HashMap();
            param.put("orderBy","addTime");
            param.put("orderType","desc");
            param.put("of_ids",of_ids);
            PageInfo<GsGoodsEvaluateWithBLOBs> pList = goodsEvaluateService.findByCondition(param,1,12);
            CommUtil.saveIPageList2ModelAndView(CommUtil.getURL(request) + "/store_eva", "", "", pList, model);
            model.addAttribute("store", store);
            model.addAttribute("nav_id", "store_credit");
            model.addAttribute("storeViewTools", this.storeViewTools);
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "店铺信息错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    @RequestMapping({"/store_eva"})
    public String store_eva(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String id, String currentPage,
                            String eva_val){
        reCapsuleModel(model,request,response);
        Store store = this.storeService.findOne(Long.valueOf(Long.parseLong(id)));
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_eva");
        if (store.getStoreState() == 2){
            List<GsOrderform> orders = orderFormService.findByStoreId(store.getStoreId());
            List<Long> of_ids = new ArrayList<>();
            for (GsOrderform order:orders){
                of_ids.add(order.getId());
            }
            of_ids.add(-1L);
            Map param = new HashMap();
            param.put("orderBy","addTime");
            param.put("orderType","desc");
            param.put("of_ids",of_ids);
            param.put("evaluate_buyer_val",Integer.valueOf(CommUtil.null2Int(eva_val)));

            PageInfo<GsGoodsEvaluateWithBLOBs> pList = this.goodsEvaluateService.findByCondition(param,1,12);
            CommUtil.saveIPageList2ModelAndView(CommUtil.getURL(request) +"/store_eva", "",
                    "&eva_val=" + CommUtil.null2String(eva_val), pList, model);
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "店铺信息错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");
        }

        return ret;
    }

    @RequestMapping({"/store_info"})
    public String store_info(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response, String id){
        reCapsuleModel(model,request,response);
        Store store = this.storeService.findOne(Long.valueOf(Long.parseLong(id)));
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/store_info");
        if (store.getStoreState() == 2){
            model.addAttribute("store", store);
            model.addAttribute("nav_id", "store_info");
            model.addAttribute("areaViewTools", this.areaViewTools);
        }else{
            ret = "error";
            model.addAttribute("op_title", "店铺信息错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    @RequestMapping({"/store_url"})
    public String store_url(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response, String id){
        GsStoreNav nav = this.storeNavigationService.findOne(CommUtil.null2Long(id));
        Store store = this.storeService.findOne(nav.getStoreId());
        String template = "default";
        if ((store.getTemplate() != null) &&(!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = template + "/store_url";
        model.addAttribute("store", store);
        model.addAttribute("nav", nav);
        model.addAttribute("nav_id", nav.getId());

        return ret;
    }

    private void add_store_common_info(Model model, Store store){
        Map params = new HashMap();
        params.put("user_id", store.getMemberId());
        params.put("display", Boolean.valueOf(true));
        params.put("orderBy","sequence");
        params.put("orderType","asc");
        List ugcs = this.userGoodsClassService.findByCondition(params);
//        "select obj from UserGoodsClass obj where obj.user.id=:user_id and obj.display=:display and obj.parent.id is null order by obj.sequence asc",
        model.addAttribute("ugcs", ugcs);
        params.clear();
        params.put("store_recommend", Boolean.valueOf(true));
        params.put("goods_store_id", store.getStoreId());
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy","addTime");
        params.put("orderType","desc");
        List<GsGoodsWithBLOBs> goods_recommend = this.goodsService.findByCondition(params,1,8).getList();
        this.goodsViewTools.fillGoodsWithSubIds(goods_recommend);
//                .query("select obj from Goods obj where obj.goods_recommend=:recommend and obj.goods_store.id=:goods_store_id and obj.goods_status=:goods_status order by obj.addTime desc", params, 0, 8);
        params.clear();
        params.put("goods_store_id", store.getStoreId());
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy","addTime");
        params.put("orderType","desc");
        List<GsGoodsWithBLOBs> goods_new = this.goodsService.findByCondition(params,1,12).getList();
        this.goodsViewTools.fillGoodsWithSubIds(goods_new);
//                .query("select obj from Goods obj where obj.goods_store.id=:goods_store_id and obj.goods_status=:goods_status order by obj.addTime desc ", arams, 0, 12);
        model.addAttribute("goods_recommend", goods_recommend);
        model.addAttribute("goods_new", goods_new);
        model.addAttribute("goodsViewTools", this.goodsViewTools);
        model.addAttribute("storeViewTools", this.storeViewTools);
        model.addAttribute("areaViewTools", this.areaViewTools);
    }

    @RequestMapping({"/store_list"})
    public String store_list(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             String id, String sc_id,
                             String currentPage, String orderType,
                             String store_name, String store_ower,
                             String type){
        String ret = "store_list";
        List scs = this.storeClassService.findByParentId(null);
//                .query("select obj from StoreClass obj where obj.parent.id is null order by obj.sequence asc",null, -1, -1);
        model.addAttribute("scs", scs);
        Map param = new HashMap();
        param.put("orderBy","store_credit");
        param.put("orderType",orderType);
        if ((sc_id != null) && (!sc_id.equals(""))){
            param.put("sc_id", CommUtil.null2Long(sc_id));
        }
        if ((store_name != null) && (!store_name.equals(""))){
            param.put("store_name", store_name);
            model.addAttribute("store_name", store_name);
        }
        if ((store_ower != null) && (!store_ower.equals(""))){
            param.put("store_ower", store_ower);
            model.addAttribute("store_ower", store_ower);
        }
        param.put("store_status", Integer.valueOf(2));
        PageInfo<Store> pList = this.storeService.findByCondition(param,CommUtil.null2Int(currentPage),12);
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);
        model.addAttribute("storeViewTools", this.storeViewTools);
        model.addAttribute("type", type);

        return ret;
    }

    @RequestMapping({"/store_goods_search"})
    public String store_goods_search(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String keyword,
                                     String store_id,
                                     String currentPage){
        Store store = this.storeService.findOne(Long.valueOf(Long.parseLong(store_id)));
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        String ret = template + "/store_goods_search";

        Map param = new HashMap();
        param.put("store_id", CommUtil.null2Long(store_id));
        param.put("goods_name", keyword);
        param.put("goods_status", Integer.valueOf(0));
        PageInfo<GsGoodsWithBLOBs> pList = this.goodsService.findByCondition(param,CommUtil.null2Int(currentPage),20);
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);
        model.addAttribute("keyword", keyword);
        model.addAttribute("store", store);

        return ret;
    }

    @RequestMapping({"/store_head"})
    public String store_head(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response){
        model.addAttribute("CommUtil",new CommUtil());
        String ret = generateViewURL("store_head");
        Store store = this.storeService.findOne(CommUtil.null2Long(request.getAttribute("store_id")));
        generic_evaluate(store, model);
        model.addAttribute("store", store);
        model.addAttribute("storeViewTools", this.storeViewTools);

        return ret;
    }

    private void generic_evaluate(Store store, Model model){
        double description_result = 0.0D;
        double service_result = 0.0D;
        double ship_result = 0.0D;
        if ((store != null) && (store.getScId() != null) && (store.getPoint() != null)){
            GsStoreClass sc = this.storeClassService.findOne(store.getScId());
            float description_evaluate = CommUtil.null2Float(sc.getDescriptionEvaluate());
            float service_evaluate = CommUtil.null2Float(sc.getServiceEvaluate());
            float ship_evaluate = CommUtil.null2Float(sc.getShipEvaluate());
            float store_description_evaluate = CommUtil.null2Float(store.getPoint().getDescriptionEvaluate());
            float store_service_evaluate = CommUtil.null2Float(store.getPoint().getServiceEvaluate());
            float store_ship_evaluate = CommUtil.null2Float(store.getPoint().getShipEvaluate());

            description_result = CommUtil.div(Float.valueOf(store_description_evaluate - description_evaluate), Float.valueOf(description_evaluate));
            service_result = CommUtil.div(Float.valueOf(store_service_evaluate - service_evaluate), Float.valueOf(service_evaluate));
            ship_result = CommUtil.div(Float.valueOf(store_ship_evaluate - ship_evaluate), Float.valueOf(ship_evaluate));
        }
        if (description_result > 0.0D){
            model.addAttribute("description_css", "better");
            model.addAttribute("description_type", "高于");
            model.addAttribute(
                    "description_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(description_result), Integer.valueOf(100)) > 100.0D ? 100.0D :
                            CommUtil.mul(Double.valueOf(description_result), Integer.valueOf(100)))) +"%");
        }
        if (description_result == 0.0D){
            model.addAttribute("description_css", "better");
            model.addAttribute("description_type", "持平");
            model.addAttribute("description_result", "-----");
        }
        if (description_result < 0.0D){
            model.addAttribute("description_css", "lower");
            model.addAttribute("description_type", "低于");
            model.addAttribute(
                    "description_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(-description_result), Integer.valueOf(100)))) +
                            "%");
        }
        if (service_result > 0.0D){
            model.addAttribute("service_css", "better");
            model.addAttribute("service_type", "高于");
            model.addAttribute("service_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(service_result), Integer.valueOf(100)))) +
                            "%");
        }
        if (service_result == 0.0D){
            model.addAttribute("service_css", "better");
            model.addAttribute("service_type", "持平");
            model.addAttribute("service_result", "-----");
        }
        if (service_result < 0.0D){
            model.addAttribute("service_css", "lower");
            model.addAttribute("service_type", "低于");
            model.addAttribute("service_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(-service_result), Integer.valueOf(100)))) +
                            "%");
        }
        if (ship_result > 0.0D){
            model.addAttribute("ship_css", "better");
            model.addAttribute("ship_type", "高于");
            model.addAttribute("ship_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(ship_result), Integer.valueOf(100)))) + "%");
        }
        if (ship_result == 0.0D){
            model.addAttribute("ship_css", "better");
            model.addAttribute("ship_type", "持平");
            model.addAttribute("ship_result", "-----");
        }
        if (ship_result < 0.0D){
            model.addAttribute("ship_css", "lower");
            model.addAttribute("ship_type", "低于");
            model.addAttribute("ship_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(-ship_result), Integer.valueOf(100)))) + "%");
        }
    }

}
