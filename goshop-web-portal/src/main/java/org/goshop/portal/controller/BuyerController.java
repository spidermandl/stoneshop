package org.goshop.portal.controller;

import org.apache.shiro.SecurityUtils;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsService;
import org.goshop.store.i.StoreService;
import org.goshop.tools.service.OrderViewTools;
import org.goshop.tools.service.StoreViewTools;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Desmond on 21/02/2018.
 */
@Controller
@RequestMapping(value =  "/buyer")
public class BuyerController extends BaseController {


    @Autowired
    private StoreViewTools storeViewTools;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;
//    @Autowired
//    private IDynamicService dynamicService;
//    @Autowired
//    private ISnsFriendService snsFriendService;
//    @Autowired
//    private IFavoriteService favService;
//    @Autowired
//    private ISnsAttentionService SnsAttentionService;
//    @Autowired
//    private IHomePageGoodsClassService HomeGoodsClassService;
//    @Autowired
//    private IMessageService messageService;

    @Autowired
    private OrderViewTools orderViewTools;

    @Override
    protected String rootTemplatePath() {
        return "buyer/";
    }

    /**
     * 买家中心
     * @param model
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param type
     * @return
     */
    @RequestMapping({"/index"})
    public String index(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        String currentPage, String orderBy,
                        String orderType, String type){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("buyer_index");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = "wap/buyer_index";
        }
        List msgs = new ArrayList();
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        if (user != null){
//            Map params = new HashMap();
//            params.put("status", Integer.valueOf(0));
//            params.put("user_id", user==null?0:user.getId());
//            msgs = this.messageService.query(
//                    "select obj from Message obj where obj.status=:status and obj.toUser.id=:user_id and obj.parent.id is null", params, -1, -1);
//        }
//        model.addAttribute("msgs", msgs);
//        model.addAttribute("storeViewTools", this.storeViewTools);
//        model.addAttribute("orderViewTools", this.orderViewTools);
//        DynamicQueryObject qo = new DynamicQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.display", new SysMap("display", Boolean.valueOf(true)), "=");
//        if ((type == null) || (type.equals(""))){
//            type = "2";
//        }
//        if (type.equals("1")){
//            qo.addQuery("obj.user.id",
//                    new SysMap("uid",
//                            SecurityUserHolder.getCurrentUser().getId()), "=");
//        }
//        if (type.equals("2")){
//            Map map = new HashMap();
//            map.put("f_uid", SecurityUserHolder.getCurrentUser().getId());
//            List myFriends = this.snsFriendService
//                    .query(
//                            "select obj from SnsFriend obj where obj.fromUser.id=:f_uid",
//                            map, -1, -1);
//            Set ids = getSnsFriendToUserIds(myFriends);
//            Map paras = new HashMap();
//            paras.put("ids", null);
//            if (myFriends.size() > 0){
//                paras.put("ids", ids);
//            }
//            qo.addQuery("obj.user.id in (:ids)", paras);
//        }
//        if (type.equals("3")){
//            Map params = new HashMap();
//            params.put("uid", SecurityUserHolder.getCurrentUser().getId());
//            List SnsAttentions = this.SnsAttentionService
//                    .query(
//                            "select obj from SnsAttention obj where obj.fromUser.id=:uid ",
//                            params, -1, -1);
//            Set ids = getSnsAttentionToUserIds(SnsAttentions);
//            params.clear();
//            params.put("ids", ids);
//            if ((ids != null) && (ids.size() > 0)){
//                qo.addQuery("obj.user.id in (:ids)", params);
//            }
//        }
//        if (type.equals("4")){
//            qo.addQuery("obj.user.id",
//                    new SysMap("uid",
//                            SecurityUserHolder.getCurrentUser().getId()), "=");
//            qo.addQuery("obj.store.id is not null", null);
//        }
//        qo.addQuery("obj.locked", new SysMap("locked", Boolean.valueOf(false)), "=");
//        qo.addQuery("obj.dissParent.id is null", null);
//        qo.setOrderBy("addTime");
//        qo.setOrderType("desc");
//        qo.setPageSize(Integer.valueOf(10));
//        IPageList pList = this.dynamicService.list(qo);
//        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
        List list = new ArrayList();
        for (int i = 1; i <= 120; i++){
            list.add(Integer.valueOf(i));
        }
        model.addAttribute("type", type);
        model.addAttribute("emoticons", list);

        return ret;
    }

    @RequestMapping({"/nav"})
    public String nav(Model model,
                      HttpServletRequest request,
                      HttpServletResponse response){
        String ret = generateViewURL("/buyer_nav");
        String op = CommUtil.null2String(request.getAttribute("op"));
        model.addAttribute("op", op);

        return ret;
    }

    /**
     * 买家中心导航
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/head"})
    public String head(HttpServletRequest request, HttpServletResponse response){
        String ret = generateViewURL("/buyer_head");

        return ret;
    }

    @RequestMapping({"/authority"})
    public String authority(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        String ret = "error";
        model.addAttribute("op_title", "您没有该项操作权限");
        model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");

        return ret;
    }

//    private Set<Long> getSnsAttentionToUserIds(List<SnsAttention> SnsAttentions){
//        Set ids = new HashSet();
//        for (SnsAttention attention : SnsAttentions){
//            ids.add(attention.getToUser().getId());
//        }
//
//        return ids;
//    }
//
//    private Set<Long> getSnsFriendToUserIds(List<SnsFriend> myfriends){
//        Set ids = new HashSet();
//        if (myfriends.size() > 0){
//            for (SnsFriend friend : myfriends){
//                ids.add(friend.getToUser().getId());
//            }
//        }
//
//        return ids;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "动态发布保存", value = "/buyer/dynamic_save.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/dynamic_save.htm"})
//    public ModelAndView dynamic_save(HttpServletRequest request, HttpServletResponse response, String content, String currentPage, String orderBy, String orderType, String store_id, String goods_id){
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/dynamic_list.html", this.configService
//                .getSysConfig(),
//                this.userConfigService.getUserConfig(), 0, request, response);
//        WebForm wf = new WebForm();
//        Dynamic dynamic = (Dynamic)wf.toPo(request, Dynamic.class);
//        dynamic.setAddTime(new Date());
//        dynamic.setUser(SecurityUserHolder.getCurrentUser());
//        dynamic.setContent(content);
//        dynamic.setDisplay(true);
//        if ((store_id != null) && (!store_id.equals(""))){
//            Store store = this.storeService.getObjById(
//                    CommUtil.null2Long(store_id));
//            dynamic.setStore(store);
//        }
//        if ((goods_id != null) && (!goods_id.equals(""))){
//            Goods goods = this.goodsService.getObjById(
//                    CommUtil.null2Long(goods_id));
//            dynamic.setGoods(goods);
//
//            Map params = new HashMap();
//            params.put("uid", SecurityUserHolder.getCurrentUser().getId());
//            params.put("gc_id", goods.getGc().getId());
//            List hgcs = this.HomeGoodsClassService
//                    .query(
//                            "select obj from HomePageGoodsClass obj where obj.user.id=:uid and obj.gc.id=:gc_id",
//                            params, -1, -1);
//            if (hgcs.size() == 0){
//                Map map = new HashMap();
//                map.put("uid", SecurityUserHolder.getCurrentUser().getId());
//                HomePageGoodsClass hpgc = new HomePageGoodsClass();
//                hpgc.setAddTime(new Date());
//                hpgc.setUser(SecurityUserHolder.getCurrentUser());
//                hpgc.setGc(goods.getGc());
//                this.HomeGoodsClassService.save(hpgc);
//            }
//        }
//        this.dynamicService.save(dynamic);
//
//        DynamicQueryObject qo = new DynamicQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.dissParent.id is null", null);
//        qo.setOrderBy("addTime");
//        qo.setOrderType("desc");
//        qo.setPageSize(Integer.valueOf(10));
//        IPageList pList = this.dynamicService.list(qo);
//        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "删除动态", value = "/buyer/dynamic_del.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/dynamic_del.htm"})
//    public ModelAndView dynamic_ajax_del(HttpServletRequest request, HttpServletResponse response, String id, String currentPage, String orderBy, String orderType){
//        if (!id.equals("")){
//            Dynamic dynamic = this.dynamicService
//                    .getObjById(Long.valueOf(Long.parseLong(id)));
//            this.dynamicService.delete(Long.valueOf(Long.parseLong(id)));
//        }
//
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/dynamic_list.html", this.configService
//                .getSysConfig(),
//                this.userConfigService.getUserConfig(), 0, request, response);
//        DynamicQueryObject qo = new DynamicQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.dissParent.id is null", null);
//        qo.setOrderBy("addTime");
//        qo.setOrderType("desc");
//        qo.setPageSize(Integer.valueOf(10));
//        IPageList pList = this.dynamicService.list(qo);
//        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "ajax回复保存方法", value = "/buyer/dynamic_ajax_reply.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/dynamic_ajax_reply.htm"})
//    public ModelAndView dynamic_ajax_reply(HttpServletRequest request, HttpServletResponse response, String parent_id, String fieldName, String reply_content)
//            throws ClassNotFoundException {
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/dynamic_childs_list.html",
//                this.configService.getSysConfig(), this.userConfigService
//                .getUserConfig(), 0, request, response);
//        WebForm wf = new WebForm();
//        Dynamic dynamic = (Dynamic)wf.toPo(request, Dynamic.class);
//        Dynamic parent = null;
//        if ((parent_id != null) && (!parent_id.equals(""))){
//            parent = this.dynamicService.getObjById(Long.valueOf(Long.parseLong(parent_id)));
//            dynamic.setDissParent(parent);
//            this.dynamicService.update(parent);
//            dynamic.setDissParent(parent);
//        }
//        dynamic.setAddTime(new Date());
//        dynamic.setUser(SecurityUserHolder.getCurrentUser());
//        dynamic.setContent(reply_content);
//        this.dynamicService.save(dynamic);
//        mv.addObject("obj", parent);
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "ajax赞动态方法", value = "/buyer/dynamic_ajax_praise.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/dynamic_ajax_praise.htm"})
//    public void dynamic_ajax_praise(HttpServletRequest request, HttpServletResponse response, String dynamic_id)
//            throws ClassNotFoundException {
//        Dynamic dynamic = this.dynamicService.getObjById(
//                Long.valueOf(Long.parseLong(dynamic_id)));
//        dynamic.setPraiseNum(dynamic.getPraiseNum() + 1);
//        this.dynamicService.update(dynamic);
//        response.setContentType("text/plain");
//        response.setHeader("Cache-Control", "no-cache");
//        try {request.setCharacterEncoding("UTF-8");} catch (java.io.UnsupportedEncodingException e1) {e1.printStackTrace();}
//        try {
//            PrintWriter writer = response.getWriter();
//            writer.print(dynamic.getPraiseNum());
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "ajax转发动态保存方法", value = "/buyer/dynamic_ajax_turn.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/dynamic_ajax_turn.htm"})
//    public ModelAndView dynamic_ajax_turn(HttpServletRequest request, HttpServletResponse response, String dynamic_id, String content, String currentPage, String orderType, String orderBy)
//            throws ClassNotFoundException {
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/dynamic_list.html", this.configService
//                .getSysConfig(),
//                this.userConfigService.getUserConfig(), 0, request, response);
//        Dynamic dynamic = this.dynamicService.getObjById(
//                Long.valueOf(Long.parseLong(dynamic_id)));
//        dynamic.setTurnNum(dynamic.getTurnNum() + 1);
//        this.dynamicService.update(dynamic);
//        Dynamic turn = new Dynamic();
//        turn.setAddTime(new Date());
//        turn.setContent(content + "//转自" + dynamic.getUser().getUserName() +
//                ":" + dynamic.getContent());
//        turn.setUser(SecurityUserHolder.getCurrentUser());
//        this.dynamicService.save(turn);
//
//        DynamicQueryObject qo = new DynamicQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.dissParent.id is null", null);
//        qo.setOrderBy("addTime");
//        qo.setOrderType("desc");
//        qo.setPageSize(Integer.valueOf(10));
//        IPageList pList = this.dynamicService.list(qo);
//        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "删除动态下方自己发布的评论", value = "/buyer/dynamic_reply_del.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/dynamic_reply_del.htm"})
//    public ModelAndView dynamic_reply_del(HttpServletRequest request, HttpServletResponse response, String id, String parent_id){
//        if (!id.equals("")){
//            Dynamic dynamic = this.dynamicService
//                    .getObjById(Long.valueOf(Long.parseLong(id)));
//            this.dynamicService.delete(Long.valueOf(Long.parseLong(id)));
//        }
//
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/dynamic_childs_list.html",
//                this.configService.getSysConfig(), this.userConfigService
//                .getUserConfig(), 0, request, response);
//        if ((parent_id != null) && (!parent_id.equals(""))){
//            Dynamic obj = this.dynamicService.getObjById(
//                    CommUtil.null2Long(parent_id));
//            mv.addObject("obj", obj);
//        }
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "用户分享收藏店铺列表", value = "/buyer/fav_store_list.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/fav_store_list.htm"})
//    public ModelAndView fav_store_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String orderBy, String orderType){
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/fav_store_list.html", this.configService
//                .getSysConfig(),
//                this.userConfigService.getUserConfig(), 0, request, response);
//        FavoriteQueryObject qo = new FavoriteQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.user.id",
//                new SysMap("uid",
//                        SecurityUserHolder.getCurrentUser().getId()), "=");
//        qo.addQuery("obj.type", new SysMap("type", Integer.valueOf(1)), "=");
//        qo.setPageSize(Integer.valueOf(4));
//        IPageList pList = this.favService.list(qo);
//        mv.addObject("objs", pList.getResult());
//        String Ajax_url = CommUtil.getURL(request) +
//                "/buyer/fav_store_list_ajax.htm";
//        mv.addObject("gotoPageAjaxHTML", CommUtil.showPageAjaxHtml(Ajax_url,
//                "", pList.getCurrentPage(), pList.getPages()));
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "用户分享收藏店铺ajax列表", value = "/buyer/fav_store_list_ajax.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/fav_store_list_ajax.htm"})
//    public ModelAndView fav_store_list_ajax(HttpServletRequest request, HttpServletResponse response, String currentPage, String orderBy, String orderType){
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/fav_store_list_ajax.html",
//                this.configService.getSysConfig(), this.userConfigService
//                .getUserConfig(), 0, request, response);
//        FavoriteQueryObject qo = new FavoriteQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.user.id",
//                new SysMap("uid",
//                        SecurityUserHolder.getCurrentUser().getId()), "=");
//        qo.addQuery("obj.type", new SysMap("type", Integer.valueOf(1)), "=");
//        qo.setPageSize(Integer.valueOf(4));
//        IPageList pList = this.favService.list(qo);
//        mv.addObject("objs", pList.getResult());
//        String Ajax_url = CommUtil.getURL(request) +
//                "/buyer/fav_store_list_ajax.htm";
//        mv.addObject("gotoPageAjaxHTML", CommUtil.showPageAjaxHtml(Ajax_url,
//                "", pList.getCurrentPage(), pList.getPages()));
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "用户分享收藏商品列表", value = "/buyer/fav_goods_list.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/fav_goods_list.htm"})
//    public ModelAndView fav_goods_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String orderBy, String orderType){
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/fav_goods_list.html", this.configService
//                .getSysConfig(),
//                this.userConfigService.getUserConfig(), 0, request, response);
//        FavoriteQueryObject qo = new FavoriteQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.user.id",
//                new SysMap("uid",
//                        SecurityUserHolder.getCurrentUser().getId()), "=");
//        qo.addQuery("obj.type", new SysMap("type", Integer.valueOf(0)), "=");
//        qo.setPageSize(Integer.valueOf(4));
//        IPageList pList = this.favService.list(qo);
//        mv.addObject("objs", pList.getResult());
//        String Ajax_url = CommUtil.getURL(request) +
//                "/buyer/fav_goods_list_ajax.htm";
//        mv.addObject("gotoPageAjaxHTML", CommUtil.showPageAjaxHtml(Ajax_url,
//                "", pList.getCurrentPage(), pList.getPages()));
//
//        return mv;
//    }
//
//    @SecurityMapping(display = false, rsequence = 0, title = "用户分享收藏商品ajax列表", value = "/buyer/fav_goods_list_ajax.htm*", rtype = "buyer", rname = "用户中心", rcode = "user_center", rgroup = "用户中心")
//    @RequestMapping({"/buyer/fav_goods_list_ajax.htm"})
//    public ModelAndView fav_goods_list_ajax(HttpServletRequest request, HttpServletResponse response, String currentPage, String orderBy, String orderType){
//        ModelAndView mv = new JModelAndView(
//                "user/default/usercenter/fav_goods_list_ajax.html",
//                this.configService.getSysConfig(), this.userConfigService
//                .getUserConfig(), 0, request, response);
//        FavoriteQueryObject qo = new FavoriteQueryObject(currentPage, mv,
//                orderBy, orderType);
//        qo.addQuery("obj.user.id",
//                new SysMap("uid",
//                        SecurityUserHolder.getCurrentUser().getId()), "=");
//        qo.addQuery("obj.type", new SysMap("type", Integer.valueOf(0)), "=");
//        qo.setPageSize(Integer.valueOf(4));
//        IPageList pList = this.favService.list(qo);
//        mv.addObject("objs", pList.getResult());
//        String Ajax_url = CommUtil.getURL(request) +
//                "/buyer/fav_goods_list_ajax.htm";
//        mv.addObject("gotoPageAjaxHTML", CommUtil.showPageAjaxHtml(Ajax_url,
//                "", pList.getCurrentPage(), pList.getPages()));
//
//        return mv;
//    }

}
