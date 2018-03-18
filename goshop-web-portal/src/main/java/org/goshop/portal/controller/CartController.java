package org.goshop.portal.controller;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.dom4j.DocumentException;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.common.web.utils.QRCodeEncoderHandler;
import org.goshop.common.web.utils.WebForm;
import org.goshop.goods.i.GoodsPropertyService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.i.GroupGoodsService;
import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsSpecProperty;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.goods.pojo.GsGroupGoods;
import org.goshop.order.i.*;
import org.goshop.order.pojo.*;
import org.goshop.pay.i.PaymentService;
import org.goshop.pay.i.PredepositLogService;
import org.goshop.pay.pojo.GsPayment;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;
import org.goshop.pay.pojo.GsPredepositLog;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.GsArea;
import org.goshop.store.pojo.Store;
import org.goshop.tools.WxAdvancedUtil;
import org.goshop.tools.WxCommonUtil;
import org.goshop.tools.bean.WxOauth2Token;
import org.goshop.tools.bean.WxToken;
import org.goshop.tools.pay.alipay.config.AlipayConfig;
import org.goshop.tools.pay.alipay.util.AlipaySubmit;
import org.goshop.tools.service.GoodsViewTools;
import org.goshop.tools.service.PayTools;
import org.goshop.tools.service.PaymentTools;
import org.goshop.tools.service.TransportTools;
import org.goshop.users.i.MemberService;
import org.goshop.users.pojo.Member;
import org.goshop.users.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Desmond on 31/01/2018.
 * 购物车
 */
@Controller
public class CartController extends BaseController{

    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsPropertyService goodsPropertyService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderAddressService orderAddressService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private GroupGoodsService groupGoodsService;
    @Autowired
    private StoreAreaService storeAreaService;
    @Autowired
    private OrderFormLogService orderFormLogService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PredepositLogService predepositLogService;
//    @Autowired
//    private ITemplateService templateService;

//    @Autowired
//    private MsgTools msgTools;
    @Autowired
    private PayTools payTools;
    @Autowired
    private PaymentTools paymentTools;
    @Autowired
    private TransportTools transportTools;
    @Autowired
    private GoodsViewTools goodsViewTools;

    private static Logger logger = LoggerFactory.getLogger(CartController.class);

    @Override
    protected String rootTemplatePath() {
        return "store/";
    }

    @RequestMapping({ "/cart_menu_detail" })
    public String cart_menu_detail(Model model,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("cart_menu_detail");
        List<GsStoreCart> cart = cart_calc(request);
        List<GsGoodsCart> list = cart_calc(cart);

        float total_price = 0.0F;
        for (GsGoodsCart gc : list){
            String combin_price = this.goodsService.findSingleColumnById(gc.getGoodsId(),"combin_price");
            if (CommUtil.null2String(gc.getCartType()).equals("combin"))
                total_price = CommUtil.null2Float(combin_price);
            else{
                total_price = CommUtil.null2Float(
                        Double.valueOf(CommUtil.mul(Integer.valueOf(gc.getCount()), gc.getPrice()))) + total_price;
            }
        }
        model.addAttribute("total_price", Float.valueOf(total_price));
        model.addAttribute("goods_cart", list);

        return ret;
    }

    /**
     * 添加到购物车
     * @param request
     * @param response
     * @param id
     * @param count
     * @param price
     * @param gsp
     * @param buy_type
     */
    @RequestMapping({ "/add_goods_cart" })
    public void add_goods_cart(HttpServletRequest request,
                               HttpServletResponse response,
                               String id, String count,
                               String price, String gsp,
                               String buy_type){
        String cart_session_id = "";//未登录状态的记录
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("cart_session_id")){
                    cart_session_id = CommUtil.null2String(cookie.getValue());
                }
            }
        }

        if (cart_session_id.equals("")){
            cart_session_id = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("cart_session_id", cart_session_id);
            cookie.setDomain(CommUtil.generic_domain(request));
            response.addCookie(cookie);
        }

        List<GsStoreCart> cart = cart_calc(request);

        String[] gsp_ids = gsp.split(",");// 买家选择的商品规格
        Arrays.sort(gsp_ids);
        boolean add = true;// 是否加入购物车的标志位
        double total_price = 0.0D;
        int total_count = 0;
        String[] gsp_ids1;// 已有购物车内的商品规格
        // 遍历购物车明细，判断用户已有购物车内是否包含当前所选规格的商品。如果包含，则
        for (GsStoreCart sc1 : cart){
            for (GsGoodsCart gc : sc1.getGcs()){
                List<GsGoodsSpecProperty> gsps = this.goodsCartService.findSpecPropertByGoodsCartId(gc.getId());
                if ((gsp_ids != null) && (gsp_ids.length > 0) && (gsp != null) && (gsps.size() > 0)){
                    gsp_ids1 = new String[gsps.size()];
                    for (int i = 0; i < gsps.size(); i++){
                        gsp_ids1[i] = (gsps.get(i) != null ? (gsps.get(i)).getId().toString() : "");
                    }
                    Arrays.sort(gsp_ids1);
                    if ((!gc.getGoodsId().toString().equals(id)) || (!Arrays.equals(gsp_ids, gsp_ids1))){
                        continue;
                    }
                    add = false;
                }else if (gc.getGoodsId().toString().equals(id)){
                    add = false;
                }
            }
        }

        if (add){// 买家当前所选规格的商品可以添加到购物车
            GsGoodsWithBLOBs goods = this.goodsService.findBasicOne(CommUtil.null2Long(id));

            // 判断是更新购物车还是新增购物车，一个卖家一条购物车记录
            String type = "save";// 更新购物车内商品或新增加商品到购物车的标志位
            GsStoreCart sc33 = new GsStoreCart();
            // 遍历购物车，检查当前买家所选规格商品的店铺是否存在。如果存在，则更新购物车记录，否则新增购物车记录
            for (GsStoreCart sc1 : cart){
                if (sc1.getStoreId().equals(goods.getGoodsStoreId())){
                    sc33 = sc1;
                    type = "update";
                    break;
                }
            }
            sc33.setStoreId(goods.getGoodsStoreId());
            if (type.equals("save")){
                sc33.setAddtime(new Date());
                sc33.setDeletestatus(false);
                long store_cart_id = this.storeCartService.save(sc33);
                sc33.setId(store_cart_id);
            }else{
                this.storeCartService.update(sc33);
            }

            // 处理购物车明细
            GsGoodsCart obj = new GsGoodsCart();
            obj.setAddtime(new Date());
            if (CommUtil.null2String(buy_type).equals("")){
                obj.setCount(CommUtil.null2Int(count));
                obj.setPrice(BigDecimal.valueOf(CommUtil.null2Double(price)));
            }
            if (CommUtil.null2String(buy_type).equals("combin")){
                obj.setCount(1);
                obj.setCartType("combin");
                obj.setPrice(goods.getCombinPrice());
            }
            obj.setGoodsId(goods.getId());
            // 解析商品规格
            String spec_info = "";
            GsGoodsSpecProperty spec_property;
            List<GsCartGsp> links = new ArrayList<>();
            for (String gsp_id : gsp_ids){
                spec_property = this.goodsPropertyService.findOne(CommUtil.null2Long(gsp_id));
                if (spec_property != null){
                    GsCartGsp cg = new GsCartGsp();
                    cg.setGspId(spec_property.getId());
                    links.add(cg);

                    spec_info = spec_property.getSpec().getName() + ":" + spec_property.getValue() + " " + spec_info;
                }
            }
            obj.setScId(sc33.getId());
            obj.setSpecInfo(spec_info);
            long cart_id = this.goodsCartService.save(obj);// 保存购物车明细
            sc33.getGcs().add(obj);// 将购物车明细添加到购物车内
            for (GsCartGsp g:links){
                g.setCartId(cart_id);
            }
            this.goodsCartService.saveCartLinksWithSpecProperty(links);//保存购物车商品和规格属性

            double cart_total_price = 0.0D;

            for (GsGoodsCart gc1 : sc33.getGcs()){
                if (CommUtil.null2String(gc1.getCartType()).equals("")){
                    cart_total_price = cart_total_price + CommUtil.null2Double(gc1.getPrice()) * gc1.getCount();
                }
                if (!CommUtil.null2String(gc1.getCartType()).equals("combin"))
                    continue;
                cart_total_price = cart_total_price
                        + CommUtil.null2Double(
                                this.goodsService.findSingleColumnById(gc1.getId(),"combin_price")
                               ) * gc1.getCount();
            }

            sc33.setTotalPrice(BigDecimal.valueOf(CommUtil.formatMoney(Double.valueOf(cart_total_price))));
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (user == null)
                sc33.setCartSessionId(cart_session_id);
            else{
                sc33.setUserId(user.getId());
            }

            // 再次更新购物车
            if ( type.equals("save")){
                sc33.setAddtime(new Date());
            }
            this.storeCartService.update(sc33);
            //////////////
            boolean cart_add = true;
            for (GsStoreCart sc1 : cart){
                if (sc1.getStoreId().equals(sc33.getStoreId())){
                    cart_add = false;
                }
            }
            if (cart_add){
                cart.add(sc33);
            }
        }

        // 计算购物车内商品总价
        for (Object type = cart.iterator(); ((Iterator) type).hasNext();){
            GsStoreCart sc1 = (GsStoreCart) ((Iterator) type).next();

            total_count += sc1.getGcs().size();
            for (GsGoodsCart gc1 : sc1.getGcs()){
                total_price = total_price + CommUtil.mul(gc1.getPrice(), Integer.valueOf(gc1.getCount()));
            }
        }

        Map map = new HashMap();
        map.put("count", Integer.valueOf(total_count));
        map.put("total_price", Double.valueOf(total_price));

        String ret = JsonUtils.objectToJson(map);
        response.setContentType("text/plain; charset=UTF-8");
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
     * 从购物车删除商品
     * @param request
     * @param response
     * @param id
     * @param store_id
     */
    @RequestMapping({ "/remove_goods_cart" })
    public void remove_goods_cart(HttpServletRequest request,
                                  HttpServletResponse response,
                                  String id,
                                  String store_id){
        GsGoodsCart gc = this.goodsCartService.findOne(CommUtil.null2Long(id));
        GsStoreCart the_sc = this.storeCartService.findOne(gc.getScId());

        this.goodsCartService.delete(gc.getId());
        if (the_sc.getGcs().size() == 0){
            this.storeCartService.delete(the_sc.getId());
        }
        List<GsStoreCart> cart = cart_calc(request);
        double total_price = 0.0D;
        double sc_total_price = 0.0D;
        double count = 0.0D;
        for (GsStoreCart sc2 : cart){
            for (GsGoodsCart gc1 : sc2.getGcs()){
                total_price = CommUtil.null2Double(gc1.getPrice()) * gc1.getCount() + total_price;
                count += 1.0D;
                if ((store_id == null) || (store_id.equals(""))
                        || (!sc2.getStoreId().toString().equals(store_id)))
                    continue;
                sc_total_price = sc_total_price + CommUtil.null2Double(gc1.getPrice()) * gc1.getCount();
                sc2.setTotalPrice(BigDecimal.valueOf(sc_total_price));
            }

            this.storeCartService.update(sc2);
        }
        request.getSession(false).setAttribute("cart", cart);
        Map map = new HashMap();
        map.put("count", Double.valueOf(count));
        map.put("total_price", Double.valueOf(total_price));
        map.put("sc_total_price", Double.valueOf(sc_total_price));
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.objectToJson(map));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 调整购物车物品
     * @param request
     * @param response
     * @param cart_id
     * @param store_id
     * @param count
     */
    @RequestMapping({ "/goods_count_adjust" })
    public void goods_count_adjust(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String cart_id,
                                   String store_id,
                                   String count){
        List<GsStoreCart> cart = cart_calc(request);

        double goods_total_price = 0.0D;
        String error = "100";
        GsGoods goods = null;
        String cart_type = "";
        for (GsStoreCart sc : cart) {
            boolean is_break = false;
            for (GsGoodsCart gc :sc.getGcs() ) {
                if (gc.getId().toString().equals(cart_id)) {
                    is_break = true;
                    goods = goodsService.findOne(gc.getGoodsId());
                    cart_type = CommUtil.null2String(gc.getCartType());
                    break;
                }
            }
            if (is_break)
                break;
        }
        if (cart_type.equals("")){
            if (goods.getGroupBuy() == 2){
                GsGroupGoods gg = new GsGroupGoods();
                for (GsGroupGoods gg1 : goods.getGroup_goods_list()){
                    if (gg1.getGgGoodsId().equals(goods.getId())){
                        gg = gg1;
                    }
                }
                if (gg.getGgCount() >= CommUtil.null2Int(count))
                    calTotalPrice(cart,cart_id,count);
                else{
                    error = "300";//团购库存不足
                }
            }else if (goods.getGoodsInventory() >= CommUtil.null2Int(count)){
                calTotalPrice(cart,cart_id,count);
            }else{
                error = "200";//库存不足
            }
        }

        if (cart_type.equals("combin")){
            if (goods.getGoodsInventory() >= CommUtil.null2Int(count))
                calTotalPrice(cart,cart_id,count);
            else{
                error = "200";//库存不足
            }
        }
        Map map = new HashMap();
        map.put("count", count);
        for (GsStoreCart sc : cart){
            if (sc.getStoreId().equals(CommUtil.null2Long(store_id))){
                goods_total_price = Double.valueOf(CommUtil.null2Double(sc.getTotalPrice()));
                map.put("sc_total_price",goods_total_price);
//                goods_total_price += store_total_price;
            }
        }
        map.put("goods_total_price", goods_total_price);
        map.put("error", error);
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.objectToJson(map));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 查看购物车
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({ "/goods_cart1" })
    public String goods_cart1(Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("goods_cart1");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/goods_cart1");
        }
        List<GsStoreCart> cart = cart_calc(request);
        cart_calc(cart);
        if (cart != null){
            /********下面注释代码功能不明********/
//            User user = (User) SecurityUtils.getSubject().getPrincipal();
//            Store store = user==null?null:this.storeJoinService.getCurrentStore(user);
//            if (store != null){
//                for (GsStoreCart sc : cart){
//                    if (sc.getStoreId().equals(store.getStoreId())){
//                        for (GsGoodsCart gc : sc.getGcs()){
//                            this.goodsCartService.delete(gc.getId());
//                        }
//                        sc.getGcs().clear();
//                        this.storeCartService.delete(sc.getId());
//                    }
//                }
//            }
            /********************************/
            for(GsStoreCart sc:cart){
                sc.setStore(this.storeService.findBasicOne(sc.getStoreId()));
            }
            request.getSession(false).setAttribute("cart", cart);
            model.addAttribute("cart", cart);
            model.addAttribute("goodsViewTools", this.goodsViewTools);
        }else{
            ret = generateViewURL("error");
            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret = generateViewURL("wap/error");
            }
            model.addAttribute("op_title", "购物车信息为空");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        if (this.systemConfigService.getConfig().getZtc_status()){
            Map ztc_map = new HashMap();
            ztc_map.put("ztc_status", Integer.valueOf(3));
            ztc_map.put("ztc_begin_time", new Date());
            ztc_map.put("ztc_gold", Integer.valueOf(0));
            ztc_map.put("orderBy","ztc_dredge_price");
            ztc_map.put("orderType","desc");
            List goods = this.goodsService.findByCondition(ztc_map);
//            "select obj from Goods obj where obj.ztc_status =:ztc_status and obj.ztc_begin_time <=:now_date and obj.ztc_gold>:ztc_gold order by obj.ztc_dredge_price desc"

            List ztc_goods = randomZtcGoods(goods);
            model.addAttribute("ztc_goods", ztc_goods);
        }

        return ret;
    }

    /**
     * 确认购物车填写地址
     * @param request
     * @param response
     * @param store_id
     * @return
     */
    @RequestMapping({ "/goods_cart2" })
    public String goods_cart2(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String store_id){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("goods_cart2");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/goods_cart2");
        }
        List<GsStoreCart> cart = cart_calc(request);
        cart_calc(cart);
        GsStoreCart sc = null;
        if (cart != null){
            for (GsStoreCart sc1 : cart){
                if (sc1.getStoreId().equals(CommUtil.null2Long(store_id))){
                    sc = sc1;
                    break;
                }
            }
        }
        if (sc != null){
            Map params = new HashMap();
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            sc.setStore(this.storeService.findBasicOne(sc.getStoreId()));
            params.put("user_id", user==null?0:user.getId());
            params.put("orderBy","addTime");
            params.put("orderType","desc");
            List<GsAddress> addrs = this.orderAddressService.findByCondition(params);
//                    "select obj from Address obj where obj.user.id=:user_id order by obj.addTime desc", params, -1, -1);
            model.addAttribute("addrs", addrs);
            for (GsAddress addr : addrs){
                addr.setArea(this.storeAreaService.findLinkedOne(addr.getAreaId()));
            }
            if ((store_id == null) || (store_id.equals(""))){
                store_id = sc.getStoreId().toString();
            }
            String cart_session = CommUtil.randomString(32);
            request.getSession(false).setAttribute("cart_session", cart_session);
            params.clear();
            params.put("coupon_order_amount", sc.getTotalPrice());
            params.put("user_id", user==null?0:user.getId());
            params.put("coupon_begin_time", new Date());
            params.put("coupon_end_time", new Date());
            params.put("status", Integer.valueOf(0));
            params.put("coupon_join",true);
            List couponinfos = this.couponService.findByCondition(params);
//            "select obj from CouponInfo obj where obj.coupon.coupon_order_amount<=:coupon_order_amount and obj.status=:status and obj.user.id=:user_id and obj.coupon.coupon_begin_time<=:coupon_begin_time and obj.coupon.coupon_end_time>=:coupon_end_time",
            model.addAttribute("couponinfos", couponinfos);
            model.addAttribute("sc", sc);
            model.addAttribute("cart_session", cart_session);
            model.addAttribute("store_id", store_id);
            model.addAttribute("transportTools", this.transportTools);
            model.addAttribute("goodsViewTools", this.goodsViewTools);

            boolean goods_delivery = false;
            List<GsGoodsCart> goodCarts = sc.getGcs();
            for (GsGoodsCart gc : goodCarts){
                String r = this.goodsService.findSingleColumnById(gc.getGoodsId(),"goods_choice_type");
                if (CommUtil.null2Int(r) == 0){
                    goods_delivery = true;
                    break;
                }
            }
            model.addAttribute("goods_delivery", Boolean.valueOf(goods_delivery));
        }else{
            ret = generateViewURL("error");
            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret = generateViewURL("wap/error");
            }
            model.addAttribute("op_title", "购物车信息为空");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    /**
     * 生成订单  完成订单提交进入支付
     * @param request
     * @param response
     * @param cart_session
     * @param store_id
     * @param addr_id
     * @param coupon_id
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/goods_cart3" })
    public String goods_cart3(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String cart_session, String store_id,
                              String addr_id, String coupon_id) throws Exception {
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("goods_cart3");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/goods_cart3");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String cart_session1 = (String) request.getSession(false).getAttribute("cart_session");
        List<GsStoreCart> cart = cart_calc(request);
        cart_calc(cart);
        if (cart != null){
            if (CommUtil.null2String(cart_session1).equals(cart_session)){
                request.getSession(false).removeAttribute("cart_session");
                WebForm wf = new WebForm();
                GsOrderformWithBLOBs of = wf.toPo(request, GsOrderformWithBLOBs.class);
                of.setAddtime(new Date());
                of.setOrderId(user.getId() + CommUtil.formatTime("yyyyMMddHHmmss", new Date()));
                of.setAddrId(CommUtil.null2Long(addr_id));
                of.setOrderStatus(10);
                of.setUserId(user.getId());
                of.setStoreId(CommUtil.null2Long(store_id));
                of.setTotalprice(BigDecimal.valueOf(CommUtil.add(of.getGoodsAmount(), of.getShipPrice())));
                if (!CommUtil.null2String(coupon_id).equals("")){
                    GsCouponInfo ci = this.couponService.findOne(CommUtil.null2Long(coupon_id));
                    GsCoupon c = this.couponService.findCoupon(ci.getCouponId());
                    ci.setStatus(1);
                    this.couponService.update(ci);
                    of.setCiId(ci.getId());
                    of.setTotalprice(BigDecimal.valueOf(
                            CommUtil.subtract(of.getTotalprice(),
                            c.getCouponAmount())));
                }
                of.setOrderType("web");
                long o_id = this.orderFormService.save(of);
                of.setId(o_id);
                for (GsStoreCart sc : cart){
                    if (sc.getStoreId().toString().equals(store_id)){
                        for (GsGoodsCart gc: sc.getGcs()){
                            gc.setOfId(of.getId());
                            this.goodsCartService.update(gc);
                        }
                        sc.setCartSessionId(null);
                        sc.setUserId(user.getId());
                        sc.setScStatus(1);
                        this.storeCartService.update(sc);
                        break;
                    }
                }
                Cookie[] cookies = request.getCookies();
                if (cookies != null){
                    for (int i = 0; i < cookies.length; i++){
                        Cookie cookie = cookies[i];
                        if (cookie.getName().equals("cart_session_id")){
                            cookie.setDomain(CommUtil.generic_domain(request));
                            cookie.setValue("");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
                GsOrderLog ofl = new GsOrderLog();
                ofl.setAddtime(new Date());
                ofl.setOfId(of.getId());
                ofl.setLogInfo("提交订单");
                ofl.setLogUserId(user.getId());
                this.orderFormLogService.save(ofl);
                model.addAttribute("of", of);
                model.addAttribute("paymentTools", this.paymentTools);
                Member m = memberService.findByUserId(of.getUserId());
                if (this.systemConfigService.getConfig().getEmailEnable()){
                    send_email(request, of, m.getMemberEmail(), "email_tobuyer_order_submit_ok_notify");
                }
                if (this.systemConfigService.getConfig().getEmailEnable()){
                    send_sms(request, of, m.getMemberMobile(), "sms_tobuyer_order_submit_ok_notify");
                }
            }else{
                ret = generateViewURL("error");
                if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                    ret = generateViewURL("wap/error");
                }
                model.addAttribute("op_title", "订单已经失效");
                model.addAttribute("url", CommUtil.getURL(request) + "/index");
            }
        }else{
            ret = generateViewURL("error");
            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret = generateViewURL("wap/error");
            }
            model.addAttribute("op_title", "订单信息错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }

    /**
     * 买家选择付款方式,订单支付详情
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({ "/order_pay_view" })
    public String order_pay_view(Model model,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       String id){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("order_pay");

        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/order_pay");
        }
        GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(id));
        if (of.getOrderStatus() == 10){
            model.addAttribute("of", of);
            model.addAttribute("paymentTools", this.paymentTools);
            model.addAttribute("url", CommUtil.getURL(request));
        }else if (of.getOrderStatus() < 10){
            ret = generateViewURL("error");
            model.addAttribute("op_title", "该订单已经取消！");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }else{
            ret = generateViewURL("error");
            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret =generateViewURL("wap/error");
            }
            model.addAttribute("op_title", "该订单已经付款！");
            model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");
        }

        return ret;
    }

    /**
     * 跳转第三方支付页面，进行支付,订单支付
     * @param request
     * @param response
     * @param payType
     * @param order_id
     * @return
     */
    @RequestMapping({ "/order_pay" })
    public String order_pay(Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String payType, String order_id){
        reCapsuleModel(model,request,response);
        String ret = null;
        GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(order_id));
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if (of.getOrderStatus() == 10){
            if (CommUtil.null2String(payType).equals("")){
                ret = generateViewURL("error");
                if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                    ret = generateViewURL("wap/error");
                }
                model.addAttribute("op_title", "支付方式错误！");
                model.addAttribute("url", CommUtil.getURL(request) + "/index");
            }else{
                List payments = new ArrayList();
                Map params = new HashMap();
                //判断是否平台支付
                if (this.systemConfigService.getConfig().getConfig_payment_type() == 1){
                    params.put("mark", payType);
                    params.put("type", "admin");
                    payments = this.paymentService.findByCondition(params);
//                            "select obj from Payment obj where obj.mark=:mark and obj.type=:type"
                }else{
                    params.put("mark", payType);
                    params.put("store_id", of.getStoreId());
                    payments = this.paymentService.findByCondition(params);
//                            "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id"
                }
                of.setPaymentId(((GsPayment)payments.get(0)).getId());
                this.orderFormService.update(of);
                if (payType.equals("balance")){// 余额支付
                    ret = generateViewURL("balance_pay");
                }else if (payType.equals("outline")){// 线下支付
                    ret = generateViewURL("outline_pay");
                    String pay_session = CommUtil.randomString(32);
                    request.getSession(false).setAttribute("pay_session", pay_session);
                    model.addAttribute("paymentTools", this.paymentTools);
                    model.addAttribute("store_id", this.orderFormService.findOne(CommUtil.null2Long(order_id)).getStoreId());
                    model.addAttribute("pay_session", pay_session);
                }else if (payType.equals("payafter")){// 货到付款
                    ret = generateViewURL("payafter_pay");
                    String pay_session = CommUtil.randomString(32);
                    request.getSession(false).setAttribute("pay_session", pay_session);
                    model.addAttribute("paymentTools", this.paymentTools);
                    model.addAttribute("store_id",
                            this.orderFormService.findOne(CommUtil.null2Long(order_id)).getStoreId());
                    model.addAttribute("pay_session", pay_session);
                }else{// 在线支付
                    ret = generateViewURL("line_pay");
                    model.addAttribute("payType", payType);
                    model.addAttribute("url", CommUtil.getURL(request));
                    model.addAttribute("payTools", this.payTools);
                    model.addAttribute("type", "goods");
                    model.addAttribute("payment_id", of.getPaymentId());
                }
                model.addAttribute("order_id", order_id);
            }
        }else{
            ret = generateViewURL("error");
            if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
                ret = generateViewURL("wap/error");
            }
            model.addAttribute("op_title", "该订单不能进行付款！");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return ret;
    }
    /**
     * wap支付提交
     */
    @RequestMapping({ "/pay_submit" })
    public String paymentSubmit(HttpServletRequest request,
                                HttpServletResponse response, String payType, String order_id){
        GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(order_id));

        if (of != null && of.getOrderStatus() == 10){
            List payments = new ArrayList();
            Map params = new HashMap();
            // 1为平台支付:
            if (this.systemConfigService.getConfig().getConfig_payment_type() == 1){
                params.put("mark", payType);
                params.put("type", "admin");
                payments = this.paymentService.findByCondition(params);
//                        "select obj from Payment obj where obj.mark=:mark and obj.type=:type"
            }else{// 店铺支付
                params.put("store_id", of.getStoreId());
                params.put("mark", payType);
                payments = this.paymentService.findByCondition(params);
//                        "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id";
            }
            // 支付方式已经配置:wap支持支付宝wap支付以及微信公众号支付
            if (payments.size() > 0){
                GsPaymentWithBLOBs pay = (GsPaymentWithBLOBs)payments.get(0);
                of.setPaymentId(pay.getId());
                this.orderFormService.update(of);
                // 微信公众号支付
                if (payType.equals("weixin_wap")){
                    String APPID = pay.getWeixinAppid();
                    String siteURL = CommUtil.getURL(request);
                    String out_trade_no = of.getId().toString();

                    return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                            + APPID
                            + "&redirect_uri="
                            + siteURL
                            + "/wechat/oauthCode.htm?sn="
                            + out_trade_no
                            + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
                }else if (payType.equals("alipay_wap")){// 支付宝手机网页支付
                    String siteURL = CommUtil.getURL(request);
                    AlipayConfig config = new AlipayConfig();

                    config.setSeller_email(pay.getSellerEmail());
                    config.setKey(pay.getSafekey());
                    config.setPartner(pay.getPartner());
                    config.setSign_type("RSA");
                    // 把请求参数打包成数组
                    Map<String, String> sParaTemp = new HashMap<String, String>();
                    // 调用的接口名，无需修改
                    sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
                    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
                    sParaTemp.put("partner", pay.getPartner());
                    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
                    sParaTemp.put("seller_id", pay.getPartner());
                    sParaTemp.put("_input_charset", config.getInput_charset());
                    // 支付类型 ，无需修改
                    sParaTemp.put("payment_type", "1");

                    sParaTemp.put("notify_url", siteURL + "/alipay/alipay_notify.htm");
                    sParaTemp.put("return_url", siteURL + "/alipay/alipay_return.htm");
                    sParaTemp.put("out_trade_no", of.getId().toString());
                    sParaTemp.put("subject", "订单号为" + of.getOrderId());
                    // 价格测试改为1分钱
                    //sParaTemp.put("total_fee", "0.01");
                    sParaTemp.put("total_fee", of.getTotalprice().toPlainString());
                    sParaTemp.put("show_url", "/index.htm");
                    sParaTemp.put("body", "支付宝wap支付");
                    // 其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1

                    String sHtmlText = AlipaySubmit.buildRequestWap(config, sParaTemp, "get", "确定");

                    try {
                        try {request.setCharacterEncoding("UTF-8");} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
                        response.setContentType("text/html");
                        response.getWriter().print(sHtmlText);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }else{
                    // 支付方式错误
                    return "redirect:" + CommUtil.getURL(request) + "/index.htm?payMethodError";
                }
            }else{
                // 支付方式未配置
                return "redirect:" + CommUtil.getURL(request) + "/index.htm?noPayMethod";
            }
        }else{
            // 该订单状态不正确，不能进行付款！
            return "redirect:" + CommUtil.getURL(request) + "/index.htm?orderError";
        }

        return null;
    }

    /**
     * 微信CODE回调JSP并进行微信授权接口认证获取用户openid
     */
    @RequestMapping({"/wechat/oauthCode"})
    public String oauthCode(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        logger.info("支付收到微信code回调请求");
        String ret = generateViewURL("wap/wxpay");
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String sn = request.getParameter("sn");
        HttpSession session = request.getSession();
        String scode = (String)session.getAttribute("wxcode");

        if(code != null && code.equalsIgnoreCase(scode)){
        }else{
            session.setAttribute("wxcode", code);
        }
        String openId = null;
        // 用户同意授权
        if (null != code && !"".equals(code) && !"authdeny".equals(code)){
            GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(sn));
            GsPaymentWithBLOBs pay = this.paymentService.findOne(of.getPaymentId());
            // 获取网页授权access_token
            WxOauth2Token wxOauth2Token = WxAdvancedUtil.getOauth2AccessToken(
                    pay.getWeixinAppid(), pay.getWeixinAppsecret(), code);
            // 用户标识
            if(null != wxOauth2Token){
                openId = wxOauth2Token.getOpenId();
            }
            logger.info("微信code回调请求:openId={},sn={}", openId, sn);

            String prodName = "网上购物";
            String amount = of.getTotalprice().toString();

            model.addAttribute("openId", openId);
            model.addAttribute("sn", sn);
            model.addAttribute("amount", amount);
            model.addAttribute("siteName", this.systemConfigService.getConfig().getWebsiteName());
            model.addAttribute("productName", prodName);

        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "用户未授权！");
            model.addAttribute("url", CommUtil.getURL(request) + "/index.htm?authdeny");
        }

        return ret;
    }

    /**
     * 生成微信订单数据以及微信支付需要的签名等信息，传输到前端，发起调用JSAPI支付
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping({"/wechat/wxpay"})
    public void wxpay(HttpServletRequest request,
                      HttpServletResponse response,
                      String openId, String sn,
                      String productName, String totalPrice,
                      String clientUrl) throws Exception {
        String APPID = null;
        String APP_SECRET = null;
        String MCH_ID = null;
        String API_KEY = null;
        String siteURL = CommUtil.getURL(request);
        String UNI_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        logger.info("微信确认支付获取openId={},sn={}" + openId, sn);

        GsOrderformWithBLOBs of = null;
        String amount = null;
        try {
            of = this.orderFormService.findOne(CommUtil.null2Long(sn));
        } catch (Exception e){
            logger.error("微信确认支付查询paymentLog异常=" + e.getMessage());
            e.printStackTrace();
        }
        if(of == null){
            amount = "";
            logger.info("微信确认支付查询orderForm=null");
        }else{
            GsPaymentWithBLOBs pay = this.paymentService.findOne(of.getPaymentId());
            APPID = pay.getWeixinAppid();
            APP_SECRET = pay.getWeixinAppsecret();
            MCH_ID = pay.getWeixinPartnerid();
            API_KEY = pay.getWeixinPaysignkey();

            /** 将元转换为分 */
            amount = of.getTotalprice().multiply(new BigDecimal(100)).setScale(0).toString();
            logger.info("微信确认支付元转分成功amount={}", amount);
        }

        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", APPID);
        parameters.put("mch_id", MCH_ID);
        parameters.put("nonce_str", WxCommonUtil.createNoncestr());
        parameters.put("body", productName);// 商品名称

        /** 订单号 */
        parameters.put("out_trade_no", sn);
        /** 订单金额以分为单位，只能为整数 */
        //parameters.put("total_fee", "1");//测试用的金额1分钱
        parameters.put("total_fee", amount);
        /** 客户端本地ip */
        parameters.put("spbill_create_ip", request.getRemoteAddr());
        /** 支付回调地址 */
        parameters.put("notify_url", siteURL + "/wechat/paynotify.htm");
        /** 支付方式为JSAPI支付 */
        parameters.put("trade_type", "JSAPI");
        /** 用户微信的openid，当trade_type为JSAPI的时候，该属性字段必须设置 */
        parameters.put("openid", openId);

        /** 使用MD5进行签名，编码必须为UTF-8 */
        String sign = WxCommonUtil.createSignMD5("UTF-8", parameters, API_KEY);

        /**将签名结果加入到map中，用于生成xml格式的字符串*/
        parameters.put("sign", sign);

        /** 生成xml结构的数据，用于统一下单请求的xml请求数据 */
        String requestXML = WxCommonUtil.getRequestXml(parameters);
        logger.info("请求统一支付requestXML：" + requestXML);

        try {
            /** 1、使用POST请求统一下单接口，获取预支付单号prepay_id */
            String result = WxCommonUtil.httpsRequestString(UNI_URL, "POST", requestXML);
            logger.info("请求统一支付result:" + result);
            //解析微信返回的信息，以Map形式存储便于取值
            Map<String, String> map = WxCommonUtil.doXMLParse(result);
            logger.info("预支付单号prepay_id为:" + map.get("prepay_id"));
            //全局map，该map存放前端ajax请求的返回值信息，包括wx.config中的配置参数值，也包括wx.chooseWXPay中的配置参数值
            SortedMap<Object, Object> params = new TreeMap<Object, Object>();
            params.put("appId", APPID);
            params.put("timeStamp", new Date().getTime() + ""); //时间戳
            params.put("nonceStr", WxCommonUtil.createNoncestr()); //随机字符串
            params.put("package", "prepay_id=" + map.get("prepay_id")); //格式必须为 prepay_id=***
            params.put("signType", "MD5"); //签名的方式必须是MD5
            /**
             * 获取预支付prepay_id后，需要再次签名，此次签名是用于前端js中的wx.chooseWXPay中的paySign。
             * 参与签名的参数有5个，分别是：appId、timeStamp、nonceStr、package、signType 注意参数名称的大小写
             */
            String paySign = WxCommonUtil.createSignMD5("UTF-8", params, API_KEY);
            //预支付单号
            params.put("packageValue", "prepay_id=" + map.get("prepay_id"));
            params.put("paySign", paySign); //支付签名
            //付款成功后同步请求的URL，请求我们自定义的支付成功的页面，展示给用户
            params.put("sendUrl", siteURL + "/wechat/paysuccess.htm?totalPrice=" + totalPrice);

            //获取用户的微信客户端版本号，用于前端支付之前进行版本判断，微信版本低于5.0无法使用微信支付
            String userAgent = request.getHeader("user-agent");
            char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
            params.put("agent", new String(new char[] { agent }));

            /**2、获取access_token作为参数传递,由于access_token有有效期限制，和调用次数限制，可以缓存到session或者数据库中.有效期设置为小于7200秒*/
            WxToken wxtoken = WxCommonUtil.getToken(APPID, APP_SECRET);
            String token = wxtoken.getAccessToken();
            logger.info("获取的token值为:" + token);

            /**3、获取凭证ticket发起GET请求 */
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=" + token;
            logger.info("接口调用凭证ticket的requestUrl：" + requestUrl);

            String ticktresult = WxCommonUtil.httpsRequestString(requestUrl, "GET", null);
            JSONObject jsonresult = JSONObject.fromObject(ticktresult);
            //JSONObject jsonObject = WxCommonUtil.httpsRequest(requestUrl, "GET", null);
            //使用JSSDK支付，需要另一个凭证，也就是jsapi_ticket。这个是JSSDK中使用到的。
            String jsapi_ticket = jsonresult.getString("ticket");
            logger.info("jsapi_ticket：" + jsapi_ticket);
            // 获取到ticket凭证之后，需要进行一次签名
            String config_nonceStr = WxCommonUtil.createNoncestr();// 获取随机字符串
            long config_timestamp = new Date().getTime();// 时间戳
            // 加入签名的参数有4个，分别是： noncestr、jsapi_ticket、timestamp、url，注意字母全部为小写
            SortedMap<Object, Object> configMap = new TreeMap<Object, Object>();
            configMap.put("noncestr", config_nonceStr);
            configMap.put("jsapi_ticket", jsapi_ticket);
            configMap.put("timestamp", config_timestamp + "");
            configMap.put("url", clientUrl);
            //该签名是用于前端js中wx.config配置中的signature值。
            String config_sign = WxCommonUtil.createSignSHA1("UTF-8", configMap);
            // 将config_nonceStr、jsapi_ticket 、config_timestamp、config_sign一同传递到前端
            // 这几个参数名称和上面获取预支付prepay_id使用的参数名称是不一样的，不要混淆了。
            // 这几个参数是提供给前端js代码在调用wx.config中进行配置的参数，wx.config里面的signature值就是这个config_sign的值，以此类推
            params.put("config_nonceStr", config_nonceStr);
            params.put("config_timestamp", config_timestamp + "");
            params.put("config_sign", config_sign);
            // 将map转换为json字符串，传递给前端ajax回调
            String json = JSONArray.fromObject(params).toString();
            logger.info("用于wx.config配置的json：" + json);

            response.setContentType("text/plain; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            try {request.setCharacterEncoding("UTF-8");} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}

            try {
                PrintWriter writer = response.getWriter();
                writer.print(json);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (DocumentException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 微信扫码支付
     * @param request
     * @param response
     * @param order_id
     * @return
     */
    @RequestMapping({"/wechat/wxcodepay"})
    public void wxcodepay(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          String order_id){
        String UNI_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(order_id));
        String returnhtml = null;
        if (of.getOrderStatus() == 10){
            List payments = new ArrayList();
            Map params = new HashMap();
            //判断是否平台支付
            if (this.systemConfigService.getConfig().getConfig_payment_type() == 1){// 平台统一支付
                params.put("mark", "wxcodepay");
                params.put("type", "admin");
                payments = this.paymentService.findByCondition(params);
//                        "select obj from Payment obj where obj.mark=:mark and obj.type=:type";
            }else{// 店铺支付
                params.put("mark", "wxcodepay");
                params.put("store_id", of.getStoreId());
                payments = this.paymentService.findByCondition(params);
//                        "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id";
            }
            GsPaymentWithBLOBs payment = (GsPaymentWithBLOBs) payments.get(0);
            of.setPaymentId(payment.getId());
            this.orderFormService.update(of);

            String codeUrl = "";//微信返回的二维码地址信息

            SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
            parameters.put("appid", payment.getWeixinAppid());// 公众账号id
            parameters.put("mch_id", payment.getWeixinPartnerid());// 商户号
            parameters.put("nonce_str", WxCommonUtil.createNoncestr());// 随机字符串
            parameters.put("body", "在线购物");// 商品描述
            parameters.put("out_trade_no", order_id);// 商户订单号
            parameters.put("total_fee", of.getTotalprice().multiply(new BigDecimal(100)).setScale(0).toString());// 总金额
            parameters.put("spbill_create_ip", WxCommonUtil.localIp());// 终端IP.Native支付填调用微信支付API的机器IP。
            // 支付成功后回调的action，与JSAPI相同
            parameters.put("notify_url", CommUtil.getURL(request) + "/wechat/paynotify.htm"); //支付成功后回调的action，与JSAPI相同
            parameters.put("trade_type", "NATIVE");// 交易类型
            parameters.put("product_id", order_id);// 商品ID。商品号要唯一,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义
            String sign = WxCommonUtil.createSignMD5("UTF-8", parameters, payment.getWeixinPaysignkey());
            parameters.put("sign", sign);// 签名
            String requestXML = WxCommonUtil.getRequestXml(parameters);
            logger.info("requestXML" + requestXML);
            String result = WxCommonUtil.httpsRequestString(UNI_URL, "POST", requestXML);//WxCommonUtil.httpsRequest(WxConstants.UNIFIED_ORDER_URL, "POST", requestXML);
            // System.out.println(" 微信支付二维码生成" + result);
            Map<String, String> map = new HashMap<String, String>();
            try {
                map = WxCommonUtil.doXMLParse(result);
                logger.info("------------------code_url=" + map.get("code_url") + ";      result_code=" + map.get("code_url") + "------------------------------");
            } catch (Exception e){
                logger.error("doXMLParse()--error", e);
            }
            String returnCode = map.get("return_code");
            String resultCode = map.get("result_code");

            if (returnCode.equalsIgnoreCase("SUCCESS")
                    && resultCode.equalsIgnoreCase("SUCCESS")){
                codeUrl = map.get("code_url");
                // 拿到codeUrl，生成二维码图片
                byte[] imgs = QRCodeEncoderHandler.createQRCode(codeUrl);

                String urls = request.getSession().getServletContext().getRealPath("/") +
                        this.systemConfigService.getConfig().getUploadFilePath()
                        + File.separator + "weixin_qr" + File.separator + "wxpay"
                        + File.separator;
                // 图片的实际路径
                String imgfile = urls + order_id + ".png";

                QRCodeEncoderHandler.saveImage(imgs, imgfile, "png");

                // 图片的网路路径
                String imgUrl = CommUtil.getURL(request) + "/"
                        + this.systemConfigService.getConfig().getUploadFilePath()
                        + "/weixin_qr/wxpay/" + order_id + ".png";

                logger.info("图片的网路路径imgurl={}", imgUrl);

                returnhtml = "<img src='" + imgUrl + "' style='width:200px;height:200px;'/>";
            }else{
                returnhtml = "支付状态不正确";
            }
        }
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
        try {
            PrintWriter writer = response.getWriter();
            writer.print(returnhtml);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 订单线下支付
     * @param model
     * @param request
     * @param response
     * @param payType
     * @param order_id
     * @param pay_msg
     * @param pay_session
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/order_pay_outline" })
    public String order_pay_outline(Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String payType, String order_id,
                                      String pay_msg, String pay_session) throws Exception {
        String ret = generateViewURL("success");
        String pay_session1 = CommUtil.null2String(request.getSession(false).getAttribute("pay_session"));
        if (pay_session1.equals(pay_session)){
            GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(order_id));
            of.setPayMsg(pay_msg);
            Map params = new HashMap();
            params.put("mark", "outline");
            params.put("store_id", of.getStoreId());
            List payments = this.paymentService.findByCondition(params);
//                    "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id"
            if (payments.size() > 0){
                of.setPaymentId(((GsPaymentWithBLOBs) payments.get(0)).getId());
                of.setPaytime(new Date());
            }
            of.setOrderStatus(15);
            this.orderFormService.update(of);
            Store store = this.storeService.findBasicOne(of.getStoreId());
            Member m = this.memberService.findByUserId(store.getMemberId());
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                send_sms(request, of, m.getMemberMobile(), "sms_toseller_outline_pay_ok_notify");
            }
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, of, m.getMemberEmail(), "email_toseller_outline_pay_ok_notify");
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("提交线下支付申请");
            ofl.setLogUserId(user == null?0:user.getId());
            ofl.setOfId(of.getId());
            this.orderFormLogService.save(ofl);
            request.getSession(false).removeAttribute("pay_session");
            model.addAttribute("op_title", "线下支付提交成功，等待卖家审核！");
            model.addAttribute("url", CommUtil.getURL(request) + "/buyer/order.htm");
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "订单已经支付，禁止重复支付！");
            model.addAttribute("url", CommUtil.getURL(request) + "/buyer/order.htm");
        }

        return ret;
    }

    /**
     * 订单货到付款
     * @param model
     * @param request
     * @param response
     * @param payType
     * @param order_id
     * @param pay_msg
     * @param pay_session
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/order_pay_payafter" })
    public String order_pay_payafter(Model model,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           String payType, String order_id,
                                           String pay_msg, String pay_session) throws Exception {
        String ret = generateViewURL("success");
        String pay_session1 = CommUtil.null2String(request.getSession(false).getAttribute("pay_session"));
        if (pay_session1.equals(pay_session)){
            GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(order_id));
            of.setPayMsg(pay_msg);
            Map params = new HashMap();
            params.put("mark", "payafter");
            params.put("store_id", of.getStoreId());
            List payments = this.paymentService.findByCondition(params);
//                    "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id"
            if (payments.size() > 0){
                of.setPaymentId(((GsPayment) payments.get(0)).getId());
                of.setPaytime(new Date());
            }
            of.setOrderStatus(16);
            this.orderFormService.update(of);
            Store store = this.storeService.findBasicOne(of.getStoreId());
            Member m = this.memberService.findByUserId(store.getMemberId());
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                send_sms(request, of, m.getMemberMobile(), "sms_toseller_payafter_pay_ok_notify");
            }
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, of, m.getMemberEmail(), "email_toseller_payafter_pay_ok_notify");
            }

            User user = (User) SecurityUtils.getSubject().getPrincipal();
            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("提交货到付款申请");
            ofl.setLogUserId(user==null?0:user.getId());
            ofl.setOfId(of.getId());
            this.orderFormLogService.save(ofl);
            request.getSession(false).removeAttribute("pay_session");
            model.addAttribute("op_title", "货到付款提交成功，等待卖家发货！");
            model.addAttribute("url", CommUtil.getURL(request) + "/buyer/order.htm");
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "订单已经支付，禁止重复支付！");
            model.addAttribute("url", CommUtil.getURL(request) + "/buyer/order.htm");
        }

        return ret;
    }

    /**
     * 订单预付款支付
     * @param request
     * @param response
     * @param payType
     * @param order_id
     * @param pay_msg
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/order_pay_balance" })
    public String order_pay_balance(Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String payType, String order_id,
                                    String pay_msg) throws Exception {
        String ret = generateViewURL("success");
        GsOrderformWithBLOBs of = this.orderFormService.findOne(CommUtil.null2Long(order_id));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Member m = this.memberService.findByUserId(user.getId());

        if (CommUtil.null2Double(m.getAvailablePredeposit()) > CommUtil.null2Double(of.getTotalprice())){
            of.setPayMsg(pay_msg);
            of.setOrderStatus(20);
            Map params = new HashMap();
            params.put("mark", "balance");
            params.put("store_id", of.getStoreId());
            List payments = this.paymentService.findByCondition(params);
//                    "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id";
            if (payments.size() > 0){
                of.setPaymentId(((GsPayment) payments.get(0)).getId());
                of.setPaytime(new Date());
            }
            int r = this.orderFormService.update(of);
            if (this.systemConfigService.getConfig().getEmailEnable()){
                send_email(request, of, m.getMemberEmail(), "email_toseller_balance_pay_ok_notify");
                send_email(request, of, m.getMemberEmail(), "email_tobuyer_balance_pay_ok_notify");
            }
            if (this.systemConfigService.getConfig().getSmsEnbale()){
                send_sms(request, of, m.getMemberMobile(), "sms_toseller_balance_pay_ok_notify");
                send_sms(request, of, m.getMemberMobile(), "sms_tobuyer_balance_pay_ok_notify");
            }
            if (r>0){
                m.setAvailablePredeposit(BigDecimal.valueOf(CommUtil.subtract(m.getAvailablePredeposit(), of.getTotalprice())));
                m.setFreezePredeposit(BigDecimal.valueOf(CommUtil.add(m.getFreezePredeposit(), of.getTotalprice())));
                this.memberService.update(null,m);
                GsPredepositLog log = new GsPredepositLog();
                log.setAddtime(new Date());
                log.setPdLogUserId(user.getId());
                log.setPdOpType("消费");
                log.setPdLogAmount(BigDecimal.valueOf(-CommUtil.null2Double(of.getTotalprice())));
                log.setPdLogInfo("订单" + of.getOrderId() + "购物减少可用预存款");
                log.setPdType("可用预存款");
                this.predepositLogService.save(log);
                Map param = new HashMap();
                param.put("of_id",of.getId());
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

            }

            GsOrderLog ofl = new GsOrderLog();
            ofl.setAddtime(new Date());
            ofl.setLogInfo("预付款支付");
            ofl.setLogUserId(user==null?0:user.getId());
            ofl.setOfId(of.getId());
            this.orderFormLogService.save(ofl);
            model.addAttribute("op_title", "预付款支付成功！");
            model.addAttribute("url", CommUtil.getURL(request) + "/buyer/order");
        }else{
            ret = generateViewURL("error");
            model.addAttribute("op_title", "可用余额不足，支付失败！");
            model.addAttribute("url", CommUtil.getURL(request) + "/buyer/order");
        }

        return ret;
    }

    /**
     * 订单预付款支付
     * @param model
     * @param request
     * @param response
     * @param order_id
     * @return
     */
    @RequestMapping({ "/order_finish" })
    public String order_finish(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String order_id){
        reCapsuleModel(model,request,response);
        String ret = generateViewURL("order_finish");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/order_finish");
        }
        GsOrderformWithBLOBs obj = this.orderFormService.findOne(CommUtil.null2Long(order_id));
        model.addAttribute("obj", obj);

        return ret;
    }

    /**
     * 地址新增
     * @param request
     * @param response
     * @param id
     * @param store_id
     * @return
     */
    @RequestMapping({ "/cart_address" })
    public String cart_address(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String id, String store_id){
        String ret = generateViewURL("cart_address");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/cart_address");
        }
        List areas = this.storeAreaService.findByParentId(null);
        model.addAttribute("areas", areas);
        model.addAttribute("store_id", store_id);

        return ret;
    }

    /**
     * 购物车中收货地址保存
     * @param model
     * @param request
     * @param response
     * @param id
     * @param area_id
     * @param store_id
     * @return
     */
    @RequestMapping({ "/cart_address_save" })
    public String cart_address_save(Model model,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String id, String area_id,
                                    String store_id){
        WebForm wf = new WebForm();
        GsAddress address = null;
        if (id.equals("")){
            address =  wf.toPo(request, GsAddress.class);
            address.setAddtime(new Date());
        }else{
            GsAddress obj = this.orderAddressService.findOne(Long.valueOf(Long.parseLong(id)));
            address = (GsAddress) wf.toPo(request, obj);
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        address.setUserId(user==null?0:user.getId());
        address.setAreaId(CommUtil.null2Long(area_id));
        if (id.equals(""))
            this.orderAddressService.save(address);
        else
            this.orderAddressService.update(address);

        return "redirect:goods_cart2?store_id=" + store_id;
    }

    /**
     * 地址切换
     * @param request
     * @param response
     * @param addr_id
     * @param store_id
     */
    @RequestMapping({ "/order_address" })
    public void order_address(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String addr_id,
                              String store_id){
        List<GsStoreCart> cart = (List) request.getSession(false).getAttribute("cart");
        GsStoreCart sc = null;
        if (cart != null){
            for (GsStoreCart sc1 : cart){
                if (sc1.getStoreId().equals(CommUtil.null2Long(store_id))){
                    sc = sc1;
                    break;
                }
            }
        }
        GsAddress addr = this.orderAddressService.findOne(CommUtil.null2Long(addr_id));
        List sms = this.transportTools.query_cart_trans(sc, CommUtil.null2String(addr.getAreaId()));

        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.objectToJson(sms));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 收货地址列表
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param store_id
     * @return
     */
    @RequestMapping({"/address"})
    public String address(Model model,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                String currentPage, String orderBy,
                                String orderType, String store_id){
        String ret = generateViewURL("address");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/address");
        }
        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        Map map = new HashMap();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("user_id",user.getId());
        map.put("orderBy",orderBy);
        map.put("orderType",orderType);
        PageInfo pList = this.orderAddressService.findByCondition(map,CommUtil.null2Int(currentPage),12);
        CommUtil.saveIPageList2ModelAndView(url + "/address", "", params, pList, model);
        List areas = this.storeAreaService.findByParentId(null);
        model.addAttribute("areas", areas);
        model.addAttribute("store_id", store_id);

        return ret;
    }

    /**
     * 修改收货地址
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param store_id
     * @return
     */
    @RequestMapping({"/address_edit"})
    public String address_edit(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String id, String currentPage,
                                     String store_id){
        String ret = generateViewURL("cart_address");
        String wemall_view_type = CommUtil.null2String(request.getSession().getAttribute("wemall_view_type"));
        if((wemall_view_type != null) && (!wemall_view_type.equals("")) && (wemall_view_type.equals("wap"))){
            ret = generateViewURL("wap/cart_address");
        }
        List areas = this.storeAreaService.findByParentId(null);
        GsAddress obj = this.orderAddressService.findOne(CommUtil.null2Long(id));
        model.addAttribute("obj", obj);
        model.addAttribute("areas", areas);
        model.addAttribute("store_id", store_id);
        model.addAttribute("currentPage", currentPage);

        return ret;
    }

    /**
     * 收货地址删除
     * @param request
     * @param response
     * @param mulitId
     * @param currentPage
     * @param store_id
     * @return
     */
    @RequestMapping({"/address_del"})
    public String address_del(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              String mulitId, String currentPage,
                              String store_id){
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                this.orderAddressService.delete(Long.valueOf(Long.parseLong(id)));
            }
        }

        return "redirect:goods_cart2?store_id=" + store_id;
    }


    /**
     * 计算物品变化后的商店购物车的总价
     * @param cart
     * @param goods_cart_id
     * @param count
     */
    private void calTotalPrice(List<GsStoreCart> cart,String goods_cart_id,String count){
        for (GsStoreCart sc : cart){ // sc = (StoreCart)gc.next();
            boolean isAlter = false;
            double goods_total_price = 0d;
            for (int i = 0; i < sc.getGcs().size(); i++){
                GsGoodsCart gc =  sc.getGcs().get(i);
                if (gc.getId().toString().equals(goods_cart_id)){
                    goods_total_price += Double.valueOf(CommUtil.null2Int(count)*CommUtil.null2Double(gc.getPrice()));
                    if (!gc.getCount().equals(CommUtil.null2Int(count))) {//数量有变化，总价需要重新记录
                        isAlter = true;
                        gc.setCount(CommUtil.null2Int(count));
                        this.goodsCartService.update(gc);
                    }
                }else{
                    goods_total_price += Double.valueOf(CommUtil.null2Int(gc.getCount())*CommUtil.null2Double(gc.getPrice()));
                }
            }
            if (isAlter) {
                sc.setTotalPrice(BigDecimal.valueOf(goods_total_price));
                this.storeCartService.update(sc);
                return;
            }
        }
    }

    private List<GsGoodsWithBLOBs> randomZtcGoods(List<GsGoodsWithBLOBs> goods){
        Random random = new Random();
        int random_num = 0;
        int num = 0;
        if (goods.size() - 8 > 0){
            num = goods.size() - 8;
            random_num = random.nextInt(num);
        }
        Map ztc_map = new HashMap();
        ztc_map.put("ztc_status", Integer.valueOf(3));
        ztc_map.put("ztc_begin_time", new Date());
        ztc_map.put("ztc_gold", Integer.valueOf(0));
        ztc_map.put("orderBy","ztc_dredge_price");
        ztc_map.put("orderType","desc");
        ztc_map.put("limit_down",random_num);
        ztc_map.put("limit_up",(random_num+8));
        List ztc_goods = this.goodsService.findByCondition(ztc_map);
//                "select obj from Goods obj where obj.ztc_status =:ztc_status and obj.ztc_begin_time <=:now_date and obj.ztc_gold>:ztc_gold order by obj.ztc_dredge_price desc",
//                ztc_map, random_num, 8);
        Collections.shuffle(ztc_goods);

        return ztc_goods;
    }

    private void send_email(HttpServletRequest request,
                            GsOrderformWithBLOBs order,
                            String email,
                            String mark) throws Exception {
//        com.wemall.foundation.domain.Template template = this.templateService.getObjByProperty("mark", mark);
//        if ((template != null) && (template.isOpen())){
//            String subject = template.getTitle();
//            String path = request.getSession().getServletContext().getRealPath("") + File.separator + "vm"
//                    + File.separator;
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
//            p.setProperty("file.resource.loader.path", request.getRealPath("/") + "vm" + File.separator);
//            p.setProperty("input.encoding", "UTF-8");
//            p.setProperty("output.encoding", "UTF-8");
//            Velocity.init(p);
//            org.apache.velocity.Template blank = Velocity.getTemplate("msg.vm", "UTF-8");
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

    private void send_sms(HttpServletRequest request,
                          GsOrderformWithBLOBs order,
                          String mobile,
                          String mark) throws Exception {
//        com.wemall.foundation.domain.Template template = this.templateService.getObjByProperty("mark", mark);
//        if ((template != null) && (template.isOpen())){
//            String path = request.getSession().getServletContext().getRealPath("") + File.separator + "vm"
//                    + File.separator;
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
//            p.setProperty("file.resource.loader.path", request.getRealPath("/") + "vm" + File.separator);
//            p.setProperty("input.encoding", "UTF-8");
//            p.setProperty("output.encoding", "UTF-8");
//            Velocity.init(p);
//            org.apache.velocity.Template blank = Velocity.getTemplate("msg.vm", "UTF-8");
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
