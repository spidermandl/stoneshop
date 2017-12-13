package org.goshop.seller.controller;

import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsAccessoryService;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.seller.controller.tools.AreaViewTools;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.goshop.store.pojo.StoreWithBLOBs;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作商店控制器
 */
@Controller
@RequestMapping(value =  "/store")
public class StoreController{

    @Autowired
    StoreJoinService storeJoinService;
    @Autowired
    StoreAreaService storeAreaService;
    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    GoodsAccessoryService goodsAccessoryService;
    @Autowired
    StoreService storeService;

    @Autowired
    AreaViewTools areaViewTools;

    /**
     * 店铺设置
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/store_set"})
    public String store_set(@CurrentUser User user,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        String ret = "store_set";
        Store store = storeJoinService.getCurrentStore(user);
        model.addAttribute("store", store);
        model.addAttribute("areaViewTools", this.areaViewTools);
        List areas = this.storeAreaService.findByParentId(null);
        model.addAttribute("areas", areas);

        return "store/"+ret;
    }


    /**
     * 店铺幻灯
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/store_slide"})
    public String store_slide(@CurrentUser User user,
                              Model model,
                              HttpServletRequest request,
                              HttpServletResponse response){
        String ret="store_slide";
        Store store = this.storeJoinService.getCurrentStore(user);
        model.addAttribute("store", store);

        return "store/"+ret;
    }

    /**
     * 店铺地图
     * @param request
     * @param response
     * @param map_type
     * @return
     */
    @RequestMapping({"/store_map"})
    public String store_map(@CurrentUser User user,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String map_type){
        Store store = this.storeJoinService.getCurrentStore(user);
        if (CommUtil.null2String(map_type).equals("")){
            if ((store.getMapType() != null) && (!store.getMapType().equals(""))){
                map_type = store.getMapType();
            }else{
                map_type = "baidu";
            }
        }
        String ret = "store_" +map_type + "_map";
        model.addAttribute("store", store);

        return "store/"+ret;
    }

    /**
     * 店铺认证
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/store_approve"})
    public String store_approve(@CurrentUser User user,
                                Model model,
                                HttpServletRequest request,
                                HttpServletResponse response){
        String ret = "store_approve";
        Store store = this.storeJoinService.getCurrentStore(user);
        model.addAttribute("store", store);

        return "store/"+ret;
    }

    /**
     * 店铺认证保存
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/store_approve_save"})
    public String store_approve_save(@CurrentUser User user,
                                     Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        String ret = "store_approve";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);

        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String saveFilePathName = request.getSession().getServletContext().getRealPath("/") + uploadFilePath;
//        Map map = new HashMap();
//        try {
//            String fileName = store.getCard() == null ? "" : store.getCard().getName();
//            map = CommUtil.saveFileToServer(request, "card_img", saveFilePathName + File.separator + "card", fileName, null);
//            if (fileName.equals("")){
//                if (map.get("fileName") != ""){
//                    GsGoodsAccessory card = new GsGoodsAccessory();
//                    card.setName(CommUtil.null2String(map.get("fileName")));
//                    card.setExt(CommUtil.null2String(map.get("mime")));
//                    card.setSize(CommUtil.null2Float(map.get("fileSize")));
//                    card.setPath(uploadFilePath + "/card");
//                    card.setWidth(CommUtil.null2Int(map.get("width")));
//                    card.setHeight(CommUtil.null2Int(map.get("height")));
//                    card.setAddtime(new Date());
//                    this.goodsAccessoryService.save(card);
//                    store.setCard(card);
//                }
//            }else if (map.get("fileName") != ""){
//                GsGoodsAccessory card = store.getCard();
//                card.setName(CommUtil.null2String(map.get("fileName")));
//                card.setExt(CommUtil.null2String(map.get("mime")));
//                card.setSize(CommUtil.null2Float(map.get("fileSize")));
//                card.setPath(uploadFilePath + "/card");
//                card.setWidth(CommUtil.null2Int(map.get("width")));
//                card.setHeight(CommUtil.null2Int(map.get("height")));
//                this.goodsAccessoryService.update(card);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        try {
//            String fileName = store.getStore_license() == null ? "" : store.getStore_license().getName();
//            map = CommUtil.saveFileToServer(request,
//                    "license_img",
//                    saveFilePathName + File.separator + "license",
//                    fileName, null);
//            if (fileName.equals("")){
//                if (map.get("fileName") != ""){
//                    GsGoodsAccessory store_license = new GsGoodsAccessory();
//                    store_license.setName(CommUtil.null2String(map.get("fileName")));
//                    store_license.setExt(CommUtil.null2String(map.get("mime")));
//                    store_license.setSize(CommUtil.null2Float(map.get("fileSize")));
//                    store_license.setPath(uploadFilePath + "/license");
//                    store_license.setWidth(CommUtil.null2Int(map.get("width")));
//                    store_license.setHeight(CommUtil.null2Int(map.get("height")));
//                    store_license.setAddtime(new Date());
//                    this.goodsAccessoryService.save(store_license);
//                    store.setStore_license(store_license);
//                }
//            }else if (map.get("fileName") != ""){
//                GsGoodsAccessory store_license = store.getStore_license();
//                store_license.setName(CommUtil.null2String(map.get("fileName")));
//                store_license.setExt(CommUtil.null2String(map.get("mime")));
//                store_license.setSize(CommUtil.null2Float(map.get("fileSize")));
//                store_license.setPath(uploadFilePath + "/license");
//                store_license.setWidth(CommUtil.null2Int(map.get("width")));
//                store_license.setHeight(CommUtil.null2Int(map.get("height")));
//                this.goodsAccessoryService.update(store_license);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        this.storeService.update(store);

        return "redirect:/store/store_approve";
    }
}
