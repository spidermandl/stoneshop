package org.goshop.seller.controller;

import com.github.pagehelper.PageInfo;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.WebForm;
import org.goshop.goods.i.GoodsUserClassService;
import org.goshop.goods.pojo.GsGoodsUserClass;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 30/11/2017.
 * bug
 *
 */
@Controller
@RequestMapping(value =  "/goods_category")
public class GoodsClassifyController {
    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private GoodsUserClassService goodsUserClassService;

    //卖家商品分类列表
    @RequestMapping({"/goods_user_class_list"})
    public String usergoodsclass_list(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String currentPage,
                                      String orderBy,
                                      String orderType){
        String ret = "goods_user_class_list";
        String url = this.systemConfigService.getConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        int index = CommUtil.null2Int(currentPage);
        index = index==0?1:index;
        PageInfo<GsGoodsUserClass> pList = this.goodsUserClassService.findRootClassByUserId(user,index,20);

        CommUtil.saveIPageList2ModelAndView(url +
                "/goods_category/goods_user_class_list", "", params, pList, model);

        return "goods/"+ret;
    }

    //卖家商品分类保存
    @RequestMapping({"/goods_user_class_save"})
    public String usergoodsclass_save(@CurrentUser User user,
                                      Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String id,
                                      String pid){
        WebForm wf = new WebForm();
        GsGoodsUserClass userClass = null;
        boolean is_create = false;
        if (id==null || id.equals("")){
            userClass = (GsGoodsUserClass)wf.toPo(request, GsGoodsUserClass.class);
            userClass.setAddtime(new Date());
            is_create = true;
        }else{
            GsGoodsUserClass obj = this.goodsUserClassService.findOne(Long.valueOf(Long.parseLong(id)));
            userClass = (GsGoodsUserClass)wf.toPo(request, obj);
        }
        userClass.setUserId(user.getId());
        if (pid!=null && !pid.equals("")){
            userClass.setParentId(Long.valueOf(Long.parseLong(pid)));
        }

        if (is_create)
            this.goodsUserClassService.save(userClass);
        else
            this.goodsUserClassService.update(userClass);

        return "redirect:/goods_category/goods_user_class_list";
    }

    //卖家商品分类删除
    @RequestMapping({"/goods_user_class_del"})
    public String usergoodsclass_del(HttpServletRequest request,
                                     String mulitId){
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                this.goodsUserClassService.delete(Long.valueOf(Long.parseLong(id)));
            }
        }

        return "redirect:/goods_category/goods_user_class_list";
    }

    //新增卖家商品分类
    @RequestMapping({"/goods_user_class_add"})
    public String usergoodsclass_add(@CurrentUser User user,
                                     Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String currentPage,
                                     String pid){
        String ret = "goods_user_class_add";
        Map map = new HashMap();
        map.put("uid", user.getId());
        List ugcs = this.goodsUserClassService.findByUserIdAndParentId(user.getId(),null,null);
        if (!CommUtil.null2String(pid).equals("")){
            GsGoodsUserClass parent = this.goodsUserClassService.findOne(CommUtil.null2Long(pid));
            GsGoodsUserClass obj = new GsGoodsUserClass();
            obj.setParent(parent);
            model.addAttribute("obj", obj);
        }
        model.addAttribute("ugcs", ugcs);
        model.addAttribute("currentPage", currentPage);

        return "goods/"+ret;
    }

    //编辑卖家商品分类
    @RequestMapping({"/goods_user_class_edit"})
    public String usergoodsclass_edit(@CurrentUser User user,
                                            Model model,
                                            HttpServletRequest request,
                                            HttpServletResponse response,
                                            String currentPage,
                                            String id){
        String ret = "goods_user_class_add";
        List ugcs = this.goodsUserClassService.findByUserIdAndParentId(user.getId(),null,null);
        GsGoodsUserClass obj = this.goodsUserClassService.findOne(CommUtil.null2Long(id));
        if (obj.getParentId()!=null){
            GsGoodsUserClass parent = this.goodsUserClassService.findOne(CommUtil.null2Long(obj.getParentId()));
            obj.setParent(parent);
        }
        model.addAttribute("obj", obj);
        model.addAttribute("ugcs", ugcs);
        model.addAttribute("currentPage", currentPage);

        return "goods/"+ret;
    }
}
