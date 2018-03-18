package org.goshop.portal.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.goods.i.*;
import org.goshop.goods.pojo.*;
import org.goshop.order.i.GoodsCartService;
import org.goshop.portal.tools.IpAddress;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StoreClassService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.GsArea;
import org.goshop.store.pojo.GsStoreClass;
import org.goshop.store.pojo.Store;
import org.goshop.tools.service.AreaViewTools;
import org.goshop.tools.service.GoodsViewTools;
import org.goshop.tools.service.StoreViewTools;
import org.goshop.tools.service.TransportTools;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * 商品控制器
 */
@Controller
public class GoodsController extends BaseController{

    @Autowired
    private GoodsUserClassService goodsUserClassService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsEvaluateService goodsEvaluateService;
    @Autowired
    private StoreClassService storeClassService;
    @Autowired
    private GoodsPropertyService goodsPropertyService;
    @Autowired
    private GoodsCartService goodsCartService;
    @Autowired
    private GoodsTypePropertyService goodsTypePropertyService;


    @Autowired
    private AreaViewTools areaViewTools;
    @Autowired
    private GoodsViewTools goodsViewTools;
    @Autowired
    private StoreViewTools storeViewTools;
    @Autowired
    private TransportTools transportTools;
    @Autowired
    private StoreAreaService storeAreaService;
    @Autowired
    private GoodsClassService goodsClassService;
    @Autowired
    private GoodsBrandService goodsBrandService;
    @Autowired
    private GoodsConsultService goodsConsultService;

    @Override
    protected String rootTemplatePath() {
        return "store/";
    }

    @RequestMapping({"/goods_list"})
    public String goods_list(Model model,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   String gc_id, String store_id,
                                   String recommend, String currentPage,
                                   String orderBy, String orderType,
                                   String begin_price, String end_price){
        reCapsuleModel(model,request,response);
        GsGoodsUserClass ugc = this.goodsUserClassService.findOne(CommUtil.null2Long(gc_id));
        String template = "default";
        String ret = null;
        Store store = this.storeService.findOne(CommUtil.null2Long(store_id));
        if (store != null){
            if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
                template = store.getTemplate();
            }
            ret = generateViewURL(template + "/goods_list");
            Map param = new HashMap();
            param.put("orderBy",orderBy);
            param.put("orderType",orderType);
            param.put("goods_store_id",store.getStoreId());

            if (ugc != null){
                List<Long> ids = genericUserGcIds(ugc);
                List ugc_list = new ArrayList();
                for (Long g_id : ids){
                    GsGoodsUserClass temp_ugc = this.goodsUserClassService.findOne(g_id);
                    ugc_list.add(temp_ugc);
                }
                param.put("goods_store_id",ids);
            }else{
                ugc = new GsGoodsUserClass();
                ugc.setClassname("全部商品");
                model.addAttribute("ugc", ugc);
            }
            if ((recommend != null) && (!recommend.equals(""))){
                param.put("goods_recommend",Boolean.valueOf(CommUtil.null2Boolean(recommend)));
            }
            if ((begin_price != null) && (!begin_price.equals(""))){
                param.put("begin_price",BigDecimal.valueOf(CommUtil.null2Double(begin_price)));
            }
            if ((end_price != null) && (!end_price.equals(""))){
                param.put("end_price",BigDecimal.valueOf(CommUtil.null2Double(end_price)));
            }

            PageInfo pList = this.goodsService.findByCondition(param,CommUtil.null2Int(currentPage),Integer.valueOf(20));
            String url = this.systemConfigService.getConfig().getAddress();
            if ((url == null) || (url.equals(""))){
                url = CommUtil.getURL(request);
            }
            CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);
            model.addAttribute("ugc", ugc);
            model.addAttribute("store", store);
            model.addAttribute("recommend", recommend);
            model.addAttribute("begin_price", begin_price);
            model.addAttribute("end_price", end_price);
            model.addAttribute("goodsViewTools", this.goodsViewTools);
            model.addAttribute("storeViewTools", this.storeViewTools);
            model.addAttribute("areaViewTools", this.areaViewTools);
            return ret;
        }
        ret = generateViewURL("error");
        model.addAttribute("op_title", "请求参数错误");
        model.addAttribute("url", CommUtil.getURL(request) + "/index");

        return ret;
    }

    /**
     * 商品详情页
     * @param request
     * @param response
     * @param id 商品id
     * @return
     */
    @RequestMapping({"/goods"})
    public String goods(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          String id){
        String ret = null;
        reCapsuleModel(model,request,response);
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        GsGoodsWithBLOBs obj = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));
        Store store = this.storeService.findOne(obj.getGoodsStoreId());
        if (obj.getGoodsStatus() == 0){// 商品上架状态
            String template = "default";
            if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
                template = store.getTemplate();
            }

            ret = generateViewURL(template + "/store_goods");

            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret = generateViewURL("wap/store_goods");
            }
            obj.setGoodsClick(obj.getGoodsClick() + 1);
            if ((this.systemConfigService.getConfig().getZtc_status()) && (obj.getZtcStatus() == 2)){
                obj.setZtcClickNum(obj.getZtcClickNum() + 1);
            }
            if ((obj.getGroupId() != null) && (obj.getGroupBuy() == 2)){
                GsGroup group = obj.getGroup();
                if (group.getEndtime().before(new Date())){
                    obj.setGroupId(null);
                    obj.setGroupBuy(0);
                    obj.setGoodsCurrentPrice(obj.getStorePrice());
                }
            }

            this.goodsService.update(obj);
            if (store.getStoreState() == 2){// 店铺状态正常
                model.addAttribute("obj", obj);
                model.addAttribute("store", store);
                Map params = new HashMap();
                params.put("display", Boolean.valueOf(true));
                params.put("user_id",store.getMemberId());
                params.put("orderBy","sequence");
                params.put("orderType","asc");
                List ugcs = this.goodsUserClassService.findByCondition(params);
                model.addAttribute("ugcs", ugcs);
                params.clear();
                params.put("goods_store_id",store.getStoreId());
                params.put("goods_recommend",Boolean.valueOf(true));
                params.put("exclude_id",obj.getId());
                params.put("orderBy","addTime");
                params.put("orderType","desc");
                List<GsGoodsWithBLOBs> selects = this.goodsService.findByCondition(params,1,Integer.valueOf(4)).getList();
                this.goodsViewTools.fillGoodsWithSubIds(selects);
                model.addAttribute("goods_recommend_list", selects);

                params.clear();
                params.put("evaluate_type", "buyer");
                params.put("evaluate_goods_id", obj.getId());

                List evas = this.goodsEvaluateService.findByCondition(params);
                model.addAttribute("eva_count", Integer.valueOf(evas.size()));
                model.addAttribute("goodsViewTools", this.goodsViewTools);
                model.addAttribute("storeViewTools", this.storeViewTools);
                model.addAttribute("areaViewTools", this.areaViewTools);
                model.addAttribute("transportTools", this.transportTools);

                List<GsGoods> user_viewed_goods = (List)request.getSession(false).getAttribute("user_viewed_goods");
                if (user_viewed_goods == null){
                    user_viewed_goods = new ArrayList();
                }
                boolean add = true;
                for (GsGoods goods : user_viewed_goods){
                    if (goods.getId().equals(obj.getId())){
                        add = false;
                        break;
                    }
                }
                if (add){
                    if (user_viewed_goods.size() >= 4)
                        user_viewed_goods.set(1, obj);
                    else
                        user_viewed_goods.add(obj);
                }
                request.getSession(false).setAttribute("user_viewed_goods", user_viewed_goods);

                IpAddress ipAddr = IpAddress.getInstance();
                String current_ip = CommUtil.getIpAddr(request);
                String current_city = ipAddr.IpStringToAddress(current_ip);
                if ((current_city == null) || (current_city.equals(""))){
                    current_city = "全国";
                }

                model.addAttribute("current_city", current_city);

                List areas = this.storeAreaService.findByParentId(null);
                model.addAttribute("areas", areas);
                generic_evaluate(store, model);
            }else{// 店铺状态异常
                ret = generateViewURL("error");
                if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                    ret = generateViewURL("wap/error");
                }
                model.addAttribute("op_title", "店铺未开通，拒绝访问");
                model.addAttribute("url", CommUtil.getURL(request) + "/index");
            }
        }else{
            ret = generateViewURL("error");
            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret = generateViewURL("wap/error");
            }
            model.addAttribute("op_title", "该商品未上架，不允许查看");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    /**
     * 商品分类展示页
     * @param request
     * @param response
     * @param gc_id
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param store_price_begin
     * @param store_price_end
     * @param brand_ids
     * @param gs_ids
     * @param properties
     * @param op
     * @param goods_name
     * @param area_name
     * @param area_id
     * @param goods_view
     * @param all_property_status
     * @param detail_property_status
     * @return
     */
    @RequestMapping({"/store_goods_list"})
    public String store_goods_list(Model model,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   String gc_id, String currentPage,
                                   String orderBy, String orderType,
                                   String store_price_begin, String store_price_end,
                                   String brand_ids, String gs_ids, String properties,
                                   String op, String goods_name, String area_name,
                                   String area_id, String goods_view,
                                   String all_property_status,
                                   String detail_property_status){
        String ret = generateViewURL("store_goods_list");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/store_goods_list");
        }
        GsGoodsClass gc = this.goodsClassService.findOne(CommUtil.null2Long(gc_id));
        model.addAttribute("gc", gc);
        if ((orderBy == null) || (orderBy.equals(""))){
            orderBy = "addTime";
        }
        if ((op != null) && (!op.equals(""))){
            model.addAttribute("op", op);
        }
        String orderBy1 = orderBy;
        if (this.systemConfigService.getConfig().getZtc_status()){
            orderBy = "ztc_dredge_price";
            orderType = "desc";
        }
        List good_class_ids = genericIds(gc);
        Map paras = new HashMap();
        Map store_params = new HashMap();
        paras.put("good_class_ids", good_class_ids);
        paras.put("orderBy",orderBy);
        paras.put("orderType",orderType);

        if ((store_price_begin != null) && (!store_price_begin.equals(""))){
            paras.put("begin_price",BigDecimal.valueOf(CommUtil.null2Double(store_price_begin)));
        }
        if ((store_price_end != null) && (!store_price_end.equals(""))){
            paras.put("end_price",BigDecimal.valueOf(CommUtil.null2Double(store_price_end)));
        }
        if ((goods_name != null) && (!goods_name.equals(""))){
            paras.put("goods_name", "%"+goods_name.trim()+"%");
            model.addAttribute("goods_name", goods_name);
        }

        if ((area_id != null) && (!area_id.equals(""))){
            GsArea area = this.storeAreaService.findOne(CommUtil.null2Long(area_id));
            model.addAttribute("area", area);
            List area_ids = getAreaChildIds(area);
            store_params.put("area_ids", area_ids);
        }
        if ((area_name != null) && (!area_name.equals(""))){
            model.addAttribute("area_name", area_name);
            Map like_area = new HashMap();
            like_area.put("area_name", area_name + "%");
            List likes_areas = this.storeAreaService.findByCondition(like_area);
            List like_area_ids = getArrayAreaChildIds(likes_areas);
            store_params.put("area_ids", like_area_ids);
        }

        store_params.put("store_state",Integer.valueOf(2));
        paras.put("store_ids",this.storeService.findIndexByCondition(store_params));
        paras.put("goods_status",Integer.valueOf(0));
        List goods_property = new ArrayList();
        if (!CommUtil.null2String(brand_ids).equals("")){
            String[] brand_id_list = brand_ids.substring(1).split("\\|");
            if (brand_id_list.length == 1){
                String brand_id = brand_id_list[0];
                String[] brand_info_list = brand_id.split(",");
                paras.put("goods_brand_id",CommUtil.null2Long(brand_info_list[0]));
                Map map = new HashMap();
                GsGoodsBrand brand = this.goodsBrandService.findOne(CommUtil.null2Long(brand_info_list[0]));
                map.put("name", "品牌");
                map.put("value", brand.getName());
                map.put("type", "brand");
                map.put("id", brand.getId());
                goods_property.add(map);
            }else{
                List brand_long_ids = new ArrayList();
                for (int i = 0; i < brand_id_list.length; i++){
                    String brand_id = brand_id_list[i];
                    String[] brand_info_list = brand_id.split(",");
                    brand_long_ids.add(CommUtil.null2Long(brand_info_list[0]));
                    Map map = new HashMap();
                    GsGoodsBrand brand = this.goodsBrandService.findOne(CommUtil.null2Long(brand_info_list[0]));
                    map.put("name", "品牌");
                    map.put("value", brand.getName());
                    map.put("type", "brand");
                    map.put("id", brand.getId());
                    goods_property.add(map);
                }
                model.addAttribute("brand_ids", brand_long_ids);
            }
        }
        /**
         * 属性相关查询
         */
        if (!CommUtil.null2String(gs_ids).equals("")){
            List gsp_lists = generic_gsp(gs_ids);
            List long_gsp_lists = new ArrayList();//数据库查询参数
            for (int j = 0; j < gsp_lists.size(); j++){
                List gsp_list = (List)gsp_lists.get(j);
                for (int i = 0; i < gsp_list.size(); i++){
                    GsGoodsSpecProperty gsp = (GsGoodsSpecProperty)gsp_list.get(i);
                    Map map = new HashMap();
                    map.put("name", gsp.getSpec().getName());
                    map.put("value", gsp.getValue());
                    map.put("type", "gs");
                    map.put("id", gsp.getId());
                    long_gsp_lists.add(gsp.getId());
                    goods_property.add(map);
                }
            }
            paras.put("ids_from_spec",this.goodsService.findGoodsIndexBySpecId(long_gsp_lists));
            model.addAttribute("gs_ids", gs_ids);
        }
        if (!CommUtil.null2String(properties).equals("")){
            String[] properties_list = properties.substring(1).split("\\|");
            List condition = new ArrayList();
            for (int i = 0; i < properties_list.length; i++){
                String property_info = properties_list[i];
                String[] property_info_list = property_info.split(",");
                GsGoodsTypeProperty gtp = this.goodsTypePropertyService.findOne(CommUtil.null2Long(property_info_list[0]));


                condition.add("name like "+"%" + gtp.getName().trim() + "%"+" and "
                        +"value like "+"%" + property_info_list[1].trim() + "%");

//                p_map.put("name" + i, "%" + gtp.getName().trim() + "%");
//                p_map.put("value" + i, "%" + property_info_list[1].trim() + "%");
//                gqo.addQuery("and (obj.goods_property like :gtp_name" + i +
//                        " and obj.goods_property like :gtp_value" + i + ")", p_map);
                Map map = new HashMap();
                map.put("name", gtp.getName());
                map.put("value", property_info_list[1]);
                map.put("type", "properties");
                map.put("id", gtp.getId());
                goods_property.add(map);
            }
            List goods_type_ids = this.goodsTypePropertyService.selectByPropertyGroup(condition);
            paras.put("goods_type_ids",goods_type_ids);
            model.addAttribute("properties", properties);
        }
        Map params = new HashMap();
        params.put("orderBy","sequence");
        params.put("orderType","asc");
        params.put("common", Boolean.valueOf(true));
        List areas = this.storeAreaService.findByCondition(params);
        model.addAttribute("areas", areas);

        PageInfo pList = this.goodsService.findByCondition(paras,CommUtil.null2Int(currentPage),Integer.valueOf(20));
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);
        model.addAttribute("gc", gc);
        model.addAttribute("orderBy", orderBy1);
        model.addAttribute("user_viewed_goods", request.getSession(false).getAttribute("user_viewed_goods"));
        model.addAttribute("goods_property", goods_property);
        if (CommUtil.null2String(goods_view).equals("list"))
            goods_view = "list";
        else{
            goods_view = "thumb";
        }

        if (this.systemConfigService.getConfig().getZtc_status()){
            List ztc_goods = null;
            Map ztc_map = new HashMap();
            ztc_map.put("ztc_status", Integer.valueOf(3));
            ztc_map.put("ztc_begin_time", new Date());
            ztc_map.put("ztc_gold", Integer.valueOf(0));
            ztc_map.put("orderBy","ztc_dredge_price");
            ztc_map.put("orderType","desc");
            if (this.systemConfigService.getConfig().getZtc_goods_view() == 0){
                ztc_goods = this.goodsService.findByCondition(ztc_map,1,5).getList();
            }
            if (this.systemConfigService.getConfig().getZtc_goods_view() == 1){
                ztc_map.put("good_class_ids",good_class_ids);
                ztc_goods = this.goodsService.findByCondition(ztc_map,1,5).getList();
            }
            model.addAttribute("ztc_goods", ztc_goods);
        }
        if ((detail_property_status != null) && (!detail_property_status.equals(""))){
            model.addAttribute("detail_property_status", detail_property_status);
            String[] temp_str = detail_property_status.split(",");
            Map pro_map = new HashMap();
            List pro_list = new ArrayList();
            for (String property_status : temp_str){
                if ((property_status != null) && (!property_status.equals(""))){
                    String[] mark = property_status.split("_");
                    pro_map.put(mark[0], mark[1]);
                    pro_list.add(mark[0]);
                }
            }
            model.addAttribute("pro_list", pro_list);
            model.addAttribute("pro_map", pro_map);
        }
        model.addAttribute("goods_view", goods_view);
        model.addAttribute("all_property_status", all_property_status);

        return ret;
    }

    /**
     * 商家热卖商品
     * @param model
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param goods_view
     * @return
     */
    @RequestMapping({"/ztc_goods_list"})
    public String ztc_goods_list(Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String currentPage, String orderBy,
                                       String orderType, String goods_view){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("ztc_goods_list");
        Map param = new HashMap();
        param.put("goods_status",Integer.valueOf(0));
        param.put("ztc_status",Integer.valueOf(3));
        param.put("ztc_begin_time",new Date());// "<"
        param.put("ztc_gold",Integer.valueOf(0));// ">"
        param.put("orderBy","ztc_dredge_price");
        param.put("orderType","desc");
        PageInfo pList = this.goodsService.findByCondition(param,CommUtil.null2Int(currentPage),Integer.valueOf(20));
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);
        model.addAttribute("goods_view", goods_view);
        model.addAttribute("user_viewed_goods", request.getSession(false).getAttribute("user_viewed_goods"));

        return ret;
    }


    @RequestMapping({"/goods_evaluation"})
    public String goods_evaluation(Model model,
                                         HttpServletRequest request,
                                         HttpServletResponse response,
                                         String id, String goods_id,
                                         String currentPage){
        String template = "default";
        Store store = this.storeService.findOne(CommUtil.null2Long(id));
        if (store != null){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/goods_evaluation");
        Map param = new HashMap();
        param.put("orderBy","addTime");
        param.put("orderType","desc");
        param.put("evaluate_goods_id",CommUtil.null2Long(goods_id));
        param.put("evaluate_type","goods");
        PageInfo pList = this.goodsEvaluateService.findByCondition(param,CommUtil.null2Int(currentPage),Integer.valueOf(8));
        CommUtil.saveIPageList2ModelAndView(CommUtil.getURL(request) + "/goods_evaluation", "", "", pList, model);
        model.addAttribute("storeViewTools", this.storeViewTools);
        model.addAttribute("store", store);
        GsGoodsWithBLOBs goods = this.goodsService.findOne(CommUtil.null2Long(goods_id));
        model.addAttribute("goods", goods);

        return ret;
    }

    @RequestMapping({"/goods_detail"})
    public String goods_detail(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String id, String goods_id){
        String template = "default";
        Store store = this.storeService.findOne(CommUtil.null2Long(id));
        if (store != null){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/goods_detail");
        GsGoodsWithBLOBs goods = this.goodsService.findOne(CommUtil.null2Long(goods_id));
        model.addAttribute("obj", goods);
        generic_evaluate(store, model);
//        this.userTools.query_user();

        return ret;
    }

    @RequestMapping({"/goods_order"})
    public String goods_order(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String id, String goods_id,
                              String currentPage){
        String template = "default";
        Store store = this.storeService.findOne(CommUtil.null2Long(id));
        if (store != null){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/goods_order");
        Map param = new HashMap();
        param.put("orderBy","addTime");
        param.put("orderType","desc");
        param.put("goods_id",CommUtil.null2Long(goods_id));
        param.put("order_status_gt",Integer.valueOf(20));
        PageInfo pList = this.goodsCartService.findByCondition(param,currentPage==null?1:CommUtil.null2Int(currentPage),Integer.valueOf(8));
        CommUtil.saveIPageList2ModelAndView(CommUtil.getURL(request)+"/goods_order", "", "", pList,model);
        model.addAttribute("storeViewTools", this.storeViewTools);

        return ret;
    }

    @RequestMapping({"/goods_consult"})
    public String goods_consult(Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id, String goods_id,
                                      String currentPage){
        String template = "default";
        Store store = this.storeService.findOne(CommUtil.null2Long(id));
        if (store != null){
            template = store.getTemplate();
        }
        String ret = generateViewURL(template + "/goods_consult");
        Map param = new HashMap();
        param.put("orderBy","addTime");
        param.put("orderType","desc");
        param.put("goods_id",CommUtil.null2Long(goods_id));
        PageInfo pList = this.goodsConsultService.findByCondition(param,CommUtil.null2Int(currentPage),Integer.valueOf(12));
        CommUtil.saveIPageList2ModelAndView(CommUtil.getURL(request) + "/goods_consult", "", "", pList, model);
        model.addAttribute("storeViewTools", this.storeViewTools);
        model.addAttribute("goods_id", goods_id);

        return ret;
    }

    @RequestMapping({"/goods_consult_save"})
    public String goods_consult_save(Model model,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           String goods_id, String consult_content,
                                           String consult_email, String Anonymous,
                                           String consult_code){
        String ret = generateViewURL("user/default/usercenter/success");
        String verify_code = CommUtil.null2String(request.getSession(false).getAttribute("consult_code"));
        boolean visit_consult = true;
        if (!this.systemConfigService.getConfig().getVisitorConsult()){
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (user == null){
                visit_consult = false;
            }
            if (CommUtil.null2Boolean(Anonymous)){
                visit_consult = false;
            }
        }
        if (visit_consult){
            if (CommUtil.null2String(consult_code).equals(verify_code)){
                GsGoodsConsultWithBLOBs obj = new GsGoodsConsultWithBLOBs();
                obj.setAddtime(new Date());
                obj.setConsultContent(consult_content);
                obj.setConsultEmail(consult_email);
                if (!CommUtil.null2Boolean(Anonymous)){
                    User user = (User) SecurityUtils.getSubject().getPrincipal();
                    obj.setConsultUserId(user.getId());
                    model.addAttribute("op_title", "咨询发布成功");
                }
                obj.setGoodsId(CommUtil.null2Long(goods_id));
                this.goodsConsultService.save(obj);
                request.getSession(false).removeAttribute("consult_code");
            }else{
                ret = generateViewURL("error");
                model.addAttribute("op_title", "验证码错误，咨询发布失败");
            }
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "不允许游客咨询");
        }
        model.addAttribute("url", CommUtil.getURL(request) + "/goods_" + goods_id);

        return ret;
    }

    /**
     * 根据商品规格查询商品规格价格
     * @param request
     * @param response
     * @param gsp 规格
     * @param id
     */
    @RequestMapping({"/load_goods_gsp"})
    public void load_goods_gsp(HttpServletRequest request,
                               HttpServletResponse response,
                               String gsp, String id){
        GsGoodsWithBLOBs goods = this.goodsService.findOne(CommUtil.null2Long(id));
        Map map = new HashMap();
        int count = 0;
        double price = 0.0D;
        if ((goods.getGroup() != null) && (goods.getGroupBuy() == 2)){
            for (GsGroupGoods gg : goods.getGroup_goods_list())
                if (gg.getGroupId().equals(goods.getGroup().getId())){
                    count = gg.getGgGroupCount() - gg.getGgDefCount();
                    price = CommUtil.null2Double(gg.getGgPrice());
                }
        }else{
            count = goods.getGoodsInventory();
            price = CommUtil.null2Double(goods.getStorePrice());
            if (goods.getInventoryType().equals("spec")){
                List<Map> list = JsonUtils.jsonToList(goods.getGoodsInventoryDetail(),Map.class);
                String[] gsp_ids = gsp.split(",");
                for (Map temp : list){
                    String[] temp_ids = CommUtil.null2String(temp.get("id")).split("_");
                    Arrays.sort(gsp_ids);
                    Arrays.sort(temp_ids);
                    if (Arrays.equals(gsp_ids, temp_ids)){
                        count = CommUtil.null2Int(temp.get("count"));
                        price = CommUtil.null2Double(temp.get("price"));
                    }
                }
            }
        }
        map.put("count", Integer.valueOf(count));
        map.put("price", Double.valueOf(price));
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (java.io.UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.objectToJson(map));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping({"/trans_fee"})
    public void trans_fee(HttpServletRequest request,
                          HttpServletResponse response,
                          String city_name,
                          String goods_id){
        Map map = new HashMap();
        GsGoods goods = this.goodsService.findOne(CommUtil.null2Long(goods_id));
        float mail_fee = 0.0F;
        float express_fee = 0.0F;
        float ems_fee = 0.0F;
        if (goods.getTransport() != null){
            if(goods.getTransport().getTransMailInfo() != null){
                mail_fee = this.transportTools.cal_goods_trans_fee(
                               CommUtil.null2String(goods.getTransport().getId()), "mail",
                               CommUtil.null2String(goods.getGoodsWeight()),
                               CommUtil.null2String(goods.getGoodsVolume()), city_name);
            }
            if(goods.getTransport().getTransExpressInfo() != null){
                express_fee = this.transportTools.cal_goods_trans_fee(
                                  CommUtil.null2String(goods.getTransport().getId()), "express",
                                  CommUtil.null2String(goods.getGoodsWeight()),
                                  CommUtil.null2String(goods.getGoodsVolume()), city_name);
            }
            if(goods.getTransport().getTransEmsInfo() != null){
                ems_fee = this.transportTools.cal_goods_trans_fee(
                              CommUtil.null2String(goods.getTransport().getId()), "ems",
                              CommUtil.null2String(goods.getGoodsWeight()),
                              CommUtil.null2String(goods.getGoodsVolume()), city_name);
            }
        }
        map.put("mail_fee", Float.valueOf(mail_fee));
        map.put("express_fee", Float.valueOf(express_fee));
        map.put("ems_fee", Float.valueOf(ems_fee));
        map.put("current_city_info", CommUtil.substring(city_name, 5));
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");}
        catch (java.io.UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.objectToJson(map));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping({"/goods_share"})
    public String goods_share(Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String goods_id){
        String ret = "goods_share";
        GsGoods goods = this.goodsService.findOne(CommUtil.null2Long(goods_id));
        model.addAttribute("obj", goods);

        return ret;
    }


    /***************************************************************************************
     * private 方法
     **************************************************************************************/
    /**
     * 获取user goods所有id
     * @param ugc
     * @return
     */
    private List<Long> genericUserGcIds(GsGoodsUserClass ugc){
        List ids = new ArrayList<>();
        ids.add(ugc.getId());
        for (GsGoodsUserClass child : ugc.getChilds()){
            List<Long> cids = genericUserGcIds(child);
            for (Long cid : cids){
                ids.add(cid);
            }
            ids.add(child.getId());
        }

        return ids;
    }

    /**
     *
     * @param areas
     * @return
     */
    private List<Long> getArrayAreaChildIds(List<GsArea> areas){
        List ids = new ArrayList();
        for (GsArea area : areas){
            ids.add(area.getId());
            for (GsArea are : area.getChilds()){
                List<Long> cids = getAreaChildIds(are);
                for (Long cid : cids){
                    ids.add(cid);
                }
            }
        }

        return ids;
    }

    private List<Long> genericIds(GsGoodsClass gc){
        List ids = new ArrayList();
        ids.add(gc.getId());
        for (GsGoodsClass child : gc.getChildren()){
            List<Long> cids = genericIds(child);
            for (Long cid : cids){
                ids.add(cid);
            }
            ids.add(child.getId());
        }

        return ids;
    }

    private List<Long> getAreaChildIds(GsArea area){
        List ids = new ArrayList();
        ids.add(area.getId());
        for (GsArea are : area.getChilds()){
            List<Long> cids = getAreaChildIds(are);
            for (Long cid : cids){
                ids.add(cid);
            }
        }

        return ids;
    }

    private List<List<GsGoodsSpecProperty>> generic_gsp(String gs_ids){
        List<List<GsGoodsSpecProperty>> list = new ArrayList<List<GsGoodsSpecProperty>>();
        String[] gs_id_list = gs_ids.substring(1).split("\\|");
        for (String gd_id_info : gs_id_list){
            String[] gs_info_list = gd_id_info.split(",");
            GsGoodsSpecProperty gsp = this.goodsPropertyService.findOne(CommUtil.null2Long(gs_info_list[0]));
            boolean create = true;
            for (List<GsGoodsSpecProperty> gsp_list : list){
                for (GsGoodsSpecProperty gsp_temp : gsp_list){
                    if (gsp_temp.getSpec().getId()
                            .equals(gsp.getSpec().getId())){
                        gsp_list.add(gsp);
                        create = false;
                        break;
                    }
                }
            }
            if (create){
                List gsps = new ArrayList();
                gsps.add(gsp);
                list.add(gsps);
            }
        }

        return list;
    }

    /****************************************
     * 计算evaluate数值
     * @param store
     * @param model
     ****************************************/
    private void generic_evaluate(Store store, Model model){
        double description_result = 0.0D;
        double service_result = 0.0D;
        double ship_result = 0.0D;
        if (store.getScId() != null){
            GsStoreClass sc = this.storeClassService.findOne(store.getScId());
            float description_evaluate = CommUtil.null2Float(sc.getDescriptionEvaluate());
            float service_evaluate = CommUtil.null2Float(sc.getServiceEvaluate());
            float ship_evaluate = CommUtil.null2Float(sc.getShipEvaluate());
            if (store.getPoint() != null){
                float store_description_evaluate =
                        CommUtil.null2Float(store.getPoint().getDescriptionEvaluate());
                float store_service_evaluate =
                        CommUtil.null2Float(store.getPoint().getServiceEvaluate());
                float store_ship_evaluate =
                        CommUtil.null2Float(store.getPoint().getShipEvaluate());

                description_result = CommUtil.div(
                        Float.valueOf(store_description_evaluate - description_evaluate),
                        Float.valueOf(description_evaluate));
                service_result = CommUtil.div(Float.valueOf(
                        store_service_evaluate - service_evaluate),
                        Float.valueOf(service_evaluate));
                ship_result = CommUtil.div(
                        Float.valueOf(store_ship_evaluate - ship_evaluate),
                        Float.valueOf(ship_evaluate));
            }
        }
        if (description_result > 0.0D){
            model.addAttribute("description_css", "better");
            model.addAttribute("description_type", "高于");
            model.addAttribute("description_result",
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(description_result), Integer.valueOf(100)))) +
                            "%");
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




