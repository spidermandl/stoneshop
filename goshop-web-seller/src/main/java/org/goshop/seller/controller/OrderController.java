package org.goshop.seller.controller;

import com.github.pagehelper.PageInfo;
import org.goshop.common.pojo.TransInfo;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.goods.i.GoodsEvaluateService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.i.GroupGoodsService;
import org.goshop.goods.pojo.*;
import org.goshop.order.i.*;
import org.goshop.order.pojo.*;
import org.goshop.pay.i.PaymentService;
import org.goshop.pay.i.RefundLogService;
import org.goshop.pay.i.RefundService;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;
import org.goshop.pay.pojo.GsRefundLog;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.goshop.tools.pay.alipay.config.AlipayConfig;
import org.goshop.tools.pay.alipay.util.AlipaySubmit;
import org.goshop.tools.service.PaymentTools;
import org.goshop.tools.service.StoreViewTools;
import org.goshop.users.i.MemberService;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.Member;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 卖家订单控制器
 */
@Controller
public class OrderController {

    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderFormLogService orderFormLogService;
    @Autowired
    private GoodsCartService goodsCartService;
    @Autowired
    private GoodsEvaluateService goodsEvaluateService;
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GroupGoodsService groupGoodsService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ExpressCompanyService expressCompanyService;
    @Autowired
    private RefundLogService refundLogService;
    @Autowired
    private GoodsReturnService goodsReturnService;
    @Autowired
    private GoodsReturnItemService goodsReturnItemService;
    @Autowired
    private GoodsReturnLogService goodsReturnLogService;
    @Autowired
    private IntergralLogService integralLogService;
    @Autowired
    private StoreJoinService storeJoinService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreAreaService storeAreaService;
    @Autowired
    private OrderAddressService orderAddressService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private ExpressCompanyService expressCompayService;
//    @Autowired
//    private ITemplateService templateService;

    @Autowired
    private StoreViewTools storeViewTools;
    @Autowired
    private PaymentTools paymentTools;
//    @Autowired
//    private MsgTools msgTools;


    private static final String PREFIX_URI = "order";
    /**
     * 卖家订单列表
     * @param user
     * @param model
     * @param request
     * @param response
     * @param currentPage
     * @param order_status
     * @param order_id
     * @param beginTime
     * @param endTime
     * @param buyer_userName
     * @return
     */
    @RequestMapping({"/order"})
    public String order(@CurrentUser User user,
                              Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String currentPage, String order_status,
                              String order_id, String beginTime,
                              String endTime, String buyer_userName){
        String ret = "seller_order";
        Map param = new HashMap();
        param.put("orderBy","addTime");
        param.put("orderType","desc");
        param.put("store_id",this.storeJoinService.getCurrentStore(user).getStoreId());
        if (!CommUtil.null2String(order_status).equals("")){
            if (order_status.equals("order_submit")){
                param.put("order_status",Integer.valueOf(10));
            }
            if (order_status.equals("order_pay")){
                param.put("order_status",Integer.valueOf(20));
            }
            if (order_status.equals("order_shipping")){
                param.put("order_status",Integer.valueOf(30));
            }
            if (order_status.equals("order_receive")){
                param.put("order_status",Integer.valueOf(40));
            }
            if (order_status.equals("order_evaluate")){
                param.put("order_status",Integer.valueOf(50));
            }
            if (order_status.equals("order_finish")){
                param.put("order_status",Integer.valueOf(60));
            }
            if (order_status.equals("order_cancel")){
                param.put("order_status",Integer.valueOf(0));
            }
        }
        if (!CommUtil.null2String(order_id).equals("")){
            param.put("order_id",order_id);
        }
        if (!CommUtil.null2String(beginTime).equals("")){
            param.put("beginTime",CommUtil.formatDate(beginTime));
        }
        if (!CommUtil.null2String(endTime).equals("")){
            param.put("endTime",CommUtil.formatDate(endTime));
        }
        if (!CommUtil.null2String(buyer_userName).equals("")){
            User buyer = this.userService.findByUserName(buyer_userName);
            if (buyer!=null)
                param.put("user_id",buyer.getId());
        }
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
        }
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);
        model.addAttribute("storeViewTools", this.storeViewTools);
        model.addAttribute("order_id", order_id);
        model.addAttribute("order_status", order_status == null ? "all" : order_status);
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("buyer_userName", buyer_userName);
        model.addAttribute("CommUtil",new CommUtil());

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家订单详情
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/order_view"})
    public String order_view(@CurrentUser User user,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             String id){
        String ret = "order_view";
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

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            TransInfo transInfo = query_ship_getData(CommUtil.null2String(obj.getId()));
            model.addAttribute("transInfo", transInfo);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您店铺中没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }
        model.addAttribute("CommUtil",new CommUtil());
        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家取消订单
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_cancel"})
    public String order_cancel(@CurrentUser User user,
                               Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String id, String currentPage){
        String ret = "seller_order_cancel";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家取消订单保存
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
    public String order_cancel_save(@CurrentUser User user,
                                    Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String id, String currentPage,
                                    String state_info,
                                    String other_state_info) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);
        // 判断订单所属店铺是否是当前登录用户的店铺
        if (obj.getStoreId().equals(store.getStoreId())){
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
                send_email(request, obj, "email_tobuyer_order_cancel_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_tobuyer_order_cancel_notify");
            }
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 卖家调整订单费用
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_fee"})
    public String order_fee(@CurrentUser User user,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String id, String currentPage){
        String ret = "seller_order_fee";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家调整订单费用保存
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param goods_amount
     * @param ship_price
     * @param totalPrice
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_fee_save"})
    public String order_fee_save(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String id, String currentPage,
                                 String goods_amount, String ship_price,
                                 String totalPrice) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        // 判断订单所属店铺是否是当前登录用户的店铺
        if (obj.getStoreId().equals(store.getStoreId())){
            obj.setGoodsAmount(BigDecimal.valueOf(CommUtil.null2Double(goods_amount)));
            obj.setShipPrice(BigDecimal.valueOf(CommUtil.null2Double(ship_price)));
            obj.setTotalprice(BigDecimal.valueOf(CommUtil.null2Double(totalPrice)));
            this.orderFormService.update(obj);

            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("调整订单费用");
            ofl.setLogUserId(user.getId());
            ofl.setOfId(obj.getId());
            this.orderFormLogService.save(ofl);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_tobuyer_order_update_fee_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_tobuyer_order_fee_notify");
            }
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 线下付款确认
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/seller_order_outline"})
    public String seller_order_outline(@CurrentUser User user,
                                       Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String id, String currentPage){
        String ret = "seller_order_outline";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        // 判断订单所属店铺是否是当前登录用户的店铺
        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 线下付款确认保存
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param state_info
     * @return
     * @throws Exception
     */
    @RequestMapping({"/seller_order_outline_save"})
    public String seller_order_outline_save(@CurrentUser User user,
                                            Model model,
                                            HttpServletRequest request,
                                            HttpServletResponse response,
                                            String id, String currentPage,
                                            String state_info) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        // 判断订单所属店铺是否是当前登录用户的店铺
        if (obj.getStoreId().equals(store.getStoreId())){
            obj.setOrderStatus(20);
            this.orderFormService.update(obj);

            Map param = new HashMap();
            param.put("of_id",obj.getId());
            List<GsGoodsCart> goodsCarts = this.goodsCartService.findByCondition(param);
            for (GsGoodsCart gc : goodsCarts){
                GsGoodsWithBLOBs goods = this.goodsService.findBasicOne(gc.getGoodsId());
                if ((goods.getGroup() != null) && (goods.getGroupBuy() == 2)){
                    for (GsGroupGoods gg : goods.getGroup_goods_list()){
                        if (gg.getGroupId().equals(goods.getGroup().getId())){
                            gg.setGgCount(gg.getGgCount() - gc.getCount());
                            gg.setGgDefCount(gg.getGgDefCount() + gc.getCount());
                            this.groupGoodsService.update(gg);
                        }
                    }
                }
                List gsps = new ArrayList();
                List<GsGoodsSpecProperty> sps = this.goodsCartService.findSpecPropertByGoodsCartId(gc.getId());
                for (GsGoodsSpecProperty gsp : sps){
                    gsps.add(gsp.getId().toString());
                }
                String[] gsp_list = new String[gsps.size()];
                gsps.toArray(gsp_list);
                goods.setGoodsSalenum(goods.getGoodsSalenum() + gc.getCount());
                Map temp;
                if (goods.getInventoryType().equals("all")){
                    goods.setGoodsInventory(goods.getGoodsInventory() - gc.getCount());
                }else{
                    List list = JsonUtils.jsonToList(goods.getGoodsInventoryDetail(),ArrayList.class);
                    for (Iterator localIterator4 = list.iterator(); localIterator4.hasNext();){
                        temp = (Map) localIterator4.next();
                        String[] temp_ids = CommUtil.null2String(temp.get("id")).split("_");
                        Arrays.sort(temp_ids);
                        Arrays.sort(gsp_list);
                        if (Arrays.equals(temp_ids, gsp_list)){
                            temp.put("count",
                                    Integer.valueOf(CommUtil.null2Int(temp.get("count")) - gc.getCount()));
                        }
                    }
                    goods.setGoodsInventoryDetail(JsonUtils.objectToJson(list));
                }
                for (GsGroupGoods gg : goods.getGroup_goods_list()){
                    if ((!gg.getGroupId().equals(goods.getGroup().getId())) || (gg.getGgCount() != 0))
                        continue;
                    goods.setGroupBuy(3);
                }

                this.goodsService.update(goods);
            }
            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("确认线下付款");
            ofl.setLogUserId(user==null?0:user.getId());
            ofl.setOfId(obj.getId());
            ofl.setStateInfo(state_info);
            this.orderFormLogService.save(ofl);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_tobuyer_order_outline_pay_ok_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_tobuyer_order_outline_pay_ok_notify");
            }
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 卖家确认发货
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_shipping"})
    public String order_shipping(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String id, String currentPage){
        String ret = "seller_order_shipping";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);

            Map map = new HashMap();
            map.put("of_id", CommUtil.null2Long(id));
            List<GsGoodsCart> goodsCarts = this.goodsCartService.findByCondition(map);
//                    "select obj from GoodsCart obj where obj.of.id = :oid",
            List deliveryGoods = new ArrayList();
            boolean physicalGoods = false;
            for (GsGoodsCart gc : goodsCarts){
                gc.setGoods(this.goodsService.findBasicOne(gc.getGoodsId()));
                if (gc.getGoods().getGoodsChoiceType() == 1)
                    deliveryGoods.add(gc);
                else{
                    physicalGoods = true;
                }
            }
            Map params = new HashMap();
            params.put("orderBy","company_sequence");
            params.put("orderType","asc");
            params.put("status", Integer.valueOf(0));
            List expressCompanys = this.expressCompanyService.findByCondition(params);
//                    .query("select obj from ExpressCompany obj where obj.company_status=:status order by company_sequence asc",
            model.addAttribute("expressCompanys", expressCompanys);
            model.addAttribute("physicalGoods", Boolean.valueOf(physicalGoods));
            model.addAttribute("deliveryGoods", deliveryGoods);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家确认发货保存
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param shipCode
     * @param state_info
     * @param order_seller_intro
     * @param ec_id
     * @return
     * @throws Exception
     */
    @RequestMapping({"/order_shipping_save"})
    public String order_shipping_save(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id, String currentPage,
                                      String shipCode, String state_info,
                                      String order_seller_intro,
                                      String ec_id) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        GsExpressCompany ec = this.expressCompanyService.findOne(CommUtil.null2Long(ec_id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            obj.setOrderStatus(30);
            obj.setShipcode(shipCode);
            obj.setShiptime(new Date());
            obj.setEc(ec);
            obj.setOrderSellerIntro(order_seller_intro);
            this.orderFormService.update(obj);
            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("确认发货");
            ofl.setStateInfo(state_info);
            ofl.setLogUserId(user.getId());
            ofl.setOfId(obj.getId());
            this.orderFormLogService.save(ofl);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_tobuyer_order_ship_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_tobuyer_order_ship_notify");
            }

            GsPaymentWithBLOBs payment = this.paymentService.findOne(obj.getPaymentId());
            if (payment.getMark().equals("alipay")){
                boolean synch = false;
                String safe_key = "";
                String partner = "";

                if (!CommUtil.null2String(payment.getSafekey()).equals("")){
                    if (!CommUtil.null2String(payment.getPartner()).equals("")){
                        safe_key = payment.getSafekey();
                        partner = payment.getPartner();
                        synch = true;
                    }
                }
                Map params = new HashMap();
                params.put("type", "admin");
                params.put("mark", "alipay");
                List<GsPaymentWithBLOBs> payments = this.paymentService.findByCondition(params);
//                        .query("select obj from Payment obj where obj.type=:type and obj.mark=:mark",
                if ((payments.size() > 0) && (payments.get(0) != null)){
                    if (!CommUtil.null2String((payments.get(0)).getSafekey()).equals("")){
                        if (!CommUtil.null2String((payments.get(0)).getPartner()).equals("")){
                            safe_key = (payments.get(0)).getSafekey();
                            partner = (payments.get(0)).getPartner();
                            synch = true;
                        }
                    }
                }
                if (synch){
                    AlipayConfig config = new AlipayConfig();
                    config.setKey(safe_key);
                    config.setPartner(partner);
                    Map sParaTemp = new HashMap();
                    sParaTemp.put("service", "send_goods_confirm_by_platform");
                    sParaTemp.put("partner", config.getPartner());
                    sParaTemp.put("_input_charset", config.getInput_charset());
                    sParaTemp.put("trade_no", obj.getOutOrderId());
                    sParaTemp.put("logistics_name", ec.getCompanyName());
                    sParaTemp.put("invoice_no", shipCode);
                    sParaTemp.put("transport_type", ec.getCompanyType());

                    String str1 = AlipaySubmit.buildRequest(config, "web", sParaTemp, "", "");
                }
            }
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 卖家修改物流
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_shipping_code"})
    public String order_shipping_code(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id, String currentPage){
        String ret = "seller_order_shipping_code";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家修改物流保存
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param shipCode
     * @param state_info
     * @return
     */
    @RequestMapping({"/order_shipping_code_save"})
    public String order_shipping_code_save(@CurrentUser User user,
                                           Model model,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           String id, String currentPage,
                                           String shipCode, String state_info){
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            obj.setShipcode(shipCode);
            this.orderFormService.update(obj);
            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("修改物流信息");
            ofl.setStateInfo(state_info);
            ofl.setLogUserId(user.getId());
            ofl.setOfId(obj.getId());
            this.orderFormLogService.save(ofl);
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 卖家退款
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_refund"})
    public String order_refund(@CurrentUser User user,
                               Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String id, String currentPage){
        String ret = "seller_order_refund";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("paymentTools", this.paymentTools);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家退款保存
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param refund
     * @param refund_log
     * @param refund_type
     * @return
     */
    @RequestMapping({"/order_refund_save"})
    public String order_refund_save(@CurrentUser User user,
                                    Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String id, String currentPage,
                                    String refund, String refund_log,
                                    String refund_type){
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            obj.setRefund(BigDecimal.valueOf(CommUtil.add(obj.getRefund(),
                    refund)));
            this.orderFormService.update(obj);

            String type = "预存款";
            if (type.equals(refund_type)){
                Member seller = this.memberService.findByUserId(
                        this.storeService.findBasicOne(obj.getStoreId()).getMemberId());
                seller.setAvailablePredeposit(BigDecimal.valueOf(
                        CommUtil.subtract(seller.getAvailablePredeposit(), BigDecimal.valueOf(CommUtil.null2Double(refund)))));
                this.memberService.update(null,seller);
                Member buyer = this.memberService.findByUserId((obj.getUserId()));
                buyer.setAvailablePredeposit(BigDecimal.valueOf(CommUtil.add(
                        buyer.getAvailablePredeposit(), BigDecimal.valueOf(CommUtil.null2Double(refund)))));
                this.memberService.update(null,buyer);
            }
            GsRefundLog log = new GsRefundLog();
            log.setAddtime(new Date());
            log.setRefundId(CommUtil.formatTime("yyyyMMddHHmmss", new Date()) + obj.getUserId().toString());
            log.setOfId(obj.getId());
            log.setRefund(BigDecimal.valueOf(CommUtil.null2Double(refund)));
            log.setRefundLog(refund_log);
            log.setRefundType(refund_type);
            log.setRefundUserId(user.getId());
            this.refundLogService.save(log);
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 卖家退货
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_return"})
    public String order_return(@CurrentUser User user,
                               Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String id, String currentPage){
        String ret = "seller_order_return";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家退货保存
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param return_info
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_return_save"})
    public String order_return_save(@CurrentUser User user,
                                    Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String id, String return_info,
                                    String currentPage){
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            Enumeration enum1 = request.getParameterNames();
            GsGoodsReturn gr = new GsGoodsReturn();
            gr.setAddtime(new Date());
            gr.setOfId(obj.getId());
            gr.setReturnId(CommUtil.formatTime("yyyyMMddHHmmss", new Date()) + obj.getId().toString());
            gr.setUserId(user.getId());
            gr.setReturnInfo(return_info);
            this.goodsReturnService.save(gr);
            while (enum1.hasMoreElements()){
                String paramName = (String) enum1.nextElement();
                if (paramName.indexOf("refund_count_") >= 0){
                    GsGoodsCart gc = this.goodsCartService.findOne(CommUtil.null2Long(paramName.substring(13)));
                    int count = CommUtil.null2Int(request.getParameter(paramName));
                    if (count > 0){
                        gc.setCount(gc.getCount() - count);
                        this.goodsCartService.update(gc);
                        GsGoodsReturnitem item = new GsGoodsReturnitem();
                        item.setAddtime(new Date());
                        item.setCount(count);
                        item.setGoodsId(gc.getGoodsId());
                        item.setGrId(gr.getId());
                        item.setSpecInfo(gc.getSpecInfo());
                        this.goodsReturnItemService.save(item,gc);

                        GsGoodsWithBLOBs goods = this.goodsService.findBasicOne(gc.getGoodsId());
                        if (goods.getInventoryType().equals("all")){
                            goods.setGoodsInventory(goods.getGoodsInventory() + count);
                        }else{
                            List gsps = new ArrayList();
                            List<GsGoodsSpecProperty> return_gsps = this.goodsCartService.findSpecPropertByGoodsCartId(gc.getId());
                            for (GsGoodsSpecProperty gsp : return_gsps){
                                gsps.add(gsp.getId().toString());
                            }
                            String[] gsp_list = new String[gsps.size()];
                            gsps.toArray(gsp_list);
                            List<Map> list = (List) JsonUtils.jsonToList(goods.getGoodsInventoryDetail(),ArrayList.class);

                            for (Map temp : list){
                                String[] temp_ids = CommUtil.null2String(temp.get("id")).split("_");
                                Arrays.sort(temp_ids);
                                Arrays.sort(gsp_list);
                                if (Arrays.equals(temp_ids, gsp_list)){
                                    temp.put("count", Integer.valueOf(CommUtil.null2Int(temp.get("count")) + count));
                                }
                            }
                            goods.setGoodsInventoryDetail(JsonUtils.objectToJson(list));
                        }
                        goods.setGoodsSalenum(goods.getGoodsSalenum() - count);
                        this.goodsService.update(goods);

                        GsPaymentWithBLOBs payment = this.paymentService.findOne(obj.getPaymentId());
                        if (payment.getMark().equals("balance")){
                            BigDecimal balance = goods.getGoodsCurrentPrice();
                            Member seller = this.memberService.findByUserId(user.getId());

                            if (this.systemConfigService.getConfig().getBalance_fenrun() == 1){
                                Map params = new HashMap();
                                params.put("type", "admin");
                                params.put("mark", "balance");
                                List<GsPaymentWithBLOBs> payments = this.paymentService.findByCondition(params);
//                                        .query("select obj from Payment obj where obj.type=:type and obj.mark=:mark"
                                GsPaymentWithBLOBs shop_payment = new GsPaymentWithBLOBs();
                                if (payments.size() > 0){
                                    shop_payment = payments.get(0);
                                }

                                double shop_availableBalance =
                                        CommUtil.null2Double(balance) * CommUtil.null2Double(shop_payment.getBalanceDivideRate());
                                balance = BigDecimal.valueOf(CommUtil.null2Double(balance) - shop_availableBalance);
                            }
                            seller.setAvailablePredeposit(
                                    BigDecimal.valueOf(CommUtil.subtract(seller.getAvailablePredeposit(), balance)));
                            this.memberService.update(null,seller);
                            Member buyer = this.memberService.findByUserId(obj.getUserId());
                            buyer.setAvailablePredeposit(
                                    BigDecimal.valueOf(CommUtil.add(buyer.getAvailablePredeposit(), balance)));
                            this.memberService.update(null,seller);
                        }
                    }
                }
            }
            GsGoodsReturnlog grl = new GsGoodsReturnlog();
            grl.setAddtime(new Date());
            grl.setGrId(gr.getId());
            grl.setOfId(obj.getId());
            grl.setReturnUserId(user.getId());
            this.goodsReturnLogService.save(grl);
        }

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 卖家评价
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/order_evaluate"})
    public String order_evaluate(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String id, String currentPage){
        String ret = "seller_order_evaluate";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            model.addAttribute("currentPage", currentPage);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家评价保存
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param evaluate_info
     * @param evaluate_seller_val
     * @return
     */
    @RequestMapping({"/order_evaluate_save"})
    public String order_evaluate_save(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id, String evaluate_info,
                                      String evaluate_seller_val){
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            if (obj.getOrderStatus() == 50){
                obj.setOrderStatus(60);
                obj.setFinishtime(new Date());
                this.orderFormService.update(obj);
                Enumeration enum1 = request.getParameterNames();
                List maps = new ArrayList();
                while (enum1.hasMoreElements()){
                    String paramName = (String) enum1.nextElement();
                    if (paramName.indexOf("evaluate_seller_val") >= 0){
                        String value = request.getParameter(paramName);
                        GsGoodsEvaluateWithBLOBs eva = this.goodsEvaluateService.findOne(CommUtil.null2Long(paramName.substring(19)));
                        eva.setEvaluateSellerVal(CommUtil.null2Int(request.getParameter(paramName)));
                        eva.setEvaluateSellerUserId(user.getId());
                        eva.setEvaluateSellerInfo(request.getParameter("evaluate_info" + eva.getId().toString()));
                        eva.setEvaluateSellerTime(new Date());
                        this.goodsEvaluateService.update(eva);
                        Member member = this.memberService.findByUserId(obj.getUserId());
                        member.setMemberCredit(member.getMemberCredit() + eva.getEvaluateSellerVal());

                        if (this.systemConfigService.getConfig().getIntegral()){
                            int integral = 0;

                            if (this.systemConfigService.getConfig().getConsumptionRatio() > 0){
                                integral = CommUtil.null2Int(Double.valueOf(CommUtil.div(obj.getTotalprice(),
                                        Integer.valueOf(this.systemConfigService.getConfig().getConsumptionRatio()))));
                            }
                            integral = integral > this.systemConfigService.getConfig().getEveryIndentLimit() ?
                                        this.systemConfigService.getConfig().getEveryIndentLimit() : integral;
                            member.setMemberPoints(member.getMemberPoints() + integral);
                            this.userService.update(user);
                            GsIntegrallog log = new GsIntegrallog();
                            log.setAddtime(new Date());
                            log.setContent("订单" + obj.getOrderId() + "完成增加" + integral + "分");
                            log.setIntegral(integral);
                            log.setIntegralUserId(user.getId());
                            log.setType("login");
                            this.integralLogService.save(log);
                        }
                    }
                }
            }

            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("评价订单");
            ofl.setLogUserId(user.getId());
            ofl.setOfId(obj.getId());
            this.orderFormLogService.save(ofl);
        }
        String ret = "success";
        model.addAttribute("op_title", "订单评价成功！");
        model.addAttribute("url", CommUtil.getURL(request) + "/order");

        return ret;
    }

    /**
     * 打印订单
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/order_print"})
    public String order_print(@CurrentUser User user,
                              Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String id){
        String ret = "order_print";
        if ((id != null) && (!id.equals(""))){
            GsOrderformWithBLOBs orderform = this.orderFormService.findOne(CommUtil.null2Long(id));
            model.addAttribute("obj", orderform);
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家物流详情
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/ship_view"})
    public String order_ship_view(@CurrentUser User user,
                                  Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String id){
        String ret = "order_ship_view";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
            TransInfo transInfo = query_ship_getData(
                    CommUtil.null2String(obj.getId()));
            model.addAttribute("transInfo", transInfo);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您店铺中没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }


    /**
     * 卖家物流详情
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/order_query_userinfor"})
    public String seller_query_userinfor(@CurrentUser User user,
                                         Model model,
                                         HttpServletRequest request,
                                         HttpServletResponse response,
                                         String id){
        String ret = "seller_query_userinfor";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        model.addAttribute("obj", obj);

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 买家退货申请详情
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/seller_order_return_apply_view"})
    public String seller_order_return_apply_view(@CurrentUser User user,
                                                 Model model,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 String id, String currentPage){
        String ret = "seller_order_return_apply_view";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            model.addAttribute("obj", obj);
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 卖家保存退货申请
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @param gr_id
     * @param currentPage
     * @param mark
     * @return
     * @throws Exception
     */
    @RequestMapping({"/seller_order_return"})
    public String seller_order_return(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id, String gr_id,
                                      String currentPage, String mark) throws Exception {
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
                Enumeration enum1 = request.getParameterNames();
                GsGoodsReturn gr = this.goodsReturnService.findOne(CommUtil.null2Long(gr_id));
                obj.setOrderStatus(46);
                int auto_order_return = this.systemConfigService.getConfig().getAuto_order_return();
                Calendar cal = Calendar.getInstance();
                cal.add(6, auto_order_return);
                obj.setReturnShiptime(cal.getTime());
                if (this.systemConfigService.getConfig().getEmailEnable()){
                    send_email(request, obj, "email_tobuyer_order_return_apply_ok_notify");
                }
                if (this.systemConfigService.getConfig().getSmsEnbale()) {
                    Member m = this.memberService.findByUserId(user.getId());
                    send_sms(request, obj, m.getMemberMobile(), "sms_tobuyer_order_return_apply_ok_notify");
                }
        }else{
            obj.setOrderStatus(48);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, obj, "email_tobuyer_order_return_apply_refuse_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                Member m = this.memberService.findByUserId(user.getId());
                send_sms(request, obj, m.getMemberMobile(), "sms_tobuyer_order_return_apply_refuse_notify");
            }
        }
        this.orderFormService.update(obj);

        return "redirect:order?currentPage=" + currentPage;
    }

    /**
     * 确认买家退货
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/seller_order_return_confirm"})
    public String seller_order_return_confirm(@CurrentUser User user,
                                              Model model,
                                              HttpServletRequest request,
                                              HttpServletResponse response,
                                              String id){
        String ret = "error";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            obj.setOrderStatus(47);
            this.orderFormService.update(obj);
            ret = "success";
            model.addAttribute("op_title", "您已成功确认退货");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }else{
            model.addAttribute("op_title", "您店铺中没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    /**
     * 买家退商品流详情
     * @param user
     * @param model
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/seller_order_return_ship_view"})
    public String seller_order_return_ship_view(@CurrentUser User user,
                                                Model model,
                                                HttpServletRequest request,
                                                HttpServletResponse response,
                                                String id){
        String ret = "error";
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);

        if (obj.getStoreId().equals(store.getStoreId())){
            if ((obj.getReturnShipcode() != null) &&
                    (!obj.getReturnShipcode().equals("")) &&
                    (obj.getReturnEcId() != null)){
                ret = "/seller_order_return_ship_view";
                TransInfo transInfo = query_return_ship(
                        CommUtil.null2String(obj.getId()));
                model.addAttribute("obj", obj);
                model.addAttribute("transInfo", transInfo);
            }else{
                model.addAttribute("op_title", "买家没有提交退商品流信息");
                model.addAttribute("url", CommUtil.getURL(request) + "/order");
            }
        }else{
            model.addAttribute("op_title", "您店铺中没有编号为" + id + "的订单！");
            model.addAttribute("url", CommUtil.getURL(request) + "/order");
        }

        return PREFIX_URI+"/"+ret;
    }

    private TransInfo query_ship_getData(String id){
        TransInfo info = new TransInfo();
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        try {
            URL url = new URL("http://api.kuaidi100.com/api?id=" +
                    this.systemConfigService.getConfig().getKuaidi_id() +
                    "&com=" + (obj.getEc() != null ? obj.getEc().getCompanyMark() : "") +
                    "&nu=" + obj.getShipcode() + "&show=0&muti=1&order=asc");
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

            /*byte[] b = new byte[10000];
            int numRead = urlStream.read(b);
            String content = new String(b, 0, numRead, charSet);
            while (numRead != -1){
              numRead = urlStream.read(b);
              if (numRead == -1)
                continue;
              String newContent = new String(b, 0, numRead, charSet);
              content = content + newContent;
            }*/

            String line;
            StringBuffer stringBuffer = new StringBuffer();
            Reader reader = new InputStreamReader(urlStream, charSet);
            //增加缓冲功能
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            if (bufferedReader != null){
                bufferedReader.close();
            }
            String content = stringBuffer.toString();

            info = JsonUtils.jsonToPojo(content,TransInfo.class);
            urlStream.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return info;
    }

    private TransInfo query_return_ship(String id){
        TransInfo info = new TransInfo();
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(id));
        try {
            GsExpressCompany company = obj.getReturnEcId()==null?null:this.expressCompanyService.findOne(obj.getReturnEcId());
            URL url = new URL("http://api.kuaidi100.com/api?id=" +
                    this.systemConfigService.getConfig().getKuaidi_id() +
                    "&com=" + (company != null ? company.getCompanyMark() : "") + "&nu=" +
                    obj.getShipcode() + "&show=0&muti=1&order=asc");
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

    private void send_email(HttpServletRequest request, GsOrderformWithBLOBs order, String mark) throws Exception {
//        com.wemall.foundation.domain.Template template = this.templateService.getObjByProperty("mark", mark);
//        if ((template != null) && (template.isOpen())){
//            String email = order.getUser().getEmail();
//            String subject = template.getTitle();
//            String path = request.getSession().getServletContext()
//                    .getRealPath("") +
//                    File.separator + "vm" + File.separator;
//            if (!CommUtil.fileExist(path)){
//                CommUtil.createFolder(path);
//            }
//            PrintWriter pwrite = new PrintWriter(
//                    new OutputStreamWriter(new FileOutputStream(path + "msg.vm", false), "UTF-8"));
//            pwrite.print(template.getContent());
//            pwrite.flush();
//            pwrite.close();
//
//            Properties p = new Properties();
//            p.setProperty("file.resource.loader.path",
//                    request.getRealPath("") + File.separator + "vm" +
//                            File.separator);
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
//        if ((template != null) && (template.isOpen())){
//            String path = request.getSession().getServletContext()
//                    .getRealPath("") +
//                    File.separator + "vm" + File.separator;
//            if (!CommUtil.fileExist(path)){
//                CommUtil.createFolder(path);
//            }
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
