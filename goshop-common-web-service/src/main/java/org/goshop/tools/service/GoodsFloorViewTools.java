package org.goshop.tools.service;

import org.goshop.assets.i.AccessoryService;
import org.goshop.assets.pojo.GsAccessory;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.goods.i.GoodsBrandService;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.pojo.GsGoods;
import org.goshop.goods.pojo.GsGoodsBrand;
import org.goshop.goods.pojo.GsGoodsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 商品楼层工具组件
 */
@Component
public class GoodsFloorViewTools {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsClassService goodsClassService;

    @Autowired
    private AccessoryService accessoryService;

//    @Autowired
//    private IAdvertPositionService advertPositionService;
//
//    @Autowired
//    private IAdvertService advertService;

    @Autowired
    private GoodsBrandService goodsBrandService;

    public List<GsGoodsClass> generic_gf_gc(String json){
        List gcs = new ArrayList();
        if ((json != null) && (!json.equals(""))){
            List<Map> list = JsonUtils.jsonToList(json,Map.class);
            for (Map map : list){
                GsGoodsClass the_gc = this.goodsClassService.findOne(CommUtil.null2Long(map.get("pid")));
                if (the_gc != null){
                    int count = CommUtil.null2Int(map.get("gc_count"));
                    GsGoodsClass gc = new GsGoodsClass();
                    gc.setId(the_gc.getId());
                    gc.setClassname(the_gc.getClassname());
                    for (int i = 1; i <= count; i++){
                        GsGoodsClass child = this.goodsClassService.findOne(CommUtil.null2Long(map.get("gc_id"+i)));
                        gc.getChildren().add(child);
                    }
                    gcs.add(gc);
                }
            }
        }

        return gcs;
    }

    public List<GsGoods> generic_goods(String json){
        List goods_list = new ArrayList();
        if ((json != null) && (!json.equals(""))){
            Map map = JsonUtils.jsonToPojo(json,Map.class);
            for (int i = 1; i <= 10; i++){
                String key = "goods_id" + i;

                GsGoods goods = this.goodsService.findOne(CommUtil.null2Long(map.get(key)));
                if (goods != null){
                    goods_list.add(goods);
                }
            }
        }

        return goods_list;
    }

    public Map generic_goods_list(String json){
        Map map = new HashMap();
        map.put("list_title", "商品排行");
        if ((json != null) && (!json.equals(""))){
            Map list = JsonUtils.jsonToPojo(json,Map.class);
            map.put("list_title", CommUtil.null2String(list.get("list_title")));
            map.put("goods1", this.goodsService.findOne(CommUtil.null2Long(list.get("goods_id1"))));
            map.put("goods2", this.goodsService.findOne(CommUtil.null2Long(list.get("goods_id2"))));
            map.put("goods3", this.goodsService.findOne(CommUtil.null2Long(list.get("goods_id3"))));
            map.put("goods4", this.goodsService.findOne(CommUtil.null2Long(list.get("goods_id4"))));
            map.put("goods5", this.goodsService.findOne(CommUtil.null2Long(list.get("goods_id5"))));
            map.put("goods6", this.goodsService.findOne(CommUtil.null2Long(list.get("goods_id6"))));
        }

        return map;
    }

    public String generic_adv(String web_url, String json){
        String template = "<div style='float:left;overflow:hidden;'>";
        if ((json != null) && (!json.equals(""))){
            Map map = JsonUtils.jsonToPojo(json,Map.class);
            if (CommUtil.null2String(map.get("adv_id")).equals("")){
                GsAccessory img = this.accessoryService.findOne(CommUtil.null2Long(map.get("acc_id")));
                if (img != null){
                    String url = CommUtil.null2String(map.get("acc_url"));
                    template = template + "<a href='" + url +
                            "' target='_blank'><img src='" + web_url + "/" +
                            img.getPath() + "/" + img.getName() + "' /></a>";
                }
            }else{
//                AdvertPosition ap = this.advertPositionService
//                        .getObjById(CommUtil.null2Long(map.get("adv_id")));
//                AdvertPosition obj = new AdvertPosition();
//                obj.setAp_type(ap.getAp_type());
//                obj.setAp_status(ap.getAp_status());
//                obj.setAp_show_type(ap.getAp_show_type());
//                obj.setAp_width(ap.getAp_width());
//                obj.setAp_height(ap.getAp_height());
//                List advs = new ArrayList();
//                for (Advert temp_adv : ap.getAdvs()){
//                    if ((temp_adv.getAd_status() != 1) ||
//                            (!temp_adv.getAd_begin_time().before(new Date())) ||
//                            (!temp_adv.getAd_end_time().after(new Date()))) continue;
//                    advs.add(temp_adv);
//                }
//
//                if (advs.size() > 0){
//                    if (obj.getAp_type().equals("img")){
//                        if (obj.getAp_show_type() == 0){
//                            obj.setAp_acc(((Advert)advs.get(0)).getAd_acc());
//                            obj.setAp_acc_url(((Advert)advs.get(0)).getAd_url());
//                            obj.setAdv_id(CommUtil.null2String(((Advert)advs.get(0))
//                                    .getId()));
//                        }
//                        if (obj.getAp_show_type() == 1){
//                            Random random = new Random();
//                            int i = random.nextInt(advs.size());
//                            obj.setAp_acc(((Advert)advs.get(i)).getAd_acc());
//                            obj.setAp_acc_url(((Advert)advs.get(i)).getAd_url());
//                            obj.setAdv_id(CommUtil.null2String(((Advert)advs.get(i))
//                                    .getId()));
//                        }
//                    }
//                }else{
//                    obj.setAp_acc(ap.getAp_acc());
//                    obj.setAp_text(ap.getAp_text());
//                    obj.setAp_acc_url(ap.getAp_acc_url());
//                    Advert adv = new Advert();
//                    adv.setAd_url(obj.getAp_acc_url());
//                    adv.setAd_acc(ap.getAp_acc());
//                    obj.getAdvs().add(adv);
//                }
//
//                template = template + "<a href='" + obj.getAp_acc_url() +
//                        "' target='_blank'><img src='" + web_url + "/" +
//                        obj.getAp_acc().getPath() + "/" +
//                        obj.getAp_acc().getName() + "' /></a>";
            }
        }
        template = template + "</div>";

        return template;
    }

    public List<GsGoodsBrand> generic_brand(String json){
        List brands = new ArrayList();
        if ((json != null) && (!json.equals(""))){
            Map map =  JsonUtils.jsonToPojo(json,Map.class);
            for (int i = 1; i <= 11; i++){
                String key = "brand_id" + i;
                GsGoodsBrand brand = this.goodsBrandService.findOne(CommUtil.null2Long(map.get(key)));
                if (brand != null){
                    brands.add(brand);
                }
            }
        }

        return brands;
    }
}
