package com.zxy97.blog.util.string;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateTime {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getNow(){
        return sdf.format(new Date());
    }
}
