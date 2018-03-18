package org.goshop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController{

    @Override
    protected String rootTemplatePath() {
        return "";
    }

    /**
     * 打开首页
     */
    @RequestMapping
    public String index(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        reCapsuleModel(model,request,response);
        return "index";
    }



}
