package org.goshop.seller.controller;

/**
 * Created by Desmond on 16/12/2017.
 */

import com.github.pagehelper.PageInfo;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.StoreJoinService;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.goshop.users.i.UserService;
import org.goshop.users.pojo.GsPermission;
import org.goshop.users.pojo.GsUserBanPerm;
import org.goshop.users.pojo.Member;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

/**
 * 店铺导航控制器
 */
@Controller
@RequestMapping(value =  "/store")
public class StoreSubAccountController {

    @Autowired
    StoreJoinService storeJoinService;
    @Autowired
    StoreService storeService;
    @Autowired
    UserService userService;

    /**
     * 子账户列表
     * @param request
     * @param response
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @return
     */
    @RequestMapping({"/sub_account_list"})
    public String sub_account_list(@CurrentUser User user,
                                     Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String currentPage,
                                     String orderBy,
                                     String orderType){
        String ret = "sub_account_list";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        Store store = this.storeService.findOne(storeId);
        model.addAttribute("store", store);
        model.addAttribute("CommUtil",new CommUtil());
        PageInfo<User> pList = this.userService.findChilds(user.getId(),CommUtil.null2Int(currentPage),12);
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, model);

        return "store/"+ret;
    }

    /**
     * 子账户添加
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/sub_account_add"})
    public String sub_account_add(@CurrentUser User user,
                                  Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        String ret = "sub_account_add";
        Long storeId = this.storeJoinService.getCurrentStore(user).getStoreId();
        Store store = this.storeService.findOne(storeId);
        if (store == null){
            ret = "error";
            model.addAttribute("op_title", "您尚未开设店铺");
            model.addAttribute("url", CommUtil.getURL(request) + "/store/index");
        }

        List<User> users = this.userService.findChilds(user.getId());
        if (users.size() >= store.getStoreGrade().getSgAccountNum()){
            ret = "error";
            model.addAttribute("op_title", "您的店铺等级不能继续添加子账户,请升级店铺等级");
            model.addAttribute("url", CommUtil.getURL(request) + "/store/store_grade");
        }
        model.addAttribute("store", store);
        List rgs = this.userService.findPermissionGroupByType("SELLER","addTime","asc");
        model.addAttribute("rgs", rgs);

        return "store/"+ret;
    }

    /**
     * 子账户编辑
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping({"/sub_account_edit"})
    public String sub_account_edit(Model model,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     String id){
        String ret = "sub_account_add";
        User user = this.userService.findOne(CommUtil.null2Long(id));
        Store store = this.storeJoinService.getCurrentStore(user);
        if (store == null){
            ret = "error";
            model.addAttribute("op_title", "您尚未开设店铺");
            model.addAttribute("url", CommUtil.getURL(request) + "/store/index");
        }
        model.addAttribute("store", store);
        List rgs = this.userService.findPermissionGroupByType("SELLER","addTime","asc");
        List perms = this.userService.findPermissionListByUserId(user.getId());
        user.setPerms(perms);

        model.addAttribute("rgs", rgs);
        model.addAttribute("obj", user);

        return "store/"+ret;
    }


    /**
     * 子账户保存
     * @param request
     * @param response
     * @param id
     * @param userName
     * @param trueName
     * @param sex
     * @param birthday
     * @param QQ
     * @param telephone
     * @param mobile
     * @param password
     * @param role_ids
     */
    @RequestMapping({"/sub_account_save"})
    public void sub_account_save(@CurrentUser User parent,
                                 Model model,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 String id,
                                 String userName,
                                 String trueName,
                                 String sex,
                                 String birthday,
                                 String QQ,
                                 String telephone,
                                 String mobile,
                                 String password,
                                 String role_ids){
        boolean ret = true;
        String msg = "保存成功";
        Long storeId = this.storeJoinService.getCurrentStore(parent).getStoreId();
        Store store = this.storeService.findOne(storeId);
        userName = CommUtil.clearContent(userName);
        List<User> users = this.userService.findChilds(parent.getId());
        if (users.size() >= store.getStoreGrade().getSgAccountNum()){
            ret = false;
            msg = "已经超过子账户上线";
        }else if (CommUtil.null2String(id).equals("")){
            User user = new User();
            user.setCreated(new Timestamp(new Date().getTime()));
            user.setUserName(userName);
            user.setLoginName(userName);
            user.setParentId(parent.getId());
//            user.setTrueName(trueName);
//            user.setSex(CommUtil.null2Int(sex));
//            user.setBirthday(CommUtil.formatDate(birthday));
//            user.setQQ(QQ);
//            user.setMobile(mobile);
//            user.setTelephone(telephone);
//            user.setParent(parent);
//            user.setUserRole("BUYER_SELLER");
            user.setPassword(password);

            /**** 插入用户 ****/
            long uId = this.userService.save(user);
            user.setId(uId);
            /*** 修改用户角色 ****/
            userService.addRole2User(user,"seller");
            /*** 过滤权限 ****/
            banPermission(uId,role_ids.split(","));
        }else{
            User user = this.userService.findOne(CommUtil.null2Long(id));
            user.setUserName(userName);
            user.setLoginName(userName);
            user.setUpcreated(new Timestamp(new Date().getTime()));
//            user.setTrueName(trueName);
//            user.setSex(CommUtil.null2Int(sex));
//            user.setBirthday(CommUtil.formatDate(birthday));
//            user.setQQ(QQ);
//            user.setMobile(mobile);
//            user.setTelephone(telephone);
//            user.getRoles().clear();

            /*** 过滤权限 ****/
            banPermission(user.getId(),role_ids.split(","));
            /*** 修改用户角色 ****/
            userService.addRole2User(user,"seller");
            /**** 更新用户 ****/
            this.userService.update(user);
            msg = "更新成功";
        }

        Map map = new HashMap();
        map.put("ret", Boolean.valueOf(ret));
        map.put("msg", msg);
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (java.io.UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.objectToJson(map));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 子账户删除
     * @param request
     * @param response
     * @param mulitId
     * @return
     */
    @RequestMapping({"/sub_account_del"})
    public String sub_account_del(HttpServletRequest request,
                                  HttpServletResponse response,
                                  String mulitId){
        User user = this.userService.findOne(CommUtil.null2Long(mulitId));
        this.userService.delete(user.getId());

        return "redirect:sub_account_list";
    }

    /****************************************************************************************************
     * private
     ***************************************************************************************************/
    /**
     * 增加禁止权限
     * @param userId
     * @param str_p_ids
     */
    private void banPermission(Long userId,String[] str_p_ids){
        List<GsPermission> all = this.userService.findPermissionListByType("SELLER");
        List<Long> permIds = new ArrayList<>();
        for (String s_id : str_p_ids){
            Long id = CommUtil.null2Long(s_id);
            if (id!=-1)
                permIds.add(id);
        }
        List<GsPermission> checks = this.userService.findPermissionListByIds(permIds);
        Iterator<GsPermission> it = all.iterator();
        while(it.hasNext()){
            GsPermission a = it.next();
            for (GsPermission g:checks){
                if(a.getId()==g.getId()){
                    it.remove();
                    break;
                }
            }
        }
        List<GsUserBanPerm> bans = new ArrayList<>();
        for (int i=0;i<all.size();i++){
            GsUserBanPerm ban = new GsUserBanPerm();
            ban.setuId(userId);
            ban.setPermissionId(all.get(i).getId());
            bans.add(ban);
        }
        this.userService.addBanPermission(bans);
    }
}
