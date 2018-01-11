package org.goshop.portal.controller;

import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.i.GoodsUserClassService;
import org.goshop.store.i.StoreClassService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.GsStoreClass;
import org.goshop.store.pojo.Store;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.User;
import org.goshop.tools.service.AreaViewTools;
import org.goshop.tools.service.GoodsViewTools;
import org.goshop.tools.service.StoreViewTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 27/12/2017.
 * 显示店铺完整信息
 */
@Controller
@RequestMapping(value = "/store")
public class StoreLookingController {

    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    StoreService storeService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsUserClassService goodsUserClassService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    StoreClassService storeClassService;

    @Autowired
    GoodsViewTools goodsViewTools;
    @Autowired
    StoreViewTools storeViewTools;
    @Autowired
    AreaViewTools areaViewTools;

    @RequestMapping({"/index"})
    public String index(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String id){
        String ret = "";
        String serverName = request.getServerName().toLowerCase();
        Store store = null;
        if ((id == null) &&
                (serverName.indexOf(".") >= 0) &&
                (serverName.indexOf(".") != serverName.lastIndexOf(".")) &&
                (this.systemConfigService.getConfig().getSecond_domain_open())){
            String secondDomain = serverName.substring(0,serverName.indexOf("."));
            store = this.storeService.findBySecondDomain(secondDomain);
        }else{
            store = this.storeService.findOne(CommUtil.null2Long(id));
        }
        if (store == null){
            ret ="error";
            model.addAttribute("op_title", "不存在该店铺信息");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
            return "store/"+ret;
        }
        String template = "default";
        if ((store.getTemplate() != null) && (!store.getTemplate().equals(""))){
            template = store.getTemplate();
        }
        ret = "/store_index";
        if (store.getStoreState() == 2){
            add_store_common_info(model, store);
            model.addAttribute("store", store);
            model.addAttribute("nav_id", "store_index");
        }else{
            ret= "error";
            model.addAttribute("op_title", "店铺已经关闭或者未开通店铺");
            model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");
        }
        generic_evaluate(store, model);

        return "store/"+ret;
    }

    private void add_store_common_info(Model model, Store store){
        Map params = new HashMap();
        User user = userService.findOne(store.getMemberId());
        params.put("user_id", userService.findOne(store.getMemberId()));
        params.put("display", Boolean.valueOf(true));
        List ugcs = this.goodsUserClassService.findByUserIdAndParentId(user.getId(),null,true);
        model.addAttribute("ugcs", ugcs);
        params.clear();
        params.put("goods_recommend", Boolean.valueOf(true));
        params.put("goods_store_id", store.getStoreId());
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "addTime");
        params.put("orderType", "desc");
        List goods_recommend = this.goodsService.findByCondition(params,1,8).getList();
        params.clear();
        params.put("goods_store_id", store.getStoreId());
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "addTime");
        params.put("orderType", "desc");
        List goods_new = this.goodsService.findByCondition(params,1,12).getList();

        model.addAttribute("goods_recommend", goods_recommend);
        model.addAttribute("goods_new", goods_new);
        model.addAttribute("goodsViewTools", this.goodsViewTools);
        model.addAttribute("storeViewTools", this.storeViewTools);
        model.addAttribute("areaViewTools", this.areaViewTools);
    }

    private void generic_evaluate(Store store, Model model){
        double description_result = 0.0D;
        double service_result = 0.0D;
        double ship_result = 0.0D;
        if ((store != null) && (store.getScId() != null) && (store.getPoint() != null)){
            GsStoreClass sc = this.storeClassService.findOne(Long.parseLong(store.getScId().toString()));
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
                    CommUtil.null2String(Double.valueOf(CommUtil.mul(Double.valueOf(description_result),
                            Integer.valueOf(100)) > 100.0D ?
                            100.0D :
                            CommUtil.mul(Double.valueOf(description_result), Integer.valueOf(100)))) + "%");
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
