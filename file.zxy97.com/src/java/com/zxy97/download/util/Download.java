package com.zxy97.download.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhaoxuyang
 */
public class Download {

    public static void main(String[] args) throws IOException {
        //http://www.xxx.com/data
        //http://www.xxx.com/data
        //http://www.xxx.com/data.txt
        //http://www.xxx.com/data.xhjhj?asask
        String folderName = "d:/qq";
        File saveFolder = new File(folderName);
        File[] files = saveFolder.listFiles();
        int len = files.length;
        files = null;
        for (long qq = 100000+ len; qq < 9999999999L; qq++) {
            String src = "http://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640";
            File folder = new File(folderName);
            String fileName = qq + ".jpg";
            boolean isDownload = download(folder.getAbsolutePath(), fileName, src);
            System.out.println(qq+" | "+isDownload);
        }
    }

    public static String getFileName(String url) {

        int index = url.lastIndexOf(".");
        if (index != -1) {
            String str = url.substring(index);
            int index2 = str.indexOf("?");
            if (index2 >= 2) {
                return str.substring(0, index2 - 1);
            } else {
                return str;
            }
        } else {
            int index3 = url.lastIndexOf("/");
            if (index3 != -1 && (index3 + 1) != url.length()) {
                return url.substring(index3 + 1);
            } else {
                return "";
            }
        }
    }

    public static String getFolderName(String url) {
        int indexStart = url.indexOf("/");
        int indexEnd = url.lastIndexOf("/");
        return "download" + url.substring(indexStart + 1, indexEnd);
    }

    public static boolean download(String folderName, String fileName, String src) {
        boolean isDownload;
        try {
            File folder = new File(folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(folder.getAbsolutePath() + File.separator + fileName);
            URL url = new URL(src);
            FileOutputStream fos;
            try (InputStream is = url.openStream()) {
                fos = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int read;
                while ((read = is.read(b, 0, b.length)) != -1) {
                    fos.write(b, 0, read);
                }
                isDownload = true;
            }
            fos.close();
        } catch (MalformedURLException ex) {
            isDownload = download2(folderName, fileName, src);//换种方式下载
        } catch (FileNotFoundException ex) {
            isDownload = download2(folderName, fileName, src);//换种方式下载
        } catch (IOException ex) {
            isDownload = download2(folderName, fileName, src);//换种方式下载
        }
        return isDownload;
    }

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @return
     */
    public static boolean download2(String savePath, String fileName, String urlStr) {
        boolean isDownload = false;
        FileOutputStream fos = null;
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3 * 1000);//设置超时间为3秒
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");//防止屏蔽程序抓取而返回403错误
            InputStream inputStream = conn.getInputStream();//得到输入流
            byte[] getData = readInputStream(inputStream);//获取自己数组
            File saveDir = new File(savePath);//文件保存位置
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            File file = new File(saveDir + File.separator + fileName);
            fos = new FileOutputStream(file);
            fos.write(getData);
            fos.close();
            if (inputStream != null) {
                inputStream.close();
            }
            isDownload = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isDownload;
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
