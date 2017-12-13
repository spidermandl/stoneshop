package org.goshop.seller.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Desmond on 13/12/2017.
 */
@Controller
@RequestMapping(value =  "/unauthorized")
public class UnauthorizedController {

    @RequestMapping("")
    public String refuse(Model model,
                         HttpServletRequest request,
                         HttpServletResponse response){
        String ret = "/refuse/index";
        Subject subject = SecurityUtils.getSubject();
        if (subject==null) {
            model.addAttribute("refuse_type", "NoUser");
            return ret;
        }
        boolean permitted = subject.isPermitted("seller:read");
        if (!permitted) {
            model.addAttribute("refuse_type", "NoShop");
            return ret;
        }
        return ret;
    }
}
