//     _                                 ___ _____                    
//  __| |_      ______  ______  ___   _ / _ \___  |___ ___  _ __ ___  
// / _` \ \ /\ / /_  / |_  /\ \/ / | | | (_) | / // __/ _ \| '_ ` _ \ 
//| (_| |\ V  V / / / _ / /  >  <| |_| |\__, |/ /| (_| (_) | | | | | |
// \__,_| \_/\_/ /___(_)___|/_/\_\\__, |  /_//_/(_)___\___/|_| |_| |_|
//                                |___/                               
package com.zxy97.dwz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileSystem {

    final static GetPath PATH = new GetPath();
    public static String URL_ROOT = PATH.getWebRootPath() + PATH.getValueByKey("urlSaveRoot");
    private static final String HTML_NAME = "index.html";

    public static boolean create(String url_short, String url_long) throws FileNotFoundException {
        File fileDir = new File(URL_ROOT + File.separator + url_short);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File fileHtml = new File(URL_ROOT + File.separator + url_short + File.separator + HTML_NAME);
        if (fileHtml.exists()) {
            return false;
        }
        String content = "<head><meta http-equiv=\"refresh\" content=\"0;url=" + url_long + "\"></head>";
        try (PrintWriter pt = new PrintWriter(fileHtml)) {
            pt.append(content);
            pt.flush();
        }
        return true;

    }

    public static String long2HexStr(long num) {
        int i = (int) num;
        return Integer.toHexString(i);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(long2HexStr(122L));
    }

}
