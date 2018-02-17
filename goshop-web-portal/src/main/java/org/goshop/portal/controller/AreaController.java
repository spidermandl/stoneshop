package org.goshop.portal.controller;

import org.goshop.common.web.utils.JsonUtils;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.pojo.GsArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 17/02/2018.
 */
@Controller
public class AreaController {

    @Autowired
    private StoreAreaService storeAreaService;

    @RequestMapping({"/load_area"})
    public void load_area(HttpServletRequest request,
                          HttpServletResponse response,
                          String pid){
        List<GsArea> areas = this.storeAreaService.findByParentId(Long.valueOf(Long.parseLong(pid)));
//                "select obj from Area obj where obj.parent.id=:pid"
        List list = new ArrayList();
        for (GsArea area : areas){
            Map map = new HashMap();
            map.put("id", area.getId());
            map.put("areaName", area.getAreaname());
            list.add(map);
        }
        String temp = JsonUtils.objectToJson(list);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (java.io.UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(temp);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
