package org.goshop.portal.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.goshop.common.pojo.TransInfo;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.goods.i.GoodsEvaluateService;
import org.goshop.goods.pojo.GsGoodsEvaluate;
import org.goshop.goods.pojo.GsGoodsEvaluateWithBLOBs;
import org.goshop.order.i.ExpressCompanyService;
import org.goshop.order.i.OrderAddressService;
import org.goshop.order.i.OrderFormLogService;
import org.goshop.order.i.OrderFormService;
import org.goshop.order.pojo.*;
import org.goshop.pay.i.PaymentService;
import org.goshop.pay.i.PredepositLogService;
import org.goshop.pay.i.RefundService;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;
import org.goshop.pay.pojo.GsPredepositLog;
import org.goshop.pay.pojo.GsRefundLog;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StorePointService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.GsStorePoint;
import org.goshop.store.pojo.Store;
import org.goshop.store.pojo.StoreWithBLOBs;
import org.goshop.tools.service.UtilViewTools;
import org.goshop.users.i.MemberService;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.Member;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 买家订单控制器
 */
@Controller
public class OrderController extends BaseController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderFormLogService orderFormLogService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsEvaluateService goodsEvaluateService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExpressCompanyService expressCompayService;
    @Autowired
    private PredepositLogService predepositLogService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private StorePointService storePointService;
    @Autowired
    private StoreAreaService storeAreaService;
    @Autowired
    private OrderAddressService orderAddressService;
    @Autowired
    private RefundService refundService;
//    @Autowired
//    private ITemplateService templateService;
//    @Autowired
//    private IGoodsReturnItemService goodsReturnItemService;
//    @Autowired
//    private IGoodsReturnService goodsReturnService;
//
//    @Autowired
//    private MsgTools msgTools;
    @Autowired
    private UtilViewTools utilViewTools;

    @Override
    protected String rootTemplatePath() {
        return "buyer/";
    }

    /**
     * 买家订单列表
     * @param request
     * @param response
     * @param currentPage
     * @param order_id
     * @param beginTime
     * @param endTime
     * @param order_status
     * @return
     */
    @RequestMapping({"/order"})
    public String order(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String currentPage, String order_id,
                              String beginTime, String endTime,
                              String order_status){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("buyer_order");

        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/buyer_order");
        }
        Map param = new HashMap();
        param.put("orderType","desc");
        param.put("orderBy","addTime");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        param.put("user_id", user==null?0:user.getId());
        if (!CommUtil.null2String(order_id).equals("")){
//            param.put("obj.order_id", new SysMap("order_id", "%" + order_id + "%"), "like");
            param.put("order_id",order_id);
            model.addAttribute("order_id", order_id);
        }
        if (!CommUtil.null2String(beginTime).equals("")){
            param.put("beginTime", CommUtil.formatDate(beginTime));
            model.addAttribute("beginTime", beginTime);
        }
        if (!CommUtil.null2String(beginTime).equals("")){
            param.put("endTime", CommUtil.formatDate(endTime));
            model.addAttribute("endTime", endTime);
        }
        if (!CommUtil.null2String(order_status).equals("")){
            if (order_status.equals("order_submit")){
                param.put("order_status", Integer.valueOf(10));
            }
            if (order_status.equals("order_pay")){
                param.put("order_status", Integer.valueOf(20));
            }
            if (order_status.equals("order_shipping")){
                param.put("order_status", Integer.valueOf(30));
            }
            if (order_status.equals("order_receive")){
                param.put("order_status", Integer.valueOf(40));
            }
            if (order_status.equals("order_finish")){
                param.put("order_status", Integer.valueOf(60));
            }
            if (order_status.equals("order_cancel")){
                param.put("order_status", Integer.valueOf(0));
            }
        }
        model.addAttribute("order_status", order_status);
        PageInfo<GsOrderformWithBLOBs> pList = this.orderFormService.findByCondition(param,CommUtil.null2Int(currentPage),12);
        Map p = new HashMap();
        for (GsOrderformWithBLOBs order:pList.getList()){
            p.clear();
            p.put("of_id",order.getId());
            List<GsGoodsCart> carts = this.goodsCartService.findByCondition(p);
            for (GsGoodsCart c:carts){
                c.setGoods(this.goodsService.findBasicOne(c.getGoodsId()));
            }
            order.setGcs(carts);
            Store store = this.storeService.findBasicOne(order.getStoreId());
            User u = this.userService.findOne(store.getMemberId());
            store.setUser(u);
            order.setStore(store);
        }
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);

        return ret;
    }

    /**
     * 买家订单列表
     * @param model
     * @param request
     * @param response
     * @param currentPage
     * @param order_id
     * @param beginTime
     * @param endTime
     * @param order_status
     */
    @RequestMapping({"/ajaxorder"})
    public void ajaxorder(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          String currentPage, String order_id,
                          String beginTime, String endTime,
                          String order_status){
        Map<String, Object> map = new HashMap<String, Object>();

        Map param = new HashMap();
        param.put("orderType","desc");
        param.put("orderBy","addTime");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        param.put("user_id", user==null?0:user.getId());
        if (!CommUtil.null2String(order_id).equals("")){
//            param.put("obj.order_id", new SysMap("order_id", "%" + order_id + "%"), "like");
            param.put("order_id",order_id);
            model.addAttribute("order_id", order_id);
        }
        if (!CommUtil.null2String(beginTime).equals("")){
            param.put("beginTime", CommUtil.formatDate(beginTime));
            model.addAttribute("beginTime", beginTime);
        }
        if (!CommUtil.null2String(beginTime).equals("")){
            param.put("endTime", CommUtil.formatDate(endTime));
            model.addAttribute("endTime", endTime);
        }
        if (!CommUtil.null2String(order_status).equals("")){
            if (order_status.equals("order_submit")){
                param.put("order_status", Integer.valueOf(10));// 待付款
            }
            if (order_status.equals("order_pay")){
                param.put("order_status", Integer.valueOf(20));// 待发货
            }
            if (order_status.equals("order_shipping")){
                param.put("order_status", Integer.valueOf(30));// 已发货
            }
            if (order_status.equals("order_receive")){
                param.put("order_status", Integer.valueOf(40));// 已收货
            }
            if (order_status.equals("order_finish")){
                param.put("order_status", Integer.valueOf(60));// 已结束
            }
            if (order_status.equals("order_cancel")){
                param.put("order_status", Integer.valueOf(0));// 已取消
            }
        }
        map.put("order_status", order_status);
        PageInfo pList = this.orderFormService.findByCondition(map,CommUtil.null2Int(currentPage),12);
        CommUtil.saveWebPaths(map, this.systemConfigService.getConfig(), request);
        map.put("show", "orders");
        utilViewTools.saveIPageList2Map("", "", "", pList, map);

        String ret = JsonUtils.objectToJson(map);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(ret);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 取消订单
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_cancel"})
    public String order_cancel(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String id, String currentPage){
        String ret = "buyer_order_cancel";

        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));

        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = "wap/buyer_order_cancel";
        }
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 订单取消确认
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param state_info
     * @param other_state_info
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_cancel_save"})
    public String order_cancel_save(Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String id, String currentPage,
                                    String state_info,
                                    String other_state_info) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            obj.setOrderStatus(0);
            this.orderFormService.update(obj);
            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("取消订单");
            ofl.setLogUserId(user.getId());
            ofl.setOfId(obj.getId());
            if (state_info.equals("other"))
                ofl.setStateInfo(other_state_info);
            else{
                ofl.setStateInfo(state_info);
            }
            this.orderFormLogService.save(ofl);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_toseller_order_cancel_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_toseller_order_cancel_notify");
            }
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 收货确认
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_cofirm"})
    public String order_cofirm(Model model,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                String id, String currentPage){
        String ret = generateViewURL("buyer_order_cofirm");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = "wap/buyer_order_cofirm";
        }
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 买家确认收货
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_cofirm_save"})
    public String order_cofirm_save(Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String id, String currentPage) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            obj.setOrderStatus(40);
            int r = this.orderFormService.update(obj);
            if (r>0){
                GsOrderLog ofl = new GsOrderLog();
                ofl.setAddtime(new Date());
                ofl.setLogInfo("确认收货");
                ofl.setLogUserId(user.getId());
                ofl.setOfId(obj.getId());
                this.orderFormLogService.save(ofl);
                Store store = this.storeService.findBasicOne(obj.getStoreId());
                if (this.systemConfigService.getConfig().getEmailEnable()){
                    send_email(request, obj, "email_toseller_order_receive_ok_notify");
                }
                if (this.systemConfigService.getConfig().getSmsEnbale()){
                    Member m = this.memberService.findByUserId(store.getMemberId());
                    send_sms(request, obj, m.getMemberMobile(), "sms_toseller_order_receive_ok_notify");
                }
                GsPaymentWithBLOBs payment = this.paymentService.findOne(obj.getPaymentId());
                if (payment.getMark().equals("balance")){
                    User seller = this.userService.findOne(store.getMemberId());
                    if (this.systemConfigService.getConfig().getBalance_fenrun() == 1){
                        Map params = new HashMap();
                        params.put("type", "admin");
                        params.put("mark", "balance");
                        List<GsPaymentWithBLOBs> payments = this.paymentService.findByCondition(params);
//                        "select obj from Payment obj where obj.type=:type and obj.mark=:mark"
                        GsPaymentWithBLOBs shop_payment = new GsPaymentWithBLOBs();
                        if (payments.size() > 0){
                            shop_payment = payments.get(0);
                        }

                        double shop_availableBalance = CommUtil.null2Double(obj.getTotalprice()) * CommUtil.null2Double(shop_payment.getBalanceDivideRate());
                        User admin = this.userService.findByLoginName("admin");
                        this.userService.changeDepositBalance(admin,shop_availableBalance);
                        GsPredepositLog log = new GsPredepositLog();
                        log.setAddtime(new Date());
                        log.setPdLogUserId(seller.getId());
                        log.setPdOpType("分润");
                        log.setPdLogAmount(BigDecimal.valueOf(shop_availableBalance));
                        log.setPdLogInfo("订单" + obj.getOrderId() + "确认收货平台分润获得预存款");
                        log.setPdType("可用预存款");
                        this.predepositLogService.save(log);

                        double seller_availableBalance = CommUtil.null2Double(obj.getTotalprice()) - shop_availableBalance;
                        this.userService.changeDepositBalance(seller,seller_availableBalance);
                        GsPredepositLog log1 = new GsPredepositLog();
                        log1.setAddtime(new Date());
                        log1.setPdLogUserId(seller.getId());
                        log1.setPdOpType("增加");
                        log1.setPdLogAmount(BigDecimal.valueOf(seller_availableBalance));
                        log1.setPdLogInfo("订单" + obj.getOrderId() + "确认收货增加预存款");
                        log1.setPdType("可用预存款");
                        this.predepositLogService.save(log1);

                        User buyer = this.userService.findOne(obj.getUserId());
                        this.userService.changeFreezeBalance(buyer,CommUtil.subtract(0, obj.getTotalprice()));
                    }else{
                        this.userService.changeDepositBalance(seller,obj.getTotalprice().doubleValue());
                        GsPredepositLog log = new GsPredepositLog();
                        log.setAddtime(new Date());
                        log.setPdLogUserId(seller.getId());
                        log.setPdOpType("增加");
                        log.setPdLogAmount(obj.getTotalprice());
                        log.setPdLogInfo("订单" + obj.getOrderId() + "确认收货增加预存款");
                        log.setPdType("可用预存款");
                        this.predepositLogService.save(log);

                        User buyer = this.userService.findOne(obj.getUserId());
                        this.userService.changeFreezeBalance(buyer,CommUtil.subtract(0, obj.getTotalprice()));
                        this.userService.update(buyer);
                    }
                }
            }
        }
        String url = "redirect:order?currentPage=" + currentPage;

        return url;
    }

    /**
     * 买家评价
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/order_evaluate"})
    public String order_evaluate(Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String id){
        String ret = "/buyer_order_evaluate";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
            if (obj.getOrderStatus() >= 50){
                ret = "success";
                model.addAttribute("op_title", "订单已经评价！");
                model.addAttribute("url", CommUtil.getURL(request) + "/order");
            }
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 买家评价保存
     * @param request
     * @param response
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_evaluate_save"})
    public String order_evaluate_save(Model model,
                                            HttpServletRequest request,
                                            HttpServletResponse response,
                                            String id) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            if (obj.getOrderStatus() == 40){
                obj.setOrderStatus(50);
                this.orderFormService.update(obj);
                GsOrderLog ofl = new GsOrderLog();
                ofl.setAddtime(new Date());
                ofl.setLogInfo("评价订单");
                ofl.setLogUserId(user.getId());
                ofl.setOfId(obj.getId());
                this.orderFormLogService.save(ofl);
                Map param = new HashMap();
                param.put("of_id",obj.getId());
                List<GsGoodsCart> goodsCart = this.goodsCartService.findByCondition(param);
                for (GsGoodsCart gc : goodsCart){
                    GsGoodsEvaluateWithBLOBs eva = new GsGoodsEvaluateWithBLOBs();
                    eva.setAddtime(new Date());
                    eva.setEvaluateGoodsId(gc.getGoodsId());
                    eva.setEvaluateInfo(request.getParameter("evaluate_info_" + gc.getId()));
                    eva.setEvaluateBuyerVal(CommUtil.null2Int(request.getParameter("evaluate_buyer_val" + gc.getId())));
                    eva.setDescriptionEvaluate(BigDecimal.valueOf(
                            CommUtil.null2Double(request.getParameter("description_evaluate" + gc.getId()))));
                    eva.setServiceEvaluate(BigDecimal.valueOf(CommUtil.null2Double(request.getParameter("service_evaluate" + gc.getId()))));
                    eva.setShipEvaluate(BigDecimal.valueOf(CommUtil.null2Double(request.getParameter("ship_evaluate" + gc.getId()))));
                    eva.setEvaluateType("goods");
                    eva.setEvaluateUserId(user.getId());
                    eva.setOfId(obj.getId());
                    eva.setGoodsSpec(gc.getSpecInfo());
                    this.goodsEvaluateService.save(eva);
                    Map params = new HashMap();
                    params.put("store_id", obj.getStoreId());
                    List<GsGoodsEvaluateWithBLOBs> evas = this.goodsEvaluateService.findByCondition(params);
//                        "select obj from Evaluate obj where obj.of.store.id=:store_id"
                    double store_evaluate1 = 0.0D;
                    double store_evaluate1_total = 0.0D;
                    double description_evaluate = 0.0D;
                    double description_evaluate_total = 0.0D;
                    double service_evaluate = 0.0D;
                    double service_evaluate_total = 0.0D;
                    double ship_evaluate = 0.0D;
                    double ship_evaluate_total = 0.0D;
                    DecimalFormat df = new DecimalFormat("0.0");
                    for (GsGoodsEvaluate eva1 : evas){
                        store_evaluate1_total = store_evaluate1_total + eva1.getEvaluateBuyerVal();
                        description_evaluate_total = description_evaluate_total + CommUtil.null2Double(eva1.getDescriptionEvaluate());
                        service_evaluate_total = service_evaluate_total + CommUtil.null2Double(eva1.getServiceEvaluate());
                        ship_evaluate_total = ship_evaluate_total + CommUtil.null2Double(eva1.getShipEvaluate());
                    }
                    store_evaluate1 = CommUtil.null2Double(df.format(store_evaluate1_total / evas.size()));
                    description_evaluate = CommUtil.null2Double(df.format(description_evaluate_total / evas.size()));
                    service_evaluate = CommUtil.null2Double(df.format(service_evaluate_total / evas.size()));
                    ship_evaluate = CommUtil.null2Double(df.format(ship_evaluate_total / evas.size()));

                    Store store = this.storeService.findBasicOne(obj.getStoreId());
                    store.setStoreCredit(store.getStoreCredit() + eva.getEvaluateBuyerVal());
                    this.storeService.update((StoreWithBLOBs) store);
                    params.clear();
                    params.put("store_id", store.getStoreId());
                    List<GsStorePoint> sps = this.storePointService.findByCondition(param);
//                            "select obj from StorePoint obj where obj.store.id=:store_id"
                    GsStorePoint point = null;
                    if (sps.size() > 0)
                        point = sps.get(0);
                    else{
                        point = new GsStorePoint();
                    }
                    point.setAddtime(new Date());
                    point.setStoreId(store.getStoreId());
                    point.setDescriptionEvaluate(BigDecimal.valueOf(description_evaluate));
                    point.setServiceEvaluate(BigDecimal.valueOf(service_evaluate));
                    point.setShipEvaluate(BigDecimal.valueOf(ship_evaluate));
                    point.setStoreEvaluate1(BigDecimal.valueOf(store_evaluate1));
                    if (sps.size() > 0)
                        this.storePointService.update(point);
                    else{
                        this.storePointService.save(point);
                    }

                    User u = this.userService.findOne(obj.getUserId());
                    //积分更新
                    //user.setIntegral(user.getIntegral() + this.systemConfigService.getConfig().getIndentComment());
                    this.userService.changePoint(u,this.systemConfigService.getConfig().getIndentComment());
                }
            }
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_toseller_evaluate_ok_notify");
            }
        }
        String ret = "success";
        model.addAttribute("op_title", "订单评价成功！");
        model.addAttribute("url", CommUtil.getURL(request) + "/order");

        return ret;
    }

    /**
     * 删除订单信息
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_delete"})
    public String order_delete(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String id, String currentPage) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId()) && (obj.getOrderStatus() == 0)){
            this.orderFormService.delete(obj.getId());
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 买家订单详情
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/order_view"})
    public String order_view(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             String id){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("order_view");
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeService.findBasicOne(obj.getStoreId());
        obj.setStore(store);
        if (store.getAreaId()!=null)
            store.setArea(this.storeAreaService.findLinkedOne(store.getAreaId()));

        Map p = new HashMap();
        p.clear();
        p.put("of_id",obj.getId());
        List<GsGoodsCart> carts = this.goodsCartService.findByCondition(p);
        for (GsGoodsCart c:carts){
            c.setGoods(this.goodsService.findBasicOne(c.getGoodsId()));
        }
        obj.setGcs(carts);
        if (obj.getAddrId()!=null){
            GsAddress addr = this.orderAddressService.findOne(obj.getAddrId());
            addr.setArea(this.storeAreaService.findLinkedOne(addr.getAreaId()));
            obj.setAddr(addr);
        }
        if (obj.getEcId()!=null){
            obj.setEc(this.expressCompayService.findOne(obj.getEcId()));
        }

        p.clear();
        p.put("of_id",obj.getId());
        List<GsOrderLog> logs = this.orderFormLogService.findByCondition(p);
        obj.setOfls(logs);

        p.clear();
        p.put("of_id",obj.getId());
        List<GsRefundLog> refundlogs = this.refundService.findByCondition(p);
        obj.setGrls(refundlogs);

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
            TransInfo transInfo = query_ship_getData(CommUtil.null2String(obj.getId()));
            model.addAttribute("transInfo", transInfo);
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 买家物流详情
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/ship_view"})
    public String order_ship_view(Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String id){
        String ret = "/order_ship_view";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        if ((obj != null) && (!obj.equals(""))){
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (obj.getUserId().equals(user==null?0:user.getId())){
                model.addAttribute("obj", obj);
                TransInfo transInfo = query_ship_getData(CommUtil.null2String(obj.getId()));
                model.addAttribute("transInfo", transInfo);
            }else{
                ret = generateViewURL("error");
                model.addAttribute("op_title", "您查询的物流不存在！");
                model.addAttribute("url", CommUtil.getURL(request) + "/order");
            }
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您查询的物流不存在！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 物流跟踪查询
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/query_ship"})
    public String query_ship(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             String id){
        String ret = "query_ship_data";
        TransInfo info = query_ship_getData(id);
        model.addAttribute("transInfo", info);

        return ret;
    }

    /**
     * 虚拟商品信息
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/order_seller_intro"})
    public String order_seller_intro(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String id){
        String ret = "/order_seller_intro";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
        }

        return ret;
    }

    /**
     * 买家退货申请
     * @param request
     * @param response
     * @param id
     * @param view
     * @return
     */
    @RequestMapping({"/order_return_apply"})
    public String order_return_apply(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String id, String view){
        String ret = "/order_return_apply";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
            if ((view != null) && (!view.equals("")))
                model.addAttribute("view", Boolean.valueOf(true));
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 买家退货申请保存
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param return_content
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_return_apply_save"})
    public String order_return_apply_save(Model model,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          String id, String currentPage,
                                          String return_content) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            obj.setOrderStatus(45);
            obj.setReturnContent(return_content);
            this.orderFormService.update(obj);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_toseller_order_return_apply_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_toseller_order_return_apply_notify");
            }
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 买家退商品流信息
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_return_ship"})
    public String order_return_ship(Model model,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          String id, String currentPage){
        String ret = "/order_return_ship";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (obj.getUserId().equals(user==null?0:user.getId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);

            Map map = new HashMap();
            map.put("of_id", CommUtil.null2Long(id));
            List<GsGoodsCart> goodsCarts = this.goodsCartService.findByCondition(map);
//                    "select obj from GoodsCart obj where obj.of.id = :oid",
            List deliveryGoods = new ArrayList();
            boolean physicalGoods = false;
            for (GsGoodsCart gc : goodsCarts){
                int type = CommUtil.null2Int(this.goodsService.findSingleColumnById(gc.getId(),"goods_choice_type"));
                if (type == 1)
                    deliveryGoods.add(gc);
                else{
                    physicalGoods = true;
                }
            }
            Map params = new HashMap();
            params.put("company_status", Integer.valueOf(0));
            params.put("orderBy","company_sequence");
            params.put("orderType","asc");
            List expressCompanys = this.expressCompayService.findByCondition(params);
//            "select obj from ExpressCompany obj where obj.company_status=:status order by company_sequence asc",
            model.addAttribute("expressCompanys", expressCompanys);
            model.addAttribute("physicalGoods", Boolean.valueOf(physicalGoods));
            model.addAttribute("deliveryGoods", deliveryGoods);
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return ret;
    }

    /**
     * 买家退商品流信息保存
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param ec_id
     * @param return_shipCode
     * @return
     */
    @RequestMapping({"/buyer/order_return_ship_save.htm"})
    public String order_return_ship_save(Model model,
                                         HttpServletRequest request,
                                         HttpServletResponse response,
                                         String id, String currentPage,
                                         String ec_id, String return_shipCode){
        String ret = "/order_return_apply_view";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        GsExpressCompany ec = this.expressCompayService.findOne(CommUtil.null2Long(ec_id));
        obj.setReturnEcId(ec.getId());
        obj.setShipcode(return_shipCode);
        this.orderFormService.update(obj);

        return "redirect:order?currentPage=" + currentPage;
    }

    private TransInfo query_ship_getData(String id){
        TransInfo info = new TransInfo();
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        GsExpressCompany ec = this.expressCompayService.findOne(obj.getEcId());
        try {
            String query_url = "http://api.kuaidi100.com/api?id=" +
                    this.systemConfigService.getConfig().getKuaidi_id() +
                    "&com=" + (
                    ec != null ? ec.getCompanyMark() : "") +
                    "&nu=" + obj.getShipcode() + "&show=0&muti=1&order=asc";
            URL url = new URL(query_url);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            String type = URLConnection.guessContentTypeFromStream(urlStream);
            String charSet = null;
            if (type == null)
                type = con.getContentType();
            if ((type == null) || (type.trim().length() == 0) || (type.trim().indexOf("text/html") < 0))
                return info;
            if (type.indexOf("charset=") > 0)
                charSet = type.substring(type.indexOf("charset=") + 8);
            byte[] b = new byte[10000];
            int numRead = urlStream.read(b);
            String content = new String(b, 0, numRead, charSet);
            while (numRead != -1){
                numRead = urlStream.read(b);
                if (numRead == -1)
                    continue;
                String newContent = new String(b, 0, numRead, charSet);
                content = content + newContent;
            }

            info = JsonUtils.jsonToPojo(content,TransInfo.class);
            urlStream.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return info;
    }


    /**
     * wap服务中心
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/service_center" })
    public String service_center(Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response){
        String ret = "wap/service_center";

        return ret;
    }

    private void send_email(HttpServletRequest request, GsOrderformWithBLOBs order, String mark) throws Exception {
//        com.wemall.foundation.domain.Template template = this.templateService.getObjByProperty("mark", mark);
//        if (template.isOpen()){
//            String email = order.getStore().getUser().getEmail();
//            String subject = template.getTitle();
//            String path = request.getSession().getServletContext()
//                    .getRealPath("/") +
//                    "/vm/";
//            PrintWriter pwrite = new PrintWriter(
//                    new OutputStreamWriter(new FileOutputStream(path + "msg.vm", false), "UTF-8"));
//            pwrite.print(template.getContent());
//            pwrite.flush();
//            pwrite.close();
//
//            Properties p = new Properties();
//            p.setProperty("file.resource.loader.path",
//                    request.getRealPath("/") + "vm" + File.separator);
//            p.setProperty("input.encoding", "UTF-8");
//            p.setProperty("output.encoding", "UTF-8");
//            Velocity.init(p);
//            org.apache.velocity.Template blank = Velocity.getTemplate("msg.vm",
//                    "UTF-8");
//            VelocityContext context = new VelocityContext();
//            context.put("buyer", order.getUser());
//            context.put("seller", order.getStore().getUser());
//            context.put("config", this.configService.getSysConfig());
//            context.put("send_time", CommUtil.formatLongDate(new Date()));
//            context.put("webPath", CommUtil.getURL(request));
//            context.put("order", order);
//            StringWriter writer = new StringWriter();
//            blank.merge(context, writer);
//
//            String content = writer.toString();
//            this.msgTools.sendEmail(email, subject, content);
//        }
    }

    private void send_sms(HttpServletRequest request, GsOrderformWithBLOBs order, String mobile, String mark) throws Exception {
//        com.wemall.foundation.domain.Template template = this.templateService.getObjByProperty("mark", mark);
//        if (template.isOpen()){
//            String path = request.getSession().getServletContext()
//                    .getRealPath("/") +
//                    "/vm/";
//            PrintWriter pwrite = new PrintWriter(
//                    new OutputStreamWriter(new FileOutputStream(path + "msg.vm", false), "UTF-8"));
//            pwrite.print(template.getContent());
//            pwrite.flush();
//            pwrite.close();
//
//            Properties p = new Properties();
//            p.setProperty("file.resource.loader.path",
//                    request.getRealPath("/") + "vm" + File.separator);
//            p.setProperty("input.encoding", "UTF-8");
//            p.setProperty("output.encoding", "UTF-8");
//            Velocity.init(p);
//            org.apache.velocity.Template blank = Velocity.getTemplate("msg.vm",
//                    "UTF-8");
//            VelocityContext context = new VelocityContext();
//            context.put("buyer", order.getUser());
//            context.put("seller", order.getStore().getUser());
//            context.put("config", this.configService.getSysConfig());
//            context.put("send_time", CommUtil.formatLongDate(new Date()));
//            context.put("webPath", CommUtil.getURL(request));
//            context.put("order", order);
//            StringWriter writer = new StringWriter();
//            blank.merge(context, writer);
//
//            String content = writer.toString();
//            this.msgTools.sendSMS(mobile, content);
//        }
    }

}
