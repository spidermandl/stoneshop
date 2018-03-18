package org.goshop.portal.controller;

import org.apache.shiro.SecurityUtils;
import org.goshop.assets.i.AccessoryService;
import org.goshop.assets.pojo.GsAccessory;
import org.goshop.common.service.SysConfig;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.HttpInclude;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.order.i.GoodsCartService;
import org.goshop.order.i.StoreCartService;
import org.goshop.order.pojo.GsGoodsCart;
import org.goshop.order.pojo.GsStoreCart;
import org.goshop.shiro.Constants;
import org.goshop.shiro.tag.ShiroTagFreeMarkerConfigurer;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.pojo.Store;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 16/01/2018.
 */
abstract public class BaseController {

    @Autowired
    protected SystemConfigService systemConfigService;
    @Autowired
    protected StoreJoinService storeJoinService;
    @Autowired
    protected StoreCartService storeCartService;
    @Autowired
    protected GoodsCartService goodsCartService;
    @Autowired
    protected GoodsService goodsService;

    abstract protected String rootTemplatePath();
//    @Autowired
//    protected FreeMarkerConfigurer freemarkerConfig;
    /**
     * 加入model参数
     * @param model
     * @param request
     * @param response
     */
    protected void reCapsuleModel(Model model, HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user!=null) {
            model.addAttribute("user", user);
//        (User)request.getAttribute(Constants.CURRENT_USER);
            model.addAttribute("user_id", user.getId());
        }
        model.addAttribute("config",systemConfigService.getConfig());
        model.addAttribute("CommUtil",new CommUtil());
        model.addAttribute("httpInclude", new HttpInclude(request, response));
        model.addAttribute("domainPath", CommUtil.generic_domain(request));
        model.addAttribute("imageWebServer",null);//静态资源服务器地址
    }
    /***
     * 获取ftl模板文件名
     * @param viewName
     * @return
     */
    protected String generateViewURL(String viewName){
        String lang = systemConfigService.getConfig().getSysLanguage();
        if ( lang != null){
            if (lang.equals("zh_cn")){
                return rootTemplatePath() + viewName;
            }else{
                return rootTemplatePath() + viewName;
            }
        }else{
            return rootTemplatePath() + viewName;
        }
    }

    /**
     * 获取用户在商店的购物车
     * @param request
     * @return
     */
    protected List<GsStoreCart> cart_calc(HttpServletRequest request){
        List<GsStoreCart> cart = new ArrayList<>();
        List<GsStoreCart> user_cart = new ArrayList<>();
        List<GsStoreCart> cookie_cart = new ArrayList<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Store store = user==null?null:this.storeJoinService.getCurrentStore(user);
        String cart_session_id = "";
        Map params = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("cart_session_id")){
                    cart_session_id = CommUtil.null2String(cookie.getValue());
                }
            }
        }
        if (user != null){// 买家已登录
            if (!cart_session_id.equals("")){
                if (store != null){
                    params.clear();
                    params.put("cart_session_id", cart_session_id);
                    params.put("user_id", user.getId());
                    params.put("sc_status", Integer.valueOf(0));
                    params.put("store_id", store.getStoreId());
                    List<GsStoreCart> store_cookie_cart = this.storeCartService.findByCondition(params);
//                    "select obj from StoreCart obj where (obj.cart_session_id=:cart_session_id or obj.user.id=:user_id) and obj.sc_status=:sc_status and obj.store.id=:store_id",
                    for (GsStoreCart sc : store_cookie_cart){
                        // sc = (StoreCart)localIterator1.next();
                        for (GsGoodsCart gc : sc.getGcs()){
                            this.goodsCartService.delete(gc.getId());
                        }
                        this.storeCartService.delete(sc.getId());
                    }
                }

                params.clear();
                params.put("cart_session_id", cart_session_id);
                params.put("sc_status", Integer.valueOf(0));
                cookie_cart = this.storeCartService.findByCondition(params);
//                "select obj from StoreCart obj where obj.cart_session_id=:cart_session_id and obj.sc_status=:sc_status",

                params.clear();
                params.put("user_id", user.getId());
                params.put("sc_status", Integer.valueOf(0));
                user_cart = this.storeCartService.findByCondition(params);
//                        "select obj from StoreCart obj where obj.user.id=:user_id and obj.sc_status=:sc_status"

            }else{
                params.clear();
                params.put("user_id", user.getId());
                params.put("sc_status", Integer.valueOf(0));
                user_cart = this.storeCartService.findByCondition(params);
//                        "select obj from StoreCart obj where obj.user.id=:user_id and obj.sc_status=:sc_status"
            }

        }else if (!cart_session_id.equals("")){// 买家未登录
            params.clear();
            params.put("cart_session_id", cart_session_id);
            params.put("sc_status", Integer.valueOf(0));
            cookie_cart = this.storeCartService.findByCondition(params);
//                    "select obj from StoreCart obj where obj.cart_session_id=:cart_session_id and obj.sc_status=:sc_status",
        }

        // 遍历买家登录状态下的购物车，并将购物车内容添加到cart对象里
        for (GsStoreCart sc : user_cart){
            boolean sc_add = true;
            for (GsStoreCart sc1 : cart){
                if (sc1.getStoreId().equals(sc.getStoreId())){
                    sc_add = false;
                }
            }
            if (sc_add){
                cart.add(sc);
            }
        }
        // 遍历买家未登录状态下的购物车，并将购物车内容添加到cart对象里
        for (GsStoreCart sc : cookie_cart){
            boolean sc_add = true;
            for (GsStoreCart sc1 : cart){
                if (sc1.getStoreId().equals(sc.getStoreId())){
                    sc_add = false;
                    for (GsGoodsCart gc : sc.getGcs()){
                        gc.setScId(sc.getId());
                        this.goodsCartService.update(gc);
                    }
                    this.storeCartService.delete(sc.getId());
                }
            }
            if (sc_add){
                cart.add(sc);
                Map param = new HashMap();
                param.put("sc_id",sc.getId());
                sc.setGcs(this.goodsCartService.findByCondition(param));
            }
        }

        return cart;
    }

    /**
     * 获取所有具体购物
     * @param cart
     * @return
     */
    protected List<GsGoodsCart> cart_calc(List<GsStoreCart> cart){
        List<GsGoodsCart> list = new ArrayList<>();
        if(cart != null){
            for(GsStoreCart sc : cart){
                if(sc != null){
                    list.addAll(sc.getGcs());
                }
            }
        }
        for (GsGoodsCart ggc:list){
            GsGoodsWithBLOBs goods = this.goodsService.findBasicOne(ggc.getGoodsId());
            ggc.setGoods(goods);
        }

        return list;
    }
}
