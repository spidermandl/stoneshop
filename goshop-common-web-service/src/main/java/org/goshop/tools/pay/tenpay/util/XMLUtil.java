package org.goshop.tools.pay.tenpay.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMLUtil {
    public static Map doXMLParse(String strxml)
    throws DocumentException, IOException {
        if ((strxml == null) || ("".equals(strxml))){
            return null;
        }

        Map m = new HashMap();
        InputStream in = HttpClientUtil.String2Inputstream(strxml);

        SAXReader builder = new SAXReader();
        Document doc = builder.read(in);
        Element root = doc.getRootElement();
        List list = root.elements();
        Iterator it = list.iterator();
        while (it.hasNext()){
            Element e = (Element)it.next();
            String k = e.getName();
            String v = "";
            List children = e.elements();
            if (children.isEmpty())
                v = e.getText();
            else{
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        in.close();

        return m;
    }

    public static String getChildrenText(List children){
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()){
            Iterator it = children.iterator();
            while (it.hasNext()){
                Element e = (Element)it.next();
                String name = e.getName();
                String value = e.getText();
                List list = e.elements();
                sb.append("<" + name + ">");
                if (!list.isEmpty()){
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static String getXMLEncoding(String strxml)
    throws DocumentException, IOException {
        InputStream in = HttpClientUtil.String2Inputstream(strxml);
        SAXReader builder = new SAXReader();
        Document doc = builder.read(in);
        in.close();
        return doc.getXMLEncoding();
    }
}




