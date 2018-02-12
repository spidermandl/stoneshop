package org.goshop.resource.controller;

import org.goshop.common.service.SystemConfigService;
import org.goshop.common.utils.FileUtils;
import org.goshop.common.web.utils.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Desmond on 25/01/2018.
 */
@Controller
public class ResourceController {

    @Autowired
    SystemConfigService systemConfigService;

    /**
     * 上传文件
     * @param request
     * @param response
     * @param file
     * @param name
     */
    @RequestMapping({"/store"})
    public void upload(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "uploadFile", required = false) MultipartFile file,
                       @RequestParam(value = "uploadFileName", required = false) String name) {
        String root = request.getSession().getServletContext().getRealPath("/");
        try {
            FileUtils.saveFile(file.getInputStream(), root + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目录大小
     * @param request
     * @param response
     * @param path
     * @return
     */
    @RequestMapping({"/folder_size"})
    @ResponseBody
    public Double size(HttpServletRequest request,
                       HttpServletResponse response,
                       String path){
        String abs_path = request.getSession().getServletContext().getRealPath("/")+path;
        if(!CommUtil.createFolder(abs_path))
            return 0D;
        double csize = CommUtil.fileSize(new File(path));
        return csize;
    }

    /**
     * 删除文件
     * @param request
     * @param response
     * @param path
     */
    @RequestMapping({"/file_delete"})
    public void delete(HttpServletRequest request,
                       HttpServletResponse response,
                       String path) {
        String abs_path = request.getSession().getServletContext().getRealPath("/")+path;
        FileUtils.deleteFile(abs_path);
    }



}
