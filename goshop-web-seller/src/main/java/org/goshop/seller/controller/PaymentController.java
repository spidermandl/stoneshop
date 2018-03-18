package org.goshop.seller.controller;

import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.common.web.utils.WebForm;
import org.goshop.order.i.OrderFormService;
import org.goshop.order.pojo.GsOrderformWithBLOBs;
import org.goshop.pay.i.PaymentService;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.pojo.Store;
import org.goshop.tools.service.PaymentTools;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家支付方式控制器
 */
@Controller
public class PaymentController {
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private StoreJoinService storeJoinService;

    @Autowired
    private PaymentTools paymentTools;

    /**
     * 支付方式列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/payment"})
    public String payment(Model model,
                          @CurrentUser User user,
                          HttpServletRequest request,
                          HttpServletResponse response){
        String ret = "order/payment";
        String store_payment = this.systemConfigService.getConfig().getStore_payment();
        if ((store_payment != null) && (!store_payment.equals(""))){
            Map map = JsonUtils.jsonToPojo(store_payment,HashMap.class);
            model.addAttribute("map", map);
            model.addAttribute("paymentTools", this.paymentTools);
            model.addAttribute("user_id",user==null?0:user.getId());
        }

        return ret;
    }

    /**
     * 支付方式安装
     * @param request
     * @param response
     * @param mark
     * @return
     */
    @RequestMapping({"/payment_install"})
    public String payment_install(Model model,
                                  @CurrentUser User user,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String mark){
        String ret = "order/payment/" + mark;
        Map map = new HashMap();
        Store store = this.storeJoinService.getCurrentStore(user);
        if ((mark != null) && (!mark.equals(""))){
            map.put("store_id", store.getStoreId());
            map.put("mark", mark);
            List objs = this.paymentService.findByCondition(map);
//                    "select obj from Payment obj where obj.store.id=:store_id and obj.mark=:mark"
            if (objs.size() > 0){
                model.addAttribute("obj", objs.get(0));
            }
        }

        return ret;
    }

    /**
     * 支付方式卸载
     * @param request
     * @param response
     * @param mark
     * @return
     */
    @RequestMapping({"/payment_uninstall"})
    public String payment_uninstall(Model model,
                                    @CurrentUser User user,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    String mark){
        String ret = "order/success";
        Map params = new HashMap();
        Store store = this.storeJoinService.getCurrentStore(user);
        params.put("mark", mark);
        params.put("store_id", store.getStoreId());
        List<GsPaymentWithBLOBs> objs = this.paymentService.findByCondition(params);
//                "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id",
        if (objs.size() > 0){
            params.clear();
            params.put("payment_id",objs.get(0).getId());
            List<GsOrderformWithBLOBs> orders = this.orderFormService.findByCondition(params);
            for (GsOrderformWithBLOBs of : orders){
                of.setPaymentId(null);
                this.orderFormService.update(of);
            }
            this.paymentService.delete(objs.get(0).getId());
        }
        model.addAttribute("op_title", "支付方式卸载成功");
        model.addAttribute("url", CommUtil.getURL(request) + "/payment");

        return ret;
    }

    /**
     * 支付方式编辑
     * @param request
     * @param response
     * @param mark
     * @return
     */
    @RequestMapping({"/payment_edit"})
    public String payment_edit(Model model,
                               @CurrentUser User user,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String mark){
        String ret = "order/payment/" + mark;
        Store store = this.storeJoinService.getCurrentStore(user);
        Map params = new HashMap();
        params.put("mark", mark);
        params.put("store_id", store.getStoreId());
        List objs = this.paymentService.findByCondition(params);
//                "select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id",
        if (objs.size() > 0)
            model.addAttribute("obj", objs.get(0));

        return ret;
    }

    /**
     * 支付方式保存
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/payment_save"})
    public String payment_save(Model model,
                               @CurrentUser User user,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               String id){
        String ret = "order/success";
        WebForm wf = new WebForm();
        if (!id.equals("")){
            GsPaymentWithBLOBs obj = this.paymentService.findOne(CommUtil.null2Long(id));
            GsPaymentWithBLOBs payment = (GsPaymentWithBLOBs)wf.toPo(request, obj);
            this.paymentService.update(payment);
        }else{
            GsPaymentWithBLOBs payment = wf.toPo(request, GsPaymentWithBLOBs.class);
            payment.setAddtime(new Date());
            payment.setType("user");
            Store store = this.storeJoinService.getCurrentStore(user);
            payment.setStoreId(store.getStoreId());
            this.paymentService.save(payment);
        }
        model.addAttribute("op_title", "支付方式保存成功");
        model.addAttribute("url", CommUtil.getURL(request) + "/payment");

        return ret;
    }
}
