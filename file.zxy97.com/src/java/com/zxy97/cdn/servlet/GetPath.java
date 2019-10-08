package com.zxy97.cdn.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class GetPath {

    public String[] getSuffixArray() throws IOException {
        String str = getValueByKey("suffixArray");
        return str.split(" ");
    }

    public String getValueByKey(String key) {
        try {
            return getValueByKey(getWebRootPath() + "/WEB-INF/config.properties", key);
        } catch (IOException ex) {
            return null;
        }
    }

    private String getValueByKey(String configFilePath, String key) throws FileNotFoundException, IOException {
        String value;
        Properties prop = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(configFilePath));
        prop.load(in);
        value = prop.getProperty(key);
        return value;

    }

    /**
     * @return WebRoot的绝对路径
     */
    public String getWebRootPath() {
        final String strClassName = getClass().getName();

        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }

        String strClassFileName;
        if ("".equals(strPackageName)) {
            strClassFileName = strClassName;
        } else {
            strClassFileName = strClassName.substring(strPackageName.length() + 1, strClassName.length());
        }

        URL url = getClass().getResource(strClassFileName + ".class");

        String strURL = url.toString();
        strURL = strURL.substring(strURL.indexOf("/") + 1, strURL.lastIndexOf("WEB-INF"));

        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return strURL;
        }

        return "/" + strURL;
    }
}
