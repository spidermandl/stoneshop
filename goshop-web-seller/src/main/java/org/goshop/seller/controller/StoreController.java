package org.goshop.seller.controller;

import org.apache.shiro.SecurityUtils;
import org.goshop.assets.i.AccessoryService;
import org.goshop.assets.pojo.GsAccessory;
import org.goshop.common.service.AttachmentService;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.HttpInclude;
import org.goshop.common.web.utils.WebForm;
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
import org.goshop.tools.service.AreaViewTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 操作商店基本信息控制器
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
    AttachmentService attachmentService;

    @Autowired
    AreaViewTools areaViewTools;

//    /**
//     * 我的店铺
//     * @return
//     */
//    @RequestMapping({"/store_me"})
//    public void me(HttpServletRequest request,
//                     HttpServletResponse response,
//                     String id){
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        Store store = this.storeJoinService.getCurrentStore(user);
//        if (store ==null) {
//            HttpInclude include = new HttpInclude(request,response);
//        }
//        return "redirect:/store?id="+store.getStoreId();
//    }

    /**
     * 店铺设置
     * @return
     */
    @RequestMapping({"/store_set"})
    public String store_set(@CurrentUser User user,
                            Model model){
        String ret = "store_set";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        model.addAttribute("store", store);
        model.addAttribute("areaViewTools", this.areaViewTools);
        List areas = this.storeAreaService.findByParentId(null);
        model.addAttribute("areas", areas);
        model.addAttribute("config",this.systemConfigService.getConfig());

        return "store/"+ret;
    }


    /**
     * 店铺幻灯
     * @return
     */
    @RequestMapping({"/store_slide"})
    public String store_slide(@CurrentUser User user,
                              Model model){
        String ret="store_slide";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        model.addAttribute("store", store);

        return "store/"+ret;
    }

    /**
     * 店铺地图
     * @param map_type
     * @return
     */
    @RequestMapping({"/store_map"})
    public String store_map(@CurrentUser User user,
                            Model model,
                            String map_type){
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
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
     * @return
     */
    @RequestMapping({"/store_approve"})
    public String store_approve(@CurrentUser User user,
                                Model model){
        String ret = "store_approve";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        model.addAttribute("store", store);

        return "store/"+ret;
    }

    /**
     * 店铺设置保存
     * @param request
     * @param area_id
     * @param store_second_domain
     * @return
     */
    @RequestMapping({"/store_set_save"})
    public String store_set_save(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 String area_id,
                                 String store_second_domain){
        String ret = "success";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        WebForm wf = new WebForm();
        wf.toPo(request, store);

        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String saveFilePathName = rootPath + uploadFilePath + "/store_logo";
        Map map;
        try {
            String fileName = store.getLogo() == null ? "" : store.getLogo().getName();
            map = CommUtil.saveFileToServer(request, "logo", saveFilePathName, fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    GsAccessory store_logo = new GsAccessory();
                    storeAccessory(request,store_logo,map,
                            uploadFilePath + "/store_logo",
                            saveFilePathName+File.separator+map.get("fileName"));
                    store_logo.setAddtime(new Date());
                    long id = this.accessoryService.save(store_logo);
                    store.setStoreLabel(id);
                }
            }else if (map.get("fileName") != ""){
                GsAccessory store_logo = store.getLogo();
                storeAccessory(request,store_logo,map,
                        uploadFilePath + "/store_logo",
                        saveFilePathName+File.separator+map.get("fileName"));
                this.accessoryService.update(store_logo);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        saveFilePathName = rootPath + this.systemConfigService.getConfig().getUploadFilePath() + "/store_banner";
        try {
            String fileName = store.getBanner() == null ? "" : store.getBanner().getName();
            map = CommUtil.saveFileToServer(request, "banner", saveFilePathName, fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    GsAccessory store_banner = new GsAccessory();
                    storeAccessory(request,store_banner,map,
                            uploadFilePath + "/store_banner",
                            saveFilePathName+File.separator+map.get("fileName"));
                    store_banner.setAddtime(new Date());
                    long id = this.accessoryService.save(store_banner);
                    store.setStoreBanner(id);
                }
            }else if (map.get("fileName") != ""){
                GsAccessory store_banner = store.getBanner();
                storeAccessory(request,store_banner,map,
                        uploadFilePath + "/store_banner",
                        saveFilePathName+File.separator+map.get("fileName"));
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
     * 店铺地图保存
     * @param request
     * @param store_lat
     * @param store_lng
     * @return
     */
    @RequestMapping({"/store_map_save"})
    public String store_map_save(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                 String store_lat,
                                 String store_lng){
        String ret = "success";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        store.setStoreLat(BigDecimal.valueOf(CommUtil.null2Double(store_lat)));
        store.setStoreLng(BigDecimal.valueOf(CommUtil.null2Double(store_lng)));
        this.storeService.update(store);
        model.addAttribute("op_title", "店铺设置成功");
        model.addAttribute("url", CommUtil.getURL(request) + "/store/store_map");

        return "store/"+ret;
    }
    /**
     * 店铺认证保存
     * @param request
     * @return
     */
    @RequestMapping({"/store_approve_save"})
    public String store_approve_save(@CurrentUser User user,
                                     HttpServletRequest request){
        String ret = "store_approve";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String saveFilePathName = rootPath + uploadFilePath;
        Map map;
        try {
            String fileName = store.getCard() == null ? "" : store.getCard().getName();
            map = CommUtil.saveFileToServer(request, "card_img", saveFilePathName + File.separator + "card", fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    GsAccessory card = new GsAccessory();
                    storeAccessory(request,card,map,
                            uploadFilePath + "/card",
                            saveFilePathName+File.separator+map.get("fileName"));
                    card.setAddtime(new Date());
                    long id = this.accessoryService.save(card);
                    store.setStoreImage(id);
                }
            }else if (map.get("fileName") != ""){
                GsAccessory card = store.getCard();
                storeAccessory(request,card,map,
                        uploadFilePath + "/card",
                        saveFilePathName+File.separator+map.get("fileName"));
                this.accessoryService.update(card);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            String fileName = store.getLicense() == null ? "" : store.getLicense().getName();
            map = CommUtil.saveFileToServer(request,
                    "license_img",
                    saveFilePathName + File.separator + "license",
                    fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    GsAccessory store_license = new GsAccessory();
                    store_license.setName(CommUtil.null2String(map.get("fileName")));
                    store_license.setExt(CommUtil.null2String(map.get("mime")));
                    store_license.setSize(CommUtil.null2Float(map.get("fileSize")));
                    store_license.setPath(uploadFilePath + "/license");
                    store_license.setWidth(CommUtil.null2Int(map.get("width")));
                    store_license.setHeight(CommUtil.null2Int(map.get("height")));
                    store_license.setAddtime(new Date());
                    String source = saveFilePathName+File.separator+fileName;
                    this.attachmentService.upload(source,source.substring(rootPath.length()));

                    long id = this.accessoryService.save(store_license);
                    store.setStoreImage1(id);
                }
            }else if (map.get("fileName") != ""){
                GsAccessory store_license = store.getLicense();
                store_license.setName(CommUtil.null2String(map.get("fileName")));
                store_license.setExt(CommUtil.null2String(map.get("mime")));
                store_license.setSize(CommUtil.null2Float(map.get("fileSize")));
                store_license.setPath(uploadFilePath + "/license");
                store_license.setWidth(CommUtil.null2Int(map.get("width")));
                store_license.setHeight(CommUtil.null2Int(map.get("height")));
                this.accessoryService.update(store_license);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        this.storeService.update(store);

        return "redirect:/store/"+ret;
    }

    /**
     * 店铺幻灯保存
     * @param request
     * @return
     */
    @RequestMapping({"/store_slide_save"})
    public String store_slide_save(@CurrentUser User user,
                                   Model model,
                                   HttpServletRequest request){
        String ret = "store_slide";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        StoreWithBLOBs store = this.storeService.findOne(storeId);
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String saveFilePathName = rootPath +uploadFilePath + File.separator + "store_slide";

        for (int i = 1; i <= 5; i++){
            Map map;
            String fileName = "";
            GsStoreSlide slide = null;
            if (store.getSlides().size() >= i){
                fileName = store.getSlides().get(i - 1).getAcc().getName();
                slide = store.getSlides().get(i - 1);
            }
            try {
                map = CommUtil.saveFileToServer(request, "acc" + i,saveFilePathName, fileName, null);
                GsAccessory acc = null;
                if (fileName.equals("")){
                    if (map.get("fileName") != ""){
                        acc = new GsAccessory();
                        storeAccessory(request,acc,map,
                                uploadFilePath + "/store_slide",
                                saveFilePathName+File.separator+map.get("fileName"));
                        acc.setAddtime(new Date());
                        long id = this.accessoryService.save(acc);
                        acc.setId(id);
                    }
                }else if (map.get("fileName") != ""){
                    if (slide!=null) {
                        acc = slide.getAcc();
                        storeAccessory(request,acc,map,
                                uploadFilePath + "/store_slide",
                                saveFilePathName+File.separator+map.get("fileName"));
                        this.accessoryService.update(acc);
                    }
                }

                if (acc != null){
                    if (slide == null){
                        slide = new GsStoreSlide();
                        slide.setAccId(acc.getId());
                        slide.setAddtime(new Date());
                        slide.setStoreId(store.getStoreId());
                        slide.setUrl(request.getParameter("acc_url" + i));
                        this.storeSlideService.save(slide);
                    } else{
                        slide.setUrl(request.getParameter("acc_url" + i));
                        this.storeSlideService.update(slide);
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        model.addAttribute("store", store);

        return "redirect:/store/"+ret;
    }

    /**
     * 远程存储 accessory
     * @param request
     * @param acc
     * @param map
     * @param relative_path
     * @param local_path
     */
    private void storeAccessory(HttpServletRequest request,
                                GsAccessory acc,
                                Map map,
                                String relative_path,
                                String local_path){
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        acc.setName(CommUtil.null2String(map.get("fileName")));
        acc.setExt(CommUtil.null2String(map.get("mime")));
        acc.setSize(CommUtil.null2Float(map.get("fileSize")));
        acc.setPath(relative_path);
        acc.setWidth(CommUtil.null2Int(map.get("width")));
        acc.setHeight(CommUtil.null2Int(map.get("height")));

        try {
            this.attachmentService.upload(local_path,local_path.substring(rootPath.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
