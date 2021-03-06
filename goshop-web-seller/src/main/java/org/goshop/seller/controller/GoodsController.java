package org.goshop.seller.controller;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.goshop.assets.i.AccessoryService;
import org.goshop.assets.i.AlbumService;
import org.goshop.assets.pojo.GsAccessory;
import org.goshop.assets.pojo.GsAlbum;
import org.goshop.common.pojo.ResponseStatus;
import org.goshop.common.service.AttachmentService;
import org.goshop.common.utils.StringUtils;
import org.goshop.common.web.utils.WebForm;
import org.goshop.order.i.GoodsCartService;
import org.goshop.order.i.OrderFormService;
import org.goshop.order.pojo.GsGoodsCart;
import org.goshop.store.i.StoreService;
import org.goshop.goods.pojo.GsTransport;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.*;
import org.goshop.goods.pojo.*;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.goods.i.TransportService;
import org.goshop.goods.pojo.GsTransportWithBLOBs;
import org.goshop.store.pojo.Store;
import org.goshop.store.pojo.StoreJoin;
import org.goshop.store.pojo.StoreWithBLOBs;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.User;
import org.goshop.tools.service.GoodsViewTools;
import org.goshop.tools.service.StoreTools;
import org.goshop.tools.service.StoreViewTools;
import org.goshop.tools.service.TransportTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 增加\编辑商品控制器
 */
@Controller
@RequestMapping(value =  "/goods")
public class GoodsController {

    @Autowired
    GoodsClassStapleService goodsClassStapleService;
    @Autowired
    GoodsClassService goodsClassService;
    @Autowired
    GoodsCommonService goodsCommonService;
    @Autowired
    StoreJoinService storeJoinService;
    @Autowired
    AccessoryService accessoryService;
    @Autowired
    GoodsUserClassService goodsUserClassService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    AlbumService albumService;
    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    TransportService transportService;
    @Autowired
    GoodsBrandService goodsBrandService;
    @Autowired
    GoodsPropertyService goodsPropertyService;
    @Autowired
    GoodsTypeService goodsTypeService;
    @Autowired
    StoreService storeService;
    @Autowired
    UserService userService;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    GoodsCartService goodsCartService;
    @Autowired
    OrderFormService orderFormService;
    @Autowired
    GoodsEvaluateService goodsEvaluateService;

    @Autowired
    StoreTools storeTools;
    @Autowired
    TransportTools transportTools;
    @Autowired
    StoreViewTools storeViewTools;
    @Autowired
    GoodsViewTools goodsViewTools;

    @RequestMapping("/step_one")
    public String one(Model model,
                      @CurrentUser User user,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        List<GoodsClassStaple> goodsClassStapleList=goodsClassStapleService.findByMemberId(user.getId());
        model.addAttribute("P_GOODS_CLASS_STAPLE_LIST", goodsClassStapleList);
        List<GsGoodsClass> goodsClassList= goodsClassService.findByGcParentId(0L);
        model.addAttribute("P_GOODS_CLASS_LIST", goodsClassList);
        return "goods/good_add_step_one";
    }

    /**
     * 选择分类
     * @param id
     * @param deep
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/class")
    public @ResponseBody List getClass(
            @RequestParam("gc_id") Long id,
            @RequestParam("deep") String deep,
            HttpServletRequest request,
            HttpServletResponse response) {

        List<GsGoodsClass> goodsClassList = goodsClassService.findByGcParentId(id);

        if (goodsClassList==null||goodsClassList.size()==0) {//叶子节点分类
            request.getSession(false).setAttribute("goods_class_info", goodsClassService.findOne(id));
        }
        return goodsClassList;
    }

    @RequestMapping("/class_staple")
    public @ResponseBody Object getClassStaple(
            @RequestParam("stapleid") Long stapleId,
            HttpServletRequest request,
            HttpServletResponse response) {
        JsonGoodsClass json = goodsClassStapleService.selectGoodsClassStaple(stapleId);
        return json;
    }

    /**
     * 第一步操作
     * @param stapleId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/staple_del")
    public
    @ResponseBody
    Object stapleDel(
            @RequestParam("stapleid") Long stapleId,
            HttpServletRequest request,
            HttpServletResponse response) {
        goodsClassStapleService.delete(stapleId);
        return ResponseStatus.get(true);
    }

    /**
     * 第二步操作
     * @param model
     * @param user
     * @param classId
     * @param tId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/step_two")
    public String two(Model model,
                      @CurrentUser User user,
                      @RequestParam("class_id") Long classId,
                      @RequestParam("t_id") String tId,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        //判断分类是否3级
        GsGoodsClass goodsClass = goodsClassService.findOne(classId);
        //检查并保存常用分类
        //goodsClassStapleService.checkSaveStaple(user,goodsClass);


        StoreJoin storeJoin = storeJoinService.getCurrentUserStoreJoin(user);
        Long storeId = storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = storeService.findOne(storeId);
        //已提交申请 "10";
        //缴费完成 "11";
        //审核成功 "20";
        //审核失败 "30";
        //缴费审核失败 "31";
        //审核通过开店 "40";
        if (storeJoin.getJoininState().equals("40")){
            if (request.getSession(false).getAttribute("goods_class_info") != null){
                GsGoodsClass gc = (GsGoodsClass) request.getSession(false).getAttribute("goods_class_info");
                gc = this.goodsClassService.findOne(gc.getId());
                String goods_class_info = storeTools.generic_goods_class_info(gc);
                model.addAttribute("goods_class",gc);
                model.addAttribute("goods_class_info", goods_class_info.substring(0,
                        goods_class_info.length() - 1));
                request.getSession(false).removeAttribute("goods_class_info");
            }
            String path = request.getSession().getServletContext()
                    .getRealPath("/")
                    + File.separator
                    + "upload"
                    + File.separator
                    + "store"
                    + File.separator + store.getStoreId();
            double img_remain_size = 0.0D;
            if (store.getStoreGrade().getSgSpaceLimit() > 0.0F){
                img_remain_size = store.getStoreGrade().getSgSpaceLimit()
                        - CommUtil.div(Double.valueOf(CommUtil.fileSize(new File(path))), Integer.valueOf(1024));
            }
            List ugcs = this.goodsUserClassService.findByUserIdAndParentId(user.getId(),null,true);
            List gbs = this.goodsBrandService.findByUserId(user);
            model.addAttribute("gbs", gbs);
            model.addAttribute("ugcs", ugcs);
            model.addAttribute("img_remain_size", Double.valueOf(img_remain_size));
            model.addAttribute("imageSuffix",
                    this.storeViewTools.genericImageSuffix(this.systemConfigService.getConfig().getImageSuffix()));
            String goods_session = CommUtil.randomString(32);
            model.addAttribute("goods_session", goods_session);
            request.getSession(false).setAttribute("goods_session",goods_session);
        }
//        if (store_status == 0){
//            model.addAttribute("op_title", "您尚未开通店铺，不能发布商品");
//            model.addAttribute("url", CommUtil.getURL(request) + "/seller/index.htm");
//        }
//        if (store_status == 1){
//            model.addAttribute("op_title", "您的店铺在审核中，不能发布商品");
//            model.addAttribute("url", CommUtil.getURL(request) + "/seller/index.htm");
//        }
//        if (store_status == 3){
//            model.addAttribute("op_title", "您的店铺已被关闭，不能发布商品");
//            model.addAttribute("url", CommUtil.getURL(request) + "/seller/index.htm");
//        }

        model.addAttribute("goods_class",goodsClass);
        model.addAttribute("config",systemConfigService.getConfig());
        model.addAttribute("CommUtil",new CommUtil());
        model.addAttribute("trans_count",this.transportService.hasTransfortTemplate(storeId));
        return "goods/good_add_step_two";
    }

    /**
     * 第三部操作，保存商品
     * @param model
     * @param user
     * @param request
     * @param response
     * @param id
     * @param goods_class_id
     * @param image_ids
     * @param goods_main_img_id
     * @param user_class_ids
     * @param goods_brand_id
     * @param goods_spec_ids
     * @param goods_properties
     * @param inventory_details
     * @param goods_session
     * @param transport_type
     * @param transport_id
     * @return
     */
    @RequestMapping("/step_three")
    public String three(Model model,
                        @CurrentUser User user,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        String id,
                        String goods_class_id,
                        String image_ids,
                        String goods_main_img_id,
                        String user_class_ids,
                        String goods_brand_id,
                        String goods_spec_ids,
                        String goods_properties,
                        String inventory_details,
                        String goods_session,
                        String transport_type,
                        String transport_id){
        String ret = null;
        String goods_session1 = CommUtil.null2String(request.getSession(false).getAttribute("goods_session"));
        if (goods_session1==null || goods_session1.equals("")){
            ret = "error";
            model.addAttribute("op_title", "禁止重复提交表单");
            model.addAttribute("url", CommUtil.getURL(request) + "/home");
            return "goods/"+ret;
        }
        if(!(goods_session1.equals(goods_session))){
            ret = "error";
            model.addAttribute("op_title", "参数错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/home");
            return "goods/"+ret;
        }

        WebForm wf = new WebForm();
        GsGoodsWithBLOBs goods = null;
        if ((id == null) || (id.equals(""))){
            ret = "add_goods_finish";
            goods = wf.toPo(request, GsGoodsWithBLOBs.class);
            goods.setAddtime(new Date());
            Store store = storeJoinService.getCurrentStore(user);
            goods.setGoodsStoreId(store.getStoreId());
        }else{
            ret = "success";
            model.addAttribute("op_title", "商品编辑成功");
            model.addAttribute("url", CommUtil.getURL(request) + "goods/step_one");
            GsGoodsWithBLOBs obj = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));
            goods = (GsGoodsWithBLOBs) wf.toPo(request, obj);
        }
        if ((!Integer.valueOf(2).equals(goods.getCombinStatus()))
                && (!Integer.valueOf(2).equals(goods.getDeliveryStatus()))
                && (!Integer.valueOf(2).equals(goods.getBargainStatus()))
                && (!Integer.valueOf(2).equals(goods.getActivityStatus()))){
            goods.setGoodsCurrentPrice(goods.getStorePrice());
        }
        goods.setZtcAdminId(user.getId());
        goods.setGoodsName(CommUtil.clearContent(goods.getGoodsName()));
        GsGoodsClass gc = this.goodsClassService.findOne(Long.valueOf(Long.parseLong(goods_class_id)));
        goods.setGcId(gc.getId());
        //主照片
        GsAccessory main_img = null;
        if ((goods_main_img_id != null) && (!goods_main_img_id.equals(""))){
            main_img = this.accessoryService.findOne(Long.valueOf(Long.parseLong(goods_main_img_id)));
            goods.setGoodsMainPhotoId(main_img.getId());
        }

        /**
         * 商品照片逻辑
         */
        String[] img_ids = image_ids.split(",");

        for (int i = 0; i < img_ids.length; i++){
            String img_id = img_ids[i];
            if (!img_id.equals("")){
                GsAccessory img = new GsAccessory();
                img.setId(CommUtil.null2Long(img_id));
                goods.getGoodsPhotos().add(img);
            }
        }
        /***************** end *********************************/

        /**
         * 商品分类逻辑
         */
        goods.getGoodsUgcs().clear();
        String[] ugc_ids = user_class_ids.split(",");

        for (int i = 0; i < ugc_ids.length; i++){
            String ugc_id = ugc_ids[i];
            if (!ugc_id.equals("")){
                GsGoodsUserClass ugc =
                        this.goodsUserClassService.findOne(Long.valueOf(Long.parseLong(ugc_id)));
                goods.getGoodsUgcs().add(ugc);
            }
        }
        /***************** end *********************************/
        /**
         * 商品品牌
         */
        if ((goods_brand_id != null) && (!goods_brand_id.equals(""))){
            GsGoodsBrand goods_brand =
                    this.goodsBrandService.findOne(Long.valueOf(Long.parseLong(goods_brand_id)));
            goods.setGoodsBrandId(goods_brand.getId());
            goods.setGoodsBrand(goods_brand);
        }
        goods.getGoodsSpecs().clear();
        String[] spec_ids = goods_spec_ids.split(",");

        for (int i = 0; i < spec_ids.length; i++){
            String spec_id = spec_ids[i];
            if (!spec_id.equals("")){
                GsGoodsSpecProperty gsp = this.goodsPropertyService.findOne(Long.valueOf(Long.parseLong(spec_id)));
                goods.getGoodsSpecs().add(gsp);
            }
        }
        /***************** end *********************************/
        /**
         * 组装商品属性
         */
        List maps = new ArrayList();
        if (org.apache.commons.lang.StringUtils.isNotEmpty(goods_properties)) {
            String[] properties = goods_properties.split(";");
            String[] list;
            for (int i = 0; i < properties.length; i++) {
                String property = properties[i];
                if (StringUtils.isNotEmpty(property)) {
                    list = property.split(",");
                    Map map = new HashMap();
                    map.put("id", list[0]);
                    map.put("val", list[1]);
                    map.put("name", this.goodsTypeService.findOne(Long.valueOf(Long.parseLong(list[0]))).getName());
                    maps.add(map);
                }
            }
            goods.setGoodsProperty(JSONObject.fromObject(maps).toString());
        }
        maps.clear();
        /***************** end *********************************/
        /**
         * 组装商品多规格库存和价格
         */
        if(org.apache.commons.lang.StringUtils.isNotEmpty(inventory_details)){
            String[] inventory_list = inventory_details.split(";");
            for (int i = 0; i < inventory_list.length; i++){
                String inventory = inventory_list[i];
                if (org.apache.commons.lang.StringUtils.isNotEmpty(inventory)){
                    String[] list1 = inventory.split(",");
                    Map map = new HashMap();
                    map.put("id", list1[0]);
                    map.put("count", list1[1]);
                    map.put("price", list1[2]);
                    maps.add(map);
                }
            }
            goods.setGoodsDetails(JSONObject.fromObject(maps).toString());
        }
        /***************** end *********************************/

        /**
         * 物流相关逻辑
         */
        if (transport_id==null){
            goods.setTransportId(null);
        }else if (CommUtil.null2Int(transport_type) == 0){
            GsTransport trans = this.transportService.findOne(CommUtil.null2Long(transport_id));
            goods.setTransportId(trans.getId());
        }else if (CommUtil.null2Int(transport_type) == 1){
            goods.setTransportId(null);
        }
        /***************** end *********************************/

        if ((id == null) || (id.equals(""))){
            Long g_id = this.goodsService.save(goods);
            goods.setId(g_id);
            /**************************************************
             ************************************************/
//                String goods_lucene_path = (new StringBuilder(String.valueOf(System.getProperty("wemall.root"))))
//                        .append(File.separator).append("lucene").append(File.separator).append("goods").toString();
//                File file = new File(goods_lucene_path);
//                if (!file.exists()){
//                    CommUtil.createFolder(goods_lucene_path);
//                }
//                LuceneVo vo = new LuceneVo();
//                vo.setVo_id(goods.getId());
//                vo.setVo_title(goods.getGoods_name());
//                vo.setVo_content(goods.getGoods_details());
//                vo.setVo_type("goods");
//                vo.setVo_store_price(CommUtil.null2Double(goods.getStore_price()));
//                vo.setVo_add_time(goods.getAddTime().getTime());
//                vo.setVo_goods_salenum(goods.getGoods_salenum());
//                LuceneUtil lucene = LuceneUtil.instance();
//                LuceneUtil.setIndex_path(goods_lucene_path);
//                lucene.writeIndex(vo);
        }else{
            this.goodsService.update(goods);

//                String goods_lucene_path = (new StringBuilder(String.valueOf(System.getProperty("wemall.root")))).append(File.separator).append("lucene").append(File.separator).append("goods").toString();
//                File file = new File(goods_lucene_path);
//                if (!file.exists()){
//                    CommUtil.createFolder(goods_lucene_path);
//                }
//                LuceneVo vo = new LuceneVo();
//                vo.setVo_id(goods.getId());
//                vo.setVo_title(goods.getGoods_name());
//                vo.setVo_content(goods.getGoods_details());
//                vo.setVo_type("goods");
//                vo.setVo_store_price(CommUtil.null2Double(goods.getStore_price()));
//                vo.setVo_add_time(goods.getAddTime().getTime());
//                vo.setVo_goods_salenum(goods.getGoods_salenum());
//                LuceneUtil lucene = LuceneUtil.instance();
//                LuceneUtil.setIndex_path(goods_lucene_path);
//                lucene.update(CommUtil.null2String(goods.getId()), vo);
        }
        model.addAttribute("obj", goods);
        request.getSession(false).removeAttribute("goods_session");


        return "goods/"+ret;
    }
    /**
     * 商品编辑
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({ "/goods_edit" })
    public String goods_edit(@CurrentUser User user,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             String id){
        String ret = "good_add_step_two";
        GsGoodsWithBLOBs obj = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));

        Long storeId = this.storeService.findOne(obj.getGoodsStoreId()).getStoreId();
        StoreWithBLOBs store = storeService.findOne(storeId);
        User member = this.userService.findOne(store.getMemberId());
        if (member.getId().equals(user.getId())){
            String path = request.getSession().getServletContext()
                    .getRealPath("/")
                    + File.separator
                    + "upload"
                    + File.separator
                    + "store"
                    + File.separator + store.getStoreId();
            double img_remain_size = store.getStoreGrade().getSgSpaceLimit()
                    - CommUtil.div(Double.valueOf(CommUtil.fileSize(new File(path))), Integer.valueOf(1024));

            List ugcs = this.goodsUserClassService.findByUserIdAndParentId(user.getId(),null,true);
            PageInfo<GsAccessory> pList = this.accessoryService.findByUserId(user.getId(),1,8);
            String photo_url = CommUtil.getURL(request) + "/seller/load_photo.htm";
            List gbs = this.goodsBrandService.findByUserId(user);
            model.addAttribute("gbs", gbs);
            model.addAttribute("photos", pList.getList());
            model.addAttribute("gotoPageAjaxHTML",
                    CommUtil.showPageAjaxHtml(photo_url, "",pList.getPageNum(), pList.getPages()));
            model.addAttribute("ugcs", ugcs);
            model.addAttribute("img_remain_size", Double.valueOf(img_remain_size));
            model.addAttribute("obj", obj);
            if (request.getSession(false).getAttribute("goods_class_info") != null){
                GsGoodsClass session_gc=(GsGoodsClass) request.getSession(false).getAttribute("goods_class_info");
                obj.setGc(this.goodsClassService.findOne(session_gc.getId()));
                model.addAttribute("goods_class_info",this.storeTools.generic_goods_class_info(obj.getGc()));
                model.addAttribute("goods_class", obj.getGc());
                request.getSession(false).removeAttribute("goods_class_info");
            }else if (obj.getGcId() != null){
                obj.setGc(goodsClassService.findOne(obj.getGcId()));
                model.addAttribute("goods_class_info",this.storeTools.generic_goods_class_info(obj.getGc()));
                model.addAttribute("goods_class", obj.getGc());
            }
            model.addAttribute("trans_count",this.transportService.hasTransfortTemplate(storeId));
            String goods_session = CommUtil.randomString(32);
            model.addAttribute("goods_session", goods_session);
            request.getSession(false).setAttribute("goods_session",goods_session);
            model.addAttribute("imageSuffix",
                    this.storeViewTools.genericImageSuffix(
                            this.systemConfigService.getConfig().getImageSuffix()));
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有该商品信息！");
            model.addAttribute("url", CommUtil.getURL(request) + "/index");
        }

        return "goods/"+ret;
    }
    /**
     * 商品上下架管理
     * @param request
     * @param response
     * @param mulitId
     * @return
     */
    @RequestMapping({ "/goods_sale" })
    public String goods_sale(@CurrentUser User user,
                             HttpServletRequest request,
                             HttpServletResponse response, String mulitId){
        String url = "/forsell_list";
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                GsGoodsWithBLOBs goods = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));

                Store store = this.storeService.findOne(goods.getGoodsStoreId());
                User member = this.userService.findOne(store.getMemberId());
                if (member.getId().equals(user.getId())){
                    int goods_status = goods.getGoodsStatus() == 0 ? 1 : 0;
                    goods.setGoodsStatus(goods_status);
                    this.goodsService.update(goods);// 更新商品资料
                    if (goods_status == 0){
                        url = "goods_storage";

                        // 更新全文检索
                        String goods_lucene_path = (new StringBuilder(String.valueOf(System.getProperty("wemall.root")))).append(File.separator).append("lucene").append(File.separator).append("goods").toString();
                        File file = new File(goods_lucene_path);
                        if (!file.exists()){
                            CommUtil.createFolder(goods_lucene_path);
                        }

//                        LuceneVo vo = new LuceneVo();
//                        vo.setVo_id(goods.getId());
//                        vo.setVo_title(goods.getGoods_name());
//                        vo.setVo_content(goods.getGoods_details());
//                        vo.setVo_type("goods");
//                        vo.setVo_store_price(CommUtil.null2Double(goods
//                                .getStore_price()));
//                        vo.setVo_add_time(goods.getAddTime().getTime());
//                        vo.setVo_goods_salenum(goods.getGoods_salenum());
//                        LuceneUtil lucene = LuceneUtil.instance();
//                        LuceneUtil.setIndex_path(goods_lucene_path);
//                        lucene.update(CommUtil.null2String(goods.getId()), vo);
                    }else{
                        String goods_lucene_path = (new StringBuilder(String.valueOf(System.getProperty("wemall.root")))).append(File.separator).append("lucene").append(File.separator).append("goods").toString();
                        File file = new File(goods_lucene_path);
                        if (!file.exists()){
                            CommUtil.createFolder(goods_lucene_path);
                        }
//                        LuceneUtil lucene = LuceneUtil.instance();
//                        lucene.delete_index(CommUtil.null2String(goods.getId()));
                    }
                }
            }
        }

        return "redirect:/goods/" + url;
    }

    /**
     * 商品删除
     * @param request
     * @param response
     * @param mulitId
     * @param op
     * @return
     */
    @RequestMapping({ "/goods_del" })
    public String goods_del(@CurrentUser User user,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String mulitId, String op){
        String url = "forsell_list";
        if (CommUtil.null2String(op).equals("storage")){
            url = "goods_storage";
        }
        if (CommUtil.null2String(op).equals("out")){
            url = "goods_out";
        }
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                GsGoodsWithBLOBs goods = this.goodsService.findOne(CommUtil.null2Long(id));

                Store store = this.storeService.findOne(goods.getGoodsStoreId());
                User member = this.userService.findOne(store.getMemberId());
                if (member.getId().equals(user.getId())){
                    Map map = new HashMap();
                    map.put("goods_id", goods.getId());
                    List<GsGoodsCart> goodCarts = this.goodsCartService.findByCondition(map);
//                            "select obj from GoodsCart obj where obj.goods.id = :gid",

                    for (GsGoodsCart gc : goodCarts){
                        Long of_id = gc.getOfId();
                        this.goodsCartService.delete(gc.getId());
                        if (this.goodsCartService.isEmptyCart(of_id)){
                            this.orderFormService.delete(of_id);
                        }
                    }
                    this.goodsService.delete(goods.getId());
//
//                    String goods_lucene_path = (new StringBuilder(String.valueOf(System.getProperty("wemall.root")))).append(File.separator).append("lucene").append(File.separator).append("goods").toString();
//                    File file = new File(goods_lucene_path);
//                    if (!file.exists()){
//                        CommUtil.createFolder(goods_lucene_path);
//                    }
//                    LuceneUtil lucene = LuceneUtil.instance();
//                    LuceneUtil.setIndex_path(goods_lucene_path);
//                    lucene.delete_index(CommUtil.null2String(id));
                }
            }
        }

        return "redirect:/goods/" + url;
    }

    /**
     * 产品规格显示
     * @param request
     * @param response
     * @param goods_spec_ids
     * @return
     */
    @RequestMapping({ "/goods_inventory" })
    public String goods_inventory(Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String goods_spec_ids){
        String ret = "goods_inventory";
        String[] spec_ids = goods_spec_ids.split(",");
        List<GsGoodsSpecProperty> gsps = new ArrayList();
        for (String spec_id : spec_ids){
            if (!spec_id.equals("")){
                GsGoodsSpecProperty gsp = this.goodsPropertyService.findOne(Long.valueOf(Long.parseLong(spec_id)));
                gsps.add(gsp);
            }
        }
        Set<GsGoodsSpecification> specs = new HashSet<>();
        for (GsGoodsSpecProperty gsp : gsps){
            specs.add(gsp.getSpec());
        }
        for (GsGoodsSpecification spec : specs){
            spec.getProperties().clear();
            for (GsGoodsSpecProperty gsp : gsps){
                if (gsp.getSpec().getId().equals(spec.getId())){
                    spec.getProperties().add(gsp);
                }
            }
        }
        GsGoodsSpecification[] spec_list = specs.toArray(new GsGoodsSpecification[specs.size()]);
        Arrays.sort(spec_list, new Comparator(){
            public int compare(Object obj1, Object obj2){
                GsGoodsSpecification a = (GsGoodsSpecification) obj1;
                GsGoodsSpecification b = (GsGoodsSpecification) obj2;
                if (a.getSequence() == b.getSequence()){
                    return 0;
                }
                return a.getSequence() > b.getSequence() ? 1 : -1;
            }
        });
        List gsp_list = generic_spec_property(specs);
        model.addAttribute("specs", Arrays.asList(spec_list));
        model.addAttribute("gsps", gsp_list);

        return "goods/"+ret;
    }
    /**
     * 上传商品展示图片
     * @param user
     * @param request
     * @param response
     * @param album_id
     */
    @RequestMapping({ "/swf_upload.htm" })
    public void swf_upload( @CurrentUser User user,
                            HttpServletRequest request,
                            HttpServletResponse response, String album_id){

        Long storeId = storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = storeService.findOne(storeId);
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String path = this.storeTools.createUserFolder(rootPath, store.getStoreId());
        String url = this.storeTools.createUserFolderURL(store.getStoreId());

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("imgFile");
        double fileSize = Double.valueOf(file.getSize()).doubleValue();
        fileSize /= 1048576.0D;
        double csize = this.attachmentService.foldSize(path.substring(rootPath.length()));
//                CommUtil.fileSize(new File(path));
        double remainSpace = 0.0D;
        if (store.getStoreGrade().getSgSpaceLimit() != 0.0F)
            remainSpace = (store.getStoreGrade().getSgSpaceLimit() * 1024.0F - csize) * 1024.0D;
        else{
            remainSpace = 10000000.0D;
        }
        Map json_map = new HashMap();
        if (remainSpace > fileSize){
            try {
                Map map = CommUtil.saveFileToServer(request, "imgFile", path, null, null);
                GsAccessory image = this.storeTools.bundleAccessory(map,url,store.getStoreId(),user.getId());
                GsAlbum album = null;
                if ((album_id != null) && (!album_id.equals(""))){
                    album = this.albumService.findOne(CommUtil.null2Long(album_id));
                }else{
                    album = this.albumService.getDefaultAlbum(user.getId());
                    if (album == null){
                        album = new GsAlbum();
                        album.setAddtime(new Date());
                        album.setAlbumName("默认相册");
                        album.setAlbumSequence(-10000);
                        album.setAlbumDefault(true);
                        album.setUserId(user.getId());
                        this.albumService.save(album);
                    }
                }
                image.setAlbumId(album.getId());
                long image_id = this.accessoryService.save(image);
                image.setId(image_id);
                json_map.put("url", CommUtil.getURL(request) + "/" + url + "/" + image.getName());
                json_map.put("id", image.getId());
                json_map.put("remainSpace", Double.valueOf(remainSpace == 10000.0D ? 0.0D : remainSpace));

                String ext = image.getExt().indexOf(".") < 0 ? "." + image.getExt() : image.getExt();
                String source = request.getSession().getServletContext().getRealPath("/")
                        + image.getPath() + File.separator + image.getName();
                String target = source + "_small" + ext;
                CommUtil.createSmall(source, target,
                        this.systemConfigService.getConfig().getSmallWidth(),
                        this.systemConfigService.getConfig().getSmallHeight());

                String midtarget = source + "_middle" + ext;
                CommUtil.createSmall(source, midtarget,
                        this.systemConfigService.getConfig().getMiddleWidth(),
                        this.systemConfigService.getConfig().getMiddleHeight());

                this.attachmentService.upload(source,source.substring(rootPath.length()));
                this.attachmentService.upload(target,target.substring(rootPath.length()));
                this.attachmentService.upload(midtarget,midtarget.substring(rootPath.length()));

            } catch (IOException e){
                e.printStackTrace();
            }
        }else{
            json_map.put("url", "");
            json_map.put("id", "");
            json_map.put("remainSpace", Integer.valueOf(0));
        }

        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");}
        catch (java.io.UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(json_map).toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * 删除上传图片
     * @param user
     * @param request
     * @param response
     * @param image_id
     */
    @RequestMapping({ "/goods_image_del.htm" })
    public void goods_image_del(@CurrentUser User user,
                                HttpServletRequest request,
                                HttpServletResponse response, String image_id){
        Long storeId = storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = storeService.findOne(storeId);
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String path = this.storeTools.createUserFolder(rootPath, store.getStoreId());
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");} catch (java.io.UnsupportedEncodingException e1) {e1.printStackTrace();}
        try {
            Map map = new HashMap();
            GsAccessory img = this.accessoryService.findOne(CommUtil.null2Long(image_id));
            int ret = 0;
            if (img!=null) {
                //更新主照片
                List<GsGoodsWithBLOBs> mainList = this.goodsService.findGoodsByMainPhoto(img);
                for (GsGoodsWithBLOBs goods : mainList) {
                    goods.setGoodsMainPhotoId(null);
                    this.goodsService.update(goods);
                }
                //删除所有该附件与goods的关联信息
                this.goodsService.removeLinkByAccessoryId(img);
                //删除附件记录
                ret = this.accessoryService.delete(img);
                if (ret > 0) {
                    storeTools.del_acc(request, img);
                }

            }
            /****删除远程文件***/
            String ext = img.getExt().indexOf(".") < 0 ? "." + img.getExt() : img.getExt();
            String source = img.getPath() + File.separator + img.getName();
            String target = source + "_small" + ext;
            String midtarget = source + "_middle" + ext;
            this.attachmentService.deleteFile(source);
            this.attachmentService.deleteFile(target);
            this.attachmentService.deleteFile(midtarget);
            /*****************/
            double csize = this.attachmentService.foldSize(path.substring(rootPath.length()));
//                    CommUtil.fileSize(new File(path));
            double remainSpace = 10000.0D;
            if (store.getStoreGrade().getSgSpaceLimit() != 0.0F) {
                remainSpace = CommUtil.div(Double.valueOf(store.getStoreGrade().getSgSpaceLimit()
                        * 1024.0F - csize), Integer.valueOf(1024));
            }
            map.put("result", Boolean.valueOf(ret>0?true:false));
            map.put("remainSpace", Double.valueOf(remainSpace));
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(map).toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取用户上传的accessory
     * @param user
     * @param model
     * @param request
     * @param response
     * @param currentPage
     * @param type
     * @return
     */
    @RequestMapping({ "/goods_img_album.htm" })
    public String goods_img_album(@CurrentUser User user,
                                  Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String currentPage, String type){
        Integer pageSize = Integer.valueOf(16);
        Integer page = 1;
        try {
            page = Integer.valueOf(currentPage);
        }catch (Exception e){
        }

        PageInfo<GsAccessory> pList = accessoryService.findByUserId(user.getId(),page,pageSize);
        String photo_url = CommUtil.getURL(request)
                + "/goods/goods_img_album.htm";
        model.addAttribute("photos", pList.getList());
        model.addAttribute("gotoPageAjaxHTML",
                CommUtil.showPageAjaxHtml(photo_url,"", pList.getPageNum(), pList.getPages()));

        return "goods/"+type;
    }

    /**
     * 商品运费模板分页显示
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param ajax
     * @return
     */
    @RequestMapping({ "/goods_transport" })
    public String goods_transport(@CurrentUser User user,
                                  Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String currentPage,
                                  String orderBy,
                                  String orderType,
                                  String ajax){
        String ret = "goods/goods_transport";
        if (CommUtil.null2Boolean(ajax)){
            ret = "goods/goods_transport_list";
        }
        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        Store store = storeJoinService.getCurrentStore(user);
        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        PageInfo<GsTransportWithBLOBs> plist = this.transportService.findByStoreId(
                store.getStoreId(),index,1,orderBy,orderType);
        CommUtil.saveIPageList2ModelAndView(
                url + "/goods/goods_transport", "", params, plist, model);
        model.addAttribute("transportTools", this.transportTools);
        model.addAttribute("CommUtil",new CommUtil());

        return ret;
    }

    /**
     * 查询出售中的商品列表
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param goods_name
     * @param user_class_id
     * @return
     */
    @RequestMapping({ "/forsell_list" })
    public String sellList(@CurrentUser User user,
                           Model model,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           String currentPage,
                           String orderBy,
                           String orderType,
                           String goods_name,
                           String user_class_id){
        String ret = "goods";
        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        Store store = this.storeJoinService.getCurrentStore(user);
        String params = "";
        Map<String,Object> condition = new HashedMap();
        condition.put("goods_store_id",store.getStoreId());
        condition.put("goods_status",0);
        condition.put("orderBy","addTime");
        condition.put("orderType","desc");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(goods_name)){
            condition.put("goods_name",goods_name);
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(user_class_id)){
            condition.put("user_class_id", Long.valueOf(Long.parseLong(user_class_id)));
        }

        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        PageInfo<GsGoodsWithBLOBs> pList = goodsService.findByCondition(condition,index,12);// 根据条件查询商品
        fillGoodsListContent(pList.getList());
        CommUtil.saveIPageList2ModelAndView(url + "/goods/forsell_list", "",
                params, pList, model);
        model.addAttribute("uid",user.getId());
        model.addAttribute("storeTools", this.storeTools);
        model.addAttribute("goodsViewTools", this.goodsViewTools);

        return "goods/"+ret;
    }

    /**
     * 仓库中的商品列表
     * @param request
     * @param response
     * @param currentPage
     * @param goods_name
     * @param user_class_id
     * @return
     */
    @RequestMapping({ "/goods_storage" })
    public String goods_storage(@CurrentUser User user,
                                Model model,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                String currentPage,
                                String goods_name,
                                String user_class_id){
        String ret = "goods_storage";
        String url = this.systemConfigService.getConfig().getAddress();
        if (org.apache.commons.lang.StringUtils.isEmpty(url)){
            url = CommUtil.getURL(request);
        }
        Store store = this.storeJoinService.getCurrentStore(user);
        String params = "";
        Map<String,Object> condition = new HashedMap();
        condition.put("goods_store_id",store.getStoreId());
        condition.put("goods_status",1);
        condition.put("orderBy","goods_seller_time");
        condition.put("orderType","desc");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(goods_name)){
            condition.put("goods_name",goods_name);
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(user_class_id)){
            condition.put("user_class_id", Long.valueOf(Long.parseLong(user_class_id)));
        }

        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        PageInfo<GsGoodsWithBLOBs> pList = goodsService.findByCondition(condition,index,12);// 根据条件查询商品
        fillGoodsListContent(pList.getList());
        CommUtil.saveIPageList2ModelAndView(url + "/goods/goods_storage",
                "", params, pList, model);
        model.addAttribute("uid",user.getId());
        model.addAttribute("storeTools", this.storeTools);
        model.addAttribute("goodsViewTools", this.goodsViewTools);

        return "goods/"+ret;
    }

    /**
     * 违规下架商品
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @param goods_name
     * @param user_class_id
     * @return
     */
    @RequestMapping({ "/goods_out" })
    public String goods_out(@CurrentUser User user,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String currentPage, String orderBy,
                            String orderType, String goods_name,
                            String user_class_id){
        String ret = "goods_out";
        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        Store store = this.storeJoinService.getCurrentStore(user);
        String params = "";
        Map<String,Object> condition = new HashedMap();
        condition.put("goods_store_id",store.getStoreId());
        condition.put("goods_status",-2);
        condition.put("orderBy","goods_seller_time");
        condition.put("orderType","desc");

        if (org.apache.commons.lang.StringUtils.isNotEmpty(goods_name)){
            condition.put("goods_name",goods_name);
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(user_class_id)){
            condition.put("user_class_id", Long.valueOf(Long.parseLong(user_class_id)));
        }
        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        PageInfo<GsGoodsWithBLOBs> pList = goodsService.findByCondition(condition,index,12);// 根据条件查询商品
        fillGoodsListContent(pList.getList());
        CommUtil.saveIPageList2ModelAndView(url + "/goods/goods_out", "",params, pList, model);
        model.addAttribute("uid",user.getId());
        model.addAttribute("storeTools", this.storeTools);
        model.addAttribute("goodsViewTools", this.goodsViewTools);

        return "goods/"+ret;
    }

    /**
     * 查询goods相关附属信息
     * @param list
     */
    private void fillGoodsListContent(List<GsGoodsWithBLOBs> list){
        for (GsGoodsWithBLOBs goods : list){
            if (goods.getGoodsMainPhotoId()!=null){//获取主图片
                goods.setGoods_main_photo(this.accessoryService.findOne(goods.getGoodsMainPhotoId()));
            }
            if (goods.getGcId()!=null){//获取goods 分类
                goods.setGc(this.goodsClassService.findOne(goods.getGcId()));
            }
        }
    }
    private List<List<GsGoodsSpecProperty>> generic_spec_property(
            Set<GsGoodsSpecification> specs){
        List result_list = new ArrayList();
        List list = new ArrayList();
        int max = 1;
        for (GsGoodsSpecification spec : specs){
            list.add(spec.getProperties());
        }

        GsGoodsSpecProperty[][] gsps = list2group(list);
        for (int i = 0; i < gsps.length; i++){
            max *= gsps[i].length;
        }
        for (int i = 0; i < max; i++){
            List temp_list = new ArrayList();
            int temp = 1;
            for (int j = 0; j < gsps.length; j++){
                temp *= gsps[j].length;
                temp_list.add(j, gsps[j][(i / (max / temp) % gsps[j].length)]);
            }
            GsGoodsSpecProperty[] temp_gsps = (GsGoodsSpecProperty[]) temp_list
                    .toArray(new GsGoodsSpecProperty[temp_list.size()]);
            Arrays.sort(temp_gsps, new Comparator(){
                public int compare(Object obj1, Object obj2){
                    GsGoodsSpecProperty a = (GsGoodsSpecProperty) obj1;
                    GsGoodsSpecProperty b = (GsGoodsSpecProperty) obj2;
                    if (a.getSpec().getSequence() == b.getSpec().getSequence()){
                        return 0;
                    }
                    return a.getSpec().getSequence() > b.getSpec()
                            .getSequence() ? 1 : -1;
                }
            });
            result_list.add(Arrays.asList(temp_gsps));
        }

        return result_list;
    }

    private GsGoodsSpecProperty[][] list2group(
            List<List<GsGoodsSpecProperty>> list){
        GsGoodsSpecProperty[][] gps = new GsGoodsSpecProperty[list.size()][];
        for (int i = 0; i < list.size(); i++){
            gps[i] = ((GsGoodsSpecProperty[]) ((List) list.get(i))
                    .toArray(new GsGoodsSpecProperty[((List) list.get(i)).size()]));
        }

        return gps;
    }
}
