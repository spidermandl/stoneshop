package org.goshop.portal.controller;

import org.apache.shiro.SecurityUtils;
import org.goshop.common.service.SysConfig;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.HttpInclude;
import org.goshop.shiro.Constants;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Desmond on 16/01/2018.
 */
abstract public class BaseController {

    @Autowired
    protected SystemConfigService systemConfigService;
    /**
     * 加入model参数
     * @param model
     * @param request
     * @param response
     */
    protected void reCapsuleModel(Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
//        (User)request.getAttribute(Constants.CURRENT_USER);
        model.addAttribute("config",systemConfigService.getConfig());
        model.addAttribute("CommUtil",new CommUtil());
        model.addAttribute("httpInclude", new HttpInclude(request, response));
    }
    /***
     * @param viewName
     * @return
     */
    protected String generateViewURL(String viewName){
        String lang = systemConfigService.getConfig().getSysLanguage();
        if ( lang != null){
            if (lang.equals("zh_cn")){
                return "store/" + viewName;
            }else{
                return "store/" + viewName;
            }
        }else{
            return "store/" + viewName;
        }
    }
}
