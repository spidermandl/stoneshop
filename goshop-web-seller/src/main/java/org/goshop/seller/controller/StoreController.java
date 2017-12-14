package org.goshop.seller.controller;

import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.WebForm;
import org.goshop.goods.i.AccessoryService;
import org.goshop.goods.pojo.GsAccessory;
import org.goshop.seller.controller.tools.AreaViewTools;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreService;
import org.goshop.store.i.StoreSlideService;
import org.goshop.store.pojo.GsArea;
import org.goshop.store.pojo.GsStoreSlide;
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
    AccessoryService accessoryService;
    @Autowired
    StoreService storeService;
    @Autowired
    StoreSlideService storeSlideService;

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
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
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
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
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
     * 店铺设置保存
     * @param request
     * @param response
     * @param area_id
     * @param store_second_domain
     * @return
     */
    @RequestMapping({"/store_set_save"})
    public String store_set_save(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String area_id,
                                 String store_second_domain){
        String ret = "success";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        WebForm wf = new WebForm();
        wf.toPo(request, store);

        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String saveFilePathName = request.getSession().getServletContext()
                .getRealPath("/") +
                uploadFilePath + "/store_logo";
        Map map = new HashMap();
        try {
            String fileName = store.getLogo() == null ? "" : store.getLogo().getName();
            map = CommUtil.saveFileToServer(request, "logo", saveFilePathName, fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    GsAccessory store_logo = new GsAccessory();
                    store_logo.setName(CommUtil.null2String(map.get("fileName")));
                    store_logo.setExt(CommUtil.null2String(map.get("mime")));
                    store_logo.setSize(CommUtil.null2Float(map.get("fileSize")));
                    store_logo.setPath(uploadFilePath + "/store_logo");
                    store_logo.setWidth(CommUtil.null2Int(map.get("width")));
                    store_logo.setHeight(CommUtil.null2Int(map.get("height")));
                    store_logo.setAddtime(new Date());
                    long id = this.accessoryService.save(store_logo);
                    store.setStoreLabel(id);
                }
            }else if (map.get("fileName") != ""){
                GsAccessory store_logo = store.getLogo();
                store_logo.setName(CommUtil.null2String(map.get("fileName")));
                store_logo.setExt(CommUtil.null2String(map.get("mime")));
                store_logo.setSize(CommUtil.null2Float(map.get("fileSize")));
                store_logo.setPath(uploadFilePath + "/store_logo");
                store_logo.setWidth(CommUtil.null2Int(map.get("width")));
                store_logo.setHeight(CommUtil.null2Int(map.get("height")));
                this.accessoryService.update(store_logo);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        saveFilePathName = request.getSession().getServletContext().getRealPath("/") +
                this.systemConfigService.getConfig().getUploadFilePath() + "/store_banner";
        try {
            String fileName = store.getBanner() == null ? "" : store.getBanner().getName();
            map = CommUtil.saveFileToServer(request, "banner", saveFilePathName, fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    GsAccessory store_banner = new GsAccessory();
                    store_banner.setName(CommUtil.null2String(map.get("fileName")));
                    store_banner.setExt(CommUtil.null2String(map.get("mime")));
                    store_banner.setSize(CommUtil.null2Float(map.get("fileSize")));
                    store_banner.setPath(uploadFilePath + "/store_banner");
                    store_banner.setWidth(CommUtil.null2Int(map.get("width")));
                    store_banner.setHeight(CommUtil.null2Int(map.get("height")));
                    store_banner.setAddtime(new Date());
                    long id = this.accessoryService.save(store_banner);
                    store.setStoreBanner(id);
                }
            }else if (map.get("fileName") != ""){
                GsAccessory store_banner = store.getBanner();
                store_banner.setName(CommUtil.null2String(map.get("fileName")));
                store_banner.setExt(CommUtil.null2String(map.get("mime")));
                store_banner.setSize(CommUtil.null2Float(map.get("fileSize")));
                store_banner.setPath(uploadFilePath + "/store_banner");
                store_banner.setWidth(CommUtil.null2Int(map.get("width")));
                store_banner.setHeight(CommUtil.null2Int(map.get("height")));
                this.accessoryService.update(store_banner);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        GsArea area = this.storeAreaService.findOne(CommUtil.null2Long(area_id));
        store.setArea(area);
        if (this.systemConfigService.getConfig().getSecond_domain_open()){
            if ((this.systemConfigService.getConfig().getDomain_allow_count() > store.getStoreDomainTimes())
                    &&
                    (!CommUtil.null2String(store_second_domain).equals(""))){
                if (!store_second_domain.equals(store.getStoreDomain())){
                    store.setStoreDomain(store_second_domain);
                    store.setStoreDomainTimes(store.getStoreDomainTimes() + 1);
                }
            }
        }
        this.storeService.update(store);
        model.addAttribute("op_title", "店铺设置成功");
        model.addAttribute("url", CommUtil.getURL(request) + "/store/store_set");

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
        Map map = new HashMap();
//        try {
//            String fileName = store.getCard() == null ? "" : store.getCard().getName();
//            map = CommUtil.saveFileToServer(request, "card_img", saveFilePathName + File.separator + "card", fileName, null);
//            if (fileName.equals("")){
//                if (map.get("fileName") != ""){
//                    GsAccessory card = new GsAccessory();
//                    card.setName(CommUtil.null2String(map.get("fileName")));
//                    card.setExt(CommUtil.null2String(map.get("mime")));
//                    card.setSize(CommUtil.null2Float(map.get("fileSize")));
//                    card.setPath(uploadFilePath + "/card");
//                    card.setWidth(CommUtil.null2Int(map.get("width")));
//                    card.setHeight(CommUtil.null2Int(map.get("height")));
//                    card.setAddtime(new Date());
//                    this.accessoryService.save(card);
//                    store.setCard(card);
//                }
//            }else if (map.get("fileName") != ""){
//                GsAccessory card = store.getCard();
//                card.setName(CommUtil.null2String(map.get("fileName")));
//                card.setExt(CommUtil.null2String(map.get("mime")));
//                card.setSize(CommUtil.null2Float(map.get("fileSize")));
//                card.setPath(uploadFilePath + "/card");
//                card.setWidth(CommUtil.null2Int(map.get("width")));
//                card.setHeight(CommUtil.null2Int(map.get("height")));
//                this.accessoryService.update(card);
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
//                    GsAccessory store_license = new GsAccessory();
//                    store_license.setName(CommUtil.null2String(map.get("fileName")));
//                    store_license.setExt(CommUtil.null2String(map.get("mime")));
//                    store_license.setSize(CommUtil.null2Float(map.get("fileSize")));
//                    store_license.setPath(uploadFilePath + "/license");
//                    store_license.setWidth(CommUtil.null2Int(map.get("width")));
//                    store_license.setHeight(CommUtil.null2Int(map.get("height")));
//                    store_license.setAddtime(new Date());
//                    this.accessoryService.save(store_license);
//                    store.setStore_license(store_license);
//                }
//            }else if (map.get("fileName") != ""){
//                GsAccessory store_license = store.getStore_license();
//                store_license.setName(CommUtil.null2String(map.get("fileName")));
//                store_license.setExt(CommUtil.null2String(map.get("mime")));
//                store_license.setSize(CommUtil.null2Float(map.get("fileSize")));
//                store_license.setPath(uploadFilePath + "/license");
//                store_license.setWidth(CommUtil.null2Int(map.get("width")));
//                store_license.setHeight(CommUtil.null2Int(map.get("height")));
//                this.accessoryService.update(store_license);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        this.storeService.update(store);

        return "redirect:/store/"+ret;
    }

    /**
     * 店铺幻灯保存
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/store_slide_save"})
    public String store_slide_save(@CurrentUser User user,
                                   Model model,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        String ret = "store_slide";
        Store store = this.storeJoinService.getCurrentStore(user);
        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String saveFilePathName = request.getSession().getServletContext().getRealPath("/") + uploadFilePath + File.separator + "store_slide";

//        for (int i = 1; i <= 5; i++){
//            Map map = new HashMap();
//            String fileName = "";
//            GsStoreSlide slide = null;
//            if (store.getSlides().size() >= i){
//                fileName = store.getSlides().get(i - 1).getAcc().getName();
//                slide = store.getSlides().get(i - 1);
//            }
//            try {
//                map = CommUtil.saveFileToServer(request, "acc" + i,saveFilePathName, fileName, null);
//                GsAccessory acc = null;
//                if (fileName.equals("")){
//                    if (map.get("fileName") != ""){
//                        acc = new GsAccessory();
//                        acc.setName(CommUtil.null2String(map.get("fileName")));
//                        acc.setExt(CommUtil.null2String(map.get("mime")));
//                        acc.setSize(CommUtil.null2Float(map.get("fileSize")));
//                        acc.setPath(uploadFilePath + "/store_slide");
//                        acc.setWidth(CommUtil.null2Int(map.get("width")));
//                        acc.setHeight(CommUtil.null2Int(map.get("height")));
//                        acc.setAddtime(new Date());
//                        this.accessoryService.save(acc);
//                    }
//                }else if (map.get("fileName") != ""){
//                    acc = slide.getAcc();
//                    acc.setName(CommUtil.null2String(map.get("fileName")));
//                    acc.setExt(CommUtil.null2String(map.get("mime")));
//                    acc.setSize(CommUtil.null2Float(map.get("fileSize")));
//                    acc.setPath(uploadFilePath + "/store_slide");
//                    acc.setWidth(CommUtil.null2Int(map.get("width")));
//                    acc.setHeight(CommUtil.null2Int(map.get("height")));
//                    acc.setAddtime(new Date());
//                    this.accessoryService.update(acc);
//                }
//
//                if (acc != null){
//                    if (slide == null){
//                        slide = new GsStoreSlide();
//                        slide.setAccId(acc.getId());
//                        slide.setAddtime(new Date());
//                        slide.setStoreId(store.getStoreId());
//                    }
//                    slide.setUrl(request.getParameter("acc_url" + i));
//                    if (slide == null)
//                        this.storeSlideService.save(slide);
//                    else
//                        this.storeSlideService.update(slide);
//                }
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        model.addAttribute("store", store);

        return "store/"+ret;
    }
}
