package org.goshop.tools.service;

import org.goshop.common.web.utils.CommUtil;
import org.goshop.order.i.OrderFormLogService;
import org.goshop.order.i.OrderFormService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单工具组件
 */
@Component
public class OrderViewTools {
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private StoreService storeService;

    public int query_user_order(String order_status,String user_id){
        Map params = new HashMap();
        int status = -1;
        if (order_status.equals("order_submit")){
            status = 10;
        }
        if (order_status.equals("order_pay")){
            status = 20;
        }
        if (order_status.equals("order_shipping")){
            status = 30;
        }
        if (order_status.equals("order_receive")){
            status = 40;
        }
        if (order_status.equals("order_finish")){
            status = 60;
        }
        if (order_status.equals("order_cancel")){
            status = 0;
        }
        params.put("status", Integer.valueOf(status));
        params.put("user_id", CommUtil.null2Long(user_id));
        List ofs = this.orderFormService.findByCondition(params);
//                "select obj from OrderForm obj where obj.order_status=:status and obj.user.id=:user_id"

        return ofs.size();
    }

    public int query_store_order(String order_status,String user_id){
        Store store = this.storeService.findByMemberId(CommUtil.null2Long(user_id));
        if (store != null){
            Map params = new HashMap();
            int status = -1;
            if (order_status.equals("order_submit")){
                status = 10;
            }
            if (order_status.equals("order_pay")){
                status = 20;
            }
            if (order_status.equals("order_shipping")){
                status = 30;
            }
            if (order_status.equals("order_receive")){
                status = 40;
            }
            if (order_status.equals("order_finish")){
                status = 60;
            }
            if (order_status.equals("order_cancel")){
                status = 0;
            }
            params.put("status", Integer.valueOf(status));
            params.put("store_id", store.getStoreId());
            List ofs = this.orderFormService.findByCondition(params);
//                    "select obj from OrderForm obj where obj.order_status=:status and obj.store.id=:store_id"
            return ofs.size();
        }

        return 0;
    }
}
