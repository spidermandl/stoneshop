package org.goshop.common.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface AttachmentService {

    /**
     * 上传文件
     * @param path 根路径
     * @param name 尾路径
     * @return
     */
    String upload(String path,String name) throws IOException;
    /**
     * 通过MultipartFile对象保存文件，返回文件名称
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile) throws IOException;

    /**
     * 通过InputStream对象保存文件，返回文件名称
     * @param inputStream
     * @return
     */
    String upload(InputStream inputStream, String type) throws Exception;

    /**
     * 获取文件路径
     */
    String getPath();

    /**
     * 通过id获取文件
     * @param
     * @return
     */
    File download(String id);

    void download(String path, String fileName, HttpServletResponse response);

    String upload(File file, String type) throws Exception;

    void delete(String id);

    /**
     * 获取文件夹大小
     * @param path
     * @return
     */
    double foldSize(String path);

    void deleteFile(String path);
}
