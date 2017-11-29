package org.test;

import org.goshop.common.service.SystemConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by Desmond on 28/11/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
public class CommonWebTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    SystemConfigService systemConfigService;

    @Test
    public void sysConfigTest(){
        //String rule = systemConfigService.getConfig().getCreditrule();
        //System.out.println(rule);
        Properties prop = new Properties();
        try {
            String path = "jar:file:/Users/Desmond/Desktop/goshop2/goshop-web-seller/target/goshop-web-seller-2.0-SNAPSHOT/WEB-INF/lib/goshop-common-config-2.0-SNAPSHOT.jar!/system.properties";
                    //ResourceUtils.getFile("classpath:system.properties").getPath();
            //Resource res = new ClassPathResource("resources/system.properties");
            //读取属性文件a.properties
            InputStream in = //res.getInputStream();
                    new BufferedInputStream(new FileInputStream(path));
            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            //PropertyPlaceholderConfigurer.getProperty("sql.name");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
