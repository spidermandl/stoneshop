package org.goshop.seller.controller;

import com.github.pagehelper.PageInfo;
import org.goshop.common.web.utils.ResponseMessageUtils;
import org.goshop.shiro.bind.annotation.CurrentUser;
import org.goshop.store.i.AlbumClassService;
import org.goshop.store.pojo.AlbumClass;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value =  "/album")
public class AlbumController {

    @Autowired
    AlbumClassService albumClassService;

    @RequestMapping("/space")
    public String space(@RequestParam(value="p",required=false) Integer curPage,
                        @RequestParam(value = "sort",required=false) Integer sort,
                        Model model,
                        @CurrentUser User user,
                      HttpServletRequest request,
                      HttpServletResponse response) {

        PageInfo<AlbumClass> page=albumClassService.findByStoreId(curPage, 20, user.getId(),sort);
        model.addAttribute("P_PAGE",page);
        return "goods/album_space";
    }

    @RequestMapping("/add_page")
    public String addPage(Model model,@CurrentUser User user, HttpServletRequest request,
                          HttpServletResponse response) {

        Integer count = albumClassService.findCountByStoreId(user.getId());

        model.addAttribute("P_CLASS_COUNT", count);
        return "goods/album_add_page";
    }

    @RequestMapping("/check_name")
    public @ResponseBody
    Boolean getClass(@RequestParam("ac_name") String name,@CurrentUser User user,
                     HttpServletRequest request, HttpServletResponse response) {
        AlbumClass albumClass = albumClassService.findOneByAclassNameAndStoreId(name, user.getId());
        return albumClass == null;
    }

    @RequestMapping("/save_class")
    public @ResponseBody
    void  saveClass(AlbumClass albumClass,@CurrentUser User user,
                    HttpServletRequest request, HttpServletResponse response) {

        String url=request.getContextPath()+"/album/space";
        String name = "相册创建成功";
        try {
            albumClassService.save(albumClass,user.getId());
        } catch (Exception e) {
            name = "数据库异常！";
        }
        ResponseMessageUtils.xmlCDataOut(response, name, url);
    }

    @RequestMapping("/del_class")
    public @ResponseBody
    void  delClass(@RequestParam("id") Integer id,@CurrentUser User user,
                   HttpServletRequest request, HttpServletResponse response) {
        String url=request.getContextPath()+"/album/space";
        String name="相册删除成功";
        try {
            albumClassService.delete(id, user.getId());
        } catch (Exception e) {
            name = e.getMessage();
        }
        ResponseMessageUtils.xmlCDataOut(response, name, url);
    }

    @RequestMapping("/update_page")
    public String updatePage(@RequestParam("id") Integer aclassId,
                             @CurrentUser User user,
                             Model model, HttpServletRequest request,
                             HttpServletResponse response) {

        AlbumClass albumClass=albumClassService.findByAclassIdAndUserId(aclassId, user.getId());
        Assert.notNull(albumClass, aclassId+ ",没有找到！");

        model.addAttribute("P_ALBUMCLASS", albumClass);
        return "goods/album_update_Page";
    }

    @RequestMapping("/update_class")
    public @ResponseBody
    void  updateClass(AlbumClass albumClass,
                      @CurrentUser User user,
                      HttpServletRequest request, HttpServletResponse response) {

        String url=request.getContextPath()+"/album/space";
        String name="相册修改成功";
        try {
            albumClassService.update(albumClass, user.getId());
        }catch (Exception e){
            name = e.getMessage();
        }
        ResponseMessageUtils.xmlCDataOut(response, name, url);
    }
}
