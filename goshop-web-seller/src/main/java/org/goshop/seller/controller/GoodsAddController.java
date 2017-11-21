package org.goshop.seller.controller;

import org.goshop.common.pojo.ResponseStatus;
import org.goshop.common.utils.CommUtil;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.i.GoodsClassStapleService;
import org.goshop.goods.i.GoodsCommonService;
import org.goshop.goods.pojo.*;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.pojo.StoreJoin;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value =  "/goods_add")
public class GoodsAddController {

    @Autowired
    GoodsClassStapleService goodsClassStapleService;

    @Autowired
    GoodsClassService goodsClassService;

    @Autowired
    GoodsCommonService goodsCommonService;

    @Autowired
    StoreJoinService storeJoinService;

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

    @RequestMapping("/class")
    public
    @ResponseBody
    List getClass(
            @RequestParam("gc_id") Long id,
            @RequestParam("deep") String deep,
            HttpServletRequest request,
            HttpServletResponse response) {

        List<GsGoodsClass> goodsClassList = goodsClassService.findByGcParentId(id);
        return goodsClassList;
    }

    @RequestMapping("/class_staple")
    public
    @ResponseBody
    Object getClassStaple(
            @RequestParam("stapleid") Integer stapleId,
            HttpServletRequest request,
            HttpServletResponse response) {

        JsonGoodsClass json = goodsClassStapleService.selectGoodsClassStaple(stapleId);
        return json;
    }

    @RequestMapping("/staple_del")
    public
    @ResponseBody
    Object stapleDel(
            @RequestParam("stapleid") Integer stapleId,
            HttpServletRequest request,
            HttpServletResponse response) {
        goodsClassStapleService.delete(stapleId);
        return ResponseStatus.get(true);
    }

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
        //已提交申请 "10";
        //缴费完成 "11";
        //审核成功 "20";
        //审核失败 "30";
        //缴费审核失败 "31";
        //审核通过开店 "40";
        if (storeJoin.getJoininState().equals("40")){
            if (request.getSession(false).getAttribute("goods_class_info") != null){
                GsGoodsClass gc = (GsGoodsClass) request.getSession(false)
                        .getAttribute("goods_class_info");
                gc = this.goodsClassService.findOne(gc.getId());
                String goods_class_info = generic_goods_class_info(gc);
                model.addAttribute("goods_class",gc);
                model.addAttribute("goods_class_info", goods_class_info.substring(0,
                        goods_class_info.length() - 1));
                request.getSession(false).removeAttribute("goods_class_info");
            }
//            String path = request.getSession().getServletContext()
//                    .getRealPath("/")
//                    + File.separator
//                    + "upload"
//                    + File.separator
//                    + "store"
//                    + File.separator + user.getStore().getId();
//            double img_remain_size = 0.0D;
//            if (user.getStore().getGrade().getSpaceSize() > 0.0F){
//                img_remain_size = user.getStore().getGrade().getSpaceSize()
//                        - CommUtil.div(Double.valueOf(CommUtil
//                        .fileSize(new File(path))), Integer
//                        .valueOf(1024));
//            }
            Map params = new HashMap();
            params.put("user_id", user.getId());
            params.put("display", Boolean.valueOf(true));
//            List ugcs = this.userGoodsClassService
//                    .query("select obj from UserGoodsClass obj where obj.user.id=:user_id and obj.display=:display and obj.parent.id is null order by obj.sequence asc",
//                            params, -1, -1);
//            List gbs = this.goodsBrandService.query(
//                    "select obj from GoodsBrand obj order by obj.sequence asc",
//                    null, -1, -1);
//            model.addAttribute("gbs", gbs);
//            model.addAttribute("ugcs", ugcs);
//            model.addAttribute("img_remain_size", Double.valueOf(img_remain_size));
//            model.addAttribute("imageSuffix", this.storeViewTools
//                    .genericImageSuffix(this.configService.getSysConfig()
//                            .getImageSuffix()));
            String goods_session = CommUtil.randomString(32);
            model.addAttribute("goods_session", goods_session);
            request.getSession(false).setAttribute("goods_session",
                    goods_session);
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
        return "goods/good_add_step_two";
    }

    public String three(Model model,
                        @CurrentUser User user,
                        GoodsCommonWithBLOBs goodsCommonWithBLOBs){
        goodsCommonService.save(goodsCommonWithBLOBs);
        return "goods/good_add_step_three";
    }
    /***
     * private func
     */
    private String generic_goods_class_info(GsGoodsClass gc){
        String goods_class_info = gc.getClassname() + ">";
        if (gc.getParentId() != null){
            GsGoodsClass g = null;
            g = goodsClassService.findOne(g.getParentId());
            String class_info = generic_goods_class_info(g);
            goods_class_info = class_info + goods_class_info;
        }

        return goods_class_info;
    }
}
