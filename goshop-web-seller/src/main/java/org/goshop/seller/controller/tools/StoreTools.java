package org.goshop.seller.controller.tools;

import org.goshop.common.web.utils.CommUtil;
import org.goshop.goods.i.GoodsClassService;
import org.goshop.goods.pojo.GsGoodsAccessory;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.GsGoodsSpecification;
import org.goshop.store.i.StoreService;
import org.goshop.store.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created by Desmond on 23/11/2017.
 */
@Component
public class StoreTools {
    @Autowired
    private GoodsClassService goodsClassService;

    @Autowired
    private StoreService storeService;

    public String genericProperty(GsGoodsSpecification spec){
        String val = "";
//        for (GoodsSpecProperty gsp : spec.getProperties()){
//            val = val + "," + gsp.getValue();
//        }
//        if (!val.equals("")){
//            return val.substring(1);
//        }

        return "";
    }

    public String createUserFolder(HttpServletRequest request,Store store){
        String path = "";
        String uploadFilePath = "upload";
//        String uploadFilePath = config.getUploadFilePath();
//        if (config.getImageSaveType().equals("sidImg")){
//            path = request.getSession().getServletContext().getRealPath("/") +
//                    uploadFilePath + File.separator + "store" +
//                    File.separator + store.getStoreId();
//        }
//
//        if (config.getImageSaveType().equals("sidYearImg")){
//            path = request.getSession().getServletContext().getRealPath("/") +
//                    uploadFilePath + File.separator + "store" +
//                    File.separator + store.getStoreId() + File.separator +
//                    CommUtil.formatTime("yyyy", new Date());
//        }
//        if (config.getImageSaveType().equals("sidYearMonthImg")){
//            path = request.getSession().getServletContext().getRealPath("/") +
//                    uploadFilePath + File.separator + "store" +
//                    File.separator + store.getStoreId() + File.separator +
//                    CommUtil.formatTime("yyyy", new Date()) + File.separator +
//                    CommUtil.formatTime("MM", new Date());
//        }
//        if (config.getImageSaveType().equals("sidYearMonthDayImg")){
            path = request.getSession().getServletContext().getRealPath("/") +
                    uploadFilePath + File.separator + "store" +
                    File.separator + store.getStoreId() + File.separator +
                    CommUtil.formatTime("yyyy", new Date()) + File.separator +
                    CommUtil.formatTime("MM", new Date()) + File.separator +
                    CommUtil.formatTime("dd", new Date());
//        }
        CommUtil.createFolder(path);

        return path;
    }

    public String createUserFolderURL(Store store){
        String path = "";
        String uploadFilePath = "upload";
//        String uploadFilePath = config.getUploadFilePath();
//        if (config.getImageSaveType().equals("sidImg")){
//            path = uploadFilePath + "/store/" + store.getStoreId().toString();
//        }
//
//        if (config.getImageSaveType().equals("sidYearImg")){
//            path = uploadFilePath + "/store/" + store.getStoreId() + "/" +
//                    CommUtil.formatTime("yyyy", new Date());
//        }
//        if (config.getImageSaveType().equals("sidYearMonthImg")){
//            path = uploadFilePath + "/store/" + store.getStoreId() + "/" +
//                    CommUtil.formatTime("yyyy", new Date()) + "/" +
//                    CommUtil.formatTime("MM", new Date());
//        }
//        if (config.getImageSaveType().equals("sidYearMonthDayImg")){
            path = uploadFilePath + "/store/" + store.getStoreId() + "/" +
                    CommUtil.formatTime("yyyy", new Date()) + "/" +
                    CommUtil.formatTime("MM", new Date()) + "/" +
                    CommUtil.formatTime("dd", new Date());
//        }

        return path;
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
//        Store store = this.storeService.getObjByProperty("user.id",
//                CommUtil.null2Long(user_id));
//        if (store != null)
//            status = 1;

        return status;
    }

    public static boolean del_acc(HttpServletRequest request, GsGoodsAccessory acc){
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
