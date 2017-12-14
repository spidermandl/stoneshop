package org.goshop.common.controller;


import org.goshop.common.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/att")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @RequestMapping
    public void download(String path,String inline,String name,HttpServletResponse response) {
        //File file=attachmentService.download(path);
        //DownloadUtils.download(response, file, inline, name);
        attachmentService.download(path,name,response);
    }

}
