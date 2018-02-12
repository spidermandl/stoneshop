package org.goshop.tools.pay.paypal;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

public class PaypalUtils {
    public static final String getPath(HttpServletRequest request, String path){
        StringBuffer url = new StringBuffer();
        String s = request.getProtocol();
        url.append(s.substring(0, s.indexOf('/')).toLowerCase());
        url.append("://");
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append(request.getContextPath());
        if (path.charAt(0) != '/'){
            url.append("/");
        }
        url.append(path);
        return url.toString();
    }

    public static final String encode(String value){
        try {
            value = URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException){
        }
        return value;
    }

    public static final Properties execute(HttpPost post) throws Exception {
        Properties props = new Properties();
        CloseableHttpClient client= HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            // 判断返回状态
            if ((statusCode < 200) || (statusCode >= 300)){
                throw new Exception(
                        "HTTP request failed: response status code '" +
                                statusCode + "' received where 2xx expected");
            }
            props.load(response.getEntity().getContent());
            Properties localProperties1 = props;
            return localProperties1;
        } finally {
            if (response != null) {
                post.releaseConnection();
            }
        }
    }
}




