package org.goshop.portal.controller;

import org.apache.shiro.SecurityUtils;
import org.goshop.common.service.SysConfig;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsBrandService;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.i.GoodsFloorService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.pojo.*;
import org.goshop.order.i.GoodsCartService;
import org.goshop.order.i.StoreCartService;
import org.goshop.order.pojo.GsGoodsCart;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreService;
import org.goshop.order.pojo.GsStoreCart;
import org.goshop.store.pojo.Store;
import org.goshop.users.i.MessageService;
import org.goshop.users.pojo.User;
import org.goshop.tools.service.GoodsFloorViewTools;
import org.goshop.tools.service.GoodsViewTools;
import org.goshop.tools.service.NavViewTools;
import org.goshop.tools.service.StoreViewTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 商城首页控制器
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsClassService goodsClassService;
    @Autowired
    private GoodsBrandService goodsBrandService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private GoodsFloorService goodsFloorService;


    @Autowired
    private GoodsViewTools goodsViewTools;
    @Autowired
    private StoreViewTools storeViewTools;
    @Autowired
    private NavViewTools navViewTools;
    @Autowired
    private GoodsFloorViewTools gf_tools;

//    @Autowired
//    private IPartnerService partnerService;
//
//    @Autowired
//    private IRoleService roleService;
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IArticleClassService articleClassService;
//
//    @Autowired
//    private IArticleService articleService;
//
//    @Autowired
//    private IAccessoryService accessoryService;
//
//    @Autowired
//    private INavigationService navigationService;
//
//    @Autowired
//    private IGroupGoodsService groupGoodsService;
//
//    @Autowired
//    private IGroupService groupService;
//
//    @Autowired
//    private IBargainGoodsService bargainGoodsService;
//
//    @Autowired
//    private IDeliveryGoodsService deliveryGoodsService;
//
//    @Autowired
//    private MsgTools msgTools;

    /**
     * 页面最上面部分
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/top" })
    public String top(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("top");
        List msgs = new ArrayList();
        if(user != null){
            Map params = new HashMap();
            params.put("status", Integer.valueOf(0));
            params.put("parent_id",null);
            params.put("reply_status", Integer.valueOf(1));
            params.put("fromUser_id", user.getId());
            params.put("toUser_id", user.getId());
            msgs = this.messageService.findBySelfRelated(params);
        }
        Store store = null;
        if(user != null) {
            store = this.storeJoinService.getCurrentStore(user);
            store = this.storeService.findOne(store.getStoreId());
        }
        model.addAttribute("store", store);
        model.addAttribute("navTools", this.navViewTools);
        model.addAttribute("msgs", msgs);
        List<GsStoreCart> cart = cart_calc(request);
        List<GsGoodsCart> list = cart_calc(cart);

        float total_price = 0.0F;
        for(GsGoodsCart gc : list){
            GsGoods goods = this.goodsService.findOne(gc.getGoodsId());
            if(CommUtil.null2String(gc.getCartType()).equals("combin"))
                total_price = CommUtil.null2Float(goods.getCombinPrice());
            else{
                total_price = CommUtil.null2Float(
                        Double.valueOf(CommUtil.mul(Integer.valueOf(gc.getCount()), goods.getGoodsCurrentPrice()))) + total_price;
            }
        }

        model.addAttribute("total_price", Float.valueOf(total_price));
        model.addAttribute("cart_goods", list);

        return ret;
    }
    /**
     * 横着的导航栏
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/nav" })
    public String nav(Model model,
                      HttpServletRequest request,
                      HttpServletResponse response){
        String ret = generateViewURL("nav");
        List<GsGoodsClass> gcs =
                this.goodsClassService.findByParentIdAndDisplay(null,Boolean.valueOf(true),"sequence","asc",1,14).getList();
        model.addAttribute("gcs", gcs);
        model.addAttribute("navTools", this.navViewTools);

        return ret;
    }

    @RequestMapping({ "/nav1" })
    public String nav1(Model model,
                       HttpServletRequest request,
                       HttpServletResponse response){
        String ret = generateViewURL("nav1");
        List<GsGoodsClass> gcs =
                this.goodsClassService.findByParentIdAndDisplay(null,Boolean.valueOf(true),"sequence","asc",1,14).getList();
        model.addAttribute("gcs", gcs);
        model.addAttribute("navTools", this.navViewTools);

        return ret;
    }

    @RequestMapping({ "/head" })
    public String head(Model model,
                       HttpServletRequest request,
                       HttpServletResponse response){
        String ret = generateViewURL("head");
        String type = CommUtil.null2String(request.getAttribute("type"));
        model.addAttribute("type", type.equals("") ? "goods" : type);

        return ret;
    }

    @RequestMapping({ "/login_head" })
    public String login_head(HttpServletRequest request, HttpServletResponse response){
        String ret = "login_head";
        return ret;
    }

    @RequestMapping({ "/floor" })
    public String floor(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
        String ret = generateViewURL("floor");
        Map params = new HashMap();
        params.put("gf_display", Boolean.valueOf(true));
        params.put("orderBy","gf_sequence");
        params.put("orderType","asc");
        List floors = this.goodsFloorService.findByCondition(params);
//                "select obj from GoodsFloor obj where obj.gf_display=:gf_display and obj.parent.id is null order by obj.gf_sequence asc", params, -1, -1);
        model.addAttribute("floors", floors);
        model.addAttribute("gf_tools", this.gf_tools);
        model.addAttribute("url", CommUtil.getURL(request));

        return ret;
    }

    @RequestMapping({"/floor_ajax"})
    public void floorAjax(HttpServletRequest request, HttpServletResponse response, Long id, Integer count){
        Map params = new HashMap();
        params.put("gf_display", Boolean.valueOf(true));
        params.put("id", id);
        params.put("orderBy","gf_sequence");
        params.put("orderType","asc");
        List floors = this.goodsFloorService.findByCondition(params);
//                "select obj from GoodsFloor obj where obj.gf_display=:gf_display and obj.parent.id is null and obj.id=:id order by obj.gf_sequence asc", params, -1, -1);
        Map<String, Object> map = new HashMap<String, Object>();
        CommUtil.saveWebPaths(map, this.systemConfigService.getConfig(), request);
        String ret = showLoadFloorAjaxHtml(floors, count, CommUtil.getURL(request), map);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (java.io.UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(ret);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping({ "/footer" })
    public String footer(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response){
        String ret = generateViewURL("footer");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/footer");
        }
        model.addAttribute("navTools", this.navViewTools);

        return ret;
    }

    /**
     * PC首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/index" })
    public String index(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("index");
        //设置为PC访问
        request.getSession().setAttribute("wemall_view_type", "pc");
        List gcs = this.goodsClassService.findByParentIdAndDisplay(null,Boolean.valueOf(true),"sequence","asc",1,15).getList();

        model.addAttribute("gcs", gcs);
        // 推荐品牌
        Map params = new HashMap();
        params.put("audit", Integer.valueOf(1));
        params.put("recommend", Boolean.valueOf(true));
//        List gbs = this.goodsBrandService.query("select obj from GoodsBrand obj where obj.audit=:audit and obj.recommend=:recommend order by obj.sequence", params, 0, 4);
//        model.addAttribute("gbs", gbs);
        // 底部显示的合作伙伴
        params.clear();
//        List img_partners = this.partnerService.query("select obj from Partner obj where obj.image.id is not null order by obj.sequence asc", params, -1, -1);
//        model.addAttribute("img_partners", img_partners);
//        List text_partners = this.partnerService.query("select obj from Partner obj where obj.image.id is null order by obj.sequence asc", params, -1, -1);
//        model.addAttribute("text_partners", text_partners);
        // 底部新闻分类显示
        params.clear();
        params.put("mark", "news");
        //List acs = this.articleClassService.query("select obj from ArticleClass obj where obj.parent.id is null and obj.mark!=:mark order by obj.sequence asc", params, 0, 9);
        //model.addAttribute("acs", acs);
        // 商城新闻
        params.clear();
        params.put("class_mark", "news");
        params.put("display", Boolean.valueOf(true));
        //List articles = this.articleService.query("select obj from Article obj where obj.articleClass.mark=:class_mark and obj.display=:display order by obj.addTime desc", params, 0, 5);
        //model.addAttribute("articles", articles);
        // 查询推荐店铺商品
        params.clear();
        params.put("store_recommend", Boolean.valueOf(true));
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "store_recommend_time");
        params.put("orderType", "desc");
        List store_reommend_goods_list = this.goodsService.findByCondition(params);
//                "select obj from Goods obj where obj.store_recommend=:store_recommend and obj.goods_status=:goods_status order by obj.store_recommend_time desc", params, -1, -1);
        List store_reommend_goods = new ArrayList();
        int max = store_reommend_goods_list.size() >= 5 ? 4 : store_reommend_goods_list.size() - 1;
        for(int i = 0; i <= max; i++){
            store_reommend_goods.add(store_reommend_goods_list.get(i));
        }
        // 1、推荐商品可在后台编辑
        model.addAttribute("store_reommend_goods", store_reommend_goods);
        model.addAttribute("store_reommend_goods_count", Double.valueOf(Math.ceil(CommUtil.div(Integer.valueOf(store_reommend_goods_list.size()), Integer.valueOf(5)))));
        model.addAttribute("goodsViewTools", this.goodsViewTools);
        model.addAttribute("storeViewTools", this.storeViewTools);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
            model.addAttribute("user", user);
        }
        // 团购查询
        params.clear();
        params.put("beginTime", new Date());
        params.put("endTime", new Date());
//        List groups = this.groupService.query("select obj from Group obj where obj.beginTime<=:beginTime and obj.endTime>=:endTime", params, -1, -1);
//        if(groups.size() > 0){
//            // 2、团购商品
//            params.clear();
//            params.put("gg_status", Integer.valueOf(1));
//            params.put("gg_recommend", Integer.valueOf(1));
//            params.put("group_id", ((Group)groups.get(0)).getId());
//            List ggs = this.groupGoodsService.query("select obj from GroupGoods obj where obj.gg_status=:gg_status and obj.gg_recommend=:gg_recommend and obj.group.id=:group_id order by obj.gg_recommend_time desc", params, 0, 5);
//            //if(ggs.size() > 0)
//            model.addAttribute("ggs", ggs);
//        }
        // 3、天天特价
        params.clear();
        params.put("bg_time", CommUtil.formatDate(CommUtil.formatShortDate(new Date())));
        params.put("bg_status", Integer.valueOf(1));
        //List bgs = this.bargainGoodsService.query("select obj from BargainGoods obj where obj.bg_time=:bg_time and obj.bg_status=:bg_status", params, 0, 5);
        //model.addAttribute("bgs", bgs);
        // 4、热销商品倒序-疯狂抢购
        params.clear();
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "goods_salenum");
        params.put("orderType", "desc");
        List list = this.goodsService.findByCondition(params,1,5).getList();
//                "select obj from Goods obj where obj.goods_status=:goods_status order by obj.goods_salenum desc", params, 0, 5);
        model.addAttribute("fengKuangs", list);

        // 5、随机生成推荐商品 猜您喜欢
        List store_guess_goods = new ArrayList();
        Random rand = new Random();
        int recommend_goods_random = rand.nextInt(5);
        int begin = recommend_goods_random * 5;
        if(begin > store_reommend_goods_list.size() - 1){
            begin = 0;
        }
        int maxsize = begin + 5;
        if(maxsize > store_reommend_goods_list.size()){
            begin -= maxsize - store_reommend_goods_list.size();
            maxsize--;
        }
        for(int i = 0; i < store_reommend_goods_list.size(); i++){
            if((i >= begin) && (i < maxsize)){
                store_guess_goods.add(store_reommend_goods_list.get(i));
            }
        }
        model.addAttribute("cais", store_guess_goods);
        // 6、满送商品
        params.clear();
        params.put("d_status", Integer.valueOf(1));
        params.put("d_begin_time", new Date());
        params.put("d_end_time", new Date());
        //List dgs = this.deliveryGoodsService.query("select obj from DeliveryGoods obj where obj.d_status=:d_status and obj.d_begin_time<=:d_begin_time and obj.d_end_time>=:d_end_time order by obj.d_audit_time desc", params, 0, 5);
        //model.addAttribute("dgs", dgs);

        // 7、新品上架
        params.clear();
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "addTime");
        params.put("orderType", "desc");
        List new_goods_list = this.goodsService.findByCondition(params,1,5).getList();
//                "select obj from Goods obj where obj.goods_status=:goods_status order by obj.addTime desc", params, 0, 5);
        model.addAttribute("xinShangs", new_goods_list);

        // 8、点击数最多:人气商品
        params.clear();
        params.put("goods_status", Integer.valueOf(0));
        List hot_goods_list = this.goodsService.findByCondition(params,1,5).getList();
//            "select obj from Goods obj where obj.goods_status=:goods_status order by obj.goods_click desc", params, 0, 5);
        model.addAttribute("hots", hot_goods_list);

        return ret;
    }

    @RequestMapping({ "/close.htm" })
    public String wapclose(HttpServletRequest request, HttpServletResponse response){
        String ret = "close.html";
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))) {
            ret = "wap/close.html";
        }

        return ret;
    }

    @RequestMapping({ "/404" })
    public String error404(Model model ,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        String ret = "404.html";
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            //String store_id = CommUtil.null2String(request.getSession(false).getAttribute("store_id"));
            ret = "wap/404.html";
            model.addAttribute("url", CommUtil.getURL(request) + "/wap/index.htm");
        }

        return ret;
    }

    @RequestMapping({ "/500" })
    public String error500(Model model,
                           HttpServletRequest request,
                           HttpServletResponse response){
        String ret = "500.html";
        String wemall_view_type = CommUtil.null2String(request.getSession(false).getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            String store_id = CommUtil.null2String(request.getSession(false).getAttribute("store_id"));
            ret = "wap/500.html";
            model.addAttribute("url", CommUtil.getURL(request) + "/wap/index.htm?store_id=" + store_id);
        }

        return ret;
    }

    @RequestMapping({ "/goods_class" })
    public String goods_class(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response){
        String ret = "goods_class.html";
        String wemall_view_type = CommUtil.null2String(request.getSession(false).getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = "wap/goods_class.html";
        }
        Map params = new HashMap();
        params.put("display", Boolean.valueOf(true));
        params.put("orderBy", "sequence");
        params.put("orderType", "asc");
        List gcs = this.goodsClassService.findByCondition(params);
//                "select obj from GoodsClass obj where obj.parent.id is null and obj.display=:display order by obj.sequence asc", params, -1, -1);
        model.addAttribute("gcs", gcs);

        return ret;
    }

    /**
     * 商品分类
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/goodsclass" })
    public String goodsclass(Model model,HttpServletRequest request, HttpServletResponse response){
        String ret = "goodsclass";
        String wemall_view_type = CommUtil.null2String(request.getSession(false).getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = "wap/goodsclass";
        }
        Map params = new HashMap();
        params.put("orderBy", "sequence");
        params.put("orderType", "asc");
        List gcs = this.goodsClassService.findByCondition(params);
//                "select obj from GoodsClass obj where obj.parent.id is null  order by obj.sequence asc", params, -1, -1);
        model.addAttribute("gcs", gcs);

        return ret;
    }

    @RequestMapping({ "/forget" })
    public String forget(Model model,HttpServletRequest request, HttpServletResponse response){
        String ret = "forget";
        SysConfig config = this.systemConfigService.getConfig();
        if(!config.getEmailEnable()){
            ret = "error";
            model.addAttribute("op_title", "系统关闭邮件功能，不能找回密码");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    @RequestMapping({ "/find_pws" })
    public String find_pws(User user,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             String userName,
                             String email,
                             String code){
        String ret = "success";
        HttpSession session = request.getSession(false);
        String verify_code = (String)session.getAttribute("verify_code");
        if(code.toUpperCase().equals(verify_code)){
//            if(user.getEmail().equals(email.trim())){
//                String pws = CommUtil.randomString(6).toLowerCase();
//                String subject = this.systemConfigService.getConfig().getTitle() + "密码找回邮件";
//                String content = user.getUserName() + ",您好！您通过密码找回功能重置密码，新密码为：" + pws;
//                boolean r = this.msgTools.sendEmail(email, subject, content);
//                if(r){
//                    user.setPassword(Md5Encrypt.md5(pws));
//                    this.userService.update(user);
//                    model.addAttribute("op_title", "新密码已经发送到邮箱:<font color=red>" + email + "</font>，请查收后重新登录");
//                    model.addAttribute("url", CommUtil.getURL(request) + "/user/login.htm");
//                }else{
//                    ret = "error";
//                    model.addAttribute("op_title", "邮件发送失败，密码暂未执行重置");
//                    model.addAttribute("url", CommUtil.getURL(request) + "/forget.htm");
//                }
//            }else{
//                ret = "error";
//                model.addAttribute("op_title", "用户名、邮箱不匹配");
//                model.addAttribute("url", CommUtil.getURL(request) + "/forget.htm");
//            }
        }else{
            ret = "error";
            model.addAttribute("op_title", "验证码不正确");
            model.addAttribute("url", CommUtil.getURL(request) + "/forget");
        }

        return ret;
    }

    @RequestMapping({ "/switch_recommend_goods" })
    public String switch_recommend_goods(@CurrentUser User user,
                                         Model model,
                                         HttpServletRequest request,
                                         HttpServletResponse response,
                                         int recommend_goods_random){
        String ret = "switch_recommend_goods";
        Map params = new HashMap();
        params.put("store_recommend", Boolean.valueOf(true));
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "store_recommend_time");
        params.put("orderType", "desc");
        List store_reommend_goods_list = this.goodsService.findByCondition(params);
//                "select obj from Goods obj where obj.store_recommend=:store_recommend and obj.goods_status=:goods_status order by obj.store_recommend_time desc", params, -1, -1);
        List store_reommend_goods = new ArrayList();
        int begin = recommend_goods_random * 5;
        if(begin > store_reommend_goods_list.size() - 1){
            begin = 0;
        }
        int max = begin + 5;
        if(max > store_reommend_goods_list.size()){
            begin -= max - store_reommend_goods_list.size();
            max--;
        }
        for(int i = 0; i < store_reommend_goods_list.size(); i++){
            if((i >= begin) && (i < max)){
                store_reommend_goods.add(store_reommend_goods_list.get(i));
            }
        }
        model.addAttribute("store_reommend_goods", store_reommend_goods);

        return ret;
    }

    @RequestMapping({ "/outline" })
    public String outline(Model model,HttpServletRequest request, HttpServletResponse response){
        String ret = "error";
        model.addAttribute("op_title", "该用户在其他地点登录，您被迫下线！");
        model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = "wap/error";
            model.addAttribute("url", CommUtil.getURL(request) + "/wap/index");
        }

        return ret;
    }

    /**
     * wap首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/wap/index" })
    public String wapindex(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        String ret ="wap/index";
        //设置为wap访问
        request.getSession().setAttribute("wemall_view_type", "wap");
        List gcs = this.goodsClassService.findByParentIdAndDisplay(null,Boolean.valueOf(true),"sequence","asc",1,15).getList();
        model.addAttribute("gcs", gcs);
        Map params = new HashMap();
        params.put("audit", Integer.valueOf(1));
        params.put("recommend", Boolean.valueOf(true));
//        List gbs = this.goodsBrandService.query("select obj from GoodsBrand obj where obj.audit=:audit and obj.recommend=:recommend order by obj.sequence", params, -1, -1);
//        model.addAttribute("gbs", gbs);
//        params.clear();
//        List img_partners = this.partnerService.query("select obj from Partner obj where obj.image.id is not null order by obj.sequence asc", params, -1, -1);
//        model.addAttribute("img_partners", img_partners);
//        List text_partners = this.partnerService.query("select obj from Partner obj where obj.image.id is null order by obj.sequence asc", params, -1, -1);
//        model.addAttribute("text_partners", text_partners);
//        params.clear();
//        params.put("mark", "news");
//        List acs = this.articleClassService.query("select obj from ArticleClass obj where obj.parent.id is null and obj.mark!=:mark order by obj.sequence asc", params, 0, 9);
//        model.addAttribute("acs", acs);
        params.clear();
        params.put("store_recommend", Boolean.valueOf(true));
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy", "store_recommend_time");
        params.put("orderType", "desc");
        List store_reommend_goods_list = this.goodsService.findByCondition(params,1,6).getList();
//                "select obj from Goods obj where obj.store_recommend=:store_recommend and obj.goods_status=:goods_status order by obj.store_recommend_time desc", params, 0, 6
        List store_reommend_goods = new ArrayList();
        int max = store_reommend_goods_list.size() >= 21 ? 20 : store_reommend_goods_list.size() - 1;
        for(int i = 0; i <= max; i++){
            store_reommend_goods.add(store_reommend_goods_list.get(i));
        }
        model.addAttribute("store_reommend_goods", store_reommend_goods);

        model.addAttribute("store_reommend_goods_count", Double.valueOf(Math.ceil(CommUtil.div(Integer.valueOf(store_reommend_goods_list.size()), Integer.valueOf(5)))));
        model.addAttribute("goodsViewTools", this.goodsViewTools);
        model.addAttribute("storeViewTools", this.storeViewTools);
        if(user != null){
            model.addAttribute("user", user);
        }
        params.clear();
        params.put("beginTime", new Date());
        params.put("endTime", new Date());
//        List groups = this.groupService.query("select obj from Group obj where obj.beginTime<=:beginTime and obj.endTime>=:endTime", params, -1, -1);
//        if(groups.size() > 0){
//            params.clear();
//            params.put("gg_status", Integer.valueOf(1));
//            params.put("gg_recommend", Integer.valueOf(1));
//            params.put("group_id", ((Group)groups.get(0)).getId());
//            List ggs = this.groupGoodsService.query("select obj from GroupGoods obj where obj.gg_status=:gg_status and obj.gg_recommend=:gg_recommend and obj.group.id=:group_id order by obj.gg_recommend_time desc", params, 0, 1);
//            if(ggs.size() > 0)
//                model.addAttribute("group", ggs.get(0));
//        }
        params.clear();
        params.put("bg_time", CommUtil.formatDate(CommUtil.formatShortDate(new Date())));
        params.put("bg_status", Integer.valueOf(1));
        //List bgs = this.bargainGoodsService.query("select obj from BargainGoods obj where obj.bg_time=:bg_time and obj.bg_status=:bg_status", params, 0, 5);
        //model.addAttribute("bgs", bgs);
        params.clear();
        params.put("d_status", Integer.valueOf(1));
        params.put("d_begin_time", new Date());
        params.put("d_end_time", new Date());
        //List dgs = this.deliveryGoodsService.query("select obj from DeliveryGoods obj where obj.d_status=:d_status and obj.d_begin_time<=:d_begin_time and obj.d_end_time>=:d_end_time order by obj.d_audit_time desc", params, 0, 3);
        //model.addAttribute("dgs", dgs);

        List msgs = new ArrayList();
        if(user != null){
            params.clear();
            params.put("status", Integer.valueOf(0));
            params.put("reply_status", Integer.valueOf(1));
            params.put("from_user_id", user.getId());
            params.put("to_user_id", user.getId());
            //msgs = this.messageService.query("select obj from Message obj where obj.parent.id is null and (obj.status=:status and obj.toUser.id=:to_user_id) or (obj.reply_status=:reply_status and obj.fromUser.id=:from_user_id) ", params, -1, -1);
        }
        Store store = null;
        if(user != null) {
            store = this.storeService.getCurrentStore(user);
        }
        model.addAttribute("store", store);
        model.addAttribute("navTools", this.navViewTools);
        model.addAttribute("msgs", msgs);
        List<GsGoodsCart> list = new ArrayList<>();
        List<GsStoreCart> cart = new ArrayList<>();
        List<GsStoreCart> user_cart = new ArrayList<>();
        List<GsStoreCart> cookie_cart = new ArrayList<>();

        String cart_session_id = "";
        params.clear();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("cart_session_id")){
                    cart_session_id = CommUtil.null2String(cookie.getValue());
                }
            }
        }
        if(user != null){
            if(!cart_session_id.equals("")){
                if(store != null){
                    params.clear();
                    params.put("cart_session_id", cart_session_id);
                    params.put("user_id", user.getId());
                    params.put("sc_status", Integer.valueOf(0));
                    params.put("store_id", store.getStoreId());
                    List<GsStoreCart> store_cookie_cart = this.storeCartService.findOwnCartByCondition(params);
//                            "select obj from StoreCart obj where (obj.cart_session_id=:cart_session_id or obj.user.id=:user_id) and obj.sc_status=:sc_status and obj.store.id=:store_id", params, -1, -1);
                    for(GsStoreCart sc : store_cookie_cart){
                        for(GsGoodsCart gc : sc.getGcs()){
                            this.goodsCartService.delete(gc.getId());
                        }
                        this.storeCartService.delete(sc.getId());
                    }
                }

                params.clear();
                params.put("cart_session_id", cart_session_id);
                params.put("sc_status", Integer.valueOf(0));
                cookie_cart = this.storeCartService.findOwnCartByCondition(params);
//                        query("select obj from StoreCart obj where obj.cart_session_id=:cart_session_id and obj.sc_status=:sc_status", params, -1, -1);

                params.clear();
                params.put("user_id", user.getId());
                params.put("sc_status", Integer.valueOf(0));
                user_cart = this.storeCartService.findOwnCartByCondition(params);
//                        query("select obj from StoreCart obj where obj.user.id=:user_id and obj.sc_status=:sc_status", params, -1, -1);
            }else{
                params.clear();
                params.put("user_id", user.getId());
                params.put("sc_status", Integer.valueOf(0));
                user_cart = this.storeCartService.findOwnCartByCondition(params);
//                        query("select obj from StoreCart obj where obj.user.id=:user_id and obj.sc_status=:sc_status", params, -1, -1);
            }

        }else if(!cart_session_id.equals("")){
            params.clear();
            params.put("cart_session_id", cart_session_id);
            params.put("sc_status", Integer.valueOf(0));
            cookie_cart = this.storeCartService.findOwnCartByCondition(params);
//                    query("select obj from StoreCart obj where obj.cart_session_id=:cart_session_id and obj.sc_status=:sc_status", params, -1, -1);
        }

        for(GsStoreCart sc : user_cart){
            boolean sc_add = true;
            for(GsStoreCart sc1 : cart){
                if(sc1.getStoreId().equals(sc.getStoreId())){
                    sc_add = false;
                }
            }
            if(sc_add)
                cart.add(sc);
        }
        boolean sc_add;
        for(GsStoreCart sc : cookie_cart){
            sc_add = true;
            for(GsStoreCart sc1 : cart){
                if(sc1.getStoreId().equals(sc.getStoreId())){
                    sc_add = false;
                    for(GsGoodsCart gc : sc.getGcs()){
                        gc.setScId(sc1.getStoreId());
                        this.goodsCartService.update(gc);
                    }
                    this.storeCartService.delete(sc.getId());
                }
            }
            if(sc_add){
                cart.add(sc);
            }
        }
        if(cart != null){
            for(GsStoreCart sc : cart){
                if(sc != null){
                    list.addAll(sc.getGcs());
                }
            }
        }
        float total_price = 0.0F;
        for(GsGoodsCart gc : list){
            GsGoods goods = this.goodsService.findOne(gc.getGoodsId());
            if(CommUtil.null2String(gc.getCartType()).equals("combin"))
                total_price = CommUtil.null2Float(goods.getCombinPrice());
            else{
                total_price = CommUtil.null2Float(Double.valueOf(CommUtil.mul(Integer.valueOf(gc.getCount()), goods.getGoodsCurrentPrice()))) + total_price;
            }
        }
        model.addAttribute("total_price", Float.valueOf(total_price));
        model.addAttribute("cart", list);

        return ret;
    }

    public String showLoadFloorAjaxHtml(List lists, int i, String url, Map<String, Object> map){
        String img = null;
        String loadimg = map.get("imageWebServer") + "/resources/style/common/images/loader.gif";
        String errorimg = map.get("webPath") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
        String goods_url = null;
        String goods_class_url = null;
        String child_goods_class_url = null;

        GsGoodsFloorWithBLOBs floor = (GsGoodsFloorWithBLOBs) lists.get(0);

        img = null;

        StringBuffer sb = new StringBuffer(1000);
//        sb.append("<div class='floor " + floor.getGfCss() + "'>").append("<div class='floor_box' id='floor_" + i + "'>");
//        sb.append("<div class='floor_menu'>").append("<div class='title'>").append("<div class='txt-type'>").append("<span>").append(i).append("</span>");
//        sb.append("<h2 title='").append(floor.getGfName()).append("'>").append(floor.getGfName()).append("</h2></div></div><div class='flr_m_details'><ul class='flr_m_du'>");
//        List<GsGoodsClass> gcs = this.gf_tools.generic_gf_gc(floor.getGfGcList());
//        for(GsGoodsClass gc : gcs){
//            goods_class_url = map.get("webPath") + "/store_goods_list_" + gc.getId() + ".htm";
//            sb.append("<li><h4><a href='").append(goods_class_url).append("'>").append(gc.getClassname()).append("</a></h4><p>");
//            for(GsGoodsClass c_gc : gc.getChildren()){
//                child_goods_class_url = map.get("webPath") + "/store_goods_list_" + c_gc.getId() + ".htm";
//                sb.append("<span><a href='").append(child_goods_class_url).append("' target='_blank'>").append(c_gc.getClassname()).append("</a></span>");
//            }
//            sb.append("</p></li>");
//        }
//        sb.append("</ul><div class='flr_advertisment'>");
//        //拼接左侧广告
//        sb.append(gf_tools.generic_adv(url, floor.getGfLeftAdv()));
//
//        sb.append("</div></div></div><div class='floorclass'><ul class='floorul'>");
//
//        int num = 0;
//        for(GsGoodsFloor info : floor.getChilds()){
//            num++;
//            sb.append("<li ");
//            if(num == 1){
//                sb.append("class='this'");
//            }
//            sb.append("style='cursor:pointer;' id='").append(info.getId()).append("' store_gc='").append(floor.getId()).append("' >");
//            sb.append(info.getGfName()).append("<s></s></li>");
//        }
//        sb.append("</ul>");
//
//        int count = 0;
//
//        for(GsGoodsFloor info : floor.getChilds()){
//            count++;
//            sb.append("<div id='").append(info.getId()).append("' store_gc='").append(floor.getId()).append("' class='ftab'");
//            if(count > 1){
//                sb.append("style='display:none;'");
//            }
//            sb.append("><div class='ftabone'><div class='classpro'>");
//            for(GsGoods goods : this.gf_tools.generic_goods(info.getGf_gc_goods())){
//                if(goods != null){
//                    if(goods.getGoods_main_photo() != null)
//                        img = map.get("imageWebServer") + "/" + goods.getGoods_main_photo().getPath() + "/" + goods.getGoods_main_photo().getName() + "_small." + goods.getGoods_main_photo().getExt();
//                    else
//                        img = map.get("imageWebServer") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
//
//                    goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//
//                    if((Boolean)map.get("IsSecondDomainOpen")){
//                        goods_url = "http://" + goods.getGoods_store().getStore_second_domain() + "." + map.get("domainPath") + "/goods_" + goods.getId() + ".htm";
//                    }
//                    sb.append("<div class='productone'><ul class='this'><li><span class='center_span'>");
//                    sb.append("<p><a href='").append(goods_url).append("' target='_blank' ><img src='").append(img).append("' original='");
//                    sb.append(img).append("' onerror=\"this.src=").append(errorimg).append(";\" /></a></p></span></li>");
//                    sb.append("<li class='pronames'><a href='")
//                            .append(goods_url).append("' target='_blank'>")
//                            .append(goods.getGoodsName()).append("</a></li>");
//                    sb.append("<li><span class=\"hui2\">市场价：</span><span class=\"through hui\">￥")
//                            .append(goods.getGoodsPrice());
//                    sb.append("</span></li><li><span class=\"hui2\">商城价：</span><strong class=\"red\">￥")
//                            .append(goods.getGoodsCurrentPrice());
//                    sb.append("</strong></li></ul></div>");
//                }
//            }
//            sb.append("</div></div></div>");
//        }
//        sb.append("</div><div class='ranking'>");
//        Map<String, Object> mmap = gf_tools.generic_goods_list(floor.getGfListGoods());
//        sb.append("<h1>").append(mmap.get("list_title")).append("</h1>");
//
//        if(mmap.get("goods1") != null){
//            GsGoods goods = (GsGoods)mmap.get("goods1");
//            if(goods.getGoods_main_photo() != null)
//                img = map.get("imageWebServer") + "/" + goods.getGoods_main_photo().getPath() + "/" + goods.getGoods_main_photo().getName() + "_small." + goods.getGoods_main_photo().getExt();
//            else
//                img = map.get("imageWebServer") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
//
//            goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//
//            sb.append("<ul class=\"rankul\"><li class=\"rankimg\"><a href='").append(goods_url).append("' target=\"_blank\">");
//            sb.append("<img src='").append(img).append("' original='").append(img).append("' onerror=\"this.src='").append(errorimg).append("';\"  width='73' height='73'/>");
//            sb.append("</a><span class=\"rankno1\"></span></li><li class=\"rankhui\"><strong><a href='").append(goods_url).append("' target=\"_blank\">");
//            sb.append(CommUtil.substring(goods.getGoodsName(), 12)).append("</a></strong></li><li class=\"rankmoney\">￥").append(goods.getGoods_current_price());
//            sb.append("</li></ul>");
//        }
//
//        if(mmap.get("goods2") != null){
//            GsGoods goods = (GsGoods)mmap.get("goods2");
//            if(goods.getGoods_main_photo() != null)
//                img = map.get("imageWebServer") + "/" + goods.getGoods_main_photo().getPath() + "/" + goods.getGoods_main_photo().getName() + "_small." + goods.getGoods_main_photo().getExt();
//            else
//                img = map.get("imageWebServer") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
//
//            goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//
//            sb.append("<ul class=\"rankul\"><li class=\"rankimg\"><a href='").append(goods_url).append("' target=\"_blank\">");
//            sb.append("<img src='").append(img).append("' original='").append(img).append("' onerror=\"this.src='").append(errorimg).append("';\"  width='73' height='73'/>");
//            sb.append("</a><span class=\"rankno1\"></span></li><li class=\"rankhui\"><strong><a href='").append(goods_url).append("' target=\"_blank\">");
//            sb.append(CommUtil.substring(goods.getGoodsName(), 12)).append("</a></strong></li><li class=\"rankmoney\">￥").append(goods.getGoods_current_price());
//            sb.append("</li></ul>");
//        }
//
//        if(mmap.get("goods3") != null){
//            GsGoods goods = (GsGoods) mmap.get("goods3");
//            if(goods.getGoods_main_photo() != null)
//                img = map.get("imageWebServer") + "/" + goods.getGoods_main_photo().getPath() + "/" + goods.getGoods_main_photo().getName() + "_small." + goods.getGoods_main_photo().getExt();
//            else
//                img = map.get("imageWebServer") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
//
//            goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//
//            sb.append("<ul class=\"rankul\"><li class=\"rankimg\"><a href='").append(goods_url).append("' target=\"_blank\">");
//            sb.append("<img src='").append(img).append("' original='").append(img).append("' onerror=\"this.src='").append(errorimg).append("';\"  width='73' height='73'/>");
//            sb.append("</a><span class=\"rankno1\"></span></li><li class=\"rankhui\"><strong><a href='").append(goods_url).append("' target=\"_blank\">");
//            sb.append(CommUtil.substring(goods.getGoodsName(), 12)).append("</a></strong></li><li class=\"rankmoney\">￥").append(goods.getGoods_current_price());
//            sb.append("</li></ul>");
//        }
//
//        sb.append("<ul class=\"rankul2\">");
//        if(mmap.get("goods4") != null){
//            GsGoods goods = (GsGoods) mmap.get("goods4");
//            goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//            sb.append("<li><a href='")
//                    .append(goods_url).append("' target='_blank'>")
//                    .append(CommUtil.substring(goods.getGoodsName(), 14))
//                    .append("</a></li>");
//        }
//        if(mmap.get("goods5") != null){
//            GsGoods goods = (GsGoods) mmap.get("goods5");
//            goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//            sb.append("<li><a href='").append(goods_url)
//                    .append("' target='_blank'>")
//                    .append(CommUtil.substring(goods.getGoodsName(), 14))
//                    .append("</a></li>");
//        }
//        if(mmap.get("goods6") != null){
//            GsGoods goods = (GsGoods) mmap.get("goods6");
//            goods_url = map.get("webPath") + "/goods_" + goods.getId() + ".htm";
//            sb.append("<li><a href='")
//                    .append(goods_url)
//                    .append("' target='_blank'>")
//                    .append(CommUtil.substring(goods.getGoodsName(), 14))
//                    .append("</a></li>");
//        }
//        sb.append("</ul><div class=\"rank_advertisment\">");
//        //拼接右侧广告
//        sb.append(this.gf_tools.generic_adv(url, floor.getGfRightAdv()));
//        sb.append("</div></div></div><div class=\"floor_brand\"><span class=\"fl_brand_sp\"></span><span class=\"flr_sp_brand\">");
//
//        for(GsGoodsBrand brand : this.gf_tools.generic_brand(floor.getGfBrandList())){
//            String brand_url = map.get("webPath") + "/brand_goods_" + brand.getId() + ".htm";
//            String brand_img = map.get("imageWebServer") + "/" + brand.getBrandLogo().getPath() + "/" + brand.getBrandLogo().getName();
//            sb.append("<a href='").append(brand_url).append("' target='_blank'><img src='").append(brand_img).append("' width='98' height='35' /></a>");
//        }
//        sb.append("</span></div></div>");

        return sb.toString();
    }

}
