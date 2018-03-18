package org.goshop.tools.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsEvaluateService;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.order.i.GoodsCartService;
import org.goshop.order.pojo.GsGoodsCart;
import org.goshop.order.pojo.GsOrderformWithBLOBs;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 20/02/2018.
 */
@Component
public class UtilViewTools {

    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsCartService goodsCartService;
    @Autowired
    private GoodsEvaluateService goodsEvaluateService;


    public String showBrandGoodsAjaxHtml(List lists, Map<String, Object> map){
        return showGoodsHtml(lists,map);
    }

    public String showLoadGoodsAjaxHtml(List lists, Map<String, Object> map){
        return showGoodsHtml(lists,map);
    }

    public String showGoodsHtml(List lists, Map<String, Object> map){
        StringBuffer s = new StringBuffer(300);

        String loadimg = map.get("imageWebServer") + "/static/images/common/loader.gif";
        String errorimg = map.get("webPath") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
        String goods_url = null;

        if (lists != null && lists.size() > 0){
            for(int i = 0; i < lists.size(); i++){
                GsGoodsWithBLOBs goods = (GsGoodsWithBLOBs)lists.get(i);
                String img = null;
                if(goods.getGoods_main_photo() != null){
                    img = map.get("imageWebServer") + "/" + goods.getGoods_main_photo().getPath() + "/" + goods.getGoods_main_photo().getName() + "_small." + goods.getGoods_main_photo().getExt();
                }else{
                    img = map.get("imageWebServer") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
                }

                goods_url = map.get("webPath") + "/goods?id=" + goods.getId();

                Store store = this.storeService.findBasicOne(goods.getId());
                if((Boolean)map.get("IsSecondDomainOpen")){
                    goods_url = "http://" + store.getStoreDomain() + "." + map.get("domainPath") + "/goods?id=" + goods.getId();
                }

                s.append("<a href='").append(goods_url).append("'>");
                s.append("<dl><dt><img src='").append(loadimg).append("' original='").append(img).append("' onerror='this.src=").append(errorimg).append(";'/>");
                s.append("</dt><dd><h3>");
                if(goods.getGroupBuy() == 2){
                    s.append("<span style=\"color:#F00;\">[团购]</span>");
                }
                if(goods.getActivityStatus() == 2){
                    s.append("<span style=\"color:#F00;\">[活动]</span>");
                }
                if(goods.getCombinStatus() == 2){
                    s.append("<span style=\"color:#F00;\">[组合]</span>");
                }
                if(goods.getBargainStatus() == 2){
                    s.append("<span style=\"color:#F00;\">[特价]</span>");
                }
                if(goods.getDeliveryStatus() == 2){
                    s.append("<span style=\"color:#F00;\">[买就送]</span>");
                }
                Map param = new HashMap();
                param.put("evaluate_goods_id",goods.getId());
                int count = this.goodsEvaluateService.findCountByCondition(param);
                s.append("<b>").append(CommUtil.substring(goods.getGoodsName(), 25)).append("</b></h3>");
                s.append("<span>市场价：<b class=\"goods2market\">￥").append(goods.getGoodsPrice()).append("</b><i>，</i> </span>");
                s.append("<span>商城价：<strong>￥").append(goods.getStorePrice()).append("</strong></span>").append("<em>");
                s.append("<span>描述相符：<i style=\"color:#F00\">").append(CommUtil.null2Double(goods.getDescriptionEvaluate())).append("</i> 分， </span>");
                s.append("<span>累计评价：<i style=\"color:#f60\">").append(count).append("，</i></span>");
                s.append("<span>已售出<strong class=\"orange\">").append(goods.getGoodsSalenum()).append("</strong>笔</span>").append("</em></dd></dl></a>");
            }
        }

        return s.toString();
    }

    public String showOrdersAjaxHtml(List<Object> lists, Map<String, Object> map){
        StringBuffer s = new StringBuffer(300);
        String img = null;
        String goods_url = null;

        if (lists != null && lists.size() > 0){
            for(int i = 0; i < lists.size(); i++){
                GsOrderformWithBLOBs obj = (GsOrderformWithBLOBs)lists.get(i);

                s.append("<div class='order_page_box'><h1><span class='fl'>订单号：");
                s.append(obj.getOrderId()).append("</span><span class='fr'>");
                if(obj.getOrderStatus() == 0){
                    s.append("已取消");
                }else if(obj.getOrderStatus() == 10){
                    s.append("待发货");
                }else if(obj.getOrderStatus() == 20){
                    s.append("待发货");
                }else if(obj.getOrderStatus() == 30){
                    s.append("待收货");
                }else if(obj.getOrderStatus() == 40){
                    s.append("已收货");
                }else{
                    s.append("&nbsp;");
                }
                s.append("</span></h1>");

                Map param = new HashMap();
                param.put("goods_id",obj.getId());
                List<GsGoodsCart> goodsCart = this.goodsCartService.findByCondition(param);
                for(GsGoodsCart gc : goodsCart){
                    if(gc.getGoods().getGoods_main_photo() != null){
                        img = map.get("imageWebServer") + "/" + gc.getGoods().getGoods_main_photo().getPath() + "/" + gc.getGoods().getGoods_main_photo().getName() + "_small." + gc.getGoods().getGoods_main_photo().getExt();
                    }else{
                        img = map.get("imageWebServer") + "/" + map.get("goodsImagePath") + "/" + map.get("goodsImageName");
                    }

                    goods_url = map.get("webPath") + "/goods?=" + gc.getGoods().getId();

                    Store store = this.storeService.findBasicOne(gc.getGoods().getId());
                    if((Boolean)map.get("IsSecondDomainOpen")){
                        goods_url = "http://" + store.getStoreDomain() + "." + map.get("domainPath") + "/goods?id=" + gc.getGoods().getId();
                    }

                    s.append("<div class='order_goods'><div class='dt'>").append("<a href='").append(goods_url).append("' >");
                    s.append("<img src='").append(img).append("' width='50' height='50'></a></div>");
                    s.append("<div style='float: left;'>").append("<a href='").append(goods_url).append("' >");
                    s.append("<span style='line-height: 24px'>").append(gc.getGoods().getGoodsName()).append("</span></a><br></div></div>");
                }
                s.append("<div class='order_total'> 实付：<b>￥").append(obj.getTotalprice()).append("&nbsp;").append("<i style='color:#59cfff'>(含运费:￥");
                s.append(CommUtil.null2Float(obj.getShipPrice())).append(")</i></b></div>");

                s.append("<div class='order_box_bt'>");
                if(obj.getOrderStatus() == 0){
                    s.append("<a style='color:#ff4f19; text-decoration:underline;' href='javascript:void(0);' ");
                    s.append("onclick=\"if(confirm('删除订单，删除后不可恢复，是否继续?'))window.location.href='").append(map.get("webPath"));
                    s.append("/buyer/order_delete.htm?id=").append(obj.getId()).append("&currentPage=").append(map.get("currentPage")).append("'\" >删除订单</a>");
                }
                if(obj.getOrderStatus() == 10){
                    s.append("<a style='color:#ff4f19; text-decoration:underline;' href='javascript:void(0);' ");
                    s.append("onclick=\"if(confirm('取消订单，是否继续?'))window.location.href='").append(map.get("webPath"));
                    s.append("/buyer/order_cancel.htm?id=").append(obj.getId()).append("&currentPage=").append(map.get("currentPage")).append("'\" >取消订单</a>");
                }

                if(obj.getOrderStatus() == 47){
                    s.append("退货完成,已结束");
                }

                if(obj.getOrderStatus() == 48){
                    s.append("卖家拒绝退货申请");
                }

                if(obj.getOrderStatus() == 49){
                    s.append("退货失败");
                }

                if(obj.getOrderStatus() == 65){
                    s.append("已结束,不可评价");
                }

                //退货完成,已结束 47;卖家拒绝退货申请 48;退货失败 49;已结束,不可评价 65
                if(obj.getOrderStatus() == 10){
                    s.append("<a href=").append(map.get("webPath")).append("/order_pay_view?id=").append(obj.getId()).append(" target='_blank' class='bg_orange'>付款</a>");
                }
                if(obj.getOrderStatus() == 30){
                    s.append("<a href='javascript:void(0);' ");
                    s.append("onclick=\"if(confirm('订单确认收货，是否继续?'))window.location.href='").append(map.get("webPath"));
                    s.append("/buyer/order_cofirm_save?id=").append(obj.getId()).append("&currentPage=").append(map.get("currentPage")).append("'\" >确认收货</a>");
                }
                s.append("</div>");
                s.append("</div>");
            }
        }

        return s.toString();
    }

    public void saveIPageList2Map(String url, String staticURL,
                                         String params, PageInfo pList,
                                         Map<String, Object> map){
        if (pList != null){
            map.put("totalPage", new Integer(pList.getPages()));
            map.put("pageSize", Integer.valueOf(pList.getPageSize()));
            map.put("rows", new Integer(pList.getEndRow()-pList.getStartRow()));
            map.put("currentPage", new Integer(pList.getPageNum()));
            if("brand_goods".equalsIgnoreCase(String.valueOf(map.get("show")))){
                map.put("ajaxLoadHtml", showBrandGoodsAjaxHtml(pList.getList(), map));
            }else if("orders".equalsIgnoreCase(String.valueOf(map.get("show")))){
                map.put("ajaxLoadHtml", showOrdersAjaxHtml(pList.getList(), map));
            }
        }
    }
}
