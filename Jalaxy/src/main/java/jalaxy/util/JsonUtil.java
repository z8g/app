/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jalaxy.util;

import jalaxy.model.Tool;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhaoxuyang
 */
public class JsonUtil {

    public static void main(String[] args) {
        Tool tool = new Tool();
        String text = JsonUtil.toJsonString(tool); //序列化
        String str = JsonUtil.parseJson("{ name: 123}", String.class); //反序列化
    }

    public static String toJsonString(Object object) {
        return null;
    }

    public static String parseJson(String jsonString, Class<?> c) {
        System.out.println(c.getName());
        return null;
    }
}
