package org.goshop.common.service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 26/01/2018.
 */
public class RemovehostAttachmentImpl implements AttachmentService {

    @Autowired
    SystemConfigService systemConfigService;

    @Value("${RES_URL}")
    private String remotePath;

    // 创建Httpclient对象
    @Autowired(required = false)
    private CloseableHttpClient httpClient;

    // 请求信息的配置
    @Autowired(required = false)
    private RequestConfig requestConfig;

    @Override
    public String upload(String path, String name) throws IOException {
        File file = new File(path);
        postFile(remotePath+"/store",file,name);
        return null;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String type) throws Exception {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public File download(String id) {
        return null;
    }

    @Override
    public void download(String path, String fileName, HttpServletResponse response) {

    }

    @Override
    public String upload(File file, String type) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public double foldSize(String path) {
        try {
            String result = doGet(remotePath+"/folder_size?path="+path);
            return Double.parseDouble(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deleteFile(String path) {
        try {
            doGet(remotePath+"/file_delete?"+path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行Get请求
     *
     * @param url
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url) throws URISyntaxException,
            ClientProtocolException, IOException {
        return doGet(url, null);
    }

    /**
     * 执行Get请求
     *
     * @param url
     * @param params
     *            请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url, Map<String, Object> params)
            throws URISyntaxException, ClientProtocolException, IOException {
        // 定义请求的参数
        URI uri = null;
        if (params != null) {
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addParameter(entry.getKey(),
                        String.valueOf(entry.getValue()));
            }
            uri = builder.build();
        }

        // 创建http GET请求
        HttpGet httpGet = null;
        if (uri != null) {
            httpGet = new HttpGet(uri);
        } else {
            httpGet = new HttpGet(url);
        }
        // 设置请求参数
        httpGet.setConfig(this.requestConfig);

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     *
     * @param url
     * @param params
     *            请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url, Map<String, Object> params)
            throws URISyntaxException, ClientProtocolException, IOException {
        // 设置post参数
        List<NameValuePair> parameters = null;
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = null;

        // 定义请求的参数
        if (params != null) {
            // 设置post参数
            parameters = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                // 添加参数
                parameters.add(new BasicNameValuePair(entry.getKey(), String
                        .valueOf(entry.getValue())));
            }
            // 构造一个form表单式的实体
            formEntity = new UrlEncodedFormEntity(parameters);
        }

        // 创建http GET请求
        HttpPost httpPost = null;
        if (formEntity != null) {
            httpPost = new HttpPost(url);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
            // 伪装浏览器请求
//            httpPost.setHeader(
//                    "User-Agent",
//                    "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
        } else {
            httpPost = new HttpPost(url);
            // 伪装浏览器请求
//            httpPost.setHeader(
//                    "User-Agent",
//                    "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
        }
        // 设置请求参数
        httpPost.setConfig(this.requestConfig);

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     *
     * @param url 请求中的参数
     * @return 请求到的内容
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(String url) throws URISyntaxException,
                                ClientProtocolException, IOException {
        return doPost(url, null);
    }

    /**
     * 上传文件
     *
     */
    public void postFile(String url ,File file,String name) throws ParseException, IOException {
        // 把一个普通参数和文件上传给下面这个地址 是一个servlet
        HttpPost httpPost = new HttpPost(url);

        FileBody f_body = new FileBody(file);
        StringBody s_body = new StringBody(name, ContentType.TEXT_PLAIN);
        /*StringBody uploadFileName = new StringBody("my.png",
                ContentType.create("text/plain", Consts.UTF_8));*/
        // 以浏览器兼容模式运行，防止文件名乱码。
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addPart("uploadFile", f_body) // uploadFile对应服务端类的同名属性<File类型>
                .addPart("uploadFileName", s_body)// uploadFileName对应服务端类的同名属性<String类型>
                .setCharset(CharsetUtils.get("UTF-8")).build();

        httpPost.setEntity(reqEntity);

        // 设置请求参数
        httpPost.setConfig(this.requestConfig);

        // 请求的结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取服务端返回的数据,并返回
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
