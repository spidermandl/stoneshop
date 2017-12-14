package org.goshop.seller.controller;

import com.github.pagehelper.PageInfo;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.WebForm;
import org.goshop.goods.i.AccessoryService;
import org.goshop.goods.i.GoodsBrandService;
import org.goshop.goods.pojo.GsAccessory;
import org.goshop.goods.pojo.GsGoodsBrand;
import org.goshop.seller.controller.tools.StoreTools;
import org.goshop.shiro.bind.annotation.CurrentUser;
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
import java.util.Map;

/**
 * 增加\编辑商品品牌
 */
@Controller
@RequestMapping(value = "/brand")
public class BrandsController {

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private GoodsBrandService goodsBrandService;

    @Autowired
    private AccessoryService accessoryService;

    @Autowired
    private StoreTools storeTools;

    @RequestMapping({"/usergoodsbrand_list"})
    public String usergoodsbrand_list(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String currentPage,
                                      String orderBy,
                                      String orderType){
        String ret = "goods_user_brand_list";
        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        orderBy = orderBy==null?"addTime":orderBy;
        orderType = orderType==null?"desc":orderType;
        PageInfo<GsGoodsBrand> pList = this.goodsBrandService.findByUserId(user,index,12,orderBy,orderType);
        for (GsGoodsBrand gb : pList.getList()){
            if (gb.getBrandlogoId()!=null)
                gb.setBrandLogo(this.accessoryService.findOne(gb.getBrandlogoId()));
        }
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);

        return "goods/"+ret;
    }

    @RequestMapping({"/usergoodsbrand_add"})
    public String usergoodsbrand_add(HttpServletRequest request, HttpServletResponse response){
        String ret = "goods_user_brand_add";

        return "goods/"+ret;
    }

    @RequestMapping({"/usergoodsbrand_edit"})
    public String usergoodsbrand_edit(Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response, String id){
        String ret = "goods_user_brand_add";
        if ((id != null) && (!id.equals(""))){
            GsGoodsBrand goodsBrand = this.goodsBrandService.findOne(Long.valueOf(Long.parseLong(id)));
            if (goodsBrand.getBrandlogoId()!=null)
                goodsBrand.setBrandLogo(this.accessoryService.findOne(goodsBrand.getBrandlogoId()));
            model.addAttribute("obj", goodsBrand);
        }
        model.addAttribute("edit", Boolean.valueOf(true));

        return "goods/"+ret;
    }

    @RequestMapping({"/usergoodsbrand_del"})
    public String usergoodsbrand_del(HttpServletRequest request, String id, String currentPage){
        if (!id.equals("")){
            GsGoodsBrand brand = this.goodsBrandService.findOne(Long.valueOf(Long.parseLong(id)));
            if (brand.getAudit() != 1){
                GsAccessory accessory = this.accessoryService.findOne(brand.getBrandlogoId());
                storeTools.del_acc(request, accessory);
                this.goodsBrandService.delete(Long.valueOf(Long.parseLong(id)));
            }
        }

        return "redirect:/brand/usergoodsbrand_list?currentPage=" + currentPage;
    }

    @RequestMapping({"/usergoodsbrand_save"})
    public String usergoodsbrand_save(@CurrentUser User user,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id, String cmd,
                                      String cat_name, String list_url,
                                      String add_url){
        WebForm wf = new WebForm();
        GsGoodsBrand goodsBrand = null;
        if (id.equals("")){
            goodsBrand = (GsGoodsBrand)wf.toPo(request, GsGoodsBrand.class);
            goodsBrand.setAddtime(new Date());
            goodsBrand.setAudit(0);
            goodsBrand.setUserstatus(1);
            goodsBrand.setUserId(user.getId());
        }else{
            GsGoodsBrand obj = this.goodsBrandService.findOne(Long.valueOf(Long.parseLong(id)));
            goodsBrand = (GsGoodsBrand)wf.toPo(request, obj);
        }

        String uploadFilePath = this.systemConfigService.getConfig().getUploadFilePath();
        String saveFilePathName = request.getSession().getServletContext().getRealPath("/")
                +uploadFilePath + File.separator + "brand";
        try {
            GsAccessory photo = null;
            String fileName = "";
            if (goodsBrand.getBrandlogoId()!=null){
                photo = this.accessoryService.findOne(goodsBrand.getBrandlogoId());
                goodsBrand.setBrandLogo(photo);
                fileName = photo.getName();
            }

            Map map = CommUtil.saveFileToServer(request, "brandLogo",saveFilePathName, fileName, null);
            if (fileName.equals("")){
                if (map.get("fileName") != ""){
                    photo = new GsAccessory();
                    photo.setName(CommUtil.null2String(map.get("fileName")));
                    photo.setExt(CommUtil.null2String(map.get("mime")));
                    photo.setSize(CommUtil.null2Float(map.get("fileSize")));
                    photo.setPath(uploadFilePath + "/brand");
                    photo.setWidth(CommUtil.null2Int(map.get("width")));
                    photo.setHeight(CommUtil.null2Int(map.get("height")));
                    photo.setAddtime(new Date());
                    Long p_id = this.accessoryService.save(photo);
                    goodsBrand.setBrandlogoId(p_id);
                }
            }else if (map.get("fileName") != ""){
                photo.setName(CommUtil.null2String(map.get("fileName")));
                photo.setExt(CommUtil.null2String(map.get("mime")));
                photo.setSize(CommUtil.null2Float(map.get("fileSize")));
                photo.setPath(uploadFilePath + "/brand");
                photo.setWidth(CommUtil.null2Int(map.get("width")));
                photo.setHeight(CommUtil.null2Int(map.get("height")));
                photo.setAddtime(new Date());
                this.accessoryService.update(photo);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        if (id.equals(""))
            this.goodsBrandService.save(goodsBrand);
        else
            this.goodsBrandService.update(goodsBrand);

        return "redirect:/brand/usergoodsbrand_list";
    }

}
