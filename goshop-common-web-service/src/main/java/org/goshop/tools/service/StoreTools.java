package org.goshop.tools.service;

import org.goshop.assets.pojo.GsAccessory;
import org.goshop.common.service.SysConfig;
import org.goshop.common.service.SystemConfigService;
import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsSpecProperty;
import org.goshop.goods.pojo.GsGoodsSpecification;
import org.goshop.store.i.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desmond on 23/11/2017.
 */
@Component
public class StoreTools {
    @Autowired
    private GoodsClassService goodsClassService;
    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private StoreService storeService;

    public String genericProperty(GsGoodsSpecification spec){
        String val = "";
        for (GsGoodsSpecProperty gsp : spec.getProperties()){
            val = val + "," + gsp.getValue();
        }
        if (!val.equals("")){
            return val.substring(1);
        }

        return "";
    }

    public String createUserFolder(HttpServletRequest request,Long storeId){
        String path = "";
        SysConfig config = this.systemConfigService.getConfig();
        String uploadFilePath = config.getUploadFilePath();
        String sub = (storeId==null)?"store_join":storeId.toString();
        if (config.getImageSaveType().equals("sidImg")){
            path = request.getSession().getServletContext().getRealPath("/") +
                    uploadFilePath + File.separator + "store" +
                    File.separator + sub;
        }

        if (config.getImageSaveType().equals("sidYearImg")){
            path = request.getSession().getServletContext().getRealPath("/") +
                    uploadFilePath + File.separator + "store" +
                    File.separator + sub + File.separator +
                    CommUtil.formatTime("yyyy", new Date());
        }
        if (config.getImageSaveType().equals("sidYearMonthImg")){
            path = request.getSession().getServletContext().getRealPath("/") +
                    uploadFilePath + File.separator + "store" +
                    File.separator + sub + File.separator +
                    CommUtil.formatTime("yyyy", new Date()) + File.separator +
                    CommUtil.formatTime("MM", new Date());
        }
        if (config.getImageSaveType().equals("sidYearMonthDayImg")){
            path = request.getSession().getServletContext().getRealPath("/") +
                    uploadFilePath + File.separator + "store" +
                    File.separator + sub + File.separator +
                    CommUtil.formatTime("yyyy", new Date()) + File.separator +
                    CommUtil.formatTime("MM", new Date()) + File.separator +
                    CommUtil.formatTime("dd", new Date());
        }
        CommUtil.createFolder(path);

        return path;
    }

    public String createUserFolderURL(Long storeId){
        String path = "";
        SysConfig config = this.systemConfigService.getConfig();
        String uploadFilePath = config.getUploadFilePath();
        String sub = (storeId==null)?"store_join":storeId.toString();
        if (config.getImageSaveType().equals("sidImg")){
            path = uploadFilePath + "/store/" + sub;
        }

        if (config.getImageSaveType().equals("sidYearImg")){
            path = uploadFilePath + "/store/" + sub + "/" +
                    CommUtil.formatTime("yyyy", new Date());
        }
        if (config.getImageSaveType().equals("sidYearMonthImg")){
            path = uploadFilePath + "/store/" + sub + "/" +
                    CommUtil.formatTime("yyyy", new Date()) + "/" +
                    CommUtil.formatTime("MM", new Date());
        }
        if (config.getImageSaveType().equals("sidYearMonthDayImg")){
            path = uploadFilePath + "/store/" + sub + "/" +
                    CommUtil.formatTime("yyyy", new Date()) + "/" +
                    CommUtil.formatTime("MM", new Date()) + "/" +
                    CommUtil.formatTime("dd", new Date());
        }

        return path;
    }


    /**
     * 包装accessory数据
     * @param map
     * @param url
     * @param storeId
     * @param userId
     * @return
     */
    public GsAccessory bundleAccessory(Map map,
                                       String url,
                                       Long storeId,
                                       Long userId){
        Map params = new HashMap();
        params.put("store_id", storeId);
//                List wms = this.waterMarkService
//                        .query("select obj from WaterMark obj where obj.store.id=:store_id", params, -1, -1);
//                if (wms.size() > 0){
//                    WaterMark mark = (WaterMark) wms.get(0);
//                    if (mark.isWm_image_open()){
//                        String pressImg = request.getSession().getServletContext().getRealPath("")
//                                + File.separator + mark.getWm_image().getPath()
//                                + File.separator + mark.getWm_image().getName();
//                        String targetImg = path + File.separator + map.get("fileName");
//                        int pos = mark.getWm_image_pos();
//                        float alpha = mark.getWm_image_alpha();
//                        CommUtil.waterMarkWithImage(pressImg, targetImg, pos, alpha);
//                    }
//                    if (mark.isWm_text_open()){
//                        String targetImg = path + File.separator + map.get("fileName");
//                        int pos = mark.getWm_text_pos();
//                        String text = mark.getWm_text();
//                        String markContentColor = mark.getWm_text_color();
//                        CommUtil.waterMarkWithText(
//                                targetImg, targetImg, text, markContentColor,
//                                new Font(mark.getWm_text_font(), 1, mark.getWm_text_font_size()), pos, 100.0F);
//                    }
//                }
        GsAccessory image = new GsAccessory();
        image.setAddtime(new Date());
        image.setExt((String) map.get("mime"));
        image.setPath(url);
        image.setWidth(CommUtil.null2Int(map.get("width")));
        image.setHeight(CommUtil.null2Int(map.get("height")));
        image.setName(CommUtil.null2String(map.get("fileName")));
        image.setUserId(userId);
        image.setSize(CommUtil.null2Float(map.get("fileSize")));

        return image;
    }

    public String generic_goods_class_info(GsGoodsClass gc){
        if (gc != null){
            String goods_class_info = generic_the_goods_class_info(gc);
            return goods_class_info.substring(0, goods_class_info.length());
        }

        return "";
    }


    private String generic_the_goods_class_info(GsGoodsClass gc){
        if (gc != null){
            String goods_class_info = gc.getClassname() ;
            if (gc.getParentId() != null){
                GsGoodsClass g = goodsClassService.findOne(gc.getParentId());
                String class_info = generic_the_goods_class_info(g);
                goods_class_info = g==null?goods_class_info:(class_info +">"+ goods_class_info);
            }
            return goods_class_info;
        }

        return "";
    }

    public int query_store_with_user(String user_id){
        int status = 0;
//        Store store = this.storeService.findByMemberId( CommUtil.null2Long(user_id));
//        if (store != null)
//            status = 1;

        return status;
    }

    public static boolean del_acc(HttpServletRequest request, GsAccessory acc){
        boolean ret = true;
        boolean ret1 = true;
        if (acc != null){
            String path = request.getRealPath("/") + acc.getPath() +
                    File.separator + acc.getName();
            String small_path = request.getRealPath("/") + acc.getPath() +
                    File.separator + acc.getName() + "_small." + acc.getExt();
            ret = CommUtil.deleteFile(path);
            ret1 = CommUtil.deleteFile(small_path);
        }

        return (ret) && (ret1);
    }
}
