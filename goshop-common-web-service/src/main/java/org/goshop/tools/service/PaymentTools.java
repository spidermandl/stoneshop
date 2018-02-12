package org.goshop.tools.service;

import org.goshop.common.web.utils.CommUtil;
import org.goshop.pay.i.PaymentService;
import org.goshop.pay.pojo.GsPayment;
import org.goshop.pay.pojo.GsPaymentWithBLOBs;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ֧���������
 */
@Component
public class PaymentTools {
    @Autowired(required = false)
    private PaymentService paymentService;
    @Autowired
    private StoreService storeService;

    /**
     * ��ѯ֧����ʽ
     * @param mark
     * @param type
     * @return
     */
    public boolean queryPayment(String mark, String type){
        Map params = new HashMap();
        params.put("mark", mark);
        params.put("type", type);
        List objs = this.paymentService.findByCondition(params);
//                    "select obj from Payment obj where obj.mark=:mark and obj.type=:type"
        if (objs.size() > 0){
            return ((GsPayment)objs.get(0)).getInstall();
        }

        return false;
    }

    /**
     * ��ѯ֧����ʽ
     * @param mark
     * @return
     */
    public Map queryPayment(String mark,Long user_id){
        Map params = new HashMap();
        params.put("mark", mark);
        params.put("type", "user");
        Store store = this.storeService.findByMemberId(user_id);
        params.put("store_id", store.getStoreId());
        List objs = this.paymentService.findByCondition(params);
//                    "select obj from Payment obj where obj.mark=:mark and obj.type=:type and obj.store.id=:store_id",
        Map ret = new HashMap();
        if (objs.size() == 1){
            ret.put("install", Boolean.valueOf(((GsPayment)objs.get(0)).getInstall()));
            ret.put("already", Boolean.valueOf(true));
        }else{
            ret.put("install", Boolean.valueOf(false));
            ret.put("already", Boolean.valueOf(false));
        }

        return ret;
    }

    /**
     * ��ѯ����֧����ʽ
     * @param mark
     * @param store_id
     * @return ��װ״̬��֧��˵��
     */
    public Map queryStorePayment(String mark, String store_id){
        Map ret = new HashMap();
        Map params = new HashMap();
        params.put("mark", mark);
        params.put("store_id", CommUtil.null2Long(store_id));
        List objs = this.paymentService.findByCondition(params);
//                    .query("select obj from Payment obj where obj.mark=:mark and obj.store.id=:store_id"
        if (objs.size() == 1){
            ret.put("install", Boolean.valueOf(((GsPayment)objs.get(0)).getInstall()));
            ret.put("content", (((GsPaymentWithBLOBs)objs.get(0)).getContent()));
        }else{
            ret.put("install", Boolean.valueOf(false));
            ret.put("content", "");
        }

        return ret;
    }

    /**
     * ��ѯƽ̨֧����ʽ
     * @param mark
     * @return ��װ״̬��֧��˵��
     */
    public Map queryShopPayment(String mark){
        Map ret = new HashMap();
        Map params = new HashMap();
        params.put("mark", mark);
        params.put("type", "admin");
        List objs = this.paymentService.findByCondition(params);
//                    .query("select obj from Payment obj where obj.mark=:mark and obj.type=:type"
        if (objs.size() == 1){
            ret.put("install", Boolean.valueOf(((GsPayment)objs.get(0)).getInstall()));
            ret.put("content", (((GsPaymentWithBLOBs)objs.get(0)).getContent()));
        }else{
            ret.put("install", Boolean.valueOf(false));
            ret.put("content", "");
        }

        return ret;
    }
}




