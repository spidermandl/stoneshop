package org.goshop.seller.controller;


import org.goshop.common.service.AttachmentService;
import org.goshop.common.exception.PageException;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.AlbumPicService;
import org.goshop.store.pojo.AlbumPic;
import org.goshop.goods.pojo.JsonAlbumPic;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping(value =  "/album_pic")
public class AlbumPicController {

    @Autowired
    AlbumPicService albumPicService;

    @Autowired
    AttachmentService attachmentService;

    @RequestMapping("/image/upload")
    public @ResponseBody
    JsonAlbumPic imageUpload(@RequestParam(value="category_id") Integer aclassId,
                             @RequestParam(value = "file") MultipartFile file,
                             @CurrentUser User user,
                             HttpServletRequest request, HttpServletResponse response) {
        Assert.isTrue(file.getSize() == 0, "请上传图片！");
        Assert.isTrue(file.getSize() < 1000000, "图片超过了1M，请编辑后重新上传！");
        String imagePath="";
        try {
            imagePath= attachmentService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PageException("文件保存错误！");
        }
        BufferedImage sourceImg=null;
        try {
            sourceImg = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String apicSpec=sourceImg.getWidth()+"x"+sourceImg.getHeight();
        AlbumPic albumPic= albumPicService.save(user.getId(),aclassId, imagePath, file.getSize(), file.getName(),file.getOriginalFilename(),apicSpec);
        JsonAlbumPic jsonImage=new JsonAlbumPic();
        jsonImage.setFile_id(albumPic.getApicId());
        jsonImage.setFile_name(albumPic.getApicName());
        jsonImage.setFile_path(albumPic.getApicCover());
        jsonImage.setOrigin_file_name(albumPic.getApicTag());
        jsonImage.setState(true);
        return jsonImage;
    }
}
