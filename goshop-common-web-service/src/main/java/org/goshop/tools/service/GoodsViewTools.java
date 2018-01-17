package org.goshop.tools.service;

import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.i.GoodsUserClassService;
import org.goshop.goods.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Desmond on 07/12/2017.
 */
@Component
public class GoodsViewTools {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsClassService goodsClassService;

    @Autowired
    private GoodsUserClassService goodsUserClassService;

    /**
     * ������Ʒid���ɸ���Ʒ�Ķ���
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GsGoodsSpecification> generic_spec(String id){
        List specs = new ArrayList();
        if ((id != null) && (!id.equals(""))){
            GsGoods goods = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));
            for (GsGoodsSpecProperty gsp : goods.getGoodsSpecs()){
                GsGoodsSpecification spec = gsp.getSpec();
                if (!specs.contains(spec)){
                    specs.add(spec);
                }
            }
        }
        Collections.sort(specs, new Comparator(){
            public int compare(Object gs1, Object gs2){
                return (((GsGoodsSpecification)gs1).getSequence()) - (((GsGoodsSpecification)gs2).getSequence());
            }
        });

        return specs;
    }

    public List<GsGoodsUserClass> query_user_class(String pid,Long uid){
        List list = new ArrayList();
        if ((pid == null) || (pid.equals(""))){
            list = this.goodsUserClassService.findByUserIdAndParentId(uid,null,null);
        }else{
            list = this.goodsUserClassService.findByUserIdAndParentId(uid,Long.valueOf(Long.parseLong(pid)),null);
        }

        return list;
    }

    public List<GsGoodsWithBLOBs> query_with_gc(String gc_id, int count){
        List<GsGoodsWithBLOBs> list = new ArrayList();
        GsGoodsClass gc = this.goodsClassService.findOne(CommUtil.null2Long(gc_id));
        if (gc != null){
            List ids = genericIds(gc);
            Map params = new HashMap();
            params.put("good_class_ids", ids);
            params.put("goods_status", Integer.valueOf(0));
            params.put("orderBy","goods_click");
            params.put("orderType","desc");
            list = this.goodsService.findByCondition(params);
        }

        return list;
    }

    private List<Long> genericIds(GsGoodsClass gc){
        List ids = new ArrayList();
        ids.add(gc.getId());
        for (GsGoodsClass child : gc.getChildren()){
            List<Long> cids = genericIds(child);
            for (Long cid : cids){
                ids.add(cid);
            }
            ids.add(child.getId());
        }
        return ids;
    }

    public List<GsGoodsWithBLOBs> sort_sale_goods(Long store_id, int count){
        Map params = new HashMap();
        params.put("goods_store_id", store_id);
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy","goods_salenum");
        params.put("orderType","desc");
        List list = this.goodsService.findByCondition(params);

        return list;
    }

    public List<GsGoodsWithBLOBs> sort_sale_goods(String store_id, int count){
        return sort_sale_goods(CommUtil.null2Long(store_id),count);
    }

    public List<GsGoodsWithBLOBs> sort_collect_goods(String store_id, int count){
        Map params = new HashMap();
        params.put("goods_store_id", CommUtil.null2Long(store_id));
        params.put("goods_status", Integer.valueOf(0));
        params.put("orderBy","goods_collect");
        params.put("orderType","desc");
        List list = this.goodsService.findByCondition(params);
        return list;
    }

    public List<GsGoodsWithBLOBs> query_combin_goods(String id){
        return this.goodsService.findOne(CommUtil.null2Long(id)).getCombinGoods();
    }
}
