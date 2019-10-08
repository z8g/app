/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 

package net.zhaoxuyang.blog.function;

import java.lang.invoke.MethodHandles;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintTime implements Function {
    final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Override
    public String call(Object[] objects, Context context) {
        LOG.info("String call");
        LOG.info("objects=" + Arrays.toString(objects));
        LOG.info("context=" + context);
        Date date = (Date) objects[0];

        Date now = new Date();
        Long fiveM = date.getTime() + (5*60*1000);
        Long thM = date.getTime() + (30*60*1000);
        Long oneH = date.getTime() + (60*60*1000);
        if(now.getTime() < fiveM){
            return "刚刚";
        }
        if(now.getTime() < thM){
            return "半小时前";
        }
        if(now.getTime() < oneH){
            return "一小时前";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(objects[1].toString());

        return sdf.format(date);
    }
}
