package com.zxy97.make.util;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Util {

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
        return strURL;
    }

    public static void writeFile(String filePath, String text) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(text);
        osw.flush();
        osw.close();
    }

    public static String readToString2(String fileName) throws IOException {
        String FileContent = "";
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            FileContent += line;
            FileContent += "\r\n";
        }
        return FileContent;
    }

    public static String readToString(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return "";
        }
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent, "utf-8");
    }

    public static String replace(String content, HashMap<String, String> map) {
        String t = content;
        for (String key : map.keySet()) {
            String value = map.get(key);
            t = t.replaceAll("##" + key + "##", value);
        }
        return t;
    }

    public static String getSaveFilePath() {
        return "html/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".html";
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(new Util().getWebRootPath());
//        System.out.println(readToString(new Util().getWebRootPath() + "index.jsp"));
//
//        String str = "12345643";
//        System.out.println(str.replaceAll("4", "我"));
//        System.out.println(str);
        //writeFile("C:/1/2/test.html", "123");
        String str = readToString2("D:\\Code\\各大OJ离线\\OJ离线\\index.html");
        System.out.println(str);
    }
}
