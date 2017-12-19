package org.goshop.seller.controller;

/**
 * Created by Desmond on 16/12/2017.
 */

import com.github.pagehelper.PageInfo;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.WebForm;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreNavigationService;
import org.goshop.store.pojo.GsStoreNav;
import org.goshop.store.pojo.Store;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 店铺导航控制器
 */
@Controller
@RequestMapping(value =  "/store")
public class StoreNavigationController {

    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    StoreJoinService storeJoinService;
    @Autowired
    StoreNavigationService storeNavigationService;

    /**
     * 卖家导航管理
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @return
     */
    @RequestMapping({"/store_nav"})
    public String store_nav(@CurrentUser User user,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            String currentPage,
                            String orderBy,
                            String orderType){
        String ret = "store_nav";

        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        Store store = this.storeJoinService.getCurrentStore(user);
        PageInfo<GsStoreNav> pList = this.storeNavigationService.findByStoreId(store.getStoreId(),CommUtil.null2Int(currentPage),12,orderBy,orderType);
        CommUtil.saveIPageList2ModelAndView(url + "/store/store_nav", "",
                params, pList, model);

        return "store/"+ret;
    }

    /**
     * 卖家导航添加
     * @param request
     * @param response
     * @param currentPage
     * @return
     */
    @RequestMapping({"/store_nav_add"})
    public String store_nav_add(Model model,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                String currentPage){
        String ret = "store_nav_add";
        model.addAttribute("currentPage", currentPage);

        return "store/"+ret;
    }

    /**
     * 卖家导航编辑
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping({"/store_nav_edit"})
    public String store_nav_edit(Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String id,
                                 String currentPage){
        String ret = "store_nav_add";
        if ((id != null) && (!id.equals(""))){
            GsStoreNav storenavigation = this.storeNavigationService.findOne(Long.valueOf(Long.parseLong(id)));
            model.addAttribute("obj", storenavigation);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("edit", Boolean.valueOf(true));
        }

        return "store/"+ret;
    }

    /**
     * 卖家导航保存
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param cmd
     * @return
     */
    @RequestMapping({"/store_nav_save"})
    public String store_nav_save(@CurrentUser User user,
                                 Model model,
                                 HttpServletRequest request,
                                       HttpServletResponse response,
                                       String id,
                                       String currentPage,
                                       String cmd){
        WebForm wf = new WebForm();
        GsStoreNav gsStoreNav = null;
        if (id.equals("")){
            gsStoreNav = wf.toPo(request, GsStoreNav.class);
            gsStoreNav.setAddtime(new Date());
        }else{
            GsStoreNav obj = this.storeNavigationService.findOne(Long.valueOf(Long.parseLong(id)));
            gsStoreNav = (GsStoreNav)wf.toPo(request, obj);
        }
        Store store = this.storeJoinService.getCurrentStore(user);
        gsStoreNav.setStoreId(store.getStoreId());
        if (id.equals(""))
            this.storeNavigationService.save(gsStoreNav);
        else
            this.storeNavigationService.update(gsStoreNav);
        String ret = "success";
        model.addAttribute("url", CommUtil.getURL(request) + "/store/store_nav");
        model.addAttribute("op_title", "保存导航成功");

        return "store/"+ret;
    }

    /**
     * 卖家导航删除
     * @param request
     * @param response
     * @param mulitId
     * @param currentPage
     * @return
     */
    @RequestMapping({"/store_nav_del"})
    public String store_nav_del(HttpServletRequest request, HttpServletResponse response, String mulitId, String currentPage){
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                GsStoreNav gsStoreNav = this.storeNavigationService.findOne(Long.valueOf(Long.parseLong(id)));
                this.storeNavigationService.delete(gsStoreNav);
            }
        }

        return "redirect:/store/" + "store_nav?currentPage=" + currentPage;
    }
}
