package org.goshop.seller.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.goshop.common.pojo.ResponseStatus;
import org.goshop.common.utils.StringUtils;
import org.goshop.common.web.utils.WebForm;
import org.goshop.seller.controller.tools.GoodsViewTools;
import org.goshop.seller.controller.tools.StoreViewTools;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.GsTransport;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.*;
import org.goshop.goods.pojo.*;
import org.goshop.seller.controller.tools.StoreTools;
import org.goshop.seller.controller.tools.TransportTools;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.TransportService;
import org.goshop.store.pojo.GsTransportWithBLOBs;
import org.goshop.store.pojo.Store;
import org.goshop.store.pojo.StoreJoin;
import org.goshop.users.i.MemberService;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.Member;
import org.goshop.users.pojo.User;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    GoodsAccessoryService goodsAccessoryService;

    @Autowired
    GoodsUserClassService goodsUserClassService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsAlbumService goodsAlbumService;

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
        Store store = storeJoinService.getCurrentStore(user);
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
                        - CommUtil.div(
                                Double.valueOf(CommUtil.fileSize(new File(path))), Integer.valueOf(1024));
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
        return "goods/good_add_step_two";
    }

    @RequestMapping("/step_three")
    public String three(Model model,
                        @CurrentUser User user,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        String id,String goods_class_id,
                        String image_ids,String goods_main_img_id,
                        String user_class_ids,String goods_brand_id,
                        String goods_spec_ids,String goods_properties,
                        String inventory_details,String goods_session,
                        String transport_type,String transport_id){
        String ret = null;
        String goods_session1 = CommUtil.null2String(request.getSession(false).getAttribute("goods_session"));
        if (goods_session1==null || goods_session1.equals("")){
            ret = "error";
            model.addAttribute("op_title", "禁止重复提交表单");
            model.addAttribute("url", CommUtil.getURL(request) + "/goods/index.htm");
            return "goods/"+ret;
        }
        if(!(goods_session1.equals(goods_session))){
            ret = "error";
            model.addAttribute("op_title", "参数错误");
            model.addAttribute("url", CommUtil.getURL(request) + "/goods/index.htm");
            return "goods/"+ret;
        }

        WebForm wf = new WebForm();
        GsGoodsWithBLOBs goods = null;
        if ((id == null) || (id.equals(""))){
            ret = "add_goods_finish";
            goods = (GsGoodsWithBLOBs) wf.toPo(request, GsGoodsWithBLOBs.class);
            goods.setAddtime(new Date());
            Store store = storeJoinService.getCurrentStore(user);
            goods.setGoodsStoreId(store.getStoreId());
        }else{
            ret = "success.html";
            model.addAttribute("op_title", "商品编辑成功");
            model.addAttribute("url", CommUtil.getURL(request) + "/goods_" + id + ".htm");
            GsGoodsWithBLOBs obj = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));
            goods = (GsGoodsWithBLOBs) wf.toPo(request, obj);
        }
        if ((!Integer.valueOf(2).equals(goods.getCombinStatus()))
                && (!Integer.valueOf(2).equals(goods.getDeliveryStatus()))
                && (!Integer.valueOf(2).equals(goods.getBargainStatus()))
                && (!Integer.valueOf(2).equals(goods.getActivityStatus()))){
            goods.setGoodsCurrentPrice(goods.getStorePrice());
        }
        goods.setGoodsName(clearContent(goods.getGoodsName()));
        GsGoodsClass gc = this.goodsClassService.findOne(Long.valueOf(Long.parseLong(goods_class_id)));
        goods.setGcId(gc.getId());
        //主照片
        GsGoodsAccessory main_img = null;
        if ((goods_main_img_id != null) && (!goods_main_img_id.equals(""))){
            main_img = this.goodsAccessoryService.findOne(Long.valueOf(Long.parseLong(goods_main_img_id)));
            goods.setGoodsMainPhotoId(main_img.getId());
        }

        /**
         * 商品照片逻辑
         */
        String[] img_ids = image_ids.split(",");

        for (int i = 0; i < img_ids.length; i++){
            String img_id = img_ids[i];
            if (!img_id.equals("")){
                GsGoodsAccessory img = new GsGoodsAccessory();
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
        if (CommUtil.null2Int(transport_type) == 0){
            GsTransport trans = this.transportService.findOne(CommUtil.null2Long(transport_id));
            goods.setTransportId(trans.getId());
        }
        if (CommUtil.null2Int(transport_type) == 1){
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
            //this.goodsService.update(goods);

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
                GsGoodsWithBLOBs goods =
                        this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));

                Store store = this.storeService.findOne(goods.getGoodsStoreId());
                User member = this.userService.findOne(store.getMemberId());
                if (member.getId().equals(user.getId())){
                    int goods_status = goods.getGoodsStatus() == 0 ? 1 : 0;
                    goods.setGoodsStatus(goods_status);
                    this.goodsService.update(goods);// 更新商品资料
                    if (goods_status == 0){
                        url = "/goods_storage";

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
                    map.put("gid", goods.getId());
//                    List<GoodsCart> goodCarts = this.goodsCartService
//                            .query("select obj from GoodsCart obj where obj.goods.id = :gid",
//                                    map, -1, -1);
//                    Long ofid = null;
//                    Long of_id;
//                    for (GoodsCart gc : goodCarts){
//                        of_id = gc.getOf().getId();
//                        this.goodsCartService.delete(gc.getId());
//                        OrderForm of = this.orderFormService.getObjById(of_id);
//                        if (of.getGcs().size() == 0){
//                            this.orderFormService.delete(of_id);
//                        }
//                    }
//
//                    List<Evaluate> evaluates = goods.getEvaluates();
//                    for (Evaluate e : evaluates){
//                        this.evaluateService.delete(e.getId());
//                    }
//                    goods.getGoodsUgcs().clear();
//                    goods.getGoods_ugcs().clear();
//                    goods.getGoods_photos().clear();
//                    goods.getGoods_ugcs().clear();
//                    goods.getGoods_specs().clear();
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
    @RequestMapping({ "/goods_inventory.htm" })
    public String goods_inventory(Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String goods_spec_ids){
        String ret = "goods_inventory";
        String[] spec_ids = goods_spec_ids.split(",");
        List<GsGoodsSpecProperty> gsps = new ArrayList();
        // GoodsSpecProperty gsp;
        for (String spec_id : spec_ids){
            if (!spec_id.equals("")){
                GsGoodsSpecProperty gsp =
                        this.goodsPropertyService.findOne(Long.valueOf(Long.parseLong(spec_id)));
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

        Store store = storeJoinService.getCurrentStore(user);
        String path = this.storeTools.createUserFolder(request, store);
        String url = this.storeTools.createUserFolderURL(store);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("imgFile");
        double fileSize = Double.valueOf(file.getSize()).doubleValue();
        fileSize /= 1048576.0D;
        double csize = CommUtil.fileSize(new File(path));
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
                Map params = new HashMap();
                params.put("store_id", store.getStoreId());
//                List wms = this.waterMarkService
//                        .query("select obj from WaterMark obj where obj.store.id=:store_id", params, -1, -1);
//                if (wms.size() > 0){
//                    WaterMark mark = (WaterMark) wms.get(0);
//                    if (mark.isWm_image_open()){
//                        String pressImg = request.getSession().getServletContext().getRealPath("")
//                                + File.separator + mark.getWm_image().getPath()
//                                + File.separator + mark.getWm_image().getName();
//                        String targetImg = path + File.separator + map.get("fileName");
//                        int pos = mark.getWm_image_pos();
//                        float alpha = mark.getWm_image_alpha();
//                        CommUtil.waterMarkWithImage(pressImg, targetImg, pos, alpha);
//                    }
//                    if (mark.isWm_text_open()){
//                        String targetImg = path + File.separator + map.get("fileName");
//                        int pos = mark.getWm_text_pos();
//                        String text = mark.getWm_text();
//                        String markContentColor = mark.getWm_text_color();
//                        CommUtil.waterMarkWithText(
//                                targetImg, targetImg, text, markContentColor,
//                                new Font(mark.getWm_text_font(), 1, mark.getWm_text_font_size()), pos, 100.0F);
//                    }
//                }
                GsGoodsAccessory image = new GsGoodsAccessory();
                image.setAddtime(new Date());
                image.setExt((String) map.get("mime"));
                image.setPath(url);
                image.setWidth(CommUtil.null2Int(map.get("width")));
                image.setHeight(CommUtil.null2Int(map.get("height")));
                image.setName(CommUtil.null2String(map.get("fileName")));
                image.setUserId(user.getId());
                image.setSize((float)fileSize);
                GsAlbum album = null;
                if ((album_id != null) && (!album_id.equals(""))){
                    album = this.goodsAlbumService.findOne(CommUtil.null2Long(album_id));
                }else{
                    album = this.goodsAlbumService.getDefaultAlbum(user);
                    if (album == null){
                        album = new GsAlbum();
                        album.setAddtime(new Date());
                        album.setAlbumName("默认相册");
                        album.setAlbumSequence(-10000);
                        album.setAlbumDefault(true);
                        album.setUserId(user.getId());
                        this.goodsAlbumService.save(album);
                    }
                }
                image.setAlbumId(album.getId());
                long image_id = this.goodsAccessoryService.save(image);
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

                String midext = image.getExt().indexOf(".") < 0 ? "." + image.getExt() : image.getExt();
                String midtarget = source + "_middle" + ext;
                CommUtil.createSmall(source, midtarget,
                        this.systemConfigService.getConfig().getMiddleWidth(),
                        this.systemConfigService.getConfig().getMiddleHeight());
            } catch (IOException e){
                e.printStackTrace();
            }
        }else{
            json_map.put("url", "");
            json_map.put("id", "");
            json_map.put("remainSpace", Integer.valueOf(0));
        }

        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");} catch (java.io.UnsupportedEncodingException e1) {e1.printStackTrace();}
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(json_map).toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 上传商品说明嵌入图片
     * @param user
     * @param request
     * @param response
     * @throws ClassNotFoundException
     */
    @RequestMapping({"/upload.htm"})
    public void upload(@CurrentUser User user,
                       HttpServletRequest request,
                       HttpServletResponse response) throws ClassNotFoundException {
        Store store = storeJoinService.getCurrentStore(user);

        String saveFilePathName = this.storeTools.createUserFolder(request,store);
        String url = this.storeTools.createUserFolderURL(store);

//        String webPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
//        if ((this.configService.getSysConfig().getAddress() != null) &&
//                (!this.configService.getSysConfig().getAddress().equals(""))){
//            webPath = this.configService.getSysConfig().getAddress() + webPath;
//        }

        JSONObject obj = new JSONObject();
        try {
            Map map = CommUtil.saveFileToServer(request, "imgFile", saveFilePathName, null, null);
            url = CommUtil.getURL(request) + "/" + url + "/" + map.get("fileName");
            obj.put("error", Integer.valueOf(0));
            obj.put("url", url);
        } catch (IOException e){
            obj.put("error", Integer.valueOf(1));
            obj.put("message", e.getMessage());
            e.printStackTrace();
        }
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");} catch (java.io.UnsupportedEncodingException e1) {e1.printStackTrace();}
        try {
            PrintWriter writer = response.getWriter();
            writer.print(obj.toString());
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
        Store store = storeJoinService.getCurrentStore(user);
        String path = this.storeTools.createUserFolder(request, store);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");} catch (java.io.UnsupportedEncodingException e1) {e1.printStackTrace();}
        try {
            Map map = new HashMap();
            GsGoodsAccessory img = this.goodsAccessoryService.findOne(CommUtil.null2Long(image_id));
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
                ret = this.goodsAccessoryService.delete(img);
                if (ret > 0) {
                    storeTools.del_acc(request, img);
                }

            }
            double csize = CommUtil.fileSize(new File(path));
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

        PageInfo<GsGoodsAccessory> pList = goodsAccessoryService.findByUserId(user,page,pageSize);
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
    @RequestMapping({ "/goods_transport.htm" })
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
        PageInfo<GsTransportWithBLOBs> plist = this.transportService.findByStoreId(store,index,1,orderBy,orderType);
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
                           String currentPage, String orderBy,
                           String orderType, String goods_name,
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
        //获取主图片
        for (GsGoodsWithBLOBs goods : pList.getList()){
            if (goods.getGoodsMainPhotoId()!=null){
                goods.setGoods_main_photo(this.goodsAccessoryService.findOne(goods.getGoodsMainPhotoId()));
            }
        }
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
     * @param orderBy
     * @param orderType
     * @param goods_name
     * @param user_class_id
     * @return
     */
    @RequestMapping({ "/goods_storage" })
    public String goods_storage(@CurrentUser User user,
                                Model model,
                                HttpServletRequest request,
                                HttpServletResponse response, String currentPage,
                                String orderBy,String orderType,
                                String goods_name, String user_class_id){
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
        CommUtil.saveIPageList2ModelAndView(url + "/goods/goods_storage",
                "", params, pList, model);
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
        CommUtil.saveIPageList2ModelAndView(url + "/goods/goods_out", "",
                params, pList, model);
        model.addAttribute("storeTools", this.storeTools);
        model.addAttribute("goodsViewTools", this.goodsViewTools);

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
                             HttpServletResponse response, String id){
        String ret = "good_add_step_two";
        GsGoodsWithBLOBs obj = this.goodsService.findOne(Long.valueOf(Long.parseLong(id)));

        Store store = this.storeService.findOne(obj.getGoodsStoreId());
        User member = this.userService.findOne(store.getMemberId());
        if (member.getId().equals(user.getId())){
            store = this.storeJoinService.getCurrentStore(user);
            String path = request.getSession().getServletContext()
                    .getRealPath("/")
                    + File.separator
                    + "upload"
                    + File.separator
                    + "store"
                    + File.separator + store.getStoreId();
            double img_remain_size = store.getStoreGrade().getSgSpaceLimit()
                    - CommUtil.div(
                    Double.valueOf(CommUtil.fileSize(new File(path))),
                    Integer.valueOf(1024));
            List ugcs = this.goodsUserClassService.findByUserIdAndParentId(user.getId(),null,true);

            PageInfo<GsGoodsAccessory> pList = this.goodsAccessoryService.findByUserId(user,1,8);
            String photo_url = CommUtil.getURL(request)
                    + "/seller/load_photo.htm";
            List gbs = this.goodsBrandService.findByUserId(user);
            model.addAttribute("gbs", gbs);
            model.addAttribute("photos", pList.getList());
            model.addAttribute("gotoPageAjaxHTML",
                    CommUtil.showPageAjaxHtml(photo_url, "",pList.getPageNum(), pList.getPages()));
            model.addAttribute("ugcs", ugcs);
            model.addAttribute("img_remain_size", Double.valueOf(img_remain_size));
            model.addAttribute("obj", obj);
            if (request.getSession(false).getAttribute("goods_class_info") != null){
                GsGoodsClass session_gc =
                        (GsGoodsClass) request.getSession(false).getAttribute("goods_class_info");
                GsGoodsClass gc = this.goodsClassService.findOne(session_gc.getId());
                model.addAttribute(
                        "goods_class_info",this.storeTools.generic_goods_class_info(gc));
                model.addAttribute("goods_class", gc);
                request.getSession(false).removeAttribute("goods_class_info");
            }else if (obj.getGcId() != null){
                obj.setGc(goodsClassService.findOne(obj.getGcId()));
                model.addAttribute(
                        "goods_class_info",this.storeTools.generic_goods_class_info(obj.getGc()));
                model.addAttribute("goods_class", obj.getGc());
            }

            String goods_session = CommUtil.randomString(32);
            model.addAttribute("goods_session", goods_session);
            request.getSession(false).setAttribute("goods_session",goods_session);
            model.addAttribute("imageSuffix",
                    this.storeViewTools.genericImageSuffix(
                            this.systemConfigService.getConfig().getImageSuffix()));
        }else{
            ret = "error";
            model.addAttribute("op_title", "您没有该商品信息！");
            model.addAttribute("url", CommUtil.getURL(request) + "/index.htm");
        }

        return "goods/"+ret;
    }
    /****************************************************************
     * *****************private func
     ****************************************************************/
    private String clearContent(String inputString){
        String htmlStr = inputString;
        String textStr = "";
        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";
            String regEx_html = "<[^>]+>";
            String regEx_html1 = "<[^>]+";
            Pattern p_script = Pattern.compile(regEx_script, 2);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");

            Pattern p_style = Pattern.compile(regEx_style, 2);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");

            Pattern p_html = Pattern.compile(regEx_html, 2);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");

            Pattern p_html1 = Pattern.compile(regEx_html1, 2);
            Matcher m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll("");

            textStr = htmlStr;
        } catch (Exception e){
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;
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
