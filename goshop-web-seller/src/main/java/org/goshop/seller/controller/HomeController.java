package org.goshop.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value =  "/home")
public class HomeController {

    @RequestMapping
    public String one(Model model,
                      HttpServletRequest request,
                      HttpServletResponse response) {

        return "home/home";
    }
}
