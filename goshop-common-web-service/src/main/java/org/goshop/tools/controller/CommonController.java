package org.goshop.tools.controller;

import net.sf.json.JSONObject;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.common.web.utils.JsonUtils;
import org.goshop.store.i.StoreAreaService;
import org.goshop.store.pojo.GsArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 13/12/2017.
 * 通用Controller
 */
@Controller
public class CommonController {

    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    StoreAreaService storeAreaService;
    /**
     * 上传商品说明嵌入图片
     * @param request
     * @param response
     * @throws ClassNotFoundException
     */
    @RequestMapping({"/upload"})
    public void upload(HttpServletRequest request,
                       HttpServletResponse response) throws ClassNotFoundException {

        String saveFilePathName = request.getSession().getServletContext().getRealPath("/") +
                this.systemConfigService.getConfig().getUploadFilePath() + File.separator + "common";
        String webPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
        if ((this.systemConfigService.getConfig().getAddress() != null) &&
                (!this.systemConfigService.getConfig().getAddress().equals(""))){
            webPath = this.systemConfigService.getConfig().getAddress() + webPath;
        }

        JSONObject obj = new JSONObject();
        try {
            Map map = CommUtil.saveFileToServer(request, "imgFile", saveFilePathName, null, null);
            String url = webPath + "/" + this.systemConfigService.getConfig().getUploadFilePath() + "/common/" + map.get("fileName");
            obj.put("error", Integer.valueOf(0));
            obj.put("url", url);
        } catch (IOException e){
            obj.put("error", Integer.valueOf(1));
            obj.put("message", e.getMessage());
            e.printStackTrace();
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {request.setCharacterEncoding("UTF-8");} catch (java.io.UnsupportedEncodingException e1) {e1.printStackTrace();}
        try {
            PrintWriter writer = response.getWriter();
            writer.print(obj.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @RequestMapping({"/load_area"})
    public void load_area(HttpServletRequest request,
                          HttpServletResponse response,
                          String pid){
        Map params = new HashMap();
        params.put("pid", Long.valueOf(Long.parseLong(pid)));
        List<GsArea> areas = this.storeAreaService.findByParentId(Long.parseLong(pid));
        List list = new ArrayList();
        for (GsArea area : areas){
            Map map = new HashMap();
            map.put("id", area.getId());
            map.put("areaname", area.getAreaname());
            list.add(map);
        }
        String temp = JsonUtils.objectToJson(list);
        response.setContentType("text/plain; charset=UTF-8");
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
